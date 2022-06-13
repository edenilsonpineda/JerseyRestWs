

CREATE TABLE cursosUDB.ALUMNOS (
  `nombreAlumno` varchar(100) COLLATE utf8mb4_spanish2_ci NOT NULL,
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `fechaNacimiento` date NOT NULL,
  `numeroTelefono` varchar(15) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `email` varchar(50) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `curso` int unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ALUMNOS_FK` (`curso`),
  CONSTRAINT `ALUMNOS_FK` FOREIGN KEY (`curso`) REFERENCES `CURSOS` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

INSERT INTO cursosUDB.ALUMNOS (`nombreAlumno`, `id`, `fechaNacimiento`, `numeroTelefono`, `email`, `curso`) VALUES
('Angelica Cruz',	4,	'1997-03-05',	NULL,	'angelica.cruz@alumno.udb.edu.sv',	10),
('Michelle Duran Duran',	5,	'1999-11-15',	'64138840',	'michelle.duran@alumno.udb.edu.sv',	10),
('Oscar Cortez',	6,	'1987-04-25',	'+50365550055',	'oscar.cortez87@hotmail.com',	3),
('Pamela Ochoa',	7,	'2022-05-01',	'',	'',	10);

DROP TABLE IF EXISTS cursosUDB.CURSOS;
CREATE TABLE  cursosUDB.CURSOS (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `nombreCurso` varchar(80) COLLATE utf8mb4_spanish2_ci NOT NULL,
  `nombreProfesor` varchar(80) COLLATE utf8mb4_spanish2_ci NOT NULL,
  `numeroTelefono` varchar(15) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `email` varchar(50) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

INSERT INTO  cursosUDB.CURSOS (`id`, `nombreCurso`, `nombreProfesor`, `numeroTelefono`, `email`) VALUES
(2,	'Desarrollo de Aplicaciones con NodeJS',	'Juan Tocumen Rivera',	'+50533408890',	'juan.tocumen@docente.udb.edu.sv'),
(3,	'Principios de SCRUM',	'Enrique Alfaro',	'+50370110053',	'enrique.alfaro@docente.udb.edu.sv'),
(10, 'Desarrollo de Aplicaciones Empresariales con JAVA EE',	'Inga. Fátima Beltran',	'',	'fatima.beltran@coach.telus.com.sv'),
(11, 'Introducción a Postman',	'Ing. Diego Alvarado Rivas','',	'diego.a.rivas@hotmail.com');

-- 2022-05-19 04:20:02