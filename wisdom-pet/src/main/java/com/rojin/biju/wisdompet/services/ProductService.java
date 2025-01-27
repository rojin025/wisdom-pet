package com.rojin.biju.wisdompet.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rojin.biju.wisdompet.data.entities.ProductEntity;
import com.rojin.biju.wisdompet.data.repositories.ProductRepository;
import com.rojin.biju.wisdompet.web.errors.NotFoundException;
import com.rojin.biju.wisdompet.web.models.Product;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> getProducts(){
        Iterable<ProductEntity> productEntities = this.productRepository.findAll();
        List<Product> products = new ArrayList<>();
        productEntities.forEach(productEntity -> {
            products.add(translateDbToWeb(productEntity));
        });

        return products;
    }

    public Product getProduct(long id){
        Optional<ProductEntity> optional = this.productRepository.findById(id);

        if(optional.isEmpty()){
            throw new NotFoundException("Product entity not found with id: " + id);
        }

        return this.translateDbToWeb(optional.get());
    }

    public Product createOrUpdateProduct(Product product){
        ProductEntity entity = this.translateWebToDb(product);
        entity = this.productRepository.save(entity);

        return this.translateDbToWeb(entity);
    }

    public void deleteProduct(long id ){
        this.productRepository.deleteById(id);
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
