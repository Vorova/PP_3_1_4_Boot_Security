package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.Optional;

@Service
public class UserServiceImp implements UserService{

    private final UserRepository repository;

    @Autowired
    public UserServiceImp(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<User> allUsers() {
        return repository.findAll();
    }

    @Override
    public void saveOrUpdate(User user) {
        repository.save(user);
    }

    @Override
    public void delete(long id) {
        Optional<User> user = getById(id);
        user.ifPresent(repository::delete);
    }

    @Override
    public Optional<User> getById(long id) {
        return repository.findById(id);
    }
}
