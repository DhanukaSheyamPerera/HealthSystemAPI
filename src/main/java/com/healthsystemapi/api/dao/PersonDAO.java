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

import com.healthsystemapi.api.model.Person;
import com.healthsystemapi.api.util.exception.CustomPersonException;

/**
 * The PersonDAO class is responsible for handling the CRUD operations for Person objects.
 * It uses a static Map to simulate database storage.
 */
public class PersonDAO {

    private static Map<String, Person> persons = new HashMap<>();

    /**
     * Retrieves all Person objects.
     * @return A Map of Person objects with their IDs as keys.
     */
    public static Map<String, Person> getAllPersons() {
        return persons;
    }

    /**
     * Retrieves a Person object by its ID.
     * @param id The ID of the Person to retrieve.
     * @return The Person object associated with the given ID.
     * @throws CustomPersonException If the Person does not exist.
     */
    public static Person getPersonByID(String id) throws CustomPersonException {
        Person person = persons.get(id);
        if (person == null) {
            throw CustomPersonException.personDoesNotExist(id);
        } else {
            return person;
        }
    }

    /**
     * Adds a new Person object.
     * @param person The Person object to add.
     */
    public static void addPerson(Person person){
        persons.put(person.getID(), person);
    }
    
    /**
     * Updates a Person object by its ID.
     * @param id The ID of the Person to update.
     * @param updatedPerson The updated Person object.
     * @return true if the update is successful.
     */
    public static boolean updatePersonByID(String id, Person updatedPerson) {
        persons.put(id, updatedPerson);
        return true;
    }
    
    /**
     * Deletes a Person object by its ID.
     * @param id The ID of the Person to delete.
     * @return true if the deletion is successful.
     * @throws CustomPersonException If the Person does not exist.
     */
    public static boolean deletePersonByID(String id)throws CustomPersonException{
        if(persons.remove(id) == null){
            throw CustomPersonException.personDoesNotExist(id);
        } else {
            return true;
        }
    }
}
