package com.businessproj.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Kseniya
 */

@Entity
@Table(name = "Payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "datePayment")
    private Date datePayment;

    @Column(name = "amountPayment")
    private float amountPayment;

    @Column(unique = true, name = "numbDocPayment")
    private String numbDocPayment;

    @ManyToOne
    @JoinColumn
    private OrderHeader order;

    public Long getId() {
        return id;
    }

    public void setId(Long idPayment) {
        this.id = idPayment;
    }

    public Date getDatePayment() {
        return datePayment;
    }

    public void setDatePayment(Date datePayment) {
        this.datePayment = datePayment;
    }

    public float getAmountPayment() {
        return amountPayment;
    }

    public void setAmountPayment(float amountPayment) {
        this.amountPayment = amountPayment;
    }

    public String getNumbDocPayment() {
        return numbDocPayment;
    }

    public void setNumbDocPayment(String numbDocPayment) {
        this.numbDocPayment = numbDocPayment;
    }

    public OrderHeader getOrderHeader() {
        return order;
    }

    public void setOrderHeader(OrderHeader order) {
        this.order = order;
    }
}
