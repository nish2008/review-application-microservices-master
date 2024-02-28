package com.dreamcompany.reviewapplication.reviewservice.controller;

import com.dreamcompany.reviewapplication.reviewservice.model.Product;
import com.dreamcompany.reviewapplication.reviewservice.model.Review;
import com.dreamcompany.reviewapplication.reviewservice.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @Autowired
    KafkaTemplate kafkaTemplate;

    @GetMapping("/writereview/{pid}")
    public Review writeReviewMethod(@PathVariable("pid") int id)
    {
         Review obj = reviewService.writeReview(id);
         kafkaTemplate.send("logdiary", "ReviewService : Empty review object is made and returned");
         return obj;

    }

    @PostMapping("/savedreview")
    public void saveReviewDb(@RequestBody Review review)
    {
        reviewService.saveRev(review);
        kafkaTemplate.send("logdiary","ReviewService : Review saved for id : " + review.getReviewId());
    }

    @GetMapping("/getreview/{pid}")
    public List<Review> getReviewsForProduct(@PathVariable("pid") Integer pid) {
        kafkaTemplate.send("logdiary", "ReviewService: Review has been returned for the product : "+pid);
        return reviewService.getReviewsForProduct(pid);
    }
}
