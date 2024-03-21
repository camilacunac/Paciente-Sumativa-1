package com.example.patients;

import java.util.Date;
import java.util.UUID;

public class Consultation extends MedicalService {
    private String id;
    private String motivo;

    public Consultation(String motivo, String fecha, String diagnostico, String tratamiento) {
        super(fecha, diagnostico, tratamiento);
        this.id = UUID.randomUUID().toString();
        ;
        this.motivo = motivo;
    }

    public String getId() {
        return id;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}
