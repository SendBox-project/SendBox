package com.zerock.sendbox;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class MainController {
//유리 테스트용입니다
    @GetMapping("/home")
    public String selectImageList(Model model) {
        model.addAttribute("title", "");

        return "user/home";
    }

}