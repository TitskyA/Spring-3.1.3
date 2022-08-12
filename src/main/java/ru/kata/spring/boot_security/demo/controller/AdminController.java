package ru.kata.spring.boot_security.demo.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.Arrays;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/admin")
public class AdminController {

    final PasswordEncoder passwordEncoder;
    final UserService userService;
    final RoleService roleService;

    public AdminController(UserServiceImpl userService, RoleServiceImpl roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/allUsers")
    public String showAllUsers(Model model, @AuthenticationPrincipal User user) {

        model.addAttribute("currentUser", user);
        model.addAttribute("users", userService.listUsers());
        model.addAttribute("newUser", new User());
        return "admin";
    }

    @PostMapping("/saveUser")
    public String addUser(@ModelAttribute("user") User user) {
        user.getRoles().stream().filter(x -> roleService.findByRoleName(x.getName()) == null).forEach(x -> roleService.add(x));
        user.setRoles(user.getRoles().stream().map(x -> roleService.findByRoleName(x.getName())).collect(Collectors.toList()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
        return "redirect:/admin/allUsers";
    }

    @PatchMapping ("/update")
    public String saveChanges(@ModelAttribute User user, @RequestParam(value = "roles") String roles) {
        String[] usersRoles = roles.split(",");
        user.setRoles(Arrays.stream(usersRoles).map(x -> roleService.findByRoleName(x)).collect(Collectors.toList()));
        if (!user.getPassword().equals(userService.get(user.getId()).getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userService.update(user);
        return "redirect:/admin/allUsers";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin/allUsers";
    }



}
