package com.appsferybe.estore.productservice.command.product;

import com.appsferybe.estore.core.events.ProductReservationCancelEvent;
import com.appsferybe.estore.core.events.ProductReservationCancelledEvent;
import com.appsferybe.estore.core.events.ProductReservedEvent;
import com.appsferybe.estore.productservice.repository.product.ProductRepository;
import com.appsferybe.estore.productservice.entity.product.ProductEntity;
import com.appsferybe.estore.productservice.event.ProductCreateEvent;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product-group")
public class ProductEventsHandler {
    private final ProductRepository productRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductEventsHandler.class);


    public ProductEventsHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @ExceptionHandler(resultType = IllegalArgumentException.class)
    public void handle(IllegalArgumentException exception) {
    }

    @ExceptionHandler(resultType = Exception.class)
    public void handle(Exception exception) throws Exception {
        throw exception;
    }

    @EventHandler
    public void on(ProductCreateEvent productCreateEvent) throws Exception {
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(productCreateEvent, productEntity);

        try {
            productRepository.save(productEntity);
        } catch (IllegalArgumentException ex) {
            throw new Exception(ex);
        }
    }

    @EventHandler
    public void on(ProductReservedEvent productReservedEvent) {
        ProductEntity productEntity = productRepository.findByProductId(productReservedEvent.getProductId());
        productEntity.setQuantity(productEntity.getQuantity() - productReservedEvent.getQuantity());

        productRepository.save(productEntity);
        LOGGER.info("ProductReservedEvent is called for productId: " + productReservedEvent.getProductId() +
                " and orderId: " + productReservedEvent.getOrderId());
    }

    @EventHandler
    public void on(ProductReservationCancelledEvent productReservationCancelledEvent) {
        ProductEntity currentlyProduct = productRepository.findByProductId(productReservationCancelledEvent.getProductId());

        int newQuantity = currentlyProduct.getQuantity() + productReservationCancelledEvent.getQuantity();
        currentlyProduct.setQuantity(newQuantity);
        productRepository.save(currentlyProduct);
    }
}
