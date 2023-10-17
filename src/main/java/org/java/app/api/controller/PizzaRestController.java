package org.java.app.api.controller;

import java.util.List;
import java.util.Optional;

import org.java.app.api.dto.PizzaDTO;
import org.java.app.db.pojo.Pizza;
import org.java.app.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/pizzas")
public class PizzaRestController {
	
	@Autowired
	private PizzaService pizzaService;
	
	@GetMapping
	public ResponseEntity<List<Pizza>> getAll(@RequestParam(required = false, name = "name") String query) {
		
		List<Pizza> pizzas = null;
		
		if (query == null) 
			pizzas = pizzaService.findAll();
		else 
			pizzas = pizzaService.findByName(query);
		
		return new ResponseEntity<>(pizzas, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Pizza> getById(@PathVariable int id) {
		
		Optional<Pizza> pizza = pizzaService.findById(id);
		
		if (pizza.isEmpty()) {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(pizza.get(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Pizza> createPizza(
			@RequestBody PizzaDTO pizzaDto
		) {
		
		Pizza pizza = new Pizza(pizzaDto);
		pizza = pizzaService.save(pizza);
		
		return new ResponseEntity<>(pizza, HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Pizza> updatePizza(
			@PathVariable int id,
			@RequestBody PizzaDTO pizzaDto
		) {
		
		Optional<Pizza> optPizza = pizzaService.findById(id);
		
		if (optPizza.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		Pizza pizza = optPizza.get();
		pizza.fillFromDto(pizzaDto);
		
		pizza = pizzaService.save(pizza);
		
		return new ResponseEntity<>(pizza, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Boolean> deletePizza(
			@PathVariable int id
		) {
		
		Optional<Pizza> optPizza = pizzaService.findById(id);
		
		if (optPizza.isEmpty())
			return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
		
		Pizza pizza = optPizza.get();
		
		pizzaService.delete(pizza);
		
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

}
