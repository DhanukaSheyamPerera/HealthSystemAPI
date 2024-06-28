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

import com.healthsystemapi.api.model.Doctor;
import com.healthsystemapi.api.util.exception.CustomDoctorException;

/**
 * The DoctorDAO class is responsible for managing the CRUD operations for Doctor objects.
 * It provides a static Map to simulate a database and methods to interact with this data.
 */
public class DoctorDAO {

    private static Map<String, Doctor> doctors = new HashMap<>(); // Simulated database of doctors

    public static int doctorCount = 0; // Counter for the number of doctors

    /**
     * Retrieves all doctors from the simulated database.
     * @return A Map containing all Doctor objects.
     */
    public static Map<String, Doctor> getAllDoctors() {
        return doctors;
    }

    /**
     * Retrieves a Doctor object by its ID.
     * @param id The ID of the Doctor to retrieve.
     * @return The Doctor object associated with the given ID.
     * @throws CustomDoctorException If the Doctor does not exist in the database.
     */
    public static Doctor getDoctorByID(String id) throws CustomDoctorException {
        Doctor doctor = doctors.get(id);
        if (doctor == null) {
            throw CustomDoctorException.doctorDoesNotExist(id);
        } else {
            return doctor;
        }
    }

    /**
     * Adds a new Doctor object to the simulated database.
     * @param doctor The Doctor object to add.
     */
    public static void addDoctor(Doctor doctor){
        doctors.put(doctor.getID(), doctor);
        doctorCount++;
    }
    
    /**
     * Updates a Doctor object in the simulated database by its ID.
     * @param id The ID of the Doctor to update.
     * @param updatedDoctor The updated Doctor object.
     * @return true if the update is successful.
     */
    public static boolean updateDoctorByID(String id, Doctor updatedDoctor) {
        doctors.put(id, updatedDoctor);
        return true;
    }
    
    /**
     * Deletes a Doctor object from the simulated database by its ID.
     * @param id The ID of the Doctor to delete.
     * @return true if the deletion is successful.
     * @throws CustomDoctorException If the Doctor does not exist in the database.
     */
    public static boolean deleteDoctorByID(String id)throws CustomDoctorException{
        if(doctors.remove(id) == null){
            throw CustomDoctorException.doctorDoesNotExist(id);
        } else {
            return true;
        }
    }
}
