package com.example.ressourceservice.dto;

import com.example.ressourceservice.enums.RessourceType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class RessourceRequestDto {
    private String nom;
    @Enumerated(EnumType.STRING)
    private RessourceType type;
}
