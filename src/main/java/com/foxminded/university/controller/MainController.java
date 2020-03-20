package com.foxminded.university.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class MainController {

    @RequestMapping("/")
    public String handleRequestRoot(Model model) {
        return "main";
    }

    @RequestMapping("/main")
    public String handleRequestMain(Model model) {
        return "main";
    }
}
