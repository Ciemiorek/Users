package com.ciemiorek.users.controllers;

import com.ciemiorek.users.models.UserTest;
import com.ciemiorek.users.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {


private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping ("/id")
    public ResponseEntity getUserById(@RequestParam Long id){
            return ResponseEntity.ok(userRepository.findById(id));
    }

    @GetMapping("/all")

    public ResponseEntity getUsers(){
        return ResponseEntity.ok(userRepository.findAll());
    }



}
