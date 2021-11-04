package com.solinvictus.Products.Controller;

import java.util.List;
import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.solinvictus.Products.CQRS.commands.CreateProductCommand;
import com.solinvictus.Products.Entity.Product;
import com.solinvictus.Products.Models.CreateProductModel;
import com.solinvictus.Products.Service.ProductsServices;

@RestController
public class ProductsController {
	
	@Autowired
	private ProductsServices prodServices;
	
	@Autowired
	CommandGateway commandGateway;
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProducts(){
		
		List<Product> listOfProducts = prodServices.getProducts();
		return new ResponseEntity<>(listOfProducts, HttpStatus.OK);		
	}
	
	@GetMapping("/product?{id}")
	public ResponseEntity<Product> getProduct(@PathVariable(required= true) String id){
		
		return new ResponseEntity<>(prodServices.getProduct(Long.valueOf(id)), HttpStatus.OK);
	}
	
	@PostMapping("/saveProduct")
	public ResponseEntity<Void> productCreator(@RequestBody CreateProductModel createProductModel){
		
		CreateProductCommand createProductCommand = CreateProductCommand.builder().
				productId(UUID.randomUUID().toString()).
				title(createProductModel.getTitle()).
				description(createProductModel.getDescription()).
				price(createProductModel.getPrice()).
				discountPercentage(createProductModel.getDiscountPercentage()).
				available(createProductModel.isAvailable()).build();
		try {
			commandGateway.sendAndWait(createProductCommand);
//			commandGateway.send(createProductCommand);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
