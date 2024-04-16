package com.zerock.sendbox.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "rooms")
@Table(name = "store")
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

    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY)
    private List<Room> rooms = new ArrayList<>();;


}
