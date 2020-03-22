package com.foxminded.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.foxminded.university.domain.Group;
import com.foxminded.university.exception.EntityNotFoundException;
import com.foxminded.university.exception.QueryNotExecuteException;
import com.foxminded.university.service.GroupService;

@Controller
@RequestMapping
public class GroupController {

    private static final String ATTRIBUTE_HTML_GROUP = "group";
    private static final String ATTRIBUTE_HTML_GROUPS = "groups";
    private static final String ATTRIBUTE_HTML_MESSAGE = "message";
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

    @RequestMapping(value = "/groups", method = RequestMethod.POST)
    public ModelAndView deleteGroup(@RequestParam(value = "id") int id) {
        ModelAndView model = new ModelAndView("groups");
        try {
            groupService.deleteGroup(id);
            String message = "Successfully delete group";
            model.addObject(ATTRIBUTE_HTML_MESSAGE, message);
        } catch (DataAccessException e) {
            String errorMessage = "Problem with deleting group";
            model.addObject(ATTRIBUTE_HTML_MESSAGE, errorMessage);
        }
        return model;
    }

    @RequestMapping(value = "/groupInfo", method = RequestMethod.GET)
    public ModelAndView getGroupInfo(@RequestParam(value = "id") int id) {
        ModelAndView model = new ModelAndView("groupInfo");
        try {
            model.addObject(ATTRIBUTE_HTML_GROUP, groupService.getGroupDto(id));
        } catch (EntityNotFoundException e) {
            String errorMessage = "Problem with finding group";
            model.addObject(ATTRIBUTE_HTML_MESSAGE, errorMessage);
        }
        return model;
    }

    @RequestMapping(value = "/updateGroup", method = RequestMethod.GET)
    public ModelAndView updateGroupInfo(@RequestParam(value = "id") int id) {
        ModelAndView model = new ModelAndView("updateGroup");
        try {
            model.addObject(ATTRIBUTE_HTML_GROUP, groupService.getGroupDto(id));
        } catch (EntityNotFoundException e) {
            String errorMessage = "Problem with updating group";
            model.addObject(ATTRIBUTE_HTML_MESSAGE, errorMessage);
        }
        return model;
    }

    @RequestMapping(value = "/updateGroup", method = RequestMethod.POST)
    public ModelAndView updateGroup(@RequestParam(value = "id") int id, @RequestParam(value = "title") String title,
            @RequestParam(value = "year") int year, @RequestParam(value = "department") String department) {
        ModelAndView model = new ModelAndView("updateGroup");
        try {
            Group newGroup = groupService.getGroupById(id);
            newGroup.setTitle(title);
            newGroup.setYear(year);
            groupService.updateGroup(newGroup);
            String message = "Succeccfully update group";
            model.addObject(ATTRIBUTE_HTML_MESSAGE, message);
        } catch (QueryNotExecuteException e) {
            String errorMessage = "Problem with updating group";
            model.addObject(ATTRIBUTE_HTML_MESSAGE, errorMessage);
        }
        return model;
    }

    @RequestMapping(value = "/addGroup", method = RequestMethod.GET)
    public ModelAndView addGroup() {
        ModelAndView model = new ModelAndView("addGroup");
        return model;
    }

    @RequestMapping(value = "/addGroup", method = RequestMethod.POST)
    public ModelAndView addNewGroup(@RequestParam(value = "title") String title,
            @RequestParam(value = "year") int year, @RequestParam(value = "department") String department) {
        ModelAndView model = new ModelAndView("groups");
        try {
            Group newGroup = new Group();
            newGroup.setTitle(title);
            newGroup.setYear(year);
            groupService.addGroup(newGroup);
            String message = "Succeccfully add new group";
            model.addObject(ATTRIBUTE_HTML_MESSAGE, message);
        } catch (QueryNotExecuteException e) {
            String errorMessage = "Problem with adding group";
            model.addObject(ATTRIBUTE_HTML_MESSAGE, errorMessage);
        }
        return model;
    }

}
