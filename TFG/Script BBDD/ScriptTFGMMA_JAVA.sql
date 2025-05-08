CREATE  DATABASE if not exists nextgenmma;
use nextgenmma;
--
CREATE TABLE if not exists gimnasio (
    id_gimnasio INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    contraseña VARCHAR(100),
    NIF VARCHAR(100),
    direccion VARCHAR(255),
    web VARCHAR(255),
    ciudad VARCHAR(100)
);

--
CREATE TABLE if not exists federacion (
    id_federacion INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    arte_marcial VARCHAR(100),
    fecha_fundacion date,
    numero_asociacion INT 
);

--
CREATE TABLE if not exists liga (
    id_liga INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    ciudad VARCHAR(100),
    participantes INT,
    id_federacion INT,
    FOREIGN KEY (id_federacion) REFERENCES federacion(id_federacion)
        ON DELETE SET NULL ON UPDATE CASCADE
);

--
CREATE TABLE if not exists entrenador (
    id_entrenador INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100),
    dni VARCHAR(100),
    experiencia INT,
    numero_colegiado INT,
    id_gimnasio INT,
    FOREIGN KEY (id_gimnasio) REFERENCES gimnasio(id_gimnasio)
        ON DELETE SET NULL ON UPDATE CASCADE
);

--
CREATE TABLE if not exists peleador (
    id_peleador INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100),
    dni VARCHAR(9),
    peso INT,
    victorias INT,
    apodo VARCHAR(100),
    fecha_nacimiento date,
    id_gimnasio INT,
    id_entrenador INT,
    id_liga INT,
    FOREIGN KEY (id_gimnasio) REFERENCES gimnasio(id_gimnasio)
        ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (id_entrenador) REFERENCES entrenador(id_entrenador)
        ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (id_liga) REFERENCES liga(id_liga)
        ON DELETE SET NULL ON UPDATE CASCADE
);

--
CREATE TABLE if not exists post (
    id_post INT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(100),
    mensaje VARCHAR(100),
    foto BLOB
);
