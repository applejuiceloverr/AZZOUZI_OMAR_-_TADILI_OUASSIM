package com.example.microservicecommandes.health;

import com.example.microservicecommandes.repository.CommandeRepository;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CommandesHealthIndicator implements HealthIndicator {

    private final CommandeRepository commandeRepository;

    public CommandesHealthIndicator(CommandeRepository commandeRepository) {
        this.commandeRepository = commandeRepository;
    }

    @Override
    public Health health() {
        long count = commandeRepository.count();
        if (count > 0) {
            return Health.up().withDetail("message", "Commandes are available").build();
        } else {
            return Health.down().withDetail("message", "No commandes available").build();
        }
    }
}
