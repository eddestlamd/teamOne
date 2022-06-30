package com.example.teamOne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class RestaurantController {


    @Autowired
    DishRepository dishRepository;

    @Autowired
    ShoppingCartRepository shoppingCartRepository;


    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        List<Dish> dishes = dishRepository.findAll();
        model.addAttribute("dishes", dishes);
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
        if (shoppingCart == null) {
            shoppingCart = new ShoppingCart();
            shoppingCartRepository.save(shoppingCart);
            session.setAttribute("shoppingCart", shoppingCart);
        }

        return "index";
    }


    @PostMapping("/AddToCart1/{id}")
    public String addDish1(@PathVariable Long id, HttpSession session) {
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
        Dish dish = dishRepository.findById(id).get();
        List<Dish> orders = shoppingCart.getOrder();
        orders.add(dish);

        shoppingCart.setOrder(orders);
        shoppingCartRepository.save(shoppingCart);
        dish.setShoppingCart(shoppingCart);
        dishRepository.save(dish);


        return "redirect:/";
    }


    @GetMapping("/cart/")
    public String cart(Model model, HttpSession session) {
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
        List<Dish> dishes = shoppingCart.getOrder();
        model.addAttribute("dishes", dishes);
        return "cart";
    }

    @GetMapping("/DeleteFromCart/{index}")
    public String deleteDish(Model model, @PathVariable int index, HttpSession session) {
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
        List<Dish> dishes = shoppingCart.getOrder();
        dishes.remove(index);
        model.addAttribute("dishes", dishes);
        return "cart";
    }


    @GetMapping("/order")
    public String placeOrder(HttpSession session, HttpServletResponse res){
        session.removeAttribute("shoppingCart"); // this would be an ok solution
        session.invalidate(); // you could also invalidate the whole session, a new session will be created the next request
        Cookie cookie = new Cookie("JSESSIONID", "");
        cookie.setMaxAge(0);
        res.addCookie(cookie);
        return "done";
    }



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
