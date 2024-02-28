package com.dreamcompany.reviewapplication.productservice.repository;



import com.dreamcompany.reviewapplication.userdetailservice.model.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepo extends CrudRepository<Review,Integer> {

}
