package com.example.teamOne;

import javax.persistence.*;

@Entity
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    int price;
    String url;
    @ManyToOne
    ShoppingCart shoppingcart;

    public Dish() {
    }

    public Dish(Long id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingcart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingcart = shoppingCart;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
