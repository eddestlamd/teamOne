package com.example.teamOne;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RestaurantControllerTest {

    private static final Long SHOPPING_CART_ID = 1L;
    private static final Long DISH_ID = 1L;
    private static final String DISH_NAME = "Margerita";
    private static final int DISH_PRICE = 99;

    @Autowired
    DishRepository dishRepository;
    @Autowired
    ShoppingCartRepository shoppingCartRepository;


    @Test
    void index() {
        //GIVEN
        ShoppingCart expectedShoppingCart = new ShoppingCart();
        expectedShoppingCart.setId(SHOPPING_CART_ID);
        List<Dish> expectedDishes = new ArrayList<>();
        Dish dish = new Dish();
        dish.setShoppingCart(expectedShoppingCart);
        dish.setId(DISH_ID);
        dish.setName(DISH_NAME);
        dish.setPrice(DISH_PRICE);
        expectedDishes.add(dish);

        //WHEN
        List<Dish> actualDishes = dishRepository.findAll();
        ShoppingCart actualShoppingCart = shoppingCartRepository.save(expectedShoppingCart);

        //THEN
        assertEquals(SHOPPING_CART_ID, actualShoppingCart.getId());
        assertEquals(DISH_ID, actualDishes.get(0).getId());
        assertEquals(DISH_NAME, actualDishes.get(0).getName());
        assertEquals(DISH_PRICE, actualDishes.get(0).getPrice());
    }

    @Test
    void addDish1() {
        //GIVEN
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(SHOPPING_CART_ID);
        Dish dish = dishRepository.findById(DISH_ID).get();
        List<Dish> expectedOrders = new ArrayList<>();
        expectedOrders.add(dish);
        shoppingCart.setOrder(expectedOrders);

        //WHEN
        ShoppingCart cart = shoppingCartRepository.save(shoppingCart);
        List<Dish> actualOrders = cart.getOrder();

        //THEN
        assertEquals(expectedOrders.get(0).getName(), actualOrders.get(0).getName());
        assertEquals(expectedOrders.get(0).getPrice(), actualOrders.get(0).getPrice());
        assertEquals(expectedOrders.get(0).getId(), actualOrders.get(0).getId());
    }



    @Test
    void cart() {
    }

    @Test
    void deleteDish() {
    }

    @Test
    void placeOrder() {
    }
}