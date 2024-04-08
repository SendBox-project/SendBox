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
    private String boardTitle;

    @Column
    private String boardContent;

    @Column(name = "a_m_no")
    private Integer aMNo;

    @Column(length = 2, columnDefinition = "char(2)")
    private String boardType;
}
