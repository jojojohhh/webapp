package com.swlab.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/home")
@Controller
public class HomeController {

    @GetMapping("")
    public String getHome(Model model) {
        model.addAttribute("currentPage", "home");
        return "content/home";
    }


}
