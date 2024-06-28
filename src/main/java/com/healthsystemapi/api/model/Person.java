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
 * The Person class holds information about an individual.
 */
public class Person {

    private String id; // Unique identifier for the person
    private String username; // Username of the person
    private String contactInformation; // Contact information of the person
    private String address; // Address of the person

    /**
     * Default constructor for creating an instance of Person.
     */
    public Person() {}

    /**
     * Constructor for creating an instance of Person with username, contact information, and address.
     * @param username The username of the person.
     * @param contactInformation The contact information of the person.
     * @param address The address of the person.
     */
    public Person(String username, String contactInformation, String address) {
        this.id = null;
        this.username = username;
        this.contactInformation = contactInformation;
        this.address = address;
    }

    /**
     * Constructor for creating an instance of Person with id, username, contact information, and address.
     * @param id The unique identifier for the person.
     * @param username The username of the person.
     * @param contactInformation The contact information of the person.
     * @param address The address of the person.
     */
    public Person(String id, String username, String contactInformation, String address) {
        this.id = id;
        this.username = username;
        this.contactInformation = contactInformation;
        this.address = address;
    }

    /**
     * Retrieves the unique identifier of the person.
     * @return A string representing the person's unique identifier.
     */
    public String getID() {
        return id;
    }

    /**
     * Retrieves the username of the person.
     * @return A string representing the person's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Retrieves the contact information of the person.
     * @return A string representing the person's contact information.
     */
    public String getContactInformation() {
        return contactInformation;
    }

    /**
     * Retrieves the address of the person.
     * @return A string representing the person's address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Updates the unique identifier of the person.
     * @param id A string containing the new identifier.
     */
    public void setID(String id) {
        this.id = id;
    }

    /**
     * Updates the username of the person.
     * @param username A string containing the new username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Updates the contact information of the person.
     * @param contactInformation A string containing the new contact information.
     */
    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    /**
     * Updates the address of the person.
     * @param address A string containing the new address.
     */
    public void setAddress(String address) {
        this.address = address;
    }
}
