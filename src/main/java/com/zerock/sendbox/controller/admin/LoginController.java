package com.zerock.sendbox.controller.admin;

import com.zerock.sendbox.dto.admin.LoginDTO;
import com.zerock.sendbox.entity.AdminMember;
import com.zerock.sendbox.service.admin.LoginService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Log4j2
public class LoginController {

    private final LoginService loginService;


    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "exception", required = false) String exception, Model model) {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);

        return "admin/member/login_form";
    }

    //패스워드 재설정
    @GetMapping("/reset_password_form")
    public String resetPassword() {
        return "admin/member/reset_password_form";
    }

    @PostMapping("/reset_password_form")
    public String resetPassword(@RequestParam("adminId") String adminId,
                                @RequestParam("mail") String mail,
                                RedirectAttributes redirectAttributes) {
        // 아이디와 이메일로 사용자를 찾습니다.
        AdminMember adminMember = loginService.findByAdminIdAndMail(adminId, mail);

        if (adminMember != null) {
            // 사용자가 존재하는 경우, 비밀번호 재설정 이메일을 보냅니다.
            loginService.sendPasswordResetMail(mail);
            redirectAttributes.addFlashAttribute("successMessage", "비밀번호 재설정 이메일이 전송되었습니다.");
        } else {
            // 사용자가 존재하지 않는 경우, 적절한 메시지를 전송합니다.
            redirectAttributes.addFlashAttribute("errorMessage", "아이디 또는 이메일 주소가 올바르지 않습니다.");
        }
        return "admin/member/login_form"; // 비밀번호 재설정 이메일을 보낸 후 로그인 폼으로 이동
    }

    @GetMapping("/forgot_id")
    public String showForgotIdForm() {
        return "admin/member/forgot_id_form";
    }

    @PostMapping("/forgot_id")
    public String processForgotIdForm(@RequestParam("mail") String mail, RedirectAttributes redirectAttributes) {
        // 이메일 주소를 이용하여 사용자를 찾습니다.
        AdminMember adminMember = loginService.findByMail(mail);

        if (adminMember != null) {
            // 사용자가 존재하는 경우, 해당 이메일 주소로 아이디를 전송합니다.
            String userId = adminMember.getAdminId();
            String subject = "Your ID Recovery";
            String text = "Your ID is: " + userId;
            loginService.sendMail(mail, subject, text);
            log.info("아이디 찾기: 사용자 아이디 {}를 {}로 전송", userId, mail);
            redirectAttributes.addFlashAttribute("successMessage", "가입하신 이메일 주소로 아이디를 전송했습니다.");
        } else {
            // 사용자가 존재하지 않는 경우, 적절한 메시지를 전송합니다.
            log.info("아이디 찾기: 해당 이메일 주소와 연결된 사용자가 없습니다.");
            redirectAttributes.addFlashAttribute("errorMessage", "해당 이메일 주소와 연결된 사용자가 없습니다.");
        }

        return "admin/member/login_form";
    }


}