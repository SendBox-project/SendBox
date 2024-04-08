package com.zerock.sendbox.repository;

import com.zerock.sendbox.entity.OwnerMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerMemberRepository extends JpaRepository<OwnerMember, Integer> {
}
