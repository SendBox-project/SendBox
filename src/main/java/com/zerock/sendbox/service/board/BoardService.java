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

    PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO);

    @Transactional
    void removeWithAdminAnswer(Integer boardNo);

    void modify(BoardDTO boardDTO);


    default Board dtoToEntity(BoardDTO dto) {
        AdminMember adminMember = AdminMember.builder().mail(dto.getWriterMail()).build();

        Board board = Board.builder()
                .boardNo(dto.getBoardNo())
                .adminMember(adminMember)

                .title(dto.getTitle())
                .content(dto.getContent())
                .thumbnail(dto.getThumbnail())
//                .writer(adminMember)
                .boardType(dto.getBoardType())
                .build();
        return board;
    }

    default BoardDTO entityToDTO(Board board, AdminMember adminMember, Long AdminAnswerCount) {

        BoardDTO boardDTO = BoardDTO.builder()
                .boardNo(board.getBoardNo())
                .title(board.getTitle())
                .content(board.getContent())
                .regDate(board.getRegDate())
                .modDate(board.getModDate())
                .writerMail(adminMember.getMail())
                .writerName(adminMember.getName())
                .AdminAnswerCount(AdminAnswerCount.intValue())
                .build();

        return boardDTO;

    }


}
