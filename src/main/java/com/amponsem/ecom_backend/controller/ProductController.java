package com.amponsem.ecom_backend.controller;


import com.amponsem.ecom_backend.model.Product;
import com.amponsem.ecom_backend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

   @RequestMapping("/")
    public String home() {
       return "Hello World";
   }

   @GetMapping("/products")
    public List<Product> getProducts() {
       return productService.getAllProducts();
   }

   @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable int id) {
       return productService.getProductById(id);
   }


}
