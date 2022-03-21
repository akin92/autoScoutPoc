package com.logicbig.example.model;

public class AvarageListingBySeller {

    private String sellerType;
    private Double price;

    public AvarageListingBySeller(String sellerType, Double price) {
        this.sellerType = sellerType;
        this.price = price;
    }

    public AvarageListingBySeller() {
    }

    public String getSellerType() {
        return sellerType;
    }

    public void setSellerType(String sellerType) {
        this.sellerType = sellerType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}
