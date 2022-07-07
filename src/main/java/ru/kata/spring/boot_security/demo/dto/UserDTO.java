package ru.kata.spring.boot_security.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class UserDTO {

    private long id;
    private String username;
    private String email;
    private Set<RoleDTO> roles;

}
