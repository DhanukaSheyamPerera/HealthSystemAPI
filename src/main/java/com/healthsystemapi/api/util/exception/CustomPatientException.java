/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.healthsystemapi.api.util.exception;

/**
 *
 * @author dhanu
 */


/**
 * Custom exception class for handling patient-related exceptions.
 * Extends the Exception class to provide detailed information about patient-specific issues.
 */
public class CustomPatientException extends Exception {

    /**
     * Constructs a new CustomPatientException with the specified detail message.
     * @param message the detail message.
     */
    public CustomPatientException(String message) {
        super(message);
    }

    /**
     * Factory method for creating an instance of CustomPatientException when input fields contain invalid data.
     * @return a new CustomPatientException instance with a predefined message.
     */
    public static CustomPatientException invalidFieldValues() {
        return new CustomPatientException("Input fields contain invalid data options!");
    }

    /**
     * Factory method for creating an instance of CustomPatientException when a patient does not exist.
     * @param id the ID of the non-existent patient.
     * @return a new CustomPatientException instance with a predefined message including the ID.
     */
    public static CustomPatientException patientDoesNotExist(String id) {
        return new CustomPatientException("The patient with id " + id + " does not exist!");
    }
    
    /**
     * Factory method for creating an instance of CustomPatientException when a patient ID format is invalid.
     * @return a new CustomPatientException instance with a predefined message.
     */
    public static CustomPatientException patientIDFormatInvalid(){
        return new CustomPatientException("The patient ID is not valid!");
    }
}
