package com.zerock.sendbox.service.board;

import com.zerock.sendbox.dto.board.*;
import com.zerock.sendbox.entity.Inquary;
import com.zerock.sendbox.entity.UserMember;
import com.zerock.sendbox.repository.InquaryRepository;
import com.zerock.sendbox.repository.ReplyRepository;
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
public class InquaryServiceImpl implements InquaryService{

    private final InquaryRepository inquaryRepository;

    private final ReplyRepository replyRepository;

    @Override
    public Integer register(InquaryDTO dto) {
        log.info(dto);

        Inquary inquary = dtoToEntity(dto);

        inquaryRepository.save(inquary);

        return inquary.getInquaryNo();
    }

    @Override
    public PageResultDTO2<InquaryDTO, Object[]> getList(PageRequestDTO2 pageRequestDTO2) {

        log.info(pageRequestDTO2);

        Function<Object[], InquaryDTO> fn = (en -> entityToDTO((Inquary)en[0], (UserMember)en[1], (Long) en[2]));

        Page<Object[]> result = inquaryRepository.getInquaryWithReplyCount(
                pageRequestDTO2.getPageable(Sort.by("inquaryNo").descending()));

        return new PageResultDTO2<>(result, fn);

    }

    @Override
    public InquaryDTO get(Integer inquaryNo) {
        Object result = inquaryRepository.getInquaryByInquaryNo(inquaryNo);

        Object[] arr = (Object[])result;

        return entityToDTO((Inquary)arr[0], (UserMember)arr[1], (Long)arr[2]);
    }

    @Transactional
    @Override
    public void removeWithReplies(Integer inquaryNo) {

        replyRepository.deleteByInquaryNo(inquaryNo);

        replyRepository.deleteById(inquaryNo);
    }

    @Transactional
    @Override
    public void modify(InquaryDTO inquaryDTO) {
        Inquary inquary = inquaryRepository.getReferenceById(inquaryDTO.getInquaryNo());

        if(inquary != null) {
            inquary.changeTitle(inquaryDTO.getTitle());
            inquary.changeContent(inquaryDTO.getContent());

            inquaryRepository.save(inquary);
        }
    }

}
