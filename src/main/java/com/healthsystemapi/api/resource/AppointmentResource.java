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

import com.healthsystemapi.api.model.Appointment;
import com.healthsystemapi.api.dao.AppointmentDAO;
import com.healthsystemapi.api.util.exception.CustomAppointmentException;
import com.healthsystemapi.api.util.exception.CustomIDException;
import com.healthsystemapi.api.util.exception.CustomDoctorException;
import com.healthsystemapi.api.util.exception.CustomPatientException;
import com.healthsystemapi.api.util.customenum.IDPrefix;
import com.healthsystemapi.api.util.Identifier;
import com.healthsystemapi.api.util.DateTimeValidator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.HashMap;

import com.healthsystemapi.api.model.Doctor;
import com.healthsystemapi.api.model.Patient;
import com.healthsystemapi.api.dao.DoctorDAO;
import com.healthsystemapi.api.dao.PatientDAO;

/**
 * Resource class for handling appointments in the health system API.
 */
@Path("/appointments")
public class AppointmentResource {

    private static final Logger logger = LogManager.getLogger(AppointmentResource.class);

    /**
     * Retrieves all appointments.
     *
     * @return Response with a JSON containing all appointments and their details.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAppointments() {
        logger.info("Getting all appointments");
        Map<String, Appointment> appointments = AppointmentDAO.getAllAppointments();
        Map<String, Object> appointmentsWithDetails = new HashMap<>();

        for (Map.Entry<String, Appointment> entry : appointments.entrySet()) {
            Appointment appointment = entry.getValue();
            Map<String, Object> appointmentDetails = new HashMap<>();
            appointmentDetails.put("date", appointment.getDate());
            appointmentDetails.put("time", appointment.getTime());

            try {
                Doctor doctor = DoctorDAO.getDoctorByID(appointment.getDoctorID());
                appointmentDetails.put("doctorID", doctor);
            } catch (CustomDoctorException e) {
                appointmentDetails.put("doctorID", null);
            }

            try {
                Patient patient = PatientDAO.getPatientByID(appointment.getPatientID());
                appointmentDetails.put("patientID", patient);
            } catch (CustomPatientException e) {
                appointmentDetails.put("patientID", null);
            }

            appointmentsWithDetails.put(entry.getKey(), appointmentDetails);
        }

        return Response.ok(appointmentsWithDetails).build();
    }

    /**
     * Retrieves an appointment by its ID.
     *
     * @param appointmentID The ID of the appointment to retrieve.
     * @return Response with a JSON containing the appointment details.
     */
    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAppointmentByID(@QueryParam("id") String appointmentID) {
        try {
            Identifier.validateID(appointmentID, IDPrefix.APP);

            Appointment appointment = AppointmentDAO.getAppointmentByID(appointmentID);
            logger.info("Getting appointment by ID: {}", appointmentID);

            Patient patient = null;
            Doctor doctor = null;
            try {
                patient = PatientDAO.getPatientByID(appointment.getPatientID());
                doctor = DoctorDAO.getDoctorByID(appointment.getDoctorID());
            } catch (CustomPatientException | CustomDoctorException e) {
                logger.error(e.getMessage());
            }

            Map<String, Object> appointmentDetails = new HashMap<>();
            appointmentDetails.put("date", appointment.getDate());
            appointmentDetails.put("time", appointment.getTime());
            appointmentDetails.put("patient", patient);
            appointmentDetails.put("doctor", doctor);

            Map<String, Object> response = new HashMap<>();
            response.put("appointment", appointmentDetails);

            return Response.ok(response).build();

        } catch (CustomAppointmentException | CustomIDException e) {
            logger.error(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    /**
     * Creates a new appointment.
     *
     * @param appointment The appointment object to be created.
     * @return Response with a JSON containing the created appointment details.
     */
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAppointment(Appointment appointment) {
        try {
            Identifier.validateID(appointment.getPatientID(), IDPrefix.PAT);
            Identifier.validateID(appointment.getDoctorID(), IDPrefix.DOC);

            Patient patient = PatientDAO.getPatientByID(appointment.getPatientID());
            Doctor doctor = DoctorDAO.getDoctorByID(appointment.getDoctorID());

            DateTimeValidator.validateDateFormat(appointment.getDate(), appointment.getTime());
            String id = Identifier.generateID(IDPrefix.APP);
            appointment.setID(id);
            AppointmentDAO.addAppointment(appointment);
            logger.info("Created appointment: {}", id);
            return Response.status(Response.Status.CREATED).entity(appointment).build();

        } catch (CustomAppointmentException | CustomIDException | CustomPatientException | CustomDoctorException e) {
            logger.error(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    /**
     * Updates an existing appointment.
     *
     * @param updatedAppointment The updated appointment object.
     * @return Response with a JSON containing the updated appointment details.
     */
    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAppointment(Appointment updatedAppointment) {
        try {
            Identifier.validateID(updatedAppointment.getID(), IDPrefix.APP);
            Identifier.validateID(updatedAppointment.getDoctorID(), IDPrefix.DOC);
            Identifier.validateID(updatedAppointment.getPatientID(), IDPrefix.PAT);

            Patient patient = PatientDAO.getPatientByID(updatedAppointment.getPatientID());
            Doctor doctor = DoctorDAO.getDoctorByID(updatedAppointment.getDoctorID());

            if (updatedAppointment.getDate().isBlank() || updatedAppointment.getTime().isBlank()) {
                throw CustomAppointmentException.invalidFieldValues();
            }

            DateTimeValidator.validateDateFormat(updatedAppointment.getDate(), updatedAppointment.getTime());

            Appointment oldAppointment = AppointmentDAO.getAppointmentByID(updatedAppointment.getID());
            if (oldAppointment == null) {
                throw CustomAppointmentException.appointmentDoesNotExist(updatedAppointment.getID());
            }

            updatedAppointment.setID(oldAppointment.getID());
            AppointmentDAO.updateAppointmentByID(oldAppointment.getID(), updatedAppointment);
            logger.info("Updated appointment: {}", updatedAppointment.getID());
            return Response.status(Response.Status.OK).entity(updatedAppointment).build();

        } catch (CustomAppointmentException | CustomIDException | CustomPatientException | CustomDoctorException e) {
            logger.error(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    /**
     * Deletes an appointment by its ID.
     *
     * @param id The ID of the appointment to be deleted.
     * @return Response with a JSON indicating the deletion status.
     */
    @DELETE
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAppointmentByID(@QueryParam("id") String id) {
        try {
            Identifier.validateID(id, IDPrefix.APP);

            Appointment appointment = AppointmentDAO.getAppointmentByID(id);
            String patientID = appointment.getPatientID();
            String doctorID = appointment.getDoctorID();

            AppointmentDAO.deleteAppointmentByID(id);
            logger.info("Deleted appointment: {}", id);

            Map<String, String> response = new HashMap<>();
            response.put("message", "These objects are not deleted!");
            response.put("patientID", patientID);
            response.put("doctorID", doctorID);

            return Response.status(Response.Status.OK).entity(response).build();

        } catch (CustomAppointmentException | CustomIDException e) {
            logger.error(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
