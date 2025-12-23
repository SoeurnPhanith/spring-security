package com.example.demo_spring_security.controller;

import com.example.demo_spring_security.entity.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductsController {

    @GetMapping
    public List<Product> getAllProducts() {

        List<Product> products = new ArrayList<>();

        products.add(new Product(1, "Laptop", 10, 75000.00));
        products.add(new Product(2, "Mouse", 50, 500.00));
        products.add(new Product(3, "Keyboard", 30, 1500.00));
        products.add(new Product(4, "Monitor", 15, 12000.00));
        products.add(new Product(5, "Printer", 5, 18000.00));

        return products;
    }

}
