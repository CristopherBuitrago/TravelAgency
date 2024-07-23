-- customer routine
DELIMITER $$

CREATE PROCEDURE create_customer (
    IN in_name VARCHAR(45),
    IN in_last_name VARCHAR(45),
    IN in_age INT,
    IN in_docType VARCHAR(10),
    IN in_docNumber INT,
    OUT out_message VARCHAR(60)
)
BEGIN
    DECLARE docExists INT;

    -- verify if the document type exists
    SELECT COUNT(*) INTO docExists
    FROM document_type dt
    WHERE dt.code = in_docType;

    IF docExists > 0 THEN
        -- if docExists create customer
        INSERT INTO customer (name, lastName, age, documentType, documentNumber) VALUES
        (in_name, in_last_name, in_age, in_docType, in_docNumber);
        -- set custom message
        SET out_message = "Customer created successfully!";
    ELSE
        -- set a custom message if doc not exists
        SET out_message = "Ups! The document type not exists. Please try again";
    END IF;
END$$

CREATE PROCEDURE find_customer (
    IN in_id INT
)
BEGIN    
    -- query
    SELECT c.id, c.name, c.lastName AS last_name, c.age, c.documentType AS document_type, c.documentNumber AS document_number
    FROM customer c
    WHERE c.id = in_id;
END$$

CREATE PROCEDURE update_customer(
    IN in_id INT,
    IN in_name VARCHAR(45),
    IN in_last_name VARCHAR(45),
    IN in_age INT,
    IN in_docType VARCHAR(10),
    IN in_docNumber INT,
    OUT out_message VARCHAR(45)
)
BEGIN
    DECLARE customerExists INT;
    DECLARE docExists INT;

    -- verify if user exists
    SELECT COUNT(*) INTO customerExists
    FROM customer
    WHERE id = in_id;

    IF customerExists > 0 THEN
        -- verify if the docType is into database
        SELECT COUNT(*) INTO docExists
        FROM document_type
        WHERE code = in_docType;

        IF docExists > 0 THEN
            -- update customer if doc exists
            UPDATE customer SET 
                name = in_name,
                lastName = in_last_name,
                age = in_age,
                documentType = in_docType,
                documentNumber = in_docNumber
            WHERE id = id;
            SET out_message = 'customer updated successfully!';
        ELSE
            -- if doc not exists assign an error out_message
            SET out_message = 'Ups! Document type not exists.';
        END IF;
    ELSE
        -- if the customer not exists set a message
        SET out_message = 'Ups! The customer not exists, try again';
    END IF;
END$$

-- user routine

CREATE PROCEDURE create_user(
    IN username VARCHAR(40),
    IN email VARCHAR(40),
    IN password VARCHAR(40),
    IN role VARCHAR(10),
    OUT response VARCHAR(30)
)
BEGIN
    DECLARE roleExists INT;

    SELECT COUNT(*) INTO roleExists
    FROM role
    WHERE code = role;

    IF roleExists > 0 THEN
        -- crear el usuario si el rol existe
        INSERT INTO user(username, email, password, role) VALUES (username, email, password, role);
        SET response = 'User created successfully';
    ELSE
        -- Si el rol no existe, asignar un mensaje de error a la respuesta
        SET response = 'Role does not exist';
    END IF;
END$$

CREATE PROCEDURE delete_user(
	IN id INT,
    OUT response VARCHAR(40)
)
BEGIN
	-- declare user exists
    DECLARE userExists INT;

    -- verify user exists
    SELECT COUNT(*) INTO userExists
    FROM user u
    WHERE u.id = id;

    IF userExists > 0 THEN
        -- delete user
        DELETE FROM user u WHERE u.id = id;
        -- set response
        SET response = "User created successfully!";
    ELSE
        -- if user not exists set a message
        SET response = "Error: The user not exists.";
    END IF;
END$$

