package com.solinvictus.Products.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solinvictus.Products.Entity.Product;
import com.solinvictus.Products.Exceptions.ProductNotFoundException;
import com.solinvictus.Products.Repositories.ProductRepo;

@Service
public class ProductsServices {

	@Autowired
	private ProductRepo productRepo;
	
	public boolean createProduct(Product p) {
		productRepo.save(p);
		return true;
	}
	
	public boolean deleteProduct(Product p) {
		productRepo.delete(p);
		return true;
	}
	
	public Product getProduct(Long id) throws ProductNotFoundException {
		Optional<Product> prod = productRepo.findById(id);
		if(prod.isPresent())
			return prod.get();
		else
			throw new ProductNotFoundException("Product with id : " + id+ " not found.");
	}
	
	public List<Product> getProducts() {
		List<Product> prods = productRepo.findAll();
		return prods;
	}
	
}
