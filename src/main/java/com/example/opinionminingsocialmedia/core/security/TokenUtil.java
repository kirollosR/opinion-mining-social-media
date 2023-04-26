package com.example.opinionminingsocialmedia.core.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenUtil {
    private long TOKEN_VALIDITY = 604800L; // 7 Days
    private long EXP_TOKEN_VALIDITY = 1004800L; // 7 Days
    private String TOKEN_SECRET = "214125442A472D4B6150645367566B59703373357638792F423F4528482B4D6251655468576D5A7134743777217A24432646294A404E635266556A586E327235";

    public String generateToken(UserDetails userDetails) {
        HashMap<String, Object> calims = new HashMap<String, Object>();
//        clims.put("jti", id);
//        clims.put("sub", userDetails.getUsername());
//        clims.put("created", new Date());

        return buildToken(calims, userDetails, TOKEN_VALIDITY * 1000); // 7 days
    }


    public String generateRefreshToken(UserDetails userDetails) {
        HashMap<String, Object> calims = new HashMap<String, Object>();
//        clims.put("jti", id);
//        clims.put("sub", userDetails.getUsername());
//        clims.put("created", new Date());

        return buildToken(calims, userDetails, EXP_TOKEN_VALIDITY * 1000); // 7 days
    }

    private String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long expiration
    ) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    private Date getExpirationDate() {
        return new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000);
    }

    public String getUserNameFromToken(String token) {

        Claims claims = getClaims(token);

        return claims.getSubject();

    }


    public String getIdFromToken(String token) {

        Claims claims = getClaims(token);

        return claims.getId();

    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = getUserNameFromToken(token);

        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        Date expiration = getClaims(token).getExpiration();
        return expiration.before(new Date());
    }

    private Claims getClaims(String token) {
        Claims claims;
        try {
            claims = Jwts.parserBuilder().setSigningKey(getSigningKey())
                    .build().parseClaimsJws(token).getBody();
        } catch (Exception ex) {
            claims = null;
        }

        return claims;
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(TOKEN_SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