CREATE PROCEDURE update_user(
    IN id INT,
    IN newUsername VARCHAR(40),
    IN newEmail VARCHAR(40),
    IN newPassword VARCHAR(40),
    IN newRole VARCHAR(10),
    OUT response VARCHAR(40)
)
BEGIN
    DECLARE userExists INT;
    DECLARE roleExists INT;

    -- Verificar si el usuario existe
    SELECT COUNT(*) INTO userExists
    FROM user
    WHERE id = id;

    IF userExists > 0 THEN
        -- Verificar si el nuevo rol existe en la tabla de roles
        SELECT COUNT(*) INTO roleExists
        FROM role
        WHERE code = newRole;

        IF roleExists > 0 THEN
            -- Actualizar el usuario si el rol existe
            UPDATE user SET 
                username = newUsername,
                email = newEmail,
                password = newPassword,
                role = newRole
            WHERE id = id;

            SET response = 'User updated successfully';
        ELSE
            -- Si el rol no existe, asignar un mensaje de error a la respuesta
            SET response = 'Role does not exist';
        END IF;
    ELSE
        -- Si el usuario no existe, asignar un mensaje de error a la respuesta
        SET response = 'User does not exist';
    END IF;
END$$

-- added find user
CREATE PROCEDURE find_user (
    IN id INT
)
BEGIN
    -- query
    SELECT u.id, u.username, u.email, r.name AS role
    FROM user u
    JOIN role r
    ON r.code = u.role
    WHERE u.id = id;
END $$

-- added view user_view
CREATE VIEW user_view AS
SELECT u.id, u.username, u.email, r.name AS role
FROM user u
JOIN role r
ON r.code = u.role;

-- added get_users
CREATE PROCEDURE get_users()
BEGIN 
    -- select user_view
    SELECT * FROM user_view;
END$$

-- plane routine

CREATE PROCEDURE add_plane (
	IN plate VARCHAR(30),
    IN chairs INT,
    IN fabricationDate DATE,
    IN status INT,
    IN airline INT,
    IN model INT,
    OUT response VARCHAR(30)
)
BEGIN
	-- declare the response
    DECLARE response VARCHAR(30);
    
	-- insert int plane
    INSERT INTO plane (plate, chairs, fabricationDate, status, model, airline)
    VALUES (plate, chairs, fabricationDate, status, airline, model);
    
    -- set message
    IF row_count() > 0 THEN
		SET response = "Plane created successfully!";
	ELSE
		SET response = "Error creating plane";
	END IF;
END$$

CREATE PROCEDURE search_plane (
	IN plate VARCHAR(10)
)
BEGIN
    -- Query to get the plane details
    SELECT p.id, p.plate, p.chairs, p.fabricationDate, ps.name, pm.name, a.name
    FROM plane p
    JOIN plane_status ps
    ON ps.id = p.status
    JOIN plane_model pm
    ON pm.id = p.model
    JOIN airline a
    ON a.id = p.airline
    WHERE p.plate = plate;

END$$

CREATE PROCEDURE add_employee_flight (
	IN employeeId INT,
    IN flightId INT,
    OUT response VARCHAR(50)
)
BEGIN
	-- declare response 
    DECLARE response VARCHAR(50);
    
    -- insert into trip_crew
    INSERT INTO trip_crew (employee, flightConnection)
    VALUES (employeeId, flightId);
    
    -- set response
    IF row_count() > 0 THEN
		SET response = "Employee added successfully";
	ELSE
		SET response = "Error adding employee";
	END IF;
END$$

-- trip routine

CREATE PROCEDURE create_reserve (
	IN tripId INT,
    IN paymentId INT,
    IN flightFareId INT,
    IN customerId INT,
    OUT response VARCHAR(30)
)
BEGIN
	-- declare response
    DECLARE response VARCHAR(70);
    
    -- insert into customer_reservation
    INSERT INTO customer_reservation (trip, payment, flightFare, customer)
    VALUES (tripId, paymentId, flightFareId, customerId);
    
    -- set response
    IF row_count() > 0 THEN
		SET response = "Reservation created successfully!";
	ELSE
		SET response = "Error creating reservation";
	END IF;
END$$

