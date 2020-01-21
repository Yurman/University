package com.foxminded.university.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.foxminded.university.service.GroupService;

@Controller
@RequestMapping("/GroupInfo")
public class GroupInfoController {

    @Autowired
    GroupService group;

    @RequestMapping(value = "GroupInfo", method = RequestMethod.POST)
    public String deleteUser(@RequestParam int id) {
        group.getGroupById(id);
        return "GroupInfo";
    }
}
