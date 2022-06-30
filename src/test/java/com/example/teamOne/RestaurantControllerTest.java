package com.example.teamOne;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Dish expectedDish = new Dish(DISH_ID, DISH_NAME, DISH_PRICE);
        List<Dish> expectedOrders = new ArrayList<>();
        expectedOrders.add(expectedDish);
        shoppingCart.setOrder(expectedOrders);

        //WHEN
        Dish actualDish = dishRepository.findById(DISH_ID).get();
        ShoppingCart expectedShoppingCart = shoppingCartRepository.save(shoppingCart);

        //THEN
        assertEquals(expectedOrders, expectedShoppingCart.getOrder());
        assertEquals(expectedDish.getName(), actualDish.getName());
        
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