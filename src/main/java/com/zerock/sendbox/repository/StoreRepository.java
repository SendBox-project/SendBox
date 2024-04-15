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
    //사업자의 예약 내역 리스트 조회
    @Query("select s,r,o,u from Store s inner join  Room r on s.storeNo = r.store.storeNo " +
            "inner join Orders o on r.roomNo = o.room.roomNo " +
            "inner join UserMember u on o.userMember.userNo = u.userNo where o.reservationStatus != '예약대기' and r.store.ownerMember.ownerNo =:ownerNo")
    List<Store> findAllUserReservation(@Param("ownerNo") Integer ownerNo);
}
