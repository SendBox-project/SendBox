package com.zerock.sendbox.repository;

import com.zerock.sendbox.entity.Store;
import feign.Param;
import org.springframework.data.domain.Pageable;
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

  //검색기능
    /*@Query("SELECT s FROM Store s JOIN FETCH s.rooms r WHERE r.storeName LIKE '%:storeName%'")*/
   /* @Query("SELECT s,r FROM Store s inner join Room r on  s.storeNo = r.store.storeNo WHERE s.storeName LIKE concat ('%', :storeName, '%')")*/
    List<Store> findAllByStoreNameContaining(@Param("storeName") String storeName);

    //검색어가 주어지지 않으면 전체 리스트 조회
    @Query("select s from Store s")
    List<Store> findAllStores(Pageable pageable);

  
    //사업자의 예약 내역 리스트 조회
    @Query("select s,r,o,u from Store s inner join  Room r on s.storeNo = r.store.storeNo " +
            "inner join Orders o on r.roomNo = o.room.roomNo " +
            "inner join UserMember u on o.userMember.userNo = u.userNo where o.reservationStatus != '예약대기' and r.store.ownerMember.ownerNo =:ownerNo")
    List<Store> findAllUserReservation(@Param("ownerNo") Integer ownerNo);
}
