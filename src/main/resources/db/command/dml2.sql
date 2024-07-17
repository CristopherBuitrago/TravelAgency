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

-- auth

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

CREATE PROCEDURE register(
    IN username VARCHAR(40),
    IN email VARCHAR(40),
    IN password VARCHAR(40),
    OUT response VARCHAR(100)
)
BEGIN
    -- create the user
    INSERT INTO user (username, email, password, role) VALUES
    (username, email, password, "CUSTOMER");

    SET response = CONCAT("Welcome ",username," - CUSTOMER");
END$$