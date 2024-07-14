-- creation of inserts, procedures and views
-- this document is open to modifications

-- Customer Database
-- Inserciones para la tabla document_type
INSERT INTO document_type (code, name) VALUES
("CC", "Cédula de ciudadanía"),
("CE", "Cédula de extranjería"),
("PP", "Pasaporte"),
("TI", "Tarjeta de identidad"),
("RC", "Registro Civil");

-- Inserciones para la tabla customer
INSERT INTO customer (name, lastName, age, documentType, documentNumber) VALUES
("Juan", "Pérez", 30, "CC", 1234567890),
("María", "Gómez", 25, "TI", 2345678901),
("Carlos", "Rodríguez", 40, "CE", 3456789012),
("Ana", "Martínez", 22, "PP", 4567890123),
("Luis", "Fernández", 35, "RC", 5678901234);


-- Airport Database
INSERT INTO country (code, name) VALUES
("USA", "Estados Unidos"),
("CAN", "Canadá"),
("MEX", "México"),
("PER", "Perú"),
("CHL", "Chile"),
("BOL", "Bolivia"),
("ARG", "Argentina"),
("URU", "Uruguay"),
("COL", "Colombia"),
("ECU", "Ecuador"),
("PAN", "Panamá"),
("PRY", "Paraguay"),
("GUY", "Guyana"),
("SUR", "Suriname"),
("BRA", "Brasil"),
("CHN", "China"),
("JPN", "Japón"),
("KOR", "Corea del Sur"),
("TWN", "Taiwán"),
("HKG", "Hong Kong"),
("VNM", "Vietnam"),
("IDN", "Indonesia"),
("MYS", "Malasia"),
("THA", "Tailandia"),
("PHL", "Filipinas"),
("SGP", "Singapur"),
("ISR", "Israel"),
("JOR", "Jordania"),
("LBY", "Libia"),
("MAR", "Marruecos"),
("OMN", "Omán"),
("QAT", "Qatar"),
("SAU", "Arabia Saudita"),
("SYR", "Siria"),
("TUN", "Túnez"),
("ARE", "Emiratos Árabes Unidos"),
("YEM", "Yemen"),
("VEN", "Venezuela"),
("ZAF", "Sudáfrica del Sur"),
("GBR", "Reino Unido");

INSERT INTO city (code, name, country) VALUES
("WAS", "Washington D.C.", "USA"),
("OTT", "Ottawa", "CAN"),
("MEX", "Ciudad de México", "MEX"),
("LIM", "Lima", "PER"),
("SCL", "Santiago", "CHL"),
("LPB", "La Paz", "BOL"),
("BUE", "Buenos Aires", "ARG"),
("MVD", "Montevideo", "URU"),
("BOG", "Bogotá", "COL"),
("UIO", "Quito", "ECU"),
("PTY", "Panamá", "PAN"),
("ASU", "Asunción", "PRY"),
("GEO", "Georgetown", "GUY"),
("PBM", "Paramaribo", "SUR"),
("BSB", "Brasilia", "BRA"),
("PEK", "Beijing", "CHN"),
("TKY", "Tokyo", "JPN"),
("SEL", "Seúl", "KOR"),
("TPE", "Taipéi", "TWN"),
("HKG", "Hong Kong", "HKG"),
("HAN", "Hanoi", "VNM"),
("JKT", "Yakarta", "IDN"),
("KUL", "Kuala Lumpur", "MYS"),
("BKK", "Bangkok", "THA"),
("MNL", "Manila", "PHL"),
("SIN", "Singapur", "SGP"),
("JRS", "Jerusalén", "ISR"),
("AMM", "Amán", "JOR"),
("TRP", "Trípoli", "LBY"),
("RAB", "Rabat", "MAR"),
("MCT", "Mascate", "OMN"),
("DOH", "Doha", "QAT"),
("RUH", "Riad", "SAU"),
("DAM", "Damasco", "SYR"),
("TUN", "Túnez", "TUN"),
("AUH", "Abu Dhabi", "ARE"),
("SAN", "Sanaa", "YEM"),
("CCS", "Caracas", "VEN"),
("PTA", "Pretoria", "ZAF"),
("LON", "Londres", "GBR");

