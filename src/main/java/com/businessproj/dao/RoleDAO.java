package com.businessproj.dao;

import com.businessproj.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Kseniya
 */
@Repository
public interface RoleDAO extends JpaRepository<Role, Long> {}
