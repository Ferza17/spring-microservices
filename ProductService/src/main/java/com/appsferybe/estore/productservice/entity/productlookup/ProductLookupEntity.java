package com.appsferybe.estore.productservice.entity.productlookup;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.common.aliasing.qual.Unique;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "productlookup")
public class ProductLookupEntity implements Serializable {
    @Id
    @Column(unique = true)
    private String productId;
    @Column(unique = true)
    private String title;
}
