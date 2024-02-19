package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.entities.Role;

import java.util.List;

public interface RoleDAO {
    List<Role> findAll();
    void saveRole(Role role);
}
