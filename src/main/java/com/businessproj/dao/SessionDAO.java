package com.businessproj.dao;

import com.businessproj.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by
 */
@Repository
public interface SessionDAO  extends JpaRepository<Session, Long> {
    public Session findOneByIdentity(String identity);
}
