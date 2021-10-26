package com.example.productservice.command.productlookup;

import com.example.productservice.entity.productlookup.ProductLookupEntity;
import com.example.productservice.event.ProductCreateEvent;
import com.example.productservice.repository.productlookup.ProductLookupRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@ProcessingGroup("product-group")
public class ProductLookupEventHandler {

    private final ProductLookupRepository productLookupRepository;

    public ProductLookupEventHandler(ProductLookupRepository productLookupRepository) {
        this.productLookupRepository = productLookupRepository;
    }


    @EventHandler
    public void on(ProductCreateEvent event) {
        ProductLookupEntity productLookupEntity = new ProductLookupEntity(event.getProductId(), event.getTitle());
        productLookupRepository.save(productLookupEntity);
    }
}
