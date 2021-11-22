package com.solinvictus.Products;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductsApplication.class, args);
	}
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}    
	
	@Bean
	public XStream getXStream() { 
		final XStream xstream =  new XStream();
		System.out.println("XStream Bean Created");
		xstream.addPermission(AnyTypePermission.ANY);
//		xstream.allowTypes(new Class[] { 
//		        com.solinvictus.Products.CQRS.events.ProductCreatedEvent.class
//		        });
		return xstream;
	}
	
	
}
