package com.example.patients;

import java.util.Date;
import java.util.UUID;

public class Attention extends MedicalService {
    private String id;
    private String tipo;

    public Attention(String tipo, String fecha, String diagnostico, String tratamiento) {
        super(fecha, diagnostico, tratamiento);
        this.id = UUID.randomUUID().toString();
        ;
        this.tipo = tipo;
    }

    public String getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTipo(String motivo) {
        this.tipo = motivo;
    }
}
