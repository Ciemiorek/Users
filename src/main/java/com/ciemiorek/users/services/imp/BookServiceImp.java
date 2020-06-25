package com.ciemiorek.users.services.imp;

import com.ciemiorek.users.API.request.BookRequest;
import com.ciemiorek.users.API.response.*;
import com.ciemiorek.users.common.MsgSource;
import com.ciemiorek.users.exception.CommonBadRequestException;
import com.ciemiorek.users.exception.CommonConflictException;
import com.ciemiorek.users.models.Book;
import com.ciemiorek.users.models.UserTest;
import com.ciemiorek.users.repository.BookRepository;
import com.ciemiorek.users.services.AbstractCommonService;
import com.ciemiorek.users.services.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.google.common.base.Strings.isNullOrEmpty;
import static java.util.Objects.isNull;

@Service
public class BookServiceImp extends AbstractCommonService implements BookService {

    private  final BookRepository bookRepository;

    public BookServiceImp(MsgSource msgSource ,BookRepository bookRepository) {
        super(msgSource);
        this.bookRepository = bookRepository;
    }

    @Transactional
    @Override
    public ResponseEntity addBook(BookRequest bookRequest) {
        verifyBookRequest(bookRequest);
        Book book = addBookToDataSource(bookRequest);
        return ResponseEntity.ok(new AddBookResponse(msgSource.OK001,book.getId()));
    }

    @Override
    public ResponseEntity getAllBooks() {
        List<Book> bookList = bookRepository.findAll().stream().sorted().collect(Collectors.toList());
        return ResponseEntity.ok(new BooksResponse(msgSource.OK002,bookList));
    }

    @Override
    public ResponseEntity getBookById(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(!optionalBook.isPresent()){
            throw new CommonConflictException(msgSource.Err005);
        }
        return ResponseEntity.ok(new BookResponse(msgSource.OK001,optionalBook.get()));
    }

    private void verifyBookRequest(BookRequest bookRequest){
        if(isNullOrEmpty(bookRequest.getAuthor())
            || isNullOrEmpty(bookRequest.getName())
            || isNull(bookRequest.getIsbn())){
            throw new CommonBadRequestException(msgSource.Err001);
    }}

    private Book addBookToDataSource(BookRequest bookRequest){
        Book book = new Book(bookRequest.getName(),bookRequest.getAuthor(),bookRequest.getIsbn());
       return bookRepository.save(book);
    }
}
