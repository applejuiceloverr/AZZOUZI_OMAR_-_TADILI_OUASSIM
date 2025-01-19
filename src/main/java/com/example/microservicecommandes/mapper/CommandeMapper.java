package com.example.microservicecommandes.mapper;

import com.example.microservicecommandes.dto.CommandeDTO;
import com.example.microservicecommandes.model.Commande;
import org.springframework.stereotype.Component;

@Component
public class CommandeMapper {

    public CommandeDTO toDTO(Commande commande) {
        CommandeDTO dto = new CommandeDTO();
        dto.setId(commande.getId());
        dto.setDescription(commande.getDescription());
        dto.setQuantite(commande.getQuantite());
        dto.setDate(commande.getDate());
        dto.setMontant(commande.getMontant());
        dto.setIdProduit(commande.getIdProduit());
        return dto;
    }

    public Commande toEntity(CommandeDTO dto) {
        Commande commande = new Commande();
        commande.setId(dto.getId());
        commande.setDescription(dto.getDescription());
        commande.setQuantite(dto.getQuantite());
        commande.setDate(dto.getDate());
        commande.setMontant(dto.getMontant());
        commande.setIdProduit(dto.getIdProduit());
        return commande;
    }
}
