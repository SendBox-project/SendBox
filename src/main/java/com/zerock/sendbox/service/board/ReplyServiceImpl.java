package com.zerock.sendbox.service.board;

import com.zerock.sendbox.dto.board.ReplyDTO;
import com.zerock.sendbox.entity.Inquary;
import com.zerock.sendbox.entity.Reply;
import com.zerock.sendbox.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService{

    private final ReplyRepository replyRepository;

    @Override
    public Integer register(ReplyDTO replyDTO) {
        Reply reply = dtoToEntity(replyDTO);

        replyRepository.save(reply);

        return reply.getReplyNo();
    }

    @Override
    public List<ReplyDTO> getList(Integer inquaryNo) {
        List<Reply> result = replyRepository
                .getRepliesByInquaryOrderByReplyNo(Inquary.builder().inquaryNo(inquaryNo).build());
        return result.stream().map(reply -> entityToDTO(reply)).collect(Collectors.toList());
    }

    @Override
    public void modify(ReplyDTO replyDTO) {
        Reply reply = dtoToEntity(replyDTO);

        replyRepository.save(reply);
    }

    @Override
    public void remove(Integer replyNo) {
        replyRepository.deleteById(replyNo);
    }
}
