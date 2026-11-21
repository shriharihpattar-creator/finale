package com.hospitalsystem.model;

/**
 * Member 4 - Patient Record with diagnosis.
 */
public class Patient extends Person {

    private final String diagnosis;

    public Patient(String id, String name, int age, String diagnosis) {
        super(id, name, age);
        this.diagnosis = diagnosis;
    }

    public String getDisease() {
        return diagnosis;
    }

    @Override
    public String getRole() {
        return "Patient";
    }

    @Override
    public String getDetails() {
        return super.getDetails() + " | Diagnosis: " + diagnosis;
    }
}
