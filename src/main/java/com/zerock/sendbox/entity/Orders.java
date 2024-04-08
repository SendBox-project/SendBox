package com.zerock.sendbox.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer orderNo;

    @Column
    private Integer roomNo;

    @Column
    private Integer totalPrice;

    @Column
    private LocalDate orderDate;

    @Column
    private Integer totalAmount;
}
