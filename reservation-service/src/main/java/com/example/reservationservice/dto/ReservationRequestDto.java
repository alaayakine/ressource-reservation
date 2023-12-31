package com.example.reservationservice.dto;

import com.example.reservationservice.entities.Personne;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ReservationRequestDto {

    private String nom;
    private String contexte;
    private Date date;
    private int duree;
    private Personne personne;
}
