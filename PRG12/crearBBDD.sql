-- ============================================================
-- crearBBDD.sql
-- Creació de l'esquema de la base de dades del fòrum
-- Relació 1:M entre publicacio i comentari
-- ============================================================

CREATE DATABASE IF NOT EXISTS forum
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE forum;

-- ------------------------------------------------------------
-- Taula: publicacio
-- Cada fila representa una publicació al fòrum.
-- ------------------------------------------------------------
CREATE TABLE IF NOT EXISTS publicacio (
    id                INT          AUTO_INCREMENT PRIMARY KEY,
    nick_que_publica  VARCHAR(50)  NOT NULL,
    text              TEXT         NOT NULL,
    data_publicacio   DATETIME     NOT NULL,
    paraules_clau     VARCHAR(200)
);

-- ------------------------------------------------------------
-- Taula: comentari
-- Cada fila és un comentari associat a una publicació (1:M).
-- La clau forana publicacio_id garanteix la integritat referencial:
-- no es pot inserir un comentari per a una publicació inexistent,
-- ni eliminar una publicació que tingui comentaris associats.
-- ------------------------------------------------------------
CREATE TABLE IF NOT EXISTS comentari (
    id              INT   AUTO_INCREMENT PRIMARY KEY,
    nick_comenta    VARCHAR(50)  NOT NULL,
    text            TEXT         NOT NULL,
    valoracio       INT          NOT NULL CHECK (valoracio BETWEEN 1 AND 5),
    data_comentari  DATETIME     NOT NULL,
    publicacio_id   INT          NOT NULL,
    FOREIGN KEY (publicacio_id) REFERENCES publicacio(id)
);
