package com.zerock.sendbox.repository;

import com.zerock.sendbox.entity.AdminMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminMemberRepository extends JpaRepository<AdminMember, Integer> {

    AdminMember findByAdminIdAndMail(String adminId, String mail);


    AdminMember findByMail(String mail);

    AdminMember findByAdminId(String adminId);
}
