package com.example.demoTest.service;

import com.example.demoTest.entity.UserEntity;
import com.example.demoTest.exceptions.NoSuchUserException;
import com.example.demoTest.exceptions.UserAlreadyExistsException;
import com.example.demoTest.model.User;
import com.example.demoTest.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    public UserRepo userRepo;

    public UserEntity registration(UserEntity user) throws UserAlreadyExistsException {
        if(userRepo.findByUsername(user.getUsername()) != null) throw new UserAlreadyExistsException("Пользователь с таким логином уже существует");
        return userRepo.save((user));
    }

    public User getOne(Long id) throws NoSuchUserException {
        UserEntity user = userRepo.findById(id).orElseThrow(() -> new NoSuchUserException("Пользователя №" + id + " не существует!"));
        return User.toModel(user);
    }

    public List<User> getAll(){
        return userRepo.findAll().stream().map(User::toModel).collect(Collectors.toList());
    }

    public Long delete(Long id) throws NoSuchUserException {
        if(userRepo.findById(id).isEmpty()) throw new NoSuchUserException("Пользователя №" + id + " не существует!");
        userRepo.deleteById(id);
        return id;
    }
}
