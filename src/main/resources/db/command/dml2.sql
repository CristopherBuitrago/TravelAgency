DELIMITER $$

CREATE FUNCTION `calculate_change`(amount DOUBLE, tripId INT) RETURNS double
BEGIN
	DECLARE tripPrice  DOUBLE;
    DECLARE totalChange DOUBLE;
    
    -- select the trip's price
    SELECT t.price INTO tripPrice
    FROM trip t
    WHERE t.id = tripId;
    
    -- calculate the change
    SET totalChange = (amount - tripPrice);
    
    -- return the value
	RETURN totalChange;
END$$

CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`%` 
    SQL SECURITY DEFINER
VIEW `TravelAgency`.`available_attendants` AS
    SELECT 
        `e`.`id` AS `id`,
        CONCAT(`e`.`name`, ' ', `e`.`lastName`) AS `employee`,
        `tr`.`name` AS `role`
    FROM
        (`TravelAgency`.`employee` `e`
        JOIN `TravelAgency`.`tripulation_role` `tr` ON ((`tr`.`id` = `e`.`tripulationRole`)))
    WHERE
        ((`e`.`tripulationRole` = 4)
            AND `e`.`id` IN (SELECT 
                `tc`.`employee`
            FROM
                `TravelAgency`.`trip_crew` `tc`)
            IS FALSE)$$

CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`%` 
    SQL SECURITY DEFINER
VIEW `TravelAgency`.`available_copilots` AS
    SELECT 
        `e`.`id` AS `id`,
        CONCAT(`e`.`name`, ' ', `e`.`lastName`) AS `employee`,
        `tr`.`name` AS `role`
    FROM
        (`TravelAgency`.`employee` `e`
        JOIN `TravelAgency`.`tripulation_role` `tr` ON ((`tr`.`id` = `e`.`tripulationRole`)))
    WHERE
        ((`e`.`tripulationRole` = 2)
            AND `e`.`id` IN (SELECT 
                `tc`.`employee`
            FROM
                `TravelAgency`.`trip_crew` `tc`)
            IS FALSE)$$

CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`%` 
    SQL SECURITY DEFINER
VIEW `TravelAgency`.`available_engineers` AS
    SELECT 
        `e`.`id` AS `id`,
        CONCAT(`e`.`name`, ' ', `e`.`lastName`) AS `employee`,
        `tr`.`name` AS `role`
    FROM
        (`TravelAgency`.`employee` `e`
        JOIN `TravelAgency`.`tripulation_role` `tr` ON ((`tr`.`id` = `e`.`tripulationRole`)))
    WHERE
        ((`e`.`tripulationRole` = 6)
            AND `e`.`id` IN (SELECT 
                `tc`.`employee`
            FROM
                `TravelAgency`.`trip_crew` `tc`)
            IS FALSE) $$

CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`%` 
    SQL SECURITY DEFINER
VIEW `TravelAgency`.`available_flights` AS
    SELECT 
        `fc`.`id` AS `id`,
        `fc`.`connectionNumber` AS `connection_number`
    FROM
        (`TravelAgency`.`flight_connection` `fc`
        LEFT JOIN `TravelAgency`.`trip_crew` `tc` ON ((`tc`.`flightConnection` = `fc`.`id`)))
    WHERE
        `fc`.`id` IN (SELECT 
                `TravelAgency`.`trip_crew`.`flightConnection`
            FROM
                `TravelAgency`.`trip_crew`)
            IS FALSE$$

CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`%` 
    SQL SECURITY DEFINER
VIEW `TravelAgency`.`available_pilots` AS
    SELECT 
        `e`.`id` AS `id`,
        CONCAT(`e`.`name`, ' ', `e`.`lastName`) AS `employee`,
        `tr`.`name` AS `role`
    FROM
        (`TravelAgency`.`employee` `e`
        JOIN `TravelAgency`.`tripulation_role` `tr` ON ((`tr`.`id` = `e`.`tripulationRole`)))
    WHERE
        ((`e`.`tripulationRole` = 1)
            AND `e`.`id` IN (SELECT 
                `tc`.`employee`
            FROM
                `TravelAgency`.`trip_crew` `tc`)
            IS FALSE)$$

CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`%` 
    SQL SECURITY DEFINER
VIEW `TravelAgency`.`user_view` AS
    SELECT 
        `u`.`id` AS `id`,
        `u`.`username` AS `username`,
        `u`.`email` AS `email`,
        `r`.`name` AS `role`
    FROM
        (`TravelAgency`.`user` `u`
        JOIN `TravelAgency`.`role` `r` ON ((`r`.`code` = `u`.`role`)))$$

CREATE PROCEDURE `add_employee_flight`(
    IN in_role_id INT,
    IN in_employee_id INT,
    IN in_flight_id INT,
    OUT response VARCHAR(200)
)
this_proc:BEGIN
    DECLARE validEmployee INT;

    -- switch
    CASE
        WHEN in_role_id = 1 THEN
            -- verify if the pilot is into available pilots
            SELECT COUNT(*) INTO validEmployee
            FROM available_pilots ap
            WHERE ap.id = in_employee_id;

            IF (validEmployee = 0) THEN
                -- set error message
                SET response = "Ups! it seems that the pilot is not available. Please try again";
                LEAVE this_proc;
            END IF;

            -- insert data
            INSERT INTO trip_crew (employee, flightConnection)
            VALUES (in_employee_id, in_flight_id);

            -- set successful message
            SET response = "Pilot added successful!";
            LEAVE this_proc;

        WHEN in_role_id = 2 THEN
            -- verify if the co-pilot is into available co-pilots
            SELECT COUNT(*) INTO validEmployee
            FROM available_copilots ac
            WHERE ac.id = in_employee_id;

            IF (validEmployee = 0) THEN
                -- set error message
                SET response = "Ups! it seems that the co-pilot is not available. Please try again";
                LEAVE this_proc;
            END IF;

            -- insert data
            INSERT INTO trip_crew (employee, flightConnection)
            VALUES (in_employee_id, in_flight_id);

            -- set successful message
            SET response = "Co-pilot added successful!";
            LEAVE this_proc;

        WHEN in_role_id = 4 THEN
            -- verify if the attendant is into available attendants
            SELECT COUNT(*) INTO validEmployee
            FROM available_attendants aa
            WHERE aa.id = in_employee_id;

            IF (validEmployee = 0) THEN
                -- set error message
                SET response = "Ups! it seems that the attendant is not available. Please try again";
                LEAVE this_proc;
            END IF;

            -- insert data
            INSERT INTO trip_crew (employee, flightConnection)
            VALUES (in_employee_id, in_flight_id);

            -- set successful message
            SET response = "Attendant added successful!";
            LEAVE this_proc;

        WHEN in_role_id = 6 THEN
            -- verify if the engineer is into available engineers
            SELECT COUNT(*) INTO validEmployee
            FROM available_engineers ae
            WHERE ae.id = in_employee_id;

            IF (validEmployee = 0) THEN
                -- set error message
                SET response = "Ups! it seems that the engineer is not available. Please try again";
                LEAVE this_proc;
            END IF;

            -- insert data
            INSERT INTO trip_crew (employee, flightConnection)
            VALUES (in_employee_id, in_flight_id);

            -- set successful message
            SET response = "Engineer added successful!";
            LEAVE this_proc;
            
        ELSE
            SET response = "Invalid role ID.";
            LEAVE this_proc;
    END CASE;
END$$

