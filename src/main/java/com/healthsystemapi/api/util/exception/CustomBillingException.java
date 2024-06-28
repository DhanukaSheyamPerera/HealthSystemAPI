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
 * Custom exception class for handling billing-related exceptions.
 * Extends the Exception class to provide detailed information about issues specific to billing.
 */
public class CustomBillingException extends Exception {

    /**
     * Constructs a new CustomBillingException with the specified detail message.
     * @param message the detail message.
     */
    public CustomBillingException(String message) {
        super(message);
    }

    /**
     * Factory method for creating an instance of CustomBillingException when input fields contain invalid data.
     * @return a new CustomBillingException instance with a predefined message.
     */
    public static CustomBillingException invalidFieldValues() {
        return new CustomBillingException("Input fields contain invalid data options!");
    }

    /**
     * Factory method for creating an instance of CustomBillingException when a billing record does not exist.
     * @param id the ID of the non-existent billing record.
     * @return a new CustomBillingException instance with a predefined message including the ID.
     */
    public static CustomBillingException billingDoesNotExist(String id) {
        return new CustomBillingException("The billing with id " + id + " does not exist!");
    }
    
    /**
     * Factory method for creating an instance of CustomBillingException when a billing ID format is invalid.
     * @return a new CustomBillingException instance with a predefined message.
     */
    public static CustomBillingException billingIDFormatInvalid(){
        return new CustomBillingException("The billing ID is not valid!");
    }
}
