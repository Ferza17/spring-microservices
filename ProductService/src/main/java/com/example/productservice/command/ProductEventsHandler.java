package com.example.productservice.command;

import com.example.productservice.entity.ProductEntity;
import com.example.productservice.event.ProductCreateEvent;
import com.example.productservice.repository.ProductRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ProductEventsHandler {
    private final ProductRepository productRepository;


    public ProductEventsHandler(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @EventHandler
    public void on(ProductCreateEvent productCreateEvent){
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(productCreateEvent, productEntity);

        productRepository.save(productEntity);
    }
}
