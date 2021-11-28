package com.solinvictus.Products.CQRS.events.handlers;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.solinvictus.Products.CQRS.entity.Product;
import com.solinvictus.Products.CQRS.events.ProductCreatedEvent;
import com.solinvictus.Products.Repositories.ProductRepo;

@Service
@ProcessingGroup("product-group")
public class ProductEventsHandler {

	private final ProductRepo productRepo;
	
	public ProductEventsHandler(ProductRepo productRepo) {
		this.productRepo = productRepo;
	}
	
	@EventHandler
	public void on(ProductCreatedEvent productCreatedEvent) {
		//1. Create object of entity
		Product product = new Product();
		//2. Copy from productCreatedEvent(event) to entity 
		BeanUtils.copyProperties(productCreatedEvent, product);
		System.out.println("Product is about to get saved");
		//3. repository.save(entity);
		productRepo.save(product);
	}	
	
}
