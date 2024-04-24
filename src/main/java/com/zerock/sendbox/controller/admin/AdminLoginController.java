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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Log4j2
public class AdminLoginController {

    private final LoginService loginService;


    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "exception", required = false) String exception, Model model) {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);

        return "/admin/member/login_form";
    }

//    @GetMapping("/adminlogin")
//    public String login() {
//        log.info("login.........");
//
//        return "admin/member/login_form";
//    }
//
//    @PostMapping("/adminloginconfirm")
//    public String login(@ModelAttribute LoginDTO loginDTO, RedirectAttributes redirectAttributes,
//                        HttpServletRequest request, HttpServletResponse response) {
//        AdminMember adminMember = loginService.authenticate(loginDTO);
//
//        if (adminMember != null) {
//            // 로그인 성공 시 세션 및 쿠키 설정
//            HttpSession session = request.getSession();
//            session.setAttribute("adminId", adminMember.getAdminId());
//
//            // 쿠키 생성 및 설정
//            String adminIdCookieValue = adminMember.getAdminId().replaceAll("\\s+", "_"); // 공백 대체
//            Cookie cookie = new Cookie("adminId", adminIdCookieValue);
//            cookie.setMaxAge(60 * 60 * 24); // 쿠키 유효기간 설정 (예: 24시간)
//            response.addCookie(cookie);
//
//            log.info("Login success for admin: {}", adminMember.getAdminId());
//            return "admin/home";
//        } else {
//            redirectAttributes.addFlashAttribute("error", "아이디 또는 비밀번호가 올바르지 않습니다.");
//            return "admin/member/login_ng";
//        }
//    }
//
//    @GetMapping("/logout")
//    public String logout(HttpServletRequest request, HttpServletResponse response) {
//        log.info("Logout success");
//
//        // 세션 비우기
//        HttpSession session = request.getSession(false);
//        if (session != null) {
//            session.invalidate();
//        }
//
//        // 쿠키 삭제
//        Cookie[] cookies = request.getCookies();
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if ("adminId".equals(cookie.getName())) {
//                    cookie.setMaxAge(0);
//                    response.addCookie(cookie);
//                    break;
//                }
//            }
//        }
//
//        return "admin/member/login_form";
//    }


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