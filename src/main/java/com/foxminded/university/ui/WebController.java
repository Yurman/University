package com.foxminded.university.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.foxminded.university.service.GroupService;
import com.foxminded.university.service.StudentService;

@Controller
@RequestMapping
public class WebController {

    @Autowired
    GroupService group;
    @Autowired
    StudentService student;

    @RequestMapping("/")
    public String handleRequestRoot(Model model) {
        return "main";
    }

    @RequestMapping("/main")
    public String handleRequestMain(Model model) {
        model.addAttribute("msg", "Welcome to our University");
        return "main";
    }

    @RequestMapping("/Groups")
    public String handleRequestGroups(Model model) {
        model.addAttribute("groups", group.getAllGroups());
        return "Groups";
    }

    @RequestMapping("/Students")
    public String handleRequestStudents(Model model) {
        model.addAttribute("students", student.getAllStudents());
        return "Students";
    }

    @RequestMapping(value = "/GroupInfo", method = RequestMethod.POST)
    public String handleRequestGroupInfo(@RequestParam(value = "id") int id, Model model) {
        model.addAttribute("group", group.getGroupById(id));
        return "GroupInfo";
    }

    @RequestMapping(value = "/StudentInfo", method = RequestMethod.POST)
    public String handleRequestStudentInfo(@RequestParam(value = "id") int id, Model model) {
        model.addAttribute("student", student.getStudentById(id));
        return "StudentInfo";
    }
}
