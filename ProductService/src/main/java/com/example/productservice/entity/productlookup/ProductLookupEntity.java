package com.example.productservice.entity;

import lombok.Data;
import org.checkerframework.common.aliasing.qual.Unique;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

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
