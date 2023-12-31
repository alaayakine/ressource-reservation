package com.example.reservationservice.dto;

import com.example.reservationservice.entities.Reservation;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PersonneRequestDto {

    private String nom;
    private String email;
    private String fonction;

    private List<Reservation> reservations;
}
