package com.dreamcompany.reviewapplication.productservice.controller;

import com.dreamcompany.reviewapplication.productservice.model.Product;
import com.dreamcompany.reviewapplication.productservice.model.Review;
import com.dreamcompany.reviewapplication.productservice.repository.ProductRepo;
import com.dreamcompany.reviewapplication.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/products")
    //@Cacheable(value ="cachelist" , key = "'key2'")
    public List<Product> getProducts() throws InterruptedException {
        Thread.sleep(10000);
        return productService.getAllProducts();

    }

    @GetMapping("/getreview/{pid}")
    public List<Review> getReviews(@PathVariable("pid") int pid)
    {
          return productService.review(pid);
    }

    @GetMapping("/getlist")
    public List<Product> getList(Iterator<Product> productIterator)
    {
        return productService.getListFromIterator(productIterator);
    }


}
