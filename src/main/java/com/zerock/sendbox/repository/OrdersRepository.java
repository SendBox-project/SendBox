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
            "inner join Store s on r.store.storeNo = s.storeNo where o.paymentNo is not null and o.userNo =:userNo")
    List<Orders> findAllReservation(@Param("userNo") Integer userNo);

    //장바구니 리스트 조회

    @Query("select o,r,s from Orders o inner join Room r on o.room.roomNo = r.roomNo " +
            "inner join Store s on  r.store.storeNo = s.storeNo where o.paymentNo is null and o.userNo =:userNo")
    List<Orders> findAllCartList(@Param("userNo") Integer userNo);

    //장바구니 단건 및 전체 삭제
    @Query("delete from Orders o where o.orderNo in :orderNo") // Orders 테이블에서 주문 번호(orderNo)가 주어진 목록(:orders)에 포함되는 경우 해당 주문을 삭제
    Integer deleteCart(@Param("orderNo") List<Integer> orderNo); // "orderNo"에 1, 2, 3인 애들이 있다면 쿼리를 실행해서 이 애들을 전부 삭제하고 3개가 삭제 된 것을 리턴 값 3으로 보낸다!
}
