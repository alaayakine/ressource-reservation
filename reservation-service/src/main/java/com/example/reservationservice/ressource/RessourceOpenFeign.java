package com.example.reservationservice.ressource;

import com.example.reservationservice.enums.RessourceType;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "RESSOURCE-SERVICE",configuration = OpenFiegnConfiguration.class)
public interface RessourceOpenFeign {

    @GetMapping("/ressources/{id}")
    @CircuitBreaker(name = "ressourceService", fallbackMethod = "getDefaultRessource")
    Ressource getRessourceById(@PathVariable("id") int id);

    @GetMapping("/ressources")
    @CircuitBreaker(name = "ressourceService", fallbackMethod = "getAllDefaultRessources")
    List<Ressource> getAllRessources();

    default Ressource getDefaultRessource(int id, Exception exception){
        Ressource ressource=new Ressource();
        ressource.setId(id);
        ressource.setNom("Not Vailable");
        ressource.setType(null);
        return ressource;
    }
    default List<Ressource> getAllDefaultRessources(Exception exception){
        return List.of();
    }
}
