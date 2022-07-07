package ru.kata.spring.boot_security.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    private long id;

    @Column
    private String authority;

    @Transient
    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    public Role(long id, String authority) {
        this.id = id;
        this.authority = authority;
    }
}