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

import com.healthsystemapi.api.model.MedicalRecord;
import com.healthsystemapi.api.util.exception.CustomMedicalRecordException;

/**
 * The MedicalRecordDAO class is responsible for managing the CRUD operations for MedicalRecord objects.
 * It provides a static Map to simulate a database and methods to interact with this data.
 */
public class MedicalRecordDAO {

    private static Map<String, MedicalRecord> medicalRecords = new HashMap<>(); // Simulated database of medical records

    /**
     * Retrieves all medical records from the simulated database.
     * @return A Map containing all MedicalRecord objects.
     */
    public static Map<String, MedicalRecord> getAllMedicalRecords() {
        return medicalRecords;
    }

    /**
     * Retrieves a MedicalRecord object by its ID.
     * @param id The ID of the MedicalRecord to retrieve.
     * @return The MedicalRecord object associated with the given ID.
     * @throws CustomMedicalRecordException If the MedicalRecord does not exist in the database.
     */
    public static MedicalRecord getMedicalRecordByID(String id) throws CustomMedicalRecordException {
        MedicalRecord medicalRecord = medicalRecords.get(id);
        if (medicalRecord == null) {
            throw CustomMedicalRecordException.medicalRecordDoesNotExist(id);
        } else {
            return medicalRecord;
        }
    }

    /**
     * Adds a new MedicalRecord object to the simulated database.
     * @param medicalRecord The MedicalRecord object to add.
     */
    public static void addMedicalRecord(MedicalRecord medicalRecord){
        medicalRecords.put(medicalRecord.getID(), medicalRecord);
    }
    
    /**
     * Updates a MedicalRecord object in the simulated database by its ID.
     * @param id The ID of the MedicalRecord to update.
     * @param updatedMedicalRecord The updated MedicalRecord object.
     * @return true if the update is successful.
     */
    public static boolean updateMedicalRecordByID(String id, MedicalRecord updatedMedicalRecord) {
        medicalRecords.put(id, updatedMedicalRecord);
        return true;
    }
    
    /**
     * Deletes a MedicalRecord object from the simulated database by its ID.
     * @param id The ID of the MedicalRecord to delete.
     * @return true if the deletion is successful.
     * @throws CustomMedicalRecordException If the MedicalRecord does not exist in the database.
     */
    public static boolean deleteMedicalRecordByID(String id)throws CustomMedicalRecordException{
        if(medicalRecords.remove(id) == null){
            throw CustomMedicalRecordException.medicalRecordDoesNotExist(id);
        } else {
            return true;
        }
    }
}
