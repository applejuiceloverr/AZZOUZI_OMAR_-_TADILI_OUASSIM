package com.example.microservicecommandes.dto;

import java.time.LocalDate;

public class CommandeDTO {
    private Long id;
    private String description;
    private Integer quantite;
    private LocalDate date;
    private Double montant;
    private Long idProduit; // Add this field

    // Default constructor
    public CommandeDTO() {
    }

    // Parameterized constructor
    public CommandeDTO(Long id, String description, Integer quantite, LocalDate date, Double montant, Long idProduit) {
        this.id = id;
        this.description = description;
        this.quantite = quantite;
        this.date = date;
        this.montant = montant;
        this.idProduit = idProduit; // Initialize idProduit
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public Long getIdProduit() {
        return idProduit; // Getter for idProduit
    }

    public void setIdProduit(Long idProduit) {
        this.idProduit = idProduit; // Setter for idProduit
    }
}
