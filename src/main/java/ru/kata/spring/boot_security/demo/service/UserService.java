package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.Optional;

public interface UserService {

    Iterable<User> allUsers();
    void save(User user);

    void update(User user);

    void delete(long id);
    Optional<User> getById(long id);
    User getByUsername(String username);
}
