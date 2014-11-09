package com.businessproj.controller;

import com.businessproj.SessionIdentifierGenerator;
import com.businessproj.dao.*;
import com.businessproj.model.*;
import com.businessproj.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//import javax.faces.application.FacesMessage;
//import javax.faces.context.FacesContext;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Kseniya
 */

@Controller
public class UserpageController {
    private static final Logger logger = LoggerFactory.getLogger(UserpageController.class);

    @Autowired
    private UserDAO userDAO;
    User users;

    @Autowired
    private MessageDAO messageDAO;
    List<Message> messages;

    @Autowired
    private GroupItemDAO groupitemDAO;
    List<GroupItem> groups;

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private SessionDAO sessionDAO;

    @Autowired
    private UserService userService;

    @RequestMapping("/userpage")
    public String userpage(Model model,
                           @RequestParam("id") Long id) {
        users = userDAO.findOne(id);
        model.addAttribute("user", users);
        groups = groupitemDAO.findAll();
        model.addAttribute("groups", groups);
        return "userpage";
    }

    @RequestMapping("/users")
    public String users(Model model) {
        List<User> usrs = userDAO.findAll();
        model.addAttribute("users", usrs);
        groups = groupitemDAO.findAll();
        model.addAttribute("groups", groups);
        return "users";
    }

    @RequestMapping("/register")
    public String register(Model model) {

        groups = groupitemDAO.findAll();
        model.addAttribute("groups", groups);
        return "register";
    }

    @RequestMapping("/login")
    public String login(Model model) {

        groups = groupitemDAO.findAll();
        model.addAttribute("groups", groups);
        return "login";
    }

    @RequestMapping("/messages")
    public String messages(Model model,
                           @RequestParam("id") Long id,
                           HttpServletRequest request) {
        User user = userDAO.findOne(id);
        List<Message> messagesTo = messageDAO.findAllByToUser(user);
        List<Message> messagesFrom = messageDAO.findAllByFromUser(user);
        model.addAttribute("messagesTo", messagesTo);
        model.addAttribute("messagesFrom", messagesFrom);
        User user1 = userService.findUser(request);
        groups = groupitemDAO.findAll();
        model.addAttribute("groups", groups);
        if (user != null) {
            model.addAttribute("user", user1);
            model.addAttribute("flag", true);
        } else {
            model.addAttribute("flag", false);
        }
        return "messages";
    }

    @RequestMapping("sendmessage")
    public String sendmessage(Model model,
                           @RequestParam("id") Long id,
                           @RequestParam("nametouser") String toUser,
                           @RequestParam("message") String message) {
        User user = userDAO.findOne(id);
        User touser = userDAO.findByNameUser(toUser);
        Message mess = new Message();
        mess.setFromUser(user);
        mess.setToUser(touser);
        mess.setMessage(message);
        messageDAO.save(mess);

        messages = messageDAO.findAllByToUser(user);
        model.addAttribute("messages", messages);
        groups = groupitemDAO.findAll();
        model.addAttribute("groups", groups);
        model.addAttribute("user", user);
        return "messages";
    }

    @RequestMapping("/sendmoney")
    public String sendmoney(Model model,
                              HttpServletRequest request,
                              @RequestParam("id") Long id) {

        User user = userService.findUser(request);
        groups = groupitemDAO.findAll();
        model.addAttribute("groups", groups);
        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("flag", true);
        } else {
            model.addAttribute("flag", false);
        }
        return "sendmoney";
    }

    @RequestMapping("/send")
    public String send(Model model,
                            @RequestParam("id") Long id,
                            @RequestParam("nametouser") String toUser,
                            @RequestParam("amount") Float amount) {
        User user = userDAO.findOne(id);
        User touser = userDAO.findByNameUser(toUser);
        if (user.getAccBalanceUser() - amount > 3){
            touser.setAccBalanceUser(touser.getAccBalanceUser() + amount);
            user.setAccBalanceUser(user.getAccBalanceUser() - amount);
            String mess = "Send money is complete!";
            model.addAttribute("mess", mess);
        } else {
            String error = "You can't send money to another user!";
            model.addAttribute("mess", error);
        }
        groups = groupitemDAO.findAll();
        model.addAttribute("groups", groups);
        return "userpage";
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.GET)
    public String createUser(Model model,
                             @RequestParam("login") String login,
                             @RequestParam("pass") String password,
                             @RequestParam("email") String email){
        User user = new User();
        user.setNameUser(login);
        user.setPassUser(password);
        user.setEmailUser(email);
        Long idrole = new Long(2);
        user.setAccBalanceUser(1000000);
        Role role = roleDAO.findOne(idrole);
        user.setRole(role);
        userDAO.save(user);
        groups = groupitemDAO.findAll();
        model.addAttribute("groups", groups);
        model.addAttribute("message", "Congratulations! You are registed!");
        return "redirect:/index";
    }

    @RequestMapping("/auth")
    public String auth(HttpServletResponse response, Model model,
                       @RequestParam("login") String nameus,
                       @RequestParam("pass") String password) {
        User user = userDAO.findByNameUserAndPassUser(nameus, password);
        groups = groupitemDAO.findAll();
        model.addAttribute("groups", groups);
        if (user != null) {
            Session session = new Session();
            session.setUser(user);
            SessionIdentifierGenerator sid = new SessionIdentifierGenerator();
            session.setIdentity(sid.nextSessionId());
            sessionDAO.save(session);
            Cookie cook = new Cookie("sessionid", session.getIdentity());
            response.addCookie(cook);
            model.addAttribute("user", user);
            model.addAttribute("flag", true);
            return "index";
        } else {
            return "login";
        }
    }
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, Model model, HttpServletResponse response) {
        String sessionid = null;
        Cookie[] cookies = request.getCookies();
        for(int i = 0; i< cookies.length ; ++i){
            if(cookies[i].getName().equals("sessionid")){
                sessionid = cookies[i].getValue();
                break;
            }
        }
        Session ses = sessionDAO.findOneByIdentity(sessionid);
        User loguser = ses.getUser();
        if (loguser != null) {
            sessionDAO.delete(ses);
        }
        groups = groupitemDAO.findAll();
       // model.addAttribute("user", loguser);
        model.addAttribute("flag", false);
        model.addAttribute("groups", groups);
        return "index";
    }


}
