CREATE TABLE Cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_usuario VARCHAR(100),
    domicilio VARCHAR(100)
);

CREATE TABLE Compañia (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    año_de_fundacion YEAR
);

CREATE TABLE Juego (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    precio DECIMAL(10, 2),
    año_de_salida YEAR,
    compañia_id INT,
    FOREIGN KEY (compañia_id) REFERENCES Compañia(id)
);
CREATE TABLE Consola (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    año_salida YEAR
);

-- Tabla intermedia para la relación N:N entre Cliente y Juego
CREATE TABLE Cliente_Juego (
    cliente_id INT,
    juego_id INT,
    PRIMARY KEY (cliente_id, juego_id),
    FOREIGN KEY (cliente_id) REFERENCES Cliente(id),
    FOREIGN KEY (juego_id) REFERENCES Juego(id)
);

-- Tabla intermedia para la relación N:N entre Juego y Consola
CREATE TABLE Juego_Consola (
    juego_id INT,
    consola_id INT,
    PRIMARY KEY (juego_id, consola_id),
    FOREIGN KEY (juego_id) REFERENCES Juego(id),
    FOREIGN KEY (consola_id) REFERENCES Consola(id)
);
