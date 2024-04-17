package com.zerock.sendbox.controller.owner;

import com.zerock.sendbox.entity.Orders;
import com.zerock.sendbox.entity.OwnerMember;
import com.zerock.sendbox.entity.Store;
import com.zerock.sendbox.service.owner.OwnerMypageService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping("/owner")
public class OwnerMypageController {
    @Autowired
    OwnerMypageService ownerMypageService;

    @Autowired
    PasswordEncoder passwordEncoder;

    //오너 정보 수정폼 >> 단순 화면 조회
    @GetMapping("/mypageForm")
    public String mypageForm(Model model) {
        String ownerId = "tkddk";
        OwnerMember ownerMember = ownerMypageService.findByOwnerId(ownerId); //OwnerMember 타입으로 결과값을 받는다.
        model.addAttribute("owner", ownerMember);
        return "owner/member/modify_account_form";
    }

    //오너 정보 수정
    @PostMapping("/updateInfo")
    public String updateOwner(@ModelAttribute OwnerMember ownerMember) { // 프론트에서 "오너 정보 수정 완료" 버튼을 누르면 그 값을 @ModelAttribute로 받으면 된다.
        System.out.println("ownerMember = " + ownerMember);
        //비밀번호 암호화
        ownerMember.setPassword(passwordEncoder.encode(ownerMember.getPassword()));
        OwnerMember result =  ownerMypageService.updateInfo(ownerMember);
        if(result != null) {
            return "owner/member/modify_account_ok";
        } else {
            return "owner/member/modify_account_ng";
        }

    }

    //오너 정보 탈퇴
    @PostMapping("/deleteInfo")
    public String deleteOwner(@ModelAttribute OwnerMember ownerMember, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // String userId = auth.getName();
        Integer ownerNo = 1;
        OwnerMember owner = ownerMypageService.findByOwnerNo(ownerNo);

        if(passwordEncoder.matches(ownerMember.getPassword(), owner.getPassword())) { // 실제 입력한 비번, DB에 비번 비교
            Integer result = ownerMypageService.deleteInfo(ownerNo);
            response.setContentType("text/html; charset=UTF-8"); //응답의 content type을 설정, "text/html"은 전송될 데이터의 종류가 HTML임을 나타냄
            PrintWriter writer = response.getWriter(); //이 PrintWriter를 통해 HTML 코드나 다른 텍스트 데이터를 클라이언트로 전송
            writer.println("<script>alert('탈퇴가 완료되었습니다.');</script>");
            writer.flush();
            session.invalidate();
            return "owner/home";
        } else {
            model.addAttribute("owner", owner); // 비번 틀렸을때 다시 modify_account_form 프론트 화면으로 가야하니까 값을 뿌려준다.
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.println("<script>alert('비밀번호가 틀렸습니다.');</script>");
            writer.flush();
            return "owner/member/modify_account_form"; // 원래 리다이렉트를 하면 model.~ 안해도 되지만 alert창과 redirect 같이 사용이 안됨!
        }

    }

    //빈곽 예약 내역 조회
    @GetMapping("/reservationList")
    public String reservationForm() {
        return "owner/member/reservationList";
    }

    //사업자의 예약 내역 리스트 조회
    @GetMapping("/reservationListAjax")
    public String reservationList(Model model, @PageableDefault(size = 10, sort = "startDate", direction = Sort.Direction.DESC)Pageable pageable) {
        Integer ownerNo = 1;
        int page = (pageable.getPageNumber() == 0) ? 0 :(pageable.getPageNumber() - 1); //화면상에선 1부터 시작하지만 자바는 0부터 시작해서 1-1= 0이고 이게 실제 페이지론 1 페이지
        pageable = PageRequest.of(page,  pageable.getPageSize(), pageable.getSort()); //위에 page 변수에 넣은 값을 다시 pageable에 할당
        List<Store> reservationList = ownerMypageService.findAllUserReservation(ownerNo); // 매개변수를 보내고 리턴값을 받고 이 리턴값을 왼쪽에 저장
        int start = (int)pageable.getOffset(); // 예약리스트의 첫 행 >> 현재 페이지의 오프셋(시작 인덱스)을 반환 후, 이를 정수로 캐스팅하여 start 변수에 저장합니다.
        int end = Math.min((start + pageable.getPageSize()), reservationList.size()); // 예약리스트의 마지막 행, 두개 중 최소값을 취함 >> 한 페이지에 예약리스트에 담긴거의 개수를 표시하려고 하는 거

        List<Store> pageContent = reservationList.subList(start, end); // start 인덱스부터 end-1 인덱스까지의 항목을 포함합니다. 따라서 pageContent에는 현재 페이지에 해당하는 항목들이 포함되어 있습니다.(size를 10으로 제한해서 최대 10까지 나옴)
        Page<Store> orders = new PageImpl<>(pageContent, pageable, reservationList.size()); // 현재 페이지 항목, pageable, 예약리스트에 담긴 개수를 orders에 담는다 !
        model.addAttribute("reservations",orders);
        System.out.println("store.getContent() = " + orders.getContent());
        return "owner/member/reservationListAjax";

    }


}


