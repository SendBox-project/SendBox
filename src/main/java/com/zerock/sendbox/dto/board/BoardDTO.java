package com.zerock.sendbox.dto.board;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

    private Integer boardNo;

    private Integer adminNo;

    private String title;

    private String content;

    @Column(length = 2, columnDefinition = "char(2)")
    private String boardType;

    private LocalDateTime regDate;

    private LocalDateTime modDate;
}
