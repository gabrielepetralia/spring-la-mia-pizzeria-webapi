package org.java.app.db.serv;

import java.util.List;

import org.java.app.db.pojo.Pizza;
import org.java.app.db.pojo.SpecialOffer;
import org.java.app.db.repo.SpecialOfferRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecialOfferService {
	
	@Autowired
	private SpecialOfferRepo specialOfferRepo;
	
	public void save(SpecialOffer specialOffer) {
		specialOfferRepo.save(specialOffer);
	}
	
	public List<SpecialOffer> findAll() {
		return specialOfferRepo.findAll();
	}
	
	public SpecialOffer findById(int id) {
		return specialOfferRepo.findById(id).get();
	}
	
	public void delete(SpecialOffer specialOffer) {
		specialOfferRepo.delete(specialOffer);
	}
	
	public List<SpecialOffer> findByPizza(Pizza pizza) {
		return specialOfferRepo.findByPizza(pizza);
	}
}
