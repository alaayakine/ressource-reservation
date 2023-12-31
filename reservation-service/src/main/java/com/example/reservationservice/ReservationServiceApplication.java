package com.example.reservationservice;

import com.example.reservationservice.entities.Personne;
import com.example.reservationservice.entities.Reservation;
import com.example.reservationservice.repository.PersonneRepository;
import com.example.reservationservice.repository.ReservationRepository;
import com.example.reservationservice.ressource.Ressource;
import com.example.reservationservice.ressource.RessourceOpenFeign;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class ReservationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservationServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner (ReservationRepository reservationRepository,
										 PersonneRepository personneRepository, RessourceOpenFeign ressourceOpenFeign){
		return args -> {

			int k=1;

			for (int i = 1; i <= 10; i++) {

				Personne personne=new Personne();
				personne.setNom("Personne "+i);
				personne.setEmail("personne" + i + "@gmail.com");
				personne.setFonction("Function "+i);

				personne=personneRepository.save(personne);

				List<Reservation>reservations=new ArrayList<>();

				Random random = new Random();

				for (int j = 1; j <= random.nextInt(1,3); j++) {

					Ressource ressource = ressourceOpenFeign.getRessourceById(random.nextInt(1,10));

					Reservation reservation=new Reservation();
					reservation.setNom("Reservation "+k);
					reservation.setContexte("context "+k);
					reservation.setDate(new Date());
					reservation.setDuree(random.nextInt(1,4));
					reservation.setPersonne(personne);
					reservation.setRessource(ressource);
					reservation.setRessource_id(ressource.getId());

					reservationRepository.save(reservation);
					reservations.add(reservation);

					k++;
				}

				personneRepository.save(personne);
			}
		};
	}

}
