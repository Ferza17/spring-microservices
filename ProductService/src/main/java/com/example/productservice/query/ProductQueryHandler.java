package com.example.productservice.query;

import com.example.productservice.entity.product.ProductEntity;
import com.example.productservice.model.product.ProductRestModel;
import com.example.productservice.repository.product.ProductRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductQueryHandler {
    private final ProductRepository productRepository;

    public ProductQueryHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @QueryHandler
    public List<ProductRestModel> findProducts(FindProductQuery query) {
        List<ProductRestModel> productRest = new ArrayList<>();
        List<ProductEntity> storedProducts = productRepository.findAll();

        for (ProductEntity productEntity: storedProducts) {
            ProductRestModel productRestModel = new ProductRestModel();
            BeanUtils.copyProperties(productEntity, productRestModel);
            productRest.add(productRestModel);
        }

        return productRest;
    }
}
