package com.example.microservicecommandes.dto;
import java.time.LocalDate;
public class CommandeDTO {
    private Long id;
    private String description;
    private Integer quantite;
    private LocalDate date;
    private Double montant;

    public CommandeDTO(Long id, String description, Integer quantite, LocalDate date, Double montant) {
        this.id = id;
        this.description = description;
        this.quantite = quantite;
        this.date = date;
        this.montant = montant;
    }
    public CommandeDTO() {
    }
    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public LocalDate getDate() {
        return date;
    }

    public Double getMontant() {
        return montant;
    }
}
