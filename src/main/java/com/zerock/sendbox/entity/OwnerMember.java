package com.zerock.sendbox.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OwnerMember extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer ownerNo;

    @Column
    private String ownerId;

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
    private String region;

    @Column
    private String brn;

    @ColumnDefault("'N'")
    @Column(length = 1,  columnDefinition = "char(1)", nullable = false)
    private String deleteYn;

}
