package com.hospitalsystem.model;

/**
 * Member 2 - Doctor Entity.
 * Inherits from Person. Contains specialization, consultation fee, and visiting schedule.
 */
public class Doctor extends Person {

    private final String specialty;
    private final double consultationFee;
    private final String visitingDays;

    public Doctor(String docId, String docName, int docAge, String specialty, double consultationFee, String visitingDays) {
        super(docId, docName, docAge);
        this.specialty = specialty;
        this.consultationFee = consultationFee;
        this.visitingDays = visitingDays;
    }

    public String getSpecialization() {
        return specialty;
    }

    public double getFee() {
        return consultationFee;
    }

    public String getAvailableDays() {
        return visitingDays;
    }

    @Override
    public String getRole() {
        return "Doctor";
    }

    @Override
    public String getDetails() {
        return super.getDetails() + " | Specialization: " + specialty 
                + " | Fee: Rs." + consultationFee + " | Schedule: " + visitingDays;
    }
}
