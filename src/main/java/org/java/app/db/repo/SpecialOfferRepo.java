package org.java.app.db.repo;

import java.util.List;

import org.java.app.db.pojo.Pizza;
import org.java.app.db.pojo.SpecialOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialOfferRepo extends JpaRepository<SpecialOffer, Integer>{
	public List<SpecialOffer> findByPizza(Pizza pizza);
}
