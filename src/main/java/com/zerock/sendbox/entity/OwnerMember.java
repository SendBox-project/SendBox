package com.zerock.sendbox.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OwnerMember extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "o_m_no")
    private Integer oMNo;

    @Column(name = "o_m_id")
    private String oMId;

    @Column(name = "o_m_pw")
    private String oMPw;

    @Column(name = "o_m_name")
    private String oMName;

    @Column(length = 1, name = "o_m_gender", columnDefinition = "char(1)")
    private String oMGender;

    @Column(name = "o_m_mail")
    private String oMMail;

    @Column(name = "o_m_phone")
    private String oMPhone;

    @Column(length = 3, name = "o_m_region",  columnDefinition = "char(3)")
    private String oMRegion;

    @Column(name= "o_m_BRN")
    private String oMBrn;

    @Column(name = "o_m_store")
    private String oMStore;






}
