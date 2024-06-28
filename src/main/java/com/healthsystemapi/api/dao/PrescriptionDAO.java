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

import com.healthsystemapi.api.model.Prescription;
import com.healthsystemapi.api.util.exception.CustomPrescriptionException;

/**
 * The PrescriptionDAO class is responsible for managing the CRUD operations for Prescription objects.
 * It provides a static Map to simulate a database and methods to interact with this data.
 */
public class PrescriptionDAO {

    private static Map<String, Prescription> prescriptions = new HashMap<>(); // Simulated database of prescriptions

    /**
     * Retrieves all prescriptions from the simulated database.
     * @return A Map containing all Prescription objects.
     */
    public static Map<String, Prescription> getAllPrescriptions() {
        return prescriptions;
    }

    /**
     * Retrieves a Prescription object by its ID.
     * @param id The ID of the Prescription to retrieve.
     * @return The Prescription object associated with the given ID.
     * @throws CustomPrescriptionException If the Prescription does not exist in the database.
     */
    public static Prescription getPrescriptionByID(String id) throws CustomPrescriptionException {
        Prescription prescription = prescriptions.get(id);
        if (prescription == null) {
            throw CustomPrescriptionException.prescriptionDoesNotExist(id);
        } else {
            return prescription;
        }
    }

    /**
     * Adds a new Prescription object to the simulated database.
     * @param prescription The Prescription object to add.
     */
    public static void addPrescription(Prescription prescription){
        prescriptions.put(prescription.getID(), prescription);
    }
    
    /**
     * Updates a Prescription object in the simulated database by its ID.
     * @param id The ID of the Prescription to update.
     * @param updatedPrescription The updated Prescription object.
     * @return true if the update is successful.
     */
    public static boolean updatePrescriptionByID(String id, Prescription updatedPrescription) {
        prescriptions.put(id, updatedPrescription);
        return true;
    }
    
    /**
     * Deletes a Prescription object from the simulated database by its ID.
     * @param id The ID of the Prescription to delete.
     * @return true if the deletion is successful.
     * @throws CustomPrescriptionException If the Prescription does not exist in the database.
     */
    public static boolean deletePrescriptionByID(String id)throws CustomPrescriptionException{
        if(prescriptions.remove(id) == null){
            throw CustomPrescriptionException.prescriptionDoesNotExist(id);
        } else {
            return true;
        }
    }
}
