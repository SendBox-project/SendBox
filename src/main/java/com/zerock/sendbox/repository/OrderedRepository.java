package com.zerock.sendbox.repository;

import com.zerock.sendbox.entity.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedRepository extends JpaRepository<Ordered, Integer> {
}
