package com.zerock.sendbox.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Review{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer reviewNo;

    @Column
    private Integer userNo;

    @Column
    private Integer storeNo;

    @Column
    private String content;

    @Column
    private LocalDateTime regDate;
}
