package com.foxminded.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.foxminded.university.exception.EntityNotFoundException;
import com.foxminded.university.service.StudentService;
import com.foxminded.university.service.dto.StudentDto;

@Controller
@RequestMapping
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping("/students")
    public String getStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudentDto());
        return "students";
    }

    @RequestMapping(value = "/studentInfo", method = RequestMethod.GET)
    public String getStudentInfo(@RequestParam(value = "id") int id, Model model) {
        StudentDto studentDto;
        try {
            studentDto = studentService.getStudentDto(id);
        } catch (EntityNotFoundException e) {
            studentDto = new StudentDto();
            studentDto.setId(0);
            model.addAttribute("student", studentDto);
        }
        model.addAttribute("student", studentDto);
        return "studentInfo";
    }
}
