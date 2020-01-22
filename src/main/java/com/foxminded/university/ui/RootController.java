package com.foxminded.university.ui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class RootController {

    @RequestMapping
    public String handleRequest(Model model) {
        model.addAttribute("msg", "Just a massage for main");
        return "main";
    }

}
