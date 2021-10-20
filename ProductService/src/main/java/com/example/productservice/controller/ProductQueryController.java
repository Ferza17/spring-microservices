package com.example.productservice.controller;

import com.example.productservice.model.product.CreateProductRestModel;
import com.example.productservice.model.product.ProductRestModel;
import com.example.productservice.query.FindProductQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductQueryController {
    @Autowired
    QueryGateway queryGateway;

    @GetMapping
    public List<ProductRestModel> getProducts() {
        FindProductQuery findProductQuery = new FindProductQuery();
        List<ProductRestModel> products = queryGateway
                .query(findProductQuery, ResponseTypes
                        .multipleInstancesOf(ProductRestModel.class))
                .join();

        return products;
    }
}
