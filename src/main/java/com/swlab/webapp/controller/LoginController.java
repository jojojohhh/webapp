package com.swlab.webapp.controller;

import com.swlab.webapp.dto.UserDto;
import com.swlab.webapp.model.user.SecurityUser;
import com.swlab.webapp.model.user.UserRole;
import com.swlab.webapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class LoginController {

    private final UserService userService;


    @GetMapping(value = {"", "/login"})
    public String getIndex(@AuthenticationPrincipal SecurityUser user) {
        if (user != null && user.getRoleTypes().contains(UserRole.RoleType.ROLE_VIEW)) {
            return "redirect:/home";
        }
        return "content/login";
    }

    @GetMapping("/join")
    public String getJoin(@AuthenticationPrincipal SecurityUser user) {
        if (user != null && user.getRoleTypes().contains(UserRole.RoleType.ROLE_VIEW)) {
            return "redirect:/home";
        }
        return "content/join";
    }

    @ResponseBody
    @PostMapping("/join")
    public Map<String, Object> postJoin(@RequestBody UserDto userDto) {
        Map<String, Object> res = new HashMap<>();

        if (userService.findByEmail(userDto.getEmail()).isPresent()) {
            res.put("duplicate", true);
            return res;
        }
        res.put("success", userService.join(userDto) != null ? true : false);
        return res;
    }

    @RequestMapping(value = "/err/denied")
    public String getAccessDenied() {
        return "err/denied";
    }
}
