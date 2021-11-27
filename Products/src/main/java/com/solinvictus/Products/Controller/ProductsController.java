package com.solinvictus.Products.Controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.solinvictus.Products.CQRS.commands.CreateProductCommand;
import com.solinvictus.Products.CQRS.entity.Product;
import com.solinvictus.Products.CQRS.query.FindProductsQuery;
import com.solinvictus.Products.Models.CreateProductModel;
import com.solinvictus.Products.Models.ReadProductModel;
import com.solinvictus.Products.Service.ProductsServices;

@RestController
public class ProductsController {
	
	@Autowired
	private ProductsServices prodServices;
	
	@Autowired
	CommandGateway commandGateway;
	
	@Autowired
	QueryGateway queryGateway;
	
	
	@GetMapping("/products")
	public ResponseEntity<List<ReadProductModel>> getAllProducts(){
		
		FindProductsQuery findProductsQuery = new FindProductsQuery();
		List<ReadProductModel> products = queryGateway.query(findProductsQuery, 
				ResponseTypes.multipleInstancesOf(ReadProductModel.class)).join();
		return new ResponseEntity<>(products, HttpStatus.OK);		
	}
	
	@GetMapping("/product?{id}")
	public ResponseEntity<Product> getProduct(@PathVariable(required= true) String id){
		
		return new ResponseEntity<>(prodServices.getProduct(id), HttpStatus.OK);
	}
	
	
	//Command Controller
	@PostMapping("/saveProduct")
	public String productCreator(@Valid @RequestBody CreateProductModel createProductModel){
		
		CreateProductCommand createProductCommand = CreateProductCommand.builder().
				productId(UUID.randomUUID().toString()).
				title(createProductModel.getTitle()).
				description(createProductModel.getDescription()).
				price(createProductModel.getPrice()).
				discountPercentage(createProductModel.getDiscountPercentage()).
				available(createProductModel.isAvailable()).build();
		String returnValue;
		try {
			returnValue = commandGateway.sendAndWait(createProductCommand);
			//			commandGateway.send(createProductCommand);
		}catch(Exception e) {
			e.printStackTrace();
			returnValue = e.getLocalizedMessage();
		}
		return returnValue;
	}
	
	
	
}
