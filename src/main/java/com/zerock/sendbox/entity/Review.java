package com.zerock.sendbox.entity;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "u_m_no")
    private Integer uMNo;

    @Column
    private Integer stNo;

    @Column
    private String reviewContent;

    @Column
    private Date regDate;
}
