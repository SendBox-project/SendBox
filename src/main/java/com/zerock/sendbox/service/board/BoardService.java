package com.zerock.sendbox.service.board;

import com.zerock.sendbox.dto.board.BoardDTO;
import com.zerock.sendbox.dto.board.PageRequestDTO;
import com.zerock.sendbox.dto.board.PageResultDTO;
import com.zerock.sendbox.entity.AdminMember;
import com.zerock.sendbox.entity.Board;
import org.springframework.transaction.annotation.Transactional;

public interface BoardService {

    Integer register(BoardDTO dto);

    BoardDTO get(Integer boardNo);

    BoardDTO entityToDTO(Board board, AdminMember adminMember, Long AdminAnswerCount);

    PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO);

    @Transactional
    void removeWithAdminAnswer(Integer boardNo);

    void modify(BoardDTO boardDTO);





}
