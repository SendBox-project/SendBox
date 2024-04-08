package com.zerock.sendbox.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roomNo;

    @Column
    private Integer storeNo;

    @Column
    private Integer size;

    @Column
    private Integer price;

    @Column
    private Integer remain;
}
