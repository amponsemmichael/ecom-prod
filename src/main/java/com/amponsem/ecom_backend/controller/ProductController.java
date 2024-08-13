package com.amponsem.ecom_backend.controller;


import com.amponsem.ecom_backend.model.Product;
import com.amponsem.ecom_backend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
