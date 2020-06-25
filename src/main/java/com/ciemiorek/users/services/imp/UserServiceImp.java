package com.ciemiorek.users.services.imp;

import com.ciemiorek.users.API.request.UserRequest;
import com.ciemiorek.users.API.response.AddUserResponse;
import com.ciemiorek.users.API.response.BasicResponse;
import com.ciemiorek.users.API.response.UserResponse;
import com.ciemiorek.users.API.response.UsersResponse;
import com.ciemiorek.users.common.MsgSource;
import com.ciemiorek.users.exception.CommonBadRequestException;
import com.ciemiorek.users.exception.CommonConflictException;
import com.ciemiorek.users.models.Book;
import com.ciemiorek.users.models.UserTest;
import com.ciemiorek.users.repository.BookRepository;
import com.ciemiorek.users.repository.UserRepository;
import com.ciemiorek.users.services.AbstractCommonService;
import com.ciemiorek.users.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.google.common.base.Strings.isNullOrEmpty;

@Service
public class UserServiceImp extends AbstractCommonService implements UserService {

   private UserRepository userRepository;
   private BookRepository bookRepository;


    public UserServiceImp(MsgSource msgSource , UserRepository userRepository, BookRepository bookRepository) {
        super(msgSource);
        this.userRepository =userRepository;
        this.bookRepository =bookRepository;
    }

    @Override
    public ResponseEntity getUserByID(Long id) {
        Optional<UserTest> optionalUserTest = userRepository.findById(id);
        if(!optionalUserTest.isPresent()){
            throw new CommonConflictException(msgSource.Err004);
        }
        return ResponseEntity.ok(new UserResponse(msgSource.OK002,optionalUserTest.get()));
    }

    @Transactional
    @Override
    public ResponseEntity addUser(UserRequest userRequest) {
        verifyUserRequest(userRequest);
        UserTest userTest = addUserToDataBase(userRequest);
        return ResponseEntity.ok(new AddUserResponse(msgSource.OK003,userTest.getId()));
    }

    @Override
    public ResponseEntity getUsers() {
        List<UserTest> userList = userRepository.findAll().stream().sorted().collect(Collectors.toList());
        return ResponseEntity.ok(new UsersResponse(msgSource.OK002,userList));
    }

    @Override
    @Transactional
    public ResponseEntity borrowBook(Long bookID, Long userID) {
            Book book = isBookWithThatId(bookID);
            Optional<UserTest> userTestOptional = Optional.ofNullable(book.getUserTest());
            if(userTestOptional.isPresent()){
                throw new CommonConflictException(msgSource.Err007);
            }
            userTestOptional = userRepository.findById(userID);
            if(!userTestOptional.isPresent()){
                throw new CommonConflictException(msgSource.Err004);
            }
            UserTest userTest = userTestOptional.get();

            userTest.getBooks().add(book);
            book.setUserTest(userTest);
            userRepository.save(userTest);
            bookRepository.save(book);

            return ResponseEntity.ok(BasicResponse.of(msgSource.OK004));
    }

    @Override
    @Transactional
    public ResponseEntity returnBook(Long bookID, Long userID) {
        Book book = isBookWithThatId(bookID);

        Optional<UserTest> userTestOptionalFromBook = Optional.ofNullable(book.getUserTest());
        if(!userTestOptionalFromBook.isPresent()){
            throw new CommonConflictException(msgSource.Err008);
        }

        Optional<UserTest> userTestOptional = userRepository.findById(userID);
        if(!userTestOptional.isPresent()){
            throw new CommonConflictException(msgSource.Err004);
        }

        UserTest userFromBook = userTestOptionalFromBook.get();
        UserTest userTest = userTestOptional.get();

        //check is this user borrow this book
        if(!userTest.equals(userFromBook)){
            throw  new CommonConflictException(msgSource.Err009);
        }

        book.setUserTest(null);
        userTest.getBooks().remove(book);

        userRepository.save(userTest);
        bookRepository.save(book);


        return ResponseEntity.ok(BasicResponse.of(msgSource.OK005));
    }




    private Book isBookWithThatId(Long bookID){
        Optional<Book> optionalBook = bookRepository.findById(bookID);
        if(!optionalBook.isPresent()){
            throw new CommonConflictException(msgSource.Err005);
        }
        return optionalBook.get();
    }


    private void verifyUserRequest(UserRequest userRequest)  {
        if(isNullOrEmpty(userRequest.getName())
          ||isNullOrEmpty(userRequest.getSurname())
          ||isNullOrEmpty(userRequest.getEmail())){

            throw  new CommonBadRequestException( msgSource.Err001);
        }
    }

    private UserTest addUserToDataBase (UserRequest userRequest){
        return userRepository.save(new UserTest(userRequest.getName(),userRequest.getSurname(),userRequest.getEmail()));
    }

}
