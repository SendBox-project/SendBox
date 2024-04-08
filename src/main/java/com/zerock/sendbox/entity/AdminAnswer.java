package com.zerock.sendbox.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class AdminAnswer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column
    private Integer anNo;

    @Column
    private String anTitle;

    @Column
    private String anContent;

    @Column
    private Integer aMNo;

    @Column
    private Integer inquaryNo;

}
