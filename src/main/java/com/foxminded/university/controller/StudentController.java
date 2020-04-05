package com.foxminded.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    private StudentService studentService;
    private GroupService groupService;

    @Autowired
    public StudentController(StudentService studentService, GroupService groupService) {
        this.studentService = studentService;
        this.groupService = groupService;

    }

    @RequestMapping("/students")
    public ModelAndView getStudents() {
        ModelAndView model = new ModelAndView("students");
        try {
            model.addObject(ATTRIBUTE_HTML_STUDENTS, studentService.getAllStudentDto());
        } catch (QueryNotExecuteException e) {
            String errorMessage = "Problem with finding students";
            model.addObject(ATTRIBUTE_HTML_MESSAGE, errorMessage);
        }
        return model;
    }

    @GetMapping(value = "/student-info")
    public ModelAndView getStudentInfoView(@RequestParam(value = "id") int id) {
        ModelAndView model = new ModelAndView("student-info");
        try {
            model.addObject(ATTRIBUTE_HTML_STUDENT, studentService.getStudentDtoById(id));
        } catch (EntityNotFoundException | QueryNotExecuteException e) {
            String errorMessage = "Problem with finding student";
            model.addObject(ATTRIBUTE_HTML_MESSAGE, errorMessage);
        }
        return model;
    }

    @GetMapping(value = "/delete-student")
    public String deleteStudent(@RequestParam(value = "id") int id, RedirectAttributes redirectAttributes) {
        String message = null;
        try {
            studentService.deleteStudent(id);
            message = "Successfully delete student";
        } catch (QueryNotExecuteException e) {
            message = "Problem with deleting student";
        }
        redirectAttributes.addFlashAttribute(ATTRIBUTE_HTML_MESSAGE, message);
        return "redirect:/students";
    }

    @GetMapping(value = "/edit-student")
    public ModelAndView showEditStudentView(@RequestParam(name = "id", required = false) Integer id) {
        ModelAndView model = new ModelAndView("edit-student");
        try {
            StudentDto studentDto = (id != null) ? studentService.getStudentDtoById(id) : new StudentDto();
            model.addObject(ATTRIBUTE_HTML_STUDENT, studentDto);
            model.addObject(ATTRIBUTE_HTML_GROUPS, groupService.getAllGroupDto());
        } catch (EntityNotFoundException | QueryNotExecuteException e) {
            String errorMessage = "Problem with editing student";
            model.addObject(ATTRIBUTE_HTML_MESSAGE, errorMessage);
        }
        return model;
    }

    @PostMapping(value = "/edit-student")
    public String editStudent(@ModelAttribute("studentDto") StudentDto studentDto,
            RedirectAttributes redirectAttributes) {
        String message = null;
        try {
            if (studentDto.getId() != 0) {
                studentService.updateStudentDto(studentDto);
                message = "Succeccfully update student";
            } else {
                studentService.addStudentDto(studentDto);
                message = "Succeccfully add new student";
            }
        } catch (EntityNotFoundException | QueryNotExecuteException e) {
            message = "Problem with editing student";
        }
        redirectAttributes.addFlashAttribute(ATTRIBUTE_HTML_MESSAGE, message);
        return "redirect:/students";
    }
}
