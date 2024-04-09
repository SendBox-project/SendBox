package com.zerock.sendbox.service.board;

import com.zerock.sendbox.dto.board.BoardDTO;
import com.zerock.sendbox.dto.board.BoardWriteRequestDTO;
import com.zerock.sendbox.dto.board.PageRequestDTO;
import com.zerock.sendbox.dto.board.PageResultDTO;

public interface BoardService {


    Integer saveBoard(BoardWriteRequestDTO boardWriteRequestDTO, Integer boardNo);

    Integer saveBoard(BoardWriteRequestDTO boardWriteRequestDTO, String mail);
}
