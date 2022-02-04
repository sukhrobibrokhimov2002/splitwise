package ai.sukhrob.splitwise.config;

import ai.sukhrob.splitwise.domain.auth.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class ConfiguringAuditing implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null &&
                authentication.isAuthenticated() &&
                !authentication.getPrincipal().equals("anonymousUser")) {
            User user = (User) authentication.getPrincipal();
            return Optional.of(user.getId());
        }

        return Optional.empty();
    }
}
