package com.example.reservationservice.entities;

import com.example.reservationservice.ressource.Ressource;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;
    private String contexte;
    private Date date;
    private int duree;

    @Transient
    private Ressource ressource;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int ressource_id;

    @ManyToOne
    @JsonIgnoreProperties("reservations")
    private Personne personne;
}
