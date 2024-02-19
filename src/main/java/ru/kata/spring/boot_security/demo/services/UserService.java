package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.entities.User;

import java.util.List;

public interface UserService {

    User getByUsername(String username);

    List<User> getAllUsers();

    User getUserByid(long id);

    void save(User user);

    void update(User user, long id);

    void deleteUserByid(long id);
}
