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
public class Reservation extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column
    private Integer reservationNo;

    @Column
    private Integer orderNo;

    @Column
    private Date reservationStatus;

    @Column
    private Integer uMNo;

    @Column
    private String paymentType;

    @Column
    private String PaymentMethod;
}
