package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.Optional;

public interface UserService {

    Iterable<User> allUsers();
    void saveOrUpdate(User user);
    void delete(long id);
    Optional<User> getById(long id);
}
