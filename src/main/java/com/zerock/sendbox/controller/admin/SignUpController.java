package com.zerock.sendbox.controller.admin;

import com.zerock.sendbox.dto.admin.SignUpDTO;
import com.zerock.sendbox.service.admin.SignUpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Log4j2
public class SignUpController {

    private final SignUpService signUpService;

    @GetMapping("/create_account_form")
    public String createAdminMember() {
        log.info("sign..........");

        return "admin/member/create_account_form";
    }

    @PostMapping("/create_account_form")
    public String createAdminMember(SignUpDTO signUpDTO, RedirectAttributes redirectAttributes) {
        if(signUpService.isIdDuplicated(signUpDTO.getAdminId())) {
            redirectAttributes.addFlashAttribute("message", "이미 사용 중인 아이디입니다.");
            return "admin/member/create_account_form";
        }

        signUpService.join(signUpDTO);
        redirectAttributes.addFlashAttribute("message", "회원가입이 완료되었습니다.");
        return "admin/member/login_form";
    }
}
