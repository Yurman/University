package com.yurman.university.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class MainController {

    @GetMapping("/")
    public String handleRequestRoot(Model model) {
        return "main";
    }

    @GetMapping("/main")
    public String handleRequestMain(Model model) {
        return "main";
    }
}
