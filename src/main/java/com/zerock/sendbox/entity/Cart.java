package com.zerock.sendbox.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_m_no")
    private Integer cartMNo;

    @Column(name = "u_m_no")
    private Integer uMNO;

    @Column
    private Integer orderNo;

}
