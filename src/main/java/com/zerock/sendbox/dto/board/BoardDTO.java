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

    private Integer cnt;

    private String title;

    private String content;

    private String thumbnail;

    private String writerMail; //작성자의 이메일(id)

    private String writerName; //작성자의 이름

    @Column(length = 3, columnDefinition = "char(3)")
    private String boardType;

    private int replyCount;

    private LocalDateTime regDate;

    private LocalDateTime modDate;
}
