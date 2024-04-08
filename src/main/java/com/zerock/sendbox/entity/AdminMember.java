package com.zerock.sendbox.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AdminMember extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "a_m_no")
    private Integer aMNo;

    @Column(name = "a_m_id")
    private String aMId;


    @Column(name = "a_m_approval")
    private Integer aMApproval;

    @Column(name = "a_m_pw")
    private String aMPw;

    @Column(name = "a_m_name")
    private String aMName;

    @Column(length = 1, name = "a_m_gender", columnDefinition = "char(1)")
    private String aMGender;

    @Column(name = "a_m_mail")
    private String aMMail;

    @Column(name = "a_m_phone")
    private String aMPhone;

    @Column(length = 3, columnDefinition = "char(3)", name = "a_m_part")
    private String aMPart;

}
