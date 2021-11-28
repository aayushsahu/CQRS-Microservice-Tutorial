package com.solinvictus.Products.CQRS.events.handlers;

import java.util.ArrayList;
import java.util.List;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.solinvictus.Products.CQRS.entity.Product;
import com.solinvictus.Products.CQRS.query.FindProductsQuery;
import com.solinvictus.Products.Models.ReadProductModel;
import com.solinvictus.Products.Repositories.ProductRepo;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class ProductsQueryHandler {
	
	private final ProductRepo productRepo;
	
	@QueryHandler
	public List<ReadProductModel> findProducts(FindProductsQuery query){
		
		List<ReadProductModel> products = new ArrayList<>();
		List<Product> storedProducts = productRepo.findAll();
		
		storedProducts.stream().forEach((p) -> {
			ReadProductModel product = new ReadProductModel();
			BeanUtils.copyProperties(p, product);
			products.add(product);
		});
		
		return products;
	}
	
	
}
