package com.swlab.webapp.controller;

import com.swlab.webapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/home")
@RequiredArgsConstructor
@Controller
public class HomeController {

    private final UserService userService;

    @GetMapping("")
    public String getHome(Model model) {
        model.addAttribute("currentPage", "home");
        return "content/home";
    }


    @GetMapping("/users")
    public String getUsers(Model model){
        model.addAttribute("users", userService.findAll());
        model.addAttribute("currentPage", "user");
        return "content/users";
    }

}
