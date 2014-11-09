package com.businessproj.dao;

import com.businessproj.model.CartLine;
import com.businessproj.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

/**
 * Created by Kseniya
 */
@Repository
public interface CartLineDAO extends JpaRepository<CartLine, Long> {
    public List<CartLine> findAllByCart(Cart cart);
}