INSERT INTO airline (name) VALUES
("American Airlines"),
("Delta Air Lines"),
("United Airlines");

INSERT INTO airport (name, city) VALUES
("Aeropuerto Dulles", "WAS"),
("Aeropuerto Macdonald-Cartier", "OTT"),
("Aeropuerto Ciudad de México", "MEX"),
("Aeropuerto Jorge Chávez", "LIM"),
("Aeropuerto Arturo Merino Benítez", "SCL"),
("Aeropuerto El Alto", "LPB"),
("Aeropuerto Ministro Pistarini", "BUE"),
("Aeropuerto Carrasco", "MVD"),
("Aeropuerto El Dorado", "BOG"),
("Aeropuerto Mariscal Sucre", "UIO"),
("Aeropuerto Tocumen", "PTY"),
("Aeropuerto Silvio Pettirossi", "ASU"),
("Aeropuerto Cheddi Jagan", "GEO"),
("Aeropuerto Johan Adolf Pengel", "PBM"),
("Aeropuerto Brasilia", "BSB"),
("Aeropuerto Pekín-Capital", "PEK"),
("Aeropuerto Haneda", "TKY"),
("Aeropuerto Incheon", "SEL"),
("Aeropuerto Taoyuan", "TPE"),
("Aeropuerto Hong Kong", "HKG"),
("Aeropuerto Noi Bai", "HAN"),
("Aeropuerto Soekarno-Hatta", "JKT"),
("Aeropuerto Kuala Lumpur", "KUL"),
("Aeropuerto Suvarnabhumi", "BKK"),
("Aeropuerto Ninoy Aquino", "MNL"),
("Aeropuerto Singapur-Changi", "SIN"),
("Aeropuerto Ben Gurión", "JRS"),
("Aeropuerto Queen Alia", "AMM"),
("Aeropuerto Trípoli", "TRP"),
("Aeropuerto Rabat-Salé", "RAB"),
("Aeropuerto Mascate", "MCT"),
("Aeropuerto Hamad", "DOH"),
("Aeropuerto Rey Khalid", "RUH"),
("Aeropuerto Damasco", "DAM"),
("Aeropuerto Túnez-Cartago", "TUN"),
("Aeropuerto Abu Dhabi", "AUH"),
("Aeropuerto Sana'a", "SAN"),
("Aeropuerto Maiquetía", "CCS"),
("Aeropuerto OR Tambo", "PTA"),
("Aeropuerto Heathrow", "LON");

INSERT INTO airport_has_airline (airline, airport) VALUES
(1, 1), (2, 1), (3, 1),
(1, 2), (2, 2), (3, 2),
(1, 3), (2, 3), (3, 3),
(1, 4), (2, 4), (3, 4),
(1, 5), (2, 5), (3, 5),
(1, 6), (2, 6), (3, 6),
(1, 7), (2, 7), (3, 7),
(1, 8), (2, 8), (3, 8),
(1, 9), (2, 9), (3, 9),
(1, 10), (2, 10), (3, 10),
(1, 11), (2, 11), (3, 11),
(1, 12), (2, 12), (3, 12),
(1, 13), (2, 13), (3, 13),
(1, 14), (2, 14), (3, 14),
(1, 15), (2, 15), (3, 15),
(1, 16), (2, 16), (3, 16),
(1, 17), (2, 17), (3, 17),
(1, 18), (2, 18), (3, 18),
(1, 19), (2, 19), (3, 19),
(1, 20), (2, 20), (3, 20),
(1, 21), (2, 21), (3, 21),
(1, 22), (2, 22), (3, 22),
(1, 23), (2, 23), (3, 23),
(1, 24), (2, 24), (3, 24),
(1, 25), (2, 25), (3, 25),
(1, 26), (2, 26), (3, 26),
(1, 27), (2, 27), (3, 27),
(1, 28), (2, 28), (3, 28),
(1, 29), (2, 29), (3, 29),
(1, 30), (2, 30), (3, 30),
(1, 31), (2, 31), (3, 31),
(1, 32), (2, 32), (3, 32),
(1, 33), (2, 33), (3, 33),
(1, 34), (2, 34), (3, 34),
(1, 35), (2, 35), (3, 35),
(1, 36), (2, 36), (3, 36),
(1, 37), (2, 37), (3, 37),
(1, 38), (2, 38), (3, 38),
(1, 39), (2, 39), (3, 39),
(1, 40), (2, 40), (3, 40);

