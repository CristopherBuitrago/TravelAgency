DELIMITER $$

-- Procedure to create a customer
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

    -- Verify if the document type exists
    SELECT COUNT(*) INTO docExists
    FROM document_type dt
    WHERE dt.code = in_docType;

    IF docExists > 0 THEN
        -- If docExists create customer
        INSERT INTO customer (name, lastName, age, documentType, documentNumber) VALUES
        (in_name, in_last_name, in_age, in_docType, in_docNumber);
        -- Set custom message
        SET out_message = "Customer created successfully!";
    ELSE
        -- Set a custom message if doc not exists
        SET out_message = "Ups! The document type does not exist. Please try again";
    END IF;
END$$

-- Procedure to find a customer by ID
CREATE PROCEDURE find_customer (
    IN in_id INT
)
BEGIN    
    -- Query
    SELECT c.id, c.name, c.lastName AS last_name, c.age, c.documentType AS document_type, c.documentNumber AS document_number
    FROM customer c
    WHERE c.id = in_id;
END$$

-- Procedure to update a customer
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

    -- Verify if user exists
    SELECT COUNT(*) INTO customerExists
    FROM customer
    WHERE id = in_id;

    IF customerExists > 0 THEN
        -- Verify if the docType exists in the database
        SELECT COUNT(*) INTO docExists
        FROM document_type
        WHERE code = in_docType;

        IF docExists > 0 THEN
            -- Update customer if doc exists
            UPDATE customer SET 
                name = in_name,
                lastName = in_last_name,
                age = in_age,
                documentType = in_docType,
                documentNumber = in_docNumber
            WHERE id = in_id;
            SET out_message = 'Customer updated successfully!';
        ELSE
            -- If doc does not exist assign an error message
            SET out_message = 'Ups! Document type does not exist.';
        END IF;
    ELSE
        -- If the customer does not exist set a message
        SET out_message = 'Ups! The customer does not exist, try again';
    END IF;
END$$

-- Procedure to create a user
CREATE PROCEDURE create_user(
    IN username VARCHAR(40),
    IN email VARCHAR(40),
    IN password VARCHAR(40),
    IN role VARCHAR(10),
    OUT response VARCHAR(30)
)
BEGIN
    DECLARE roleExists INT;
    DECLARE emailExists INT;

    -- Check if role exists
    SELECT COUNT(*) INTO roleExists
    FROM role
    WHERE code = role;

    -- Check if email exists
    SELECT COUNT(*) INTO emailExists
    FROM user u
    WHERE u.email = email;

    IF emailExists > 0 THEN
        SET response = "Email not valid. Try again";
    ELSE 
        IF roleExists > 0 THEN
            -- Create user
            INSERT INTO user(username, email, password, role) VALUES (username, email, password, role);
            SET response = 'User created successfully';
        ELSE
            -- If role does not exist throw error
            SET response = 'Role does not exist';
        END IF;
    END IF;
END$$

-- Procedure to delete a user
CREATE PROCEDURE delete_user(
    IN id INT,
    OUT response VARCHAR(40)
)
BEGIN
    -- Declare user exists
    DECLARE userExists INT;

    -- Verify user exists
    SELECT COUNT(*) INTO userExists
    FROM user u
    WHERE u.id = id;

    IF userExists > 0 THEN
        -- Delete user
        DELETE FROM user u WHERE u.id = id;
        -- Set response
        SET response = "User deleted successfully!";
    ELSE
        -- If user does not exist set a message
        SET response = "Error: The user does not exist.";
    END IF;
END$$

-- Procedure to update a user
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
    DECLARE emailExists INT;

    -- Verify if user exists
    SELECT COUNT(*) INTO userExists
    FROM user
    WHERE id = id;

    -- Verify if the email exists
    SELECT COUNT(*) INTO emailExists
    FROM user
    WHERE email = newEmail;

    IF userExists > 0 THEN
        -- Verify if new role exists in the database
        SELECT COUNT(*) INTO roleExists
        FROM role
        WHERE code = newRole;

        IF roleExists > 0 THEN
            -- Verify if email exists
            IF emailExists > 0 THEN
                -- Throw error
                SET response = "Email invalid.";
            ELSE 
                -- Update user if the email does not exist
                UPDATE user SET 
                    username = newUsername,
                    email = newEmail,
                    password = newPassword,
                    role = newRole
                WHERE id = id;
                SET response = 'User updated successfully';
            END IF;
        ELSE
            -- If the role does not exist, assign an error message
            SET response = 'Role does not exist';
        END IF;
    ELSE
        -- If the user does not exist, assign an error message
        SET response = 'User does not exist';
    END IF;
END$$

-- Procedure to find a user by ID
CREATE PROCEDURE find_user (
    IN id INT
)
BEGIN
    -- Query
    SELECT u.id, u.username, u.email, r.name AS role
    FROM user u
    JOIN role r
    ON r.code = u.role
    WHERE u.id = id;
END $$

-- Create view user_view
CREATE VIEW user_view AS
SELECT u.id, u.username, u.email, r.name AS role
FROM user u
JOIN role r
ON r.code = u.role;

-- Procedure to get all users
CREATE PROCEDURE get_users()
BEGIN 
    -- Select user_view
    SELECT * FROM user_view;
END$$

-- Procedure for login
CREATE PROCEDURE login (
    IN in_email VARCHAR(40),
    IN in_password VARCHAR(40),
    OUT response VARCHAR(100)
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
            SET response = CONCAT("Welcome ", username, " - ", role);
        ELSE
            -- Set error message
            SET response = "Incorrect password or email";
        END IF;
    ELSE
        SET response = "Incorrect password or email.";
    END IF;
END$$

-- Procedure for register
CREATE PROCEDURE register(
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