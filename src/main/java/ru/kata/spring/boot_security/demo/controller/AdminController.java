package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String index(Model model) {
        String title = "(ADMIN) Старница с пользователями";
        model.addAttribute("title", title);
        model.addAttribute("users", userService.allUsers());
        model.addAttribute("user", new User());
        model.addAttribute("role", new Role());
        return "admin/index";
    }

    @PostMapping(value = "/add")
    public String add(@ModelAttribute User user) {
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping(value="/edit/{id}")
    public String edit(@PathVariable long id, ModelMap model) {
        model.addAttribute("user", userService.getById(id));
        return "admin/edit";
    }

    @PostMapping(value="/save")
    public String save(@ModelAttribute User user) {
        userService.update(user);
        return "redirect:/admin/";
    }

    @GetMapping(value="/delete/{id}")
    public String delete(@PathVariable long id) {
        userService.delete(id);
        return "redirect:/admin/";
    }

}