INSERT INTO door (code, airport) VALUES
("A1", 1), ("A2", 1), ("A3", 1),
("B1", 2), ("B2", 2), ("B3", 2),
("C1", 3), ("C2", 3), ("C3", 3),
("D1", 4), ("D2", 4), ("D3", 4),
("E1", 5), ("E2", 5), ("E3", 5),
("F1", 6), ("F2", 6), ("F3", 6),
("G1", 7), ("G2", 7), ("G3", 7),
("H1", 8), ("H2", 8), ("H3", 8),
("I1", 9), ("I2", 9), ("I3", 9),
("J1", 10), ("J2", 10), ("J3", 10),
("K1", 11), ("K2", 11), ("K3", 11),
("L1", 12), ("L2", 12), ("L3", 12),
("M1", 13), ("M2", 13), ("M3", 13),
("N1", 14), ("N2", 14), ("N3", 14),
("O1", 15), ("O2", 15), ("O3", 15),
("P1", 16), ("P2", 16), ("P3", 16),
("Q1", 17), ("Q2", 17), ("Q3", 17),
("R1", 18), ("R2", 18), ("R3", 18),
("S1", 19), ("S2", 19), ("S3", 19),
("T1", 20), ("T2", 20), ("T3", 20),
("U1", 21), ("U2", 21), ("U3", 21),
("V1", 22), ("V2", 22), ("V3", 22),
("W1", 23), ("W2", 23), ("W3", 23),
("X1", 24), ("X2", 24), ("X3", 24),
("Y1", 25), ("Y2", 25), ("Y3", 25),
("Z1", 26), ("Z2", 26), ("Z3", 26),
("AA1", 27), ("AA2", 27), ("AA3", 27),
("AB1", 28), ("AB2", 28), ("AB3", 28),
("AC1", 29), ("AC2", 29), ("AC3", 29),
("AD1", 30), ("AD2", 30), ("AD3", 30),
("AE1", 31), ("AE2", 31), ("AE3", 31),
("AF1", 32), ("AF2", 32), ("AF3", 32),
("AG1", 33), ("AG2", 33), ("AG3", 33),
("AH1", 34), ("AH2", 34), ("AH3", 34),
("AI1", 35), ("AI2", 35), ("AI3", 35),
("AJ1", 36), ("AJ2", 36), ("AJ3", 36),
("AK1", 37), ("AK2", 37), ("AK3", 37),
("AL1", 38), ("AL2", 38), ("AL3", 38),
("AM1", 39), ("AM2", 39), ("AM3", 39),
("AN1", 40), ("AN2", 40), ("AN3", 40);


-- TripBooking Database
INSERT INTO flight_fare (title, details, value) VALUES
("único", "Viaje unico sin ninguna escala (parada), tiempo aproximado del vuelo: 2h", 1500),
("doble", "Viaje con una escala (parada), tiempo aproximado del vuelo: 6h", 1000),
("mix", "Viaje con más de una escala (parada), tiempo aproximado del vuelo: 8h", 500);

INSERT INTO scale (scaleCode) VALUES
("SCL001"), ("SCL002"), ("SCL003"),
("SCL004"), ("SCL005"), ("SCL006"),
("SCL007"), ("SCL008"), ("SCL009"),
("SCL010"), ("SCL011"), ("SCL012"),
("SCL013"), ("SCL014"), ("SCL015"),
("SCL016"), ("SCL017"), ("SCL018"),
("SCL019"), ("SCL020"), ("SCL021"),
("SCL022"), ("SCL023"), ("SCL024"),
("SCL025"), ("SCL026"), ("SCL027"),
("SCL028"), ("SCL029"), ("SCL030"),
("SCL031"), ("SCL032"), ("SCL033"),
("SCL034"), ("SCL035"), ("SCL036"),
("SCL037"), ("SCL038"), ("SCL039"),
("SCL040");

