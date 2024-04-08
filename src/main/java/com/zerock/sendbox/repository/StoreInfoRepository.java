package com.zerock.sendbox.repository;

import com.zerock.sendbox.entity.StoreInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreInfoRepository extends JpaRepository<StoreInfo, Integer> {
}