CREATE PROCEDURE search_reserve (
    IN in_reserve INT,
    OUT reserve_id INT,
    OUT trip_id INT,
    OUT total_payed DOUBLE,
    OUT flight_cost DOUBLE,
    OUT customer VARCHAR(40)
)
BEGIN
    DECLARE reserve_not_found CONDITION FOR SQLSTATE '45000';

    -- Query to get the reservation details
    SELECT cr.id,
           cr.trip, 
           p.amount, 
           ff.value, 
           CONCAT(c.name, ' ', c.lastName) AS customer
    INTO reserve_id,
         trip_id, 
         total_payed, 
         flight_cost, 
         customer
    FROM customer_reservation cr
    JOIN payment p ON p.id = cr.payment
    JOIN flight_fare ff ON ff.id = cr.flightFare
    JOIN customer c ON c.id = cr.customer
    WHERE cr.id = in_reserve;

    -- If no rows found, raise an exception
    IF reserve_id IS NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'The reservation is not in the database';
    END IF;
END$$

-- maintenance routine


CREATE PROCEDURE regis_maintan_plane (
	IN planeId INT,
    IN revisionDate DATE,
    IN description TEXT,
    IN employeeId INT,
    OUT response VARCHAR(50)
)
BEGIN
	-- declare response
    DECLARE response VARCHAR(50);
    
    -- insert into plane_revision
    INSERT INTO plane_revision (revisionDate, plane, description, employee)
    VALUES (revisionDate, planeId, description, employeeId);
    
    -- set response
    IF row_count() > 0 THEN
		SET response = "Revision registered successfully!";
	ELSE
		SET response = "Error registering revision";
	END IF;
END$$

CREATE PROCEDURE add_trip_booking(
    IN in_customer_id INT,
    IN in_trip_id INT,
    IN in_booking_date DATE,
    OUT response VARCHAR(200)
)
this_proc:BEGIN
    DECLARE customerExists INT;
    DECLARE tripExists INT;
    DECLARE bookingExists INT;
    DECLARE clientDidPayment INT;
    DECLARE paymentId INT;
    
    -- verify if the customer exists    
    SELECT COUNT(*) INTO customerExists
    FROM customer c
    WHERE c.id = in_customer_id;
    
    IF (customerExists = 0) THEN
        -- if the customer doesn't exist, set error message
        SET response = "Ups! it seems that the client does not exist.";
        LEAVE this_proc;
    END IF;
    
    -- verify if the trip exists    
    SELECT COUNT(*) INTO tripExists
    FROM trip t
    WHERE t.id = in_trip_id;
    
    IF (tripExists = 0) THEN
        -- if the trip doesn't exist, set error message
        SET response = "Ups! it seems that the trip does not exist.";
        LEAVE this_proc;
    END IF;
    
    -- verify if the customer has made a booking
    SELECT COUNT(*) INTO bookingExists
    FROM customer_reservation cr
    WHERE cr.customer = in_customer_id AND cr.trip = in_trip_id;
    
    IF (bookingExists > 0) THEN
        -- set an error message
        SET response = "Ups! it seems that the customer made a booking before.";
        LEAVE this_proc;
    END IF;
    
    -- verify if the customer did the payment for the trip
    SELECT p.id INTO paymentId
    FROM payment p
    WHERE p.customer = in_customer_id AND p.purchasedTrip = in_trip_id
    LIMIT 1;
    
    SET clientDidPayment = (paymentId IS NOT NULL);
    
    IF (clientDidPayment = 0) THEN
        -- if the client didn't do the payment, set an error message
        SET response = "Ups! it seems that the customer has not made the payment yet.";
        LEAVE this_proc;
    END IF;
    
    -- insert into customer_reservation
    INSERT INTO customer_reservation (customer, trip, payment, reservationDate)
    VALUES (in_customer_id, in_trip_id, paymentId, in_booking_date);
    
    -- Set a successful message
    SET response = "Trip booking registered successfully!";
END$$


-- delete trip booking
CREATE PROCEDURE delete_trip_booking(
	IN in_trip_booking_id INT,
    OUT response VARCHAR(200)
)
BEGIN
	DECLARE tripBookingExists INT;
	
    -- verify if the trip exists
    SELECT COUNT(*) INTO tripBookingExists
    FROM  customer_reservation cr
    WHERE cr.id + in_trip_booking_id;
    
    IF (tripBookingExists > 0 ) THEN
		-- if trip exists, delete trip
        DELETE FROM customer_reservation WHERE id = in_trip_id;
        
        -- set successfull message
        SET response = "Trip booking deleted successfully!";
	ELSE
		-- set an error message
        SET response = "Ups! Trip booking not found.";
	END IF;
