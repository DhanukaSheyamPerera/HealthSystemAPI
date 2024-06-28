/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.healthsystemapi.api.util.patient;

/**
 *
 * @author dhanu
 */


/**
 * Represents a patient's medical history, including past illnesses, treatments, and allergies.
 */
public class MedicalHistory {
    private String pastIllnesses;
    private String pastTreatments;
    private String allergies;

    /**
     * Default constructor for creating an empty MedicalHistory object.
     */
    public MedicalHistory(){}

    /**
     * Constructs a MedicalHistory object with specified details of past illnesses, treatments, and allergies.
     * @param pastIllnesses A string detailing the patient's past illnesses.
     * @param pastTreatments A string detailing the patient's past treatments.
     * @param allergies A string detailing the patient's allergies.
     */
    public MedicalHistory(String pastIllnesses, String pastTreatments, String allergies) {
        this.pastIllnesses = pastIllnesses;
        this.pastTreatments = pastTreatments;
        this.allergies = allergies;
    }

    /**
     * Gets the past illnesses of the patient.
     * @return A string of past illnesses.
     */
    public String getPastIllnesses() {
        return pastIllnesses;
    }

    /**
     * Sets the past illnesses of the patient.
     * @param pastIllnesses A string of past illnesses to set.
     */
    public void setPastIllnesses(String pastIllnesses) {
        this.pastIllnesses = pastIllnesses;
    }

    /**
     * Gets the past treatments of the patient.
     * @return A string of past treatments.
     */
    public String getPastTreatments() {
        return pastTreatments;
    }

    /**
     * Sets the past treatments of the patient.
     * @param pastTreatments A string of past treatments to set.
     */
    public void setPastTreatments(String pastTreatments) {
        this.pastTreatments = pastTreatments;
    }

    /**
     * Gets the allergies of the patient.
     * @return A string of allergies.
     */
    public String getAllergies() {
        return allergies;
    }

    /**
     * Sets the allergies of the patient.
     * @param allergies A string of allergies to set.
     */
    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }
}

