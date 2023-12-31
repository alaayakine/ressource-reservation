package com.example.reservationservice.ressource;

import com.example.reservationservice.enums.RessourceType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Ressource {
    private int id;

    private String nom;

    @Enumerated(EnumType.STRING)
    private RessourceType type;
}
