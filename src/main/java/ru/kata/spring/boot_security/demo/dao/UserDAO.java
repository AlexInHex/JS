package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {

    User getByUsername(String username);

    List<User> getAllUsers();

    Optional<User> getUserByid(long id);

    void save(User user);

    void update(User user, long id);

    void deleteUserByid(long id);
}
