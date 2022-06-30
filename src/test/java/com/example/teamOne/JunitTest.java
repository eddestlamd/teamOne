package com.example.teamOne;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.Optional;

@SpringBootTest
public class JunitTest {

    @Autowired
  DishRepository dishRepository;



    @Test
    public void findByIdOneShouldReturnMargerita() {
        Optional<Dish> dish = dishRepository.findById(1L);
        Assertions.assertEquals("Margerita", dish.get().getName());

    }

    @Test
    public void testSaveToRepositoryMethod(){
        Dish dish = new Dish(null,"Pizza",500,1,"x");
        dishRepository.save(dish);

        Assertions.assertEquals("Pizza",dish.getName());

        Assertions.assertTrue(dishRepository.findById(dish.getId()).isPresent(), String.valueOf(true));

        dishRepository.delete(dish);

        Assertions.assertFalse(dishRepository.findById(dish.getId()).isPresent(), String.valueOf(false));

    }
}
