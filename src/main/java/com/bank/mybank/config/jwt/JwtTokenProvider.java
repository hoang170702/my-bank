package com.bank.mybank.config.jwt;

import com.bank.mybank.service.Impl.CustomUserDetails;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {
    private final String JWT_SECRET = "abcde23456fghijklLMNOPQRSTUVWXYZ1789";
    private final long JWT_EXPIRATION = 86400000L;


    public String generateToken(CustomUserDetails customUserDetails) {
        Date now = new Date();
        Date expirateDate = new Date(now.getTime() + JWT_EXPIRATION);
        return Jwts.builder()
                .setSubject(Integer.toString(customUserDetails.getUser().getId()))
                .setIssuedAt(now)
                .setExpiration(expirateDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    public int getUserIdFromJwt(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
        return Integer.parseInt(claims.getSubject());
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }
}
