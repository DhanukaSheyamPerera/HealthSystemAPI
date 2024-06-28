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
 * Custom exception class for handling person-related exceptions.
 * Extends the Exception class to provide detailed information about person-specific issues.
 */
public class CustomPersonException extends Exception {

    /**
     * Constructs a new CustomPersonException with the specified detail message.
     * @param message the detail message.
     */
    public CustomPersonException(String message) {
        super(message);
    }

    /**
     * Factory method for creating an instance of CustomPersonException when input fields contain invalid data.
     * @return a new CustomPersonException instance with a predefined message.
     */
    public static CustomPersonException invalidFieldValues() {
        return new CustomPersonException("Input fields contain invalid data options!");
    }

    /**
     * Factory method for creating an instance of CustomPersonException when a person does not exist.
     * @param id the ID of the non-existent person.
     * @return a new CustomPersonException instance with a predefined message including the ID.
     */
    public static CustomPersonException personDoesNotExist(String id) {
        return new CustomPersonException("The person with id " + id + " does not exist!");
    }
    
    /**
     * Factory method for creating an instance of CustomPersonException when a person ID format is invalid.
     * @return a new CustomPersonException instance with a predefined message.
     */
    public static CustomPersonException personIDFormatInvalid(){
        return new CustomPersonException("The person ID is not valid!");
    }
}
