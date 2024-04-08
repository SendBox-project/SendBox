package com.zerock.sendbox.repository;

import com.zerock.sendbox.entity.UserMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMemberRepository extends JpaRepository<UserMember, Integer> {
}
