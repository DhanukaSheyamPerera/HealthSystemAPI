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

import com.healthsystemapi.api.model.Patient;
import com.healthsystemapi.api.util.exception.CustomPatientException;

/**
 * The PatientDAO class is responsible for managing the CRUD operations for Patient objects.
 * It provides a static Map to simulate a database and methods to interact with this data.
 */
public class PatientDAO {

    private static Map<String, Patient> patients = new HashMap<>(); // Simulated database of patients

    public static int patientCount = 0; // Counter for the number of patients

    /**
     * Retrieves all patients from the simulated database.
     * @return A Map containing all Patient objects.
     */
    public static Map<String, Patient> getAllPatients() {
        return patients;
    }

    /**
     * Retrieves a Patient object by its ID.
     * @param id The ID of the Patient to retrieve.
     * @return The Patient object associated with the given ID.
     * @throws CustomPatientException If the Patient does not exist in the database.
     */
    public static Patient getPatientByID(String id) throws CustomPatientException {
        Patient patient = patients.get(id);
        if (patient == null) {
            throw CustomPatientException.patientDoesNotExist(id);
        } else {
            return patient;
        }
    }

    /**
     * Adds a new Patient object to the simulated database.
     * @param patient The Patient object to add.
     */
    public static void addPatient(Patient patient){
        patients.put(patient.getID(), patient);
        patientCount++;
    }
    
    /**
     * Updates a Patient object in the simulated database by its ID.
     * @param id The ID of the Patient to update.
     * @param updatedPatient The updated Patient object.
     * @return true if the update is successful.
     */
    public static boolean updatePatientByID(String id, Patient updatedPatient) {
        patients.put(id, updatedPatient);
        return true;
    }
    
    /**
     * Deletes a Patient object from the simulated database by its ID.
     * @param id The ID of the Patient to delete.
     * @return true if the deletion is successful.
     * @throws CustomPatientException If the Patient does not exist in the database.
     */
    public static boolean deletePatientByID(String id)throws CustomPatientException{
        if(patients.remove(id) == null){
            throw CustomPatientException.patientDoesNotExist(id);
        } else {
            return true;
        }
    }
}
