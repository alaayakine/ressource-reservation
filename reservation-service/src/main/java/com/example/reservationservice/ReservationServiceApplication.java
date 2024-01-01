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

import java.util.*;

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
			List<String> names=new ArrayList<>(Arrays.asList("alaa","sabile","salah","ayoub","mohammed"));

			int k=1;

			for (int i = 1; i <= 10; i++) {
				String s = names.get(new Random().nextInt(4));
				Personne personne=new Personne();
				personne.setNom(s);
				personne.setEmail(s + i + "@gmail.com");
				personne.setFonction(UUID.randomUUID().toString().substring(0,6));

				personne=personneRepository.save(personne);

				List<Reservation>reservations=new ArrayList<>();

				Random random = new Random();

				for (int j = 1; j <= random.nextInt(1,3); j++) {

					Ressource ressource = ressourceOpenFeign.getRessourceById(random.nextInt(1,10));

					Reservation reservation=new Reservation();
					reservation.setNom("Reservation "+k);
					reservation.setContexte("cxt "+k);
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