END$$

-- find trip by id
CREATE PROCEDURE find_booking_id (
	IN in_trip_booking_id INT
)
BEGIN
	-- query
    SELECT c.name AS customer, cr.trip AS trip_id, p.amount AS payment_amount, cr.flightFare AS flight_fare_id
    FROM customer_reservation cr
    JOIN customer c
    ON c.id = cr.customer
    JOIN payment p
    ON p.customer = c.id
    WHERE cr.id = in_trip_booking_id;
END$$

CREATE PROCEDURE find_booking_customer(
	IN in_customer_id INT
)
BEGIN
	-- query
    SELECT cr.id, c.name AS customer, cr.trip AS trip_id, p.amount AS payment_amount, cr.flightFare AS flight_fare_id
    FROM customer_reservation cr
    JOIN customer c
    ON c.id = cr.customer
    JOIN payment p
    ON p.customer = c.id
    WHERE c.id = in_customer_id;
END$$

CREATE PROCEDURE update_trip_booking(
	IN in_trip_booking_id INT,
    IN in_customer_id INT,
    IN in_trip_id INT,
    IN in_payment_id INT,
    OUT response VARCHAR(200)
)
this_proc:BEGIN
	DECLARE tripBookingExists INT;
    DECLARE customerExists INT;
    DECLARE tripExists INT;
    DECLARE paymentExists INT;
    
    -- verify if the trip booking exists
    SELECT COUNT(*) INTO tripBookingExists
    FROM customer_reservation cr
    WHERE cr.id = in_trip_booking_id;
    
    IF (tripBookingExists = 0) THEN
        -- if the trip booking doesn't exist, set error message
        SET response = "Ups! it seems that the trip booking does not exist.";
        LEAVE this_proc;
    END IF;
    
    -- verify if the customer exists
    SELECT COUNT(*) INTO customerExists
    FROM customer c
    WHERE c.id = in_customer_id;
    
    IF (customerExists = 0) THEN
        -- if the customer doesn't exist, set error message
        SET response = "Ups! it seems that the customer does not exist.";
        LEAVE this_proc;
    END IF;
    
    -- verify if the trip exists
    SELECT COUNT(*) INTO tripExists
    FROM trip t
    WHERE t.id = in_trip_id;
    
    IF (tripExists = 0) THEN
        -- if the trip doesn't exist, set error message
        SET response = "Ups! it seems that the trip does not exist.";
        LEAVE this_proc;
    END IF;
    
    -- verify if the payment exists
    SELECT COUNT(*) INTO paymentExists
    FROM payment p
    WHERE p.id = in_payment_id;
    
    IF (paymentExists = 0) THEN
        -- if the payment doesn't exist, set error message
        SET response = "Ups! it seems that the payment does not exist.";
        LEAVE this_proc;
    END IF;
    
    -- update the trip booking
    UPDATE customer_reservation
    SET customer = in_customer_id,
        trip = in_trip_id,
        payment = in_payment_id
    WHERE id = in_trip_booking_id;
    
    -- set a successful message
    SET response = "Trip booking updated successfully!";
END$$

-- payments

CREATE PROCEDURE create_payment_tc (
    IN in_card_number VARCHAR(10),
    IN in_payment_amount DOUBLE,
    IN in_payment_date DATE,
    IN in_customer_id INT,
    IN in_trip_id INT,
    OUT response VARCHAR(200)
)
this_proc:BEGIN
    DECLARE customerExists INT;
    DECLARE tripExists INT;
    DECLARE paymentChange DOUBLE;
    
    -- verify if the customer exists
    SELECT COUNT(*) INTO customerExists
    FROM customer
    WHERE id = in_customer_id;
    
    IF (customerExists = 0) THEN
        -- if customer doesn't exist set an error message
        SET response = "Ups! it seems that the client doesn't exist. Please try again";
        -- leave this procedure
        LEAVE this_proc;
    END IF;
    
    -- verify if the trip exists
    SELECT COUNT(*) INTO tripExists
    FROM trip
    WHERE id = in_trip_id;
    
    IF (tripExists = 0) THEN
        -- if the trip doesn't exist set an error message
        SET response = "Ups! it seems that the trip doesn't exist. Please try again.";
        -- leave this procedure
        LEAVE this_proc;
    END IF;
    
    -- verify if it is a bad payment
    SET paymentChange = calculate_change(in_payment_amount, in_trip_id);
    
    IF (paymentChange < 0.0) THEN
        -- if the client doesn't have enough money set an error message
        SET response = "Ups! it seems that the payment does not match the price of the trip.";
        -- leave this procedure
        LEAVE this_proc;
    END IF;
    
    -- insert the data
    INSERT INTO payment (paymentMethod, cardNumber, amount, `change`, paymentDate, customer, purchasedTrip) 
    VALUES ("TC", in_card_number, in_payment_amount, paymentChange, in_payment_date, in_customer_id, in_trip_id);
    
    -- set successful message
    SET response = CONCAT("Payment created successfully. Total change: ", paymentChange);
