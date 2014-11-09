package com.businessproj.controller;

import com.businessproj.dao.GroupItemDAO;
import com.businessproj.dao.SessionDAO;
import com.businessproj.model.GroupItem;
import com.businessproj.model.Session;
import com.businessproj.model.User;
import com.businessproj.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.businessproj.dao.ItemDAO;
import com.businessproj.model.Item;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Kseniya
 */
@Controller
public class ItempageController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private ItemDAO itemDAO;

    Item item;
    List<Item> items;

    @Autowired
    private GroupItemDAO groupitemDAO;
    List<GroupItem> groups;

    @Autowired
    SessionDAO sessionDAO;

    @Autowired
    private UserService userService;

    @RequestMapping("/itempage")
    public String itempage(Model model,
                           @RequestParam("iditem") Long iditem,
                           HttpServletRequest request) {

        Item item = itemDAO.findOne(iditem);
        model.addAttribute("selectitem", item);
        User user = userService.findUser(request);
        groups = groupitemDAO.findAll();
        model.addAttribute("groups", groups);
        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("flag", true);
        } else {
            model.addAttribute("flag", false);
        }
        return "itempage";
    }


    @RequestMapping("/createitem")
    public String createitem(Model model,
                             HttpServletRequest request) {

        groups = groupitemDAO.findAll();
        model.addAttribute("groups", groups);
        User user = userService.findUser(request);
        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("flag", true);
        } else {
            model.addAttribute("flag", false);
        }
        return "createitem";
    }

    @RequestMapping("/additem")
    public String additem(Model model,
                          @RequestParam("nameitem") String nameitem,
                          @RequestParam("modelitem") String modelitem,
                          @RequestParam("photoitem") String photoitem,
                          @RequestParam("characteritem") String characteritem,
                          @RequestParam("priceitem") Float priceitem,
                          @RequestParam("groupitem") Long groupitem,
                          HttpServletRequest request) {

        groups = groupitemDAO.findAll();
        model.addAttribute("groups", groups);
        GroupItem group = groupitemDAO.findOne(groupitem);
        Item newitem = new Item();
        newitem.setNameItem(nameitem);
        newitem.setModelItem(modelitem);
        newitem.setPriceItem(priceitem);
        newitem.setCharacterItem(characteritem);
        newitem.setPhoto(photoitem);
        newitem.setGroupItem(group);
        itemDAO.save(newitem);
        String sessionid = null;
        Cookie[] cookies = request.getCookies();
        for(int i = 0; i< cookies.length ; ++i){
            if(cookies[i].getName().equals("sessionid")){
                sessionid = cookies[i].getValue();
                break;
            }
        }
        Session ses = sessionDAO.findOneByIdentity(sessionid);
        User user = ses.getUser();
        groups = groupitemDAO.findAll();
        model.addAttribute("groups", groups);
        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("flag", true);
        } else {
            model.addAttribute("flag", false);
        }
        return "redirect:/index";
    }

    @RequestMapping("/modelitem")
    public String modelitem(Model model,
                            @RequestParam("namemodel") String namemodel,
                            HttpServletRequest request) {
        groups = groupitemDAO.findAll();
        model.addAttribute("namemodel", namemodel);
        model.addAttribute("groups", groups);
        User user = userService.findUser(request);
        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("flag", true);
        } else {
            model.addAttribute("flag", false);
        }
        return "modelitem";
    }

}
