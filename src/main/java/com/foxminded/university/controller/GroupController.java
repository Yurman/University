package com.foxminded.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView getGroups() {
        ModelAndView model = new ModelAndView("groups.html");
        model.addObject("groups", groupService.getAllGroupDto());
        return model;
    }

    @RequestMapping(value = "/groupInfo", method = RequestMethod.GET)
    public ModelAndView getGroupInfo(@RequestParam(value = "id") int id) {
        GroupDto groupDto;
        try {
            groupDto = groupService.getGroupDto(id);
        } catch (EntityNotFoundException e) {
            groupDto = new GroupDto();
            groupDto.setId(0);
        }
        ModelAndView model = new ModelAndView("groupInfo.html");
        model.addObject("group", groupDto);
        return model;
    }
}
