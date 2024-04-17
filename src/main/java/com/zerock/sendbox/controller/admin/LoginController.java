package com.zerock.sendbox.controller.admin;

import com.zerock.sendbox.dto.admin.LoginDTO;
import com.zerock.sendbox.entity.AdminMember;
import com.zerock.sendbox.service.admin.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Log4j2
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login_form")
    public String login() {
        log.info("login.........");

        return "admin/member/login_form";
    }

    @PostMapping("/login_form")
    public String login(@ModelAttribute LoginDTO loginDTO, RedirectAttributes redirectAttributes) {
        AdminMember adminMember = loginService.authenticate(loginDTO);

        if (adminMember != null) {
            // 로그인 성공 시
            // 여기에서 세션 등에 관련 정보를 저장하거나 JWT 토큰을 발급하는 등의 작업을 수행할 수 있습니다.
            log.info("Login success for admin: {}", adminMember.getAdminId());
            // 로그인 성공 후 리다이렉트할 URL
            return "admin/home";
        } else {
            // 로그인 실패 시
            redirectAttributes.addFlashAttribute("error", "아이디 또는 비밀번호가 올바르지 않습니다.");
            return "admin/member/create_account_form";
        }

    }
}
