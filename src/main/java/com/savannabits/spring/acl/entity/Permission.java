package com.savannabits.spring.acl.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
public class Permission {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Boolean enabled;

    @ManyToMany(mappedBy = "permissions")
    private Collection<Role> roles;
}
