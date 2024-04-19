package com.zerock.sendbox.controller.owner;

import com.zerock.sendbox.dto.owner.OwnerSignUpDTO;
import com.zerock.sendbox.service.owner.OwnerSignUpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/owner")
@RequiredArgsConstructor
@Log4j2
public class OwnerSignUpController {

    private final OwnerSignUpService ownerSignUpService;

    @GetMapping("/create_account_form")
    public String createOwnerMember() {
        log.info("sign.......");

        return "/owner/member/create_account_form";
    }

    @PostMapping("/create_account_form")
    public String createOwnerMember(OwnerSignUpDTO signUpDTO, RedirectAttributes redirectAttributes) {
        if(ownerSignUpService.isIdDuplicated(signUpDTO.getOwnerId())) {
            redirectAttributes.addFlashAttribute("message", "이미 사용 중인 아이디입니다.");
            return "/owner/member/create_account_form";
        }

        ownerSignUpService.join(signUpDTO);
        redirectAttributes.addFlashAttribute("message", "회원가입이 완료되었습니다.");
        return "owner/member/login_form";
    }
}