
package com.zerock.sendbox.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    // 비밀번호 암호화
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Configuration
    @Order(0)
    public static class UserSecurityConfig {
        @Autowired
        private DataSource dataSource;

        /* 로그인 실패 핸들러 의존성 주입 */
        @Autowired
        private AuthenticationFailureHandler customFailureHandler;

        @Bean
        public SecurityFilterChain filterChain1(HttpSecurity http) throws Exception {
            // 권한에 따라 허용하는 url 설정
            // /login, /signup 페이지는 모두 허용, 다른 페이지는 인증된 사용자만 허용

            http
                    .authorizeHttpRequests(
                            (authorizeHttpRequests) ->
                                    authorizeHttpRequests
                                            .requestMatchers("/user/create_account_form", "/user/login", "/admin/login","/owner/login",
                                                    "/admin/loginProc","/owner/loginProc", "/user/terms","/user/forgot_id","/user/reset_password_form",
                                                    "/checkPositionLogin","/checkPositionRegister","/css/**","/js/**","/image/**").permitAll()
//                                            .requestMatchers("/user/**").hasRole("USER")
                                            .anyRequest().authenticated()
                    );

            // login 설정
            http
                    .formLogin((formLogin) ->
                            formLogin
                                    .loginPage("/user/login")
                                    .loginProcessingUrl("/user/loginProc")
                                    .usernameParameter("userId")
                                    .passwordParameter("password")
                                    .defaultSuccessUrl("/user/home", true)
                                    .failureHandler(customFailureHandler) // 로그인 실패 핸들러
                                    .permitAll()
                    );

            // logout 설정
            http
                    .logout((logout) ->
                            logout
                                    .logoutSuccessUrl("/")
                                    .invalidateHttpSession(true).deleteCookies("JSESSIONID")
                                    .permitAll()
                    );

            return http.build();
        }

        // Authentication 로그인 , Authorization 권한
        @Autowired
        public void configureGlobal1(AuthenticationManagerBuilder auth) throws Exception {
            auth.jdbcAuthentication()
                    .dataSource(dataSource)
                    .passwordEncoder(passwordEncoder())
                    // 인증처리
                    .usersByUsernameQuery("select user_id, password, true "
                            + "from user_member "
                            + "where user_id = ?")
                    // 권한처리
                    .authoritiesByUsernameQuery("select m.user_id, r.name "
                            + "from user_member m inner join member_role r on m.role_id = r.role_id "
                            + "where m.user_id = ?");
        }
    }

