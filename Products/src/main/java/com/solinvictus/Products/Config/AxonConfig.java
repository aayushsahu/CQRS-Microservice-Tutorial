package com.solinvictus.Products.Config;

import com.solinvictus.Products.CQRS.commands.ProductAggregate;
import com.solinvictus.Products.CQRS.commands.interceptors.CreateProductCommandInterceptor;
import com.solinvictus.Products.Exceptions.ProductServiceEventsErrorHandler;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.eventhandling.PropagatingErrorHandler;
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
    
    @Autowired
    public void configure(EventProcessingConfigurer config) {
    	
    	config.registerListenerInvocationErrorHandler("product-group", conf -> new ProductServiceEventsErrorHandler());
    	
    	//alternate approach : using this we would not need to create ProductServiceEventsErrorHandler class
    	//config.registerListenerInvocationErrorHandler("product-group", conf -> PropagatingErrorHandler.instance());
    }
}
