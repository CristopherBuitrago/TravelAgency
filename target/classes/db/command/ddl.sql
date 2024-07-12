--Create and use of database TravelAgency
DROP DATABASE IF EXISTS TravelAgency;
CREATE DATABASE TravelAgency;
USE TravelAgency;

-- Customer Database

CREATE TABLE document_type (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(40) NOT NULL,
    CONSTRAINT Pk_document_type PRIMARY KEY (id)
);

CREATE TABLE customer (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL,
    lastName VARCHAR(45) NOT NULL,
    age INT NOT NULL,
    document_type INT NOT NULL,
    CONSTRAINT Pk_customer PRIMARY KEY (id),
    CONSTRAINT Fk_customer_1 FOREIGN KEY (document_type) REFERENCES document_type(id)
);

-- Airport Database

CREATE TABLE country (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL,
    CONSTRAINT Pk_country PRIMARY KEY (id)
);

CREATE TABLE city (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL,
    country INT NOT NULL,
    CONSTRAINT Pk_city PRIMARY KEY (id),
    CONSTRAINT Fk_city_1 FOREIGN KEY (country) REFERENCES country(id)
)

CREATE TABLE airport (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL,
    city INT NOT NULL,
    CONSTRAINT Pk_airport PRIMARY KEY (id),
    CONSTRAINT Fk_airport_1 FOREIGN KEY (city) REFERENCES city(id)
);

CREATE TABLE scale (
    id INT NOT NULL AUTO_INCREMENT,
    scaleCode VARCHAR(10) NOT NULL,
    airport INT NOT NULL,
    CONSTRAINT Pk_scale PRIMARY KEY (id),
    CONSTRAINT Fk_scale_1 FOREIGN KEY (airport) REFERENCES airport(id)
);

-- TripBooking Database

CREATE TABLE flight_fare (
    id INT NOT NULL AUTO_INCREMENT,
    description VARCHAR(20) NOT NULL,
    details TEXT NOT NULL,
    value DOUBLE NOT NULL,
    CONSTRAINT Pk_flight_fare PRIMARY KEY (id),
);

CREATE TABLE trip (
    id INT NOT NULL AUTO_INCREMENT,
    tripDate DATE NOT NULL,
    priceTrip DOUBLE NOT NULL,
    originAirport INT NOT NULL,
    destinationAirport INT NOT NULL,
    CONSTRAINT Pk_trip PRIMARY KEY (id),
    CONSTRAINT Fk_trip_1 FOREIGN KEY (originAirport) REFERENCES airport(id),
    CONSTRAINT Fk_trip_2 FOREIGN KEY (destinationAirport) REFERENCES airport(id)
);

CREATE TABLE trip_booking (
    id INT NOT NULL AUTO_INCREMENT,
    date INT NOT NULL,
    trip INT NOT NULL,
    CONSTRAINT Pk_trip_booking PRIMARY KEY (id),
    CONSTRAINT Fk_trip_booking_1 FOREIGN KEY (trip) REFERENCES trip(id)
);

CREATE TABLE trip_booking_detail (
    id INT NOT NULL AUTO_INCREMENT,
    tripBooking INT NOT NULL,
    customer INT NOT NULL,
    flightFare INT NOT NULL,
    CONSTRAINT Pk_trip_booking_details PRIMARY KEY (id),
    CONSTRAINT Fk_trip_booking_details_1 FOREIGN KEY (tripBooking) REFERENCES trip_booking(id),
    CONSTRAINT Fk_trip_booking_details_2 FOREIGN KEY (customer) REFERENCES customer(id),
    CONSTRAINT Fk_trip_booking_details_3 FOREIGN KEY (flightFare) REFERENCES flight(id)
);

-- Employee Database

CREATE TABLE airline (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL,
    CONSTRAINT Pk_airline PRIMARY KEY (id)
);

CREATE TABLE tripulation_role (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(40) NOT NULL,
    description TEXT NOT NULL,
    CONSTRAINT Pk_tripulation_role PRIMARY KEY (id)
);

