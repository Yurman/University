package com.foxminded.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.foxminded.university.exception.EntityNotFoundException;
import com.foxminded.university.exception.QueryNotExecuteException;
import com.foxminded.university.service.GroupService;
import com.foxminded.university.service.StudentService;
import com.foxminded.university.service.dto.StudentDto;

@Controller
@RequestMapping
public class StudentController {

    private static final String ATTRIBUTE_HTML_STUDENT = "student";
    private static final String ATTRIBUTE_HTML_MESSAGE = "message";
    private static final String ATTRIBUTE_HTML_GROUPS = "groups";
    private static final String ATTRIBUTE_HTML_PAGE_ADDRESS = "pageAddress";
    private static final String ATTRIBUTE_HTML_PAGE_TITLE = "pageTitle";

    private StudentService studentService;
    private GroupService groupService;

    @Autowired
    public StudentController(StudentService studentService, GroupService groupService) {
        this.studentService = studentService;
        this.groupService = groupService;

    }

    @ModelAttribute(value = "studentDto")
    public StudentDto newStudentDto() {
        return new StudentDto();
    }

    @RequestMapping("/students")
    public ModelAndView getStudents() {
        ModelAndView model = new ModelAndView("students");
        model.addObject("students", studentService.getAllStudentDto());
        return model;
    }

    @PostMapping(value = "/studentInfo")
    public ModelAndView deleteStudent(@RequestParam(value = "id") int id) {
        ModelAndView model = new ModelAndView("students");
        try {
            studentService.deleteStudent(id);
            String message = "Successfully delete student";
            model.addObject(ATTRIBUTE_HTML_MESSAGE, message);
        } catch (QueryNotExecuteException e) {
            String errorMessage = "Problem with deleting student";
            model.addObject(ATTRIBUTE_HTML_MESSAGE, errorMessage);
        }
        return model;
    }

    @GetMapping(value = "/studentInfo")
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

    @GetMapping(value = "/updateStudent")
    public ModelAndView updateStudentInfo(@RequestParam(value = "id") int id) {
        ModelAndView model = new ModelAndView("updateStudent");
        try {
            StudentDto studentDto = studentService.getStudentDto(id);
            model.addObject(ATTRIBUTE_HTML_PAGE_ADDRESS, "updateStudent");
            model.addObject(ATTRIBUTE_HTML_PAGE_TITLE, "Update Student");
            model.addObject(ATTRIBUTE_HTML_STUDENT, studentDto);
            model.addObject(ATTRIBUTE_HTML_GROUPS, groupService.getAllGroupDto());
        } catch (EntityNotFoundException e) {
            String errorMessage = "Problem with finding student";
            model.addObject(ATTRIBUTE_HTML_MESSAGE, errorMessage);
        }
        return model;
    }

    @PostMapping(value = "/updateStudent")
    public ModelAndView updateStudent(@ModelAttribute("studentDto") StudentDto studentDto) {
        ModelAndView model = new ModelAndView("updateStudent");
        try {
            studentService.updateStudent(studentService.convertDtoToStudent(studentDto));
            String message = "Succeccfully update student";
            model.addObject(ATTRIBUTE_HTML_MESSAGE, message);
        } catch (QueryNotExecuteException e) {
            String errorMessage = "Problem with updating student";
            model.addObject(ATTRIBUTE_HTML_MESSAGE, errorMessage);
        }
        return model;
    }

    @GetMapping(value = "/addStudent")
    public ModelAndView addStudent() {
        StudentDto studentDto = new StudentDto();
        ModelAndView model = new ModelAndView("addStudent");
        model.addObject(ATTRIBUTE_HTML_PAGE_ADDRESS, "addStudent");
        model.addObject(ATTRIBUTE_HTML_PAGE_TITLE, "Add Student");
        model.addObject(ATTRIBUTE_HTML_STUDENT, studentDto);
        model.addObject(ATTRIBUTE_HTML_GROUPS, groupService.getAllGroupDto());
        return model;
    }

    @PostMapping(value = "/addStudent")
    public ModelAndView addNewStudent(@ModelAttribute("studentDto") StudentDto studentDto) {
        ModelAndView model = new ModelAndView("addStudent");
        try {
            studentService.addStudent(studentService.convertDtoToStudent(studentDto));
            String message = "Succeccfully add new student";
            model.addObject(ATTRIBUTE_HTML_MESSAGE, message);
        } catch (QueryNotExecuteException e) {
            String errorMessage = "Problem with adding student";
            model.addObject(ATTRIBUTE_HTML_MESSAGE, errorMessage);
        }
        return model;
    }
}
