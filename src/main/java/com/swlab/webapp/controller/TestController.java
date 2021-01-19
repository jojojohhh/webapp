package com.swlab.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/test")
    public String getTest(Model model) {
        model.addAttribute("currentPage", "home");
        return "home";
    }
}
