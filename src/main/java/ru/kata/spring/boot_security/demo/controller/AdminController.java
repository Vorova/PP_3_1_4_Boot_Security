package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    // Главная страница админ панели
    @GetMapping
    public String index(Model model, Principal user) {
        model.addAttribute("title", "List users");
        model.addAttribute("users", userService.allUsers());
        model.addAttribute("user", userService.getByUsername(user.getName()));
        model.addAttribute("newUser", new User());
        return "admin/index";
    }

    // Добавление нового пользователя
    @PostMapping(value = "/add")
    public String add(@ModelAttribute User user, @RequestParam Long[] roles) {
        userService.save(user, roles);
        return "redirect:/admin";
    }

    @PostMapping(value="/save")
    public String save(@ModelAttribute User user) {
        userService.update(user);
        return "redirect:/admin/";
    }
}
