package com.ciemiorek.users.controllers;

import com.ciemiorek.users.API.request.UserRequest;
import com.ciemiorek.users.repository.UserRepository;
import com.ciemiorek.users.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping ("/{id}")
    public ResponseEntity getUserById(@RequestParam Long id) throws Exception {
            return userService.getUserByID(id);
    }

    @GetMapping("/all")

    public ResponseEntity getUsers(){
        return userService.getUsers();
    }

    @PostMapping(value = "/add",produces = "application/json")
    public ResponseEntity addUser(@RequestBody UserRequest userRequest) throws Exception {
        return  userService.addUser(userRequest);
    }



}
