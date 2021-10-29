package com.appsferybe.estore.productservice.command.product;

import com.appsferybe.estore.productservice.entity.productlookup.ProductLookupEntity;
import com.appsferybe.estore.productservice.repository.productlookup.ProductLookupRepository;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.BiFunction;

@Component
public class CreateProductCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {

    private final ProductLookupRepository productLookupRepository;

    public CreateProductCommandInterceptor(ProductLookupRepository productLookupRepository) {
        this.productLookupRepository = productLookupRepository;
    }

    @Override
    public CommandMessage<?> handle(CommandMessage<?> message) {
        return MessageDispatchInterceptor.super.handle(message);
    }

    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(List<? extends CommandMessage<?>> messages) {
        return (index, command) -> {

            if (CreateProductCommand.class.equals(command.getPayloadType())) {

                CreateProductCommand createProductCommand = (CreateProductCommand) command.getPayload();

                try {
                    ProductLookupEntity productLookupEntity = productLookupRepository
                            .findByProductIdOrTitle(
                                    createProductCommand.getProductId(),
                                    createProductCommand.getTitle()
                            );
                    if (productLookupEntity != null) {
                        throw new IllegalStateException(
                                String.format(
                                        "product with productId %s or title %s already exist",
                                        createProductCommand.getProductId(),
                                        createProductCommand.getTitle()
                                )
                        );
                    }

                } catch (Exception ex) {
                    throw new IllegalArgumentException(ex);
                }

            }
            return command;
        };
    }
}
