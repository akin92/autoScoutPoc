package com.logicbig.example.controller;

import com.logicbig.example.model.*;
import com.logicbig.example.service.AutoScoutService;
import com.logicbig.example.util.ExcelParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RestApiController {


    @Autowired
    ExcelParser excelParser;

    @Autowired
    AutoScoutService autoScoutService;

    @PostConstruct
    public void fillTables() {
        SellerType type1 = new SellerType(1, "private");
        SellerType type2 = new SellerType(2, "dealer");
        SellerType type3 = new SellerType(3, "other");
        autoScoutService.saveSellerType(type1);
        autoScoutService.saveSellerType(type2);
        autoScoutService.saveSellerType(type3);

        //First Fill Listing
        excelParser.parse(true);
        //Secong Fill Contacts
        excelParser.parse(false);
    }

    @GetMapping("/listing")
    public ResponseEntity<List<Listing>> getAllCategories() {
        try {
            List<Listing> listing = new ArrayList<>(autoScoutService.getAllListing());

            if (listing.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(listing, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/avarageThirtyPercent")
    public ResponseEntity<Integer> getAvarageThirtyPercent() {
        try {
            Integer avarage = 0;
            avarage = autoScoutService.getAvarageThirtyPercent();

            return new ResponseEntity<>(avarage, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/sellerTypes")
    public ResponseEntity<List<SellerType>> getAllSellerType() {
        try {
            List<SellerType> sellerTypes = new ArrayList<>();
            sellerTypes.addAll(autoScoutService.getAllSellerTypes());

            if (sellerTypes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(sellerTypes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/avarageListing")
    public ResponseEntity<List<AvarageListingBySeller>> getAllAvarageListing() {
        try {
            List<AvarageListingBySeller> avarageListingBySeller = new ArrayList<>(autoScoutService.getAllAvarageListingSeller());

            if (avarageListingBySeller.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(avarageListingBySeller, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/makeDistribution")
    public ResponseEntity<List<MakeDistribution>> getAllMakeDistributionListing() {
        try {
            List<MakeDistribution> makeDistributions = new ArrayList<>();
            autoScoutService.getAllMakeDistributions().forEach(makeDistributions::add);

            if (makeDistributions.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(makeDistributions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/mostFiveContacts/{date}")
    public ResponseEntity<List<MostFiveContact>> getAllmostFiveContacts(@PathVariable("date") String date) {
        try {
            List<MostFiveContact> mostFiveContacts = new ArrayList<>(autoScoutService.getAllMostFiveContacts(date));

            if (mostFiveContacts.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(mostFiveContacts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/contacts")
    public ResponseEntity<List<Contacts>> getAllContacts() {
        try {
            List<Contacts> contacts = new ArrayList<>(autoScoutService.getAllContacts());

            if (contacts.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(contacts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
