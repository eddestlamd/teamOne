package com.example.teamOne;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Dish {
    @Id
    Long id;
    String dish;
}
