/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.healthsystemapi.api.dao;

/**
 *
 * @author dhanu
 */

import java.util.Map;
import java.util.HashMap;

import com.healthsystemapi.api.model.Appointment;
import com.healthsystemapi.api.util.exception.CustomAppointmentException;

/**
 * The AppointmentDAO class is responsible for managing the CRUD operations for Appointment objects.
 * It provides a static Map to simulate a database and methods to interact with this data.
 */
public class AppointmentDAO {

    private static Map<String, Appointment> appointments = new HashMap<>(); // Simulated database of appointments

    /**
     * Retrieves all appointments from the simulated database.
     * @return A Map containing all Appointment objects.
     */
    public static Map<String, Appointment> getAllAppointments() {
        return appointments;
    }

    /**
     * Retrieves an Appointment object by its ID.
     * @param id The ID of the Appointment to retrieve.
     * @return The Appointment object associated with the given ID.
     * @throws CustomAppointmentException If the Appointment does not exist in the database.
     */
    public static Appointment getAppointmentByID(String id) throws CustomAppointmentException {
        Appointment appointment = appointments.get(id);
        if (appointment == null) {
            throw CustomAppointmentException.appointmentDoesNotExist(id);
        } else {
            return appointment;
        }
    }

    /**
     * Adds a new Appointment object to the simulated database.
     * @param appointment The Appointment object to add.
     */
    public static void addAppointment(Appointment appointment){
        appointments.put(appointment.getID(), appointment);
    }
    
    /**
     * Updates an Appointment object in the simulated database by its ID.
     * @param id The ID of the Appointment to update.
     * @param updatedAppointment The updated Appointment object.
     * @return true if the update is successful.
     */
    public static boolean updateAppointmentByID(String id, Appointment updatedAppointment) {
        appointments.put(id, updatedAppointment);
        return true;
    }
    
    /**
     * Deletes an Appointment object from the simulated database by its ID.
     * @param id The ID of the Appointment to delete.
     * @return true if the deletion is successful.
     * @throws CustomAppointmentException If the Appointment does not exist in the database.
     */
    public static boolean deleteAppointmentByID(String id)throws CustomAppointmentException{
        if(appointments.remove(id) == null){
            throw CustomAppointmentException.appointmentDoesNotExist(id);
        } else {
            return true;
        }
    }
}
