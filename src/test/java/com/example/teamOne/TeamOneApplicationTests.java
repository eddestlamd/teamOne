package com.example.teamOne;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TeamOneApplicationTests {

	@Autowired
	MockMvc mvc;

	@Test
	void contextLoads() {
	}

	@Test
	public void testIndex() throws Exception {
		mvc.perform(
						MockMvcRequestBuilders.get("/")
				)
				.andExpect(status().is2xxSuccessful())
				.andExpect(MockMvcResultMatchers.content().string(containsString("Ristorante Pizzeria")))
				.andExpect(MockMvcResultMatchers.content().string(not(containsString("Shopping Cart"))));
	}

	@Test
	public void testCart() throws Exception {
		mvc.perform(
						MockMvcRequestBuilders.get("/cart")
				)
				.andExpect(status().is2xxSuccessful())
				.andExpect(MockMvcResultMatchers.content().string(containsString("Shopping Cart")))
				.andExpect(MockMvcResultMatchers.content().string(not(containsString("Ristorante Pizzeria"))));
	}

}
