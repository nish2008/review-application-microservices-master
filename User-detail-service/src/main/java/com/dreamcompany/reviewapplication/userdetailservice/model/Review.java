package com.dreamcompany.reviewapplication.userdetailservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int reviewId;


    @ManyToOne
    @JoinColumn(name = "id" , nullable = false)
    private Product product;

    private String reviewdesc;

    public Review() {
    }

    public Review(Product product) {
        this.product = product;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getReviewdesc() {
        return reviewdesc;
    }

    public void setReviewdesc(String reviewdesc) {
        this.reviewdesc = reviewdesc;
    }

    public Review(int reviewId, Product product, String reviewdesc) {
        this.reviewId = reviewId;
        this.product = product;
        this.reviewdesc = reviewdesc;
    }

    public Review(Product product, String reviewdesc) {
        this.product = product;
        this.reviewdesc = reviewdesc;
    }

}
