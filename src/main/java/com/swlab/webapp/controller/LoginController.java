package com.swlab.webapp.controller;

import com.swlab.webapp.model.user.SecurityUser;
import com.swlab.webapp.model.user.UserRole;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping(value = {"", "/login"})
    public String getIndex(@AuthenticationPrincipal SecurityUser user) {
        if (user != null) {
            if (user.getRoleTypes().contains(UserRole.RoleType.ROLE_VIEW)) {
                return "redirect:/home";
            }
        }
        return "content/login";
    }

    @GetMapping(value = "/err/denied")
    public String getAccessDenied() {
        return "err/denied";
    }
}
