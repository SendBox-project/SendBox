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
public class
    UserMypageController {
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
            return "user/modify_account_form";
        }
    }

    //예약 내역 리스트 조회
    @GetMapping("/reservationList")
    public String reservationList(Model model, @PageableDefault(size = 10, sort = "startDate", direction = Sort.Direction.DESC)Pageable pageable) {
        Integer userNo = 1;
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1); //page 번호가 0이면 page 뱐수에 0 설정, 그 외에는 페이지 번호에서 1을 뺀 값을 page 변수에 할당
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

        return "user/member/reservationList";
    }

    //장바구니 리스트 조회
    @GetMapping("/cartList")
    public String cartList(Model model, @PageableDefault(size = 10, sort = "startDate", direction = Sort.Direction.DESC)Pageable pageable) {
        Integer userNo = 1;
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1); // 화면상에선 1부터 시작하지만 자바는 0부터 시작해서 1-1= 0이고 이게 실제 페이지론 1 페이지
        pageable = PageRequest.of(page, pageable.getPageSize(), pageable.getSort());//위에 page 변수에 넣은 값을 다시 pageable에 할당
        List<Orders> cartList = userMypageService.findAllCartList(userNo, pageable); // 매개변수를 보내고 리턴값을 받고 이 리턴값을 왼쪽에 저장
        int start = (int) pageable.getOffset(); // 장바구니의 첫 행 >> 현재 페이지의 오프셋(시작 인덱스)을 반환 후, 이를 정수로 캐스팅하여 start 변수에 저장합니다.
        int end = Math.min((start + pageable.getPageSize()), cartList.size()); // 장바구니의 마지막 행, 두개 중 최소값을 취함 >> 한 페이지에 장바구니 담긴거의 개수를 표시하려고 하는 거

        List<Orders> pageContent = cartList.subList(start, end); // start 인덱스부터 end-1 인덱스까지의 항목을 포함합니다. 따라서 pageContent에는 현재 페이지에 해당하는 항목들이 포함되어 있습니다.(size를 10으로 제한해서 최대 10까지 나옴)
        Page<Orders> orders = new PageImpl<>(pageContent, pageable, cartList.size()); // 현재 페이지 항목, pageable, 장바구니에 담긴 개수를 order스에 담는다 !
        model.addAttribute("cartList", orders); // 페이지로 감싼 orders가 아래 프론트로 간다.

        return "user/member/cartList";
    }

}
