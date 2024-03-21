package com.example.patients;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class PatientService {
    private Map<String, Patient> patients = new HashMap<>();

    public PatientService() {
        Patient paciente1 = new Patient("12345678-9", "Juan", "González", "López",
                "1980-05-15", "Masculino", "Calle A #123", "+56987654321", "juan.gonzalez@example.com");
        paciente1.getHistorialMedico()
                .addConsultation(new Consultation("Dolor de cabeza", "2024-03-20", "Migraña", "Descanso y medicación"));
        paciente1.getHistorialMedico().addAttetion(
                new Attention("Radioterapia", "2024-03-20", "Cáncer de pulmón", "Tratamiento con radiación"));
        patients.put(paciente1.getRut(), paciente1);

        Patient paciente2 = new Patient("87654321-0", "María", "Martínez", "Pérez",
                "1995-10-20", "Femenino", "Avenida B #456", "+56901234567", "maria.martinez@example.com");
        paciente2.getHistorialMedico().addConsultation(new Consultation("Fiebre alta", "2024-03-21", "Infección viral",
                "Medicación y reposo"));
        paciente2.getHistorialMedico()
                .addAttetion(new Attention("Cirugia", "2024-03-21", "Fractura de pierna", "Intervención quirúrgica"));
        patients.put(paciente2.getRut(), paciente2);

        Patient paciente3 = new Patient("23456789-1", "Pedro", "Sánchez", "Ramírez",
                "1988-03-25", "Masculino", "Calle C #789", "+56876543210", "pedro.sanchez@example.com");
        paciente3.getHistorialMedico().addConsultation(new Consultation("Dolor de garganta", "2024-03-22", "Faringitis",
                "Gárgaras y medicación"));
        paciente3.getHistorialMedico()
                .addAttetion(new Attention("Quimioterapia", "2024-03-22", "Leucemia", "Tratamiento con quimioterapia"));
        patients.put(paciente3.getRut(), paciente3);
    }

    public Response getAllPatients() {
        return new Response("success", patients.values(), "");
    }

    public Response getPatientByRut(String rut) {
        Patient patient = patients.get(rut);
        if (patient != null) {
            return new Response("success", patient, "");
        }
        return new Response("error", "", "No se encontro ningun paciente con ese rut");
    }

    public Response getHistorialByRut(String rut) {
        Patient patient = patients.get(rut);
        if (patient != null) {
            return new Response("success", patient.getHistorialMedico(), "");
        }
        return new Response("error", "", "No se encontro ningun paciente con ese rut");
    }

    public Response addPatient(Patient patient) {
        Response res;
        try {
            if (patients.containsKey(patient.getRut())) {
                throw new IllegalArgumentException("El rut ya está registrado");
            }

            if (!validateRut(patient.getRut())) {
                throw new IllegalArgumentException("El rut ingresado no es valido");
            }

            if (!validateEmail(patient.getEmail())) {
                throw new IllegalArgumentException("El email ingresado no es valido");
            }

            if (!validatePhone(patient.getTelefono())) {
                throw new IllegalArgumentException(
                        "El telefono ingresado no es valido");
            }

            patients.put(patient.getRut(), patient);
            res = new Response("success", patient, "");
        } catch (IllegalArgumentException e) {
            res = new Response("error", "", e.getMessage());
        } catch (Exception e) {
            res = new Response("error", "", "Error al procesar la solicitud");
        }
        return res;
    }

    public Response addConsultForPatient(String rut, Consultation consulta) {
        try {
            Patient patient = patients.get(rut);
            if (patient != null) {
                patient.getHistorialMedico().addConsultation(consulta);
                return new Response("success", patient, "");
            }
            return new Response("error", "", "No se encontro ningun paciente con ese rut");
        } catch (Exception e) {
            return new Response("error", "", "Error al procesar la solicitud");
        }
    }

    public Response addAttetionForPatient(String rut, Attention atencion) {
        try {
            Patient patient = patients.get(rut);
            if (patient != null) {
                patient.getHistorialMedico().addAttetion(atencion);
                return new Response("success", patient, "");
            }
            return new Response("error", "", "No se encontro ningun paciente con ese rut");
        } catch (Exception e) {
            return new Response("error", "", "Error al procesar la solicitud");
        }
    }

    public static boolean validatePhone(String telefono) {
        return telefono.matches("^\\+?56?9[0-9]{8}$");
    }

    public static boolean validateEmail(String email) {
        String patron = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(patron);
        return pattern.matcher(email).matches();
    }

    public static boolean validateRut(String rutCompleto) {
        if (!rutCompleto.matches("^[0-9]+-[0-9kK]{1}$"))
            return false;
        String[] tmp = rutCompleto.split("-");
        String digv = tmp[1];
        String rut = tmp[0];
        if (digv.equalsIgnoreCase("K"))
            digv = "k";
        return dv(rut).equals(digv);
    }

    private static String dv(String T) {
        int M = 0;
        int S = 1;
        for (int i = T.length() - 1; i >= 0; i--) {
            S = (S + Integer.parseInt(String.valueOf(T.charAt(i))) * (9 - M++ % 6)) % 11;
        }
        return (S != 0) ? String.valueOf(S - 1) : "k";
    }
}