END$$

CREATE PROCEDURE create_payment_td (
    IN in_card_number VARCHAR(10),
    IN in_payment_amount DOUBLE,
    IN in_payment_date DATE,
    IN in_customer_id INT,
    IN in_trip_id INT,
    OUT response VARCHAR(200)
)
this_proc:BEGIN
    DECLARE customerExists INT;
    DECLARE tripExists INT;
    DECLARE paymentChange DOUBLE;
    
    -- verify if the customer exists
    SELECT COUNT(*) INTO customerExists
    FROM customer
    WHERE id = in_customer_id;
    
    IF (customerExists = 0) THEN
        -- if customer doesn't exist set an error message
        SET response = "Ups! it seems that the client doesn't exist. Please try again";
        -- leave this procedure
        LEAVE this_proc;
    END IF;
    
    -- verify if the trip exists
    SELECT COUNT(*) INTO tripExists
    FROM trip
    WHERE id = in_trip_id;
    
    IF (tripExists = 0) THEN
        -- if the trip doesn't exist set an error message
        SET response = "Ups! it seems that the trip doesn't exist. Please try again.";
        -- leave this procedure
        LEAVE this_proc;
    END IF;
    
    -- verify if it is a bad payment
    SET paymentChange = calculate_change(in_payment_amount, in_trip_id);
    
    IF (paymentChange < 0.0) THEN
        -- if the client doesn't have enough money set an error message
        SET response = "Ups! it seems that the payment does not match the price of the trip.";
        -- leave this procedure
        LEAVE this_proc;
    END IF;
    
    -- insert the data
    INSERT INTO payment (paymentMethod, cardNumber, amount, `change`, paymentDate, customer, purchasedTrip) 
    VALUES ("TD", in_card_number, in_payment_amount, paymentChange, in_payment_date, in_customer_id, in_trip_id);
    
    -- set successful message
    SET response = CONCAT("Payment created successfully. Total change: ", paymentChange);
END$$

CREATE PROCEDURE create_payment_efec (
    IN in_payment_amount DOUBLE,
    IN in_payment_date DATE,
    IN in_customer_id INT,
    IN in_trip_id INT,
    OUT response VARCHAR(200)
)
this_proc:BEGIN
    DECLARE customerExists INT;
    DECLARE tripExists INT;
    DECLARE paymentChange DOUBLE;
    
    -- verify if the customer exists
    SELECT COUNT(*) INTO customerExists
    FROM customer
    WHERE id = in_customer_id;
    
    IF (customerExists = 0) THEN
        -- if customer doesn't exist set an error message
        SET response = "Ups! it seems that the client doesn't exist. Please try again";
        -- leave this procedure
        LEAVE this_proc;
    END IF;
    
    -- verify if the trip exists
    SELECT COUNT(*) INTO tripExists
    FROM trip
    WHERE id = in_trip_id;
    
    IF (tripExists = 0) THEN
        -- if the trip doesn't exist set an error message
        SET response = "Ups! it seems that the trip doesn't exist. Please try again.";
        -- leave this procedure
        LEAVE this_proc;
    END IF;
    
    -- verify if it is a bad payment
    SET paymentChange = calculate_change(in_payment_amount, in_trip_id);
    
    IF (paymentChange < 0.0) THEN
        -- if the client doesn't have enough money set an error message
        SET response = "Ups! it seems that the payment does not match the price of the trip.";
        -- leave this procedure
        LEAVE this_proc;
    END IF;
    
    -- insert the data
    INSERT INTO payment (paymentMethod, cardNumber, amount, `change`, paymentDate, customer, purchasedTrip) 
    VALUES ("EFEC", NULL, in_payment_amount, paymentChange, in_payment_date, in_customer_id, in_trip_id);
    
    -- set successful message
    SET response = CONCAT("Payment created successfully. Total change: ", paymentChange);
