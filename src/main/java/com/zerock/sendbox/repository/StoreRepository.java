package com.zerock.sendbox.repository;

import com.zerock.sendbox.entity.Orders;
import com.zerock.sendbox.entity.Store;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Integer> {

    //매장 정보 수정폼 조회
    @Query("select s from Store s  where s.ownerMember.ownerNo =:ownerNo")
    Store findByInfoOwnerNo(@Param("ownerNo")Integer ownerNo);

    //매장 정보 수정
    Store findByStoreNo(Integer storeNo);
}
