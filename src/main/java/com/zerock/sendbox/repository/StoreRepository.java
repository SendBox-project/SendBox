package com.zerock.sendbox.repository;

import com.zerock.sendbox.entity.Orders;
import com.zerock.sendbox.entity.Store;
import feign.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Integer> {

  //검색기능
    //1차코드
    //매장 리스트 조회. 매장 이름에 단어 포함되면 검색 결과에 포함
//    @Query("SELECT r.price, s.storeName, s.thumbnail FROM Room r INNER JOIN r.store s WHERE s.storeName LIKE %:storeName%")
//    @Query("SELECT r.price, s.storeName, s.thumbnail FROM Room r INNER JOIN Store s ON s.storeNo = r.store.storeNo WHERE s.storeName LIKE %:storeName%")
//    List<Store> findAllByStoreName(@Param("storeName") String storeName);


    //2차코드
    @Query("SELECT s FROM Store s JOIN FETCH s.rooms r WHERE s.storeName LIKE %:store_name%")
    List<Store> findAllByStoreName(@Param("storeName") String storeName);

    //검색어가 주어지지 않으면 전체 리스트 조회
    @Query("select s from Store s")
    List<Store> findAllStores(Pageable pageable);

  
    //사업자의 예약 내역 리스트 조회
    @Query("select s,r,o,u from Store s inner join  Room r on s.storeNo = r.store.storeNo " +
            "inner join Orders o on r.roomNo = o.room.roomNo " +
            "inner join UserMember u on o.userMember.userNo = u.userNo where o.reservationStatus != '예약대기' and r.store.ownerMember.ownerNo =:ownerNo")
    List<Store> findAllUserReservation(@Param("ownerNo") Integer ownerNo);
}
