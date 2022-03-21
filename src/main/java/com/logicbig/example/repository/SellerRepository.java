package com.logicbig.example.repository;

import com.logicbig.example.model.SellerType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<SellerType,Integer> {
    SellerType findByType(String type);
}
