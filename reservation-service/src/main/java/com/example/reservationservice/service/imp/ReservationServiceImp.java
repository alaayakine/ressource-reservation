package com.example.reservationservice.service.imp;

import com.example.reservationservice.entities.Personne;
import com.example.reservationservice.entities.Reservation;
import com.example.reservationservice.repository.PersonneRepository;
import com.example.reservationservice.repository.ReservationRepository;
import com.example.reservationservice.ressource.RessourceOpenFeign;
import com.example.reservationservice.service.ReservationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImp implements ReservationService {

    private  ReservationRepository reservationRepository;
    private  PersonneRepository personneRepository;

    private RessourceOpenFeign ressourceOpenFeign;

    public ReservationServiceImp(ReservationRepository reservationRepository, PersonneRepository personneRepository,
                                 RessourceOpenFeign ressourceOpenFeign) {
        this.reservationRepository = reservationRepository;
        this.personneRepository=personneRepository;
        this.ressourceOpenFeign=ressourceOpenFeign;
    }

    @Override
    public Reservation getReservationById(int id) {
        Reservation reservation=reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found with id " + id));
        reservation.setRessource(ressourceOpenFeign.getRessourceById(reservation.getRessource_id()));

        return reservation;
    }

    @Override
    public List<Reservation> getAllReservations() {
        List<Reservation>reservations=reservationRepository.findAll();
        reservations.forEach(r->{r.setRessource(ressourceOpenFeign.getRessourceById(r.getRessource_id()));});
        return reservations;
    }

    @Override
    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation updateReservation(int id, Reservation reservation) {
        Reservation existingReservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found with id " + id));
        existingReservation.setNom(reservation.getNom());
        existingReservation.setContexte(reservation.getContexte());
        existingReservation.setDate(reservation.getDate());
        existingReservation.setDuree(reservation.getDuree());
        existingReservation.setRessource(reservation.getRessource());
        existingReservation.setRessource_id(reservation.getRessource_id());
        existingReservation.setPersonne(reservation.getPersonne());
        return reservationRepository.save(existingReservation);
    }

    @Override
    public void deleteReservation(int id) {
        reservationRepository.deleteById(id);
    }
    @Override
    public Personne savePersonne(Personne personne) {
        return personneRepository.save(personne);
    }

    @Override
    public Personne updatePersonne(int id, Personne personne) {
        Personne existingPersonne = personneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Personne not found with id " + id));
        existingPersonne.setNom(personne.getNom());
        existingPersonne.setEmail(personne.getEmail());
        existingPersonne.setFonction(personne.getFonction());
        existingPersonne.setReservations(personne.getReservations());
        return personneRepository.save(existingPersonne);
    }

    @Override
    public void deletePersonne(int id) {
        personneRepository.deleteById(id);
    }

    @Override
    public Personne getPersonneById(int id) {
        Personne personne=personneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Personne not found with id " + id));
        personne.getReservations().forEach(r->{
            r.setRessource(ressourceOpenFeign.getRessourceById(r.getRessource_id()));});

        return personne;
    }

    @Override
    public List<Personne> getAllPersonnes() {
        List<Personne> personnes=personneRepository.findAll();
        personnes.forEach(p -> {
            p.getReservations().forEach(r->{
                r.setRessource(ressourceOpenFeign.getRessourceById(r.getRessource_id()));
            });
        } );
        return personnes;
    }

}
