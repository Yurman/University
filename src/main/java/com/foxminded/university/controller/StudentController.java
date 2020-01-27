package com.foxminded.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.foxminded.university.service.StudentService;

@Controller
@RequestMapping
public class StudentController {

    @Autowired
    private StudentService student;

    @RequestMapping("/students")
    public String handleRequestStudents(Model model) {
        model.addAttribute("students", student.getAllStudents());
        return "students";
    }

    @RequestMapping(value = "/studentInfo", method = RequestMethod.POST)
    public String handleRequestStudentInfo(@RequestParam(value = "id") int id, Model model) {
        model.addAttribute("student", student.getStudentById(id));
        return "studentInfo";
    }
}
