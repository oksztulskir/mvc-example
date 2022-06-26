package pl.sdacademy.springdatauserdemo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.Map;

@Slf4j
public class JwtUtil {
    private static final long EXPIRED_IN_MILLIS = 1000 * 60 * 60;
    private static final String SECRET_KEY = "my_secret_key";
    private static final String ROLE_KEY = "role";

    public static String generate(UserDetails userDetails) {
        Date now = new Date();
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + EXPIRED_IN_MILLIS))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .setClaims(Map.of(ROLE_KEY, userDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .findFirst()
                        .orElse("")))
                .compact();
    }

    public static boolean validate(String token, UserDetails userDetails) {

        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();

        if (!validateIssuer(claims.getSubject(), userDetails)) {
            log.error("Issuer not validated!");
            return false;
        }

        if (!validateExpirationDate(claims.getExpiration())) {
            log.error("Token expired!");
            return false;
        }

        return true;
    }

    private static boolean validateExpirationDate(Date expirationDate) {
        return expirationDate.getTime() - new Date().getTime() >= 0;
    }

    private static boolean validateIssuer(String issuer, UserDetails userDetails) {
        return issuer.equalsIgnoreCase(userDetails.getUsername());
    }
}
