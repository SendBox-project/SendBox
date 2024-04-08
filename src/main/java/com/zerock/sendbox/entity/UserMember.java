package com.zerock.sendbox.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserMember extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "u_m_no")
    private Integer uMNo;

    @Column(name = "u_m_id")
    private String uMId;

    @Column(name = "u_m_pw")
    private String uMPw;

    @Column(name = "u_m_name")
    private String uMName;

    @Column(length = 1, name = "u_m_gender", columnDefinition = "char(1)")
    private String uMGender;

    @Column(name = "u_m_mail")
    private String uMMail;

    @Column(name = "u_m_phone")
    private String uMPhone;

}
