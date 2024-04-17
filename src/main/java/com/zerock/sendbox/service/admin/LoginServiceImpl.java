package com.zerock.sendbox.service.admin;

import com.zerock.sendbox.dto.admin.LoginDTO;
import com.zerock.sendbox.entity.AdminMember;
import com.zerock.sendbox.repository.AdminMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService{

    private final AdminMemberRepository adminMemberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AdminMember authenticate(LoginDTO loginDTO) {
        String adminId = loginDTO.getAdminId();
        String password = loginDTO.getPassword();

        AdminMember adminMember = adminMemberRepository.findByAdminId(adminId);

        if (adminMember != null && passwordEncoder.matches(password, adminMember.getPassword())) {
            // 패스워드가 일치하면 사용자 반환
            return adminMember;
        }
        // 인증 실패
        return null;
    }
}
