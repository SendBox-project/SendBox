package com.zerock.sendbox.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class ReservationController {
    //빈곽 전체 예약 내역 리스트 조회
    @GetMapping("/reservationList")
    public String reservationList() {
        return "admin/member/reservationList";
    }

}
