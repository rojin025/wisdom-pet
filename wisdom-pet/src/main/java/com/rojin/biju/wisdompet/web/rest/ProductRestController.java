package com.rojin.biju.wisdompet.web.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import com.rojin.biju.wisdompet.services.ProductService;
import com.rojin.biju.wisdompet.web.errors.BadRequestException;
import com.rojin.biju.wisdompet.web.models.Product;

import java.util.List;




@RestController
@RequestMapping("api/products")
public class ProductRestController {
    
    private final ProductService productService;

    public ProductRestController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts() {
        return this.productService.getProducts();
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct(@RequestBody Product product ) {
        return this.productService.createOrUpdateProduct(product);
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id") long id) {
        return this.productService.getProduct(id);
    }
    
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable("id")long id, @RequestBody Product product) {
        if(id != product.getProductId()){
            throw new BadRequestException("Incoming id doesn't match path" + id);
        }

        return this.productService.createOrUpdateProduct(product);
    }

    @DeleteMapping("/id")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void deleteProduct(@PathVariable("id") long id){
        this.productService.deleteProduct(id);
    }
}
