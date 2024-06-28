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
 * Custom exception class for handling medical record-related exceptions.
 * Extends the Exception class to provide detailed information about issues specific to medical records.
 */
public class CustomMedicalRecordException extends Exception {

    /**
     * Constructs a new CustomMedicalRecordException with the specified detail message.
     * @param message the detail message.
     */
    public CustomMedicalRecordException(String message) {
        super(message);
    }

    /**
     * Factory method for creating an instance of CustomMedicalRecordException when input fields contain invalid data.
     * @return a new CustomMedicalRecordException instance with a predefined message.
     */
    public static CustomMedicalRecordException invalidFieldValues() {
        return new CustomMedicalRecordException("Input fields contain invalid data options!");
    }

    /**
     * Factory method for creating an instance of CustomMedicalRecordException when a medical record does not exist.
     * @param id the ID of the non-existent medical record.
     * @return a new CustomMedicalRecordException instance with a predefined message including the ID.
     */
    public static CustomMedicalRecordException medicalRecordDoesNotExist(String id) {
        return new CustomMedicalRecordException("The medical record with id " + id + " does not exist!");
    }
    
    /**
     * Factory method for creating an instance of CustomMedicalRecordException when a medical record ID format is invalid.
     * @return a new CustomMedicalRecordException instance with a predefined message.
     */
    public static CustomMedicalRecordException medicalRecordIDFormatInvalid(){
        return new CustomMedicalRecordException("The medical record ID is not valid!");
    }
}
