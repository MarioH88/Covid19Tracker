package com.covid19.Covid19Tracker.repository;

import com.covid19.Covid19Tracker.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {

    Optional<User> findByUsername(String username);
}
