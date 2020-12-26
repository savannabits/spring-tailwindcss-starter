package com.savannabits.spring.acl.controllers.api;

import com.savannabits.spring.acl.MyUserDetailsService;
import com.savannabits.spring.acl.UserDetailsProvider;
import com.savannabits.spring.acl.models.AuthenticationRequest;
import com.savannabits.spring.acl.models.AuthenticationResponse;
import com.savannabits.spring.acl.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController("apiAuthController")
@RequestMapping(path = "/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private JwtUtils jwtUtils;


    @PostMapping("login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthenticationRequest request) throws Exception {
        try {
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(auth);
        } catch (BadCredentialsException e) {
            throw new Exception("Either username or password is incorrect");
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        final String jwt = jwtUtils.generateJWToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
