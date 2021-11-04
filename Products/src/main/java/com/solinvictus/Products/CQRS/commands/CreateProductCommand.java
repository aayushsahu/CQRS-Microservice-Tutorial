package com.solinvictus.Products.CQRS.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateProductCommand {
	
	@TargetAggregateIdentifier
	private String productId;
	private String title;
	private String description;
	private int price;
	private short discountPercentage;
	private boolean available;
}
