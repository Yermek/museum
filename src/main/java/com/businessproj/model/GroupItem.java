package com.businessproj.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Kseniya
 */

@Entity
@Table(name = "GroupItem")
public class GroupItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, name = "nameGroupItem")
    private String nameGroupItem;

    public Long getId() {
        return id;
    }

    public void setId(Long idGroupItem) {
        this.id = idGroupItem;
    }

    public String getNameGroupItem() {
        return nameGroupItem;
    }

    public void setNameGroupItem(String nameGroupItem) {
        this.nameGroupItem = nameGroupItem;
    }
}
