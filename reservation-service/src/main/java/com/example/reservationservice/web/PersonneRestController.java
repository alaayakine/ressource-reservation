package com.example.reservationservice.web;

import com.example.reservationservice.entities.Personne;
import com.example.reservationservice.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personnes")
public class PersonneRestController {

    private final ReservationService reservationService;

    public PersonneRestController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<Personne> createPersonne(@RequestBody Personne personne) {
        return ResponseEntity.ok(reservationService.savePersonne(personne));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personne> getPersonneById(@PathVariable int id) {
        return ResponseEntity.ok(reservationService.getPersonneById(id));
    }

    @GetMapping
    public ResponseEntity<List<Personne>> getAllPersonnes() {
        return ResponseEntity.ok(reservationService.getAllPersonnes());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Personne> updatePersonne(@PathVariable int id, @RequestBody Personne personne) {
        return ResponseEntity.ok(reservationService.updatePersonne(id, personne));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonne(@PathVariable int id) {
        reservationService.deletePersonne(id);
        return ResponseEntity.ok().build();
    }
}
