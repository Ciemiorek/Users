package com.ciemiorek.users.controllers;

import com.ciemiorek.users.API.request.UserRequest;
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
    public ResponseEntity getUserById(@RequestParam Long id)  {
            return userService.getUserByID(id);
    }

    @GetMapping("/all")

    public ResponseEntity getUsers(){
        return userService.getUsers();
    }

    @PostMapping(value = "/add",produces = "application/json")
    public ResponseEntity addUser(@RequestBody UserRequest userRequest)  {
        return  userService.addUser(userRequest);
    }

    @PutMapping(value = "/borrow", produces = "application/json")
    public ResponseEntity borrowBook(@RequestParam Long bookId, @RequestParam Long userID){
        return userService.borrowBook(bookId,userID);
    }

    @PutMapping(value = "/returnBook", produces = "application/json")
    public ResponseEntity returnBook(@RequestParam Long bookId, @RequestParam Long userID){
        return userService.returnBook(bookId,userID);
    }

}
