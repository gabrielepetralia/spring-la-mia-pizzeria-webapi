package org.java.app.mvc.controller;

import java.util.List;

import org.java.app.db.pojo.Ingredient;
import org.java.app.db.pojo.Pizza;
import org.java.app.db.pojo.SpecialOffer;
import org.java.app.db.serv.IngredientService;
import org.java.app.db.serv.PizzaService;
import org.java.app.db.serv.SpecialOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private IngredientService ingredientService;
	
	@Autowired
	private SpecialOfferService specialOfferService;
	
	@GetMapping
	public String getIndex(Model model, @RequestParam(required = false) String name) {
		List<Pizza> pizzas = name == null 
					? pizzaService.findAll()
					: pizzaService.findByName(name);
		
		model.addAttribute("pizzas", pizzas);
		model.addAttribute("name", name);
		
		return "pizza-index";
	}
	
	@GetMapping("/{id}")
	public String getShow(@PathVariable int id, Model model) {
		
		Pizza pizza = pizzaService.findById(id);
		model.addAttribute("pizza", pizza);
		
		return "pizza-show";
	}
	
	@GetMapping("/create")
	public String getCreateForm(Model model) {
		List<Ingredient> ingredients = ingredientService.findAll();
		
		model.addAttribute("pizza", new Pizza());
		model.addAttribute("ingredients", ingredients);
		
		return "pizza-create-edit";
	}
	
	@PostMapping("/create")
	public String storePizza(
			@Valid @ModelAttribute Pizza pizza,
			BindingResult bindingResult,
			Model model
			) {
		
		List<Ingredient> ingredients = ingredientService.findAll();
		model.addAttribute("ingredients", ingredients);
		
		if (bindingResult.hasErrors()) {
			System.out.println("Error:");
			bindingResult.getAllErrors().forEach(System.out::println);
			
			return "pizza-create-edit";
		} else 
			System.out.println("No error");
		
		try {
			pizzaService.save(pizza);
		} catch (Exception e) {
			
			// CONSTRAIN VALIDATION (unique)
			System.out.println("Errore constrain: " + e.getClass().getSimpleName());
			
			model.addAttribute("name_unique", "Nome già presente nel menù");
			
			return "pizza-create-edit";
		}
		
		return "redirect:/pizzas";
	}
	
	@GetMapping("/update/{id}")
	public String getEditForm(
			@PathVariable int id,
			Model model
		) {
		List<Ingredient> ingredients = ingredientService.findAll();
		Pizza pizza = pizzaService.findById(id);
		
		model.addAttribute("pizza", pizza);
		model.addAttribute("ingredients", ingredients);
		
		return "pizza-create-edit";
	}
	
	@PostMapping("/update/{id}")
	public String updatePizza(
			@Valid @ModelAttribute Pizza pizza,
			BindingResult bindingResult,
			Model model
			) {
		
		if (bindingResult.hasErrors()) {
			System.out.println("Error:");
			bindingResult.getAllErrors().forEach(System.out::println);
			
			return "pizza-create";
		} else 
			System.out.println("No error");
		
		try {
			pizzaService.save(pizza);
		} catch (Exception e) {
			return "pizza-create-edit";
		}
		
		return "redirect:/pizzas/" + pizza.getId();
	}
	
	@PostMapping("/delete/{id}")
	public String deletePizza(@PathVariable int id) {
		Pizza pizza = pizzaService.findById(id);
		
		List<SpecialOffer> specialOffers = specialOfferService.findByPizza(pizza);
	    for (SpecialOffer specialOffer : specialOffers) {
	        specialOfferService.delete(specialOffer);
	    }
	    
	    pizzaService.delete(pizza);
		
		return "redirect:/pizzas";
	}
	
	
	// SpecialOffer
	@GetMapping("/special-offer/{pizza_id}")
	public String createSpecialOffer(
			@PathVariable("pizza_id") int id,
			Model model
			) {
		
		Pizza pizza = pizzaService.findById(id);
		SpecialOffer specialOffer = new SpecialOffer();
		
		model.addAttribute("pizza", pizza);		
		model.addAttribute("specialOffer", specialOffer);
		model.addAttribute("pizzaId", id);
		
		return "specialOffer-create-edit";
	}
	
	@PostMapping("/special-offer/{pizza_id}")
	public String storeSpecialOffer(
			@Valid @ModelAttribute SpecialOffer specialOffer,
			BindingResult bindingResult,			
			@PathVariable("pizza_id") int id,
			Model model
		) {
		
		Pizza pizza = pizzaService.findById(id);
		
		specialOffer.setPizza(pizza);
		
		specialOfferService.save(specialOffer);
		
		return "redirect:/pizzas/" + id;
	}
	
	@GetMapping("/special-offer/update/{specialOffer_id}")
	public String editSpecialOffer(
			@PathVariable("specialOffer_id") int id,
			Model model
		) {
		
		SpecialOffer specialOffer = specialOfferService.findById(id);
		int pizzaId = specialOffer.getPizza().getId();
		
		model.addAttribute("specialOffer", specialOffer);
		model.addAttribute("pizzaId", pizzaId);
		
		return "specialOffer-create-edit";
	}
	
	@PostMapping("/special-offer/update/{specialOffer_id}")
	public String updateSpecialOffer(
			@Valid @ModelAttribute SpecialOffer specialOffer,
			BindingResult bindingResult,
			Model model
		) {
		
		specialOfferService.save(specialOffer);
		
		Pizza specialOfferPizza = specialOffer.getPizza();
		
		return "redirect:/pizzas/" + specialOfferPizza.getId();
	}
	
	@PostMapping("/special-offer/delete/{specialOffer_id}")
	public String deleteBorrowing(
			@PathVariable("specialOffer_id") int id
		) {
		
		SpecialOffer specialOffer = specialOfferService.findById(id);
		Pizza pizza = specialOffer.getPizza();
		specialOfferService.delete(specialOffer);
		
		return "redirect:/pizzas/" + pizza.getId();
	}
	
}