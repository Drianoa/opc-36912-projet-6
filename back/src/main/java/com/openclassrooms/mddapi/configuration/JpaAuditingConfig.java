package com.openclassrooms.mddapi.configuration;

import com.openclassrooms.mddapi.features.auth.User;
import com.openclassrooms.mddapi.security.MddAuditorAware;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;

@Component
@EnableJpaAuditing
public class JpaAuditingConfig {
    @Bean
    public AuditorAware<User> auditorProvider() {
        return new MddAuditorAware();
    }
}
