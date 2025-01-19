package com.example.microservicecommandes.controller;

import com.example.microservicecommandes.dto.CommandeDTO;
import com.example.microservicecommandes.service.CommandeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commandes")
public class CommandeController {

    private final CommandeService commandeService;

    public CommandeController(CommandeService commandeService) {
        this.commandeService = commandeService;
    }

    @GetMapping
    public List<CommandeDTO> getAllCommandes() {
        return commandeService.getAllCommandes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommandeDTO> getCommandeById(@PathVariable Long id) {
        return ResponseEntity.ok(commandeService.getCommandeById(id));
    }

    @PostMapping
    public ResponseEntity<?> createCommande(@RequestBody CommandeDTO commandeDTO) {
        try {
            CommandeDTO createdCommande = commandeService.createCommande(commandeDTO);
            return ResponseEntity.ok(createdCommande);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCommande(@PathVariable Long id, @RequestBody CommandeDTO commandeDTO) {
        try {
            CommandeDTO updatedCommande = commandeService.updateCommande(id, commandeDTO);
            return ResponseEntity.ok(updatedCommande);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommande(@PathVariable Long id) {
        commandeService.deleteCommande(id);
        return ResponseEntity.noContent().build();
    }
}
