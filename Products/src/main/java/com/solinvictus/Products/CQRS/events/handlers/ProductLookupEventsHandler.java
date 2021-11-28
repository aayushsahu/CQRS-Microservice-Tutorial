package com.solinvictus.Products.CQRS.events.handlers;


import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

import com.solinvictus.Products.CQRS.entity.ProductLookup;
import com.solinvictus.Products.CQRS.events.ProductCreatedEvent;
import com.solinvictus.Products.Repositories.ProductLookupRepo;

@Service
@ProcessingGroup("product-group")
public class ProductLookupEventsHandler {

	private final ProductLookupRepo productLookupRepo;

	public ProductLookupEventsHandler(ProductLookupRepo productLookupRepo) {
		this.productLookupRepo = productLookupRepo;
	}
	
	@EventHandler
	public void on(ProductCreatedEvent productCreatedEvent) {
		
		ProductLookup productLookup = new ProductLookup(productCreatedEvent.getProductId(), 
				productCreatedEvent.getTitle());
		
		//BeanUtils.copyProperties(productCreatedEvent, productLookup);
		System.out.println("Unique Product encountered. Saving to Lookup...");
		productLookupRepo.save(productLookup);
	}
	
}
