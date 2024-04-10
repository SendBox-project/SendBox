package com.zerock.sendbox.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "room")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer orderNo;

    @Column
    private Integer userNo;

    @Column
    private Integer paymentNo;

    @OneToOne
    @JoinColumn(name = "roomNo")
    private Room room;

//    @Column
//    private Integer roomNo;

    @Column
    private String reservationNum;

    @Column
    private String reservationStatus;

    @Column
    private Integer totalPrice;

    @Column
    private Integer totalAmount;

    @Column
    private LocalDate startDate;

    @Column
    private LocalDate endDate;


}