INSERT INTO scale_has_airport (scale, originAirport, destinationAirport) VALUES
(1, 1, 2), (2, 2, 3), (3, 3, 4),
(4, 4, 5), (5, 5, 6), (6, 6, 7),
(7, 7, 8), (8, 8, 9), (9, 9, 10),
(10, 10, 11), (11, 11, 12), (12, 12, 13),
(13, 13, 14), (14, 14, 15), (15, 15, 16),
(16, 16, 17), (17, 17, 18), (18, 18, 19),
(19, 19, 20), (20, 20, 21), (21, 21, 22),
(22, 22, 23), (23, 23, 24), (24, 24, 25),
(25, 25, 26), (26, 26, 27), (27, 27, 28),
(28, 28, 29), (29, 29, 30), (30, 30, 31),
(31, 31, 32), (32, 32, 33), (33, 33, 34),
(34, 34, 35), (35, 35, 36), (36, 36, 37),
(37, 37, 38), (38, 38, 39), (39, 39, 40);

INSERT INTO trip (date, price, scale) VALUES
('2024-08-01', 150.00, 1), ('2024-08-02', 200.00, 2),
('2024-08-03', 250.00, 3), ('2024-08-04', 300.00, 4),
('2024-08-05', 350.00, 5), ('2024-08-06', 400.00, 6),
('2024-08-07', 450.00, 7), ('2024-08-08', 500.00, 8),
('2024-08-09', 550.00, 9), ('2024-08-10', 600.00, 10),
('2024-08-11', 650.00, 11), ('2024-08-12', 700.00, 12),
('2024-08-13', 750.00, 13), ('2024-08-14', 800.00, 14),
('2024-08-15', 850.00, 15), ('2024-08-16', 900.00, 16),
('2024-08-17', 950.00, 17), ('2024-08-18', 1000.00, 18),
('2024-08-19', 1050.00, 19), ('2024-08-20', 1100.00, 20),
('2024-08-21', 1150.00, 21), ('2024-08-22', 1200.00, 22),
('2024-08-23', 1250.00, 23), ('2024-08-24', 1300.00, 24),
('2024-08-25', 1350.00, 25), ('2024-08-26', 1400.00, 26),
('2024-08-27', 1450.00, 27), ('2024-08-28', 1500.00, 28),
('2024-08-29', 1550.00, 29), ('2024-08-30', 1600.00, 30),
('2024-08-31', 1650.00, 31), ('2024-09-01', 1700.00, 32),
('2024-09-02', 1750.00, 33), ('2024-09-03', 1800.00, 34),
('2024-09-04', 1850.00, 35), ('2024-09-05', 1900.00, 36),
('2024-09-06', 1950.00, 37), ('2024-09-07', 2000.00, 38),
('2024-09-08', 2050.00, 39), ('2024-09-09', 2100.00, 40);

INSERT INTO payment_method (code, name, description) VALUES
("TC", "Tarjeta de crédito", "Pago con tarjeta de crédito"),
("TD", "Tarjeta de débito", "Pago con tarjeta de débito"),
("EFEC", "Pago en efectivo", "Pago en efectivo"),
("TB", "Transferencia bancaria", "Pago por transferencia bancaria");

INSERT INTO payment (paymentMethod, cardNumber, amount, paymentDate, customer) VALUES
('TC', '3456', 180.00, '2024-02-05', 5),
('TC', '1234', 150.00, '2024-03-01', 1),
('TD', '5678', 200.00, '2024-04-02', 2),
('TB', '9012', 120.00, '2024-05-04', 4),
('EFEC', NULL, 100.00, '2024-06-03', 3);

INSERT INTO customer_reservation (customer, trip, payment, flightFare, reservationDate) VALUES
(1, 1, 2, 1, '2024-02-05'),
(2, 2, 3, 2, '2024-03-01'),
(3, 3, 4, 3, '2024-04-02'),
(4, 4, 5, 1, '2024-05-04'),
(5, 5, 6, 2, '2024-06-03');


