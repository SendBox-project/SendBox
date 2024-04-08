package com.zerock.sendbox.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StorageRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roomNo;

    @Column
    private String stNo;

    @Column
    private Integer roomSize;

    @Column
    private Integer roomPrice;

    @Column
    private Integer roomRemain;
}
