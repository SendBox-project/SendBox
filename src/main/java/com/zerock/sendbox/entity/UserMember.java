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
    @Column
    private Integer userNo;

    @Column
    private String userId;

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

}
