package com.zerock.sendbox.service.Custom;

import com.zerock.sendbox.entity.AdminMember;
import com.zerock.sendbox.entity.OwnerMember;
import com.zerock.sendbox.entity.UserMember;
import com.zerock.sendbox.repository.AdminMemberRepository;
import com.zerock.sendbox.repository.OwnerMemberRepository;
import com.zerock.sendbox.repository.UserMemberRepository;
import com.zerock.sendbox.service.admin.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomImpl implements Custom {
    @Autowired
    private AdminMemberRepository adminMemberRepository;

    @Autowired
    private UserMemberRepository userMemberRepository;

    @Autowired
    private OwnerMemberRepository ownerMemberRepository;

    @Override
    public UserDetails loadMember(String userId) throws UsernameNotFoundException {
        AdminMember adminMember = adminMemberRepository.findByAdminId(userId);
        if (adminMember == null) {
            return new org.springframework.security.core.userdetails.User(
                    adminMember.getAdminId(),
                    adminMember.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"))
            );
        }

        UserMember userMember = userMemberRepository.findByUserId(userId);
        if (userMember == null) {
            return new org.springframework.security.core.userdetails.User(
                    userMember.getUserId(),
                    userMember.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
            );
        }

        OwnerMember ownerMember = ownerMemberRepository.findByOwnerId(userId);
        if (ownerMember == null) {
            return new org.springframework.security.core.userdetails.User(
                    ownerMember.getOwnerId(),
                    ownerMember.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_OWNER"))
            );
        }
     throw new UsernameNotFoundException("User not found with adminId: " + userId);
    }
}
