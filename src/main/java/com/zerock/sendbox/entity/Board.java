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

    @ManyToOne
    @JoinColumn(name = "admin_no")
    private AdminMember adminMember;

    @Column
    private String title;

    @Column
    private String content;

    @Column(length = 2, columnDefinition = "char(2)")
    private String boardType;
}
