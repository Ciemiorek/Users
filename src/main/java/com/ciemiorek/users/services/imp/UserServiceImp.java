package com.ciemiorek.users.services.imp;

import com.ciemiorek.users.API.request.UserRequest;
import com.ciemiorek.users.API.response.AddUserResponse;
import com.ciemiorek.users.common.MsgSource;
import com.ciemiorek.users.models.UserTest;
import com.ciemiorek.users.repository.UserRepository;
import com.ciemiorek.users.services.AbstractCommonService;
import com.ciemiorek.users.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.google.common.base.Strings.isNullOrEmpty;

@Service
public class UserServiceImp extends AbstractCommonService implements UserService {

   private UserRepository userRepository;

    public UserServiceImp(MsgSource msgSource , UserRepository userRepository) {
        super(msgSource);
        this.userRepository =userRepository;
    }

    @Override
    public ResponseEntity getUserByID(Long id) throws Exception {
        Optional<UserTest> optionalUserTest = userRepository.findById(id);
        if(!optionalUserTest.isPresent()){
            throw new Exception("Nie ma takiego id");
        }
        return ResponseEntity.ok(optionalUserTest.get());
    }

    @Override
    public ResponseEntity addUser(UserRequest userRequest) throws Exception {
        verifyUser(userRequest);
        UserTest userTest = addUserToDataBase(userRequest);
        return ResponseEntity.ok(new AddUserResponse("Urzytkownik dodany",userTest.getId()));
    }

    @Override
    public ResponseEntity getUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    private void verifyUser(UserRequest userRequest) throws Exception {
        if(isNullOrEmpty(userRequest.getName())||isNullOrEmpty(userRequest.getSurname())||isNullOrEmpty(userRequest.getEmail())){
            throw  new Exception("Wrong data to make user");
        }
    }

    private UserTest addUserToDataBase (UserRequest userRequest){
        return userRepository.save(new UserTest(userRequest.getName(),userRequest.getSurname(),userRequest.getEmail()));
    }

}
