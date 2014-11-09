package com.businessproj.dao;

import com.businessproj.model.Payment;
import com.businessproj.model.OrderHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

/**
 * Created by Kseniya
 */
@Repository
public interface PaymentDAO extends JpaRepository<Payment, Long> {
    public List<Payment> findAllByOrder(OrderHeader order);
}
