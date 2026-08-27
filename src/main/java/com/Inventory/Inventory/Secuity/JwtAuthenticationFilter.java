package com.Inventory.Inventory.Secuity;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String requestHeader = request.getHeader("Authorization");

        // No JWT supplied
        if (requestHeader == null || requestHeader.isBlank()) {
            filterChain.doFilter(request, response);
            return;
        }

        // Invalid Authorization header
        if (!requestHeader.startsWith("Bearer ")) {
            logger.warn("Invalid Authorization header format");
            filterChain.doFilter(request, response);
            return;
        }

        String token = requestHeader.substring(7);
        String username = null;

        try {

            username = jwtHelper.getUsernameFromToken(token);

        } catch (IllegalArgumentException e) {

            logger.error("Unable to extract username from JWT", e);

        } catch (ExpiredJwtException e) {

            logger.warn("JWT token has expired");

        } catch (MalformedJwtException e) {

            logger.warn("Malformed JWT token");

        } catch (Exception e) {

            logger.error("JWT processing failed", e);
        }

        // Authenticate user if username was successfully extracted
        if (username != null
                && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails =
                    userDetailsService.loadUserByUsername(username);

            boolean validToken =
                    jwtHelper.validateToken(token, userDetails);

            if (validToken) {

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                authentication.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request)
                );

                SecurityContextHolder
                        .getContext()
                        .setAuthentication(authentication);

            } else {

                logger.warn("JWT validation failed");
            }
        }

        filterChain.doFilter(request, response);
    }
}