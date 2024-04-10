package com.zerock.sendbox.controller.user;

import com.zerock.sendbox.entity.Orders;
import com.zerock.sendbox.entity.UserMember;
import com.zerock.sendbox.service.user.UserMypageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserMypageController {
    @Autowired
    UserMypageService userMypageService;

    @Autowired
    PasswordEncoder passwordEncoder;

    //개인 정보 수정폼
    @GetMapping("/mypageForm")
    public String mypageForm(Model model) {
        String userId = "alsrl";
        UserMember userMember = userMypageService.findByUserId(userId); //UserMember 타입으로 결과값을 받는다.
        model.addAttribute("user", userMember); // userMember를 "user"에 담아서 프론트로 보내준다.(화면에 DTO에 담겨진 데이터를 동적으로 뿌려주려고)
        System.out.println("userMember: " + userMember);
        return "user/member/modify_account_form";
    }

    //개인 정보 수정
    @PostMapping("/updateInfo")
    public String updateUser(@ModelAttribute UserMember userMember) { // 프론트에서 "개인 정보 수정 완료" 버튼을 누르면 그 값을 @ModelAttribute로 받으면 된다.
        //비밀번호 암호화
        userMember.setPassword(passwordEncoder.encode(userMember.getPassword()));
        UserMember result = userMypageService.updateInfo(userMember);
        if (result != null) {
            return "user/member/modify_account_ok";
        } else {
            return "user/member/modify_account_ng";
        }
    }

    //개인 정보 탈퇴
    @GetMapping("/deleteInfo")
    public String deleteUser() {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String userId = auth.getName();
        Integer userNo = 1;
        Integer result = userMypageService.deleteInfo(userNo);
        System.out.println("result = " + result);
        if (result == 1) {
            return "user/home";
        } else {
            return "user/userMypageForm";
        }
    }

    //예약 내역 리스트 조회
    @GetMapping("/reservationList")
    public String reservationList(Model model, @PageableDefault(size = 10, sort = "startDate", direction = Sort.Direction.ASC)Pageable pageable) {
        Integer userNo = 1;
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, pageable.getPageSize(), pageable.getSort());
/*        System.out.println("pageable = " + pageable);
        System.out.println("userNo = " + userNo);*/
        List<Orders> reservationList = userMypageService.findAllReservation(userNo, pageable);
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), reservationList.size());

        List<Orders> pageContent = reservationList.subList(start, end);
        Page<Orders> orders = new PageImpl<>(pageContent, pageable, reservationList.size());
/*        System.out.println("size = " + orders.getSize());
        System.out.println("sort = " + orders.getSort());
        System.out.println("elements = " + orders.getTotalElements());
        System.out.println("totalpages = " + orders.getTotalPages());
        System.out.println("number = " + orders.getNumber());
        System.out.println("element = " + orders.getNumberOfElements());
        System.out.println("pageable = " + orders.getPageable());*/

        model.addAttribute("reservations", orders);

        return "user/rentalHistory";
    }
}
