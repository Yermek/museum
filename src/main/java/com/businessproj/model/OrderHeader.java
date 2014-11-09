package com.businessproj.model;

import javax.persistence.*;
import java.util.List;
import java.util.Date;

/**
 * Created by Kseniya
 */

@Entity
@Table(name = "OrderHeader")
public class OrderHeader {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "dateOrder")
    private Date dateOrder;

    @Column(name = "numbDocOrder")
    private String numbDocOrder;

    @Column(name = "descOrder")
    private String descOrder;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long idOrder) {
        this.id = idOrder;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    public String getNumbDocOrder() {
        return numbDocOrder;
    }

    public void setNumbDocOrder(String numbDocOrder) {
        this.numbDocOrder = numbDocOrder;
    }

    public String getDescOrder() {
        return descOrder;
    }

    public void setDescOrder(String descOrder) {
        this.descOrder = descOrder;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
