package com.example.microservicecommandes.service;

import com.example.microservicecommandes.dto.CommandeDTO;

import java.util.List;

public interface CommandeService {
    List<CommandeDTO> getAllCommandes();
    CommandeDTO getCommandeById(Long id);
    CommandeDTO createCommande(CommandeDTO commandeDTO);
    CommandeDTO updateCommande(Long id, CommandeDTO updatedCommandeDTO);
    void deleteCommande(Long id);
}
