package com.zerock.sendbox.controller.admin;

import com.zerock.sendbox.dto.admin.LoginDTO;
import com.zerock.sendbox.entity.AdminMember;
import com.zerock.sendbox.service.admin.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/logout")
    public String logout() {
        log.info("Logout success");

        // 로그아웃 처리
        SecurityContextHolder.clearContext();

        // 로그아웃 후 리다이렉트할 URL
        return "admin/member/login_form";
    }

    @GetMapping("/reset_password_form")
    public String resetPassword() {
        return "admin/member/reset_password_form";
    }

    @PostMapping("/reset_password_form")
    public String resetPassword(@RequestParam("mail") String mail, RedirectAttributes redirectAttributes) {
        loginService.sendPasswordResetMail(mail);
        redirectAttributes.addFlashAttribute("successMessage", "비밀번호 재설정 이메일이 전송되었습니다.");
        return "admin/member/login_form"; // 비밀번호 재설정 이메일을 보낸 후 로그인 폼으로 이동
    }


}
