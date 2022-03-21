package com.logicbig.example.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "seller_type")
public class SellerType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "seller_type")
    private String type;

    public SellerType() {
    }

    public SellerType(Integer id, String type) {
        this.id = id;
        this.type = type;
    }

    public SellerType(String type, Integer avarage) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String sellerType) {
        this.type = sellerType;
    }

}
