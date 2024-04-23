package com.zerock.sendbox.controller.user;

import com.zerock.sendbox.dto.user.UserLoginDTO;
import com.zerock.sendbox.entity.UserMember;
import com.zerock.sendbox.service.user.UserLoginService;
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
@RequestMapping("/user")
@RequiredArgsConstructor
@Log4j2
public class UserLoginController {

    private final UserLoginService userLoginService;

    @GetMapping("/userlogin")
    public String login() {
        log.info("login.........");

        return "/user/member/login_form";
    }

    @PostMapping("/userloginconfirm")
    public String login(@ModelAttribute UserLoginDTO userLoginDTO, RedirectAttributes redirectAttributes,
                        HttpServletRequest request, HttpServletResponse response) {
        UserMember userMember = userLoginService.authenticate(userLoginDTO);

        if (userMember != null) {
            HttpSession session = request.getSession();
            session.setAttribute("userId", userMember.getUserId());

            String userIdCookieValue = userMember.getUserId().replaceAll("\\s+", "_");
            Cookie cookie = new Cookie("userId", userIdCookieValue);
            cookie.setMaxAge(60 * 60 * 24);
            response.addCookie(cookie);

            log.info("Login success for user: {}", userMember.getUserId());
            return "user/home";
        } else {
            redirectAttributes.addFlashAttribute("error", "아이디 또는 비밀번호가 올바르지 않습니다.");
            return "user/member/login_ng";
        }
    }

    @GetMapping("/reset_password_form")
    public String resetPassword() {
        return "user/member/reset_password_form";
    }

    @PostMapping("/reset_password_form")
    public String resetPassword(@RequestParam("userId") String userId,
                                @RequestParam("mail") String mail,
                                RedirectAttributes redirectAttributes) {
        UserMember userMember = userLoginService.findByUserIdAndMail(userId, mail);

        if (userMember != null) {
            userLoginService.sendPasswordResetMail(mail);
            redirectAttributes.addFlashAttribute("successMessage", "비밀번호 재설정 이메일이 전송되었습니다.");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "아이디 또는 이메일 주소가 올바르지 않습니다.");
        }
        return "user/member/login_form";
    }

    @GetMapping("/forgot_id")
    public String showForgotIdForm() {
        return "/user/member/forgot_id_form";
    }

    @PostMapping("/forgot_id")
    public String processForgotIdForm(@RequestParam("mail") String mail, RedirectAttributes redirectAttributes) {
        UserMember userMember = userLoginService.findByMail(mail);

        if (userMember != null) {
            String userId = userMember.getUserId();
            String subject = "Your ID Recovery";
            String text = "Your ID is: " + userId;
            userLoginService.sendMail(mail, subject, text);
            log.info("아이디 찾기: 사용자 아이디 {}fmf {}로 전송", userId, mail);
            redirectAttributes.addFlashAttribute("successMessage", "가입하신 이메일 주소로 아이디를 전송했습니다.");
        } else {
            log.info("아이디 찾기: 해당 이메일 주소와 연결된 사용자가 없습니다.");
            redirectAttributes.addFlashAttribute("errorMessage", "해당 이메일 주소와 연결된 사용자가 없습니다.");
        }
        return "user/member/login_form";
    }

}
