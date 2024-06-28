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
 * Custom exception class for handling appointment-related exceptions.
 * Extends the Exception class to provide detailed information about appointment-specific issues.
 */
public class CustomAppointmentException extends Exception {

    /**
     * Constructs a new CustomAppointmentException with the specified detail message.
     * @param message the detail message.
     */
    public CustomAppointmentException(String message) {
        super(message);
    }

    /**
     * Factory method for creating an instance of CustomAppointmentException when input fields contain invalid data.
     * @return a new CustomAppointmentException instance with a predefined message.
     */
    public static CustomAppointmentException invalidFieldValues() {
        return new CustomAppointmentException("Input fields contain invalid data options!");
    }

    /**
     * Factory method for creating an instance of CustomAppointmentException when an appointment does not exist.
     * @param id the ID of the non-existent appointment.
     * @return a new CustomAppointmentException instance with a predefined message including the ID.
     */
    public static CustomAppointmentException appointmentDoesNotExist(String id) {
        return new CustomAppointmentException("The appointment with id " + id + " does not exist!");
    }

    /**
     * Factory method for creating an instance of CustomAppointmentException when an appointment ID format is invalid.
     * @return a new CustomAppointmentException instance with a predefined message.
     */
    public static CustomAppointmentException appointmentIDFormatInvalid(){
        return new CustomAppointmentException("The appointment ID is not valid!");
    }

    /**
     * Factory method for creating an instance of CustomAppointmentException when the date or time format is invalid.
     * @return a new CustomAppointmentException instance with a predefined message about accepted formats.
     */
    public static CustomAppointmentException invalidDateFormat(){
        return new CustomAppointmentException("The date or time format is not valid! Accepted formats are yyyy-MM-dd and HH:mm:ss.");
    }
}
