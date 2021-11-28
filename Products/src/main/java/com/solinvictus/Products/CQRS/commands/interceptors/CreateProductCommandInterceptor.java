package com.solinvictus.Products.CQRS.commands.interceptors;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.logging.Logger;

import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.solinvictus.Products.CQRS.commands.CreateProductCommand;
import com.solinvictus.Products.CQRS.entity.ProductLookup;
import com.solinvictus.Products.Repositories.ProductLookupRepo;

@Component
public class CreateProductCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {

	// private static final Logger LOGGER =
	// LoggerFactory.getLogger(CreateProductCommandInterceptor.class);

	private final ProductLookupRepo productLookupRepo;

	public CreateProductCommandInterceptor(ProductLookupRepo productLookupRepo) {
		this.productLookupRepo = productLookupRepo;
	}

	@Override
	public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(
			List<? extends CommandMessage<?>> messages) {
		return (index, command) -> {
			if (CreateProductCommand.class.equals(command.getPayloadType())) {
				CreateProductCommand cmd = (CreateProductCommand) command.getPayload();
				// LOGGER.info("Intercepted Create Product Command");
				System.out.println("Intercepted Create Product Command");
				ProductLookup p = productLookupRepo.findByProductIdOrTitle(cmd.getProductId(), cmd.getTitle());
				if (p != null)
					throw new IllegalStateException("Product with productId:" + cmd.getProductId() + " or title:"
							+ cmd.getTitle() + " already exists");
			}
			return command;
		};
	}

}
