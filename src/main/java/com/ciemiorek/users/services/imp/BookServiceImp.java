package com.ciemiorek.users.services.imp;

import com.ciemiorek.users.API.request.BookRequest;
import com.ciemiorek.users.API.response.AddBookResponse;
import com.ciemiorek.users.common.MsgSource;
import com.ciemiorek.users.models.Book;
import com.ciemiorek.users.repository.BookRepository;
import com.ciemiorek.users.services.AbstractCommonService;
import com.ciemiorek.users.services.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.google.common.base.Strings.isNullOrEmpty;
import static java.util.Objects.isNull;

@Service
public class BookServiceImp extends AbstractCommonService implements BookService {

    private  final BookRepository bookRepository;

    public BookServiceImp(MsgSource msgSource ,BookRepository bookRepository) {
        super(msgSource);
        this.bookRepository = bookRepository;
    }

    @Override
    public ResponseEntity addBook(BookRequest bookRequest) {

        verifyBookRequest(bookRequest);
        Book book = addBookToDataSource(bookRequest);

        return ResponseEntity.ok(new AddBookResponse("the book has been added",book.getId()));
    }

    @Override
    public ResponseEntity getAllBooks() {
        return ResponseEntity.ok(bookRepository.findAll());
    }

    @Override
    public ResponseEntity getBookById(Long id) throws Exception {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(!optionalBook.isPresent()){
            throw  new Exception();
        }
        return ResponseEntity.ok(optionalBook.get());
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
