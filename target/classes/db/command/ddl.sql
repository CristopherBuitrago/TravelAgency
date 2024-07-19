-- Create and use database TravelAgency
DROP DATABASE IF EXISTS TravelAgency;
CREATE DATABASE TravelAgency;
USE TravelAgency;

-- Customer Database
CREATE TABLE document_type (
    code VARCHAR(10) NOT NULL,
    name VARCHAR(40) NOT NULL,
    CONSTRAINT Pk_document_type PRIMARY KEY (code)
);

CREATE TABLE customer (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL,
    lastName VARCHAR(45) NOT NULL,
    age INT NOT NULL,
    documentType VARCHAR(10) NOT NULL,
    documentNumber INT NOT NULL,
    CONSTRAINT Pk_customer PRIMARY KEY (id),
    CONSTRAINT Fk_customer_1 FOREIGN KEY (documentType) REFERENCES document_type(code)
);

-- Airport Database
CREATE TABLE country (
    code VARCHAR(10) NOT NULL,
    name VARCHAR(45) NOT NULL,
    CONSTRAINT Pk_country PRIMARY KEY (code)
);

CREATE TABLE city (
    code VARCHAR(10) NOT NULL,
    name VARCHAR(30) NOT NULL,
    country VARCHAR(10) NOT NULL,
    CONSTRAINT Pk_city PRIMARY KEY (code),
    CONSTRAINT Fk_city_1 FOREIGN KEY (country) REFERENCES country(code)
);

CREATE TABLE airline (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL,
    CONSTRAINT Pk_airline PRIMARY KEY (id)
);

CREATE TABLE airport (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL,
    city VARCHAR(10) NOT NULL,
    CONSTRAINT Pk_airport PRIMARY KEY (id),
    CONSTRAINT Fk_airport_1 FOREIGN KEY (city) REFERENCES city(code)
);

CREATE TABLE airport_has_airline (
    airline INT NOT NULL,
    airport INT NOT NULL,
    CONSTRAINT Pk_airport_has_airline PRIMARY KEY (airline, airport),
    CONSTRAINT Fk_airport_has_airline_1 FOREIGN KEY (airline) REFERENCES airline(id),
    CONSTRAINT Fk_airport_has_airline_2 FOREIGN KEY (airport) REFERENCES airport(id)
);

CREATE TABLE door (
    code VARCHAR(10) NOT NULL,
    airport INT NOT NULL,
    CONSTRAINT Pk_door PRIMARY KEY (code),
    CONSTRAINT Fk_door_1 FOREIGN KEY (airport) REFERENCES airport(id)
);

-- TripBooking Database
CREATE TABLE flight_fare (
    id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(20) NOT NULL,
    details TEXT NOT NULL,
    value DOUBLE NOT NULL,
    CONSTRAINT Pk_flight_fare PRIMARY KEY (id)
);

CREATE TABLE scale (
    code VARCHAR(10) NOT NULL,
    originAirport INT NOT NULL,
    destinationAirport INT NOT NULL,
    CONSTRAINT Pk_scale PRIMARY KEY (code),
    CONSTRAINT Fk_scale_1 FOREIGN KEY (originAirport) REFERENCES airport (id),
    CONSTRAINT Fk_scale_2 FOREIGN KEY (destinationAirport) REFERENCES airport (id)
);

CREATE TABLE trip (
    id INT NOT NULL AUTO_INCREMENT,
    date DATE NOT NULL,
    price DOUBLE NOT NULL,
    flightFare INT NOT NULL,
    CONSTRAINT Pk_trip PRIMARY KEY (id),
    CONSTRAINT Fk_trip_2 FOREIGN KEY (flightFare) REFERENCES flight_fare(id)
);

CREATE TABLE trip_has_scale (
    scale VARCHAR(10) NOT NULL,
    trip INT NOT NULL,
    CONSTRAINT Pk_trip_has_scale PRIMARY KEY (scale, trip),
    CONSTRAINT Fk_trip_has_scale_1 FOREIGN KEY (scale) REFERENCES scale (code),
    CONSTRAINT Fk_trip_has_scale_2 FOREIGN KEY (trip) REFERENCES trip (id)
);

