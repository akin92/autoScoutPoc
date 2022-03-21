package com.logicbig.example.model;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;



public class MakeDistribution {


    private String make;
    private Integer distribution;

    public MakeDistribution(String make, Integer distribution) {
        this.make = make;
        this.distribution = distribution;
    }

    public MakeDistribution() {
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public Integer getDistribution() {
        return distribution;
    }

    public void setDistribution(Integer distribution) {
        this.distribution = distribution;
    }
}
