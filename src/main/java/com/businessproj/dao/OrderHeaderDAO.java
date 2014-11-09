package com.businessproj.dao;

import com.businessproj.model.OrderHeader;
import com.businessproj.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;
/**
 * Created by Kseniya
 */
@Repository
public interface OrderHeaderDAO extends JpaRepository<OrderHeader, Long> {
    public List<OrderHeader> findByUser(User user);
}
