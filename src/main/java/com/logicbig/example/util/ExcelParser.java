package com.logicbig.example.util;

import com.logicbig.example.model.Contacts;
import com.logicbig.example.model.Listing;
import com.logicbig.example.model.SellerType;
import com.logicbig.example.repository.ContactsRepository;
import com.logicbig.example.repository.ListingRepository;
import com.logicbig.example.repository.SellerRepository;
import com.logicbig.example.service.AutoScoutService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.*;


@Service
public class ExcelParser {

    private static final Logger logger = LogManager.getLogger(ExcelParser.class);

    @Autowired
    AutoScoutService autoScoutService;

    @Transactional
    public void parse(boolean isForListing) {

        String line;
        String splitBy = ",";
        int index = 0;
        List<Listing> listingList = new ArrayList<>();
        List<Contacts> contactsList = new ArrayList<>();
        InputStream is;
        try {
            if (isForListing) {
                is = getFileFromResourceAsStream("listings.csv");
                // The class loader that loaded the class
            } else {
                is = getFileFromResourceAsStream("contacts.csv");
            }
//parsing a CSV file into BufferedReader class constructor
            InputStreamReader streamReader =
                    new InputStreamReader(is, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(streamReader);
            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                if (index == 0) {
                    index++;
                    continue;
                }
                String[] object = line.split(splitBy);    // use comma as separator
                if (isForListing) {
                    saveListingToDb(listingList, object);
                } else {
                    saveContactsToDb(contactsList, object);
                }
                index++;
            }
            if (isForListing) {
                autoScoutService.saveAllListing(listingList);
            } else {
                autoScoutService.saveAllContacts(contactsList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private InputStream getFileFromResourceAsStream(String fileName) {

        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }

    }

    private void saveListingToDb(List<Listing> listingList, String[] object) {
        logger.info("Listing [Id=" + object[0] + ", Make=" + object[1] + ", Price=" + object[2] + ", mileage=" + object[3] + ", SellerType= " + object[4] + "]");
        SellerType sellerType = autoScoutService.getSellerType(object[4].substring(1, object[4].length() - 1));
        listingList.add(new Listing(Integer.valueOf(object[0]), object[1], Integer.valueOf(object[2]), Integer.valueOf(object[3]), sellerType));
    }

    private void saveContactsToDb(List<Contacts> contactsList, String[] object) {
        logger.info("Contacts [ListingId=" + object[0] + ", ContactDate=" + object[1] + "]");
        Listing listing = autoScoutService.findListingById(Integer.valueOf(object[0]));
        contactsList.add(new Contacts(listing, new Date(new Timestamp(Long.valueOf(object[1])).getTime())));
    }

}
