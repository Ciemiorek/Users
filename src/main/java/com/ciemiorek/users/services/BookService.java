package com.ciemiorek.users.services;

import com.ciemiorek.users.API.request.BookRequest;
import org.springframework.http.ResponseEntity;

public interface BookService  {

ResponseEntity addBook(BookRequest bookRequest);
ResponseEntity getAllBooks();
ResponseEntity getBookById(Long id) throws Exception;
}
