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
import com.foxminded.university.exception.QueryNotExecuteException;
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
    private static final String ATTRIBUTE_HTML_PAGE_TITLE = "pageTitle";

    private GroupService groupService;
    private DepartmentService departmentService;

    @Autowired
    public GroupController(GroupService groupService, DepartmentService departmentService) {
        this.groupService = groupService;
        this.departmentService = departmentService;
    }

    @ModelAttribute(value = "groupDto")
    public GroupDto newGroupDto() {
        return new GroupDto();
    }

    @GetMapping("/groups")
    public ModelAndView getGroups() {
        ModelAndView model = new ModelAndView("groups");
        model.addObject(ATTRIBUTE_HTML_GROUPS, groupService.getAllGroupDto());
        return model;
    }

    @GetMapping(value = "/group_info")
    public ModelAndView getGroupInfo(@RequestParam(value = "id") int id) {
        ModelAndView model = new ModelAndView("group_info");
        try {
            model.addObject(ATTRIBUTE_HTML_GROUP, groupService.getGroupDto(id));
        } catch (EntityNotFoundException e) {
            String errorMessage = "Problem with finding group";
            model.addObject(ATTRIBUTE_HTML_MESSAGE, errorMessage);
        }
        return model;
    }

    @GetMapping(value = "/delete_group")
    public ModelAndView deleteGroup(@RequestParam(value = "id") int id) {
        ModelAndView model = new ModelAndView("delete_group");
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

    @GetMapping(value = "/edit_group")
    public ModelAndView addGroup(@RequestParam(name = "id", required = false) Integer id) {
        ModelAndView model = new ModelAndView("edit_group");
        GroupDto groupDto = new GroupDto();
        String pageTitle = null;
        try {
            if (id != null) {
                groupDto = groupService.getGroupDto(id);
                pageTitle = "Update group";
            } else {
                pageTitle = "Add group";
            }
            model.addObject(ATTRIBUTE_HTML_PAGE_TITLE, pageTitle);
            model.addObject(ATTRIBUTE_HTML_GROUP, groupDto);
            model.addObject(ATTRIBUTE_HTML_DEPARTMENTS, departmentService.getAllDepartmentDto());
        } catch (QueryNotExecuteException e) {
            String errorMessage = "Problem with editing group";
            model.addObject(ATTRIBUTE_HTML_MESSAGE, errorMessage);
        }
        return model;
    }

    @PostMapping(value = "/edit_group")
    public ModelAndView addNewGroup(@ModelAttribute("groupDto") GroupDto groupDto) {
        ModelAndView model = new ModelAndView("edit_group");
        String successMessage = null;
        try {
            if (groupDto.getId() != 0) {
                groupService.updateGroup(groupService.convertDtoToGroup(groupDto));
                successMessage = "Successfully update group";
            } else {
                groupService.addGroup(groupService.convertDtoToGroup(groupDto));
                successMessage = "Successfully add new group";
            }
            model.addObject(ATTRIBUTE_HTML_MESSAGE, successMessage);
        } catch (QueryNotExecuteException e) {
            String errorMessage = "Problem with editing group";
            model.addObject(ATTRIBUTE_HTML_MESSAGE, errorMessage);
        }
        return model;
    }

}
