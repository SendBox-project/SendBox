package com.zerock.sendbox.repository;

import com.zerock.sendbox.entity.AdminAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminAnswerRepository extends JpaRepository<AdminAnswer, Integer>{

    @Modifying
    @Query("delete from AdminAnswer r where r.board.boardNo =: boardNo")
    void deleteByBoardNo(Integer BoardNo);
}
