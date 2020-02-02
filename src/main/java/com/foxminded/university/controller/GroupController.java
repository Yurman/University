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

    private GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @RequestMapping("/groups")
    public String getGroups(Model model) {
        model.addAttribute("groups", groupService.getAllGroups());
        return "groups";
    }

    @RequestMapping(value = "/groupInfo", method = RequestMethod.GET)
    public String getGroupInfo(@RequestParam(value = "id") int id, Model model) {
        model.addAttribute("group", groupService.getGroupById(id));
        return "groupInfo";
    }
}
