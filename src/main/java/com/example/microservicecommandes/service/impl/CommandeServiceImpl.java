package com.example.microservicecommandes.service.impl;

import com.example.microservicecommandes.configurations.ApplicationPropertiesConfiguration;
import com.example.microservicecommandes.dto.CommandeDTO;
import com.example.microservicecommandes.mapper.CommandeMapper;
import com.example.microservicecommandes.model.Commande;
import com.example.microservicecommandes.repository.CommandeRepository;
import com.example.microservicecommandes.service.CommandeService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommandeServiceImpl implements CommandeService {

    private final CommandeRepository commandeRepository;
    private final CommandeMapper commandeMapper;
    private final ApplicationPropertiesConfiguration config;
    private final RestTemplate restTemplate;

    public CommandeServiceImpl(CommandeRepository commandeRepository,
                               CommandeMapper commandeMapper,
                               ApplicationPropertiesConfiguration config,
                               RestTemplate restTemplate) {
        this.commandeRepository = commandeRepository;
        this.commandeMapper = commandeMapper;
        this.config = config;
        this.restTemplate = restTemplate;
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
        return commandeRepository.findById(id)
                .map(commandeMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Commande not found"));
    }

    @Override
    public CommandeDTO createCommande(CommandeDTO commandeDTO) {
        // Validate the produit ID with the produit microservice
        validateProduit(commandeDTO.getIdProduit());

        Commande commande = commandeMapper.toEntity(commandeDTO);
        Commande savedCommande = commandeRepository.save(commande);
        return commandeMapper.toDTO(savedCommande);
    }

    @Override
    public CommandeDTO updateCommande(Long id, CommandeDTO updatedCommandeDTO) {
        return commandeRepository.findById(id).map(existingCommande -> {
            // Validate the produit ID with the produit microservice
            validateProduit(updatedCommandeDTO.getIdProduit());

            existingCommande.setDescription(updatedCommandeDTO.getDescription());
            existingCommande.setQuantite(updatedCommandeDTO.getQuantite());
            existingCommande.setDate(updatedCommandeDTO.getDate());
            existingCommande.setMontant(updatedCommandeDTO.getMontant());
            existingCommande.setIdProduit(updatedCommandeDTO.getIdProduit());
            Commande updatedCommande = commandeRepository.save(existingCommande);
            return commandeMapper.toDTO(updatedCommande);
        }).orElseThrow(() -> new RuntimeException("Commande not found"));
    }

    @Override
    public void deleteCommande(Long id) {
        if (!commandeRepository.existsById(id)) {
            throw new RuntimeException("Commande not found");
        }
        commandeRepository.deleteById(id);
    }

    /**
     * Validate if the produit ID exists in the produit microservice.
     *
     * @param idProduit the ID of the produit to validate
     */
    private void validateProduit(Long idProduit) {
        String produitServiceUrl = "http://microservice-produit/api/produits/" + idProduit;
        try {
            restTemplate.getForObject(produitServiceUrl, Object.class); // If this call succeeds, the product exists
        } catch (Exception e) {
            throw new RuntimeException("Produit with ID " + idProduit + " does not exist.");
        }
    }

}
