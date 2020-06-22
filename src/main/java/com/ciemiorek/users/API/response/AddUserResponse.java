package com.ciemiorek.users.API.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserResponse extends BasicResponse {

    private Long userID;

    public AddUserResponse( String responseMsg,Long userID) {
        super(responseMsg);
        this.userID = userID;
    }
}
