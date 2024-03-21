package com.example.patients;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MedicalHistory {
    private ArrayList<Consultation> consultas;
    private ArrayList<Attention> atenciones;

    public MedicalHistory(ArrayList<Consultation> consultas, ArrayList<Attention> atenciones) {
        this.consultas = consultas;
        this.atenciones = atenciones;
    }

    public void addConsultation(Consultation consulta) {
        this.consultas.add(consulta);
    }

    public void addAttetion(Attention atencion) {
        this.atenciones.add(atencion);
    }

    @JsonProperty("consultas")
    public ArrayList<Consultation> getConsultas() {
        return consultas;
    }

    @JsonProperty("atenciones")
    public ArrayList<Attention> getAtenciones() {
        return atenciones;
    }
}
