package com.zerock.sendbox.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "inquary")
public class Reply extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer replyNo;

    @Column
    private String replyer;

    @Column
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Inquary inquary;

}
