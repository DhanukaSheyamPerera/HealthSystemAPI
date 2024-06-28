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
 * Custom exception class for handling doctor-related exceptions.
 * Extends the Exception class to provide detailed information about doctor-specific issues.
 */
public class CustomDoctorException extends Exception {

    /**
     * Constructs a new CustomDoctorException with the specified detail message.
     * @param message the detail message.
     */
    public CustomDoctorException(String message) {
        super(message);
    }

    /**
     * Factory method for creating an instance of CustomDoctorException when input fields contain invalid data.
     * @return a new CustomDoctorException instance with a predefined message.
     */
    public static CustomDoctorException invalidFieldValues() {
        return new CustomDoctorException("Input fields contain invalid data options!");
    }

    /**
     * Factory method for creating an instance of CustomDoctorException when a doctor does not exist.
     * @param id the ID of the non-existent doctor.
     * @return a new CustomDoctorException instance with a predefined message including the ID.
     */
    public static CustomDoctorException doctorDoesNotExist(String id) {
        return new CustomDoctorException("The doctor with id " + id + " does not exist!");
    }
    
    /**
     * Factory method for creating an instance of CustomDoctorException when a doctor ID format is invalid.
     * @return a new CustomDoctorException instance with a predefined message.
     */
    public static CustomDoctorException doctorIDFormatInvalid(){
        return new CustomDoctorException("The doctor ID is not valid!");
    }
}
