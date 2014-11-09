package com.businessproj.dao;

import com.businessproj.model.GroupItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Kseniya
 */
@Repository
public interface GroupItemDAO extends JpaRepository<GroupItem, Long> {}
