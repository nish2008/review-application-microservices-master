package com.dreamcompany.reviewapplication.reviewservice.service;

import com.dreamcompany.reviewapplication.reviewservice.model.Product;
import com.dreamcompany.reviewapplication.reviewservice.model.Review;
import com.dreamcompany.reviewapplication.reviewservice.repository.ReviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    ReviewRepo reviewRepo;

    public Review writeReview(int id)
    {
        Product obj = new Product();
        obj.setId(id);
        return new Review(obj);
    }

    public void saveRev(Review review)
    {
        reviewRepo.save(review);
    }

    public List<Review> getReviewsForProduct(Integer pid) {
        return reviewRepo.findReviewByproduct_id(pid);

    }

}