CREATE TABLE payment_method (
    code VARCHAR(10) NOT NULL,
    name VARCHAR(30) NOT NULL,
    description VARCHAR(50) NOT NULL,
    CONSTRAINT Pk_payment_method PRIMARY KEY (code)
);

CREATE TABLE payment (
    id INT NOT NULL AUTO_INCREMENT,
    paymentMethod VARCHAR(10) NOT NULL,
    cardNumber VARCHAR(10) DEFAULT NULL,
    amount DOUBLE NOT NULL,
    paymentDate DATE NOT NULL,
    customer INT NOT NULL,
    purchasedTrip INT NOT NULL,
    CONSTRAINT Pk_payment PRIMARY KEY (id),
    CONSTRAINT Fk_payment_1 FOREIGN KEY (paymentMethod) REFERENCES payment_method(code),
    CONSTRAINT Fk_payment_2 FOREIGN KEY (customer) REFERENCES customer(id),
    CONSTRAINT Fk_payment_3 FOREIGN KEY (purchasedTrip) REFERENCES trip(id)
);

CREATE TABLE customer_reservation (
    id INT NOT NULL AUTO_INCREMENT,
    customer INT NOT NULL,
    trip INT NOT NULL,
    payment INT NOT NULL,
    flightFare INT NOT NULL,
    reservationDate DATE NOT NULL,
    CONSTRAINT Pk_customer_reservation PRIMARY KEY (id),
    CONSTRAINT Fk_customer_reservation_1 FOREIGN KEY (customer) REFERENCES customer(id),
    CONSTRAINT Fk_customer_reservation_2 FOREIGN KEY (trip) REFERENCES trip(id),
    CONSTRAINT Fk_customer_reservation_3 FOREIGN KEY (payment) REFERENCES payment(id),
    CONSTRAINT Fk_customer_reservation_4 FOREIGN KEY (flightFare) REFERENCES flight_fare(id)
);

-- Employee Database
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
    tripulationRole INT NOT NULL,
    admissionDate DATE NOT NULL,
    airline INT NOT NULL,
    airport INT NOT NULL,
    CONSTRAINT Pk_employee PRIMARY KEY (id),
    CONSTRAINT Fk_employee_1 FOREIGN KEY (airline) REFERENCES airline(id),
    CONSTRAINT Fk_employee_2 FOREIGN KEY (airport) REFERENCES airport(id),
    CONSTRAINT Fk_employee_3 FOREIGN KEY (tripulationRole) REFERENCES tripulation_role(id)
);

CREATE TABLE employee_revision (
    id INT NOT NULL AUTO_INCREMENT,
    employee INT NOT NULL,
    description TEXT NOT NULL,
    revisionDate DATE NOT NULL,
    CONSTRAINT Pk_employee_revision PRIMARY KEY (id),
    CONSTRAINT Fk_employee_revision_1 FOREIGN KEY (employee) REFERENCES employee(id)
);

-- Plane Database
-- fixed
CREATE TABLE plane_status (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    description TEXT NOT NULL,
    CONSTRAINT Pk_plane_status PRIMARY KEY (id)
);

-- fixed
CREATE TABLE plane_manufacture (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT Pk_plane_manufacture PRIMARY KEY (id)
);

-- fixed
CREATE TABLE plane_model (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    planeManufacture INT NOT NULL,
    CONSTRAINT Pk_plane_model PRIMARY KEY (id),
    CONSTRAINT Fk_plane_model_1 FOREIGN KEY (planeManufacture) REFERENCES plane_manufacture(id)
);

CREATE TABLE plane (
    id INT NOT NULL AUTO_INCREMENT,
    plate VARCHAR(10) NOT NULL UNIQUE,
    chairs INT NOT NULL,
    status INT NOT NULL,
    model INT NOT NULL,
    fabricationDate DATE NOT NULL,
    airline INT NOT NULL,
    CONSTRAINT Pk_plane PRIMARY KEY (id),
    CONSTRAINT Fk_plane_1 FOREIGN KEY (model) REFERENCES plane_model(id),
    CONSTRAINT Fk_plane_2 FOREIGN KEY (status) REFERENCES plane_status(id),
    CONSTRAINT Fk_plane_3 FOREIGN KEY (airline) REFERENCES airline(id)
);

