package com.example.demoTest.service;

import com.example.demoTest.entity.TaskEntity;
import com.example.demoTest.entity.UserEntity;
import com.example.demoTest.exceptions.NoSuchTaskException;
import com.example.demoTest.exceptions.NoSuchUserException;
import com.example.demoTest.model.Task;
import com.example.demoTest.repository.TaskRepo;
import com.example.demoTest.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    @Autowired
    private TaskRepo taskRepo;
    @Autowired
    private UserRepo userRepo;

    public Task createTask(TaskEntity task, Long userId) throws NoSuchUserException {
        UserEntity user = userRepo.findById(userId).orElseThrow(() -> new NoSuchUserException("Пользователя №" + userId + " не существует!"));
        task.setUser(user);
        return Task.toModel(taskRepo.save(task));
    }

    public Task complete(Long id) throws NoSuchTaskException {
        TaskEntity task = taskRepo.findById(id).orElseThrow(() -> new NoSuchTaskException("Задачи №" + id + " не существует!"));
        if (!task.getCompletion()) task.setCompletion(true);
        return Task.toModel(taskRepo.save(task));
    }
}
