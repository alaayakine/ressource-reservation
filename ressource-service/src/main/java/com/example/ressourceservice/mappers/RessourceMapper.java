package com.example.ressourceservice.mappers;

import com.example.ressourceservice.dto.RessourceResponseDto;
import com.example.ressourceservice.entities.Ressource;
import org.springframework.beans.BeanUtils;

public class RessourceMapper {

    public static RessourceResponseDto toRessourceDTO(Ressource ressource) {
        RessourceResponseDto dto = new RessourceResponseDto();
        BeanUtils.copyProperties(ressource, dto);
        return dto;
    }

    public static Ressource toRessource(RessourceResponseDto ressourceDTO) {
        Ressource ressource = new Ressource();
        BeanUtils.copyProperties(ressourceDTO, ressource);
        return ressource;
    }
}
