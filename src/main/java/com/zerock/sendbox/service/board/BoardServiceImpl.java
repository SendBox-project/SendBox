package com.zerock.sendbox.service.board;

import com.zerock.sendbox.dto.board.BoardDTO;
import com.zerock.sendbox.dto.board.PageRequestDTO;
import com.zerock.sendbox.dto.board.PageResultDTO;
import com.zerock.sendbox.entity.AdminMember;
import com.zerock.sendbox.entity.Board;
import com.zerock.sendbox.repository.AdminAnswerRepository;
import com.zerock.sendbox.repository.AdminMemberRepository;
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
    private final AdminMemberRepository adminMemberRepository;

    @Override
    public Integer register(BoardDTO dto) {
        log.info(dto);

        // BoardDTO를 이용하여 Board 엔티티 생성
        Board board = dtoToEntity(dto);

        // AdminMember 엔티티를 가져옴
        AdminMember adminMember = adminMemberRepository.findById(dto.getAdminNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 AdminMember를 찾을 수 없습니다. AdminNo: " + dto.getAdminNo()));

        // Board 엔티티에 AdminMember를 설정
        board.setAdminMember(adminMember);

        // Board 엔티티 저장
        boardRepository.save(board);

        return board.getBoardNo();
    }

    @Override
    public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
        log.info(pageRequestDTO);

        Function<Object[], BoardDTO> fn = (en -> entityToDTO((Board) en[0], (AdminMember) en[1], (Long) en[2]));

        Page<Object[]> result = boardRepository.getBoardWithAdminAnswerCount(
                pageRequestDTO.getPageable(Sort.by("boardNo").descending()));

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public BoardDTO get(Integer boardNo) {
        Object result = boardRepository.getBoardByBoardNo(boardNo);

        Object[] arr = (Object[]) result;

        return entityToDTO((Board) arr[0], (AdminMember) arr[1], (Long) arr[2]);
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
        Board board = boardRepository.findById(boardDTO.getBoardNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 Board를 찾을 수 없습니다. BoardNo: " + boardDTO.getBoardNo()));

        board.changeTitle(boardDTO.getTitle());
        board.changeContent(boardDTO.getContent());

        boardRepository.save(board);
    }

    private Board dtoToEntity(BoardDTO dto) {
        return Board.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .thumbnail(dto.getThumbnail())
                .boardType(dto.getBoardType())
                .build();
    }

    private BoardDTO entityToDTO(Board board, AdminMember adminMember, Long AdminAnswerCount) {
        return BoardDTO.builder()
                .boardNo(board.getBoardNo())
                .title(board.getTitle())
                .content(board.getContent())
                .regDate(board.getRegDate())
                .modDate(board.getModDate())
                .writerMail(adminMember.getMail())
                .writerName(adminMember.getName())
                .AdminAnswerCount(AdminAnswerCount.intValue())
                .build();
    }
}
