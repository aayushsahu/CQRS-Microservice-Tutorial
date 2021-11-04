package com.solinvictus.Products.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.solinvictus.Products.Entity.Product;


@Repository
public interface ProductRepo extends JpaRepository<Product , Long>{
}