CREATE PROCEDURE `add_trip_booking`(
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

CREATE PROCEDURE `create_customer`(
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
    
	-- set successfull message
    SET response = "Flight created successfully!";
END$$

CREATE PROCEDURE `create_payment_efec`(
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
    DECLARE paymentExists INT;
    
    -- veify if payment exists
    SELECT COUNT(*) INTO paymentExists
    FROM payment p
    WHERE (p.customer = in_customer_id) AND (p.purchasedTrip = in_trip_id);
    
    IF (paymentExists > 0) THEN
		-- if the payment exists, set error message
        SET response = "Ups! it seems that the client did the same payment before. Please, try again";
        -- leave tis procedure
        LEAVE this_proc;
	END IF;
    
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

CREATE PROCEDURE `create_payment_tc`(
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
    DECLARE paymentExists INT;
    
    -- veify if payment exists
    SELECT COUNT(*) INTO paymentExists
    FROM payment p
    WHERE (p.customer = in_customer_id) AND (p.purchasedTrip = in_trip_id);
    
    IF (paymentExists > 0) THEN
		-- if the payment exists, set error message
        SET response = "Ups! it seems that the client did the same payment before. Please, try again";
        -- leave tis procedure
        LEAVE this_proc;
	END IF;
    
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

CREATE PROCEDURE `create_payment_td`(
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
    DECLARE paymentExists INT;
    
    -- veify if payment exists
    SELECT COUNT(*) INTO paymentExists
    FROM payment p
    WHERE (p.customer = in_customer_id) AND (p.purchasedTrip = in_trip_id);
    
    IF (paymentExists > 0) THEN
		-- if the payment exists, set error message
        SET response = "Ups! it seems that the client did the same payment before. Please, try again";
        -- leave tis procedure
        LEAVE this_proc;
	END IF;
    
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

CREATE PROCEDURE `create_plane`(
    IN p_plate VARCHAR(10),
    IN p_chairs INT,
    IN p_status INT,
    IN p_model INT,
    IN p_fabricationDate DATE,
    IN p_airline INT,
    OUT out_message VARCHAR(60)
)
BEGIN
    DECLARE plateExists INT;

    -- verify if the plate exists
    SELECT COUNT(*) INTO plateExists
    FROM plane p
    WHERE p.plate = p_plate;

    IF plateExists > 0 THEN
        -- set a custom message if plate already exists
        SET out_message = CONCAT("Ups! The plate ", UPPER(p_plate), " already exists. Please try again");
    ELSE
        -- insert the plane if plate does not exist
        INSERT INTO plane (plate, chairs, status, model, fabricationDate, airline) VALUES
        (UPPER(p_plate), p_chairs, p_status, p_model, p_fabricationDate, p_airline);
        -- set a custom message if plane created successfully
        SET out_message = "Plane created successfully!";
    END IF;
END$$

CREATE PROCEDURE `create_trip`(
	IN in_date DATE,
    IN in_price DOUBLE,
    IN in_flight_fare_id INT,
    OUT response VARCHAR(200)
)
BEGIN
	DECLARE flightFareExists INT;
    
    SELECT COUNT(*) INTO flightFareExists
    FROM flight_fare ff
    WHERE ff.id = in_flight_fare_id;
    
    -- verify if the fare exists
    IF flightFareExists > 0 THEN
		-- if fare exists, insert the data into trip
        INSERT INTO trip (date, price, flightFare) VALUES
        (in_date, in_price, in_flight_fare_id);
        
        -- set successfull message
        SET response = "Trip created succesfully!";
	ELSE
		-- if not exists set a error message
        SET response = CONCAT("Ups! the flight fare with id ",id," not exists.");
	END IF;
END$$

CREATE PROCEDURE `create_user`(
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

CREATE PROCEDURE `delete_trip`(
	IN in_trip_id INT,
    OUT response VARCHAR(200)
)
BEGIN
	DECLARE tripExists INT;
    
	-- verify if the trip exists
    SELECT COUNT(*) INTO tripExists
    FROM trip t
    WHERE t.id = in_trip_id;
    
    IF tripExists > 0 THEN
		-- if trip exists delete trip
        DELETE FROM trip WHERE id = in_trip_id;
        
        -- set successfull message
        SET response = CONCAT("Trip with id ",in_trip_id," deleted succesfully!");
	ELSE	
		-- if trip not exists set an error message
        SET response = CONCAT("Ups! Trip with id ",in_trip_id," not exists.");
	END IF;
END$$

CREATE PROCEDURE `delete_trip_booking`(
	IN in_trip_booking_id INT,
    OUT response VARCHAR(200)
)
BEGIN
	DECLARE reservationExists INT;

    -- Verificar si la reservación existe
    SELECT COUNT(*) INTO reservationExists
    FROM customer_reservation
    WHERE id = in_trip_booking_id;

    IF (reservationExists > 0) THEN
        -- Si la reservación existe, eliminarla
        DELETE FROM customer_reservation WHERE id = in_trip_booking_id;
        
        -- Establecer mensaje de éxito
        SET response = "Reservation deleted successfully!";
    ELSE
        -- Establecer mensaje de error
        SET response = "Ups! Reservation not found.";
    END IF;
END$$

CREATE PROCEDURE `delete_user`(
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

CREATE PROCEDURE `find_booking_customer`(
	IN in_customer_id INT
)
BEGIN
	-- query
    SELECT cr.id, concat(c.name," ",c.lastName) AS customer, cr.trip AS trip_id, p.amount AS payment_amount, cr.reservationDate AS booking_date
	FROM customer_reservation cr
	JOIN payment p
	ON p.id = cr.payment
	JOIN trip t
	ON t.id = cr.trip
	JOIN customer c
	ON c.id = cr.customer
	WHERE cr.customer = in_customer_id;
END$$

CREATE PROCEDURE `find_booking_id`(
	IN in_trip_booking_id INT
)
BEGIN
	-- query
    SELECT cr.id, CONCAT(c.name," ",c.lastName) AS customer, cr.trip AS trip_id, p.amount AS payment_amount, cr.reservationDate AS booking_date
    FROM customer_reservation cr
    JOIN customer c
    ON c.id = cr.customer
    JOIN payment p
    ON p.customer = c.id
    WHERE cr.id = in_trip_booking_id;
END$$

CREATE PROCEDURE `find_customer`(
    IN in_id INT
)
BEGIN    
    -- query
    SELECT c.id, c.name, c.lastName AS last_name, c.age, c.documentType AS document_type, c.documentNumber AS document_number
    FROM customer c
    WHERE c.id = in_id;
END$$

CREATE PROCEDURE `find_trip`(
	IN in_trip_id INT
)
BEGIN
	-- query
	SELECT t.id, t.date, t.price, ff.title AS flight_fare
    FROM trip t
    JOIN flight_fare ff
    ON ff.id = t.flightFare
    WHERE t.id = in_trip_id;
END$$

CREATE PROCEDURE `find_user`(
    IN id INT
)
BEGIN
    -- query
    SELECT u.id, u.username, u.email, r.name AS role
    FROM user u
    JOIN role r
    ON r.code = u.role
    WHERE u.id = id;
END$$

CREATE PROCEDURE `get_employees_by_role`(
	IN in_role_type INT
)
BEGIN
	CASE
		WHEN in_role_type = 1 THEN
			SELECT * FROM available_pilots;
		WHEN in_role_type = 2 THEN
			SELECT * FROM available_copilots;
		WHEN in_role_type = 4 THEN
			SELECT * FROM available_attendants;
		WHEN in_role_type = 6 THEN
			SELECT * FROM available_engineers;
	END CASE;
END$$

CREATE PROCEDURE `get_users`()
BEGIN 
    -- select user_view
    SELECT * FROM user_view;
END$$

CREATE PROCEDURE `login`(
    IN in_email VARCHAR(40),
    IN in_password VARCHAR(40),
    OUT response VARCHAR(100),
    OUT out_role VARCHAR(10)
)
BEGIN
    DECLARE userExists INT;
    DECLARE credentials INT;
    DECLARE role VARCHAR(10);
    DECLARE username VARCHAR(40);
    
    -- Verify if the user exists
    SELECT COUNT(*) INTO userExists
    FROM user u
    WHERE u.email = in_email;

    IF userExists > 0 THEN
        -- Verify credentials
        SELECT COUNT(*) INTO credentials
        FROM user u 
        WHERE u.email = in_email AND u.password = in_password;

        IF credentials > 0 THEN
            -- Get role and username
            SELECT u.role, u.username INTO role, username
            FROM user u 
            WHERE u.email = in_email AND u.password = in_password;

            -- Set message
            SET response = CONCAT("\nWelcome ", UPPER(username), " - ", role);
            SET out_role = role;  -- Set the output role
        ELSE
            SET out_role = NULL;  -- Clear the output role
        END IF;
    ELSE
        SET out_role = NULL;  -- Clear the output role
    END IF;
END$$

CREATE PROCEDURE `register`(
    IN username VARCHAR(40),
    IN email VARCHAR(40),
    IN password VARCHAR(40),
    OUT response VARCHAR(100)
)
BEGIN
    DECLARE emailExists INT;

    -- verify if the email already exists
    SELECT COUNT(*) INTO emailExists
    FROM user u
    WHERE u.email = email;

    IF emailExists > 0 THEN
        -- set error if the message exists
        SET response = "Invalid email. try again";
    ELSE
        -- Create the user
        INSERT INTO user (username, email, password, role) VALUES
        (username, email, password, "CUSTOMER");

        SET response = "CUSTOMER";
    END IF;
END$$

CREATE PROCEDURE `select_flight`(
	IN in_flight_id INT,
    OUT response VARCHAR(200)
)
this_proc:BEGIN
	DECLARE flightExists INT;
    DECLARE flightInList INT;
    
    -- verify if the flight exists int available flights
    SELECT COUNT(*) INTO flightExists
    FROM available_flights af
    WHERE af.id = in_flight_id;
    
    IF (flightExists = 0) THEN
		-- set error message
        SET response = "Ups! it seems that the flight does not available. Please try again";
        LEAVE this_proc;
	END IF;
    
    -- verify if the flight exists into trip_crew
    SELECT COUNT(*) INTO flightInList
	FROM trip_crew tc
    WHERE tc.flightConnection = in_flight_id;
    
    IF (flightInList > 0) THEN
		-- set error message
        SET response = "Ups! It appears that the flight has been recorded before.";
        LEAVE this_proc;
	END IF;
    
    -- set successful message
    SET response = "Flight chosen successful!";
END$$

CREATE PROCEDURE `update_customer`(
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

CREATE PROCEDURE `update_trip`(
	IN in_trip_id INT,
    IN in_new_date DATE,
    IN in_new_price DOUBLE,
    IN in_new_flight_fare_id INT,
    OUT response VARCHAR(200)
)
BEGIN
	DECLARE tripExists INT;
    DECLARE fareExists INT;
    
	-- verify if the trip exists
    SELECT COUNT(*) INTO tripExists
    FROM trip t
    WHERE t.id = in_trip_id;
    
    IF tripExists > 0 THEN
		-- verify if the fare exists
        SELECT COUNT(*) INTO fareExists
        FROM flight_fare ff
        WHERE ff.id = in_new_flight_fare_id;
        
        IF fareExists > 0 THEN
			-- if fare exists, modify trip
            UPDATE trip SET
				date = in_new_date,
				price = in_new_price,
				flightFare = in_new_flight_fare_id
			WHERE id = in_trip_id;
            
            -- set successfull message
            SET response = "Trip updated successfully!";
		ELSE
			-- if the fare not exists set an error message
            SET response = CONCAT("Ups! the flight fare with id ",in_trip_id," not exists");
		END IF;
	ELSE
		-- if trip not exists set an error message
        SET response = CONCAT("Ups! Trip with id ",in_trip_id," not exists.");
	END IF;
END$$

CREATE PROCEDURE `update_trip_booking`(
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

CREATE PROCEDURE `update_user`(
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