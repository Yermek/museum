package com.businessproj.dao;

import com.businessproj.model.Item;
import com.businessproj.model.GroupItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

/**
 * Created by Kseniya
 */
@Repository
public interface ItemDAO extends JpaRepository<Item, Long> {
    public List<Item> findAllByGroupItem(GroupItem groupItem);
}
