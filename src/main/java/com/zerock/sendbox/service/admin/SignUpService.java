package com.zerock.sendbox.service.admin;

import com.zerock.sendbox.dto.admin.SignUpDTO;
import com.zerock.sendbox.entity.AdminMember;

public interface SignUpService {
    Integer join(SignUpDTO signUpDTO);


    SignUpDTO entityToDTO(AdminMember adminMember);


    boolean isIdDuplicated(String adminId);


}
