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

INSERT INTO payment_method (code, name, description) VALUES
("TC", "Tarjeta de crédito", "Pago con tarjeta de crédito"),
("TD", "Tarjeta de débito", "Pago con tarjeta de débito"),
("EFEC", "Pago en efectivo", "Pago en efectivo");

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


-- User Database
-- Inserciones para la tabla permission
INSERT INTO permission (name, description) VALUES
('register_airplane', 'Permite registrar un nuevo avión en el sistema con todos sus detalles'),
('assign_employee_to_trip', 'Permite asignar empleados a un trayecto específico'),
('view_airplane_info', 'Permite consultar la información de un avión registrado'),
('view_trip_info', 'Permite consultar la información de un trayecto específico'),
('register_airport', 'Permite registrar un nuevo aeropuerto en el sistema'),
('view_airport_info', 'Permite consultar la información de un aeropuerto registrado'),
('update_airplane_info', 'Permite actualizar la información de un avión registrado'),
('delete_airplane', 'Permite eliminar un avión del sistema'),
('assign_aircraft_to_trip', 'Permite asignar una aeronave a un trayecto específico'),
('update_trip_info', 'Permite actualizar la información de un trayecto registrado'),
('delete_trip', 'Permite eliminar un trayecto del sistema'),
('update_airport_info', 'Permite actualizar la información de un aeropuerto registrado'),
('delete_airport', 'Permite eliminar un aeropuerto del sistema'),
('view_specific_flight_info', 'Permite consultar la información de un vuelo específico'),
('view_trip_crew_assignment', 'Permite consultar la asignación de tripulación para un trayecto específico'),
('view_trip_scales', 'Permite consultar las escalas asociadas a un trayecto específico'),
('update_scale_info', 'Permite actualizar la información de una escala registrada'),
('delete_scale', 'Permite eliminar una escala del sistema'),
('register_flight_rate', 'Permite registrar una nueva tarifa de vuelo en el sistema'),
('update_flight_rate_info', 'Permite actualizar la información de una tarifa de vuelo registrada'),
('delete_flight_rate', 'Permite eliminar una tarifa de vuelo del sistema'),
('view_flight_rate_info', 'Permite consultar la información de una tarifa de vuelo específica'),
('register_document_type', 'Permite registrar un nuevo tipo de documento en el sistema'),
('update_document_type_info', 'Permite actualizar la información de un tipo de documento registrado'),
('delete_document_type', 'Permite eliminar un tipo de documento del sistema'),
('view_document_type_info', 'Permite consultar la información de un tipo de documento específico'),
('create_booking', 'Permite crear una nueva reserva de vuelo para un cliente'),
('view_customer_info', 'Permite consultar la información de un cliente registrado'),
('view_booking_info', 'Permite consultar las reservas de vuelo de un cliente o de un trayecto específico'),
('register_customer', 'Permite registrar un nuevo cliente en el sistema'),
('update_customer_info', 'Permite actualizar la información de un cliente registrado'),
('delete_booking', 'Permite eliminar una reserva de vuelo existente'),
('view_specific_flight_info_agent', 'Permite consultar la información de un vuelo específico (para agente)'),
('view_trip_crew_assignment_agent', 'Permite consultar la asignación de tripulación para un trayecto específico (para agente)'),
('view_trip_scales_agent', 'Permite consultar las escalas asociadas a un trayecto específico (para agente)'),
('view_flight_rate_info_agent', 'Permite consultar la información de una tarifa de vuelo específica (para agente)'),
('view_document_type_info_agent', 'Permite consultar la información de un tipo de documento específico (para agente)'),
('register_maintenance_review', 'Permite registrar una revisión de mantenimiento para un avión'),
('view_airplane_maintenance_history', 'Permite consultar el historial de revisiones de mantenimiento de un avión específico'),
('update_maintenance_review', 'Permite actualizar la información de una revisión de mantenimiento'),
('delete_maintenance_review', 'Permite eliminar una revisión de mantenimiento del sistema'),
('search_available_flights', 'Permite a los clientes buscar vuelos disponibles según sus criterios de búsqueda'),
('add_passenger_data', 'Permite a los clientes añadir los datos de los pasajeros que viajarán en el vuelo seleccionado'),
('select_seats', 'Permite a los clientes seleccionar los asientos para los pasajeros en el vuelo seleccionado'),
('confirm_payment', 'Permite a los clientes realizar el pago para confirmar la reserva del vuelo'),
('view_booking_details', 'Permite a los clientes consultar los detalles de sus reservas de vuelo'),
('update_booking_details', 'Permite a los clientes modificar los detalles de una reserva de vuelo existente');

-- Inserciones para la tabla role
INSERT INTO role (code, name, description) VALUES
('ADMIN', 'Administrador del Sistema', 'Acceso completo a todas las funciones administrativas'),
('SA', 'Agente de Ventas', 'Acceso a funciones relacionadas con la venta de vuelos'),
('MTECH', 'Técnico de Mantenimiento', 'Acceso a funciones relacionadas con el mantenimiento de aviones'),
('CUSTOMER', 'Cliente', 'Acceso a funciones relacionadas con la reserva y gestión de vuelos');


-- Inserciones para la tabla role_has_permission
INSERT INTO role_has_permission (role, permission) VALUES
-- Permisos para el Administrador del Sistema (admin)
('ADMIN', 1), ('ADMIN', 2), ('ADMIN', 3), ('ADMIN', 4), ('ADMIN', 5), ('ADMIN', 6), 
('ADMIN', 7), ('ADMIN', 8), ('ADMIN', 9), ('ADMIN', 10), ('ADMIN', 11), ('ADMIN', 12), 
('ADMIN', 13), ('ADMIN', 14), ('ADMIN', 15), ('ADMIN', 16), ('ADMIN', 17), ('ADMIN', 18), 
('ADMIN', 19), ('ADMIN', 20), ('ADMIN', 21), ('ADMIN', 22), ('ADMIN', 23), ('ADMIN', 24), 
('ADMIN', 25);

-- Permisos para el Agente de Ventas (SA)
INSERT INTO role_has_permission (role, permission) VALUES
('SA', 26), ('SA', 27), ('SA', 28), ('SA', 29), 
('SA', 30), ('SA', 31), ('SA', 32), ('SA', 33), 
('SA', 34), ('SA', 35), ('SA', 36), ('SA', 37), 
('SA', 38);

-- Permisos para el Técnico de Mantenimiento (MTECH)
INSERT INTO role_has_permission (role, permission) VALUES
('MTECH', 39), ('MTECH', 40), ('MTECH', 41), 
('MTECH', 42);

-- Permisos para el Cliente (customer)
INSERT INTO role_has_permission (role, permission) VALUES
('CUSTOMER', 43), ('CUSTOMER', 44), ('CUSTOMER', 45), ('CUSTOMER', 46), 
('CUSTOMER', 47), ('CUSTOMER', 48);

INSERT INTO user (username, email, password, role) VALUES
("chulo", "chulosbuitrago@gmail.com", "chulo123", "ADMIN");