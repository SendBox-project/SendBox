package com.zerock.sendbox.repository;

import com.zerock.sendbox.dto.user.UserMypageDto;
import com.zerock.sendbox.entity.UserMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMemberRepository extends JpaRepository<UserMember, Integer> {

    //개인 정보 수정폼
    UserMember findByUserId(String userId);
    
    //개인 정보 수정
    Integer save(UserMypageDto userMypageDto);
}
