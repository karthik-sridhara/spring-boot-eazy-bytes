package com.eazybytes.jobportal.security.util;

import com.eazybytes.jobportal.constants.ApplicationConstants;
import com.eazybytes.jobportal.entity.JobPortalUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    private final SecretKey secretKey;
    public JwtUtil(
            Environment env
    ){
        String secret = Objects.requireNonNull(
                env.getProperty(ApplicationConstants.JWT_SECRET_KEY),
                "JWT_SECRET must be configured"
        );
        secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateJwtToken(Authentication authentication){
        var fetchedUser = (JobPortalUser) authentication.getPrincipal();
        return Jwts.builder().issuer("Job Portal").subject("JWT Token")
                .claim("name", fetchedUser.getName())
                .claim("email", fetchedUser.getEmail())
                .claim("mobileNumber", fetchedUser.getMobileNumber())
                .claim("role", fetchedUser.getRole().getName())
                .issuedAt(new java.util.Date())
                .expiration(new java.util.Date((new java.util.Date()).getTime() + 24 * 60 * 60 * 1000))
                .signWith(secretKey).compact();
    }
}
