package com.businessproj.controller;

import com.businessproj.dao.*;
import com.businessproj.model.*;

import java.lang.*;

import com.businessproj.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Kseniya
 */
@Controller
public class ItemslistController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);


    @Autowired
    private ItemDAO itemDAO;
    List<Item> items;

    @Autowired
    SessionDAO sessionDAO;

    @Autowired
    private GroupItemDAO groupitemDAO;
    List<GroupItem> groups;

    @Autowired
    private CartDAO cartDAO;

    @Autowired
    private CartLineDAO cartLineDAO;

    @Autowired
    private UserService userService;


    @RequestMapping("/itemslist")
    public String itemslist(Model model,
                        @RequestParam("idgroup") Long idgr,
                        HttpServletRequest request,
                        HttpServletResponse response) {
        GroupItem group = groupitemDAO.findOne(idgr);
        items = itemDAO.findAllByGroupItem(group);
        model.addAttribute("items", items);
        groups = groupitemDAO.findAll();
        model.addAttribute("groups", groups);
        model.addAttribute("itemgroup", group);
        User user = userService.findUser(request);
        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("flag", true);
        } else {
            model.addAttribute("flag", false);
        }
        return "itemslist";
    }

    @RequestMapping("/addItemToCart")
    public String addItemToCart(HttpServletRequest request,
                                Model model,
                                @RequestParam("iditem") Long iditem,
                                @RequestParam("idgroup") Long idgr){
        GroupItem group = groupitemDAO.findOne(idgr);
        items = itemDAO.findAllByGroupItem(group);
        model.addAttribute("items", items);
        groups = groupitemDAO.findAll();
        model.addAttribute("groups", groups);
        model.addAttribute("itemgroup", group);
        Item item = itemDAO.findOne(iditem);
        String ipUser = request.getRemoteAddr();
        Cart cart = cartDAO.findByIpAddr(ipUser);
        if (cart == null){
            Cart newcart = new Cart();
            newcart.setIpAddr(ipUser);
            cartDAO.save(newcart);
            cart = cartDAO.findByIpAddr(ipUser);
        }
        CartLine newline = new CartLine();
        newline.setItem(item);
        newline.setCart(cart);
        newline.setPriceOneItem(item.getPriceItem());
        newline.setQuantityItem(1);
        newline.setAmountCartLine(item.getPriceItem());
        cartLineDAO.save(newline);
        User user = userService.findUser(request);
        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("flag", true);
        } else {
            model.addAttribute("flag", false);
        }
        return "redirect:/itemslist?idgroup=" + idgr;
    }

}
