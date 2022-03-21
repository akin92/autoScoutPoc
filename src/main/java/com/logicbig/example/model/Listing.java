package com.logicbig.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "listing")
public class Listing implements Serializable {
    @Id
    private Integer id;

    @Column(name = "make",nullable = false)
    private String make;

    @Column(name = "price",nullable = false)
    private Integer price;

    @Column(name = "mileage",nullable = false)
    private Integer mileage;

    @ManyToOne(cascade = {CascadeType.ALL},fetch= FetchType.LAZY)
    @JoinColumn(name = "seller_type", referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private SellerType sellerType;

    public Listing() {
    }

    public Listing(Integer id, String make, Integer price, Integer mileage, SellerType sellerType) {
        this.id = id;
        this.make = make;
        this.price = price;
        this.mileage = mileage;
        this.sellerType = sellerType;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public SellerType getSellerType() {
        return sellerType;
    }

    public void setSellerType(SellerType sellerType) {
        this.sellerType = sellerType;
    }


}
