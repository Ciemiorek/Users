package com.ciemiorek.users.API.response;

import com.ciemiorek.users.models.UserTest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserResponse extends BasicResponse {

    private UserTest userTest;

    public UserResponse(String responseMsg, UserTest userTest) {
        super(responseMsg);
        this.userTest = userTest;
    }
}
