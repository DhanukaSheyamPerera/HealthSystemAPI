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

import com.healthsystemapi.api.model.Prescription;
import com.healthsystemapi.api.dao.PrescriptionDAO;
import com.healthsystemapi.api.dao.PatientDAO;
import com.healthsystemapi.api.model.Patient;
import com.healthsystemapi.api.util.exception.CustomPrescriptionException;
import com.healthsystemapi.api.util.Identifier;
import com.healthsystemapi.api.util.customenum.IDPrefix;
import com.healthsystemapi.api.util.exception.CustomDoctorException;
import com.healthsystemapi.api.util.exception.CustomIDException;
import com.healthsystemapi.api.util.exception.CustomPatientException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Resource class for handling prescriptions in the health system API.
 */
@Path("/prescriptions")
public class PrescriptionResource {

    private static final Logger logger = LogManager.getLogger(PrescriptionResource.class);

    /**
     * Retrieves all prescriptions.
     *
     * @return Response with a JSON containing all prescriptions and their details.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPrescriptions() {
        logger.info("Getting all prescriptions");
        Map<String, Prescription> prescriptions = PrescriptionDAO.getAllPrescriptions();
        Map<String, Object> prescriptionsWithDetails = new HashMap<>();

        for (Map.Entry<String, Prescription> entry : prescriptions.entrySet()) {
            Prescription prescription = entry.getValue();
            Map<String, Object> prescriptionDetails = new HashMap<>();
            prescriptionDetails.put("medication", prescription.getMedication());
            prescriptionDetails.put("dosage", prescription.getDosage());
            prescriptionDetails.put("instructions", prescription.getInstructions());
            prescriptionDetails.put("duration", prescription.getDuration());

            try {
                Patient patient = PatientDAO.getPatientByID(prescription.getPatientID());
                prescriptionDetails.put("patient", patient);
            } catch (CustomPatientException e) {
                prescriptionDetails.put("patient", null);
            }

            prescriptionsWithDetails.put(entry.getKey(), prescriptionDetails);
        }

        return Response.ok(prescriptionsWithDetails).build();
    }

    /**
     * Retrieves a prescription by its ID.
     *
     * @param prescriptionID The ID of the prescription to retrieve.
     * @return Response with a JSON containing the prescription details.
     */
    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPrescriptionByID(@QueryParam("id") String prescriptionID) {
        try {
            Identifier.validateID(prescriptionID, IDPrefix.PRE);

            Prescription prescription = PrescriptionDAO.getPrescriptionByID(prescriptionID);
            logger.info("Getting prescription by ID: {}", prescriptionID);

            Patient patient = null;
            try {
                patient = PatientDAO.getPatientByID(prescription.getPatientID());
            } catch (CustomPatientException e) {
                logger.error(e.getMessage());
            }

            Map<String, Object> prescriptionDetails = new HashMap<>();
            prescriptionDetails.put("medication", prescription.getMedication());
            prescriptionDetails.put("dosage", prescription.getDosage());
            prescriptionDetails.put("instructions", prescription.getInstructions());
            prescriptionDetails.put("duration", prescription.getDuration());
            prescriptionDetails.put("patient", patient);

            Map<String, Object> response = new HashMap<>();
            response.put("prescription", prescriptionDetails);

            return Response.ok(response).build();

        } catch (CustomPrescriptionException | CustomIDException e) {
            logger.error(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    /**
     * Creates a new prescription.
     *
     * @param prescription The prescription object to be created.
     * @return Response with a JSON containing the created prescription details.
     */
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPrescription(Prescription prescription) {
        try {
            Identifier.validateID(prescription.getPatientID(), IDPrefix.PAT);

            Patient patient = PatientDAO.getPatientByID(prescription.getPatientID());

            if (prescription.getMedication().isBlank() || prescription.getDosage().isBlank() || prescription.getInstructions().isBlank() || prescription.getDuration().isBlank()) {
                throw CustomPrescriptionException.invalidFieldValues();
            }

            String id = Identifier.generateID(IDPrefix.PRE);
            prescription.setID(id);
            PrescriptionDAO.addPrescription(prescription);
            logger.info("Created prescription: {}", id);
            return Response.status(Response.Status.CREATED).entity(prescription).build();

        } catch (CustomPrescriptionException | CustomIDException | CustomPatientException e) {
            logger.error(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    /**
     * Updates an existing prescription.
     *
     * @param updatedPrescription The updated prescription object.
     * @return Response with a JSON containing the updated prescription details.
     */
    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePrescription(Prescription updatedPrescription) {
        try {
            Identifier.validateID(updatedPrescription.getID(), IDPrefix.PRE);

            Identifier.validateID(updatedPrescription.getPatientID(), IDPrefix.PAT);

            Patient patient = PatientDAO.getPatientByID(updatedPrescription.getPatientID());

            if (updatedPrescription.getMedication().isBlank() || updatedPrescription.getDosage().isBlank() || updatedPrescription.getInstructions().isBlank() || updatedPrescription.getDuration().isBlank()) {
                throw CustomPrescriptionException.invalidFieldValues();
            }

            Prescription oldPrescription = PrescriptionDAO.getPrescriptionByID(updatedPrescription.getID());
            if (oldPrescription == null) {
                throw CustomPrescriptionException.prescriptionDoesNotExist(updatedPrescription.getID());
            }

            updatedPrescription.setID(oldPrescription.getID());
            PrescriptionDAO.updatePrescriptionByID(oldPrescription.getID(), updatedPrescription);
            logger.info("Updated prescription: {}", updatedPrescription.getID());

            return Response.status(Response.Status.OK).entity(updatedPrescription).build();

        } catch (CustomPrescriptionException | CustomIDException | CustomPatientException e) {
            logger.error(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    /**
     * Deletes a prescription by its ID.
     *
     * @param id The ID of the prescription to be deleted.
     * @return Response with a JSON indicating the deletion status.
     */
    @DELETE
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePrescriptionByID(@QueryParam("id") String id) {
        try {
            Identifier.validateID(id, IDPrefix.PRE);

            Prescription prescription = PrescriptionDAO.getPrescriptionByID(id);
            if (prescription == null) {
                throw CustomPrescriptionException.prescriptionDoesNotExist(id);
            }

            String patientID = prescription.getPatientID();
            PrescriptionDAO.deletePrescriptionByID(id);
            logger.info("Deleted prescription: {}", id);

            Map<String, String> response = new HashMap<>();
            response.put("message", "The patient object have not been removed!");
            response.put("patientID", patientID);

            return Response.status(Response.Status.OK).entity(response).build();

        } catch (CustomPrescriptionException | CustomIDException e) {
            logger.error(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

}
