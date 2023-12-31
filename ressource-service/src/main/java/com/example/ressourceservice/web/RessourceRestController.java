package com.example.ressourceservice.web;

import com.example.ressourceservice.entities.Ressource;
import com.example.ressourceservice.enums.RessourceType;
import com.example.ressourceservice.service.RessourceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ressources")
public class RessourceRestController {

    private RessourceService ressourceService;


    public RessourceRestController(RessourceService ressourceService) {
        this.ressourceService = ressourceService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ressource> getRessourceById(@PathVariable int id) {
        Ressource ressource = ressourceService.getRessourceById(id);
        return ResponseEntity.ok(ressource);
    }

    @GetMapping
    public ResponseEntity<List<Ressource>> getAllRessources() {
        List<Ressource> ressources = ressourceService.getAllRessources();
        return ResponseEntity.ok(ressources);
    }

    @PostMapping
    public ResponseEntity<Ressource> createRessource(@RequestBody Ressource ressource) {
        Ressource createdRessource = ressourceService.saveRessource(ressource);
        return ResponseEntity.ok(createdRessource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ressource> updateRessource(@PathVariable int id, @RequestBody Ressource ressource) {
        Ressource updatedRessource = ressourceService.updateRessource(id, ressource);
        return ResponseEntity.ok(updatedRessource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRessource(@PathVariable int id) {
        ressourceService.deleteRessource(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/types")
    public ResponseEntity<RessourceType[]> getTypes() {
        return new ResponseEntity<>(RessourceType.values(), HttpStatus.OK);
    }
}
