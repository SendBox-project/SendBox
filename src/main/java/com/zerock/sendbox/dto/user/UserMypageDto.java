package com.zerock.sendbox.dto.user;


import lombok.Data;

@Data //getter, setter 다 됨
//DTO는 프로트에서 보낸 값을 받을 때 사용
public class UserMypageDto {
    private Integer userNo;
    private String userId;
    private String password;
    private String name;
    private String gender;
    private String mail;
    private String phone;
}
