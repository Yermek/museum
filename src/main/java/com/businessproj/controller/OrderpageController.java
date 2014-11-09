package com.businessproj.controller;

import com.businessproj.dao.*;
import com.businessproj.model.*;
import com.businessproj.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created
 * Controller of orders
 */
@Controller
public class OrderpageController {
    private static final Logger logger = LoggerFactory.getLogger(OrderpageController.class);

    @Autowired
    private GroupItemDAO groupitemDAO;
    List<GroupItem> groups;
    @Autowired
    private OrderHeaderDAO orderHeaderDAO;
    @Autowired
    private OrderLineDAO orderLineDAO;
    @Autowired
    private CartDAO cartDAO;
    @Autowired
    private CartLineDAO cartLineDAO;
    @Autowired
    private UserService userService;
    @Autowired
    private UserDAO userDAO;

    @RequestMapping("/orderpage")
    public String orderpage(Model model,
                            @RequestParam("idorder") Long idorder, HttpServletRequest request) {

        OrderHeader order = orderHeaderDAO.findOne(idorder);
        List <OrderLine> orderLines = orderLineDAO.findAllByOrder(order);
        model.addAttribute("orderLines", orderLines);
        User user = userService.findUser(request);
        groups = groupitemDAO.findAll();
        model.addAttribute("groups", groups);
        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("flag", true);
        } else {
            model.addAttribute("flag", false);
        }
        return "orderpage";
    }

    @RequestMapping("/createorder")
    public String createorder(Model model, @RequestParam("iduser") Long iduser,
                            @RequestParam("idcart") Long idcart,
                            @RequestParam("numbdoc") String numbdoc,
                            @RequestParam("descr") String descr) {
        groups = groupitemDAO.findAll();
        model.addAttribute("groups", groups);
        User user = new User();
        user = userDAO.findOne(iduser);
        OrderHeader order = new OrderHeader();
        order.setNumbDocOrder(numbdoc);
        order.setDescOrder(descr);
        Date curDate = new Date(System.currentTimeMillis());
        order.setDateOrder(curDate);
        order.setUser(user);
        Cart cart = cartDAO.findOne(idcart);
        List<CartLine> cartlines = cartLineDAO.findAllByCart(cart);
        for(Iterator<CartLine> i = cartlines.iterator(); i.hasNext(); ) {
            CartLine cartLine = i.next();
            OrderLine orderLine = new OrderLine();
            orderLine.setOrder(order);
            orderLine.setItem(cartLine.getItem());
            orderLine.setPriceOne(cartLine.getPriceOneItem());
            orderLine.setQuantItem(cartLine.getQuantityItem());
            orderLine.setAmountLine(cartLine.getAmountCartLine());
            orderLineDAO.save(orderLine);
        }
        return "orderpage";
    }
}
