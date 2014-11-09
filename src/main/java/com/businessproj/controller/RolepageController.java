package com.businessproj.controller;

import com.businessproj.dao.GroupItemDAO;
import com.businessproj.model.GroupItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.businessproj.dao.RoleDAO;
import com.businessproj.model.Role;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Kseniya
 */

@Controller
public class RolepageController {
    private static final Logger logger = LoggerFactory.getLogger(RolepageController.class);

    @Autowired private RoleDAO roleDAO;
    List<Role> roles;

    @Autowired private GroupItemDAO groupitemDAO;
    List<GroupItem> groups;

    @RequestMapping("/rolepage")
    public String rolepage(Model model) {
        roles = roleDAO.findAll();
        model.addAttribute("roles", roles);
        groups = groupitemDAO.findAll();
        model.addAttribute("groups", groups);
        return "rolepage";
    }

    @RequestMapping(value = "/createRole", method = RequestMethod.GET)
    public String createRole(@RequestParam("namerole") String namerole){
        Role role = new Role();
        role.setNameRole(namerole);
        roleDAO.save(role);
        return "redirect:/rolepage";
    }

}
