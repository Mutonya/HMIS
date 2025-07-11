package com.mutonya.hmisapplication.config;

import com.mutonya.hmisapplication.auth.models.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component

public class JwtFilter extends OncePerRequestFilter {
    private  final JwtService jwtService;

    private final HandlerExceptionResolver handlerExceptionResolver;

   private final ApplicationContext context;

    @Autowired
    public JwtFilter(
            JwtService jwtService,
            HandlerExceptionResolver handlerExceptionResolver,
            ApplicationContext context
    ){
        this.jwtService = jwtService;
        this.context = context;
        this.handlerExceptionResolver = handlerExceptionResolver;
    }
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            final String jwt = authHeader.substring(7);

            final String email = jwtService.extractUsername(jwt);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (email != null && authentication == null) {
                UserDetails userDetails = context.getBean(UserService.class).loadUserByUsername(email);

                if (jwtService.isTokenValid(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
            filterChain.doFilter(request, response);


        }catch (Exception e){
            handlerExceptionResolver.resolveException(request, response, null, e);

        }

    }
}
