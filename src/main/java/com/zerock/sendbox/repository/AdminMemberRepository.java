package com.zerock.sendbox.repository;

import com.zerock.sendbox.entity.AdminMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminMemberRepository extends JpaRepository<AdminMember, Integer> {
    Optional<Object> findByEmail(String mail);
}