//    @Configuration
//    @Order(1)
//    public static class OwnerSecurityConfig {
//        @Autowired
//        private DataSource dataSource;
//
//        /* 로그인 실패 핸들러 의존성 주입 */
//        @Autowired
//        private AuthenticationFailureHandler customFailureHandler;
//
//        @Bean
//        public SecurityFilterChain filterChain2(HttpSecurity http) throws Exception {
//            // 권한에 따라 허용하는 url 설정
//            // /login, /signup 페이지는 모두 허용, 다른 페이지는 인증된 사용자만 허용
//
//            http
//                    .authorizeHttpRequests(
//                            (authorizeHttpRequests) ->
//                                    authorizeHttpRequests
//                                            .requestMatchers("/owner/create_account_form","/owner/login", "/admin/login","/user/login",
//                                                    "/admin/loginProc","/user/loginProc", "/owner/terms","/owner/forgot_id","/owner/reset_password_form",
//                                                    "/checkPositionLogin","/checkPositionRegister", "/css/**","/js/**","/image/**").permitAll()
////                                            .requestMatchers("/owner/**").hasRole("OWNER")
//                                            .anyRequest().authenticated()
//                    );
//
//            // login 설정
//            http
//                    .formLogin((formLogin) ->
//                            formLogin
//                                    .loginPage("/owner/login")
//                                    .loginProcessingUrl("/owner/loginProc")
//                                    .usernameParameter("ownerId")
//                                    .passwordParameter("password")
//                                    .defaultSuccessUrl("/owner/home", true)
//                                    .failureHandler(customFailureHandler) // 로그인 실패 핸들러
//                                    .permitAll()
//                    );
//
//            // logout 설정
//            http
//                    .logout((logout) ->
//                            logout
//                                    .logoutSuccessUrl("/")
//                                    .invalidateHttpSession(true).deleteCookies("JSESSIONID")
//                                    .permitAll()
//                    );
//
//            return http.build();
//        }
//
//        // Authentication 로그인 , Authorization 권한
//        @Autowired
//        public void configureGlobal2(AuthenticationManagerBuilder auth) throws Exception {
//            auth.jdbcAuthentication()
//                    .dataSource(dataSource)
//                    .passwordEncoder(passwordEncoder())
//                    // 인증처리
//                    .usersByUsernameQuery("select owner_id, password, true "
//                            + "from owner_member "
//                            + "where owner_id = ?")
//                    // 권한처리
//                    .authoritiesByUsernameQuery("select m.owner_id, r.name "
//                            + "from owner_member m inner join member_role r on m.role_id = r.role_id "
//                            + "where m.owner_id = ?");
//        }
//    }
//
//    @Configuration
//    @Order(2)
//    public static class AdminSecurityConfig {
//        @Autowired
//        private DataSource dataSource;
//
//        /* 로그인 실패 핸들러 의존성 주입 */
//        @Autowired
//        private AuthenticationFailureHandler customFailureHandler;
//
//        @Bean
//        public SecurityFilterChain filterChain3(HttpSecurity http) throws Exception {
//            // 권한에 따라 허용하는 url 설정
//            // /login, /signup 페이지는 모두 허용, 다른 페이지는 인증된 사용자만 허용
//
//            http
//                    .authorizeHttpRequests(
//                            (authorizeHttpRequests) ->
//                                    authorizeHttpRequests
//                                            .requestMatchers("/admin/create_account_form","/admin/login","/user/login","/owner/login",
//                                                    "/owner/loginProc","/user/loginProc", "/admin/terms","/admin/forgot_id","/admin/reset_password_form",
//                                                    "/checkPositionLogin","/checkPositionRegister","/css/**","/js/**","/image/**").permitAll()
////                                            .requestMatchers("/admin/**").hasRole("ADMIN")
//                                            .anyRequest().authenticated()
//                    );
//
//            // login 설정
//            http
//                    .formLogin((formLogin) ->
//                            formLogin
//                                    .loginPage("/admin/login")
//                                    .loginProcessingUrl("/admin/loginProc")
//                                    .usernameParameter("adminId")
//                                    .passwordParameter("password")
//                                    .defaultSuccessUrl("/admin/home", true)
//                                    .failureHandler(customFailureHandler) // 로그인 실패 핸들러
//                                    .permitAll()
//                    );
//
//            // logout 설정
//            http
//                    .logout((logout) ->
//                            logout
//                                    .logoutSuccessUrl("/")
//                                    .invalidateHttpSession(true).deleteCookies("JSESSIONID")
//                                    .permitAll()
//                    );
//
//            return http.build();
//        }
//
//        // Authentication 로그인 , Authorization 권한
//        @Autowired
//        public void configureGlobal3(AuthenticationManagerBuilder auth) throws Exception {
//            auth.jdbcAuthentication()
//                    .dataSource(dataSource)
//                    .passwordEncoder(passwordEncoder())
//                    // 인증처리
//                    .usersByUsernameQuery("select admin_id, password, true "
//                            + "from admin_member "
//                            + "where admin_id = ?")
//                    // 권한처리
//                    .authoritiesByUsernameQuery("select m.admin_id, r.name "
//                            + "from admin_member m inner join member_role r on m.role_id = r.role_id "
//                            + "where m.admin_id = ?");
//        }
//    }

}