-- Employee Database
INSERT INTO tripulation_role (name, description) VALUES
("Pilot", "The person responsible for the aircraft's flight operations"),
("Copilot", "The person in charge of coordinating the aircraft's flight operations with the pilot"),
("Flight Attendant", "A person responsible for assisting the pilot in performing various tasks related to the aircraft's flight"),
("Cabin Crew Member", "A person responsible for the aircraft's crew, including passengers, flight attendants, and other staff members"),
("First Officer", "The person in charge of the aircraft's maintenance, inspection, and repair operations"),
("Flight Engineer", "A person responsible for designing and implementing the aircraft's flight plans, including safety measures, flight rules, and operational procedures"),
("Mechanic", "A person responsible for repairing the aircraft's engines, electrical systems, and other critical components"),
("Avionics Specialist", "A person responsible for designing and implementing the aircraft's avionics systems, including navigation, communication, and control systems"),
("Training Instructor", "A person responsible for providing training and instruction to pilots, flight attendants, and other crew members"),
("Air Traffic Controller", "A person responsible for controlling the flow of aircraft between airports"),
("Weather Forecaster", "A person responsible for predicting weather conditions and preparing for them"),
("Aircraft Maintenance Technician", "A person responsible for inspecting and maintaining the a craft's engines, electrical systems, and other critical components");    

INSERT INTO employee (name, lastName, age, tripulationRole, admissionDate, airline, airport) VALUES
('Juan', 'López', 35, 1, '2023-01-01', 1, 1),
('María', 'González', 28, 2, '2023-02-01', 2, 2),
('Pedro', 'Martínez', 40, 3, '2023-03-01', 3, 3),
('Laura', 'Sánchez', 30, 4, '2023-04-01', 1, 4),
('Carlos', 'Rodríguez', 32, 5, '2023-05-01', 2, 5),
('Ana', 'Pérez', 25, 6, '2023-06-01', 3, 6),
('Luis', 'Fernández', 38, 7, '2023-07-01', 1, 7),
('Elena', 'Gómez', 27, 8, '2023-08-01', 2, 8),
('Andrés', 'Díaz', 33, 9, '2023-09-01', 3, 9),
('Gabriela', 'Hernández', 29, 10, '2023-10-01', 1, 10),
('Santiago', 'Vargas', 36, 11, '2023-11-01', 2, 11),
('Valeria', 'Torres', 31, 12, '2023-12-01', 3, 12);

INSERT INTO employee_revision (employee, description, revisionDate) VALUES
(1, 'Revision inicial del empleado Juan López.', '2024-01-01'),
(2, 'Revisión de desempeño para María González.', '2024-02-01'),
(3, 'Revisión anual para Pedro Martínez.', '2024-03-01'),
(4, 'Evaluación trimestral para Laura Sánchez.', '2024-04-01'),
(5, 'Seguimiento de objetivos para Carlos Rodríguez.', '2024-05-01'),
(6, 'Revisión semestral para Ana Pérez.', '2024-06-01'),
(7, 'Revisión de competencias para Luis Fernández.', '2024-07-01'),
(8, 'Evaluación de habilidades técnicas para Elena Gómez.', '2024-08-01'),
(9, 'Revisión de rendimiento para Andrés Díaz.', '2024-09-01'),
(10, 'Revisión de metas alcanzadas para Gabriela Hernández.', '2024-10-01'),
(11, 'Revisión de desempeño para Santiago Vargas.', '2024-11-01'),
(12, 'Seguimiento de objetivos trimestrales para Valeria Torres.', '2024-12-01');


-- Plane Database
INSERT INTO plane_status (name, description) VALUES
('Active', 'The plane is currently in active service.'),
('Maintenance', 'The plane is undergoing maintenance and is temporarily out of service.'),
('Inactive', 'The plane is currently inactive and not in service.'),
('Retired', 'The plane has been retired from service and is no longer operational.');

INSERT INTO plane_manufacture (name) VALUES
('Boeing'),
('Airbus'),
('Embraer'),
('Bombardier'),
('Cessna'),
('Gulfstream'),
('Dassault Aviation'),
('Lockheed Martin'),
('Sikorsky Aircraft'),
('Antonov'),
('Beechcraft'),
('Pilatus Aircraft'),
('Fokker'),
('Saab Aircraft'),
('Aerospatiale'),
('Hawker'),
('McDonnell Douglas'),
('Piper Aircraft'),
('Raytheon Aircraft'),
('IAI (Israel Aerospace Industries)'),
('Northrop Grumman'),
('Textron Aviation');

