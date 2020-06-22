package com.ciemiorek.users.repository;

import com.ciemiorek.users.models.UserTest;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserTest, Long> {
}
