package com.example.teamOne;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shoppingCart")
    List<Dish> order = new ArrayList<>();

    public ShoppingCart() {
    }

    public ShoppingCart(Long id, List<Dish> order) {
        this.id = id;
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Dish> getOrder() {
        return order;
    }

    public void setOrder(List<Dish> order) {
        this.order = order;
    }
}
