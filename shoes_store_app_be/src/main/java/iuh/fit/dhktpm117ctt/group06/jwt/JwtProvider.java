package iuh.fit.dhktpm117ctt.group06.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtProvider {
    SecretKey key = Keys.hmacShaKeyFor(JwtConstants.SECRET_KEY.getBytes());

    public String generateToken(String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 60*60*1000)) // Token expires in 1 day
                .claim("email", email)
                .claim("authorities", role)
                .signWith(key) // Sử dụng HS256 với SecretKey
                .compact();
    }

    public String generateRefreshToken(String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 7*24*60*60*1000)) // Token expires in 10 minutes
                .claim("email", email)
                .claim("authorities", role)
                .signWith(key) // Sử dụng HS256 với SecretKey
                .compact();
    }

    public String getEmailFromToken(String jwt) {
        if (jwt.startsWith("Bearer ")) {
            jwt = jwt.substring(7); // Cắt bỏ "Bearer "
        }
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt)
                .getBody()
                .get("email", String.class);
    }
}
