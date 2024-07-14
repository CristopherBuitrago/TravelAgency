-- customer routine
DELIMITER $$

CREATE PROCEDURE create_customer (
	IN in_name VARCHAR(45),
    IN in_last_name VARCHAR(45),
    IN in_age INT,
    IN in_docType_id INT,
    IN in_docNumber INT,
	OUT out_message VARCHAR(45)
)
BEGIN
	-- declare
    DECLARE out_message VARCHAR(45);
    
    -- insert
    INSERT INTO customer (name, lastName, age, documentType, documentNumber)
    VALUES (in_name, in_last_name, in_age, in_docType_id, in_docNumber);
    
    -- verify
    IF row_count() > 0 THEN
		SET out_message = "Customer created successfully!";
	ELSE
		SET out_message = "Error creating customer";
	END IF;
END$$

CREATE PROCEDURE search_customer (
    IN in_id INT,
    OUT customer_id INT,
    OUT out_name VARCHAR(45),
    OUT out_lastName VARCHAR(45),
    OUT out_age INT,
    OUT out_documentType VARCHAR(40)
)
BEGIN
    DECLARE customer_not_found CONDITION FOR SQLSTATE '45000';
    
    -- query
    SELECT c.id, c.name, c.lastName, c.age, dt.name
    INTO customer_id, out_name, out_lastName, out_age, out_documentType
    FROM customer c
    JOIN document_type dt ON dt.id = c.documentType
    WHERE c.id = in_id;
    
    -- Si no se encontró el cliente, lanza una excepción
    IF out_name IS NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'The customer is not in the database';
    END IF;
END$$

-- user routine

CREATE PROCEDURE create_user(
    IN username VARCHAR(40),
    IN email VARCHAR(40),
    IN password VARCHAR(40),
    IN role INT,
    OUT response VARCHAR(30)
)
BEGIN
    -- insert data of user
    INSERT INTO user (username, email, password, role) VALUES (username, email, password, role);
    
    -- set message
    IF ROW_COUNT() > 0 THEN
        SET response = 'User created successfully';
    ELSE
        SET response = 'Error creating user';
    END IF;
END$$

CREATE PROCEDURE delete_user(
	IN id INT,
    OUT response VARCHAR(40)
)
BEGIN
	-- declare response
    DECLARE response VARCHAR(40);
    
    -- delete user
    DELETE FROM user WHERE id = id;
    
    --
    IF row_count() > 0 THEN
		SET response = "User deleted";
	ELSE
		SET response = "Error deleting user";
	END IF;
END$$

CREATE PROCEDURE update_user(
	IN id INT,
    IN newUsername VARCHAR(40),
    IN newEmail VARCHAR(40),
    IN newPassword VARCHAR(40),
    IN newRole INT,
    OUT response VARCHAR(40)
)
BEGIN
	-- declare response
    DECLARE response VARCHAR(40);
    
    -- update username
    UPDATE user SET 
		username = newUsername,
        email = newEmail,
        password = newPassword,
        role = newRole
    WHERE id = id;
    
    -- set result
    IF row_count() > 0 THEN
		SET response = "User modified successfully!";
	ELSE
		SET response = "Error modifying user";
	END IF;
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
	IN plate VARCHAR(10),
    OUT id INT,
    OUT plate VARCHAR(10),
    OUT chairs INT,
    OUT fabricationDate DATE,
    OUT status VARCHAR(10),
    OUT model VARCHAR(10),
    OUT airline VARCHAR(30)
)
BEGIN
	DECLARE plane_not_found CONDITION FOR SQLSTATE '45000';

    -- Query to get the plane details
    SELECT p.id, p.plate, p.chairs, p.fabricationDate, ps.name, pm.name, a.name
    INTO id, plate, chairs, fabricationDate, status, model, airline
    FROM plane p
    JOIN plane_status ps
    ON ps.id = p.status
    JOIN plane_model pm
    ON pm.id = p.model
    JOIN airline a
    ON a.id = p.airline
    WHERE p.plate = plate;
    
    -- If no rows found, raise an exception
    IF plate IS NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'The plane is not in the database';
    END IF;
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
           CONCAT(c.name, ' ', c.lastName)
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