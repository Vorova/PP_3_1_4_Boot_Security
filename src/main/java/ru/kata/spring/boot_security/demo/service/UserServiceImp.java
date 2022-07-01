package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService{

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImp(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
//        addDefaultUsersInTable();
    }

    public void addDefaultUsersInTable() {
        Role roleUser = new Role(1L,"ROLE_USER");
        Role roleAdmin = new Role(2L,"ROLE_ADMIN");

        roleRepository.save(roleUser);
        roleRepository.save(roleAdmin);

        List<Role> listRole = new ArrayList<>();
        listRole.add(roleAdmin);

        User user = new User();
        user.setUsername("admin");
        user.setEmail("admin@gmail.com");
        user.setPassword(passwordEncoder.encode("admin"));
        user.setRoles(listRole);
        userRepository.save(user);
    }

    @Override
    public Iterable<User> allUsers() {
        return userRepository.findAll();
    }

    @Override
    public void save(User user) {
        List<Role> listRole = new ArrayList<>();
        listRole.add(new Role(1L, "ROLE_USER"));

        user.setRoles(listRole);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
    }

    @Override
    public void update(User user) {
        User userFromDb = userRepository.getById(user.getId());
        userFromDb.setUsername(user.getUsername());
        userFromDb.setEmail(user.getEmail());
        userRepository.save(userFromDb);
    }

    @Override
    public void delete(long id) {
        Optional<User> user = getById(id);
        user.ifPresent(userRepository::delete);
    }

    @Override
    public Optional<User> getById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public User getByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return user.get();
        } else {
            return null;
        }


    }
}
