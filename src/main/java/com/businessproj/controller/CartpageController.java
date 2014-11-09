package com.businessproj.controller;

import com.businessproj.dao.CartDAO;
import com.businessproj.dao.CartLineDAO;
import com.businessproj.dao.GroupItemDAO;
import com.businessproj.model.*;
import com.businessproj.dao.ItemDAO;
import com.businessproj.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Kseniya
 */
@Controller
public class CartpageController {
    private static final Logger logger = LoggerFactory.getLogger(CartpageController.class);
    @Autowired
    private CartDAO cartDAO;
    @Autowired
    private CartLineDAO cartlineDAO;
    @Autowired
    private ItemDAO itemDAO;

    @Autowired
    private GroupItemDAO groupitemDAO;
    List<GroupItem> groups;

    @Autowired
    private UserService userService;

    @RequestMapping("/cartpage")
    public String cartpage(Model model, HttpServletRequest request) {

        User user = userService.findUser(request);
        groups = groupitemDAO.findAll();
        model.addAttribute("groups", groups);
        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("flag", true);
        } else {
            model.addAttribute("flag", false);
        }
        return "cartpage";
    }


    @RequestMapping(value = "/createCart", method = RequestMethod.GET)
    public String createCart(){
        Cart cart = new Cart();
        String ipaddr = "";
        cart.setIpAddr(ipaddr);
        cartDAO.save(cart);
        return "redirect:/cartpage";
    }

    @RequestMapping(value = "/addlinetocart", method = RequestMethod.GET)
    public String addLineToCart(@RequestParam("iditem") Long iditem,
                                @RequestParam("idgr") Long idgr,
                                HttpServletRequest request){
        CartLine cartline = new CartLine();
        String ipaddr = "";
        Cart cart = cartDAO.findByIpAddr(ipaddr);
        Item item = itemDAO.findOne(iditem);
        cartline.setItem(item);
        cartline.setQuantityItem(1);
        cartline.setPriceOneItem(item.getPriceItem());
        cartline.setAmountCartLine(item.getPriceItem());
        cartline.setCart(cart);
        cartlineDAO.save(cartline);
        return "redirect:/cartpage";
    }
}
