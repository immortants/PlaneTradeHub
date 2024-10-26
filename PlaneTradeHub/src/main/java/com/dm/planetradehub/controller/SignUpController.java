package com.dm.planetradehub.controller;

import com.dm.planetradehub.entity.User;
import com.dm.planetradehub.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignUpController {
    private final UserService userService;

    public SignUpController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signUp")
    public String signUpForm(Model model){
        model.addAttribute("user", new User());

        return "signUp";
    }

    @PostMapping("/signUp")
    public String signUpSubmit(@ModelAttribute User user){
        userService.signUpUser(user);

        return "redirect:/login";
    }
}
