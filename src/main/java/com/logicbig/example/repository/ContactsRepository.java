package com.logicbig.example.repository;

import com.logicbig.example.model.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContactsRepository extends JpaRepository<Contacts,Integer> {
    @Query(value = "select  res.id,li.make,li.price,li.mileage , res.con from listing li , (select cnt.* , cont.contact_date  from contacts cont, (select  l.id ,count(c.*) as con  from listing l , contacts c where l.id = c.listing_id   group by  l.id) AS cnt where cnt.id=cont.id) res where li.id = res.id and to_char(res.contact_date,'yyyy-mm')=:date order by res.con desc limit 5",nativeQuery = true)
    List<Object[]> getMostFiveContactsByDate(@Param("date") String date);

    @Query(value = "(select avg(cnt.price)   from contacts cont, (select  l.id ,count(c.*) as con ,avg(l.price) as price   from listing l , contacts c where l.id = c.listing_id   group by  l.id) AS cnt where cnt.id=cont.id) limit :count ",nativeQuery = true)
    List<Object[]> getAvarageThirtyPercent(@Param("count") Integer count);
}
