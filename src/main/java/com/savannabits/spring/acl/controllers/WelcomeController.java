package com.savannabits.spring.acl.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WelcomeController {
    @GetMapping("/")
    public String index(Model model) {
        return "welcome";
    }
    @GetMapping("/home")
    public String home(Model model) {
        return "home";
    }

}
