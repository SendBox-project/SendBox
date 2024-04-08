package com.zerock.sendbox.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.type.descriptor.jdbc.VarcharJdbcType;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StoreInfo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer stNo;

    @Column(name = "o_m_no")
    private Integer oMNo;

    @Column
    private  String stName;

    @Column
    private  String stNotice;

    @Column
    private  String stAdress;

    @Column
    private  String stPhone;

    @Column
    private  String stFilter;

    @Column
    private  String stThumbnail;

    @Column(name  = "st_info_photo")
    private  String stPhoto;
}
