package com.ciemiorek.users.API.response;

import com.ciemiorek.users.models.Book;
import lombok.Getter;
import lombok.Setter;

public class AddBookResponse extends BasicResponse {

@Getter
@Setter
private Long bookId;



    public AddBookResponse(String responseMsg,Long bookId) {
        super(responseMsg);
        this.bookId = bookId;
    }
}
