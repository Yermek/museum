package com.businessproj.dao;

import com.businessproj.model.OrderLine;
import com.businessproj.model.OrderHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

/**
 * Created by Kseniya
 */
@Repository
public interface OrderLineDAO extends JpaRepository<OrderLine, Long> {
    public List<OrderLine> findAllByOrder(OrderHeader order);
}
