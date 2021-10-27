package com.appsferybe.estore.productservice.command.productlookup;

import com.appsferybe.estore.productservice.repository.productlookup.ProductLookupRepository;
import com.appsferybe.estore.productservice.entity.productlookup.ProductLookupEntity;
import com.appsferybe.estore.productservice.event.ProductCreateEvent;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
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
