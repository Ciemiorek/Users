package com.ciemiorek.users.services;

import com.ciemiorek.users.API.request.UserRequest;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity getUserByID(Long id) ;
    ResponseEntity addUser(UserRequest userRequest) ;
    ResponseEntity getUsers();
    ResponseEntity borrowBook(double bookIsbn, Long userID);
}
