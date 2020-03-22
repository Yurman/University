package com.foxminded.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.foxminded.university.domain.Student;
import com.foxminded.university.exception.EntityNotFoundException;
import com.foxminded.university.exception.QueryNotExecuteException;
import com.foxminded.university.service.StudentService;

@Controller
@RequestMapping
public class StudentController {

    private static final String ATTRIBUTE_HTML_STUDENT = "student";
    private static final String ATTRIBUTE_HTML_MESSAGE = "message";
    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping("/students")
    public ModelAndView getStudents() {
        ModelAndView model = new ModelAndView("students");
        model.addObject("students", studentService.getAllStudentDto());
        return model;
    }

    @RequestMapping(value = "/students", method = RequestMethod.POST)
    public ModelAndView deleteStudent(@RequestParam(value = "id") int id) {
        ModelAndView model = new ModelAndView("students");
        try {
            studentService.deleteStudent(id);
            String message = "Successfully delete student";
            model.addObject(ATTRIBUTE_HTML_MESSAGE, message);
        } catch (DataAccessException e) {
            String errorMessage = "Problem with deleting student";
            model.addObject(ATTRIBUTE_HTML_MESSAGE, errorMessage);
        }
        return model;
    }

    @RequestMapping(value = "/studentInfo", method = RequestMethod.GET)
    public ModelAndView getStudentInfo(@RequestParam(value = "id") int id) {
        ModelAndView model = new ModelAndView("studentInfo");
        try {
            model.addObject(ATTRIBUTE_HTML_STUDENT, studentService.getStudentDto(id));
        } catch (EntityNotFoundException e) {
            String errorMessage = "Problem with finding student";
            model.addObject(ATTRIBUTE_HTML_MESSAGE, errorMessage);
        }
        return model;
    }

    @RequestMapping(value = "/updateStudent", method = RequestMethod.GET)
    public ModelAndView updateStudentInfo(@RequestParam(value = "id") int id) {
        ModelAndView model = new ModelAndView("updateStudent");
        try {
            model.addObject(ATTRIBUTE_HTML_STUDENT, studentService.getStudentDto(id));
        } catch (EntityNotFoundException e) {
            String errorMessage = "Problem with finding student";
            model.addObject(ATTRIBUTE_HTML_MESSAGE, errorMessage);
        }
        return model;
    }

    @RequestMapping(value = "/updateStudent", method = RequestMethod.POST)
    public ModelAndView updateStudent(@RequestParam(value = "id") int id,
            @RequestParam(value = "firstName") String firstName,
            @RequestParam(value = "lastName") String lastName, @RequestParam(value = "groupTitle") String groupTitle) {
        ModelAndView model = new ModelAndView("updateStudent");
        try {
            Student newStudent = studentService.getStudentById(id);
            newStudent.setFirstName(firstName);
            newStudent.setLastName(lastName);
            studentService.updateStudent(newStudent);
            String message = "Succeccfully update student";
            model.addObject(ATTRIBUTE_HTML_MESSAGE, message);
        } catch (DataAccessException e) {
            String errorMessage = "Problem with updating student";
            model.addObject(ATTRIBUTE_HTML_MESSAGE, errorMessage);
        }
        return model;
    }

    @RequestMapping(value = "/addStudent", method = RequestMethod.GET)
    public ModelAndView addStudent() {
        ModelAndView model = new ModelAndView("addStudent");
        return model;
    }

    @RequestMapping(value = "/addStudent", method = RequestMethod.POST)
    public ModelAndView addNewStudent(@RequestParam(value = "firstName") String firstName,
            @RequestParam(value = "lastName") String lastName, @RequestParam(value = "groupTitle") String groupTitle) {
        ModelAndView model = new ModelAndView("students");
        try {
            Student newStudent = new Student();
            newStudent.setFirstName(firstName);
            newStudent.setLastName(lastName);
            studentService.addStudent(newStudent);
            String message = "Succeccfully add new student";
            model.addObject(ATTRIBUTE_HTML_MESSAGE, message);
        } catch (QueryNotExecuteException e) {
            String errorMessage = "Problem with adding student";
            model.addObject(ATTRIBUTE_HTML_MESSAGE, errorMessage);
        }
        return model;
    }
}
