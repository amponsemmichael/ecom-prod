package com.amponsem.ecom_backend.controller;


import com.amponsem.ecom_backend.model.Product;
import com.amponsem.ecom_backend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

   @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product,
                                        @RequestPart MultipartFile imageFile) {
       try{
           Product product1 = productService.addProduct(product, imageFile);
           return new ResponseEntity<>(product1, HttpStatus.CREATED);
       }
       catch(Exception e){
           return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
       }
   }

    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId) {
       Product product = productService.getProductById(productId);
       byte[] imageFile = product.getImageData();

       return ResponseEntity.ok()
               .contentType(MediaType.valueOf(product.getImageType()))
               .body(imageFile);
    }
}
