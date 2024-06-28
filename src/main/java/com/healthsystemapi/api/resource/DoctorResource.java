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

import com.healthsystemapi.api.model.Doctor;
import com.healthsystemapi.api.dao.DoctorDAO;
import com.healthsystemapi.api.util.exception.CustomDoctorException;
import com.healthsystemapi.api.util.exception.CustomIDException;
import com.healthsystemapi.api.util.customenum.IDPrefix;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.healthsystemapi.api.util.Identifier;

/**
 * Resource class for handling doctor-related operations.
 */
@Path("/doctors")
public class DoctorResource {

    private static final Logger logger = LogManager.getLogger(DoctorResource.class);

    /**
     * Retrieves all doctors from the database.
     * @return a Response object containing all doctors
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDoctors() {
        logger.info("Getting all doctors");
        return Response.ok(DoctorDAO.getAllDoctors()).build();
    }

    /**
     * Retrieves a doctor by their ID.
     * @param doctorID the ID of the doctor to retrieve
     * @return a Response object containing the doctor's details
     */
    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDoctorByID(@QueryParam("id") String doctorID) {
        try {
            Identifier.validateID(doctorID, IDPrefix.DOC);

            logger.info("Getting doctor by ID: {}", doctorID);
            Doctor doctor = DoctorDAO.getDoctorByID(doctorID);
            return Response.ok(doctor).build();
        } catch (CustomIDException e) {
            logger.error("Invalid ID format: {}", doctorID);
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid ID format").build();
        } catch (CustomDoctorException e) {
            logger.error("Doctor not found: {}", doctorID);
            return Response.status(Response.Status.NOT_FOUND).entity("Doctor not found").build();
        }
    }

    /**
     * Creates a new doctor in the database.
     * @param doctor the doctor object to create
     * @return a Response object indicating the result of the operation
     */
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDoctor(Doctor doctor) {
        try {
            if (doctor.getUsername().isBlank() || doctor.getContactInformation().isBlank() || doctor.getAddress().isBlank() || doctor.getSpecialisation() == null) {
                throw CustomDoctorException.invalidFieldValues();
            } else {
                String id = Identifier.generateID(IDPrefix.DOC);
                doctor.setID(id);
                DoctorDAO.addDoctor(doctor);
                logger.info("Created doctor: {}", id);
                return Response.status(Response.Status.CREATED).entity(doctor).build();
            }
        } catch (CustomDoctorException | NullPointerException e) {
            logger.error("Invalid doctor details provided");
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid doctor details").build();
        }
    }

    /**
     * Updates an existing doctor's details in the database.
     * @param updatedDoctor the updated doctor object
     * @return a Response object indicating the result of the operation
     */
    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDoctor(Doctor updatedDoctor) {
        try {
            Identifier.validateID(updatedDoctor.getID(), IDPrefix.DOC);

            Doctor oldDoctor = DoctorDAO.getDoctorByID(updatedDoctor.getID());
            if (updatedDoctor.getUsername().isBlank() || updatedDoctor.getContactInformation().isBlank() || updatedDoctor.getAddress().isBlank() || updatedDoctor.getSpecialisation() == null) {
                throw CustomDoctorException.invalidFieldValues();
            } else {
                updatedDoctor.setID(oldDoctor.getID());
                DoctorDAO.updateDoctorByID(oldDoctor.getID(), updatedDoctor);
                logger.info("Updated doctor: {}", updatedDoctor.getID());
            }

            return Response.status(Response.Status.OK).entity(updatedDoctor).build();

        } catch (CustomDoctorException | CustomIDException e) {
            logger.error(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    /**
     * Deletes a doctor from the database by their ID.
     * @param id the ID of the doctor to delete
     * @return a Response object indicating the result of the operation
     */
    @DELETE
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteDoctorByID(@QueryParam("id") String id) {
        try {
            Identifier.validateID(id, IDPrefix.DOC);

            DoctorDAO.deleteDoctorByID(id);
            logger.info("Deleted doctor: {}", id);

            return Response.status(Response.Status.NO_CONTENT).build();

        } catch (CustomDoctorException | CustomIDException e) {
            logger.error(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
