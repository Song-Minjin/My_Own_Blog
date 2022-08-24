package com.innovation.myownblog.jwt;

// JWT를 위한 커스텀 필터 생성
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtFilter extends GenericFilterBean {

    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);
    public static final String AUTHORIZATION_HEADER = "Authorization";
    private TokenProvider tokenProvider;                                    // 이 JWT 필터는 우리가 만든 provider를 주입 받음
    public JwtFilter(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    // JWT 토큰의 인증 정보를 현재 실행중인 SecurityContext에 저장하는 역할 수행
    @Override       // 실제 필터링 로직은 doFilter 메소드 내부에 들어있음
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String jwt = resolveToken(httpServletRequest);                                  // Request에서 Token을 받아서
        String requestURI = httpServletRequest.getRequestURI();

        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {             // 아까 만든 provider 중 유효성 검증 메소드를 통과하고
            Authentication authentication = tokenProvider.getAuthentication(jwt);       // 토큰이 정상적이면 토큰에서 Authentication 객체를 받아와서
            SecurityContextHolder.getContext().setAuthentication(authentication);       // SecurityContext에 set해줌 (SecurityContext에 Authentication 객체가 저장되는 시점!!!)
            logger.debug("Security Context에 '{}' 인증 정보를 저장했습니다, uri: {}", authentication.getName(), requestURI);
        } else {
            logger.debug("유효한 JWT 토큰이 없습니다, uri: {}", requestURI);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String resolveToken(HttpServletRequest request) {                   // 필터링을 하기 위해 토큰 정보가 있어야 함! Request Header에서 토큰 정보를 꺼내오는 메소드
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }

        return null;
    }
}