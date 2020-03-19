package com.foxminded.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.foxminded.university.exception.EntityNotFoundException;
import com.foxminded.university.service.StudentService;

@Controller
@RequestMapping
public class StudentController {

    private static final String ATTRIBUTE_HTML_STUDENT = "student";
    private static final String ATTRIBUTE_HTML_ERROR_MESSAGE = "error";
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
        ModelAndView model = new ModelAndView("studentInfo.html");
        try {
            model.addObject(ATTRIBUTE_HTML_STUDENT, studentService.getStudentDto(id));
        } catch (EntityNotFoundException e) {
            String errorMessage = "Problem with finding student";
            model.addObject(ATTRIBUTE_HTML_ERROR_MESSAGE, errorMessage);
        }
        return model;
    }
}
