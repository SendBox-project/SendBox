package com.zerock.sendbox.dto.store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReviewDTO {
    private Integer reviewNo;
    private Integer storeNo;
    private Integer userNo;
    private String content;
    private LocalDateTime regDate;
}
