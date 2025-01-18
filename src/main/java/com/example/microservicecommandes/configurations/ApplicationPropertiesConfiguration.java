package com.example.microservicecommandes.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "mes-config-ms")
public class ApplicationPropertiesConfiguration {

    private int commandesLast;

    public int getCommandesLast() {
        return commandesLast;
    }

    public void setCommandesLast(int commandesLast) {
        this.commandesLast = commandesLast;
    }
}
