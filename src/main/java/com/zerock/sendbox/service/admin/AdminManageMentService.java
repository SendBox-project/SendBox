package com.zerock.sendbox.service.admin;

import com.zerock.sendbox.entity.AdminMember;
import com.zerock.sendbox.entity.OwnerMember;
import com.zerock.sendbox.entity.UserMember;
import com.zerock.sendbox.repository.AdminMemberRepository;
import com.zerock.sendbox.repository.OwnerMemberRepository;
import com.zerock.sendbox.repository.UserMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminManageMentService {
    @Autowired
    UserMemberRepository userMemberRepository;

    @Autowired
    OwnerMemberRepository ownerMemberRepository;

    @Autowired
    AdminMemberRepository adminMemberRepository;


    //admin의 모든 유저 리스트 조회
    public List<UserMember> findAllByDeleteYn(String deleteYn) {
        return userMemberRepository.findAllByDeleteYn(deleteYn);
    }

    //admin의 모든 오너 리스트 조회
    public List<OwnerMember> findByDeleteYn(String deleteYn) {
        return ownerMemberRepository.findAllByDeleteYn(deleteYn);
    }

    //모든 매니저 리스트 조회
    public List<AdminMember> findManager(String deleteYn) {
        return adminMemberRepository.findAllByDeleteYn(deleteYn);
    }
}
