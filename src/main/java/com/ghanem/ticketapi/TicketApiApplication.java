package com.ghanem.ticketapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, UserDetailsServiceAutoConfiguration.class})
@EntityScan("com.ghanem.ticketapi.models")
@EnableJpaRepositories("com.ghanem.ticketapi.repositories")
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class TicketApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(TicketApiApplication.class, args);

    }
}
