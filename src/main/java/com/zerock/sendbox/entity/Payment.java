package com.zerock.sendbox.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = "orders")
@DynamicUpdate
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer paymentNo;

    @OneToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "paymentNo")
    private Orders orders;

    @Column
    private String type;

    @Column
    private String method;

}
