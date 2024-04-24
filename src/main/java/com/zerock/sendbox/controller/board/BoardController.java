package com.zerock.sendbox.controller.board;

import com.zerock.sendbox.dto.board.BoardDTO;
import com.zerock.sendbox.dto.board.PageRequestDTO;
import com.zerock.sendbox.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/noticeList")
    public void list(PageRequestDTO pageRequestDTO, Model model) {

        log.info("list................" + pageRequestDTO);

        model.addAttribute("board", boardService.getList(pageRequestDTO));
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


        redirectAttributes.addFlashAttribute("msg", "게시물이 성공적으로 등록되었습니다. 게시물 번호: " + boardNo);

        return "redirect:/board/noticeList";
    }


    @GetMapping({"/read", "/modify"})
    public void read(@ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO, Integer boardNo, Model model) {
        log.info("boardNo: " + boardNo);

        BoardDTO boardDTO = boardService.get(boardNo);

        log.info(boardDTO);

        model.addAttribute("dto", boardDTO);
    }

    @PostMapping("/remove")
    public String remove(Integer boardNo, RedirectAttributes redirectAttributes) {
        log.info("boardNo" + boardNo);

        boardService.removeWithAdminAnswer(boardNo);

        redirectAttributes.addFlashAttribute("msg", boardNo);

        return "board/noticeList";
    }

    @PostMapping("/modify")
    public String modify(BoardDTO dto, @ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes) {
        log.info("post modify...................");
        log.info("dto" + dto);

        boardService.modify(dto);

        redirectAttributes.addAttribute("page",pageRequestDTO.getPage());
        redirectAttributes.addAttribute("type",pageRequestDTO.getType());
        redirectAttributes.addAttribute("keyword",pageRequestDTO.getKeyword());

        redirectAttributes.addAttribute("boardNo",dto.getBoardNo());

        return "board/read";
    }

}
