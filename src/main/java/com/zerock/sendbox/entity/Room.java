package com.zerock.sendbox.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "order")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roomNo;

    @OneToOne(mappedBy = "room")
    private Orders order;
//    @Column
//    private Integer storeNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storeNo")
    private Store store;

    @Column
    private String size;

    @Column
    private Integer price;

    @Column
    private Integer remain;




}
