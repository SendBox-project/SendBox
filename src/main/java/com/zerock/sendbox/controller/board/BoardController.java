package com.zerock.sendbox.controller.board;

import com.zerock.sendbox.dto.board.BoardDTO;
import com.zerock.sendbox.dto.board.PageRequestDTO;
import com.zerock.sendbox.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    public String registerPost(BoardDTO dto, RedirectAttributes redirectAttributes, @RequestParam("file") MultipartFile file){

        log.info("dto..." + dto);

        if (!file.isEmpty()) {
            try {
                // 업로드된 파일의 이름을 가져옴
                String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                // 파일 저장 디렉토리 설정
                String uploadDir = "./uploads/";

                // 파일 저장 디렉토리가 없으면 생성
                Path uploadPath = Paths.get(uploadDir);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                // 파일 경로 설정
                Path filePath = uploadPath.resolve(fileName);
                // 파일을 지정된 경로로 복사
                Files.copy(file.getInputStream(), filePath);

                // 업로드된 파일의 경로를 DTO에 설정
                dto.setFilePath(filePath.toString());
            } catch (IOException e) {
                e.printStackTrace();
                // 파일 처리 중 에러가 발생하면 적절히 처리합니다.
            }
        }

        //새로 추가된 엔티티의 번호
        Integer boardNo = boardService.register(dto);

        log.info("boardNo: " + boardNo);

        redirectAttributes.addFlashAttribute("msg", boardNo);

        return "redirect:/admin/board/noticeList";
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

        return "redirect:/admin/board/noticeList";
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

        return "redirect:/admin/board/read";
    }



}