INSERT INTO plane_model (name, planeManufacture) VALUES
-- Modelos de aviones para Boeing
('737', 1),
('747', 1),
('767', 1),
('777', 1),
('787', 1),
-- Modelos de aviones para Airbus
('A220', 2),
('A320', 2),
('A330', 2),
('A350', 2),
('A380', 2),
-- Modelos de aviones para Embraer
('ERJ-145', 3),
('E-Jet E2', 3),
-- Modelos de aviones para Bombardier
('CRJ Series', 4),
('Global Series', 4),
-- Modelos de aviones para Cessna
('Citation', 5),
('Caravan', 5);


INSERT INTO plane (plate, chairs, status, model, fabricationDate, airline) VALUES
-- Aviones de Boeing (airline = 1)
('AA001', 150, 1, 1, '2010-05-15', 1),
('AA002', 300, 1, 2, '2005-09-20', 1),
('AA003', 250, 2, 3, '2012-12-10', 1),
('AA004', 350, 1, 4, '2015-07-01', 1),
('AA005', 400, 3, 5, '2008-03-12', 1),
-- Aviones de Airbus (airline = 2)
('DL001', 180, 1, 6, '2011-08-25', 2),
('DL002', 220, 2, 7, '2007-11-30', 2),
('DL003', 280, 1, 8, '2014-04-05', 2),
('DL004', 350, 1, 9, '2016-09-15', 2),
('DL005', 500, 1, 10, '2013-02-18', 2),
-- Aviones de Embraer (airline = 3)
('UA001', 80, 1, 11, '2009-06-10', 3),
('UA002', 100, 1, 12, '2017-11-22', 3),
('UA003', 120, 1, 11, '2013-08-14', 3),
('UA004', 90, 1, 12, '2015-05-30', 3),
('UA005', 110, 1, 11, '2012-04-03', 3);


INSERT INTO plane_revision (revisionDate, plane, description, employee) VALUES
-- Revisiones para aviones de Boeing (airline = 1)
('2024-01-10', 1, 'Routine maintenance check.', 1),
('2024-03-20', 2, 'Engine overhaul.', 4),
('2024-05-15', 3, 'Electrical system inspection.', 7),
('2024-07-05', 4, 'Avionics upgrade.', 10),
('2024-09-28', 5, 'Interior refurbishment.', 1),
-- Revisiones para aviones de Airbus (airline = 2)
('2024-02-12', 6, 'Routine maintenance check.', 2),
('2024-04-25', 7, 'Engine overhaul.', 5),
('2024-06-18', 8, 'Electrical system inspection.', 8),
('2024-08-10', 9, 'Avionics upgrade.', 11),
('2024-10-30', 10, 'Interior refurbishment.', 2),
-- Revisiones para aviones de Embraer (airline = 3)
('2024-03-08', 11, 'Routine maintenance check.', 3),
('2024-05-22', 12, 'Engine overhaul.', 6),
('2024-07-14', 13, 'Electrical system inspection.', 9),
('2024-09-05', 14, 'Avionics upgrade.', 12),
('2024-11-19', 15, 'Interior refurbishment.', 3);



-- Flight Connection Database
INSERT INTO passenger (name, lastName, age, documentType, documentNumber) VALUES
('Juan', 'Pérez', 30, 'CC', 1234567890),
('María', 'Gómez', 25, 'TI', 2345678901),
('Carlos', 'Rodríguez', 40, 'CE', 3456789012),
('Ana', 'Martínez', 22, 'PP', 4567890123),
('Luis', 'Fernández', 35, 'RC', 5678901234);

