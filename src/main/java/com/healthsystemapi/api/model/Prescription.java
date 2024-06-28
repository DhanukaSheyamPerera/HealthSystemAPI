/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.healthsystemapi.api.model;

/**
 *
 * @author dhanu
 */

/**
 * The Prescription class represents a medical prescription in the health system.
 * It contains details about the medication, dosage, instructions, and duration for a patient's treatment.
 */
public class Prescription {

    private String id; // Unique identifier for the prescription
    private String patientID; // Identifier for the patient to whom the prescription is issued
    private String medication; // Name of the medication prescribed
    private String dosage; // Dosage of the medication
    private String instructions; // Instructions for taking the medication
    private String duration; // Duration for which the medication is prescribed

    /**
     * Default constructor for creating an instance of Prescription without parameters.
     */
    public Prescription() {
    }

    /**
     * Constructor for creating an instance of Prescription with patientID, doctorID, medication, dosage, instructions, and duration.
     * @param patientID The unique identifier of the patient.
     * @param medication The name of the medication prescribed.
     * @param dosage The dosage of the medication.
     * @param instructions The instructions for taking the medication.
     * @param duration The duration for which the medication is prescribed.
     */
    public Prescription(String patientID, String medication, String dosage, String instructions, String duration) {
        this.id = null;
        this.patientID = patientID;
        this.medication = medication;
        this.dosage = dosage;
        this.instructions = instructions;
        this.duration = duration;
    }

    /**
     * Constructor for creating an instance of Prescription with id, patientID, doctorID, medication, dosage, instructions, and duration.
     * @param id The unique identifier for the prescription.
     * @param patientID The unique identifier of the patient.
     * @param medication The name of the medication prescribed.
     * @param dosage The dosage of the medication.
     * @param instructions The instructions for taking the medication.
     * @param duration The duration for which the medication is prescribed.
     */
    public Prescription(String id, String patientID, String medication, String dosage, String instructions, String duration) {
        this.id = id;
        this.patientID = patientID;
        this.medication = medication;
        this.dosage = dosage;
        this.instructions = instructions;
        this.duration = duration;
    }

    /**
     * Retrieves the unique identifier of the prescription.
     * @return A string representing the prescription's unique identifier.
     */
    public String getID() {
        return id;
    }

    /**
     * Updates the unique identifier of the prescription.
     * @param id A string containing the new identifier.
     */
    public void setID(String id) {
        this.id = id;
    }

    /**
     * Retrieves the unique identifier of the patient for the prescription.
     * @return A string representing the patient's unique identifier.
     */
    public String getPatientID() {
        return patientID;
    }

    /**
     * Updates the unique identifier of the patient for the prescription.
     * @param patientID A string containing the new patient's identifier.
     */
    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    /**
     * Retrieves the name of the medication prescribed.
     * @return A string representing the medication.
     */
    public String getMedication() {
        return medication;
    }

    /**
     * Updates the name of the medication prescribed.
     * @param medication A string containing the new medication.
     */
    public void setMedication(String medication) {
        this.medication = medication;
    }

    /**
     * Retrieves the dosage of the medication.
     * @return A string representing the dosage.
     */
    public String getDosage() {
        return dosage;
    }

    /**
     * Updates the dosage of the medication.
     * @param dosage A string containing the new dosage.
     */
    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    /**
     * Retrieves the instructions for taking the medication.
     * @return A string representing the instructions.
     */
    public String getInstructions() {
        return instructions;
    }

    /**
     * Updates the instructions for taking the medication.
     * @param instructions A string containing the new instructions.
     */
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    /**
     * Retrieves the duration for which the medication is prescribed.
     * @return A string representing the duration.
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Updates the duration for which the medication is prescribed.
     * @param duration A string containing the new duration.
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }
}
