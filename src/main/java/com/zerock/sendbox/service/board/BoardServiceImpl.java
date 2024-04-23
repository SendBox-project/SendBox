package com.zerock.sendbox.service.board;

import com.zerock.sendbox.dto.board.BoardDTO;
import com.zerock.sendbox.dto.board.PageRequestDTO;
import com.zerock.sendbox.dto.board.PageResultDTO;
import com.zerock.sendbox.entity.*;
import com.zerock.sendbox.repository.AdminAnswerRepository;
import com.zerock.sendbox.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final AdminAnswerRepository adminAnswerRepository;

    @Override
    public Integer register(BoardDTO dto) {

        log.info(dto);

        AdminMember adminMember = AdminMember.builder().adminNo(dto.getAdminNo()).build();
        Board board = Board.builder()
                .boardNo(dto.getBoardNo())
                .title(dto.getTitle())
                .content(dto.getContent())
                .thumbnail(dto.getThumbnail())
                .boardType(dto.getBoardType())
                .adminMember(adminMember)
                .build();
        return boardRepository.save(board).getBoardNo();
    }

    @Override
    public BoardDTO entityToDTO(Board board, AdminMember adminMember, Long AdminAnswerCount) {

        BoardDTO boardDTO = BoardDTO.builder()
                .boardNo(board.getBoardNo())
                .title(board.getTitle())
                .content(board.getContent())
                .regDate(board.getRegDate())
                .modDate(board.getModDate())
                .writerName(adminMember.getName())
                .AdminAnswerCount(AdminAnswerCount.intValue())
                .build();

        return boardDTO;

    }

    @Override
    public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {

        log.info(pageRequestDTO);

        Function<Object[], BoardDTO> fn = (en -> entityToDTO((Board)en[0],(AdminMember) en[1],(Long)en[2]));

        Page<Object[]> result = boardRepository.getBoardWithAdminAnswerCount(
                pageRequestDTO.getPageable(Sort.by("boardNo").descending())  );



        return new PageResultDTO<>(result, fn);
    }

    @Override
    public BoardDTO get(Integer boardNo) {
        Object result = boardRepository.getBoardByBoardNo(boardNo);

        Object[] arr = (Object[])result;

        return entityToDTO((Board)arr[0], (AdminMember)arr[1], (Long)arr[2]);
    }

    @Transactional
    @Override
    public void removeWithAdminAnswer(Integer boardNo) {
        adminAnswerRepository.deleteByBoardNo(boardNo);

        boardRepository.deleteById(boardNo);
    }

    @Transactional
    @Override
    public void modify(BoardDTO boardDTO) {
        Board board = boardRepository.getReferenceById(boardDTO.getBoardNo());

        if(board != null) {
            board.changeTitle(boardDTO.getTitle());
            board.changeContent(boardDTO.getContent());

            boardRepository.save(board);
        }
    }
}
