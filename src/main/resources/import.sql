/*TABLA USUARIOS - POTENTIALCLIENT*/
INSERT INTO usuarios(id, direccion, email, nombre, nif, apellidos, tphno, anyo_nacimiento) VALUES (1, 'Calle de la alegría', 'smiles@gmail.com', 'Carmela', '1111A', 'Contreras del Campo', '695924211', 1976);
INSERT INTO usuarios(id, direccion, email, nombre, nif, apellidos, tphno, anyo_nacimiento) VALUES (2, 'Plaza de la esperanza', 'cabama70@gmail.com', 'Marcos', '2222B', 'Caballero Blanco', '695924222', 1970);
INSERT INTO usuarios(id, direccion, email, nombre, nif, apellidos, tphno, anyo_nacimiento) VALUES (3, 'Paseo de la victoria', 'megustanadar@gmail.com', 'Lucía', '3333C', 'La Hoz Moreno', '695924233', 2000);
INSERT INTO usuarios(id, direccion, email, nombre, nif, apellidos, tphno, anyo_nacimiento) VALUES (4, 'Calle Tomellosera', 'quijote_y_sancho_panza@gmail.com', 'Dorita', '4444D', 'Adoración Rodrigo', '695924244', 1943);
INSERT INTO usuarios(id, direccion, email, nombre, nif, apellidos, tphno, anyo_nacimiento) VALUES (5, 'Arrubial de la palmera', 'givememusic_givemelife@gmail.com', 'Rafa', '5555E', 'Jurado Barrón', '695924255', 1989);
INSERT INTO usuarios(id, direccion, email, nombre, nif, apellidos, tphno, anyo_nacimiento) VALUES (6, 'Calle del pozo', 'lluevacafeenelcampo@gmail.com', 'Lupita', '6666F', 'Campos', '695924266', 1980);
INSERT INTO usuarios(id, direccion, email, nombre, nif, apellidos, tphno, anyo_nacimiento) VALUES (6, 'Calle de la soledad', 'massoloquelauna@gmail.com', 'Javier', '6666F', 'Rodriguez Linares', '695924277', 1996);


/*TABLA CUENTAS - ACCOUNT*/
INSERT INTO cuentas(id, saldo, fecha_alta, numero_cuenta) VALUES (1, 1500, '2000-05-18', 'ES21-1989-0100-72-2030875684');
INSERT INTO cuentas(id, saldo, fecha_alta, numero_cuenta) VALUES (2, 15000, '2000-05-18', 'ES24-1989-0100-72-2030876288');
INSERT INTO cuentas(id, saldo, fecha_alta, numero_cuenta) VALUES (3, 2500, '2000-05-18', 'ES86-1989-0100-72-2030876156');
INSERT INTO cuentas(id, saldo, fecha_alta, numero_cuenta) VALUES (4, 30000, '2000-05-18', 'ES15-1989-0100-72-2030889295');

/*TABLA CLIENT_ACCOUNT*/
INSERT INTO client_account(potentialclient_id, account_id) VALUES (3, 1);
INSERT INTO client_account(potentialclient_id, account_id) VALUES (1, 2);
INSERT INTO client_account(potentialclient_id, account_id) VALUES (2, 2);
INSERT INTO client_account(potentialclient_id, account_id) VALUES (5, 3);
INSERT INTO client_account(potentialclient_id, account_id) VALUES (4, 4);
INSERT INTO client_account(potentialclient_id, account_id) VALUES (6, 4);

/**/
