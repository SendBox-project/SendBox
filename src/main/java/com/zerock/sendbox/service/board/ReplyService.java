package com.zerock.sendbox.service.board;

import com.zerock.sendbox.dto.board.ReplyDTO;
import com.zerock.sendbox.entity.Inquary;
import com.zerock.sendbox.entity.Reply;

import java.util.List;

public interface ReplyService {

    Integer register(ReplyDTO replyDTO);

    List<ReplyDTO> getList(Integer inquaryNo);

    void modify(ReplyDTO replyDTO);

    void remove(Integer replyNo);

    default Reply dtoToEntity(ReplyDTO replyDTO) {
        Inquary inquary = Inquary.builder().inquaryNo(replyDTO.getReplyNo()).build();

        Reply reply = Reply.builder()
                .replyNo(replyDTO.getReplyNo())
                .content(replyDTO.getContent())
                .replyer(replyDTO.getReplyer())
                .inquary(inquary)
                .build();
        return reply;
    }

    default ReplyDTO entityToDTO(Reply reply) {
        ReplyDTO dto = ReplyDTO.builder()
                .replyNo(reply.getReplyNo())
                .content(reply.getContent())
                .replyer(reply.getReplyer())
                .regDate(reply.getRegDate())
                .modDate(reply.getModDate())
                .build();
        return dto;
    }
}
