package com.eazybytes.jobportal.audit;

import com.eazybytes.jobportal.dto.LoggedInUser;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditorAwareImpl")
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.of("Anonymous User");
        }

        Object principal = authentication.getPrincipal();

        if(principal instanceof LoggedInUser loggedInUser) {
            return Optional.ofNullable(loggedInUser.email());
        }

        if (principal instanceof UserDetails userDetails) {
            return Optional.ofNullable(userDetails.getUsername());
        }

        if (principal instanceof String principalName && !principalName.isBlank()
                && !"anonymousUser".equalsIgnoreCase(principalName)) {
            return Optional.of(principalName);
        }

        String name = authentication.getName();
        if (name != null && !name.isBlank() && !"anonymousUser".equalsIgnoreCase(name)) {
            return Optional.of(name);
        }

        return Optional.of("Anonymous User");
    }
}
