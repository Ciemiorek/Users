package com.ciemiorek.users.services.imp;

import com.ciemiorek.users.API.request.BookRequest;
import com.ciemiorek.users.API.response.AddBookResponse;
import com.ciemiorek.users.models.Book;
import com.ciemiorek.users.repository.BookRepository;
import com.ciemiorek.users.services.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static com.google.common.base.Strings.isNullOrEmpty;
import static java.util.Objects.isNull;

@Service
public class BookServiceImp implements BookService {

    private  final BookRepository bookRepository;

    public BookServiceImp(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public ResponseEntity addBook(BookRequest bookRequest) {

        verifyBookRequest(bookRequest);
        Book book = addBookToDataSource(bookRequest);

        return ResponseEntity.ok(new AddBookResponse("the book has been added",book.getId()));
    }


    private void verifyBookRequest(BookRequest bookRequest){
        if(isNullOrEmpty(bookRequest.getAuthor())
            || isNullOrEmpty(bookRequest.getName())
            || isNull(bookRequest.getIsbn())){
            throw new RuntimeException("Wrong BookRequest");
    }}

    private Book addBookToDataSource(BookRequest bookRequest){
        Book book = new Book(bookRequest.getName(),bookRequest.getAuthor(),bookRequest.getIsbn());
       return bookRepository.save(book);
    }
}