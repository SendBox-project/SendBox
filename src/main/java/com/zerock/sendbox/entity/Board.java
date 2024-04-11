package com.zerock.sendbox.entity;


import com.zerock.sendbox.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer boardNo;

    @Column
    private Integer adminNo;

    @Column
    private String title;

    @Column
    private String content;

    @Column(length = 2, columnDefinition = "char(2)")
    private String boardType;

    @Column
    private Integer cnt;

    @Column
    private String thumbnail;
}
