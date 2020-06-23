package com.ciemiorek.users.services.imp;

import com.ciemiorek.users.API.request.UserRequest;
import com.ciemiorek.users.API.response.AddUserResponse;
import com.ciemiorek.users.API.response.BasicResponse;
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
        return ResponseEntity.ok(optionalUserTest.get());
    }

    @Override
    public ResponseEntity addUser(UserRequest userRequest) {
        verifyUser(userRequest);
        UserTest userTest = addUserToDataBase(userRequest);
        return ResponseEntity.ok(new AddUserResponse("Urzytkownik dodany",userTest.getId()));
    }

    @Override
    public ResponseEntity getUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @Override
    public ResponseEntity borrowBook(double bookIsbn, Long userID) {
       List <Book> bookList = isBookWithThatIsbn(bookIsbn);

      Optional<Book> bookOptional = bookList.stream().filter(book -> book.getUserTest()==null)
               .findAny();
        if(!bookOptional.isPresent()){
            throw new CommonConflictException(msgSource.Err007);
        }

        Optional<UserTest> optionalUserTest = userRepository.findById(userID);
        if(!optionalUserTest.isPresent()){
            throw new CommonConflictException(msgSource.Err004);
        }

        UserTest userTest = optionalUserTest.get();
        Book book = bookOptional.get();
        userTest.getBooks().add(book);
        book.setUserTest(optionalUserTest.get());
        userRepository.save(userTest);
        bookRepository.save(book);

        return ResponseEntity.ok(BasicResponse.of(msgSource.OK001));
    }

    private List<Book> isBookWithThatIsbn(double bookIsbn){
        List<Book> bookList =bookRepository.findAll().stream()
                .filter(book -> bookIsbn==book.getIsbn())
                .collect(Collectors.toList());

        if(bookList.isEmpty()){
            throw new CommonConflictException(msgSource.Err003);
        }
        return bookList;
    }


    private void verifyUser(UserRequest userRequest)  {
        if(isNullOrEmpty(userRequest.getName())||isNullOrEmpty(userRequest.getSurname())||isNullOrEmpty(userRequest.getEmail())){
            throw  new CommonBadRequestException( msgSource.Err001);
        }
    }

    private UserTest addUserToDataBase (UserRequest userRequest){
        return userRepository.save(new UserTest(userRequest.getName(),userRequest.getSurname(),userRequest.getEmail()));
    }

}
