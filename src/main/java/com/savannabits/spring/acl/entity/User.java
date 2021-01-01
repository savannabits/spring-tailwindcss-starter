package com.savannabits.spring.acl.entity;

import com.sun.istack.Nullable;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String username;
    @Nullable @Column(unique = true)
    private String email;
    private String password;
    private Boolean enabled;

    @ManyToMany
    @JoinTable(
            name = "role_user",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Collection<Role> roles;
}
