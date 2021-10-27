package com.appsferybe.estore.productservice.command.product;

import com.appsferybe.estore.productservice.repository.product.ProductRepository;
import com.appsferybe.estore.productservice.entity.product.ProductEntity;
import com.appsferybe.estore.productservice.event.ProductCreateEvent;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product-group")
public class ProductEventsHandler {
    private final ProductRepository productRepository;


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
}
