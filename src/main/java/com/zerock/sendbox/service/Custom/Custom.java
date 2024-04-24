package com.zerock.sendbox.service.Custom;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface Custom {
    UserDetails loadMember(String adminId) throws UsernameNotFoundException;
}
