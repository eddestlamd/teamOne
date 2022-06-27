package com.example.teamOne;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class Restaurant {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id;
        String restaurantName;
        String country;
        @OneToMany
        ArrayList<Dish> menu;


}
