package ru.kata.spring.boot_security.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDAOImpl implements UserDAO {

    private UserRepository userRepository;

    @Autowired
    public UserDAOImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserByid(long id) {
        Optional<User> user = userRepository.findById(id);
        return user;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);

    }

    @Override
    public void update(User user, long id) {
        user.setId(id);
        userRepository.save(user);
    }

    @Override
    public void deleteUserByid(long id) {
        userRepository.deleteById(id);
    }
}
