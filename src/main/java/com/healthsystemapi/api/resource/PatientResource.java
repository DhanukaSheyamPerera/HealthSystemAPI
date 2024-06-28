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

import com.healthsystemapi.api.model.Patient;
import com.healthsystemapi.api.dao.PatientDAO;
import com.healthsystemapi.api.util.exception.CustomPatientException;
import com.healthsystemapi.api.util.exception.CustomIDException;
import com.healthsystemapi.api.util.customenum.IDPrefix;
import com.healthsystemapi.api.util.Identifier;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Resource class for handling patient-related operations.
 */
@Path("/patients")
public class PatientResource {

    private static final Logger logger = LogManager.getLogger(PatientResource.class);

    /**
     * Retrieves all patients from the database.
     * @return a Response object containing all patients
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPatients() {
        logger.info("Getting all patients");
        return Response.ok(PatientDAO.getAllPatients()).build();
    }

    /**
     * Retrieves a patient by their ID.
     * @param patientID the ID of the patient to retrieve
     * @return a Response object containing the patient's details
     */
    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPatientByID(@QueryParam("id") String patientID) {
        try {
            Identifier.validateID(patientID, IDPrefix.PAT);

            Patient patient = PatientDAO.getPatientByID(patientID);
            logger.info("Getting patient by ID: {}", patientID);
            return Response.ok(patient).build();

        } catch (CustomPatientException | CustomIDException e) {
            logger.error(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    /**
     * Creates a new patient in the database.
     * @param patient the patient object to create
     * @return a Response object indicating the result of the operation
     */
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPatient(Patient patient) {
        try {
            if (patient.getUsername().isBlank() || patient.getContactInformation().isBlank() || patient.getAddress().isBlank() || patient.getHealthStatus() == null || patient.getMedicalHistory() == null) {
                throw CustomPatientException.invalidFieldValues();
            } else {
                String id = Identifier.generateID(IDPrefix.PAT);
                patient.setID(id);
                PatientDAO.addPatient(patient);
                logger.info("Created patient: {}", id);
                return Response.status(Response.Status.CREATED).entity(patient).build();
            }
        } catch (CustomPatientException | NullPointerException e) {
            logger.error("Invalid patient details provided");
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid patient details").build();
        }
    }

    /**
     * Updates an existing patient's details in the database.
     * @param updatedPatient the updated patient object
     * @return a Response object indicating the result of the operation
     */
    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePatient(Patient updatedPatient) {
        try {
            Identifier.validateID(updatedPatient.getID(), IDPrefix.PAT);

            Patient oldPatient = PatientDAO.getPatientByID(updatedPatient.getID());
            if (updatedPatient.getUsername().isBlank() || updatedPatient.getContactInformation().isBlank() || updatedPatient.getAddress().isBlank() || updatedPatient.getHealthStatus() == null || updatedPatient.getMedicalHistory() == null) {
                throw CustomPatientException.invalidFieldValues();
            } else {
                updatedPatient.setID(oldPatient.getID());
                PatientDAO.updatePatientByID(oldPatient.getID(), updatedPatient);
                logger.info("Updated patient: {}", updatedPatient.getID());
            }

            return Response.status(Response.Status.OK).entity(updatedPatient).build();

        } catch (CustomPatientException | CustomIDException e) {
            logger.error(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    /**
     * Deletes a patient from the database by their ID.
     * @param id the ID of the patient to delete
     * @return a Response object indicating the result of the operation
     */
    @DELETE
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePatientByID(@QueryParam("id") String id) {
        try {
            Identifier.validateID(id, IDPrefix.PAT);

            PatientDAO.deletePatientByID(id);
            logger.info("Deleted patient: {}", id);

            return Response.status(Response.Status.NO_CONTENT).build();

        } catch (CustomPatientException | CustomIDException e) {
            logger.error(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
