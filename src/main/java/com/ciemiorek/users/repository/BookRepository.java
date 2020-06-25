package com.ciemiorek.users.repository;

import com.ciemiorek.users.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book,Long> {

}
