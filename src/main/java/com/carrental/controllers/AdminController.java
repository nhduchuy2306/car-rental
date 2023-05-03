package com.carrental.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carrental.entites.Role;
import com.carrental.entites.User;
import com.carrental.utils.ResponseUtils;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/v1/admin")
@SecurityRequirement(name = "bearerAuth")
public class AdminController {
    
    private List<User> users = Arrays.asList(
        User.builder().id(1).firstname("John").lastname("Doe").email("admin@gmail.com").password("admin").role(Role.ADMIN).build(),
        User.builder().id(2).firstname("Jane").lastname("Doe").email("jane@gmail.com").password("jane").role(Role.USER).build(),
        User.builder().id(3).firstname("Jack").lastname("Doe").email("jack@gmail.com").password("jack").role(Role.USER).build()
    );

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Integer id) {
        try {
            return ResponseEntity.ok(users.get(id));
        } catch (Exception e) {
            return new ResponseEntity<>(
                ResponseUtils.error(
                    HttpStatus.NOT_FOUND,
                    "User not found",
                    "User with id " + id + " not found"
                ),
                HttpStatus.NOT_FOUND
            );
        }
    }

    @PostMapping("/users")
    public ResponseEntity<?> addUser(User user) {
        users.add(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Integer id) {
        User user = users.get(id);
        users.remove(user);
        return ResponseEntity.ok().build();
    }

}