CREATE TABLE employee (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL,
    lastName VARCHAR(45) NOT NULL,
    age INT NOT NULL,
    airline INT NOT NULL,
    airport INT NOT NULL,
    tripulationRole INT NOT NULL,
    admissionDate DATE NOT NULL,
    CONSTRAINT Pk_employee PRIMARY KEY (id),
    CONSTRAINT Fk_employee_1 FOREIGN KEY (airline) REFERENCES airline(id),
    CONSTRAINT Fk_employee_2 FOREIGN KEY (airport) REFERENCES airport(id),
    CONSTRAINT Fk_employee_3 FOREIGN KEY (tripulationRole) REFERENCES tripulation_role(id)
);

CREATE TABLE employee_revision (
    id INT NOT NULL AUTO_INCREMENT,
    employee INT NOT NULL,
    revisionDate DATE NOT NULL,
    description TEXT NOT NULL,
    CONSTRAINT Pk_employee_revision PRIMARY KEY (id),
    CONSTRAINT Fk_employee_revision_1 FOREIGN KEY (employee) REFERENCES employee(id)
);

-- Plane Database

CREATE TABLE plane_status (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL,
    description TEXT NOT NULL,
    CONSTRAINT Pk_plane_status PRIMARY KEY (id)
)

CREATE TABLE plane_manufacture (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(40) NOT NULL,
    CONSTRAINT Pk_plane_manufacture PRIMARY KEY (id)
);

CREATE TABLE plane_model (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(40) NOT NULL,
    planeManufacture INT NOT NULL,
    CONSTRAINT Pk_plane_model PRIMARY KEY (id),
    CONSTRAINT Fk_plane_model_1 FOREIGN KEY (planeManufacture) REFERENCES plane_manufacture (id)
);

CREATE TABLE plane (
    id INT NOT NULL AUTO_INCREMENT,
    plate VARCHAR(3 0) NOT NULL,
    model INT NOT NULL,
    status INT NOT NULL,
    capacity INT NOT NULL,
    fabricationDate DATE NOT NULL,
    CONSTRAINT Pk_plane PRIMARY KEY (id),
    CONSTRAINT Fk_plane_1 FOREIGN KEY (model) REFERENCES plane_model(id),
    CONSTRAINT Fk_plane_2 FOREIGN KEY (status) REFERENCES plane_status(id)
);

CREATE TABLE plane_revision (
    id INT NOT NULL AUTO_INCREMENT,
    plane INT NOT NULL,
    revisionDate DATE NOT NULL,
    description TEXT NOT NULL,
    CONSTRAINT Pk_plane_revision PRIMARY KEY (id),
    CONSTRAINT Fk_plane_revision_1 FOREIGN KEY (plane) REFERENCES plane(id)
);

CREATE TABLE employee_check_plane (
    employee INT NOT NULL,
    planeRevision INT NOT NULL,
    CONSTRAINT Pk_employee_check_plane PRIMARY KEY (employee, planeRevision),
    CONSTRAINT Fk_employee_check_plane_1 FOREIGN KEY (employee) REFERENCES employee(id),
    CONSTRAINT Fk_employee_check_plane_2 FOREIGN KEY (planeRevision) REFERENCES plane_revision(id)
);

-- Flight Connection Database

CREATE TABLE flight_connection (
    id INT NOT NULL AUTO_INCREMENT,
    trip INT NOT NULL,
    plane INT NOT NULL,
    departureTime TIME NOT NULL,
    arrivalTime TIME NOT NULL,
    CONSTRAINT Pk_flight_connection PRIMARY KEY (id),
    CONSTRAINT Fk_flight_connection_1 FOREIGN KEY (trip) REFERENCES trip(id),   
    CONSTRAINT Fk_flight_connection_2 FOREIGN KEY (plane) REFERENCES plane(id)
);

CREATE TABLE trip_crew (
    flightConnection INT NOT NULL,
    employee INT NOT NULL,
    CONSTRAINT Pk_trip_crew PRIMARY KEY (flightConnection, employee),
    CONSTRAINT Fk_trip_crew_1 FOREIGN KEY (flightConnection) REFERENCES flight_connection(id),
    CONSTRAINT Fk_trip_crew_2 FOREIGN KEY (employee) REFERENCES employee(id)
);