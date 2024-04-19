package com.zerock.sendbox.controller.admin;

import com.zerock.sendbox.entity.AdminMember;
import com.zerock.sendbox.entity.OwnerMember;
import com.zerock.sendbox.entity.UserMember;
import com.zerock.sendbox.service.admin.AdminManageMentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminManageMentController {
    @Autowired
    AdminManageMentService adminManageMentService;

    //빈곽 유저 리스트 조회
    @GetMapping("/userList")
    public String userList() {
        return "admin/member/userList";
    }

    //admin의 모든 유저 리스트 조회
    @GetMapping("/userListAjax")
    public String userListAjax(Model model, @PageableDefault(size = 10, sort = "startDate", direction = Sort.Direction.DESC) Pageable pageable) {
        String N;
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1); // 화면상에선 1부터 시작하지만 자바는 0부터 시작해서 1-1= 0이고 이게 실제 페이지론 1 페이지
        pageable = PageRequest.of(page, pageable.getPageSize(), pageable.getSort()); //위에 page 변수에 넣은 값을 다시 pageable에 할당
        List<UserMember> userList = adminManageMentService.findAllByDeleteYn("N"); // 매개변수를 보내고 리턴값을 받고 이 리턴값을 왼쪽에 저장
        int start = (int) pageable.getOffset(); // 유저명단의 첫 행 >> 현재 페이지의 오프셋(시작 인덱스)을 반환 후, 이를 정수로 캐스팅하여 start 변수에 저장합니다.
        int end = Math.min((start + pageable.getPageSize()), userList.size()); // 유저명단의 마지막 행, 두개 중 최소값을 취함 >> 한 페이지에 장바구니 담긴거의 개수를 표시하려고 하는 거

        List<UserMember> pageContent = userList.subList(start, end); // start 인덱스부터 end-1 인덱스까지의 항목을 포함합니다. 따라서 pageContent에는 현재 페이지에 해당하는 항목들이 포함되어 있습니다.(size를 10으로 제한해서 최대 10까지 나옴)
        Page<UserMember> userMember = new PageImpl<>(pageContent, pageable, userList.size()); // 현재 페이지 항목, pageable, 장바구니에 담긴 개수를 userMember에 담는다 !

        /*System.out.println("userMember = " + userMember.getContent());*/
        model.addAttribute("userMember", userMember); //페이지로 감싼 userMember가 아래 프론트로 간다.

        return "admin/member/userListAjax";
    }

    //빈곽 오너 리스트 조회
    @GetMapping("/ownerList")
    public String ownerList() {
        return "admin/member/ownerList";
    }

    //admin의 모든 오너 리스트 조회
    @GetMapping("/ownerListAjax")
    public String ownerListAjax(Model model, @PageableDefault(size = 10, sort = "startDate", direction = Sort.Direction.DESC) Pageable pageable) {
        String N;
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, pageable.getPageSize(), pageable.getSort());
        List<OwnerMember> ownerList = adminManageMentService.findByDeleteYn("N");
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), ownerList.size());

        List<OwnerMember> pageContent = ownerList.subList(start, end);
        Page<OwnerMember> ownerMember = new PageImpl<>(pageContent, pageable, ownerList.size());

        /*System.out.println("ownerMember = " + ownerMember.getContent());*/
        model.addAttribute("ownerMember", ownerMember);

        return "admin/member/ownerListAjax";
    }

    //빈곽 매니저 리스트 조회
    @GetMapping("/managerList")
    public String managerList() {
        return "admin/member/managerList";
    }

    //모든 매니저 리스트 조회
    @GetMapping("/managerListAjax")
    public String managerListAjax(Model model, @PageableDefault(size = 10, sort = "startDate", direction = Sort.Direction.DESC) Pageable pageable) {
        String N;
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, pageable.getPageSize(), pageable.getSort());
        List<AdminMember> adminList = adminManageMentService.findManager("N");
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), adminList.size());

        List<AdminMember> pageContent = adminList.subList(start, end);
        Page<AdminMember> adminMember = new PageImpl<>(pageContent, pageable, adminList.size());

        model.addAttribute("adminMember", adminMember);

        return "admin/member/managerListAjax";
    }

}
