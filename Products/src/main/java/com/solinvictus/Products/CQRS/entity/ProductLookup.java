package com.solinvictus.Products.CQRS.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Table(name= "productLookup")
@NoArgsConstructor
@AllArgsConstructor	
public class ProductLookup implements Serializable {
	
	private static final long serialVersionUID = -2483825684663747028L;
	
	@Id
	private String productId;
	
	@Column(unique= true)
	private String title;
	
	

}
