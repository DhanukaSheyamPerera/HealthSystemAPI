/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.healthsystemapi.api.util;

/**
 *
 * @author dhanu
 */


import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import com.healthsystemapi.api.util.exception.CustomAppointmentException;

/**
 * Utility class for validating date and time formats.
 */
public class DateTimeValidator {

    /**
     * Validates the format of the provided date and time strings.
     * @param date the date string to validate
     * @param time the time string to validate
     * @throws CustomAppointmentException if the date or time format is invalid
     */
    public static void validateDateFormat(String date, String time) throws CustomAppointmentException {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        try {
            dateFormatter.parse(date);
            timeFormatter.parse(time);
        } catch (DateTimeParseException e) {
            throw CustomAppointmentException.invalidDateFormat();
        }
    }
}

