package com.dreamcompany.reviewapplication.userdetailservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Product implements Serializable  {
    private static final long serialVersionUID = 7156526077883281623L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String productName;
    String product_desc;
    Date exp;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy ="product")
    List<Review> reviews = new ArrayList<>();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProduct_desc() {
        return product_desc;
    }

    public void setProduct_desc(String product_desc) {
        this.product_desc = product_desc;
    }

    public Date getExp() {
        return exp;
    }

    public void setExp(Date exp) {
        this.exp = exp;
    }

    public Product(int id, String productName, String product_desc, Date exp) {
        this.id = id;
        this.productName = productName;
        this.product_desc = product_desc;
        this.exp = exp;
    }

    public Product() {
    }

    public Product(String productName) {
        this.productName = productName;
    }

    public Product(String productName, String product_desc, Date exp) {
        this.productName = productName;
        this.product_desc = product_desc;
        this.exp = exp;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
