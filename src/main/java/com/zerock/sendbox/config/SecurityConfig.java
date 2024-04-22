
package com.zerock.sendbox.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/home","/css/**","/js/**",
                                "/image/**","/store/searchList","/admin/**",
                                "/user/**", "/owner/**","/checkPositionLogin",
                                "/checkPositionRegister","/adminlogin","/userlogin",
                                "/ownerlogin","adminregister","/userregister",
                                "/ownerregister","/adminterms","/userterms","/ownerterms").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((formLogin) ->
                        formLogin
                                .loginPage("/home")
                                .defaultSuccessUrl("/home")
                )
                .logout((logout) ->
                        logout
                                .logoutSuccessUrl("/userlogin")
                                .invalidateHttpSession(true)
                )
                .sessionManagement((sessionManagement) ->
                        sessionManagement
                                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                                .sessionFixation().migrateSession()
                                .invalidSessionUrl("/login")
                                .sessionAuthenticationErrorUrl("/login?error=session")
                                .maximumSessions(1).expiredUrl("/login?expired")
                )
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }

   /* @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .formLogin(AbstractHttpConfigurer::disable)

                .httpBasic(AbstractHttpConfigurer::disable)

                .cors(AbstractHttpConfigurer::disable)

                .csrf(AbstractHttpConfigurer::disable)

                .sessionManagement(configurer -> configurer
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(requests -> requests
                        .anyRequest().permitAll())
                .build();
    }*/

    // 비밀번호 암호화
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}