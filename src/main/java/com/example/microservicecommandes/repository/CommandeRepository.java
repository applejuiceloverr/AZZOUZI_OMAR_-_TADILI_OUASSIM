package com.example.microservicecommandes.repository;

import com.example.microservicecommandes.model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {
    // Toutes les méthodes CRUD sont déjà disponibles :
    // save(), findById(), findAll(), deleteById(), etc.
}
