package com.zerock.sendbox.controller.user;

import com.zerock.sendbox.dto.user.UserMypageDto;
import com.zerock.sendbox.entity.UserMember;
import com.zerock.sendbox.service.user.UserMypageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserMypageController {
    @Autowired
    UserMypageService userMypageService;

    //개인 정보 수정폼
    @GetMapping("/mypageForm")
    public String mypageForm(Model model) {
        String userId = "alsrl";
        UserMember userMember = userMypageService.mypageForm(userId); //UserMember 타입으로 결과값을 받는다.
        model.addAttribute("user", userMember); // userMember "userMember"에 담아서 프론트로 보내준다.(화면에 DTO에 담겨진 데이터를 동적으로 뿌려주려고)
        System.out.println("userMember: " + userMember);
        return "user/userMypageForm";
    }

    //개인 정보 수정
    @PostMapping("/updateInfo")
    public String updateUser(@ModelAttribute UserMypageDto userMypageDto) { // 프론트에서 "개인 정보 수정 완료" 버튼을 누르면 그 값을 @ModelAttribute로 받으면 된다.
        Integer result = userMypageService.updateInfo(userMypageDto);
        if (result == 1) {
            return "user/modify_account_ok";
        } else {
            return "user/modify_account_ng";
        }
    }

    //개인 정보 탈퇴
    @PostMapping("/deleteInfo")
    public String deleteUser(@ModelAttribute UserMypageDto userMypageDto) {
        Integer result = 1;
        if (result == 1) {
            return "user/home";
        } else {
            return "user/userMypageForm";
        }

    }
}
