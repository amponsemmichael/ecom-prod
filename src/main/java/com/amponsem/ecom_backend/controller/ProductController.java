package com.amponsem.ecom_backend.controller;


import com.amponsem.ecom_backend.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

   @RequestMapping("/")
    public String home() {
       return "Hello World";
   }

//   @GetMapping("/product")
//    public List<Product> getAllProducts() {
//       List<Product> products = new ArrayList<Product>();
//       return products;
//   }
}
