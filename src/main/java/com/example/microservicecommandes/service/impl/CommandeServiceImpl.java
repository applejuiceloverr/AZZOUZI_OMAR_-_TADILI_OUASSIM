package com.example.microservicecommandes.service.impl;

import com.example.microservicecommandes.configurations.ApplicationPropertiesConfiguration;
import com.example.microservicecommandes.dto.CommandeDTO;
import com.example.microservicecommandes.mapper.CommandeMapper;
import com.example.microservicecommandes.model.Commande;
import com.example.microservicecommandes.repository.CommandeRepository;
import com.example.microservicecommandes.service.CommandeService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommandeServiceImpl implements CommandeService {

    private final CommandeRepository commandeRepository;
    private final CommandeMapper commandeMapper;
    private final ApplicationPropertiesConfiguration config;

    public CommandeServiceImpl(CommandeRepository commandeRepository,
                               CommandeMapper commandeMapper,
                               ApplicationPropertiesConfiguration config) {
        this.commandeRepository = commandeRepository;
        this.commandeMapper = commandeMapper;
        this.config = config;
    }

    @Override
    public List<CommandeDTO> getAllCommandes() {
        LocalDate cutoffDate = LocalDate.now().minusDays(config.getCommandesLast());
        return commandeRepository.findAll().stream()
                .filter(commande -> commande.getDate().isAfter(cutoffDate))
                .map(commandeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CommandeDTO getCommandeById(Long id) {
        // Trouver une commande par ID et convertir en DTO
        return commandeRepository.findById(id)
                .map(commandeMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Commande not found"));
    }

    @Override
    public CommandeDTO createCommande(CommandeDTO commandeDTO) {
        // Convertir le DTO en entité, sauvegarder, puis retourner le DTO sauvegardé
        Commande commande = commandeMapper.toEntity(commandeDTO);
        Commande savedCommande = commandeRepository.save(commande);
        return commandeMapper.toDTO(savedCommande);
    }

    @Override
    public CommandeDTO updateCommande(Long id, CommandeDTO updatedCommandeDTO) {
        // Trouver la commande existante, la mettre à jour avec les champs du DTO, sauvegarder et retourner le DTO
        return commandeRepository.findById(id).map(existingCommande -> {
            existingCommande.setDescription(updatedCommandeDTO.getDescription());
            existingCommande.setQuantite(updatedCommandeDTO.getQuantite());
            existingCommande.setDate(updatedCommandeDTO.getDate());
            existingCommande.setMontant(updatedCommandeDTO.getMontant());
            Commande updatedCommande = commandeRepository.save(existingCommande);
            return commandeMapper.toDTO(updatedCommande);
        }).orElseThrow(() -> new RuntimeException("Commande not found"));
    }

    @Override
    public void deleteCommande(Long id) {
        // Supprimer la commande par son ID
        if (!commandeRepository.existsById(id)) {
            throw new RuntimeException("Commande not found");
        }
        commandeRepository.deleteById(id);
    }
}
