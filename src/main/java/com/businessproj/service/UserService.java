package com.businessproj.service;

import com.businessproj.dao.SessionDAO;
import com.businessproj.dao.UserDAO;
import com.businessproj.model.Session;
import com.businessproj.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 */
@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SessionDAO sessionDAO;

    @Autowired
    private UserDAO userDAO;

    public String userFunction() {
        return "hello world";
    }

    public User findUser(HttpServletRequest request){
        if(request.getCookies() == null) { return null; }
        for(Cookie cookie : request.getCookies()) {
            if("sessionid".equals(cookie.getName())) {
                Session session = sessionDAO.findOneByIdentity(cookie.getValue());
                if(session == null) {
                    return null;
                }

                return session.getUser();
            }
        }

        return null;
    }

    public User requireUser(HttpServletRequest request){
        for(Cookie cookie : request.getCookies()) {
            if("sessionid".equals(cookie.getName())) {
                Session session = sessionDAO.findOneByIdentity(cookie.getValue());
                if(session == null) {
                    throw new RuntimeException("Invalid session");
                }

                return session.getUser();
            }
        }

        throw new RuntimeException("User unauthorized");
    }


}
