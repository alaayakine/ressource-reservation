package com.example.reservationservice.service;

import com.example.reservationservice.entities.Personne;
import com.example.reservationservice.entities.Reservation;

import java.util.List;

public interface ReservationService {

    Reservation getReservationById(int id);
    List<Reservation> getAllReservations();
    Reservation saveReservation(Reservation reservation);
    Reservation updateReservation(int id, Reservation reservation);
    void deleteReservation(int id);

    Personne getPersonneById(int id);
    List<Personne> getAllPersonnes();
    Personne savePersonne(Personne personne);
    Personne updatePersonne(int id, Personne personne);
    void deletePersonne(int id);
}
