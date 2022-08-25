package com.innovation.myownblog.config;

import com.innovation.myownblog.jwt.JwtAccessDeniedHandler;
import com.innovation.myownblog.jwt.JwtAuthenticationEntryPoint;
import com.innovation.myownblog.jwt.JwtSecurityConfig;
import com.innovation.myownblog.jwt.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)          // 나중에 PreAuthorize라는 어노테이션을 메소드 단위로 추가하기 위해서 적용
public class SecurityConfig {
    private final TokenProvider tokenProvider;                                  // 생성한 TokenProvider,
    private final CorsFilter corsFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;      // JwtAuthentixationEntryPoint,
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;                // JwtAccessDeniedHandler을 주입 받음

    public SecurityConfig(
            TokenProvider tokenProvider,
            CorsFilter corsFilter,
            JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
            JwtAccessDeniedHandler jwtAccessDeniedHandler
    ) {
        this.tokenProvider = tokenProvider;
        this.corsFilter = corsFilter;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {                  // BCryptPasswordEncoder 사용
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/h2-console/**"      // h2-console 하위 모든 요청과
                , "/favicon.ico"                                                    // 파비콘과
                , "/error");                                                        // 에러는 모두 인증 관련된 것을 무시하도록 설정
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // token을 사용하는 방식이기 때문에 csrf를 disable한다.  * csrf : 사이트간 위조 요청. 즉, 정상적인 사용자가 의도치 않은 위조 요청을 보내는 것. spring에서는 csrf protection이 default => protection을 통해 post, put, delete 요청으로부터 보호함
                .csrf().disable()

                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)

                .exceptionHandling()            // 우리가 매핑한 (만든) 클래스들로 핸들러들을 덮어씌우기
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                // enable h2-console
                .and()
                .headers()
                .frameOptions()
                .sameOrigin()

                // 세션을 사용하지 않기 때문에 세션 설정을 STATELESS로 설정
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()                                           // Http Request에 대한 접근 제한 설정
//                .antMatchers("/api/hello").permitAll()              // 지정 요청에 대해서는 인증 없이 모든 접근 허용
                .antMatchers("/auth/login").permitAll()             // 토큰을 받기 위한 로그인 API와,
                .antMatchers("/user/signup").permitAll()             // 회원가입을 위한 API는 토큰이 없는 상태에서 요청됨 -> 모두 열어주기

                .antMatchers(HttpMethod.GET, "/api/posts").permitAll()               // 전체 게시글 조회
                .antMatchers(HttpMethod.GET, "/api/posts/{id}").permitAll()          // 한 게시글 조회 / 비밀번호 확인

                .anyRequest().authenticated()                           // 나머지 요청에 대해서는 인증 필요

                .and()
                .apply(new JwtSecurityConfig(tokenProvider));               // JwtSecurityConfig (JwtFilter을 addFilterBefore로 등록해뒀던 클래스) 넣기

        return httpSecurity.build();
    }
}