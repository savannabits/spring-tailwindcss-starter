package com.savannabits.spring.acl.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Data
public class SignupForm {
    @NotBlank @NotNull @NotEmpty @Size(min = 5, max = 50)
    private String username;
    @NotBlank @NotNull @Email
    private String email;
    @NotBlank @NotNull @NotEmpty
    private String password;
    @NotBlank @NotNull @NotEmpty
    private String passwordConfirmation;
    @NotBlank @NotNull @NotEmpty
    private String name;
}
