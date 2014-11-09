package com.businessproj.model;

        import javax.persistence.*;
        import java.util.List;

/**
 * Created by Kseniya
 */

@Entity
@Table(name = "CartLine")
public class CartLine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, name = "quantityItem")
    private int quantityItem;

    @Column(unique = true, name = "priceOneItem")
    private float priceOneItem;

    @Column(unique = true, name = "amountCartLine")
    private float amountCartLine;

    @ManyToOne
    @JoinColumn
    private Item item;

    @ManyToOne
    @JoinColumn
    private Cart cart;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantityItem() {
        return quantityItem;
    }

    public void setQuantityItem(int quantityItem) {
        this.quantityItem = quantityItem;
    }

    public float getPriceOneItem() {
        return priceOneItem;
    }

    public void setPriceOneItem(float priceOneItem) {
        this.priceOneItem = priceOneItem;
    }

    public float getAmountCartLine() {
        return amountCartLine;
    }

    public void setAmountCartLine(float amountCartLine) {
        this.amountCartLine = amountCartLine;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
