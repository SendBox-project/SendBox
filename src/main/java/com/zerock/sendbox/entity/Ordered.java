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
public class Ordered extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column
    private Integer orderNo;

    @Column
    private Integer roomNo;

    @Column
    private Integer orderPrice;

    @Column
    private Date orderDate;

    @Column
    private Integer orderAmount;
}
