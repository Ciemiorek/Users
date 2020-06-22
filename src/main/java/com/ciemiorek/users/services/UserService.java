package com.ciemiorek.users.services;

import com.ciemiorek.users.API.request.UserRequest;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity getUserByID(Long id) throws Exception;
    ResponseEntity addUser(UserRequest userRequest) throws Exception;
    ResponseEntity getUsers();
}
