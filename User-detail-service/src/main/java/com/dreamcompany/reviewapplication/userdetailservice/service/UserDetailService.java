package com.dreamcompany.reviewapplication.userdetailservice.service;

import com.dreamcompany.reviewapplication.userdetailservice.model.Product;
import com.dreamcompany.reviewapplication.userdetailservice.model.Review;
import com.dreamcompany.reviewapplication.userdetailservice.model.User;
import com.dreamcompany.reviewapplication.userdetailservice.repository.UserRepo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class UserDetailService {

    private final Logger LOG = LoggerFactory.getLogger(UserDetailService.class);

    @Autowired
    UserRepo userRepo;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    RedisTemplate<String, List<Product>> redisTemplate;

    public void saveUserDetail(User userdetails)
    {
        userRepo.save(userdetails);
    }



    @HystrixCommand(fallbackMethod = "getAllProductsFromFallback", commandProperties = {

            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
            @HystrixProperty (name = "circuitBreaker.sleepWindowInMilliseconds", value = "500000")
    })
    public List<Product> getAllProducts()
    {
     // List list = restTemplate.getForObject("http://localhost:8086/products",List.class);
        List<Product> list = restTemplate.getForObject("http://productservice/products",List.class);
        //System.out.println("getAllProductsCalled");
        LOG.info("getAllProducts from DB : ",list.size());
        LOG.debug("getAllProducts from DB : ",list.size());
        LOG.trace("getAllProducts from DB : ",list.size());
      return list;
    }

    public Review writereview(int id)
    {
      //  Review review = restTemplate.getForObject("http://localhost:8087/writereview/"+ id ,Review.class);
        Review review = restTemplate.getForObject("http://reviewservice/writereview/"+ id ,Review.class);
        return review;
    }

    public void saveReview(Review review)
    {
       // restTemplate.postForObject("http://localhost:8087/savedreview", review ,Review.class);
        restTemplate.postForObject("http://reviewservice/savedreview", review ,Review.class);


    }

    public List<Review> readReviews(int id)
    {
       // List<Review> reviews = restTemplate.getForObject("http://localhost:8087/getreview/"+id,List.class);
        List<Review> reviews = restTemplate.getForObject("http://reviewservice/getreview/"+id,List.class);
        return reviews;
    }

    public List<Product> getAllProductsFromFallback()
    {
        LOG.info("Fallback method is called");
        if(redisTemplate.opsForHash().hasKey("cachelist", "key2")){
            LOG.info("Fallback taken care from cache");
            return (List<Product>) redisTemplate.opsForHash().get("cachelist", "key2");
        }
        LOG.info("Fallback taken care from hardcoded object");
        return new ArrayList<Product>(Arrays.asList(new Product("Strin1","String2",new Date())));
    }

}
