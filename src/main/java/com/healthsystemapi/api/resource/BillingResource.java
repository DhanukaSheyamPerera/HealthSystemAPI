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

import com.healthsystemapi.api.model.Billing;
import com.healthsystemapi.api.dao.BillingDAO;
import com.healthsystemapi.api.util.exception.CustomBillingException;
import com.healthsystemapi.api.util.exception.CustomPatientException;
import com.healthsystemapi.api.dao.PatientDAO;
import com.healthsystemapi.api.model.Patient;
import com.healthsystemapi.api.util.Identifier;
import com.healthsystemapi.api.util.customenum.IDPrefix;
import com.healthsystemapi.api.util.exception.CustomIDException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Resource class for handling billings in the health system API.
 */
@Path("/billings")
public class BillingResource {

    private static final Logger logger = LogManager.getLogger(BillingResource.class);

    /**
     * Retrieves all billings.
     *
     * @return Response with a JSON containing all billings and their details.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBillings() {
        logger.info("Getting all billings");
        Map<String, Billing> billings = BillingDAO.getAllBillings();
        Map<String, Object> billingsWithDetails = new HashMap<>();

        for (Map.Entry<String, Billing> entry : billings.entrySet()) {
            Billing billing = entry.getValue();
            Map<String, Object> billingDetails = new HashMap<>();
            billingDetails.put("invoiceAmount", billing.getInvoiceAmount());
            billingDetails.put("amountPaid", billing.getAmountPaid());
            billingDetails.put("paymentMethod", billing.getPaymentMethod());

            try {
                Patient patient = PatientDAO.getPatientByID(billing.getPatientID());
                billingDetails.put("patient", patient);
            } catch (CustomPatientException e) {
                billingDetails.put("patient", null);
            }

            billingsWithDetails.put(entry.getKey(), billingDetails);
        }

        return Response.ok(billingsWithDetails).build();
    }

    /**
     * Retrieves a billing by its ID.
     *
     * @param billingID The ID of the billing to retrieve.
     * @return Response with a JSON containing the billing details.
     */
    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBillingByID(@QueryParam("id") String billingID) {
        try {
            Identifier.validateID(billingID, IDPrefix.BIL);

            Billing billing = BillingDAO.getBillingByID(billingID);
            logger.info("Getting billing by ID: {}", billingID);

            Patient patient = null;
            try {
                patient = PatientDAO.getPatientByID(billing.getPatientID());
            } catch (CustomPatientException e) {
                logger.error("Patient not found: {}", billing.getPatientID());
            }

            Map<String, Object> billingDetails = new HashMap<>();
            billingDetails.put("id", billing.getID());
            billingDetails.put("invoiceAmount", billing.getInvoiceAmount());
            billingDetails.put("amountPaid", billing.getAmountPaid());
            billingDetails.put("paymentMethod", billing.getPaymentMethod());
            billingDetails.put("patient", patient);

            Map<String, Object> response = new HashMap<>();
            response.put("billing", billingDetails);

            return Response.ok(response).build();

        } catch (CustomBillingException | CustomIDException e) {
            logger.error(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    /**
     * Creates a new billing.
     *
     * @param billing The billing object to be created.
     * @return Response with a JSON containing the created billing details.
     */
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBilling(Billing billing) {
        try {
            Identifier.validateID(billing.getPatientID(), IDPrefix.PAT);

            Patient patient = PatientDAO.getPatientByID(billing.getPatientID());

            if (billing.getInvoiceAmount() < 0 || billing.getAmountPaid() < 0 || billing.getPaymentMethod() == null) {
                throw CustomBillingException.invalidFieldValues();
            } else {
                String id = Identifier.generateID(IDPrefix.BIL);
                billing.setID(id);
                BillingDAO.addBilling(billing);
                logger.info("Created billing: {}", id);
                return Response.status(Response.Status.CREATED).entity(billing).build();
            }
        } catch (CustomBillingException | CustomIDException | CustomPatientException e) {
            logger.error(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    /**
     * Updates an existing billing.
     *
     * @param updatedBilling The updated billing object.
     * @return Response with a JSON containing the updated billing details.
     */
    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBilling(Billing updatedBilling) {
        try {
            Identifier.validateID(updatedBilling.getID(), IDPrefix.BIL);

            Identifier.validateID(updatedBilling.getPatientID(), IDPrefix.PAT);

            Patient patient = PatientDAO.getPatientByID(updatedBilling.getPatientID());

            if (updatedBilling.getInvoiceAmount() < 0 || updatedBilling.getAmountPaid() < 0 || updatedBilling.getPaymentMethod() == null) {
                throw CustomBillingException.invalidFieldValues();
            }

            Billing oldBilling = BillingDAO.getBillingByID(updatedBilling.getID());
            if (oldBilling == null) {
                throw CustomBillingException.billingDoesNotExist(updatedBilling.getID());
            }

            updatedBilling.setID(oldBilling.getID());
            BillingDAO.updateBillingByID(oldBilling.getID(), updatedBilling);
            logger.info("Updated billing: {}", updatedBilling.getID());

            return Response.status(Response.Status.OK).entity(updatedBilling).build();

        } catch (CustomBillingException | CustomIDException | CustomPatientException e) {
            logger.error(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    /**
     * Deletes a billing by its ID.
     *
     * @param id The ID of the billing to be deleted.
     * @return Response with a JSON indicating the deletion status.
     */
    @DELETE
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteBillingByID(@QueryParam("id") String id) {
        try {
            Identifier.validateID(id, IDPrefix.BIL); 

            Billing billing = BillingDAO.getBillingByID(id);
            if (billing == null) {
                throw CustomBillingException.billingDoesNotExist(id);
            }

            String patientID = billing.getPatientID();
            BillingDAO.deleteBillingByID(id);
            logger.info("Deleted billing: {}", id);

            Map<String, String> response = new HashMap<>();
            response.put("message", "The patient object is not deleted!");
            response.put("patientID", patientID);

            return Response.status(Response.Status.OK).entity(response).build();

        } catch (CustomBillingException | CustomIDException e) {
            logger.error(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
