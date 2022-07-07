package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.dto.RoleDTO;
import ru.kata.spring.boot_security.demo.dto.UserDTO;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/admin/api")
public class ApiController {

    private final UserService userService;


    @Autowired
    public ApiController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/getUser/{id}")
    public UserDTO getUser(@PathVariable Long id) {
        User user = userService.getById(id).get();

        Set<RoleDTO> rolesDTO = new HashSet<>();
        for (Role role : user.getRoles()) {
            rolesDTO.add(new RoleDTO(
                    role.getId(),
                    role.getAuthority()
            ));
        }

        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                rolesDTO
        );
    }

    @PutMapping("/updateUser")
    public void updateUser(@RequestBody User user) {
        userService.update(user);
    }

    @DeleteMapping(value="/deleteUser")
    public void deleteUser(@RequestBody long id) {
        userService.delete(id);
    }
}
