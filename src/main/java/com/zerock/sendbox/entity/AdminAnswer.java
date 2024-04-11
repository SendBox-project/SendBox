package com.zerock.sendbox.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "board")
public class AdminAnswer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer answerNo;

    @Column
    private Integer adminNo;

    @Column
    private Integer inquaryNo;

    @Column
    private String replyer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    @Column
    private String content;




}
