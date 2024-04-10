package com.zerock.sendbox.repository;

import com.zerock.sendbox.entity.UserMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMemberRepository extends JpaRepository<UserMember, Integer> {

    //개인 정보 수정폼
    UserMember findByUserId(String userId);

    //개인 정보 탈퇴 >> Integer(리턴 타입)에 값(1 혹은 0)이 담겨서 다시 반환한다.
    Integer deleteByUserNo(Integer userNo);

}
