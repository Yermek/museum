package com.businessproj.controller;

import com.businessproj.dao.UserDAO;
import com.businessproj.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.businessproj.service.TestService;

/**
 * Created by Kseniya
 */
@Controller
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private TestService testService;

    @Autowired
    private UserDAO userDAO;

    @RequestMapping("/test")
    public String test(@RequestParam(value = "var", required = false, defaultValue = "default") String var,  Model model) {

        logger.info("test request running, var = {}", var);

        final String someVar = testService.testFunction();

        model.addAttribute("var", var);
        return "test"; // template name: templates/test.vm
    }

    @RequestMapping("/test2")
    public String test2(Model model) {
        //User someUser = userDAO.getUser(123);
        //model.addAttribute("var", someUser.getNameUser() + " " + someUser.getEmailUser());

        return "test";
    }
}
