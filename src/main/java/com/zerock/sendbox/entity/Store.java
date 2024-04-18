package com.zerock.sendbox.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "rooms")
@Table(name = "store")
@DynamicUpdate
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
