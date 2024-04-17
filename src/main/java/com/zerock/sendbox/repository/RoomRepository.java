package com.zerock.sendbox.repository;

import com.zerock.sendbox.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

    // storeNo를 통해 한 업체가 갖고 있는 room 리스트를 가져옴
    List<Room> findByStoreStoreNo(Integer storeNo);
}
