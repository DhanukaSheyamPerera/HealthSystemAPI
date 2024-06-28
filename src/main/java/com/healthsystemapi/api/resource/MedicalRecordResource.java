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

import com.healthsystemapi.api.model.MedicalRecord;
import com.healthsystemapi.api.dao.MedicalRecordDAO;
import com.healthsystemapi.api.dao.PatientDAO;
import com.healthsystemapi.api.model.Patient;
import com.healthsystemapi.api.util.exception.CustomMedicalRecordException;
import com.healthsystemapi.api.util.Identifier;
import com.healthsystemapi.api.util.customenum.IDPrefix;
import com.healthsystemapi.api.util.exception.CustomIDException;
import com.healthsystemapi.api.util.exception.CustomPatientException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Resource class for handling medical records in the health system API.
 */
@Path("/medicalrecords")
public class MedicalRecordResource {

    private static final Logger logger = LogManager.getLogger(MedicalRecordResource.class);

    /**
     * Retrieves all medical records.
     *
     * @return Response with a JSON containing all medical records and their details.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllMedicalRecords() {
        logger.info("Getting all medical records");
        Map<String, MedicalRecord> medicalRecords = MedicalRecordDAO.getAllMedicalRecords();
        Map<String, Object> medicalRecordsWithDetails = new HashMap<>();

        for (Map.Entry<String, MedicalRecord> entry : medicalRecords.entrySet()) {
            MedicalRecord medicalRecord = entry.getValue();
            Map<String, Object> medicalRecordDetails = new HashMap<>();
            medicalRecordDetails.put("diagnoses", medicalRecord.getDiagnoses());
            medicalRecordDetails.put("testResults", medicalRecord.getTestResults());
            medicalRecordDetails.put("medications", medicalRecord.getMedications());
            medicalRecordDetails.put("treatments", medicalRecord.getTreatments());

            try {
                Patient patient = PatientDAO.getPatientByID(medicalRecord.getPatientID());
                medicalRecordDetails.put("patientID", patient);
            } catch (CustomPatientException e) {
                medicalRecordDetails.put("patientID", null);
            }

            medicalRecordsWithDetails.put(entry.getKey(), medicalRecordDetails);
        }

        return Response.ok(medicalRecordsWithDetails).build();
    }

    /**
     * Retrieves a medical record by its ID.
     *
     * @param medicalRecordID The ID of the medical record to retrieve.
     * @return Response with a JSON containing the medical record details.
     */
    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMedicalRecordByID(@QueryParam("id") String medicalRecordID) {
        try {
            Identifier.validateID(medicalRecordID, IDPrefix.MER);

            MedicalRecord medicalRecord = MedicalRecordDAO.getMedicalRecordByID(medicalRecordID);
            logger.info("Getting medical record by ID: {}", medicalRecordID);

            Patient patient = null;
            try {
                patient = PatientDAO.getPatientByID(medicalRecord.getPatientID());
            } catch (CustomPatientException e) {
                logger.error("Patient not found: {}", medicalRecord.getPatientID());
            }

            Map<String, Object> medicalRecordDetails = new HashMap<>();
            medicalRecordDetails.put("diagnoses", medicalRecord.getDiagnoses());
            medicalRecordDetails.put("testResults", medicalRecord.getTestResults());
            medicalRecordDetails.put("medications", medicalRecord.getMedications());
            medicalRecordDetails.put("treatments", medicalRecord.getTreatments());
            medicalRecordDetails.put("patient", patient);

            Map<String, Object> response = new HashMap<>();
            response.put("medicalRecord", medicalRecordDetails);

            return Response.ok(response).build();

        } catch (CustomMedicalRecordException | CustomIDException e) {
            logger.error(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    /**
     * Creates a new medical record.
     *
     * @param medicalRecord The medical record object to be created.
     * @return Response with a JSON containing the created medical record details.
     */
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createMedicalRecord(MedicalRecord medicalRecord) {
        try {
            Identifier.validateID(medicalRecord.getPatientID(), IDPrefix.PAT);

            Patient patient = PatientDAO.getPatientByID(medicalRecord.getPatientID());

            if (medicalRecord.getDiagnoses().isBlank() || medicalRecord.getTestResults().isBlank() || medicalRecord.getMedications().isBlank() || medicalRecord.getTreatments().isBlank()) {
                throw CustomMedicalRecordException.invalidFieldValues();
            }

            String id = Identifier.generateID(IDPrefix.MER);
            medicalRecord.setID(id);
            MedicalRecordDAO.addMedicalRecord(medicalRecord);
            logger.info("Created medical record: {}", id);
            return Response.status(Response.Status.CREATED).entity(medicalRecord).build();

        } catch (CustomMedicalRecordException | CustomIDException | CustomPatientException e) {
            logger.error(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    /**
     * Updates an existing medical record.
     *
     * @param updatedMedicalRecord The updated medical record object.
     * @return Response with a JSON containing the updated medical record details.
     */
    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateMedicalRecord(MedicalRecord updatedMedicalRecord) {
        try {
            Identifier.validateID(updatedMedicalRecord.getID(), IDPrefix.MER);

            Identifier.validateID(updatedMedicalRecord.getPatientID(), IDPrefix.PAT);

            Patient patient = PatientDAO.getPatientByID(updatedMedicalRecord.getPatientID());

            if (updatedMedicalRecord.getDiagnoses().isBlank() || updatedMedicalRecord.getTestResults().isBlank() || updatedMedicalRecord.getMedications().isBlank() || updatedMedicalRecord.getTreatments().isBlank()) {
                throw CustomMedicalRecordException.invalidFieldValues();
            }

            MedicalRecord oldMedicalRecord = MedicalRecordDAO.getMedicalRecordByID(updatedMedicalRecord.getID());
            if (oldMedicalRecord == null) {
                throw CustomMedicalRecordException.medicalRecordDoesNotExist(updatedMedicalRecord.getID());
            }

            updatedMedicalRecord.setID(oldMedicalRecord.getID());
            MedicalRecordDAO.updateMedicalRecordByID(oldMedicalRecord.getID(), updatedMedicalRecord);
            logger.info("Updated medical record: {}", updatedMedicalRecord.getID());

            return Response.status(Response.Status.OK).entity(updatedMedicalRecord).build();

        } catch (CustomMedicalRecordException | CustomIDException | CustomPatientException e) {
            logger.error(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    /**
     * Deletes a medical record by its ID.
     *
     * @param id The ID of the medical record to be deleted.
     * @return Response with a JSON indicating the deletion status.
     */
    @DELETE
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteMedicalRecordByID(@QueryParam("id") String id) {
        try {
            Identifier.validateID(id, IDPrefix.MER);

            MedicalRecord medicalRecord = MedicalRecordDAO.getMedicalRecordByID(id);
            if (medicalRecord == null) {
                throw CustomMedicalRecordException.medicalRecordDoesNotExist(id);
            }

            String patientID = medicalRecord.getPatientID();
            MedicalRecordDAO.deleteMedicalRecordByID(id);
            logger.info("Deleted medical record: {}", id);

            Map<String, String> response = new HashMap<>();
            response.put("message", "The patient object is not deleted!");
            response.put("patientID", patientID);

            return Response.status(Response.Status.OK).entity(response).build();

        } catch (CustomMedicalRecordException | CustomIDException e) {
            logger.error(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
