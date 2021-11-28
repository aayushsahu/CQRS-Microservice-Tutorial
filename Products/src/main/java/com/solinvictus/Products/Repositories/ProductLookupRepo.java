package com.solinvictus.Products.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.solinvictus.Products.CQRS.entity.Product;
import com.solinvictus.Products.CQRS.entity.ProductLookup;


@Repository
public interface ProductLookupRepo extends JpaRepository<ProductLookup , String>{
	ProductLookup findByProductIdOrTitle(String productId, String title);
}
