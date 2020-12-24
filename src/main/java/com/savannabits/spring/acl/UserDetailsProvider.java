package com.savannabits.spring.acl;

import com.savannabits.spring.acl.model.Permission;
import com.savannabits.spring.acl.model.Role;
import com.savannabits.spring.acl.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetailsProvider implements UserDetails {
    private String username;
    private String password;
    private Boolean enabled;
    private Collection<Role> roles;
    private List<GrantedAuthority> authorities;
    public UserDetailsProvider(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.enabled = user.getEnabled();
        this.roles = user.getRoles();
    }
    public UserDetailsProvider() {

    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getGrantedAuthorities(getPermissions(roles));
    }

    private Collection<? extends GrantedAuthority> getGrantedAuthorities(List<String> permissions) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String permission : permissions) {
            authorities.add(new SimpleGrantedAuthority(permission));
        }
        return authorities;
    }

    private List<String> getPermissions(Collection<Role> roles) {
        List<String> permissions = new ArrayList<>();
        List<Permission> collection = new ArrayList<>();
        for (Role role : roles) {
            collection.addAll(role.getPermissions());
        }
        for (Permission item : collection) {
            permissions.add(item.getName());
        }
        return permissions;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
