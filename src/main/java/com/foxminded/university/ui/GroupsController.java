package com.foxminded.university.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foxminded.university.service.GroupService;

@Controller
@RequestMapping("/Groups")
public class GroupsController {
    @Autowired
    GroupService group;

    @RequestMapping
    public String handleRequest(Model model) {
        model.addAttribute("groups", group.getAllGroups());
        return "Groups";
    }
}
