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
    @Column
    private Integer adminNo;

    @Column
    private String adminId;

    @Column
    private Integer approval;

    @Column
    private String password;

    @Column
    private String name;

    @Column(length = 1, columnDefinition = "char(1)")
    private String gender;

    @Column
    private String mail;

    @Column
    private String phone;

    @Column(length = 3, columnDefinition = "char(3)")
    private String part;

}
