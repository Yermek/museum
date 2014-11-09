package com.businessproj.controller;

import com.businessproj.dao.GroupItemDAO;
import com.businessproj.model.GroupItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Kseniya
 */

@Controller
public class GrouppageController {
    private static final Logger logger = LoggerFactory.getLogger(GrouppageController.class);

    @Autowired
    private GroupItemDAO groupitemDAO;
    List<GroupItem> groups;

    @RequestMapping("/grouppage")
    public String grouppage(Model model) {
        groups = groupitemDAO.findAll();
        model.addAttribute("groups", groups);
        return "grouppage";
    }

    @RequestMapping(value = "/createGroup", method = RequestMethod.GET)
    public String createGroup(@RequestParam("namegroup") String namegroup){
        GroupItem group = new GroupItem();
        group.setNameGroupItem(namegroup);
        groupitemDAO.save(group);
        return "redirect:/grouppage";
    }
}
