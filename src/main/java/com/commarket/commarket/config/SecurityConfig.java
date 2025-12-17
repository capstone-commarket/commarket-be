package com.commarket.commarket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * PasswordEncoder Bean 등록
     * BCrypt 알고리즘을 사용하여 비밀번호를 인코딩하고 검증하는 데 사용됩니다.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * HTTP 보안 설정 정의
     * CSRF 및 폼 로그인을 비활성화하고, 특정 API 경로에 대한 접근 권한을 설정합니다.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. CSRF 보호 비활성화 (API 서버에서 토큰 인증을 사용하므로 비활성화)
                .csrf(csrf -> csrf.disable())

                // 2. 폼 로그인 비활성화 (Spring Security 기본 로그인 페이지 차단)
                .formLogin(form -> form.disable())

                // 3. HTTP 기본 인증 비활성화
                .httpBasic(basic -> basic.disable())

                /* 
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // 모든 요청을 인증 없이 허용
                );*/

                // 4. 경로별 접근 권한 설정
                .authorizeHttpRequests(auth -> auth
                        // 회원가입, 로그인, 아이디 찾기 경로는 인증 없이 접근 허용 (Whitelist)
                        .requestMatchers(
                                "/users/signup",
                                "/users/login",
                                "/users/findID"
                        ).permitAll()

                        // 나머지 모든 요청은 인증 필요
                        .anyRequest().authenticated()
                );
        return http.build();
    }
}