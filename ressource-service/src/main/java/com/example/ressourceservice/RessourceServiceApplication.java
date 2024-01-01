package com.example.ressourceservice;

import com.example.ressourceservice.entities.Ressource;
import com.example.ressourceservice.enums.RessourceType;
import com.example.ressourceservice.repository.RessourceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class RessourceServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RessourceServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner (RessourceRepository ressourceRepository){
        return args -> {
            List<String> names=new ArrayList<>(Arrays.asList("pc","usb","clavier","sourie","cable rj45"));

            for (int i = 1; i <= 10; i++) {
                Ressource ressource = new Ressource();
                ressource.setNom(names.get(new Random().nextInt(5)));
                ressource.setType((Math.random() < 0.5) ? RessourceType.MATERIEL_INF0 : RessourceType.MATERIEL_AUDIO_VUSUEL);
                ressourceRepository.save(ressource);
            }
        };
    }

}
