package com.dreamcompany.reviewapplication.productservice.service;

import com.dreamcompany.reviewapplication.userdetailservice.model.Product;
import com.dreamcompany.reviewapplication.productservice.repository.ProductRepo;
import com.dreamcompany.reviewapplication.userdetailservice.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    RedisTemplate redisTemplate;

    //@Cacheable(value ="cachelist" , key = "'key2'")
    public List<Product> getAllProducts()
    {
        if(redisTemplate.opsForHash().hasKey("cachelist", "key2")){
           return (List<Product>) redisTemplate.opsForHash().get("cachelist", "key2");
        }
        System.out.println("sdsdfdsvdfsdfdsgsdfvgdsg");
        Iterator iterator = productRepo.findAll().iterator();
        List list = getListFromIterator(iterator);
        redisTemplate.opsForHash().put("cachelist", "key2", list);
        return list;
    }

    public List<Review> review(int pid)
    {

        return productRepo.findById(pid).get().getReviews();
    }
    public  <Product> List<Product> getListFromIterator(Iterator<Product> iterator)
    {

        // Create an empty list
        List<Product> list = new ArrayList<>();

        // Add each element of iterator to the List
        iterator.forEachRemaining(list::add);

        // Return the List
        return list;
    }
}
