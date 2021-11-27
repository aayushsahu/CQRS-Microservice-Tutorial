package com.solinvictus.Products.Models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReadProductModel {
	
	private String title;
	private String description;
	private int price;
	private short discountPercentage;
	private boolean available;
}