CREATE TABLE plane_revision (
    id INT NOT NULL AUTO_INCREMENT,
    revisionDate DATE NOT NULL,
    plane INT NOT NULL,
    description TEXT NOT NULL,
    employee INT NOT NULL,
    CONSTRAINT Pk_plane_revision PRIMARY KEY (id),
    CONSTRAINT Fk_plane_revision_1 FOREIGN KEY (plane) REFERENCES plane(id),
    CONSTRAINT Fk_plane_revision_2 FOREIGN KEY (employee) REFERENCES employee(id)
);

-- Flight Connection Database
CREATE TABLE flight_connection (
    id INT NOT NULL AUTO_INCREMENT,
    connectionNumber INT NOT NULL,
    trip INT NOT NULL,
    plane INT NOT NULL,
    departureTime TIME NOT NULL,
    arrivalTime TIME NOT NULL,
    availableChairs INT NOT NULL,
    CONSTRAINT Pk_flight_connection PRIMARY KEY (id),
    CONSTRAINT Fk_flight_connection_1 FOREIGN KEY (trip) REFERENCES trip(id),
    CONSTRAINT Fk_flight_connection_2 FOREIGN KEY (plane) REFERENCES plane(id)
);

CREATE TABLE seat (
    seatNumber INT NOT NULL,
    flight INT NOT NULL,
    CONSTRAINT Pk_passenger_has_seat PRIMARY KEY (seatNumber, flight),
    CONSTRAINT Fk_passenger_has_seat_2 FOREIGN KEY (flight) REFERENCES flight_connection(id)
);

CREATE TABLE passenger (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL,
    lastName VARCHAR(45) NOT NULL,
    age INT NOT NULL,
    documentType VARCHAR(10) NOT NULL,
    documentNumber INT NOT NULL,
    seatNumber INT NOT NULL,
    seatFlight INT NOT NULL,
    CONSTRAINT Pk_passenger PRIMARY KEY (id),
    CONSTRAINT Fk_passenger_1 FOREIGN KEY (documentType) REFERENCES document_type(code),
    CONSTRAINT Fk_passenger_2 FOREIGN KEY (seatNumber, seatFlight) REFERENCES seat(seatNumber, flight)
);

CREATE TABLE trip_crew (
    employee INT NOT NULL,
    flightConnection INT NOT NULL,
    CONSTRAINT Pk_trip_crew PRIMARY KEY (flightConnection, employee),
    CONSTRAINT Fk_trip_crew_1 FOREIGN KEY (flightConnection) REFERENCES flight_connection(id),
    CONSTRAINT Fk_trip_crew_2 FOREIGN KEY (employee) REFERENCES employee(id)
);

-- User Database

CREATE TABLE permission (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL,
    description TEXT NOT NULL,
    CONSTRAINT Pk_permission PRIMARY KEY (id)
);

CREATE TABLE role (
    code VARCHAR(10) NOT NULL,
    name VARCHAR(45) NOT NULL,
    description TEXT NOT NULL,
    CONSTRAINT Pk_role PRIMARY KEY (code)
);

CREATE TABLE role_has_permission (
    role VARCHAR(10) NOT NULL,
    permission INT NOT NULL,
    CONSTRAINT Pk_role_has_permission PRIMARY KEY (role, permission),
    CONSTRAINT Fk_role_has_permission_1 FOREIGN KEY (role) REFERENCES role(code)
);

CREATE TABLE user (
    id INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(40) NOT NULL,
    email VARCHAR(40) NOT NULL UNIQUE,
    password VARCHAR(40) NOT NULL,
    role VARCHAR(10) NOT NULL,  
    CONSTRAINT Pk_user PRIMARY KEY (id),
    CONSTRAINT Fk_user_1 FOREIGN KEY (role) REFERENCES role(code)
);