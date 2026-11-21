package com.hospitalsystem.model;

/**
 * Member 4 - Appointment & Billing Model.
 */
public class Appointment {

    private final String appointmentId;
    private final Patient patient;
    private final Doctor doctor;
    private final String appointmentDate;
    private final boolean emergencyCase;
    private boolean isPaid;

    public Appointment(String appointmentId, Patient patient, Doctor doctor, String appointmentDate, boolean emergencyCase) {
        this.appointmentId = appointmentId;
        this.patient = patient;
        this.doctor = doctor;
        this.appointmentDate = appointmentDate;
        this.emergencyCase = emergencyCase;
        this.isPaid = false;
    }

    public String getId() {
        return appointmentId;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public String getDate() {
        return appointmentDate;
    }

    public boolean isEmergency() {
        return emergencyCase;
    }

    public double getAmount() {
        return doctor.getFee();
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void markAsPaid() {
        this.isPaid = true;
    }

    public String getDetails() {
        String type = emergencyCase ? "[EMERGENCY]" : "[GENERAL]";
        String status = isPaid ? "PAID" : "DUE";
        return type + " ID: " + appointmentId 
                + " | Patient: " + patient.getName() 
                + " | Doctor: Dr. " + doctor.getName() + " (" + doctor.getSpecialization() + ")"
                + " | Date: " + appointmentDate 
                + " | Amount: Rs." + getAmount() 
                + " | Status: " + status;
    }
}
