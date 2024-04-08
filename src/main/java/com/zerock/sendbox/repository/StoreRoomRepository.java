package com.zerock.sendbox.repository;

import com.zerock.sendbox.entity.StoreRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRoomRepository extends JpaRepository<StoreRoom, Integer> {
}
