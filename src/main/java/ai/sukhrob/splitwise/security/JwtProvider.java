package ai.sukhrob.splitwise.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {

    @Value("${app.jwtSecret}")
    private String secretKey;
    @Value("${app.jwtExpirationInMs}")
    private Long jwtExpirationInMs;


    public String generateToken(String phoneNumber){
        String compact = Jwts
                .builder()
                .setSubject(phoneNumber)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
        return compact;
    }

    public String getUserNameFromToken(String token){
       try {
        String subject = Jwts
                .parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody().getSubject();
        return subject;
    }catch (Exception e){
           return null;
       }
    }
    public boolean validateToken(String token){
        try {
            Jwts
                    .parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);
            return true;

        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
