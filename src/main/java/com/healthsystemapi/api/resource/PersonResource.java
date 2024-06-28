/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.healthsystemapi.api.resource;

/**
 *
 * @author dhanu
 */

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.healthsystemapi.api.model.Person;
import com.healthsystemapi.api.dao.PersonDAO;
import com.healthsystemapi.api.util.exception.CustomPersonException;
import com.healthsystemapi.api.util.exception.CustomIDException;
import com.healthsystemapi.api.util.customenum.IDPrefix;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.healthsystemapi.api.util.Identifier;

/**
 * Resource class for handling person-related operations.
 */
@Path("/persons")
public class PersonResource {

    private static final Logger logger = LogManager.getLogger(PersonResource.class);

    /**
     * Retrieves all persons from the database.
     * @return a Response object containing all persons
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPersons() {
        logger.info("Getting all persons");
        return Response.ok(PersonDAO.getAllPersons()).build();
    }

    /**
     * Retrieves a person by their ID.
     * @param personID the ID of the person to retrieve
     * @return a Response object containing the person's details
     */
    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonByID(@QueryParam("id") String personID) {
        try {
            Identifier.validateID(personID, IDPrefix.PER);

            logger.info("Getting person by ID: {}", personID);
            Person person = PersonDAO.getPersonByID(personID);
            return Response.ok(person).build();
        } catch (CustomIDException e) {
            logger.error("Invalid ID format: {}", personID);
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid ID format").build();
        } catch (CustomPersonException e) {
            logger.error("Person not found: {}", personID);
            return Response.status(Response.Status.NOT_FOUND).entity("Person not found").build();
        }
    }

    /**
     * Creates a new person in the database.
     * @param person the person object to create
     * @return a Response object indicating the result of the operation
     */
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPerson(Person person) {
        try {
            if (person.getUsername().isBlank() || person.getContactInformation().isBlank() || person.getAddress().isBlank()) {
                throw CustomPersonException.invalidFieldValues();
            } else {
                String id = Identifier.generateID(IDPrefix.PER);
                person.setID(id);
                PersonDAO.addPerson(person);
                logger.info("Created person: {}", id);
                return Response.status(Response.Status.CREATED).entity(person).build();
            }
        } catch (CustomPersonException | NullPointerException e) {
            logger.error("Invalid person details provided");
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid person details").build();
        }
    }

    /**
     * Updates an existing person's details in the database.
     * @param updatedPerson the updated person object
     * @return a Response object indicating the result of the operation
     */
    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePerson(Person updatedPerson) {
        try {
            Identifier.validateID(updatedPerson.getID(), IDPrefix.PER);

            Person oldPerson = PersonDAO.getPersonByID(updatedPerson.getID());
            if (updatedPerson.getUsername().isBlank() || updatedPerson.getContactInformation().isBlank() || updatedPerson.getAddress().isBlank()) {
                throw CustomPersonException.invalidFieldValues();
            } else {
                updatedPerson.setID(oldPerson.getID());
                PersonDAO.updatePersonByID(oldPerson.getID(), updatedPerson);
                logger.info("Updated person: {}", updatedPerson.getID());
            }

            return Response.status(Response.Status.OK).entity(updatedPerson).build();

        } catch (CustomPersonException | CustomIDException e) {
            logger.error(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    /**
     * Deletes a person from the database by their ID.
     * @param id the ID of the person to delete
     * @return a Response object indicating the result of the operation
     */
    @DELETE
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePersonByID(@QueryParam("id") String id) {
        try {
            Identifier.validateID(id, IDPrefix.PER);

            PersonDAO.deletePersonByID(id);
            logger.info("Deleted person: {}", id);

            return Response.status(Response.Status.NO_CONTENT).build();

        } catch (CustomPersonException | CustomIDException e) {
            logger.error(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
