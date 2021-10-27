package com.appsferybe.estore.productservice.repository.productlookup;

import com.appsferybe.estore.productservice.entity.productlookup.ProductLookupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductLookupRepository extends JpaRepository<ProductLookupEntity, String> {
    ProductLookupEntity findByProductIdOrTitle(String productId, String title);
}
