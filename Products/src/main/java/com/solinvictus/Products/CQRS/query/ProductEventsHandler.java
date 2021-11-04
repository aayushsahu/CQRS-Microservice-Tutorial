package com.solinvictus.Products.CQRS.query;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.solinvictus.Products.CQRS.events.ProductCreatedEvent;
import com.solinvictus.Products.Entity.Product;
import com.solinvictus.Products.Repositories.ProductRepo;

@Component
public class ProductEventsHandler {

	private final ProductRepo productRepo;

	public ProductEventsHandler(ProductRepo productRepo) {
		this.productRepo = productRepo;
	}
	
	@EventHandler
	public void on(ProductCreatedEvent productEventCreated) {
		//1. Create object of entity
		Product product = new Product();
		//2. Copy from productEventCreated(event) to entity 
		BeanUtils.copyProperties(productEventCreated, product);
		//3. repository.save(entity);
		productRepo.save(product);
	}	
	
}
