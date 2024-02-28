package com.dreamcompany.reviewapplication.userdetailservice.controller;

import com.dreamcompany.reviewapplication.userdetailservice.model.Review;
import com.dreamcompany.reviewapplication.userdetailservice.model.User;
import com.dreamcompany.reviewapplication.userdetailservice.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserDetailController {

   @Autowired
    UserDetailService userDetailService;

   @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("/userdetailpage")
    public String indexpagemethod(Model model)
    {
        model.addAttribute("userdetails",new User());
        kafkaTemplate.send("logdiary", "UserDetailService : Welcome to application");
        return "index";

    }

    @PostMapping("/continue")
    public String continuemethod(@ModelAttribute("userdetails") User userdetails, Model model) {

        userDetailService.saveUserDetail(userdetails);
        model.addAttribute("username",userdetails.getName());
        model.addAttribute("list",userDetailService.getAllProducts());
        kafkaTemplate.send("logdiary", "UserDetailService : User detail saved "+userdetails.getName());
        return "mainpage";
    }

    @GetMapping("/writereview")
    public String writereview(Model model,@RequestParam(value="id") int id) {
        Review review = userDetailService.writereview(id);
        model.addAttribute("review", review);
        model.addAttribute("productId", id);
        kafkaTemplate.send("logdiary", "UserDetailService : Blank review object created with actual product ID : "+ id);
        return "writereviewpage";

    }

    @PostMapping("/postreview")
    public String reviewdesc(@ModelAttribute("review") Review review)
    {
        userDetailService.saveReview(review);
        kafkaTemplate.send("logdiary", "UserDetailService : Review saved with new review desc : "+review.getReviewdesc());
        return "reviewsavedsuccessful";
    }

    @GetMapping("/readreview")
    public String readReview(Model model,@RequestParam(value="id") int id) {
         List<Review> reviews = userDetailService.readReviews(id);
        model.addAttribute("reviews",reviews);
        kafkaTemplate.send("logdiary", "UserDetailService : Review retrived with review id : "+ id);
        return "readreviewpage";

    }

}
