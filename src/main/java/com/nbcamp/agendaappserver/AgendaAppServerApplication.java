package com.nbcamp.agendaappserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing
public class AgendaAppServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgendaAppServerApplication.class, args);
    }

}
