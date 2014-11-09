package com.businessproj.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Kseniya
 */

@Entity
@Table(name = "Role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, name = "nameRole")
    private String nameRole;

    public Long getId() {
        return id;
    }

    public void setId(Long idRole) {
        this.id = idRole;
    }

    public String getNameRole() {
        return nameRole;
    }

    public void setNameRole(String nameRole) {
        this.nameRole = nameRole;
    }

}
