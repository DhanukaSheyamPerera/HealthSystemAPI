/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.healthsystemapi.api.model;

/**
 *
 * @author dhanu
 */

/**
 * The Appointment class represents an appointment in the health system.
 * It contains details about the appointment date, time, and the associated doctor and patient.
 */
public class Appointment {

    private String id; // Unique identifier for the appointment
    private String date; // Date of the appointment
    private String time; // Time of the appointment
    private String doctorID; // Identifier for the doctor assigned to the appointment
    private String patientID; // Identifier for the patient assigned to the appointment

    /**
     * Default constructor for creating an instance of Appointment without parameters.
     */
    public Appointment() {
    }

    /**
     * Constructor for creating an instance of Appointment with date, time, doctorID, and patientID.
     * @param date The date of the appointment.
     * @param time The time of the appointment.
     * @param doctorID The unique identifier of the doctor.
     * @param patientID The unique identifier of the patient.
     */
    public Appointment(String date, String time, String doctorID, String patientID) {
        this.id = null;
        this.date = date;
        this.time = time;
        this.doctorID = doctorID;
        this.patientID = patientID;
    }

    /**
     * Constructor for creating an instance of Appointment with id, date, time, doctorID, and patientID.
     * @param id The unique identifier for the appointment.
     * @param date The date of the appointment.
     * @param time The time of the appointment.
     * @param doctorID The unique identifier of the doctor.
     * @param patientID The unique identifier of the patient.
     */
    public Appointment(String id, String date, String time, String doctorID, String patientID) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.doctorID = doctorID;
        this.patientID = patientID;
    }

    /**
     * Retrieves the unique identifier of the appointment.
     * @return A string representing the appointment's unique identifier.
     */
    public String getID() {
        return id;
    }

    /**
     * Updates the unique identifier of the appointment.
     * @param id A string containing the new identifier.
     */
    public void setID(String id) {
        this.id = id;
    }

    /**
     * Retrieves the date of the appointment.
     * @return A string representing the date of the appointment.
     */
    public String getDate() {
        return date;
    }

    /**
     * Updates the date of the appointment.
     * @param date A string containing the new date.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Retrieves the time of the appointment.
     * @return A string representing the time of the appointment.
     */
    public String getTime() {
        return time;
    }

    /**
     * Updates the time of the appointment.
     * @param time A string containing the new time.
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Retrieves the unique identifier of the doctor for the appointment.
     * @return A string representing the doctor's unique identifier.
     */
    public String getDoctorID() {
        return doctorID;
    }

    /**
     * Updates the unique identifier of the doctor for the appointment.
     * @param doctorID A string containing the new doctor's identifier.
     */
    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    /**
     * Retrieves the unique identifier of the patient for the appointment.
     * @return A string representing the patient's unique identifier.
     */
    public String getPatientID() {
        return patientID;
    }

    /**
     * Updates the unique identifier of the patient for the appointment.
     * @param patientID A string containing the new patient's identifier.
     */
    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }
}
