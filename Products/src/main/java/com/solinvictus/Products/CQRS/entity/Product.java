package com.solinvictus.Products.CQRS.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table
@Data
public class Product implements Serializable {
	
	private static final long serialVersionUID = 8734249257492505619L;

	@Id
	private String productId;
	
	@Column(unique= true)
	private String title;
	private String description;
	private int price;
	private short discountPercentage;
	private boolean available;
}
