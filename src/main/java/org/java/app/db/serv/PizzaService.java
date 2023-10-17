package org.java.app.db.serv;

import java.util.List;
import java.util.Optional;

import org.java.app.db.pojo.Pizza;
import org.java.app.db.repo.PizzaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {

	@Autowired
	private PizzaRepo pizzaRepo;
	
	public Pizza save(Pizza pizza) {
		return pizzaRepo.save(pizza);
	}
	
	public List<Pizza> findAll() {
		return pizzaRepo.findAll();
	}
	
	public Optional<Pizza> findById(int id) {
		return pizzaRepo.findById(id);
	}
	
	public List<Pizza> findByName(String name) {
		return pizzaRepo.findByNameContaining(name);
	}
	
	public void delete(Pizza pizza) {	
		pizzaRepo.delete(pizza);
	}
}
