package com.zerock.sendbox.dto.board;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class PageResultDTO<DTO, EN> {

    private List<DTO> dtoList;
    private int totalPage;
    private int currentPage;
    private int pageSize;
    private int startPage;
    private int endPage;
    private boolean hasPrevious;
    private boolean hasNext;
    private List<Integer> pageNumbers;

    public PageResultDTO(Page<EN> result, Function<EN, DTO> converter) {
        this.dtoList = result.stream().map(converter).collect(Collectors.toList());
        this.totalPage = result.getTotalPages();
        Pageable pageable = result.getPageable();
        this.currentPage = pageable.getPageNumber() + 1;
        this.pageSize = pageable.getPageSize();
        calculatePageNumbers();
    }

    private void calculatePageNumbers() {
        int totalPages = getTotalPage();
        int currentPage = getCurrentPage();
        int pageSize = getPageSize();

        if (totalPages <= 10) {
            startPage = 1;
            endPage = totalPages;
        } else {
            int halfPages = 10 / 2;
            int pageOffset = Math.max(currentPage - halfPages, 1);
            startPage = pageOffset;
            endPage = Math.min(pageOffset + 10 - 1, totalPages);
        }

        hasPrevious = currentPage > 1;
        hasNext = currentPage < totalPages;

        pageNumbers = IntStream.rangeClosed(startPage, endPage)
                .boxed()
                .collect(Collectors.toList());
    }
}
