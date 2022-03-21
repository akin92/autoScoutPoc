package com.logicbig.example;

import com.logicbig.example.model.*;
import com.logicbig.example.repository.ContactsRepository;
import com.logicbig.example.repository.ListingRepository;
import com.logicbig.example.repository.SellerRepository;
import com.logicbig.example.service.AutoScoutService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class AutoScoutServiceTest {

    @Autowired
    public AutoScoutService autoScoutService;

    @MockBean
    public ListingRepository listingRepository;

    @MockBean
    public ContactsRepository contactsRepository;

    @MockBean
    public SellerRepository sellerRepository;

    List<Object[]> contacts;
    Object[] contactsObject;

    List<Object[]> makeDistributions;
    Object[] distrbitons;

    String date;

    @TestConfiguration
    static class AutoScoutServiceIntegrationTestContextConfiguration {

        @Bean
        public AutoScoutService autoScoutService() {
            return new AutoScoutService();
        }
    }

    @Before
    public void setUp() {
        date = "2020-01";
        contacts = new ArrayList<>();
        contactsObject = new Object[5];

        contactsObject[0] = 1000;
        contactsObject[1] = "BMW";
        contactsObject[2] = 3500;
        contactsObject[3] = 25000;
        contactsObject[4] = 15;
        contacts.add(contactsObject);

        makeDistributions = new ArrayList<>();
        distrbitons = new Object[2];
        distrbitons[0] = "BMW";
        distrbitons[1] = 10;
        makeDistributions.add(distrbitons);
    }

    @Test
    public void testGetAllListing() {
        List<Listing> listings = Mockito.mock(ArrayList.class);
        Mockito.when(autoScoutService.getAllListing()).thenReturn(listings);

    }

    @Test
    public void testGetAllContacts() {
        List<Contacts> contacts = Mockito.mock(ArrayList.class);
        Mockito.when(autoScoutService.getAllContacts()).thenReturn(contacts);

    }

    @Test
    public void testGetAllSellerTypes() {
        List<SellerType> sellerTypes = Mockito.mock(ArrayList.class);
        Mockito.when(autoScoutService.getAllSellerTypes()).thenReturn(sellerTypes);

    }

    @Test
    public void testSaveSellerType() {
        SellerType sellerType = new SellerType(1, "dealer");
        Mockito.when(autoScoutService.saveSellerType(sellerType)).thenReturn(sellerType);

    }

    @Test
    public void testGetAllAvarageListingSeller() {
        List<AvarageListingBySeller> avarageListingBySellers = Mockito.mock(ArrayList.class);
        Mockito.when(autoScoutService.getAllAvarageListingSeller()).thenReturn(avarageListingBySellers);

    }

    @Test
    public void testGetAMakeAllDistrbutions() {
        Mockito.when(listingRepository.getDistributionByMake()).thenReturn(makeDistributions);
        assertThat(autoScoutService.getAllMakeDistributions().get(0).getMake())
                .isEqualTo(distrbitons[0]);
        assertThat(autoScoutService.getAllMakeDistributions().get(0).getDistribution())
                .isEqualTo(distrbitons[1]);

    }

    @Test
    public void testGetAllMostFiveContacts() {
        Mockito.when(contactsRepository.getMostFiveContactsByDate(date)).thenReturn(contacts);

        List<MostFiveContact> allMostFiveContacts = autoScoutService.getAllMostFiveContacts(date);

        assertThat(allMostFiveContacts.get(0).getId())
                .isEqualTo(contactsObject[0]);
        assertThat(allMostFiveContacts.get(0).getMake())
                .isEqualTo(contactsObject[1]);
        assertThat(allMostFiveContacts.get(0).getPrice())
                .isEqualTo(contactsObject[2]);
        assertThat(allMostFiveContacts.get(0).getMileage())
                .isEqualTo(contactsObject[3]);
        assertThat(allMostFiveContacts.get(0).getCount())
                .isEqualTo(contactsObject[4]);

    }


}
