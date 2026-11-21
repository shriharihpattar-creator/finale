package com.hospitalsystem;

import com.hospitalsystem.exception.DoctorNotFoundException;
import com.hospitalsystem.exception.PatientNotFoundException;
import com.hospitalsystem.model.Appointment;
import com.hospitalsystem.model.Doctor;
import com.hospitalsystem.model.Patient;
import com.hospitalsystem.service.HospitalService;

import java.util.List;
import java.util.Scanner;

/**
 * Hospital Management System - Integrated Console Application
 * Collaborative Team Modules:
 * - Member 1: Patient Registration & Record Validation
 * - Member 2: Doctor Directory Management & Selection Sort DSA
 * - Member 3: Appointment Scheduling & Emergency Triage Queue
 * - Member 4: Billing Desk, Revenue Ledger & Interactive Console Dashboard
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final HospitalService hospitalService = new HospitalService();

    public static void main(String[] args) {
        System.out.println("==================================================================");
        System.out.println("            HOSPITAL MANAGEMENT SYSTEM - FINALE EDITION           ");
        System.out.println("   Collaborative Project: Member 1, Member 2, Member 3 & Member 4 ");
        System.out.println("==================================================================");

        boolean running = true;
        while (running) {
            showMenu();
            int choice = getIntInput("Choose an option (1-9): ");

            switch (choice) {
                case 1:
                    registerPatient();
                    break;
                case 2:
                    listAllPatients();
                    break;
                case 3:
                    addDoctor();
                    break;
                case 4:
                    showDoctorDirectory();
                    break;
                case 5:
                    bookPatientAppointment();
                    break;
                case 6:
                    listAllAppointments();
                    break;
                case 7:
                    processBillPayment();
                    break;
                case 8:
                    viewRevenueSummary();
                    break;
                case 9:
                    running = false;
                    System.out.println("Thank you for using the Hospital Management System. Goodbye!");
                    break;
                default:
                    System.out.println("Please enter a valid choice between 1 and 9.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n----------------- HOSPITAL SYSTEM DASHBOARD -----------------");
        System.out.println("1. Register New Patient                [Member 1 - Patient]");
        System.out.println("2. View All Registered Patients        [Member 1 - Patient]");
        System.out.println("3. Add New Doctor to Directory         [Member 2 - Doctor]");
        System.out.println("4. View Doctors Sorted by Fee (DSA)    [Member 2 - Selection Sort]");
        System.out.println("5. Book Appointment (Regular/Emergency)[Member 3 - Triage Queue]");
        System.out.println("6. View Appointment Queue              [Member 3 - Appointments]");
        System.out.println("7. Settle / Pay Appointment Bill       [Member 4 - Billing Desk]");
        System.out.println("8. View Total Hospital Revenue Report  [Member 4 - Financials]");
        System.out.println("9. Exit Application");
        System.out.println("-------------------------------------------------------------");
    }

    private static void registerPatient() {
        System.out.println("\n[Member 1: Register Patient]");
        String id = getStringInput("Patient ID (e.g. P101): ");
        String name = getStringInput("Patient Name: ");
        int age = getIntInput("Patient Age: ");
        String disease = getStringInput("Diagnosis / Symptoms: ");

        try {
            hospitalService.registerPatient(id, name, age, disease);
            System.out.println("Patient " + name + " (ID: " + id + ") successfully registered.");
        } catch (IllegalArgumentException e) {
            System.out.println("Registration failed: " + e.getMessage());
        }
    }

    private static void listAllPatients() {
        System.out.println("\n[Registered Patients]");
        List<Patient> patients = hospitalService.getPatients();
        if (patients.isEmpty()) {
            System.out.println("No patients currently registered.");
            return;
        }
        for (Patient p : patients) {
            System.out.println(p.getDetails());
        }
    }

    private static void addDoctor() {
        System.out.println("\n[Member 2: Add Doctor]");
        String id = getStringInput("Doctor ID (e.g. D101): ");
        String name = getStringInput("Doctor Name: Dr. ");
        int age = getIntInput("Doctor Age: ");
        String spec = getStringInput("Specialization (e.g. Cardiologist): ");
        double fee = getDoubleInput("Consultation Fee (Rs): ");
        String days = getStringInput("Working Days (e.g. Mon, Wed, Fri): ");

        try {
            hospitalService.addDoctor(id, name, age, spec, fee, days);
            System.out.println("Doctor Dr. " + name + " added to directory.");
        } catch (IllegalArgumentException e) {
            System.out.println("Operation failed: " + e.getMessage());
        }
    }

    private static void showDoctorDirectory() {
        System.out.println("\n[Member 2: Doctors Sorted by Consultation Fee - Selection Sort O(n^2)]");
        List<Doctor> docs = hospitalService.getDoctorsSortedByFee();
        if (docs.isEmpty()) {
            System.out.println("No doctors listed in the directory.");
            return;
        }
        for (Doctor d : docs) {
            System.out.println(d.getDetails());
        }
    }

    private static void bookPatientAppointment() {
        System.out.println("\n[Member 3: Book Appointment & Priority Triage]");
        String patientId = getStringInput("Patient ID: ");
        String doctorId = getStringInput("Doctor ID: ");
        String date = getStringInput("Appointment Date (YYYY-MM-DD): ");
        String emg = getStringInput("Is this an EMERGENCY case? (yes/no): ");
        boolean isEmergency = emg.equalsIgnoreCase("yes") || emg.equalsIgnoreCase("y");

        try {
            Appointment appt = hospitalService.bookAppointment(patientId, doctorId, date, isEmergency);
            if (isEmergency) {
                System.out.println(">> EMERGENCY CASE: Triaged to FRONT of queue (index 0)!");
            }
            System.out.println("Appointment Confirmed! Code: " + appt.getId());
        } catch (PatientNotFoundException | DoctorNotFoundException e) {
            System.out.println("Booking failed: " + e.getMessage());
        }
    }

    private static void listAllAppointments() {
        System.out.println("\n[Appointments Queue]");
        List<Appointment> list = hospitalService.getAppointments();
        if (list.isEmpty()) {
            System.out.println("No appointments currently in queue.");
            return;
        }
        for (Appointment a : list) {
            System.out.println(a.getDetails());
        }
    }

    private static void processBillPayment() {
        System.out.println("\n[Member 4: Billing Desk]");
        listAllAppointments();
        String apptId = getStringInput("Enter Appointment ID to settle bill: ");

        try {
            hospitalService.payBill(apptId);
            System.out.println("Payment Successful for appointment: " + apptId);
            System.out.printf("Updated Total Hospital Revenue: Rs. %.2f%n", hospitalService.getTotalRevenue());
        } catch (IllegalArgumentException e) {
            System.out.println("Payment Error: " + e.getMessage());
        }
    }

    private static void viewRevenueSummary() {
        System.out.println("\n================ REVENUE REPORT (MEMBER 4) ================");
        double revenue = hospitalService.getTotalRevenue();
        System.out.printf("Total Realized Hospital Revenue: Rs. %.2f%n", revenue);
        System.out.println("==========================================================");
    }

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private static double getDoubleInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid decimal number.");
            }
        }
    }
}
