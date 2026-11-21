# Hospital Management System - Finale Edition

A comprehensive, object-oriented Hospital Management System developed collaboratively by a team of 4 members in Java.

---

## 👥 Team Roles & Module Ownership

| Member | Assigned Role & Module | Key Classes & Algorithms | Core Concepts Applied |
| :--- | :--- | :--- | :--- |
| **Member 1** | **Patient Registration & Validation** | `Patient.java`, `Person.java` | OOP Inheritance, Encapsulation, HashMap $O(1)$ Lookups |
| **Member 2** | **Doctor Directory & DSA Sorting** | `Doctor.java`, `DoctorSorter.java` | **Selection Sort Algorithm** $O(n^2)$, OOP Inheritance |
| **Member 3** | **Appointment & Emergency Triage** | `Appointment.java`, `Custom Exceptions` | Priority Queueing / Triage (Index 0), Custom Exception Handling |
| **Member 4** | **Billing Desk & Revenue Dashboard** | `HospitalService.java`, `Main.java` | Financial Transaction State, Interactive CLI Console loops |

---

## 🏗️ Architecture & Core Concepts

- **Abstraction & Polymorphism:** `Person` acts as an abstract base class with common attributes (`id`, `name`, `age`) and abstract method `getRole()`. `Patient` and `Doctor` extend `Person` and provide specialized polymorphic representations via `getDetails()`.
- **Data Structures & Algorithms (DSA):** `DoctorSorter` implements the **Selection Sort** algorithm to sort doctor records in-place by consultation fee in ascending order with $O(n^2)$ time complexity and $O(1)$ space complexity.
- **Priority Queueing (Emergency Triage):** When booking appointments, emergency cases are automatically inserted at index `0` of the appointment queue, giving emergency patients immediate priority over regular consultations.
- **Exception Handling:** Robust custom exceptions (`DoctorNotFoundException`, `PatientNotFoundException`) prevent invalid operations and unhandled runtime crashes.
- **Billing & Revenue:** Real-time billing settlement tracking with double-payment prevention and total revenue aggregation across paid appointment records.

---

## 🚀 How to Compile and Run

### Prerequisites
- Java Development Kit (JDK 17 or higher)

### Build & Execution
```bash
# Compile all source files
javac -d bin src/com/hospitalsystem/*.java src/com/hospitalsystem/model/*.java src/com/hospitalsystem/service/*.java src/com/hospitalsystem/util/*.java src/com/hospitalsystem/exception/*.java

# Run the console application
java -cp bin com.hospitalsystem.Main
```

---

## 🌿 Feature Branches (Circular Review Chain)

| Branch | Member / Feature | Description |
| :--- | :--- | :--- |
| `main` | Production / Master | Fully integrated Hospital Management System |
| `feature/patient-validation` | Member 1 | Patient models, inheritance & validation |
| `feature/doctor-sorting-dsa` | Member 2 | Doctor entity & Selection Sort algorithm |
| `feature/emergency-triage-priority` | Member 3 | Emergency priority triage & exceptions |
| `feature/billing-desk-revenue` | Member 4 | Billing settlement & revenue reporting |
