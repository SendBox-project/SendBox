package com.zerock.sendbox.service.board;

import com.zerock.sendbox.dto.board.BoardWriteRequestDTO;
import com.zerock.sendbox.entity.AdminMember;
import com.zerock.sendbox.entity.Board;
import com.zerock.sendbox.repository.AdminMemberRepository;
import com.zerock.sendbox.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final AdminMemberRepository adminMemberRepository;
    private final BoardRepository boardRepository;

    @Override
    public Integer saveBoard(BoardWriteRequestDTO boardWriteRequestDTO, Integer boardNo) {
        return null;
    }

    @Override
    public Integer saveBoard(BoardWriteRequestDTO boardWriteRequestDTO, String mail) {
        AdminMember adminMember = (AdminMember) adminMemberRepository.findByEmail(mail).orElseThrow(() -> new UsernameNotFoundException("정보가 존재하지않습니다."));
        Board result = Board.builder()
                .title(boardWriteRequestDTO.getTitle())
                .content(boardWriteRequestDTO.getContent())
                .adminMember(adminMember)
                .build();
        boardRepository.save(result);

        return result.getBoardNo();
    }


}
