package com.foxminded.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.foxminded.university.service.GroupService;

@Controller
@RequestMapping
public class GroupController {

    @Autowired
    private GroupService group;

    @RequestMapping("/groups")
    public String getGroups(Model model) {
        model.addAttribute("groups", group.getAllGroups());
        return "groups";
    }

    @RequestMapping(value = "/groupInfo", method = RequestMethod.GET)
    public String getGroupInfo(@RequestParam(value = "id") int id, Model model) {
        model.addAttribute("group", group.getGroupById(id));
        return "groupInfo";
    }
}
