package com.solinvictus.Products.Config;

import com.solinvictus.Products.CQRS.commands.ProductAggregate;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import com.thoughtworks.xstream.security.TypePermission;

import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {

    @Bean
    EventSourcingRepository<ProductAggregate> productAggregateEventSourcingRepository(EventStore eventStore){
        EventSourcingRepository<ProductAggregate> repository = EventSourcingRepository.builder(ProductAggregate.class).eventStore(eventStore).build();
        return repository;
    }
}
