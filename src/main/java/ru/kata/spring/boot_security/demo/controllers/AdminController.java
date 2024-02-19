package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String allUsers(ModelMap modelMap) {
        modelMap.addAttribute("users", userService.getAllUsers());
        return "/admin/all_users";
    }

    @GetMapping("/new_user")
    public String newUser(@ModelAttribute("user") User user, ModelMap modelMap) {
        modelMap.addAttribute("roles", roleService.findAll());
        return "admin/create_user";
    }

    @PostMapping("/new")
    public String regUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/create_user";
        }
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/user/{id}")
    public String getUpdate(ModelMap modelMap, @PathVariable(name = "id") long id) {
        modelMap.addAttribute("user", userService.getUserByid(id));
        modelMap.addAttribute("roles", roleService.findAll());
        return "/admin/update_user";
    }


    @PatchMapping("/update/{id}")
    public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                             @PathVariable("id") long id) {
        if (bindingResult.hasErrors()) {
            return "/admin/update_user";
        }
        userService.update(user, id);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable(name = "id") long id) {
        userService.deleteUserByid(id);
        return "redirect:/admin";
    }

}
