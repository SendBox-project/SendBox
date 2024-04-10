package com.zerock.sendbox.service.user;

import com.zerock.sendbox.entity.Orders;
import com.zerock.sendbox.entity.UserMember;
import com.zerock.sendbox.repository.OrdersRepository;
import com.zerock.sendbox.repository.UserMemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class UserMypageService {
    @Autowired
    UserMemberRepository userMemberRepository;

    @Autowired
    OrdersRepository ordersRepository;

    //개인 정보 수정폼
    public UserMember findByUserId(String userId) {
        return userMemberRepository.findByUserId(userId);
    }

    //개인 정보 수정
    public UserMember updateInfo(UserMember userMember) {
        return userMemberRepository.save(userMember);
    }

    //개인 정보 탈퇴
    @Transactional
    public Integer deleteInfo(Integer userNo) {
       return userMemberRepository.deleteByUserNo(userNo);
    }

    //예약 내역 리스트 조회
    public List<Orders> findAllReservation(Integer userNo, Pageable pageable) {
        return ordersRepository.findAllReservation(userNo);
    }
}
