package com.zerock.sendbox.repository;

import com.zerock.sendbox.entity.StorageRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageRoomRepository extends JpaRepository<StorageRoom, Integer> {
}
