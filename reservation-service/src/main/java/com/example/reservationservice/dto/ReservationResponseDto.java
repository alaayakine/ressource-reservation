package com.example.reservationservice.dto;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ReservationResponseDto {
    private int id;
    private String nom;
    private String contexte;
    private Date date;
    private int duree;
    private PersonneResponseDto personneResponseDto;
}
