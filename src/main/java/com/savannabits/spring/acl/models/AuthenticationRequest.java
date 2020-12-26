package com.savannabits.spring.acl.models;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AuthenticationRequest {
    @NotNull
    @NotBlank(message = "Username cannot be blank")
    private String username;
    @NotNull
    @NotBlank(message = "password cannot be blank")
    private String password;

    public AuthenticationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public AuthenticationRequest() {
    }
}
