package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.*;

@Service
public class UserServiceImp implements UserService {

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

        Set<Role> listRole = new HashSet<>();
        listRole.add(roleAdmin);

        User user = new User();
        user.setUsername("admin");
        user.setEmail("admin@gmail.com");
        user.setPassword(passwordEncoder.encode("admin"));
        user.setRoles(listRole);
        userRepository.save(user);
    }

    @Override
    public List<User> allUsers() {
        return userRepository.findAll();
    }

    @Override
    public void save(User user, Long[] roles) {
        Set<Role> listRole = new HashSet<>();

        for (Long role : roles) {
            listRole.add(roleRepository.findRoleById(role));
        }

        user.setRoles(listRole);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
    }

    @Override
    public void update(User user) {
        User userDB = userRepository.getById(user.getId());
        user.setPassword(userDB.getPassword());
        userRepository.save(user);
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
        return user.orElse(null);
    }
}
