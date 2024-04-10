package com.zerock.sendbox.repository;

import com.zerock.sendbox.entity.Orders;
import com.zerock.sendbox.entity.UserMember;
import feign.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {

    //예약 내역 리스트 조회
    @Query("select o,r,s from Orders o inner join Room r on o.room.roomNo = r.roomNo " +
            "inner join Store s on r.store.storeNo = s.storeNo where o.userNo =:userNo")
    List<Orders> findAllReservation(@Param("userNo") Integer userNo);
}
