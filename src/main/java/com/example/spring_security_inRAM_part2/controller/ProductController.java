package com.example.spring_security_inRAM_part2.controller;

import com.example.spring_security_inRAM_part2.model.Products;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping ("api/products")
public class ProductController {

    List<Products> productsList = List.of(
            new Products(1, "Laptop", 10, 1200.0, "High performance laptop"),
            new Products(2, "Smartphone", 25, 800.0, "Latest Android phone"),
            new Products(3, "Tablet", 15, 500.0, "Lightweight tablet"),
            new Products(4, "Headphones", 50, 150.0, "Noise-cancelling headphones"),
            new Products(5, "Smartwatch", 30, 200.0, "Smartwatch with health tracking"),
            new Products(6, "Monitor", 20, 300.0, "24 inch LED monitor"),
            new Products(7, "Keyboard", 40, 50.0, "Mechanical keyboard"),
            new Products(8, "Mouse", 60, 40.0, "Wireless mouse"),
            new Products(9, "Printer", 12, 250.0, "All-in-one printer"),
            new Products(10, "Camera", 8, 700.0, "DSLR camera for photography")
    );

    @GetMapping
    public ResponseEntity<List<Products>> getAllProducts(){
        if(productsList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok().body(productsList);
    }


}
