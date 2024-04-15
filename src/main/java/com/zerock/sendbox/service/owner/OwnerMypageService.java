package com.zerock.sendbox.service.owner;

import com.zerock.sendbox.entity.OwnerMember;
import com.zerock.sendbox.entity.Store;
import com.zerock.sendbox.repository.OwnerMemberRepository;
import com.zerock.sendbox.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OwnerMypageService {
    @Autowired
    OwnerMemberRepository ownerMemberRepository;

    @Autowired
    StoreRepository storeRepository;

    //오너 정보 수정폼
    public OwnerMember findByOwnerId(String ownerId) {
        return ownerMemberRepository.findByOwnerId(ownerId);
    }

    //오너 정보 수정
    public OwnerMember updateInfo(OwnerMember ownerMember) {
        return ownerMemberRepository.save(ownerMember);
    }

    //오너 정보 조회

    public OwnerMember findByOwnerNo(Integer ownerNo) {
        return ownerMemberRepository.findByOwnerNo(ownerNo);
    }

    //오너 정보 탈퇴
    @Transactional // 탈퇴 성공 시 commit 실패 시 rollback으로 바로 저장하게 유도
    public Integer deleteInfo(Integer ownerNo) {
        return ownerMemberRepository.deleteInfo(ownerNo);
    }

    //사업자의 예약 내역 리스트 조회
    public List<Store> findAllUserReservation(Integer ownerNo, Pageable pageable) {
        return storeRepository.findAllUserReservation(ownerNo);
    }
}
