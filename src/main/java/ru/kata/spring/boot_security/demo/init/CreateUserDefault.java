package ru.kata.spring.boot_security.demo.init;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.HashSet;
import java.util.Set;

@Component
public class CreateUserDefault {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public CreateUserDefault(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Bean
    public void createDef() {

        Role adminRole = new Role("ROLE_ADMIN");
        Role userRole = new Role("ROLE_USER");

        roleService.saveRole(adminRole);
        roleService.saveRole(userRole);

        Set<Role> adminSet = new HashSet<>();
        adminSet.add(adminRole);
        adminSet.add(userRole);
        Set<Role> userSet = new HashSet<>();
        userSet.add(userRole);

        User user = new User("user", "user", userSet, 1);
        User admin = new User("admin", "admin", adminSet, 1);

        userService.save(user);
        userService.save(admin);

    }
}
