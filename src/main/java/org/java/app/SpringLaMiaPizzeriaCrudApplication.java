package org.java.app;

import java.time.LocalDate;

import org.java.app.db.pojo.Ingredient;
import org.java.app.db.pojo.Pizza;
import org.java.app.db.pojo.SpecialOffer;
import org.java.app.db.serv.IngredientService;
import org.java.app.db.serv.PizzaService;
import org.java.app.db.serv.SpecialOfferService;
import org.java.app.mvc.auth.pojo.Role;
import org.java.app.mvc.auth.pojo.User;
import org.java.app.mvc.auth.service.RoleService;
import org.java.app.mvc.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringLaMiaPizzeriaCrudApplication implements CommandLineRunner{
	
	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private SpecialOfferService specialOfferService;
	
	@Autowired
	private IngredientService ingredientService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaCrudApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		Ingredient ingredient1 = new Ingredient("Pomodoro", false);
		Ingredient ingredient2 = new Ingredient("Mozzarella", true);
		Ingredient ingredient3 = new Ingredient("Patatine", true);
		Ingredient ingredient4 = new Ingredient("Wurstel", true);
		Ingredient ingredient5 = new Ingredient("Salame Piccante", true);
		Ingredient ingredient6 = new Ingredient("Cipolla", false);
		Ingredient ingredient7 = new Ingredient("Tonno", true);
		Ingredient ingredient8 = new Ingredient("Ricotta", false);
		Ingredient ingredient9 = new Ingredient("Melanzane", false);
		
		ingredientService.save(ingredient1);
		ingredientService.save(ingredient2);
		ingredientService.save(ingredient3);
		ingredientService.save(ingredient4);
		ingredientService.save(ingredient5);
		ingredientService.save(ingredient6);
		ingredientService.save(ingredient7);
		ingredientService.save(ingredient8);
		ingredientService.save(ingredient9);	
		
		Pizza pizza1 = new Pizza(
				"Margherita", 
				"pizza con pomodoro e mozzarella", 
				"https://www.unmondodisapori.it/wp-content/uploads/2017/10/margherita.jpg", 
				5.50,
				ingredient1,ingredient2
			);
		Pizza pizza2 = new Pizza(
				"Tedesca", 
				"pizza con wurstel e patatine", 
				"https://www.scattidigusto.it/wp-content/uploads/2015/11/pizza-wurstel-patatine-pomodoro.jpg", 
				6.50,
				ingredient1,ingredient2,ingredient3,ingredient4
			);
		Pizza pizza3 = new Pizza(
				"Norma", 
				"pizza con melanzane e ricotta", 
				"https://cdn.cook.stbm.it/thumbnails/ricette/144/144902/hd750x421.jpg", 
				7.50,
				ingredient1,ingredient2,ingredient8,ingredient9
			);
		Pizza pizza4 = new Pizza(
				"Tonnara", 
				"pizza con tonno e cipolla",
				"https://www.gustissimo.it/articoli/ricette/pizze/pizza-tonno-e-cipolla.jpg", 
				7.00,
				ingredient1,ingredient2,ingredient6,ingredient7
			);
		Pizza pizza5 = new Pizza(
				"Diavola", 
				"pizza con salame piccante",
				"https://www.saporidellamurgia.com/prodotti/0302628/pizza_calabrese.jpg", 
				7.00,
				ingredient1,ingredient2,ingredient5
			);
		
		pizzaService.save(pizza1);
		pizzaService.save(pizza2);
		pizzaService.save(pizza3);
		pizzaService.save(pizza4);
		pizzaService.save(pizza5);
		
		SpecialOffer specialOffer1 = new SpecialOffer(
				LocalDate.now(), 
				LocalDate.now(), 
				"Offerta 1", 
				pizza1);
		SpecialOffer specialOffer2 = new SpecialOffer(
				LocalDate.now(), 
				LocalDate.now(), 
				"Offerta 2", 
				pizza2);
		SpecialOffer specialOffer3 = new SpecialOffer(
				LocalDate.now(), 
				LocalDate.now(), 
				"Offerta 3", 
				pizza3);
		SpecialOffer specialOffer4 = new SpecialOffer(
				LocalDate.now(), 
				LocalDate.now(), 
				"Offerta 4", 
				pizza4);
		SpecialOffer specialOffer5 = new SpecialOffer(
				LocalDate.now(), 
				LocalDate.now(), 
				"Offerta 5",
				pizza4);
		
		specialOfferService.save(specialOffer1);
		specialOfferService.save(specialOffer2);
		specialOfferService.save(specialOffer3);
		specialOfferService.save(specialOffer4);
		specialOfferService.save(specialOffer5);
		
		Role admin = new Role("admin");
		Role user = new Role("user");
		
		roleService.save(admin);
		roleService.save(user);
		
		final String pwdAdmin = new BCryptPasswordEncoder().encode("pwd");
		final String pwdUser = new BCryptPasswordEncoder().encode("pwd");
		
		User guybrushAdmin = new User("gabrieleAdmin", pwdAdmin, admin, user);
		User guybrushUser = new User("gabrieleUser", pwdUser, user);
		
		userService.save(guybrushAdmin);
		userService.save(guybrushUser);
		
		System.out.println("Insert OK!");
	}

}
