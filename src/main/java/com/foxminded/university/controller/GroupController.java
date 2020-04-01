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
import com.foxminded.university.service.DepartmentService;
import com.foxminded.university.service.GroupService;
import com.foxminded.university.service.dto.GroupDto;

@Controller
@RequestMapping
public class GroupController {

    private static final String ATTRIBUTE_HTML_GROUP = "group";
    private static final String ATTRIBUTE_HTML_GROUPS = "groups";
    private static final String ATTRIBUTE_HTML_DEPARTMENTS = "departments";
    private static final String ATTRIBUTE_HTML_MESSAGE = "message";

    private GroupService groupService;
    private DepartmentService departmentService;

    @Autowired
    public GroupController(GroupService groupService, DepartmentService departmentService) {
        this.groupService = groupService;
        this.departmentService = departmentService;
    }

    @GetMapping("/groups")
    public ModelAndView getGroups() {
        ModelAndView model = new ModelAndView("groups");
        model.addObject(ATTRIBUTE_HTML_GROUPS, groupService.getAllGroups());
        return model;
    }

    @GetMapping(value = "/group-info")
    public ModelAndView getGroupInfo(@RequestParam(value = "id") int id) {
        ModelAndView model = new ModelAndView("group-info");
        try {
            model.addObject(ATTRIBUTE_HTML_GROUP, groupService.getGroupById(id));
        } catch (EntityNotFoundException e) {
            String errorMessage = "Problem with finding group";
            model.addObject(ATTRIBUTE_HTML_MESSAGE, errorMessage);
        }
        return model;
    }

    @GetMapping(value = "/delete-group")
    public ModelAndView deleteGroup(@RequestParam(value = "id") int id) {
        ModelAndView model = new ModelAndView("groups");
        try {
            groupService.deleteGroup(id);
            String message = "Successfully delete group";
            model.addObject(ATTRIBUTE_HTML_MESSAGE, message);
            model.addObject(ATTRIBUTE_HTML_GROUPS, groupService.getAllGroups());
        } catch (EntityNotFoundException e) {
            String errorMessage = "Problem with deleting group";
            model.addObject(ATTRIBUTE_HTML_MESSAGE, errorMessage);
        }
        return model;
    }

    @GetMapping(value = "/edit-group")
    public ModelAndView addGroup(@RequestParam(name = "id", required = false) Integer id) {
        ModelAndView model = new ModelAndView("edit-group");
        try {
            GroupDto groupDto = (id != null) ? groupService.getGroupById(id) : new GroupDto();
            model.addObject(ATTRIBUTE_HTML_GROUP, groupDto);
            model.addObject(ATTRIBUTE_HTML_DEPARTMENTS, departmentService.getAllDepartmentDto());
        } catch (EntityNotFoundException e) {
            String errorMessage = "Problem with editing group";
            model.addObject(ATTRIBUTE_HTML_MESSAGE, errorMessage);
        }
        return model;
    }

    @PostMapping(value = "/edit-group")
    public ModelAndView addNewGroup(@ModelAttribute("groupDto") GroupDto groupDto) {
        ModelAndView model = new ModelAndView("groups");
        String message = null;
        try {
            if (groupDto.getId() != 0) {
                groupService.updateGroup(groupDto);
                message = "Successfully update group";
            } else {
                groupService.addGroup(groupDto);
                message = "Successfully add new group";
            }
            model.addObject(ATTRIBUTE_HTML_MESSAGE, message);
            model.addObject(ATTRIBUTE_HTML_GROUPS, groupService.getAllGroups());
        } catch (EntityNotFoundException e) {
            String errorMessage = "Problem with editing group";
            model.addObject(ATTRIBUTE_HTML_MESSAGE, errorMessage);
        }
        return model;
    }

}
