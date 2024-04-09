package com.zerock.sendbox.service.user;

import com.zerock.sendbox.dto.user.UserMypageDto;
import com.zerock.sendbox.entity.UserMember;
import com.zerock.sendbox.repository.UserMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMypageService {
    @Autowired
    UserMemberRepository userMemberRepository;

    //개인 정보 수정폼
    public UserMember mypageForm(String userId) {
        UserMember userMember = userMemberRepository.findByUserId(userId);
        return userMember;
    }

    //개인 정보 수정
    public Integer updateInfo(UserMypageDto userMypageDto) {
        return userMemberRepository.save(userMypageDto);
    }
}
