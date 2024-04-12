package com.zerock.sendbox.service.board;

import com.zerock.sendbox.dto.board.InquaryDTO;
import com.zerock.sendbox.dto.board.PageRequestDTO2;
import com.zerock.sendbox.dto.board.PageResultDTO2;
import com.zerock.sendbox.entity.Inquary;
import com.zerock.sendbox.entity.UserMember;

public interface InquaryService {

    Integer register(InquaryDTO dto);

    InquaryDTO get(Integer inquaryNo);

    PageResultDTO2<InquaryDTO, Object[]> getList(PageRequestDTO2 pageRequestDTO2);

    void removeWithReplies(Integer inquaryNo);

    default Inquary dtoToEntity(InquaryDTO dto) {

        UserMember userMember = UserMember.builder().mail(dto.getMemberMail()).build();

        Inquary inquary = Inquary.builder()
                .userNo(dto.getUserNo())
                .inquaryNo(dto.getInquaryNo())
                .title(dto.getTitle())
                .content(dto.getContent())
                .member(userMember)
                .build();
        return inquary;
    }

    default InquaryDTO entityToDTO(Inquary inquary, UserMember userMember, Long replyCount) {

        InquaryDTO inquaryDTO = InquaryDTO.builder()
                .userNo(inquary.getUserNo())
                .inquaryNo(inquary.getInquaryNo())
                .title(inquary.getTitle())
                .content(inquary.getContent())
                .regDate(inquary.getRegDate())
                .modDate(inquary.getModDate())
                .memberMail(userMember.getMail())
                .memberName(userMember.getName())
                .replyCount(replyCount.intValue())
                .build();
        return inquaryDTO;
    }

}
