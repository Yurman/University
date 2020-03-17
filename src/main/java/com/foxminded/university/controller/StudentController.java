package com.foxminded.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView getStudents() {
        ModelAndView model = new ModelAndView("students.html");
        model.addObject("students", studentService.getAllStudentDto());
        return model;
    }

    @RequestMapping(value = "/studentInfo", method = RequestMethod.GET)
    public ModelAndView getStudentInfo(@RequestParam(value = "id") int id) {
        StudentDto studentDto;
        try {
            studentDto = studentService.getStudentDto(id);
        } catch (EntityNotFoundException e) {
            studentDto = new StudentDto();
            studentDto.setId(0);
        }
        ModelAndView model = new ModelAndView("studentInfo.html");
        model.addObject("student", studentDto);
        return model;
    }
}
