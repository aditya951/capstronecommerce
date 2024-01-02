package com.example.capstronecommerce.controller;


import com.example.capstronecommerce.model.Product;
import com.example.capstronecommerce.service.FakeStoreProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class controller {
    @Autowired
    private FakeStoreProductService productService;

    @GetMapping() // localhost:8080/products
    public List<Product> getAllProducts() {
//        restTemplate.delete(null);
        return productService.getProducts();
//        return new ArrayList<Product>();
    }

    @GetMapping("/{id}")
    public Product getSingleProduct(@PathVariable("id") Long id) {
        return productService.getSingleProduct(id);
    }

    
    
    @PostMapping()
    public Product addNewProduct(@RequestBody Product product) {
//        Product p = new Product();
//        p.setTitle("A new product");
    	
    	System.out.println("hellobbbbbb");
        return productService.addSingleProduct(product);
//        return p;
    }

    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
    	return productService.updateSingleProductField(product,id);
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product) {
    	
    	return productService.updateSingleProduct(product,id);
    	
//        return new Product();
    }

    @DeleteMapping("/{id}")
    public Product deleteProduct(@PathVariable("id") Long id) {
    	
    	return productService.deleteSingleProduct(id);

    }


    
}
