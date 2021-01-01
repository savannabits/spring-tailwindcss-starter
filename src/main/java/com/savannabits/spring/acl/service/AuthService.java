package com.savannabits.spring.acl.service;

import com.savannabits.spring.acl.dto.SignupForm;
import com.savannabits.spring.acl.entity.Role;
import com.savannabits.spring.acl.entity.User;
import com.savannabits.spring.acl.repositories.RoleRepository;
import com.savannabits.spring.acl.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Optional;

@Service
@Transactional
public class AuthService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder encoder;
    public User createNewUser(SignupForm form) throws Throwable {
        User user = new User();
        user.setEmail(form.getEmail());
        user.setName(form.getName());
        user.setUsername(form.getUsername());
        user.setPassword(encoder.encode(form.getPassword()));
        user.setEnabled(true);

        // User Role:
        Optional<Role> userRole = roleRepository.findByName("ROLE_USER");
        userRole.orElseThrow(() -> new Throwable("The default ROLE_USER is not defined"));
        user.setRoles(Collections.singletonList(userRole.get()));
        userRepository.save(user);
        return user;
    }
}
