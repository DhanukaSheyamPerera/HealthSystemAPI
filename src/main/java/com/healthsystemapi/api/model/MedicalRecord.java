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
 * The MedicalRecord class represents a patient's medical record in the health system.
 * It contains details about diagnoses, test results, medications, and treatments.
 */
public class MedicalRecord {

    private String id; // Unique identifier for the medical record
    private String patientID; // Identifier for the patient associated with the medical record
    private String diagnoses; // Diagnoses associated with the patient
    private String testResults; // Test results associated with the patient
    private String medications; // Medications prescribed to the patient
    private String treatments; // Treatments administered to the patient

    /**
     * Default constructor for creating an instance of MedicalRecord without parameters.
     */
    public MedicalRecord() {
    }

    /**
     * Constructor for creating an instance of MedicalRecord with patientID, diagnoses, test results, medications, and treatments.
     * @param patientID The unique identifier of the patient.
     * @param diagnoses The diagnoses associated with the patient.
     * @param testResults The test results associated with the patient.
     * @param medications The medications prescribed to the patient.
     * @param treatments The treatments administered to the patient.
     */
    public MedicalRecord(String patientID, String diagnoses, String testResults, String medications, String treatments) {
        this.id = null;
        this.patientID = patientID;
        this.diagnoses = diagnoses;
        this.testResults = testResults;
        this.medications = medications;
        this.treatments = treatments;
    }

    /**
     * Constructor for creating an instance of MedicalRecord with id, patientID, diagnoses, test results, medications, and treatments.
     * @param id The unique identifier for the medical record.
     * @param patientID The unique identifier of the patient.
     * @param diagnoses The diagnoses associated with the patient.
     * @param testResults The test results associated with the patient.
     * @param medications The medications prescribed to the patient.
     * @param treatments The treatments administered to the patient.
     */
    public MedicalRecord(String id, String patientID, String diagnoses, String testResults, String medications, String treatments) {
        this.id = id;
        this.patientID = patientID;
        this.diagnoses = diagnoses;
        this.testResults = testResults;
        this.medications = medications;
        this.treatments = treatments;
    }

    /**
     * Retrieves the unique identifier of the medical record.
     * @return A string representing the medical record's unique identifier.
     */
    public String getID() {
        return id;
    }

    /**
     * Updates the unique identifier of the medical record.
     * @param id A string containing the new identifier.
     */
    public void setID(String id) {
        this.id = id;
    }

    /**
     * Retrieves the unique identifier of the patient associated with the medical record.
     * @return A string representing the patient's unique identifier.
     */
    public String getPatientID() {
        return patientID;
    }

    /**
     * Updates the unique identifier of the patient associated with the medical record.
     * @param patientID A string containing the new patient's identifier.
     */
    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    /**
     * Retrieves the diagnoses associated with the patient.
     * @return A string representing the patient's diagnoses.
     */
    public String getDiagnoses() {
        return diagnoses;
    }

    /**
     * Updates the diagnoses associated with the patient.
     * @param diagnoses A string containing the new diagnoses.
     */
    public void setDiagnoses(String diagnoses) {
        this.diagnoses = diagnoses;
    }

    /**
     * Retrieves the test results associated with the patient.
     * @return A string representing the patient's test results.
     */
    public String getTestResults() {
        return testResults;
    }

    /**
     * Updates the test results associated with the patient.
     * @param testResults A string containing the new test results.
     */
    public void setTestResults(String testResults) {
        this.testResults = testResults;
    }

    /**
     * Retrieves the medications prescribed to the patient.
     * @return A string representing the patient's medications.
     */
    public String getMedications() {
        return medications;
    }

    /**
     * Updates the medications prescribed to the patient.
     * @param medications A string containing the new medications.
     */
    public void setMedications(String medications) {
        this.medications = medications;
    }

    /**
     * Retrieves the treatments administered to the patient.
     * @return A string representing the patient's treatments.
     */
    public String getTreatments() {
        return treatments;
    }

    /**
     * Updates the treatments administered to the patient.
     * @param treatments A string containing the new treatments.
     */
    public void setTreatments(String treatments) {
        this.treatments = treatments;
    }
}
