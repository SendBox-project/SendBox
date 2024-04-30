package com.zerock.sendbox.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EntityListeners(value = { AuditingEntityListener.class })
public class Review{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer reviewNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storeNo")
    private Store store;

    @Column
    private Integer userNo;

    @Column
    private String content;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime regDate;
}
