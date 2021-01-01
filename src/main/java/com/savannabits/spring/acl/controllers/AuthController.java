package com.savannabits.spring.acl.controllers;

import com.savannabits.spring.acl.dto.SignupForm;
import com.savannabits.spring.acl.entity.User;
import com.savannabits.spring.acl.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class AuthController {
    @Autowired
    AuthService authService;
    @GetMapping("/login")
    public String login(Model model) {
        return "auth/login";
    }
    // Login form with error
    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "auth/login";
    }
    @GetMapping("/signup")
    public String signupForm(SignupForm signupForm, Model model) {
        model.addAttribute("signupForm", signupForm);
        return "auth/signup";
    }
    @PostMapping("/signup")
    public String signup(@Valid @ModelAttribute SignupForm signupForm, BindingResult bindingResult) throws Exception {
        //TODO: Implement
        if (!(signupForm.getPassword().equals(signupForm.getPasswordConfirmation()))) {
            bindingResult.addError(new FieldError("signupForm","password","The two passwords do not match."));
        }
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "auth/signup";
        }
        try {
            User user = authService.createNewUser(signupForm);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            throw new Exception(throwable.getMessage());
        }
        return "redirect:/login";
    }
}
