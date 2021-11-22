package com.solinvictus.Products.CQRS.commands;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import com.solinvictus.Products.CQRS.events.ProductCreatedEvent;

@Aggregate
public class ProductAggregate {
	
	@AggregateIdentifier
	private String productId;
	private String title;
	private String description;
	private int price;
	private short discountPercentage;
	private boolean available;
	
	public ProductAggregate() {
	}
	
	@CommandHandler
	public ProductAggregate(CreateProductCommand createProductCommand) {
		//ADD SOME MODEL DATA VALIDATION AND EXCEPTIONS BASED ON VALIDATION
		
		
		//
		ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent();
		BeanUtils.copyProperties(createProductCommand, productCreatedEvent);
		AggregateLifecycle.apply(productCreatedEvent);
	}
	
	@EventSourcingHandler
	public void on(ProductCreatedEvent productCreatedEvent) {
		this.productId = productCreatedEvent.getProductId();
		this.title =  productCreatedEvent.getTitle();
		this.description = productCreatedEvent.getDescription();
		this.price = productCreatedEvent.getPrice();
		this.discountPercentage = productCreatedEvent.getDiscountPercentage();
		this.available = productCreatedEvent.isAvailable();
	}
}