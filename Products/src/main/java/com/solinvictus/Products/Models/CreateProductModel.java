package com.solinvictus.Products.Models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class CreateProductModel {
	private String title;
	private String description;
	private int price;
	private short discountPercentage;
	private boolean available;
	
}
