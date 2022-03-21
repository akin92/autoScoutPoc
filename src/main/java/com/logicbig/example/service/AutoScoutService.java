package com.logicbig.example.service;

import com.logicbig.example.model.*;
import com.logicbig.example.repository.ContactsRepository;
import com.logicbig.example.repository.ListingRepository;
import com.logicbig.example.repository.SellerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class AutoScoutService {

    @Autowired
    ListingRepository listingRepository;

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    ContactsRepository contactsRepository;

    private static final Logger logger = LogManager.getLogger(AutoScoutService.class);

    public List<AvarageListingBySeller> getAllAvarageListingSeller() {
        return listingRepository.getAverageListingBySellerType();
    }

    public List<MakeDistribution> getAllMakeDistributions() {
        List<MakeDistribution> makeDistributions = new ArrayList<>();
        try {
            List<Object[]> list = listingRepository.getDistributionByMake();

            for (Object[] q1 : list) {

                String make = q1[0].toString();
                Integer percentage = Integer.valueOf(q1[1].toString());

                makeDistributions.add(new MakeDistribution(make, percentage));
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return makeDistributions;
    }

    public List<MostFiveContact> getAllMostFiveContacts(String date) {
        List<MostFiveContact> contacts = new ArrayList<>();
        List<Object[]> list;
        try {
            list = contactsRepository.getMostFiveContactsByDate(date);
            for (Object[] q1 : list) {

                Integer id = Integer.valueOf(q1[0].toString());
                String make = q1[1].toString();
                Integer price = Integer.valueOf(q1[2].toString());
                Integer mileage = Integer.valueOf(q1[3].toString());
                Integer count = Integer.valueOf(q1[4].toString());
                contacts.add(new MostFiveContact(id, make, price, mileage, null, count));
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return contacts;
    }

    public Integer getAvarageThirtyPercent() {
        List<Object[]> list;
        int average = 0 ;
        int percent ;
        int count ;

        try {
            count = Math.toIntExact(contactsRepository.count());
            percent = count / 100 * 30;
            list = contactsRepository.getAvarageThirtyPercent(percent);
            for (Object[] q1 : list) {
                average = (int) q1[0];
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return average;
    }

    public List<Listing> getAllListing() {
        return listingRepository.findAll();
    }

    public List<Contacts> getAllContacts() {
        return contactsRepository.findAll();
    }

    public List<SellerType> getAllSellerTypes() {
        return sellerRepository.findAll();
    }

    public SellerType saveSellerType(SellerType sellerType) {
        return sellerRepository.saveAndFlush(sellerType);
    }

    public void saveAllListing(List<Listing> listings) {
        listingRepository.saveAll(listings);
    }

    public void saveAllContacts(List<Contacts> contacts) {
        contactsRepository.saveAll(contacts);
    }

    public SellerType getSellerType(String type) {
        return sellerRepository.findByType(type);
    }

    public Listing findListingById(Integer id) {
        return listingRepository.findById(id).get();
    }
}
