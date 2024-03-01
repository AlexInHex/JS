package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<User> getAuthenticatedUser(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getByUsername(userDetails.getUsername());
        return ResponseEntity.ok(user);
    }


    @GetMapping("/current")
    public ResponseEntity<User> currentUser(Principal principal) {
        User user = userService.getByUsername(principal.getName());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //@GetMapping("/current")
    //public ResponseEntity<Map<String, Object>> currentUser(Principal principal) {
    //    User user = userService.getByUsername(principal.getName());
    //    Map<String, Object> userData = new HashMap<>();
    //    userData.put("id", user.getId());
    //    userData.put("age", user.getAge());
    //    userData.put("username", user.getUsername());
    //    List<String> roles = user.getRoles().stream()
    //            .map(role -> role.getName().substring(5))
    //            .collect(Collectors.toList());
    //    userData.put("roles", roles);
    //    return ResponseEntity.ok(userData);
    //}
}
