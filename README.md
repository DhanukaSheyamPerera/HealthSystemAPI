# Health System REST API

This project demonstrates a Health System REST API developed using the JAX-RS Framework. The API allows users to interact with a health system through various endpoints, managing entities such as patients, doctors, appointments, medical records, prescriptions, and billing.

## Table of Contents
1. [Introduction](#introduction)
2. [Installation](#installation)
3. [Project Structure](#project-structure)
4. [API Endpoints](#api-endpoints)
5. [Dependencies](#dependencies)
6. [License](#license)

## Introduction

The Health System REST API is designed to manage health-related data through a set of well-defined endpoints. The project includes various model classes, DAO implementations, resource classes, and utility classes.

## Installation

To get started with running the program, follow the steps below. The program was developed with the following configurations and software:

- NetBeans 18
- Java JDK 19
- Apache Tomcat Server 9.0.72
- Postman (for testing)

### Setting up the Server

1. Download and unzip the provided Apache Tomcat Server version 9.0.72.
2. Open NetBeans, go to the `Services` tab, and select `Web Services`.
3. Add the server by selecting `Apache Tomcat or TomEE`.
4. Locate the unzipped Apache Tomcat folder and set up the account.
5. Start the server. A green icon indicates a successful start.

### Setting Up the Project

1. Create a new Java Web Application project using Maven in NetBeans.
2. Import the project zip files using NetBeans tools.
3. Build the project by right-clicking on the project root folder and selecting `Clean and Build`. A successful build will show a "Build Success" message in the NetBeans console.
4. Deploy the project by right-clicking on the project root folder and selecting `Run`. The base URL of the project will be `http://localhost:8080/HealthSystemAPI`.

## Project Structure

The folder structure of the project is designed as follows:

```
api
├── model
├── dao
├── resource
└── util
    ├── customenum
    ├── exception
    └── patient
```

### Classes

- **model**: Contains all the model classes.
- **dao**: Contains all the Data Access Object (DAO) classes.
- **resource**: Contains all the resource classes.
- **util**: Contains utility classes, enums, exceptions, and additional patient-related classes.

## API Endpoints

The API provides various endpoints to manage the health system entities. Below are the main endpoints:

- **Patient Endpoints**
- **Doctor Endpoints**
- **Appointment Endpoints**
- **Person Endpoints**
- **Billing Endpoints**
- **Medical Record Endpoints**
- **Prescription Endpoints**

### Patient Endpoints
- **GET /patients:** Get all patients.
- **GET /patients/get?id={id}:** Get a patient by ID.
- **POST /patients/create:** Create a new patient.
- **PUT /patients/update:** Update an existing patient.
- **DELETE /patients/delete?id={id}:** Delete a patient by ID.

### Doctor Endpoints
- **GET /doctors:** Get all doctors.
- **GET /doctors/get?id={id}:** Get a doctor by ID.
- **POST /doctors/create:** Create a new doctor.
- **PUT /doctors/update:** Update an existing doctor.
- **DELETE /doctors/delete?id={id}:** Delete a doctor by ID.

### Appointment Endpoints
- **GET /appointments:** Get all appointments.
- **GET /appointments/get?id={id}:** Get an appointment by ID.
- **POST /appointments/create:** Create a new appointment.
- **PUT /appointments/update:** Update an existing appointment.
- **DELETE /appointments/delete?id={id}:** Delete an appointment by ID.

### Person Endpoints
- **GET /persons:** Get all people.
- **GET /persons/get?id={id}:** Get a person by ID.
- **POST /persons/create:** Create a new person.
- **PUT /persons/update:** Update an existing person.
- **DELETE /persons/delete?id={id}:** Delete a person by ID.

### Billing Endpoints
- **GET /billings:** Get all billings.
- **GET /billings/get?id={id}:** Get a billing by ID.
- **POST /billings/create:** Create a new billing.
- **PUT /billings/update:** Update an existing billing.
- **DELETE /billings/delete?id={id}:** Delete a billing by ID.

### Medical Record Endpoints
- **GET /medicalRecords:** Get all medical records.
- **GET /medicalRecords/get?id={id}:** Get a medical record by ID.
- **POST /medicalRecords/create:** Create a new medical record.
- **PUT /medicalRecords/update:** Update an existing medical record.
- **DELETE /medicalRecords/delete?id={id}:** Delete a medical record by ID.

### Prescription Endpoints
- **GET /prescriptions:** Get all prescriptions.
- **GET /prescriptions/get?id={id}:** Get a prescription by ID.
- **POST /prescriptions/create:** Create a new prescription.
- **PUT /prescriptions/update:** Update an existing prescription.
- **DELETE /prescriptions/delete?id={id}:** Delete a prescription by ID.


## Dependencies

The project relies on several dependencies, which are managed using Maven. Check the `pom.xml` file for more details.

## License

This project is licensed under the MIT License. See the LICENSE file for more details.