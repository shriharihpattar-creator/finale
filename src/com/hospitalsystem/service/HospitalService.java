package com.hospitalsystem.service;

import com.hospitalsystem.exception.DoctorNotFoundException;
import com.hospitalsystem.exception.PatientNotFoundException;
import com.hospitalsystem.model.Appointment;
import com.hospitalsystem.model.Doctor;
import com.hospitalsystem.model.Patient;
import com.hospitalsystem.util.DoctorSorter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Member 4 - Service Layer with Billing & Revenue Focus.
 */
public class HospitalService {

    private final Map<String, Patient> patientDatabase = new HashMap<>();
    private final Map<String, Doctor> doctorDatabase = new HashMap<>();
    private final List<Appointment> appointmentLedger = new ArrayList<>();

    private int appointmentCounter = 101;

    public HospitalService() {
        // Sample records for immediate verification
        doctorDatabase.put("D1", new Doctor("D1", "Rajesh Sharma", 45, "Cardiologist", 800.0, "Mon, Wed, Fri"));
        doctorDatabase.put("D2", new Doctor("D2", "Priya Patel", 38, "General Physician", 400.0, "Tue, Thu, Sat"));
        doctorDatabase.put("D3", new Doctor("D3", "Amit Verma", 50, "Orthopedic", 600.0, "Mon, Tue, Thu"));

        patientDatabase.put("P1", new Patient("P1", "Rohan Gupta", 30, "Fever & Cough"));
        patientDatabase.put("P2", new Patient("P2", "Sneha Rao", 25, "Joint Pain"));
    }

    public void registerPatient(String id, String name, int age, String disease) {
        if (patientDatabase.containsKey(id)) {
            throw new IllegalArgumentException("Patient ID " + id + " already exists.");
        }
        if (age <= 0) {
            throw new IllegalArgumentException("Age must be greater than zero.");
        }
        patientDatabase.put(id, new Patient(id, name, age, disease));
    }

    public void addDoctor(String id, String name, int age, String specialization, double fee, String availableDays) {
        if (doctorDatabase.containsKey(id)) {
            throw new IllegalArgumentException("Doctor ID " + id + " is already registered.");
        }
        if (fee < 0) {
            throw new IllegalArgumentException("Fee cannot be negative.");
        }
        doctorDatabase.put(id, new Doctor(id, name, age, specialization, fee, availableDays));
    }

    public Appointment bookAppointment(String patientId, String doctorId, String date, boolean isEmergency)
            throws PatientNotFoundException, DoctorNotFoundException {

        Patient patient = patientDatabase.get(patientId);
        if (patient == null) {
            throw new PatientNotFoundException("Patient ID " + patientId + " not found.");
        }

        Doctor doctor = doctorDatabase.get(doctorId);
        if (doctor == null) {
            throw new DoctorNotFoundException("Doctor ID " + doctorId + " not found.");
        }

        String apptId = "APT" + (appointmentCounter++);
        Appointment appt = new Appointment(apptId, patient, doctor, date, isEmergency);

        if (isEmergency) {
            appointmentLedger.add(0, appt);
        } else {
            appointmentLedger.add(appt);
        }

        return appt;
    }

    // Settles bill and updates payment status
    public void payBill(String appointmentId) {
        for (Appointment appt : appointmentLedger) {
            if (appt.getId().equalsIgnoreCase(appointmentId)) {
                if (appt.isPaid()) {
                    throw new IllegalArgumentException("Appointment " + appointmentId + " has already been paid.");
                }
                appt.markAsPaid();
                return;
            }
        }
        throw new IllegalArgumentException("Appointment " + appointmentId + " was not found in records.");
    }

    // Calculates aggregate hospital revenue from paid bills
    public double getTotalRevenue() {
        double grandTotal = 0.0;
        for (Appointment a : appointmentLedger) {
            if (a.isPaid()) {
                grandTotal += a.getAmount();
            }
        }
        return grandTotal;
    }

    public List<Patient> getPatients() {
        return new ArrayList<>(patientDatabase.values());
    }

    public List<Doctor> getDoctors() {
        return new ArrayList<>(doctorDatabase.values());
    }

    public List<Doctor> getDoctorsSortedByFee() {
        List<Doctor> sorted = new ArrayList<>(doctorDatabase.values());
        DoctorSorter.sortByFeeAscending(sorted);
        return sorted;
    }

    public List<Appointment> getAppointments() {
        return new ArrayList<>(appointmentLedger);
    }
}
