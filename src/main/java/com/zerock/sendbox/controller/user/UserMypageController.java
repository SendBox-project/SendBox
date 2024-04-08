package com.zerock.sendbox.controller.user;

import com.zerock.sendbox.entity.UserMember;
import com.zerock.sendbox.service.user.UserMypageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserMypageController {
    @Autowired
    UserMypageService userMypageService;

    //개인 정보 수정폼
    @GetMapping("/mypageForm")
    public String mypageForm(Model model) {
        String userId = "alsrl";
        UserMember userMember = userMypageService.mypageForm(userId); //UserMypageResDto 타입으로 결과값을 받는다.
        model.addAttribute("userMember", userMember); // userMypageResDto를 "userInfo"에 담아서 프론트로 보내준다.(화면에 DTO에 담겨진 데이터를 동적으로 뿌려주려고)
        System.out.println("userMember: " + userMember);
        return "user/userMypageForm";
    }
}
