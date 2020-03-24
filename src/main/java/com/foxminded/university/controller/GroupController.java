package com.foxminded.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.foxminded.university.domain.Group;
import com.foxminded.university.exception.EntityNotFoundException;
import com.foxminded.university.exception.QueryNotExecuteException;
import com.foxminded.university.service.DepartmentService;
import com.foxminded.university.service.GroupService;

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
        model.addObject(ATTRIBUTE_HTML_GROUPS, groupService.getAllGroupDto());
        return model;
    }

    @PostMapping(value = "/groups")
    public ModelAndView deleteGroup(@RequestParam(value = "id") int id) {
        ModelAndView model = new ModelAndView("groups");
        try {
            groupService.deleteGroup(id);
            String message = "Successfully delete group";
            model.addObject(ATTRIBUTE_HTML_MESSAGE, message);
        } catch (QueryNotExecuteException e) {
            String errorMessage = "Problem with deleting group";
            model.addObject(ATTRIBUTE_HTML_MESSAGE, errorMessage);
        }
        return model;
    }

    @GetMapping(value = "/groupInfo")
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

    @GetMapping(value = "/updateGroup")
    public ModelAndView updateGroupInfo(@RequestParam(value = "id") int id) {
        ModelAndView model = new ModelAndView("updateGroup");
        try {
            model.addObject(ATTRIBUTE_HTML_GROUP, groupService.getGroupDto(id));
            model.addObject(ATTRIBUTE_HTML_DEPARTMENTS, departmentService.getAllDepartmentDto());
        } catch (EntityNotFoundException e) {
            String errorMessage = "Problem with updating group";
            model.addObject(ATTRIBUTE_HTML_MESSAGE, errorMessage);
        }
        return model;
    }

    @PostMapping(value = "/updateGroup")
    public ModelAndView updateGroup(@RequestParam(value = "id") int id, @RequestParam(value = "title") String title,
            @RequestParam(value = "year") int year, @RequestParam(value = "departmentId") int departmentId) {
        ModelAndView model = new ModelAndView("updateGroup");
        try {
            Group newGroup = groupService.getGroupById(id);
            newGroup.setTitle(title);
            newGroup.setYear(year);
            if (departmentId != 0) {
                newGroup.setDepartment(departmentService.getDepartmentById(departmentId));
            } else
                newGroup.setDepartment(null);
            groupService.updateGroup(newGroup);
            String message = "Successfully update group";
            model.addObject(ATTRIBUTE_HTML_MESSAGE, message);
        } catch (QueryNotExecuteException e) {
            String errorMessage = "Problem with updating group";
            model.addObject(ATTRIBUTE_HTML_MESSAGE, errorMessage);
        }
        return model;
    }

    @GetMapping(value = "/addGroup")
    public ModelAndView addGroup() {
        ModelAndView model = new ModelAndView("addGroup");
        model.addObject(ATTRIBUTE_HTML_DEPARTMENTS, departmentService.getAllDepartmentDto());
        return model;
    }

    @PostMapping(value = "/addGroup")
    public ModelAndView addNewGroup(@RequestParam(value = "title") String title,
            @RequestParam(value = "year") int year, @RequestParam(value = "departmentId") int departmentId) {
        ModelAndView model = new ModelAndView("addGroup");
        try {
            Group newGroup = new Group();
            newGroup.setTitle(title);
            newGroup.setYear(year);
            if (departmentId != 0) {
                newGroup.setDepartment(departmentService.getDepartmentById(departmentId));
            }
            groupService.addGroup(newGroup);
            String message = "Successfully add new group";
            model.addObject(ATTRIBUTE_HTML_MESSAGE, message);
        } catch (QueryNotExecuteException e) {
            String errorMessage = "Problem with adding group";
            model.addObject(ATTRIBUTE_HTML_MESSAGE, errorMessage);
        }
        return model;
    }

}
