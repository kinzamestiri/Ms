package com.example.jobms.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String service;
    private boolean etat;

    public Long getId() {
        return id;
    }

    public String getService() {
        return service;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setService(String service) {
        this.service = service;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public Job() {
    }

    public Job(Long id, String service, boolean etat) {
        this.id = id;
        this.service = service;
        this.etat = etat;
    }

    public Job(String service, boolean etat) {
        this.service = service;
        this.etat = etat;
    }
}
