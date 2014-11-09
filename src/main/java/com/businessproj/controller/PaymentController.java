package com.businessproj.controller;

import com.businessproj.dao.GroupItemDAO;
import com.businessproj.dao.OrderLineDAO;
import com.businessproj.dao.PaymentDAO;
import com.businessproj.model.*;
import com.businessproj.dao.OrderHeaderDAO;
import com.businessproj.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
import java.util.*;

/**
 * Created by
 */
@Controller
public class PaymentController {
    private static final Logger logger = LoggerFactory.getLogger(OrderpageController.class);

    @Autowired
    private PaymentDAO paymentDAO;
    @Autowired
    private OrderHeaderDAO orderHeaderDAO;
    @Autowired
    private OrderLineDAO orderLineDAO;
    @Autowired
    private UserService userService;
    @Autowired
    private GroupItemDAO groupItemDAO;
    private List<GroupItem> groups;
    //@Autowired
    //private User user;

    @RequestMapping("/payment")
    public String payment(Model model,
                          @RequestParam("idorder") Long idOrder,
                          HttpServletRequest request) {
        OrderHeader order = orderHeaderDAO.findOne(idOrder);
        List<OrderLine> ordLines = orderLineDAO.findAllByOrder(order);
        float fullamount = 0;
        for(Iterator<OrderLine> i = ordLines.iterator(); i.hasNext(); ) {
            OrderLine orderLine = i.next();
            fullamount += orderLine.getAmountLine();
        }
        model.addAttribute("order", order);
        model.addAttribute("amount", fullamount);
        //user = userService.findUser(request);
        groups = groupItemDAO.findAll();
        model.addAttribute("groups", groups);
       // if (user != null) {
       //     model.addAttribute("user", user);
       //     model.addAttribute("flag", true);
      //  } else {
            model.addAttribute("flag", false);
     //   }
        return "payment";
    }

    @RequestMapping("/createpayment")
    public String createpayment(Model model,
                          @RequestParam("idorder") Long idOrder,
                          @RequestParam("amount") float fullamount,
                          @RequestParam("numbdoc") String numbdoc) {
        OrderHeader order = orderHeaderDAO.findOne(idOrder);
        Payment paym = new Payment();
        paym.setNumbDocPayment(numbdoc);
        paym.setOrderHeader(order);
        paym.setAmountPayment(fullamount);
        Date curDate = new Date(System.currentTimeMillis());
        paym.setDatePayment(curDate);
        paymentDAO.save(paym);
        model.addAttribute("order", order);
        model.addAttribute("payment", paym);
        return "redirect:/payment";
    }
}
