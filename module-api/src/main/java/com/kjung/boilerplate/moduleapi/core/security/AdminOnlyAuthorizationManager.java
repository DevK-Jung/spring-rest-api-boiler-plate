package com.kjung.boilerplate.moduleapi.core.security;

import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

// custom 권한 체크
@Component
public class AdminOnlyAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authenticationSupplier, RequestAuthorizationContext context) {

        Authentication authentication = authenticationSupplier.get();

        if (authentication == null || !authentication.isAuthenticated()) {
            return new AuthorizationDecision(false);
        }

        Object principal = authentication.getPrincipal();


        boolean adminYn = true; // todo 권한 체크 로직 추가
        if (adminYn)
            return new AuthorizationDecision(adminYn);


        return new AuthorizationDecision(false);
    }
}
