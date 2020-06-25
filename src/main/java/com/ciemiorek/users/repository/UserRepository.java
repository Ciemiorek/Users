package com.ciemiorek.users.repository;

import com.ciemiorek.users.models.UserTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<UserTest, Long> {
}
