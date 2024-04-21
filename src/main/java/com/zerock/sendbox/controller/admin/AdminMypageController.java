package com.zerock.sendbox.controller.admin;


import com.zerock.sendbox.entity.AdminMember;
import com.zerock.sendbox.service.admin.AdminMypageService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/admin")
public class AdminMypageController {
    @Autowired
    AdminMypageService adminMypageService;

    @Autowired
    PasswordEncoder passwordEncoder;

    // 개인 정보 수정폼 >> 단순 화면 조회
    @GetMapping("/mypageForm")
    public String mypageForm(Model model) {
        String adminId = "alsrlalsrl97";
        /*String adminId = "messi";*/
        AdminMember adminMember = adminMypageService.findByAdminId(adminId);
        model.addAttribute("admin", adminMember);
        return "admin/member/modify_account_form";
    }

    // 개인 정보 수정
    @PostMapping("/updateInfo")
    public String updateAdmin(@ModelAttribute AdminMember adminMember) { // 프론트에서 "개인 정보 수정 완료" 버튼을 누르면 그 값을 @ModelAttribute로 받으면 된다.
        //비밀번호 암호화
        adminMember.setPassword(passwordEncoder.encode(adminMember.getPassword()));
        AdminMember result = adminMypageService.updateInfo(adminMember);
        if(result != null) {
            return "admin/member/modify_account_ok";
        } else {
            return "admin/member/modify_account_ng";
        }
    }

    //개인 정보 탈퇴
    @PostMapping("/deleteInfo")
    public String deleteInfo(@ModelAttribute AdminMember adminMember, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // String userId = auth.getName();

        Integer adminNo = 1;
        AdminMember admin = adminMypageService.findByAdminNo(adminNo);

        if(passwordEncoder.matches(adminMember.getPassword(), admin.getPassword())) {
            Integer result = adminMypageService.deleteInfo(adminNo);
            response.setContentType("text/html; charset=UTF-8"); //응답의 content type을 설정, "text/html"은 전송될 데이터의 종류가 HTML임을 나타냄
            PrintWriter writer = response.getWriter(); //이 PrintWriter를 통해 HTML 코드나 다른 텍스트 데이터를 클라이언트로 전송
            writer.println("<script>alert('탈퇴가 완료되었습니다.');</script>");
            writer.flush();
            session.invalidate();
            return "admin/home";
        } else {
            model.addAttribute("admin", admin); // 비번 틀렸을때 다시 modify_account_form 프론트 화면으로 가야하니까 값을 뿌려준다.
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.println("<script>alert('비밀번호가 틀렸습니다.');</script>");
            writer.flush();
            return "admin/member/modify_account_form"; //  리다이렉트를 하면 model.~ 안해도 되지만 alert창과 redirect 같이 사용이 안됨!
        }
    }

}
