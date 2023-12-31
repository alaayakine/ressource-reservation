package com.example.ressourceservice;

import com.example.ressourceservice.entities.Ressource;
import com.example.ressourceservice.enums.RessourceType;
import com.example.ressourceservice.repository.RessourceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RessourceServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RessourceServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner (RessourceRepository ressourceRepository){
        return args -> {
            for (int i = 1; i <= 10; i++) {
                Ressource ressource = new Ressource();
                ressource.setNom("Ressource " + i);
                ressource.setType((Math.random() < 0.5) ? RessourceType.MATERIEL_INF0 : RessourceType.MATERIEL_AUDIO_VUSUEL);
                ressourceRepository.save(ressource);
            }
        };
    }

}
