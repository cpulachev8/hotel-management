DROP TABLE IF EXISTS user;
CREATE TABLE user (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR (20),
    password VARCHAR (255),
    role varchar (50)
);

DROP TABLE IF EXISTS room;
CREATE TABLE room (
    room_id INT AUTO_INCREMENT PRIMARY KEY,
    room_number VARCHAR (80),
    room_type varchar (80),
    status varchar (80),
    number_occupants int
);

DROP TABLE IF EXISTS room_states_relationship;
CREATE TABLE room_states_relationship (
    room_states_relationship_id INT AUTO_INCREMENT PRIMARY KEY,
    status_current VARCHAR (80),
    status_to_change varchar (80)
);

INSERT INTO room (room_number, room_type, status, number_occupants) VALUES ('101', 'Estándar', 'Libre', 3);
INSERT INTO room (room_number, room_type, status, number_occupants) VALUES ('102', 'Normal', 'Ocupada', 4);
INSERT INTO room (room_number, room_type, status, number_occupants) VALUES ('102', 'Suite', 'Limpieza', 6);

INSERT INTO room_states_relationship (status_current, status_to_change) VALUES ('Libre', 'Ocupada');
INSERT INTO room_states_relationship (status_current, status_to_change) VALUES ('Libre', 'Mantenimiento');
INSERT INTO room_states_relationship (status_current, status_to_change) VALUES ('Ocupada', 'Mantenimiento');
INSERT INTO room_states_relationship (status_current, status_to_change) VALUES ('Ocupada', 'Limpieza');
INSERT INTO room_states_relationship (status_current, status_to_change) VALUES ('Limpieza', 'Libre');
INSERT INTO room_states_relationship (status_current, status_to_change) VALUES ('Limpieza', 'Mantenimiento');
INSERT INTO room_states_relationship (status_current, status_to_change) VALUES ('Mantenimiento', 'Libre');
INSERT INTO room_states_relationship (status_current, status_to_change) VALUES ('Mantenimiento', 'Limpieza');

INSERT INTO user (username, password, role) VALUES ('user.gerente', '$2y$12$y1f8qXd3.BuxArXLNvLy1eow9tPZQuyVPXhwskd8bvp3Qu5VyArGu', 'ROLE_GERENTE');--pass123
INSERT INTO user (username, password, role) VALUES ('user.recepcionista', '$2y$12$y1f8qXd3.BuxArXLNvLy1eow9tPZQuyVPXhwskd8bvp3Qu5VyArGu', 'ROLE_RECEPCIONISTA');--pass123
INSERT INTO user (username, password, role) VALUES ('user.cliente', '$2y$12$y1f8qXd3.BuxArXLNvLy1eow9tPZQuyVPXhwskd8bvp3Qu5VyArGu', 'ROLE_CLIENTE');--pass123