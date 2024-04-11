package com.zerock.sendbox.controller.board;

import com.zerock.sendbox.dto.board.BoardDTO;
import com.zerock.sendbox.dto.board.PageRequestDTO;
import com.zerock.sendbox.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/board/")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {

        log.info("list................" + pageRequestDTO);

        model.addAttribute("result", boardService.getList(pageRequestDTO));
    }

    @GetMapping("/register")
    public void register(){
        log.info("register get...");
    }

    @PostMapping("/register")
    public String registerPost(BoardDTO dto, RedirectAttributes redirectAttributes){

        log.info("dto..." + dto);
        //새로 추가된 엔티티의 번호
        Integer boardNo = boardService.register(dto);

        log.info("boardNo: " + boardNo);

        redirectAttributes.addFlashAttribute("msg", boardNo);

        return "redirect:/board/list";
    }

}
