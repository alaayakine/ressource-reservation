package com.example.reservationservice.dto;

import com.example.reservationservice.entities.Reservation;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PersonneResponseDto {
    private int id;
    private String nom;
    private String email;
    private String fonction;

}

