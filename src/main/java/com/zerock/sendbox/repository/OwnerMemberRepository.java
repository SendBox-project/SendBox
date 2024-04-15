package com.zerock.sendbox.repository;

import com.zerock.sendbox.entity.OwnerMember;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerMemberRepository extends JpaRepository<OwnerMember, Integer> {

    //오너 정보 수정폼
    OwnerMember findByOwnerId(String ownerId);

    //오너 정보 조회
    OwnerMember findByOwnerNo(Integer ownerNo);

    //오너 정보 탈퇴 >> Integer(리턴 타입)에 값(1 혹은 0)이 담겨서 다시 반환한다.
    @Modifying // @쿼리 어노테이션(jpql쿼리, native쿼리)을 통해서 작성된 insert, update, delete 쿼리에서 사용됨!
    @Query("update OwnerMember set deleteYn = 'Y' where ownerNo =:ownerNo")
    Integer deleteInfo(@Param("ownerNo") Integer ownerNo);   // @Param을 통해 ownerNo를 보내서 쿼리 실행 후 리턴!
}
