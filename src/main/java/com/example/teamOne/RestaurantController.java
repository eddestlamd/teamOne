package com.example.teamOne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RestaurantController {


    @Autowired
    DishRepository dishRepository;

    @Autowired
    ShoppingCartRepository shoppingCartRepository;


    @GetMapping("/")
    public String index(Model model){
        List<Dish> dishes = dishRepository.findAll();
        model.addAttribute("dishes",dishes);
        return"index";
    }

//    @PostMapping("/AddToCart/{id}")
//    public String addDish( @ModelAttribute ShoppingCart shoppingCart,@PathVariable Long id){
//       Dish dish = dishRepository.findById(id).get();
//       List<Dish> orders = shoppingCart.getOrder();
//       orders.add(dish);
//       shoppingCart.setOrder(orders);
//       shoppingCartRepository.save(shoppingCart);
//       dish.setShoppingCart(shoppingCart);
//       dishRepository.save(dish);
//       for(int i=0; i<orders.size();i++)
//        System.out.println(shoppingCart.getOrder().get(i).getName());
//
//       return "redirect:/";
//    }


    @PostMapping("/AddToCart1/{id}")
    public String addDish1(HttpSession session, @PathVariable Long id){
    ShoppingCart shoppingCart= (ShoppingCart) session.getAttribute("shoppingCart");
        if (shoppingCart == null) {
            shoppingCart = new ShoppingCart();
            shoppingCartRepository.save(shoppingCart);
            session.setAttribute("shoppingCart", shoppingCart);
        }
        Dish dish = dishRepository.findById(id).get();
        List<Dish> orders = shoppingCart.getOrder();
        orders.add(dish);

        shoppingCart.setOrder(orders);
        shoppingCartRepository.save(shoppingCart);
        dish.setShoppingCart(shoppingCart);
        dishRepository.save(dish);
        for(int i=0; i<orders.size();i++)
        System.out.println(shoppingCart.getOrder().get(i).getName());


        return "redirect:/";
    }


}
