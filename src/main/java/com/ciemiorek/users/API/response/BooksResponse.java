package com.ciemiorek.users.API.response;

import com.ciemiorek.users.common.MsgSource;
import com.ciemiorek.users.models.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Stream;

@NoArgsConstructor
@Getter
@Setter
public class BooksResponse extends BasicResponse {

    private List<Book> books;

    public BooksResponse(String responseMsg, List<Book> books) {
        super(responseMsg);
        this.books = books;
    }
}