END$$

-- flights

--views

CREATE VIEW `available_pilots` AS
SELECT e.id, CONCAT(e.name, " ", e.lastName) AS employee, tr.name AS role
FROM employee e
JOIN tripulation_role tr ON tr.id = e.tripulationRole
WHERE e.tripulationRole = 1
AND e.id NOT IN (
    SELECT tc.employee
    FROM trip_crew tc
);


CREATE VIEW `available_copilots` AS
SELECT e.id, CONCAT(e.name, " ", e.lastName) AS employee, tr.name AS role
FROM employee e
JOIN tripulation_role tr ON tr.id = e.tripulationRole
WHERE e.tripulationRole = 2
AND e.id NOT IN (
    SELECT tc.employee
    FROM trip_crew tc
);


CREATE VIEW `available_engineers` AS
SELECT e.id, CONCAT(e.name, " ", e.lastName) AS employee, tr.name AS role
FROM employee e
JOIN tripulation_role tr ON tr.id = e.tripulationRole
WHERE e.tripulationRole = 6
AND e.id NOT IN (
    SELECT tc.employee
    FROM trip_crew tc
);

CREATE VIEW `available_attendants` AS
SELECT e.id, CONCAT(e.name, " ", e.lastName) AS employee, tr.name AS role
FROM employee e
JOIN tripulation_role tr ON tr.id = e.tripulationRole
WHERE e.tripulationRole = 4
AND e.id NOT IN (
    SELECT tc.employee
    FROM trip_crew tc
);

CREATE VIEW `available_flights` AS
SELECT fc.id, fc.connectionNumber AS connection_number
FROM flight_connection fc
JOIN trip_crew tc
ON tc.flightConnection = fc.id
WHERE fc.id NOT IN (
	SELECT flightConnection
    FROM trip_crew
);

-- procedures

CREATE PROCEDURE `create_flight`(
	IN in_connection_number INT,
    IN in_trip_id INT,
    IN in_plane_id INT,
	IN in_departure_time TIME,
    IN in_arrival_time TIME,
    OUT response VARCHAR(200)
)
this_proc:BEGIN
	DECLARE flightExists INT;
    DECLARE numberChairs INT;
    DECLARE tripExists INT;
    DECLARE planeExists INT;
    
    -- verify if the flight exists
	SELECT COUNT(*) INTO flightExists
    FROM flight_connection fc
    WHERE fc.trip = in_trip_id
    AND fc.plane = in_plane_id;
    
    IF (flightExists > 0) THEN
		-- set an error message
        SET response = "Ups! it seems that the flight already exists. Please try again";
        LEAVE this_proc;
	END IF;
    
    -- verify if the trip exists
    SELECT COUNT(*) INTO tripExists
    FROM trip t
    WHERE t.id = in_trip_id;
    
    IF (tripExists = 0) THEN
		-- set error message
        SET response = "Ups! it seems that the trip does not exists";
		LEAVE this_proc;
	END IF;
    
    -- verify if the plane exists
    SELECT COUNT(*) INTO planeExists
    FROM plane p
    WHERE p.id = in_plane_id;
    
    IF (planeExists = 0) THEN
		-- set error message
        SET response = "Ups! it seems that the plane does not exists";
        LEAVE this_proc;
	END IF;
    
    -- calculate total chair's number
    SELECT p.chairs INTO numberChairs
    FROM plane p
    WHERE p.id = in_plane_id;
    
    -- insert the data
    INSERT INTO flight_connection (connectionNumber, trip, plane, departureTime, arrivalTime, availableChairs)
	VALUES (in_connection_number, in_trip_id, in_plane_id, in_departure_time, in_arrival_time, numberChairs);
    
	-- set successful message
    SET response = "Flight created successfully!";
END$