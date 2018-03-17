package ru.curriculum.application.auth.principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade {

    public Authentication getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(null == authentication) {
            throw new RuntimeException("Authentication is null");
        }
        return authentication;
    }
}
