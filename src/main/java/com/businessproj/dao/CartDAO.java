package com.businessproj.dao;

import com.businessproj.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Kseniya
 */
@Repository
public interface CartDAO extends JpaRepository<Cart, Long> {
    public Cart findByIpAddr(String ipAddr);
}
