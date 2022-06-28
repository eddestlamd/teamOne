package com.example.teamOne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RestaurantController {

    @Autowired
    DishRepository dishRepository;


    @GetMapping("/")
    public String index(Model model){
        List<Dish> dish = dishRepository.findAll();


        return"index";
    }


}
