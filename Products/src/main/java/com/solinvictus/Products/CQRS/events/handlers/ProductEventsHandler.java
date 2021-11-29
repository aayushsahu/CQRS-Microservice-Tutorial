package com.solinvictus.Products.CQRS.events.handlers;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
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

	@ExceptionHandler(resultType = IllegalArgumentException.class)
	public void handle(IllegalArgumentException e) throws IllegalArgumentException {
		throw e;
	}

	@ExceptionHandler(resultType = Exception.class)
	public void handle(Exception e) throws Exception {
		throw e;
	}

	@EventHandler
	public void on(ProductCreatedEvent productCreatedEvent) {
		// 1. Create object of entity
		Product product = new Product();
		// 2. Copy from productCreatedEvent(event) to entity
		BeanUtils.copyProperties(productCreatedEvent, product);
		System.out.println("Product is about to get saved");
		// 3. repository.save(entity);
		try {
			productRepo.save(product);
			 /*
			 * NOTE:  below code is just for testing purpose to check if the exception occuring in
			 * EventHandler Method is getting propagated to @CommandHandler methods' class
			 * ProductAggregate
			 */
			 
			//throw new IllegalArgumentException();
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(
					"Error thrown from @EventHandler method in " + this.getClass().getName());
		} catch (Exception e) {
			throw e;
		}
	}
}
