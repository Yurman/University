package com.foxminded.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.foxminded.university.exception.EntityNotFoundException;
import com.foxminded.university.service.GroupService;

@Controller
@RequestMapping
public class GroupController {

    private static final String ATTRIBUTE_HTML_GROUP = "group";
    private static final String ATTRIBUTE_HTML_GROUPS = "groups";
    private static final String ATTRIBUTE_HTML_ERROR_MESSAGE = "error";
    private GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @RequestMapping("/groups")
    public ModelAndView getGroups() {
        ModelAndView model = new ModelAndView("groups");
        model.addObject(ATTRIBUTE_HTML_GROUPS, groupService.getAllGroupDto());
        return model;
    }

    @RequestMapping(value = "/groupInfo", method = RequestMethod.GET)
    public ModelAndView getGroupInfo(@RequestParam(value = "id") int id) {
        ModelAndView model = new ModelAndView("groupInfo");
        try {
            model.addObject(ATTRIBUTE_HTML_GROUP, groupService.getGroupDto(id));
        } catch (EntityNotFoundException e) {
            String errorMessage = "Problem with finding group";
            model.addObject(ATTRIBUTE_HTML_ERROR_MESSAGE, errorMessage);
        }
        return model;
    }
}
