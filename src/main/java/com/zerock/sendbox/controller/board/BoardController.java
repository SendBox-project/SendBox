package com.zerock.sendbox.controller.board;

import com.zerock.sendbox.dto.board.BoardDTO;
import com.zerock.sendbox.dto.board.PageRequestDTO;
import com.zerock.sendbox.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    BoardService boardService;

    @GetMapping("/noticeList")
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

        return "board/noticeList";
    }

    @GetMapping("/modify")
    public String modifyForm(Integer boardNo, PageRequestDTO pageRequestDTO, Model model) {
        log.info("modify get... boardNo: " + boardNo);
        BoardDTO boardDTO = boardService.get(boardNo); // boardNo를 사용하여 해당 게시물 정보를 가져옴
        model.addAttribute("dto", boardDTO);
        model.addAttribute("boardNo", boardNo);
        model.addAttribute("pageRequestDTO", pageRequestDTO); // 페이지 요청 DTO를 모델에 추가
        return "board/modify"; // 수정 폼 페이지로 이동
    }

    @PostMapping("/modify")
    public String modify(BoardDTO dto, RedirectAttributes redirectAttributes) {
        log.info("modify post... boardNo: " + dto.getBoardNo());
        boardService.modify(dto); // 게시물 수정
        redirectAttributes.addFlashAttribute("msg", "수정되었습니다."); // 수정 완료 메시지를 리다이렉트 속성에 추가
        return "redirect:/board/read?boardNo=" + dto.getBoardNo(); // 수정 후 게시물 상세 페이지로 리다이렉트
    }

    @PostMapping("/remove")
    public String remove(Integer boardNo, RedirectAttributes redirectAttributes) {
        log.info("remove... boardNo: " + boardNo);
        boardService.removeWithAdminAnswer(boardNo); // 관리자 답변과 함께 게시물 삭제
        redirectAttributes.addFlashAttribute("msg", "삭제되었습니다."); // 삭제 완료 메시지를 리다이렉트 속성에 추가
        return "redirect:/board/noticeList"; // 삭제 후 게시물 목록 페이지로 리다이렉트
    }



}
