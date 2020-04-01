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
import com.foxminded.university.service.GroupService;
import com.foxminded.university.service.StudentService;
import com.foxminded.university.service.dto.StudentDto;

@Controller
@RequestMapping
public class StudentController {

    private static final String ATTRIBUTE_HTML_STUDENT = "student";
    private static final String ATTRIBUTE_HTML_STUDENTS = "students";
    private static final String ATTRIBUTE_HTML_MESSAGE = "message";
    private static final String ATTRIBUTE_HTML_GROUPS = "groups";

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
        model.addObject(ATTRIBUTE_HTML_STUDENTS, studentService.getAllStudents());
        return model;
    }

    @GetMapping(value = "/student-info")
    public ModelAndView getStudentInfo(@RequestParam(value = "id") int id) {
        ModelAndView model = new ModelAndView("student-info");
        try {
            model.addObject(ATTRIBUTE_HTML_STUDENT, studentService.getStudentById(id));
        } catch (EntityNotFoundException e) {
            String errorMessage = "Problem with finding student";
            model.addObject(ATTRIBUTE_HTML_MESSAGE, errorMessage);
        }
        return model;
    }

    @GetMapping(value = "/delete-student")
    public ModelAndView deleteStudent(@RequestParam(value = "id") int id) {
        ModelAndView model = new ModelAndView("students");
        try {
            studentService.deleteStudent(id);
            String message = "Successfully delete student";
            model.addObject(ATTRIBUTE_HTML_MESSAGE, message);
            model.addObject(ATTRIBUTE_HTML_STUDENTS, studentService.getAllStudents());
        } catch (EntityNotFoundException e) {
            String message = "Problem with deleting student";
            model.addObject(ATTRIBUTE_HTML_MESSAGE, message);
        }
        return model;
    }

    @GetMapping(value = "/edit-student")
    public ModelAndView editStudentView(@RequestParam(name = "id", required = false) Integer id) {
        ModelAndView model = new ModelAndView("edit-student");
        try {
            StudentDto studentDto = (id != null) ? studentService.getStudentById(id) : new StudentDto();
            model.addObject(ATTRIBUTE_HTML_STUDENT, studentDto);
            model.addObject(ATTRIBUTE_HTML_GROUPS, groupService.getAllGroups());
        } catch (EntityNotFoundException e) {
            String errorMessage = "Problem with editing student";
            model.addObject(ATTRIBUTE_HTML_MESSAGE, errorMessage);
        }
        return model;
    }

    @PostMapping(value = "/edit-student")
    public ModelAndView editStudent(@ModelAttribute("studentDto") StudentDto studentDto) {
        ModelAndView model = new ModelAndView("students");
        String message = null;
        try {
            if (studentDto.getId() != 0) {
                studentService.updateStudent(studentDto);
                message = "Succeccfully update student";
            } else {
                studentService.addStudent(studentDto);
                message = "Succeccfully add new student";
            }
            model.addObject(ATTRIBUTE_HTML_MESSAGE, message);
            model.addObject(ATTRIBUTE_HTML_STUDENTS, studentService.getAllStudents());
        } catch (EntityNotFoundException e) {
            String errorMessage = "Problem with editing student";
            model.addObject(ATTRIBUTE_HTML_MESSAGE, errorMessage);
        }
        return model;
    }
}