INSERT INTO flight_connection (connectionNumber, trip, plane, departureTime, arrivalTime, availableChairs) VALUES
(1, 1, 1, '08:00:00', '10:00:00', 150),
(2, 2, 2, '09:00:00', '11:30:00', 180),
(3, 3, 3, '10:00:00', '12:45:00', 80),
(4, 4, 4, '11:00:00', '13:30:00', 250),
(5, 5, 5, '12:00:00', '14:15:00', 400),
(6, 6, 6, '13:00:00', '15:30:00', 300),
(7, 7, 7, '14:00:00', '16:45:00', 350),
(8, 8, 8, '15:00:00', '17:00:00', 500),
(9, 9, 9, '16:00:00', '18:30:00', 120),
(10, 10, 10, '17:00:00', '19:15:00', 350),
(11, 11, 11, '18:00:00', '20:00:00', 110),
(12, 12, 12, '19:00:00', '21:30:00', 250),
(13, 13, 13, '20:00:00', '22:15:00', 400),
(14, 14, 14, '21:00:00', '23:00:00', 300),
(15, 15, 15, '22:00:00', '00:30:00', 180),
(16, 16, 16, '23:00:00', '01:15:00', 150),
(17, 17, 17, '00:00:00', '02:30:00', 90),
(18, 18, 18, '01:00:00', '03:45:00', 350),
(19, 19, 19, '02:00:00', '04:15:00', 280),
(20, 20, 20, '03:00:00', '05:30:00', 220),
(21, 21, 21, '04:00:00', '06:00:00', 150),
(22, 22, 22, '05:00:00', '07:30:00', 180),
(23, 23, 23, '06:00:00', '08:15:00', 250),
(24, 24, 24, '07:00:00', '09:00:00', 300),
(25, 25, 25, '08:00:00', '10:30:00', 400),
(26, 26, 26, '09:00:00', '11:15:00', 350),
(27, 27, 27, '10:00:00', '12:45:00', 120),
(28, 28, 28, '11:00:00', '13:30:00', 350),
(29, 29, 29, '12:00:00', '14:15:00', 110),
(30, 30, 30, '13:00:00', '15:00:00', 250),
(31, 31, 31, '14:00:00', '16:30:00', 400),
(32, 32, 32, '15:00:00', '17:15:00', 300),
(33, 33, 33, '16:00:00', '18:30:00', 180),
(34, 34, 34, '17:00:00', '19:45:00', 150),
(35, 35, 35, '18:00:00', '20:30:00', 90),
(36, 36, 36, '19:00:00', '21:15:00', 350),
(37, 37, 37, '20:00:00', '22:00:00', 280),
(38, 38, 38, '21:00:00', '23:30:00', 220),
(39, 39, 39, '22:00:00', '00:15:00', 150),
(40, 40, 40, '23:00:00', '01:30:00', 180);

INSERT INTO passenger_has_seat (passenger, seatNumber, flightConnection) VALUES
-- Pasajeros en vuelos
(1, 1, 1), (2, 2, 2), (3, 3, 3),
(4, 4, 4), (5, 5, 5), (1, 6, 6),
(2, 7, 7), (3, 8, 8), (4, 9, 9),
(5, 10, 10), (1, 11, 11), (2, 12, 12),
(3, 13, 13), (4, 14, 14), (5, 15, 15),
(1, 16, 16), (2, 17, 17), (3, 18, 18),
(4, 19, 19), (5, 20, 20), (1, 21, 21),
(2, 22, 22), (3, 23, 23), (4, 24, 24),
(5, 25, 25), (1, 26, 26), (2, 27, 27),
(3, 28, 28), (4, 29, 29), (5, 30, 30),
(1, 31, 31), (2, 32, 32), (3, 33, 33),
(4, 34, 34), (5, 35, 35), (1, 36, 36),
(2, 37, 37), (3, 38, 38), (4, 39, 39),
(5, 40, 40);

INSERT INTO trip_crew (employee, flightConnection) VALUES
(1, 1), (2, 2), (3, 3),
(4, 4), (5, 5), (6, 6),
(7, 7), (8, 8), (9, 9),
(10, 10), (11, 11), (12, 12),
(1, 13), (2, 14), (3, 15),
(4, 16), (5, 17), (6, 18),
(7, 19), (8, 20), (9, 21),
(10, 22), (11, 23), (12, 24),
(1, 25), (2, 26), (3, 27),
(4, 28), (5, 29), (6, 30),
(7, 31), (8, 32), (9, 33),
(10, 34), (11, 35), (12, 36),
(1, 37), (2, 38), (3, 39),
(4, 40);


-- User Database
-- TODO: user details (role, permission, role_has_permission), plane details (plane_status, plane_manufacture, plane_model), 