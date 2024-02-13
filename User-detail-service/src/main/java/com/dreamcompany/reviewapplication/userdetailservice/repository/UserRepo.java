package com.dreamcompany.reviewapplication.userdetailservice.repository;


import com.dreamcompany.reviewapplication.userdetailservice.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User,Integer> {
}
