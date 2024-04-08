package com.zerock.sendbox.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Inquary extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column
    private Integer inquaryNo;

    @Column(name = "u_m_no")
    private Integer uMNo;

    @Column
    private String inquaryTitle;

    @Column
    private String inquaryContent;
}
