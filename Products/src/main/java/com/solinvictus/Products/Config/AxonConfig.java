package com.solinvictus.Products.Config;

import com.solinvictus.Products.CQRS.commands.ProductAggregate;
import com.solinvictus.Products.CQRS.commands.interceptors.CreateProductCommandInterceptor;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {

    @Bean
    EventSourcingRepository<ProductAggregate> productAggregateEventSourcingRepository(EventStore eventStore){
        EventSourcingRepository<ProductAggregate> repository = EventSourcingRepository.builder(ProductAggregate.class).eventStore(eventStore).build();
        return repository;
    }
    
    @Autowired
    public void registerCreateProductCommandInterceptor(ApplicationContext context, CommandBus commandBus) {
    	
    	commandBus.registerDispatchInterceptor(context.getBean(CreateProductCommandInterceptor.class));
    }
}
