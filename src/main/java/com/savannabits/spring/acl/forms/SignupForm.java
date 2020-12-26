package com.savannabits.spring.acl.forms;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class SignupForm {
    @NotBlank @NotNull @NotEmpty
    private String username;
    @NotBlank @NotNull @NotEmpty
    private String email;
    @NotBlank @NotNull @NotEmpty
    private String password;
    @NotBlank @NotNull @NotEmpty
    private String password_confirmation;
    @NotBlank @NotNull @NotEmpty
    private String name;
}
