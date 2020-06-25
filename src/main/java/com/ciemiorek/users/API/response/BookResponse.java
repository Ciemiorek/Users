package com.ciemiorek.users.API.response;

import com.ciemiorek.users.models.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BookResponse extends BasicResponse {
    private Book book;

    public BookResponse(String responseMsg, Book book) {
        super(responseMsg);
        this.book = book;
    }

}
