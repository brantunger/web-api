package com.dreadfall.webapi.component;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorAwareComponent implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        String user = SecurityContextHolder.getContext().getAuthentication() != null ?
                SecurityContextHolder.getContext().getAuthentication().getName() : "Unknown";
        return Optional.ofNullable(user);
    }
}
