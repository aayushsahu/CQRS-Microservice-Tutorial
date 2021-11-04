package com.solinvictus.Products.CQRS.events;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductCreatedEvent {

	private String productId;
	private String title;
	private String description;
	private int price;
	private short discountPercentage;
	private boolean available;
}