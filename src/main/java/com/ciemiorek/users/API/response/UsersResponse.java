package com.ciemiorek.users.API.response;

import com.ciemiorek.users.common.MsgSource;
import com.ciemiorek.users.models.UserTest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class UsersResponse extends BasicResponse{
    private List<UserTest> userTests;
    public UsersResponse(String msgSource, List<UserTest> userList) {
        super(msgSource);
        this.userTests =userList;
    }
}
