package com.eazybytes.jobportal.security.filter;

import com.eazybytes.jobportal.constants.ApplicationConstants;
import com.eazybytes.jobportal.dto.LoggedInUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;


public class JwtTokenValidatorFilter extends OncePerRequestFilter {


    private final List<String> publicPaths;
    private final AntPathMatcher pathMatcher;
    private final SecretKey secretKey;

    public JwtTokenValidatorFilter(
            List<String> paths,
            Environment env
    ){
        this.pathMatcher = new AntPathMatcher();
        this.publicPaths = paths;
        String secret = Objects.requireNonNull(
                env.getProperty(ApplicationConstants.JWT_SECRET_KEY),
                "JWT_SECRET must be configured"
        );
        if (secret.length() < 32) {
            throw new IllegalStateException("JWT_SECRET must be at least 32 characters for HS256");
        }
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader(ApplicationConstants.JWT_HEADER);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            try {
                // Extract the JWT token
                // Whoever bears (holds) the token is trusted and can access the protected resource.
                String jwt = authHeader.substring(7); // Remove 'Bearer ' prefix
                if (null != secretKey) {
                    Claims claims = Jwts.parser().verifyWith(secretKey)
                            .build().parseSignedClaims(jwt).getPayload();
                    String email = String.valueOf(claims.get("email"));
                    String role = String.valueOf(claims.get("role"));
                    String name = String.valueOf(claims.get("name"));
                    String mobileNumber = String.valueOf(claims.get("mobileNumber"));
                    Authentication authentication = new UsernamePasswordAuthenticationToken(
                            new LoggedInUser(name, email, mobileNumber, role),
                            null,
                            List.of(new SimpleGrantedAuthority(role))
                    );
                    SecurityContextHolder.setContext(new SecurityContextImpl(authentication));
                }

            } catch (ExpiredJwtException exception) {
                SecurityContextHolder.clearContext();
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Token Expired");
                return;
            } catch (Exception exception) {
                SecurityContextHolder.clearContext();
                throw new BadCredentialsException("Invalid Token received!");
            }
        }
        filterChain.doFilter(request, response);

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return publicPaths.stream().anyMatch(publicPath ->
                pathMatcher.match(publicPath, path));
    }
}
