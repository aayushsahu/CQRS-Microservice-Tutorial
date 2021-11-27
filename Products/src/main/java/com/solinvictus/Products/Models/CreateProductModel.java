 package com.solinvictus.Products.Models;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.checkerframework.checker.units.qual.min;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
public class CreateProductModel {
	
	@NotBlank(message = "Product title cannot be blank")
	@NotEmpty(message = "Product title cannot be empty")
	@NotNull(message = "Product title cannot be null")
	private String title;
	
	@NotBlank(message = "Product description cannot be blank")
	@NotEmpty(message = "Product description cannot be empty")
	@NotNull(message = "Product description cannot be null")
	private String description;
	
	@Min(value = 1,  message = "Price must be minimum equal to 1")
	private int price;
	
	@Max(value = 99, message = "Discount percentage can not be greater then 99")
	private short discountPercentage;
	
	@AssertTrue
	private boolean available;
	
}