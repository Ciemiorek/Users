package com.ciemiorek.users.controllers;

import com.ciemiorek.users.API.request.BookRequest;
import com.ciemiorek.users.repository.BookRepository;
import com.ciemiorek.users.services.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {

    BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(value = "/add",produces = "application/json")
    public ResponseEntity addBook(@RequestBody BookRequest bookRequest ){
        return bookService.addBook(bookRequest);


    }



}
