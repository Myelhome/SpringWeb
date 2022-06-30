package com.example.demoTest.controller;

import com.example.demoTest.entity.UserEntity;
import com.example.demoTest.exceptions.NoSuchUserException;
import com.example.demoTest.exceptions.UserAlreadyExistsException;
import com.example.demoTest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity getOneUser(@RequestParam Long id){
        try {
            return ResponseEntity.ok(userService.getOne(id));
        }catch (NoSuchUserException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Ошибочка вышла " + e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity getAllUsers(){
        try {
            return ResponseEntity.ok(userService.getAll());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Ошибочка вышла " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<String> registration(@RequestBody UserEntity user) {
        try {
            userService.registration(user);
            return ResponseEntity.ok("Пользователь был успешно сохранен");
        }catch (UserAlreadyExistsException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибочка вышла " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        try {
            userService.delete(id);
            return ResponseEntity.ok("Пользователь id:" + id + " был удален" );
        }catch (NoSuchUserException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибочка вышла " + e.getMessage());
        }
    }
}
