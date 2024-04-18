package com.zerock.sendbox.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "writer")
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer boardNo;

    @Column
    private Integer adminNo;

    @Column
    private Integer cnt;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private String thumbnail;
//    이미지 파일 등록용임 썸네일 아님!

    @ManyToOne (fetch = FetchType.LAZY)
    private AdminMember writer;

    @Column(length = 3, columnDefinition = "char(3)")
    private String boardType;


    public void changeTitle(String title){
        this.title = title;
    }

    public void changeContent(String content){
        this.content = content;
    }

}
