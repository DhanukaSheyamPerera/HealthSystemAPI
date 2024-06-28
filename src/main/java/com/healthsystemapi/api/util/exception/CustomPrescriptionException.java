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
 * Custom exception class for handling prescription-related exceptions.
 * Extends the Exception class to provide detailed information about issues specific to prescriptions.
 */
public class CustomPrescriptionException extends Exception {

    /**
     * Constructs a new CustomPrescriptionException with the specified detail message.
     * @param message the detail message.
     */
    public CustomPrescriptionException(String message) {
        super(message);
    }

    /**
     * Factory method for creating an instance of CustomPrescriptionException when input fields contain invalid data.
     * @return a new CustomPrescriptionException instance with a predefined message.
     */
    public static CustomPrescriptionException invalidFieldValues() {
        return new CustomPrescriptionException("Input fields contain invalid data options!");
    }

    /**
     * Factory method for creating an instance of CustomPrescriptionException when a prescription does not exist.
     * @param id the ID of the non-existent prescription.
     * @return a new CustomPrescriptionException instance with a predefined message including the ID.
     */
    public static CustomPrescriptionException prescriptionDoesNotExist(String id) {
        return new CustomPrescriptionException("The prescription with id " + id + " does not exist!");
    }
    
    /**
     * Factory method for creating an instance of CustomPrescriptionException when a prescription ID format is invalid.
     * @return a new CustomPrescriptionException instance with a predefined message.
     */
    public static CustomPrescriptionException prescriptionIDFormatInvalid(){
        return new CustomPrescriptionException("The prescription ID is not valid!");
    }
}
