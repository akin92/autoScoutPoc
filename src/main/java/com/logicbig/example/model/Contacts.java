package com.logicbig.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "contacts")
public class Contacts implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne(cascade = {CascadeType.ALL},fetch= FetchType.LAZY)
    @JoinColumn(name = "listing_id", referencedColumnName = "id",nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Listing listing;

    @Column(name = "contact_date",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss")
    private Date contactDate;


    public Contacts(Integer id, Listing listing, Date contactDate) {
        this.id = id;
        this.listing = listing;
        this.contactDate = contactDate;
    }

    public Contacts(Listing listing, Date contactDate) {
        this.listing = listing;
        this.contactDate = contactDate;
    }

    public Contacts() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Listing getListing() {
        return listing;
    }

    public void setListing(Listing listing) {
        this.listing = listing;
    }

    public Date getContactDate() {
        return contactDate;
    }

    public void setContactDate(Date contactDate) {
        this.contactDate = contactDate;
    }

}
