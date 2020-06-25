package com.ciemiorek.users.services;

import com.ciemiorek.users.API.request.UserRequest;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity getUserByID(Long id) ;
    ResponseEntity addUser(UserRequest userRequest) ;
    ResponseEntity getUsers();
    ResponseEntity borrowBook(Long bookIsbn, Long userID);
    ResponseEntity returnBook(Long bookId, Long userID);


}
