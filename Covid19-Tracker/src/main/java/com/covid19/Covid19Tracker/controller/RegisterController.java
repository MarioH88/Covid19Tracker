package com.covid19.Covid19Tracker.controller;

import com.covid19.Covid19Tracker.exceptions.UsernameTakenException;
import com.covid19.Covid19Tracker.model.User;
import com.covid19.Covid19Tracker.model.UserRole;
import com.covid19.Covid19Tracker.service.SecurityService;
import com.covid19.Covid19Tracker.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
public class RegisterController {

    private final UserService userService;
    private final SecurityService securityService;

    public RegisterController(UserService userService, SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(Model model, @RequestParam String username, @RequestParam String password) {
        try {
            User user = new User();
            user.setId(UUID.randomUUID().toString());
            user.setUsername(username);
            user.setPassword(password);
            user.setRole(UserRole.USER);

            userService.save(user);
            securityService.login(username, password);

            return "redirect:/home";
        } catch (UsernameTakenException e) {
            model.addAttribute("error", "Username taken");
            return "register";
        }
    }
}