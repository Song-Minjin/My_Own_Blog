package com.innovation.myownblog.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class TokenProvider implements InitializingBean {

    private final Logger logger = LoggerFactory.getLogger(TokenProvider.class);
    private static final String AUTHORITIES_KEY = "auth";
    private final String secret;
    private final long tokenValidityInMilliseconds;
    private Key key;

    public TokenProvider(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.token-validity-in-seconds}") long tokenValidityInSeconds) {
        this.secret = secret;
        this.tokenValidityInMilliseconds = tokenValidityInSeconds * 1000;
    }

    @Override
    public void afterPropertiesSet() {                          // 이유 : 빈이 생성되고 주입을 받은 후, 주입 받은 secret값을 Base64 Decode 한 후 key 변수에 할당하기 위함
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String createToken(Authentication authentication) {      // Authentication 객체의 권한 정보를 이용해서 토큰을 생성하는 createToken 메소드 추가
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        Date validity = new Date(now + this.tokenValidityInMilliseconds);       // 우리가 yml(properties)에서 설정해둔 만료시간 역시 설정해둔다.

        return Jwts.builder()                           // JWT 토큰을 생성하여 리턴
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact();
    }

    public Authentication getAuthentication(String token) {         // 반대로 토큰을 역으로 받아서 토큰에 담겨있는 권한 정보를 이용하여 Authentication 객체를 리턴하는 getAuthenticatioin() 메소드 생성
        Claims claims = Jwts                                        // 토큰을 파라미터로 받아, 토큰을 이용하여 클레임 생성
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends GrantedAuthority> authorities =            // 클레임에서 권한 정보들을 빼내서
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        User principal = new User(claims.getSubject(), "", authorities);        // 권한 정보들을 이용하여 유저 객체를 만들어주고

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);      // 유저객체와 토큰, 권한 정보들을 이용해서 최종적으로 Authentication 객체를 리턴
    }

    public boolean validateToken(String token) {                                    // 3. 토큰을 파라미터로 받아, 토큰의 유효성 검증
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);          // 토큰을 파라미터로 받아, 파싱을 해보고 발생하는 exception들을 캐치
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            logger.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            logger.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            logger.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            logger.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;                                               // 문제가 있으면 false, 정상이면 true
    }
}