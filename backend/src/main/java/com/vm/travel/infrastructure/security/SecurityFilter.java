package com.vm.travel.infrastructure.security;

import com.vm.travel.domain.repositories.UserRepo;
import com.vm.travel.infrastructure.exceptions.NotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    private final MessageSource messageSource;
    private final TokenService tokenService;
    private final UserRepo userRepo;
    private final Set<String> skipUrls = new HashSet<>(List.of(
            "/api/v1/cities",
            "/api/v1/cities/{cityName:.+}",
            "/api/v1/countries/{countryName:.+}",
            "/api/v1/cities/{cityName:.+}/weather/current",
            "/api/v1/cities/{cityName:.+}/weather/forecast",
            "/api/v1/auth/register",
            "/api/v1/auth/login"
    ));
    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    @Autowired
    public SecurityFilter(MessageSource messageSource, TokenService tokenService, UserRepo userRepo) {
        this.messageSource = messageSource;
        this.tokenService = tokenService;
        this.userRepo = userRepo;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        if(token != null){
            var login = tokenService.validateToken(token);
            UserDetails user;
            try {
                user = userRepo.findByLogin(login).orElseThrow(() -> new NotFoundException(messageSource.getMessage("users.not_found", null, LocaleContextHolder.getLocale())));
            } catch (NotFoundException e) {
                throw new RuntimeException(e);
            }
            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(@NonNull HttpServletRequest request) {
       return skipUrls.stream().anyMatch(p -> pathMatcher.match(p, request.getServletPath()));
    }

    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}
