package com.savannabits.spring.acl;

import com.savannabits.spring.acl.entity.User;
import com.savannabits.spring.acl.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service("UserDetailsService")
@Transactional
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByUsername(username);
        user.orElseThrow( () -> new UsernameNotFoundException("The user was not found: "+username));
        return user.map(UserDetailsProvider::new).get();
    }
}
