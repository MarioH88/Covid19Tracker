package com.covid19.Covid19Tracker.service;

import com.covid19.Covid19Tracker.exceptions.NotLoggedInException;
import com.covid19.Covid19Tracker.model.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecurityService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    public SecurityService(AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    public Optional<User> findLoggedIn() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication();
        if (!(userDetails instanceof User)) {
            return Optional.empty();
        }

        return Optional.of((User) userDetails);
    }

    public User getLoggedIn() {
        return findLoggedIn().orElseThrow(NotLoggedInException::new);
    }

    public void login(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        authenticationManager.authenticate(token);

        if (token.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(token);
        }
    }
}
