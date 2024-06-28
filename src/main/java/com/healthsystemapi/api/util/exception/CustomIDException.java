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
 * Custom exception class for handling ID-related errors.
 */
public class CustomIDException extends Exception {

    /**
     * Constructs a new CustomIDException with the specified detail message.
     * @param message the detail message.
     */
    public CustomIDException(String message) {
        super(message);
    }

    /**
     * Factory method to create a new CustomIDException indicating invalid ID format.
     * @return a new instance of CustomIDException with a predefined message.
     */
    public static CustomIDException idFormatInvalid(){
        return new CustomIDException("The ID format is not valid!");
    }
}
