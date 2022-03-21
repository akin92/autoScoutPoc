package com.logicbig.example.model;

public class MostFiveContact extends Listing{
    private Integer count;

    public MostFiveContact(Integer count) {
        this.count = count;
    }

    public MostFiveContact(Integer id, String make, Integer price, Integer mileage, SellerType sellerType, Integer count) {
        super(id, make, price, mileage, sellerType);
        this.count = count;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
