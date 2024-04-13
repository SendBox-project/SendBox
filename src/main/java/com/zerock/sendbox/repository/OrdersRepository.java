package com.zerock.sendbox.repository;

import com.zerock.sendbox.entity.Orders;
import com.zerock.sendbox.entity.UserMember;
import feign.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {

    //예약 내역 리스트 조회
    @Query("select o,r,s from Orders o inner join Room r on o.room.roomNo = r.roomNo " +
            "inner join Store s on r.store.storeNo = s.storeNo where o.paymentNo is not null and o.userNo =:userNo")
    List<Orders> findAllReservation(@Param("userNo") Integer userNo);

    //장바구니 리스트 조회
    @Query("select o,r,s from Orders o inner join Room r on o.room.roomNo = r.roomNo " +
            "inner join Store s on  r.store.storeNo = s.storeNo where o.paymentNo is null and o.userNo =:userNo")
    List<Orders> findAllCartList(@Param("userNo") Integer userNo);

    //orderNo에 맞는 장바구니 품목 한 개 가져오기
    Orders findByOrderNo(Integer orderNo);

    //장바구니 단건 삭제
    Integer deleteByOrderNo(Integer orderNo);

    //장바구니 전체 삭제
    @Modifying // delete 쿼리 사용 시 필요함 그래야 인식 가능?
    @Query("delete from Orders o where o.paymentNo is null and o.userNo =:userNo")
    Integer deleteAllCart(@Param("userNo") Integer userNo);

}
