package ru.kata.spring.boot_security.demo.dto;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class RoleDTO {

    private long id;
    private String authority;

}
