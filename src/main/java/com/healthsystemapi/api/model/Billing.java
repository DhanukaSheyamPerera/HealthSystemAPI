/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.healthsystemapi.api.model;

/**
 *
 * @author dhanu
 */

import com.healthsystemapi.api.util.customenum.PaymentMethod;

/**
 * The Billing class represents a billing record in the health system.
 * It contains details about the patient's invoice and payment information.
 */
public class Billing {

    private String id; // Unique identifier for the billing record
    private String patientID; // Identifier for the patient associated with the billing
    private double invoiceAmount; // Total amount of the invoice
    private double amountPaid; // Amount paid by the patient
    private PaymentMethod paymentMethod; // Method used for payment
    
    /**
     * Default constructor for creating an instance of Billing without parameters.
     */
    public Billing(){}

    /**
     * Constructor for creating an instance of Billing with patientID, invoiceAmount, amountPaid, and paymentMethod.
     * @param patientID The unique identifier of the patient.
     * @param invoiceAmount The total amount of the invoice.
     * @param amountPaid The amount paid by the patient.
     * @param paymentMethod The method used for payment.
     */
    public Billing(String patientID, double invoiceAmount, double amountPaid, PaymentMethod paymentMethod) {
        this.id = null;
        this.patientID = patientID;
        this.invoiceAmount = invoiceAmount;
        this.amountPaid = amountPaid;
        this.paymentMethod = paymentMethod;
    }

    /**
     * Constructor for creating an instance of Billing with id, patientID, invoiceAmount, amountPaid, and paymentMethod.
     * @param id The unique identifier for the billing record.
     * @param patientID The unique identifier of the patient.
     * @param invoiceAmount The total amount of the invoice.
     * @param amountPaid The amount paid by the patient.
     * @param paymentMethod The method used for payment.
     */
    public Billing(String id, String patientID, double invoiceAmount, double amountPaid, PaymentMethod paymentMethod) {
        this.id = id;
        this.patientID = patientID;
        this.invoiceAmount = invoiceAmount;
        this.amountPaid = amountPaid;
        this.paymentMethod = paymentMethod;
    }

    /**
     * Retrieves the unique identifier of the billing record.
     * @return A string representing the billing record's unique identifier.
     */
    public String getID() {
        return id;
    }

    /**
     * Updates the unique identifier of the billing record.
     * @param id A string containing the new identifier.
     */
    public void setID(String id) {
        this.id = id;
    }

    /**
     * Retrieves the unique identifier of the patient associated with the billing.
     * @return A string representing the patient's unique identifier.
     */
    public String getPatientID() {
        return patientID;
    }

    /**
     * Updates the unique identifier of the patient associated with the billing.
     * @param patientID A string containing the new patient's identifier.
     */
    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    /**
     * Retrieves the total amount of the invoice.
     * @return A double representing the total invoice amount.
     */
    public double getInvoiceAmount() {
        return invoiceAmount;
    }

    /**
     * Updates the total amount of the invoice.
     * @param invoiceAmount A double containing the new invoice amount.
     */
    public void setInvoiceAmount(double invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    /**
     * Retrieves the amount paid by the patient.
     * @return A double representing the amount paid.
     */
    public double getAmountPaid() {
        return amountPaid;
    }

    /**
     * Updates the amount paid by the patient.
     * @param amountPaid A double containing the new amount paid.
     */
    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    /**
     * Retrieves the payment method used.
     * @return The payment method used for the billing.
     */
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * Updates the payment method used.
     * @param paymentMethod The new payment method to be set.
     */
    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
