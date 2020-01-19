package com.foxminded.university.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foxminded.university.service.StudentService;

@Controller
@RequestMapping("/main")
public class MainController {
    @Autowired
    StudentService student;

    @RequestMapping
    public String handleRequest(Model model) {
        model.addAttribute("msg", "Just a massage for now");
        model.addAttribute("student", student.getStudentById(1).getFirstName());
        return "main";
    }
}
