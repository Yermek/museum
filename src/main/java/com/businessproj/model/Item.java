package com.businessproj.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Kseniya
 */

@Entity
@Table(name = "Item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nameItem")
    private String nameItem;

    @Column(name = "modelItem")
    private String modelItem;

    @Column(name = "characterItem")
    private String characterItem;

    @Column(name = "priceItem")
    private float priceItem;

    @Column(name = "photo")
    private String photo;

    @ManyToOne
    @JoinColumn
    private GroupItem groupItem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameItem() {
        return nameItem;
    }

    public void setNameItem(String nameItem) {
        this.nameItem = nameItem;
    }

    public String getModelItem() {
        return modelItem;
    }

    public void setModelItem(String modelItem) {
        this.modelItem = modelItem;
    }

    public String getCharacterItem() {
        return characterItem;
    }

    public void setCharacterItem(String characterItem) {
        this.characterItem = characterItem;
    }

    public float getPriceItem() {
        return priceItem;
    }

    public void setPriceItem(float priceItem) {
        this.priceItem = priceItem;
    }

    public GroupItem getGroupItem() {
        return groupItem;
    }

    public void setGroupItem(GroupItem groupItem) {
        this.groupItem = groupItem;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
