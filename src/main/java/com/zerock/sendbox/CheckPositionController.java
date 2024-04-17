package com.zerock.sendbox;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CheckPositionController {



    //로그인 할 때 직책을 확인하는 페이지
    @GetMapping("/checkPositionLogin")
    public String checkPositionLogin() {
        return "include/check_position_login";
    }
    //오너 로그인
    @GetMapping("/ownerlogin")
    public String ownerLogin() {
        return "owner/member/login_form";
    }
    //유저 로그인
    @GetMapping("/userlogin")
    public String userLogin() {
        return "user/member/login_form";
    }
    //어드민 로그인
    @GetMapping("/adminlogin")
    public String adminLogin() {
        return "admin/member/login_form";
    }


    //회원가입 할 때 직책을 확인하는 페이지
    @GetMapping("/checkPositionRegister")
    public String checkPositionRegister() {
        return "include/check_position_create_account";
    }

    //어드민 회원가입 약관
    @GetMapping("/adminterms")
    public String adminterms() {
        return "admin/member/account_terms";
    }
    //오너 회원가입 약관
    @GetMapping("/ownerterms")
    public String ownerTerms() {
        return "owner/member/account_terms";
    }
    //유저 회원가입 약관
    @GetMapping("/userterms")
    public String userTerms() {
        return "user/member/account_terms";
    }

}
