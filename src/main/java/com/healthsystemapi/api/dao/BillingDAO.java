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

import com.healthsystemapi.api.model.Billing;
import com.healthsystemapi.api.util.exception.CustomBillingException;

/**
 * The BillingDAO class is responsible for managing the CRUD operations for Billing objects.
 * It provides a static Map to simulate a database and methods to interact with this data.
 */
public class BillingDAO {

    private static Map<String, Billing> billings = new HashMap<>(); // Simulated database of billings

    /**
     * Retrieves all billings from the simulated database.
     * @return A Map containing all Billing objects.
     */
    public static Map<String, Billing> getAllBillings() {
        return billings;
    }

    /**
     * Retrieves a Billing object by its ID.
     * @param id The ID of the Billing to retrieve.
     * @return The Billing object associated with the given ID.
     * @throws CustomBillingException If the Billing does not exist in the database.
     */
    public static Billing getBillingByID(String id) throws CustomBillingException {
        Billing billing = billings.get(id);
        if (billing == null) {
            throw CustomBillingException.billingDoesNotExist(id);
        } else {
            return billing;
        }
    }

    /**
     * Adds a new Billing object to the simulated database.
     * @param billing The Billing object to add.
     */
    public static void addBilling(Billing billing){
        billings.put(billing.getID(), billing);
    }
    
    /**
     * Updates a Billing object in the simulated database by its ID.
     * @param id The ID of the Billing to update.
     * @param updatedBilling The updated Billing object.
     * @return true if the update is successful.
     */
    public static boolean updateBillingByID(String id, Billing updatedBilling) {
        billings.put(id, updatedBilling);
        return true;
    }
    
    /**
     * Deletes a Billing object from the simulated database by its ID.
     * @param id The ID of the Billing to delete.
     * @return true if the deletion is successful.
     * @throws CustomBillingException If the Billing does not exist in the database.
     */
    public static boolean deleteBillingByID(String id)throws CustomBillingException{
        if(billings.remove(id) == null){
            throw CustomBillingException.billingDoesNotExist(id);
        } else {
            return true;
        }
    }
}
