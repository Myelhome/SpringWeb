package com.example.demoTest.controller;

import com.example.demoTest.entity.TaskEntity;
import com.example.demoTest.exceptions.NoSuchTaskException;
import com.example.demoTest.exceptions.NoSuchUserException;
import com.example.demoTest.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity createTask(@RequestBody TaskEntity task,
                                     @RequestParam Long userId){
        try {
            return ResponseEntity.ok(taskService.createTask(task, userId));
        }catch (NoSuchUserException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Ошибочка вышла: " + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity createTask(@RequestParam Long id){
        try {
            return ResponseEntity.ok(taskService.complete(id));
        }catch (NoSuchTaskException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Ошибочка вышла: " + e.getMessage());
        }
    }
}
