package com.businessproj.dao;

import com.businessproj.model.OrderHeader;
import com.businessproj.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by Kseniya
 */
@Repository
public interface UserDAO extends JpaRepository<User, Long> {
    public User findByNameUser(String name);
    public User findByNameUserAndPassUser(String name, String pass);
}
