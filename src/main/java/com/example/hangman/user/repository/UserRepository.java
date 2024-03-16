package com.example.hangman.user.repository;
import com.example.hangman.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUsername (String username);
    User findUserById (Long id);
    List<User> findAllByOrderByRatingDesc ();
}
