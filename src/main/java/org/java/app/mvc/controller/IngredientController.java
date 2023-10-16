package org.java.app.mvc.controller;

import java.util.List;

import org.java.app.db.pojo.Ingredient;
import org.java.app.db.pojo.Pizza;
import org.java.app.db.serv.IngredientService;
import org.java.app.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

	@Autowired
	private IngredientService ingredientService;
	
	@Autowired
	private PizzaService pizzaService;
	
	@GetMapping
	public String getIndex(Model model) {
		List<Ingredient> ingredients = ingredientService.findAll();
		model.addAttribute("ingredients", ingredients);
		
		return "ingredient-index";
	}
	
	@GetMapping("/create")
	public String createIngredient(Model model) {
		
		model.addAttribute("ingredient", new Ingredient());
		
		return "ingredient-create";
	}
	
	@PostMapping("/create")
	public String storeIngredient(
			@Valid @ModelAttribute Ingredient ingredient,
			BindingResult bindingResult,
			Model model
			) {
		
		if (bindingResult.hasErrors()) {
			System.out.println("Error:");
			bindingResult.getAllErrors().forEach(System.out::println);
			
			return "ingredient-create";
		} else 
			System.out.println("No error");
		
		try {
			ingredientService.save(ingredient);
		} catch (Exception e) {			
			return "ingredient-create";
		}
		
		return "redirect:/ingredients";
	}
	
	@PostMapping("/delete/{id}")
	public String deleteIngredient(@PathVariable int id) {
		Ingredient ingredient = ingredientService.findById(id);
		
		for (Pizza pizza : ingredient.getPizzas()) {
            pizza.getIngredients().remove(ingredient);
            pizzaService.save(pizza);
        }
		
		ingredientService.delete(ingredient);
		
		return "redirect:/ingredients";
	}
	
}
