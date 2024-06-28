/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.healthsystemapi.api.model;

/**
 *
 * @author dhanu
 */

import com.healthsystemapi.api.util.customenum.Specialisation;

/**
 * The Doctor class represents a doctor in the health system.
 * It extends the Person class with an additional attribute for the doctor's specialisation.
 */
public class Doctor extends Person {

    private Specialisation specialisation; // The specialisation of the doctor

    /**
     * Default constructor for creating an instance of Doctor without parameters.
     */
    public Doctor() {
    }

    /**
     * Constructor for creating an instance of Doctor with specialisation, username, contact information, and address.
     * @param specialisation The specialisation of the doctor.
     * @param username The username of the person.
     * @param contactInformation The contact information of the person.
     * @param address The address of the person.
     */
    public Doctor(Specialisation specialisation, String username, String contactInformation, String address) {
        super(username, contactInformation, address);
        this.specialisation = specialisation;
    }

    /**
     * Constructor for creating an instance of Doctor with id, specialisation, username, contact information, and address.
     * @param id The unique identifier for the person.
     * @param specialisation The specialisation of the doctor.
     * @param username The username of the person.
     * @param contactInformation The contact information of the person.
     * @param address The address of the person.
     */
    public Doctor(Specialisation specialisation, String id, String username, String contactInformation, String address) {
        super(id, username, contactInformation, address);
        this.specialisation = specialisation;
    }

    /**
     * Retrieves the specialisation of the doctor.
     * @return The specialisation of the doctor.
     */
    public Specialisation getSpecialisation() {
        return specialisation;
    }

    /**
     * Updates the specialisation of the doctor.
     * @param specialisation The new specialisation to be set for the doctor.
     */
    public void setSpecialisation(Specialisation specialisation) {
        this.specialisation = specialisation;
    }
}
