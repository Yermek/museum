package com.businessproj.model;

import javax.persistence.*;

/**
 * Created by Kseniya
 */

@Entity
@Table(name = "OrderLine")
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "quantItem")
    private int quantItem;

    @Column(name = "priceOne")
    private float priceOne;

    @Column(name = "amountLine")
    private float amountLine;

    @ManyToOne
    @JoinColumn
    private Item item;

    @ManyToOne
    @JoinColumn
    private OrderHeader order;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantItem() {
        return quantItem;
    }

    public void setQuantItem(int quantItem) {
        this.quantItem = quantItem;
    }

    public float getPriceOne() {
        return priceOne;
    }

    public void setPriceOne(float priceOne) {
        this.priceOne = priceOne;
    }

    public float getAmountLine() {
        return amountLine;
    }

    public void setAmountLine(float amountLine) {
        this.amountLine = amountLine;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public OrderHeader getOrder() {
        return order;
    }

    public void setOrder(OrderHeader item) {
        this.order = order;
    }
}
