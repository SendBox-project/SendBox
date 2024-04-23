package com.zerock.sendbox.controller.owner;

import com.zerock.sendbox.dto.owner.OwnerLoginDTO;
import com.zerock.sendbox.entity.OwnerMember;
import com.zerock.sendbox.service.owner.OwnerLoginService;
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
@RequestMapping("/owner")
@RequiredArgsConstructor
@Log4j2
public class OwnerLoginController {

    private final OwnerLoginService ownerLoginService;

    // 로그인 페이지
    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "exception", required = false) String exception, Model model) {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);

        return "/owner/member/login_form";
    }

//    @GetMapping("/ownerlogin")
//    public String login() {
//        log.info("login.........");
//
//        return "/owner/member/login_form";
//    }

//    @PostMapping("/ownerloginconfirm")
//    public String login(@ModelAttribute OwnerLoginDTO ownerLoginDTO, RedirectAttributes redirectAttributes,
//                        HttpServletRequest request, HttpServletResponse response) {
//        OwnerMember ownerMember = ownerLoginService.authenticate(ownerLoginDTO);
//
//        if (ownerMember != null) {
//            HttpSession session = request.getSession();
//            session.setAttribute("ownerId", ownerMember.getOwnerId());
//
//            String ownerIdCookieValue = ownerMember.getOwnerId().replaceAll("\\s+", "_");
//            Cookie cookie = new Cookie("ownerId", ownerIdCookieValue);
//            cookie.setMaxAge(60 * 60 * 24);
//            response.addCookie(cookie);
//
//            log.info("Login success for owner: {}", ownerMember.getOwnerId());
//            return "owner/home";
//        } else {
//            redirectAttributes.addFlashAttribute("error", "아이디 또는 비밀번호가 올바르지 않습니다.");
//            return "owner/member/login_ng";
//        }
//    }

//    @GetMapping("/logout")
//    public String logout(HttpServletRequest request, HttpServletResponse response) {
//        log.info("Logout success");
//
//        HttpSession session = request.getSession(false);
//        if (session != null) {
//            session.invalidate();
//        }
//
//        Cookie[] cookies = request.getCookies();
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if ("ownerId".equals(cookie.getName())) {
//                    cookie.setMaxAge(0);
//                    response.addCookie(cookie);
//                    break;
//                }
//            }
//        }
//        return "owner/member/login_form";
//    }

    @GetMapping("/reset_password_form")
    public String resetPassword() {
        return "owner/member/reset_password_form";
    }

    @PostMapping("/reset_password_form")
    public String resetPassword(@RequestParam("ownerId") String ownerId,
                                @RequestParam("mail") String mail,
                                RedirectAttributes redirectAttributes) {
        OwnerMember ownerMember = ownerLoginService.findByOwnerIdAndMail(ownerId, mail);

        if (ownerMember != null) {
            ownerLoginService.sendPasswordResetMail(mail);
            redirectAttributes.addFlashAttribute("successMessage", "비밀번호 재설정 이메일이 전송되었습니다.");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "아이디 또는 이메일 주소가 올바르지 않습니다.");
        }

        return "owner/member/login_form";
    }

    @GetMapping("/forgot_id")
    public String showForgotIdForm() {
        return "owner/member/forgot_id_form";
    }

    @PostMapping("/forgot_id")
    public String processForgotIdForm(@RequestParam("mail") String mail, RedirectAttributes redirectAttributes) {
        OwnerMember ownerMember = ownerLoginService.findByMail(mail);

        if (ownerMember != null) {
            String userId = ownerMember.getOwnerId();
            String subject = "Your ID Recovery";
            String text = "Your ID is: " + userId;
            ownerLoginService.sendMail(mail, subject, text);
            log.info("아이디 찾기: 사용자 아이디 {}fmf {}로 전송", userId, mail);
            redirectAttributes.addFlashAttribute("successMessage", "가입하신 이메일 주소로 아이디를 전송했습니다.");

        } else {
            log.info("아이디 찾기: 해당 이메일 주소와 연결된 사용자가 없습니다.");
            redirectAttributes.addFlashAttribute("errorMessage", "해당 이메일 주소와 연결된 사용자가 없습니다.");

        }
        return "owner/member/login_form";
    }

}
