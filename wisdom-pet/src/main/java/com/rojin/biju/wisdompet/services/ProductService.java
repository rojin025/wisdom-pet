package com.rojin.biju.wisdompet.services;

import org.springframework.stereotype.Service;

import com.rojin.biju.wisdompet.data.entities.ProductEntity;
import com.rojin.biju.wisdompet.data.repositories.ProductRepository;
import com.rojin.biju.wisdompet.web.models.Product;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    private Product translateDbToWeb(ProductEntity entity){
        return new Product(
            entity.getId(),
            entity.getName(),
            entity.getPrice(),
            entity.getVendorId()
        );
    }

    private ProductEntity translateWebToDb(Product product){
        ProductEntity entity = new ProductEntity();
        
        entity.setId(product.getProductId()==null?0: product.getProductId());
        entity.setName(product.getName());
        entity.setPrice(product.getPrice());
        entity.setVendorId(product.getVendorId());

        return entity;
    }

}
