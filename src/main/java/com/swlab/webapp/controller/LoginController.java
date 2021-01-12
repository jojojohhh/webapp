package com.swlab.webapp.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("")
    public String getLogin(@AuthenticationPrincipal User user) {
        if (user != null) {
            if (user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_VIEW"))) {
                return "redirect:/home";
            }
        }
        return "redirect:/login";
    }
}
