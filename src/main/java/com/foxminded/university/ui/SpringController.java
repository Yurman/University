package com.foxminded.university.ui;

import java.time.LocalTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class SpringController {

    @RequestMapping
    public String handleRequest(Model model) {
        model.addAttribute("msg", "Just a massage for now");
        model.addAttribute("time", LocalTime.now());
        return "main";
    }
}