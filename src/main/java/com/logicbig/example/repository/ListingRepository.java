package com.logicbig.example.repository;

import com.logicbig.example.model.AvarageListingBySeller;
import com.logicbig.example.model.Listing;
import com.logicbig.example.model.MakeDistribution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import java.util.List;

public interface ListingRepository extends JpaRepository<Listing, Integer> {
    @Query("SELECT new  com.logicbig.example.model.AvarageListingBySeller(e.sellerType.type , AVG(e.price)) FROM Listing e group by e.sellerType")
    List<AvarageListingBySeller> getAverageListingBySellerType();

    @Query(value = "SELECT e.make, (count(e.make) * 100 / (SELECT count(*) FROM listing )) as percentage  FROM listing e Group BY e.make",nativeQuery = true)
    List<Object[]> getDistributionByMake();

    /*@Query(value = "select  res.id,li.make,li.price,li.mileage , res.con from listing li , (select cnt.* , cont.contact_date  from contacts cont, (select  l.id ,count(c.*) as con  from listing l , contacts c where l.id = c.listing_id   group by  l.id) AS cnt where cnt.id=cont.id) res where li.id = res.id and to_char(res.contact_date,'yyyy-mm')=:date order by res.con desc limit 5",nativeQuery = true)
    List<Object[]> getMostFiveContactsByDate(@Param("date") String date);*/

}
