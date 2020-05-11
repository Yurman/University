package com.foxminded.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    private GroupService groupService;
    private DepartmentService departmentService;

    @Autowired
    public GroupController(GroupService groupService, DepartmentService departmentService) {
        this.groupService = groupService;
        this.departmentService = departmentService;
    }

    @GetMapping("/group-templates/groups")
    public ModelAndView getGroups() {
        ModelAndView model = new ModelAndView("group-templates/groups");
        try {
            model.addObject(ATTRIBUTE_HTML_GROUPS, groupService.getAllGroupDto());
        } catch (QueryNotExecuteException e) {
            String errorMessage = "Problem with finding groups";
            model.addObject(ATTRIBUTE_HTML_MESSAGE, errorMessage);
        }
        return model;
    }

    @GetMapping(value = "/group-templates/group-info")
    public ModelAndView getGroupInfoView(@RequestParam(value = "id") int id) {
        ModelAndView model = new ModelAndView("group-templates/group-info");
        try {
            model.addObject(ATTRIBUTE_HTML_GROUP, groupService.getGroupDtoById(id));
        } catch (EntityNotFoundException | QueryNotExecuteException e) {
            String errorMessage = "Problem with finding group";
            model.addObject(ATTRIBUTE_HTML_MESSAGE, errorMessage);
        }
        return model;
    }

    @GetMapping(value = "/group-templates/delete-group")
    public String deleteGroup(@RequestParam(value = "id") int id, RedirectAttributes redirectAttributes) {
        String message = null;
        try {
            groupService.deleteGroup(id);
            message = "Successfully delete group";
        } catch (QueryNotExecuteException e) {
            message = "Problem with deleting group";
        }
        redirectAttributes.addFlashAttribute(ATTRIBUTE_HTML_MESSAGE, message);
        return "redirect:/group-templates/groups";
    }

    @GetMapping(value = "/group-templates/edit-group")
    public ModelAndView showEditGroupView(@RequestParam(name = "id", required = false) Integer id) {
        ModelAndView model = new ModelAndView("group-templates/edit-group");
        try {
            GroupDto groupDto = (id != null) ? groupService.getGroupDtoById(id) : new GroupDto();
            model.addObject(ATTRIBUTE_HTML_GROUP, groupDto);
            model.addObject(ATTRIBUTE_HTML_DEPARTMENTS, departmentService.getAllDepartmentDto());
        } catch (EntityNotFoundException | QueryNotExecuteException e) {
            String errorMessage = "Problem with editing group";
            model.addObject(ATTRIBUTE_HTML_MESSAGE, errorMessage);
        }
        return model;
    }

    @PostMapping(value = "/group-templates/edit-group")
    public String editGroup(@ModelAttribute("groupDto") GroupDto groupDto, RedirectAttributes redirectAttributes) {
        String message = null;
        try {
            if (groupDto.getId() != 0) {
                groupService.updateGroup(groupDto);
                message = "Successfully update group";
            } else {
                groupService.addGroup(groupDto);
                message = "Successfully add new group";
            }
        } catch (EntityNotFoundException | QueryNotExecuteException e) {
            message = "Problem with editing group";
        }
        redirectAttributes.addFlashAttribute(ATTRIBUTE_HTML_MESSAGE, message);
        return "redirect:/group-templates/groups";
    }

}
