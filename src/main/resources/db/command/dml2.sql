-- customer routine
DELIMITER $$

CREATE PROCEDURE create_customer (
    IN in_name VARCHAR(45),
    IN in_last_name VARCHAR(45),
    IN in_age INT,
    IN in_docType VARCHAR(10),
    IN in_docNumber INT,
    OUT out_message VARCHAR(45)
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
    SELECT c.id, c.name, c.lastName AS last_name, c.age, c.documentType AS document_type
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
    WHERE id = id;

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
            SET response = 'customer updated successfully!';
        ELSE
            -- if doc not exists assign an error response
            SET response = 'Ups! Document type not exists.';
        END IF;
    ELSE
        -- if the customer not exists set a message
        SET response = 'Ups! The customer not exists, try again';
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

DELIMITER ;