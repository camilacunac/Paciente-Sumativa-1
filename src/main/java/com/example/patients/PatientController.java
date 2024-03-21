package com.example.patients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/pacientes")
    public Response getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/paciente/{rut}")
    public Response getPatientByRut(@PathVariable String rut) {
        return patientService.getPatientByRut(rut);
    }

    @GetMapping("/historial-paciente/{rut}")
    public Response getHistorialByRut(@PathVariable String rut) {
        return patientService.getHistorialByRut(rut);
    }

    @GetMapping("/agregar-paciente")
    public Response addPatient(
            @RequestParam("rut") String rut,
            @RequestParam("nombre") String nombre,
            @RequestParam("apellidoPaterno") String apellidoPaterno,
            @RequestParam("apellidoMaterno") String apellidoMaterno,
            @RequestParam("fechaNacimiento") String fechaNac,
            @RequestParam("genero") String genero,
            @RequestParam("direccion") String direccion,
            @RequestParam("email") String email,
            @RequestParam("telefono") String telefono) {
        Patient patient = new Patient(rut, nombre, apellidoPaterno, apellidoMaterno, fechaNac, genero, direccion,
                telefono,
                email);
        return patientService.addPatient(patient);
    }

    @GetMapping("/agregar-consulta/{rut}")
    public Response addConsultForPatient(
            @PathVariable String rut,
            @RequestParam("fecha") String fecha,
            @RequestParam("diagnostico") String diagnostico,
            @RequestParam("tratamiento") String tratamiento,
            @RequestParam("motivo") String motivo) {
        Consultation consultation = new Consultation(motivo, fecha, diagnostico, tratamiento);
        return patientService.addConsultForPatient(rut, consultation);
    }

    @GetMapping("/agregar-atencion/{rut}")
    public Response addAttetionForPatient(
            @PathVariable String rut,
            @RequestParam("fecha") String fecha,
            @RequestParam("diagnostico") String diagnostico,
            @RequestParam("tratamiento") String tratamiento,
            @RequestParam("tipo") String tipo) {
        Attention attention = new Attention(tipo, fecha, diagnostico, tratamiento);
        return patientService.addAttetionForPatient(rut, attention);
    }
}
