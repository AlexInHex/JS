package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String allUsers(ModelMap modelMap, Principal principal) {
        modelMap.addAttribute("new_user", new User());
        modelMap.addAttribute("users", userService.getAllUsers());
        modelMap.addAttribute("user", userService.getByUsername(principal.getName()));
        modelMap.addAttribute("allRoles", roleService.findAll());
        return "/admin/all_users";
    }

    @PostMapping()
    public String regUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        userService.save(user);
        return "redirect:/admin";
    }

    @PutMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user,
                             @RequestParam("roles") Set<Long> roleIds,
                             @PathVariable("id") long id) {

        // Получаем объект пользователя по его id
        User existingUser = userService.getUserByid(id);

        // Обновляем поля пользователя, которые могут измениться
        existingUser.setUsername(user.getUsername());
        existingUser.setAge(user.getAge());
        existingUser.setPassword(user.getPassword());

        // Обновляем роли пользователя
        Set<Role> roles = new HashSet<>();
        for (Long roleId : roleIds) {
            Role role = roleService.getRoleById(roleId);
            roles.add(role);
        }
        existingUser.setRoles(roles);

        // Сохраняем обновленного пользователя
        userService.update(existingUser);

        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable(name = "id") long id) {
        userService.deleteUserByid(id);
        return "redirect:/admin";
    }

}
