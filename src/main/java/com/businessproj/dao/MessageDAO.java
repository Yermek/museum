package com.businessproj.dao;

import com.businessproj.model.Message;
import com.businessproj.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

/**
 * Created by Kseniya
 */
@Repository
public interface MessageDAO extends JpaRepository<Message, Long> {
    public List<Message> findAllByFromUser(User fromUser);
    public List<Message> findAllByToUser(User toUser);
}
