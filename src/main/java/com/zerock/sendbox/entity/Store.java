package com.zerock.sendbox.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer storeNo;

    @Column
    private Integer ownerNo;

    @Column
    private  String storeName;

    @Column
    private  String notice;

    @Column
    private  String address;

    @Column
    private  String phone;

    @Column
    private  String filter;

    @Column
    private  String thumbnail;

    @Column
    private  String infoPhoto;
}
