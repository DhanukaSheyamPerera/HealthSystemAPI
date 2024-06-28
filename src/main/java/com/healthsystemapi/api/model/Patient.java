/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.healthsystemapi.api.model;

/**
 *
 * @author dhanu
 */

import com.healthsystemapi.api.util.customenum.HealthStatus;
import com.healthsystemapi.api.util.patient.MedicalHistory;

/**
 * The Patient class represents a patient in the health system.
 * It extends the Person class with additional attributes related to health status and medical history.
 */
public class Patient extends Person {

    private HealthStatus healthStatus; // The current health status of the patient
    private MedicalHistory medicalHistory; // The medical history of the patient

    /**
     * Default constructor for creating an instance of Patient without parameters.
     */
    public Patient() {}

    /**
     * Constructor for creating an instance of Patient with health status, medical history, username, contact information, and address.
     * @param healthStatus The current health status of the patient.
     * @param medicalHistory The medical history of the patient.
     * @param username The username of the person.
     * @param contactInformation The contact information of the person.
     * @param address The address of the person.
     */
    public Patient(HealthStatus healthStatus, MedicalHistory medicalHistory, String username, String contactInformation, String address) {
        super(username, contactInformation, address);
        this.healthStatus = healthStatus;
        this.medicalHistory = medicalHistory;
    }

    /**
     * Constructor for creating an instance of Patient with id, health status, medical history, username, contact information, and address.
     * @param id The unique identifier for the person.
     * @param healthStatus The current health status of the patient.
     * @param medicalHistory The medical history of the patient.
     * @param username The username of the person.
     * @param contactInformation The contact information of the person.
     * @param address The address of the person.
     */
    public Patient(HealthStatus healthStatus, MedicalHistory medicalHistory, String id, String username, String contactInformation, String address) {
        super(id, username, contactInformation, address);
        this.healthStatus = healthStatus;
        this.medicalHistory = medicalHistory;
    }

    /**
     * Retrieves the health status of the patient.
     * @return The current health status of the patient.
     */
    public HealthStatus getHealthStatus() {
        return healthStatus;
    }

    /**
     * Updates the health status of the patient.
     * @param healthStatus The new health status to be set for the patient.
     */
    public void setHealthStatus(HealthStatus healthStatus) {
        this.healthStatus = healthStatus;
    }

    /**
     * Retrieves the medical history of the patient.
     * @return The medical history of the patient.
     */
    public MedicalHistory getMedicalHistory() {
        return medicalHistory;
    }

    /**
     * Updates the medical history of the patient.
     * @param medicalHistory The new medical history to be set for the patient.
     */
    public void setMedicalHistory(MedicalHistory medicalHistory) {
        this.medicalHistory = medicalHistory;
    }
}

