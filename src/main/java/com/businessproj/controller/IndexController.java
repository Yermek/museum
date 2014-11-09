package com.businessproj.controller;

import com.businessproj.dao.GroupItemDAO;
import com.businessproj.dao.SessionDAO;
import com.businessproj.model.GroupItem;
import com.businessproj.model.Session;
import com.businessproj.model.User;
import com.businessproj.service.TestService;
import com.businessproj.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Kseniya
 */
@Controller
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private GroupItemDAO groupitemDAO;
    List<GroupItem> groups;

    @Autowired
    private SessionDAO sessionDAO;

    @Autowired
    private UserService userService;


    @RequestMapping("/")
    public String index(Model model, HttpServletRequest request,
                        HttpServletResponse response) {
        User user = userService.findUser(request);
//        groups = groupitemDAO.findAll();
//        model.addAttribute("groups", groups);
        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("flag", true);
        } else {
            model.addAttribute("flag", false);
        }
        return "index";
    }

    @RequestMapping("/coininline")
    public String coininline(Model model) {
        return "coininline";
    }

    @RequestMapping("/textmessage")
    public String textmessage(HttpServletRequest request,
                              HttpServletResponse response, Model model) {
        model.addAttribute("message", "Congratulations!");
        User user = userService.findUser(request);
        groups = groupitemDAO.findAll();
        model.addAttribute("groups", groups);
        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("flag", true);
        } else {
            model.addAttribute("flag", false);
        }
        return "textmessage";
    }
}
