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
    private static final String ATTRIBUTE_HTML_STUDENTS = "students";
    private static final String ATTRIBUTE_HTML_MESSAGE = "message";
    private static final String ATTRIBUTE_HTML_GROUPS = "groups";
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
        model.addObject(ATTRIBUTE_HTML_STUDENTS, studentService.getAllStudentDto());
        return model;
    }

    @GetMapping(value = "/student_info")
    public ModelAndView getStudentInfo(@RequestParam(value = "id") int id) {
        ModelAndView model = new ModelAndView("student_info");
        try {
            model.addObject(ATTRIBUTE_HTML_STUDENT, studentService.getStudentDto(id));
        } catch (EntityNotFoundException e) {
            String errorMessage = "Problem with finding student";
            model.addObject(ATTRIBUTE_HTML_MESSAGE, errorMessage);
        }
        return model;
    }

    @GetMapping(value = "/delete_student")
    public ModelAndView deleteStudent(@RequestParam(value = "id") int id) {
        ModelAndView model = new ModelAndView("delete_student");
        String message = null;
        try {
            studentService.deleteStudent(id);
            message = "Successfully delete student";
        } catch (QueryNotExecuteException e) {
            message = "Problem with deleting student";
        }
        model.addObject(ATTRIBUTE_HTML_MESSAGE, message);
        return model;
    }

    @GetMapping(value = "/edit_student")
    public ModelAndView editStudentView(@RequestParam(name = "id", required = false) Integer id) {
        ModelAndView model = new ModelAndView("edit_student");
        StudentDto studentDto = new StudentDto();
        String pageTitle = null;
        try {
            if (id != null) {
                pageTitle = "Update Student";
                studentDto = studentService.getStudentDto(id);
            } else {
                pageTitle = "Add Student";
            }
            model.addObject(ATTRIBUTE_HTML_PAGE_TITLE, pageTitle);
            model.addObject(ATTRIBUTE_HTML_STUDENT, studentDto);
            model.addObject(ATTRIBUTE_HTML_GROUPS, groupService.getAllGroupDto());
        } catch (QueryNotExecuteException e) {
            String errorMessage = "Problem with editing student";
            model.addObject(ATTRIBUTE_HTML_MESSAGE, errorMessage);
        }
        return model;
    }

    @PostMapping(value = "/edit_student")
    public ModelAndView editStudent(@ModelAttribute("studentDto") StudentDto studentDto) {
        ModelAndView model = new ModelAndView("edit_student");
        String message = null;
        try {
            if (studentDto.getId() != 0) {
                studentService.updateStudent(studentService.convertDtoToStudent(studentDto));
                message = "Succeccfully update student";
            } else {
                studentService.addStudent(studentService.convertDtoToStudent(studentDto));
                message = "Succeccfully add new student";
            }
            model.addObject(ATTRIBUTE_HTML_MESSAGE, message);
        } catch (QueryNotExecuteException e) {
            String errorMessage = "Problem with editing student";
            model.addObject(ATTRIBUTE_HTML_MESSAGE, errorMessage);
        }
        return model;
    }
}
