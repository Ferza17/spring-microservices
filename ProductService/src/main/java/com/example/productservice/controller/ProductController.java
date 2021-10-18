package com.example.productservice.controller;

import com.example.productservice.model.product.CreateProductRestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private Environment env;

    @PostMapping
    public String createProduct(@RequestBody CreateProductRestModel createProductRestModel) {
        return "HTTP Post Handler " + createProductRestModel.getTitle();
    }

    @GetMapping
    public String getProduct() {
        return "HTTP Get Handler " + env.getProperty("local.server.port");
    }

    @PutMapping
    public String updateProduct() {
        return "HTTP Put Handler " + env.getProperty("local.server.port");
    }

    @DeleteMapping("{id}")
    public String deleteProduct(@PathVariable String id) {
        return "HTTP Del Handler " + id + " " + env.getProperty("local.server.port");
    }
}
