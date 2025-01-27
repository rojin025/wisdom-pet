package com.rojin.biju.wisdompet.data.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Entity
@Table(name="PRODUCTS")
@Data
@ToString
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private long id;

    @Column(name="NAME")
    private String name;

    @Column(name="PRICE")
    private BigDecimal price;
    
    @Column(name="VENDOR_ID")
    private long vendorId;
}
