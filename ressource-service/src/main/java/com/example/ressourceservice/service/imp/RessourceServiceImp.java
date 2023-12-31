package com.example.ressourceservice.service.imp;

import com.example.ressourceservice.entities.Ressource;
import com.example.ressourceservice.repository.RessourceRepository;
import com.example.ressourceservice.service.RessourceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RessourceServiceImp implements RessourceService {

    private RessourceRepository ressourceRepository;


    public RessourceServiceImp(RessourceRepository ressourceRepository) {
        this.ressourceRepository = ressourceRepository;
    }

    @Override
    public Ressource getRessourceById(int id) {
        return ressourceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ressource not found with id " + id));
    }

    @Override
    public List<Ressource> getAllRessources() {
        return ressourceRepository.findAll();
    }

    @Override
    public Ressource saveRessource(Ressource ressource) {
        return ressourceRepository.save(ressource);
    }

    @Override
    public Ressource updateRessource(int id, Ressource ressource) {

        Ressource existingRessource = ressourceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ressource not found with id " + id));
        existingRessource.setNom(ressource.getNom());
        existingRessource.setType(ressource.getType());
        return ressourceRepository.save(existingRessource);
    }

    @Override
    public void deleteRessource(int id) {
        ressourceRepository.deleteById(id);
    }
}
