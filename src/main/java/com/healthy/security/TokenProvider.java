package com.healthy.security;

import com.healthy.repository.UserRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TokenProvider {
    private final UserRepository userRepository;
    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.validity-in-seconds}")
    private long jwtValidityInSeconds;

    private Key key;
    private JwtParser jwtParser;

    @PostConstruct
    public void init() {
        key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
        jwtParser = Jwts.parserBuilder()
                .setSigningKey(key)
                .build();
    }

    public String createAccessToken(Authentication authentication) {

        // Obtener el email del usuario autenticado desde el principal
        String email = authentication.getName();

        com.healthy.model.entity.User user = userRepository
                .findByUserName(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con el email: " +email));


        String role = authentication
                .getAuthorities()
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No role found"))
                .getAuthority();

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim("role", role)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(new Date(System.currentTimeMillis() + jwtValidityInSeconds * 1000))
                .compact();
    }
    public Authentication getAuthentication(String token) {
        Claims claims = jwtParser.parseClaimsJws(token).getBody();

        String role = claims.get("role").toString();
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(role));

        User principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public boolean validateToken(String token) {
        try {
            jwtParser.parseClaimsJws(token);
            return true;
        } catch (JwtException e) {

            return false;
        }
    }



}