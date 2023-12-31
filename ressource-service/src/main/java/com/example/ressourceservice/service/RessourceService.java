package com.example.ressourceservice.service;

import com.example.ressourceservice.entities.Ressource;

import java.util.List;

public interface RessourceService {

    Ressource getRessourceById(int id);
    List<Ressource> getAllRessources();
    Ressource saveRessource(Ressource ressource);
    Ressource updateRessource(int id, Ressource ressource);
    void deleteRessource(int id);
}
