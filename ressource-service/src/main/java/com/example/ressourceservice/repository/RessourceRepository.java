package com.example.ressourceservice.repository;

import com.example.ressourceservice.entities.Ressource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RessourceRepository extends JpaRepository<Ressource,Integer> {

}
