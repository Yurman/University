package com.foxminded.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.foxminded.university.exception.EntityNotFoundException;
import com.foxminded.university.service.GroupService;
import com.foxminded.university.service.dto.GroupDto;

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
        model.addAttribute("groups", groupService.getAllGroupDto());
        return "groups";
    }

    @RequestMapping(value = "/groupInfo", method = RequestMethod.GET)
    public String getGroupInfo(@RequestParam(value = "id") int id, Model model) {
        GroupDto groupDto;
        try {
            groupDto = groupService.getGroupDto(id);
        } catch (EntityNotFoundException e) {
            groupDto = new GroupDto();
            groupDto.setId(0);
            model.addAttribute("group", groupDto);
        }
        model.addAttribute("group", groupDto);
        return "groupInfo";
    }
}
