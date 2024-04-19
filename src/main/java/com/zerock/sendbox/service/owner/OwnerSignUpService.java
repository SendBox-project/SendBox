package com.zerock.sendbox.service.owner;


import com.zerock.sendbox.dto.owner.OwnerSignUpDTO;
import com.zerock.sendbox.entity.OwnerMember;


public interface OwnerSignUpService {

    Integer join(OwnerSignUpDTO ownerSignUpDTOsignUpDTO);

    OwnerSignUpDTO entityToDTO(OwnerMember ownerMember);

    boolean isIdDuplicated(String ownerId);
}
