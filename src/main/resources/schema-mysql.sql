
DROP TABLE IF EXISTS JOURNAL_ENTITY_EVENT;
CREATE TABLE JOURNAL_ENTITY_EVENT
(
    ID BIGINT NOT NULL,
    CLASS VARCHAR(255) NOT NULL,
    PRIMARY KEY (ID)
);

DROP TABLE IF EXISTS JOURNAL_CUSTOM_EVENT;
CREATE TABLE JOURNAL_CUSTOM_EVENT
(
    ID BIGINT NOT NULL,
    COMMENT VARCHAR(255) NOT NULL,
    PRIMARY KEY (ID)
);

DROP TABLE IF EXISTS JOURNAL_EVENT_DATA;
CREATE TABLE JOURNAL_EVENT_DATA
(
    ID BIGINT AUTO_INCREMENT NOT NULL,
    EVENT BIGINT NOT NULL,
    PROPERTY VARCHAR(255) NOT NULL,
    VALUE VARCHAR(255) NOT NULL,
    PRIMARY KEY (ID)
);

DROP TABLE IF EXISTS JOURNAL_EVENT;
CREATE TABLE JOURNAL_EVENT
(
    ID BIGINT AUTO_INCREMENT NOT NULL,
    PRINCIPAL VARCHAR(50) NOT NULL,
    DATE TIMESTAMP,
    TYPE VARCHAR(255),
    PRIMARY KEY (ID)
);

DROP TABLE IF EXISTS CFG_DIAMANTE_FACTOR;
CREATE TABLE CFG_DIAMANTE_FACTOR
(
    ID BIGINT AUTO_INCREMENT NOT NULL,
    FACTOR_MAXIMO DECIMAL(10, 3) NOT NULL,
    FACTOR_MEDIO DECIMAL(10, 3) NOT NULL,
    FACTOR_MINIMO DECIMAL(10, 3) NOT NULL,
    FECHA_CARGA TIMESTAMP NOT NULL,
    FECHA_LISTADO DATE NOT NULL,
    PRIMARY KEY (ID)
);

DROP TABLE IF EXISTS CFG_FACTOR_ALHAJA;
CREATE TABLE CFG_FACTOR_ALHAJA
(
    ID BIGINT AUTO_INCREMENT NOT NULL,
    METAL VARCHAR(20) NOT NULL,
    CALIDAD VARCHAR(20),
    RANGO VARCHAR(20) NOT NULL,
    FACTOR DECIMAL(8, 2) NOT NULL,
    DESPLAZAMIENTO DECIMAL(8, 2) NOT NULL,
    LIMITE_INFERIOR DECIMAL(8, 2) NOT NULL,
    LIMITE_SUPERIOR DECIMAL(8, 2) NOT NULL,
    ULTIMA_ACTUALIZACION TIMESTAMP NOT NULL,
    LISTADO BIGINT,
    PRIMARY KEY (ID)
);

DROP TABLE IF EXISTS CFG_ALHAJA_LISTADO_FACTOR;
CREATE TABLE CFG_ALHAJA_LISTADO_FACTOR
(
    ID BIGINT AUTO_INCREMENT NOT NULL,
    ULTIMA_ACTUALIZACION TIMESTAMP NOT NULL,
    FECHA_LISTADO DATE NOT NULL,
    PRIMARY KEY (ID)
);

DROP TABLE IF EXISTS HIST_CFG_FACTOR_ALHAJA;
CREATE TABLE HIST_CFG_FACTOR_ALHAJA
(
    ID BIGINT AUTO_INCREMENT NOT NULL,
    METAL VARCHAR(20) NOT NULL,
    CALIDAD VARCHAR(20),
    RANGO VARCHAR(20) NOT NULL,
    FACTOR DECIMAL(8, 2) NOT NULL,
    DESPLAZAMIENTO DECIMAL(8, 2) NOT NULL,
    LIMITE_INFERIOR DECIMAL(8, 2) NOT NULL,
    LIMITE_SUPERIOR DECIMAL(8, 2) NOT NULL,
    ULTIMA_ACTUALIZACION TIMESTAMP NOT NULL,
    LISTADO BIGINT,
    PRIMARY KEY (ID)
);

DROP TABLE IF EXISTS HIST_CFG_ALHAJA_LISTADO_FACTOR;
CREATE TABLE HIST_CFG_ALHAJA_LISTADO_FACTOR
(
    ID BIGINT AUTO_INCREMENT NOT NULL,
    ULTIMA_ACTUALIZACION TIMESTAMP NOT NULL,
    FECHA_LISTADO DATE NOT NULL,
    PRIMARY KEY (ID)
);

DROP TABLE IF EXISTS CFG_DIAMANTE_VALOR_CERTIFICADO;
CREATE TABLE CFG_DIAMANTE_VALOR_CERTIFICADO
(
    ID BIGINT AUTO_INCREMENT NOT NULL,
    CERTIFICADO VARCHAR(20) NOT NULL,
    FACTOR DECIMAL(10, 3) NOT NULL,
    LISTADO BIGINT,
    PRIMARY KEY (ID)
);

DROP TABLE IF EXISTS CFG_DIAMANTE_LISTADO_VALOR_CERTIFICADO;
CREATE TABLE CFG_DIAMANTE_LISTADO_VALOR_CERTIFICADO
(
    ID BIGINT AUTO_INCREMENT NOT NULL,
    ULTIMA_ACTUALIZACION TIMESTAMP NOT NULL,
    FECHA_LISTADO DATE NOT NULL,
    PRIMARY KEY (ID)
);

DROP TABLE IF EXISTS HIST_CFG_DIAMANTE_VALOR_CERTIFICADO;
CREATE TABLE HIST_CFG_DIAMANTE_VALOR_CERTIFICADO
(
    ID BIGINT AUTO_INCREMENT NOT NULL,
    CERTIFICADO VARCHAR(20) NOT NULL,
    FACTOR DECIMAL(10, 3) NOT NULL,
    LISTADO BIGINT,
    PRIMARY KEY (ID)
);

DROP TABLE IF EXISTS HIST_CFG_DIAMANTE_LISTADO_VALOR_CERTIFICADO;
CREATE TABLE HIST_CFG_DIAMANTE_LISTADO_VALOR_CERTIFICADO
(
    ID BIGINT AUTO_INCREMENT NOT NULL,
    ULTIMA_ACTUALIZACION TIMESTAMP NOT NULL,
    FECHA_LISTADO DATE NOT NULL,
    PRIMARY KEY (ID)
);

-- ----------------------------------------------------------------------------------------------------------------------
-- INICIA - TABLAS: VALOR COMERCIAL 'DIAMANTE'
-- ----------------------------------------------------------------------------------------------------------------------

--
-- HISTÓRICOS
--

DROP TABLE IF EXISTS HIST_CFG_DIAMANTE_VALOR_COMERCIAL;
CREATE TABLE HIST_CFG_DIAMANTE_VALOR_COMERCIAL
(
    ID BIGINT NOT NULL,
    CORTE VARCHAR(20) NOT NULL,
    COLOR VARCHAR(20) NOT NULL,
    CLARIDAD VARCHAR(20) NOT NULL,
    TAMANIO_INFERIOR DECIMAL(6, 2) NOT NULL,
    TAMANIO_SUPERIOR DECIMAL(6, 2) NOT NULL,
    PRECIO DECIMAL(10, 4) NOT NULL,
    LISTADO BIGINT,
    TIPO_CAMBIO DECIMAL(12,4) NOT NULL,
    MONTOVBD DECIMAL(10, 4) NOT NULL,
    MONTOFCASTIGOXRANGO DECIMAL(10, 4) NOT NULL,
    PRIMARY KEY (ID)
);

DROP TABLE IF EXISTS HIST_CFG_DIAMANTE_LISTADO_VALOR_COMERCIAL;
CREATE TABLE HIST_CFG_DIAMANTE_LISTADO_VALOR_COMERCIAL
(
    ID BIGINT NOT NULL,
    FECHA_CARGA TIMESTAMP NOT NULL,
    FECHA_LISTADO DATE NOT NULL,
    PRIMARY KEY (ID)
);

--
-- VIGENTES
--

DROP TABLE IF EXISTS CFG_DIAMANTE_VALOR_COMERCIAL;
CREATE TABLE CFG_DIAMANTE_VALOR_COMERCIAL
(
    ID BIGINT AUTO_INCREMENT NOT NULL,
    CORTE VARCHAR(20) NOT NULL,
    COLOR VARCHAR(20) NOT NULL,
    CLARIDAD VARCHAR(20) NOT NULL,
    TAMANIO_INFERIOR DECIMAL(6, 2) NOT NULL,
    TAMANIO_SUPERIOR DECIMAL(6, 2) NOT NULL,
    PRECIO DECIMAL(10, 4) NOT NULL,
    LISTADO BIGINT,
    TIPO_CAMBIO DECIMAL(12,4) NOT NULL,
    MONTOVBD DECIMAL(10, 4) NOT NULL,
    MONTOFCASTIGOXRANGO DECIMAL(10, 4) NOT NULL,
    PRIMARY KEY (ID)
);

DROP TABLE IF EXISTS TMP_DIAMANTE_VALOR_COMERCIAL;
CREATE TABLE TMP_DIAMANTE_VALOR_COMERCIAL
(
    CORTE VARCHAR(20) NOT NULL,
    COLOR VARCHAR(20) NOT NULL,
    CLARIDAD VARCHAR(20) NOT NULL,
    TAMANIO_INFERIOR DECIMAL(6, 2) NOT NULL,
    TAMANIO_SUPERIOR DECIMAL(6, 2) NOT NULL,
    PRECIO DECIMAL(10, 4) NOT NULL,
    TIPO_CAMBIO DECIMAL(12,4) NOT NULL,
    MONTOVBD DECIMAL(10, 4) NOT NULL,
    MONTOFCASTIGOXRANGO DECIMAL(10, 4) NOT NULL
);

DROP TABLE IF EXISTS CFG_DIAMANTE_LISTADO_VALOR_COMERCIAL;
CREATE TABLE CFG_DIAMANTE_LISTADO_VALOR_COMERCIAL
(
    ID BIGINT AUTO_INCREMENT NOT NULL,
    FECHA_CARGA TIMESTAMP NOT NULL,
    FECHA_LISTADO DATE NOT NULL,
    PRIMARY KEY (ID)
);

-- ----------------------------------------------------------------------------------------------------------------------
-- TERMINA - TABLAS: VALOR COMERCIAL 'DIAMANTE'
-- ----------------------------------------------------------------------------------------------------------------------



-- ----------------------------------------------------------------------------------------------------------------------
-- INICIA - TABLAS: VALOR COMERCIAL 'ORO'
-- ----------------------------------------------------------------------------------------------------------------------

--
-- HISTÓRICOS
--

DROP TABLE IF EXISTS HIST_CFG_ALHAJA_VALOR_COMERCIAL_ORO;
CREATE TABLE HIST_CFG_ALHAJA_VALOR_COMERCIAL_ORO
(
    ID BIGINT AUTO_INCREMENT NOT NULL,
    COLOR VARCHAR(20) NOT NULL,
    CALIDAD VARCHAR(20) NOT NULL,
    PRECIO DECIMAL(10, 3) NOT NULL,
    LISTADO BIGINT,
    PRIMARY KEY (ID)
);

DROP TABLE IF EXISTS HIST_CFG_ALHAJA_LISTADO_VALOR_COMERCIAL_ORO;
CREATE TABLE HIST_CFG_ALHAJA_LISTADO_VALOR_COMERCIAL_ORO
(
    ID BIGINT AUTO_INCREMENT NOT NULL,
    ULTIMA_ACTUALIZACION TIMESTAMP NOT NULL,
    PRIMARY KEY (ID)
);

--
-- VIGENTES
--

DROP TABLE IF EXISTS CFG_ALHAJA_VALOR_COMERCIAL_ORO;
CREATE TABLE CFG_ALHAJA_VALOR_COMERCIAL_ORO
(
    ID BIGINT AUTO_INCREMENT NOT NULL,
    COLOR VARCHAR(20) NOT NULL,
    CALIDAD VARCHAR(20) NOT NULL,
    PRECIO DECIMAL(10, 3) NOT NULL,
    LISTADO BIGINT,
    PRIMARY KEY (ID)
);

DROP TABLE IF EXISTS CFG_ALHAJA_LISTADO_VALOR_COMERCIAL_ORO;
CREATE TABLE CFG_ALHAJA_LISTADO_VALOR_COMERCIAL_ORO
(
    ID BIGINT AUTO_INCREMENT NOT NULL,
    ULTIMA_ACTUALIZACION TIMESTAMP NOT NULL,
    PRIMARY KEY (ID)
);

-- ----------------------------------------------------------------------------------------------------------------------
-- TERMINA - TABLAS: VALOR COMERCIAL 'ORO'
-- ----------------------------------------------------------------------------------------------------------------------



-- ----------------------------------------------------------------------------------------------------------------------
-- INICIA - TABLAS: VALOR COMERCIAL 'METAL'
-- ----------------------------------------------------------------------------------------------------------------------

--
-- HISTÓRICOS
--

DROP TABLE IF EXISTS HIST_CFG_ALHAJA_VALOR_COMERCIAL_METAL;
CREATE TABLE HIST_CFG_ALHAJA_VALOR_COMERCIAL_METAL
(
    ID BIGINT AUTO_INCREMENT NOT NULL,
    METAL VARCHAR(20) NOT NULL,
    CALIDAD VARCHAR(20),
    PRECIO DECIMAL(10, 3) NOT NULL,
    LISTADO BIGINT,
    PRIMARY KEY (ID)
);

DROP TABLE IF EXISTS HIST_CFG_ALHAJA_LISTADO_VALOR_COMERCIAL_METAL;
CREATE TABLE HIST_CFG_ALHAJA_LISTADO_VALOR_COMERCIAL_METAL
(
    ID BIGINT AUTO_INCREMENT NOT NULL,
    ULTIMA_ACTUALIZACION TIMESTAMP NOT NULL,
    PRIMARY KEY (ID)
);

--
-- VIGENTES
--

DROP TABLE IF EXISTS CFG_ALHAJA_VALOR_COMERCIAL_METAL;
CREATE TABLE CFG_ALHAJA_VALOR_COMERCIAL_METAL
(
    ID BIGINT AUTO_INCREMENT NOT NULL,
    METAL VARCHAR(20) NOT NULL,
    CALIDAD VARCHAR(20),
    PRECIO DECIMAL(10, 3) NOT NULL,
    LISTADO BIGINT,
    PRIMARY KEY (ID)
);

DROP TABLE IF EXISTS CFG_ALHAJA_LISTADO_VALOR_COMERCIAL_METAL;
CREATE TABLE CFG_ALHAJA_LISTADO_VALOR_COMERCIAL_METAL
(
    ID BIGINT AUTO_INCREMENT NOT NULL,
    ULTIMA_ACTUALIZACION TIMESTAMP NOT NULL,
    PRIMARY KEY (ID)
);

-- ----------------------------------------------------------------------------------------------------------------------
-- TERMINA - TABLAS: VALOR COMERCIAL 'METAL'
-- ----------------------------------------------------------------------------------------------------------------------

ALTER TABLE JOURNAL_ENTITY_EVENT ADD FOREIGN KEY (ID) REFERENCES JOURNAL_EVENT (ID);
ALTER TABLE JOURNAL_CUSTOM_EVENT ADD FOREIGN KEY (ID) REFERENCES JOURNAL_EVENT (ID);
ALTER TABLE JOURNAL_EVENT_DATA ADD FOREIGN KEY (EVENT) REFERENCES JOURNAL_EVENT (ID);

CREATE INDEX IDX_CFG_DIAMANTE_FACTOR_ID ON CFG_DIAMANTE_FACTOR(ID);
CREATE INDEX IDX_CFG_DIAMANTE_FACTOR_FECHA_CARGA ON CFG_DIAMANTE_FACTOR(FECHA_CARGA);

CREATE INDEX IDX_CFG_ALHAJA_LISTADO_FACTOR_ID ON CFG_ALHAJA_LISTADO_FACTOR(ID);

CREATE INDEX IDX_CFG_FACTOR_ALHAJA_ID ON CFG_FACTOR_ALHAJA(ID);
ALTER TABLE CFG_FACTOR_ALHAJA ADD CONSTRAINT FK_CFG_FACTOR_ALHAJA_CFG_ALHAJA_LISTADO_FACTOR
FOREIGN KEY(LISTADO) REFERENCES CFG_ALHAJA_LISTADO_FACTOR(ID);

CREATE INDEX IDX_HIST_CFG_FACTOR_ALHAJA_ID ON HIST_CFG_FACTOR_ALHAJA(ID);
ALTER TABLE HIST_CFG_FACTOR_ALHAJA ADD CONSTRAINT FK_HIST_CFG_FACTOR_ALHAJA_HIST_CFG_ALHAJA_LISTADO_FACTOR
FOREIGN KEY(LISTADO) REFERENCES HIST_CFG_ALHAJA_LISTADO_FACTOR(ID);

CREATE INDEX IDX_CFG_DIAMANTE_LISTADO_VALOR_CERTIFICADO_ID ON CFG_DIAMANTE_LISTADO_VALOR_CERTIFICADO(ID);

CREATE INDEX IDX_CFG_DIAMANTE_VALOR_CERTIFICADO_ID ON CFG_DIAMANTE_VALOR_CERTIFICADO(ID);
ALTER TABLE CFG_DIAMANTE_VALOR_CERTIFICADO ADD CONSTRAINT FK_CFG_DIAMANTE_VALOR_CERTIFICADO_LISTADO
FOREIGN KEY(LISTADO) REFERENCES CFG_DIAMANTE_LISTADO_VALOR_CERTIFICADO(ID);

CREATE INDEX IDX_HIST_CFG_DIAMANTE_VALOR_CERTIFICADO_ID ON HIST_CFG_DIAMANTE_VALOR_CERTIFICADO(ID);
ALTER TABLE HIST_CFG_DIAMANTE_VALOR_CERTIFICADO ADD CONSTRAINT FK_HIST_CFG_DIAMANTE_VALOR_CERTIFICADO
FOREIGN KEY(LISTADO) REFERENCES HIST_CFG_DIAMANTE_LISTADO_VALOR_CERTIFICADO(ID);

CREATE INDEX IDX_HIST_CFG_DIAMANTE_LISTADO_VALOR_COMERCIAL_ID ON HIST_CFG_DIAMANTE_LISTADO_VALOR_COMERCIAL(ID);

CREATE INDEX IDX_HIST_CFG_DIAMANTE_VALOR_COMERCIAL_ID ON HIST_CFG_DIAMANTE_VALOR_COMERCIAL(ID);
ALTER TABLE HIST_CFG_DIAMANTE_VALOR_COMERCIAL ADD CONSTRAINT FK_HIST_CFG_DIAMANTE_VALOR_COMERCIAL
FOREIGN KEY(LISTADO) REFERENCES HIST_CFG_DIAMANTE_LISTADO_VALOR_COMERCIAL(ID) ON DELETE CASCADE;

CREATE INDEX IDX_CFG_DIAMANTE_LISTADO_VALOR_COMERCIAL_ID ON CFG_DIAMANTE_LISTADO_VALOR_COMERCIAL(ID);

CREATE INDEX IDX_CFG_DIAMANTE_VALOR_COMERCIAL_ID ON CFG_DIAMANTE_VALOR_COMERCIAL(ID);
ALTER TABLE CFG_DIAMANTE_VALOR_COMERCIAL ADD CONSTRAINT FK_CFG_DIAMANTE_VALOR_COMERCIAL
FOREIGN KEY(LISTADO) REFERENCES CFG_DIAMANTE_LISTADO_VALOR_COMERCIAL(ID) ON DELETE CASCADE;

CREATE INDEX IDX_HIST_CFG_ALHAJA_LISTADO_VALOR_COMERCIAL_ORO_ID ON HIST_CFG_ALHAJA_LISTADO_VALOR_COMERCIAL_ORO(ID);

CREATE INDEX IDX_HIST_CFG_ALHAJA_VALOR_COMERCIAL_ORO_ID ON HIST_CFG_ALHAJA_VALOR_COMERCIAL_ORO(ID);
ALTER TABLE HIST_CFG_ALHAJA_VALOR_COMERCIAL_ORO ADD CONSTRAINT FK_HIST_CFG_ALHAJA_VALOR_COMERCIAL_ORO
FOREIGN KEY(LISTADO) REFERENCES HIST_CFG_ALHAJA_LISTADO_VALOR_COMERCIAL_ORO(ID);

CREATE INDEX IDX_CFG_ALHAJA_LISTADO_VALOR_COMERCIAL_ORO_ID ON CFG_ALHAJA_LISTADO_VALOR_COMERCIAL_ORO(ID);

CREATE INDEX IDX_CFG_ALHAJA_VALOR_COMERCIAL_ORO_ID ON CFG_ALHAJA_VALOR_COMERCIAL_ORO(ID);
ALTER TABLE CFG_ALHAJA_VALOR_COMERCIAL_ORO ADD CONSTRAINT FK_CFG_ALHAJA_VALOR_COMERCIAL_ORO
FOREIGN KEY(LISTADO) REFERENCES CFG_ALHAJA_LISTADO_VALOR_COMERCIAL_ORO(ID);

CREATE INDEX IDX_HIST_CFG_ALHAJA_LISTADO_VALOR_COMERCIAL_METAL_ID ON HIST_CFG_ALHAJA_LISTADO_VALOR_COMERCIAL_METAL(ID);

CREATE INDEX IDX_HIST_CFG_ALHAJA_VALOR_COMERCIAL_METAL_ID ON HIST_CFG_ALHAJA_VALOR_COMERCIAL_METAL(ID);
ALTER TABLE HIST_CFG_ALHAJA_VALOR_COMERCIAL_METAL ADD CONSTRAINT FK_HIST_CFG_ALHAJA_VALOR_COMERCIAL_METAL
FOREIGN KEY(LISTADO) REFERENCES HIST_CFG_ALHAJA_LISTADO_VALOR_COMERCIAL_METAL(ID);

CREATE INDEX IDX_CFG_ALHAJA_LISTADO_VALOR_COMERCIAL_METAL_ID ON CFG_ALHAJA_LISTADO_VALOR_COMERCIAL_METAL(ID);

CREATE INDEX IDX_CFG_ALHAJA_VALOR_COMERCIAL_METAL_ID ON CFG_ALHAJA_VALOR_COMERCIAL_METAL(ID);
ALTER TABLE CFG_ALHAJA_VALOR_COMERCIAL_METAL ADD CONSTRAINT FK_CFG_ALHAJA_VALOR_COMERCIAL_METAL
FOREIGN KEY(LISTADO) REFERENCES CFG_ALHAJA_LISTADO_VALOR_COMERCIAL_METAL(ID);


DROP PROCEDURE IF EXISTS sp_diamante_valor_comercial_generar_historico;
DELIMITER //
CREATE PROCEDURE sp_diamante_valor_comercial_generar_historico(IN _listado BIGINT)
    BEGIN
        DECLARE EXIT HANDLER FOR SQLEXCEPTION
        BEGIN
            ROLLBACK;
            RESIGNAL;
        END;

        START TRANSACTION;
            -- Se pasa el listado vigente a la tabla de historicos
            INSERT INTO hist_cfg_diamante_listado_valor_comercial
                SELECT * FROM cfg_diamante_listado_valor_comercial
                WHERE id = _listado;

            -- Se pasan los valores comerciales vigentes a la tabla de historicos
            INSERT INTO hist_cfg_diamante_valor_comercial
                SELECT * FROM cfg_diamante_valor_comercial
                WHERE listado = _listado;

            -- Se elimina el listado y valores comerciales vigentes
            DELETE FROM cfg_diamante_listado_valor_comercial
            WHERE id = _listado;
        COMMIT;
    END //
DELIMITER ;

DROP PROCEDURE IF EXISTS sp_diamante_valor_comercial_generar_vigente;
DELIMITER //
CREATE PROCEDURE sp_diamante_valor_comercial_generar_vigente(IN _fechaListado DATE)
    BEGIN
        DECLARE idListadoVigeneteActual BIGINT;
        DECLARE idListadoVigeneteNuevo BIGINT;

        DECLARE EXIT HANDLER FOR SQLEXCEPTION
        BEGIN
            ROLLBACK;
            RESIGNAL;
        END;

        START TRANSACTION;
            -- Se selecciona el identificador del listado vigente
            SELECT MAX(id) INTO idListadoVigeneteActual FROM cfg_diamante_listado_valor_comercial;

            -- Se generan los historicos
            CALL sp_diamante_valor_comercial_generar_historico(idListadoVigeneteActual);

            -- Se inserta el nuevo listado vigente
            INSERT INTO cfg_diamante_listado_valor_comercial(fecha_listado) VALUES (_fechaListado);

            -- Se recupera el identificador del listado vigente
            SET idListadoVigeneteNuevo = last_insert_id();

            -- Se insertan los valores comerciales vigentes con el listado vigente
            INSERT INTO
                cfg_diamante_valor_comercial(corte, color, claridad, tamanio_inferior, tamanio_superior, precio, listado,
                    tipo_cambio, montovbd, montofcastigoxrango)
                SELECT corte, color, claridad, tamanio_inferior, tamanio_superior, precio, idListadoVigeneteNuevo,
                    tipo_cambio, montovbd, montofcastigoxrango
                FROM tmp_diamante_valor_comercial;

            -- Se limpia la tabla temporal
            TRUNCATE tmp_diamante_valor_comercial;
        COMMIT;
    END //
DELIMITER ;

DROP PROCEDURE IF EXISTS sp_diamante_valor_comercial_restaurar_historico;
DELIMITER //
CREATE PROCEDURE sp_diamante_valor_comercial_restaurar_historico(IN _listado BIGINT)
    BEGIN
        DECLARE idListadoVigeneteNuevo BIGINT;

        DECLARE EXIT HANDLER FOR SQLEXCEPTION
        BEGIN
            ROLLBACK;
            RESIGNAL;
        END;

        START TRANSACTION;
            -- Se pasa el listado historico a la tabla de vigentes
            INSERT INTO cfg_diamante_listado_valor_comercial (fecha_listado)
                SELECT fecha_listado FROM hist_cfg_diamante_listado_valor_comercial
                WHERE id = _listado;

            -- Se recupera el identificador del listado vigente
            SET idListadoVigeneteNuevo = last_insert_id();

            -- Se pasan los valores comerciales historicos a la tabla de vigentes
            INSERT INTO
                cfg_diamante_valor_comercial (corte, color, claridad, tamanio_inferior, tamanio_superior, precio, listado,
                    tipo_cambio, montovbd, montofcastigoxrango)
                SELECT corte, color, claridad, tamanio_inferior, tamanio_superior, precio, idListadoVigeneteNuevo,
                    tipo_cambio, montovbd, montofcastigoxrango
                FROM hist_cfg_diamante_valor_comercial
                WHERE listado = _listado;
        COMMIT;
    END //
DELIMITER ;

DROP PROCEDURE IF EXISTS sp_diamante_valor_comercial_restaurar_anterior;
DELIMITER //
CREATE PROCEDURE sp_diamante_valor_comercial_restaurar_anterior()
    BEGIN
        DECLARE idListadoVigeneteActual BIGINT;
        DECLARE idListadoHistorico BIGINT;

        DECLARE EXIT HANDLER FOR SQLEXCEPTION
        BEGIN
            ROLLBACK;
            RESIGNAL;
        END;

        START TRANSACTION;
            -- Recupera el id y fecha carga del listado vigente
            CALL sp_diamante_valor_comercial_recuperar_vigente(idListadoVigeneteActual);

            -- Selecciona el identificador del listado historico donde la fecha de
            -- carga se menor a la del lisdado vigente
            SELECT id INTO idListadoHistorico
            FROM hist_cfg_diamante_listado_valor_comercial
            ORDER BY fecha_carga DESC LIMIT 1;

            -- Verifica si no hay una fecha de carga menor a la vigente
            IF idListadoHistorico IS NULL THEN
                SIGNAL SQLSTATE '02000'
                SET MESSAGE_TEXT = 'No existe un listado de precios anterior.',
                MYSQL_ERRNO = 1329;
            END IF;

            CALL sp_diamante_valor_comercial_generar_historico(idListadoVigeneteActual);
            CALL sp_diamante_valor_comercial_restaurar_historico(idListadoHistorico);
        COMMIT;
    END //
DELIMITER ;

DROP PROCEDURE IF EXISTS sp_diamante_valor_comercial_restaurar_fecha;
DELIMITER //
CREATE PROCEDURE sp_diamante_valor_comercial_restaurar_fecha(IN _fechaIni TIMESTAMP, IN _fechaFin TIMESTAMP)
    BEGIN
        DECLARE idListadoVigeneteActual BIGINT;
        DECLARE idListadoHistorico BIGINT;

        DECLARE EXIT HANDLER FOR SQLEXCEPTION
        BEGIN
            ROLLBACK;
            RESIGNAL;
        END;

        START TRANSACTION;
            -- Recupera el id y fecha carga del listado vigente
            CALL sp_diamante_valor_comercial_recuperar_vigente(idListadoVigeneteActual);

            -- Selecciona el identificador del listado historico donde la fecha de
            -- carga este en el rango de los parametros
            SELECT id INTO idListadoHistorico
            FROM hist_cfg_diamante_listado_valor_comercial
            WHERE fecha_carga BETWEEN _fechaIni AND _fechaFin ORDER BY fecha_carga DESC LIMIT 1;

        -- Verifica si existe un listado historico para la fecha
        IF idListadoHistorico IS NULL THEN
            SIGNAL SQLSTATE '02000'
            SET MESSAGE_TEXT = 'No existe un listado de precios para la fecha.',
            MYSQL_ERRNO = 1329;
        END IF;

        CALL sp_diamante_valor_comercial_generar_historico(idListadoVigeneteActual);
        CALL sp_diamante_valor_comercial_restaurar_historico(idListadoHistorico);
        COMMIT;
    END //
DELIMITER ;


DROP PROCEDURE IF EXISTS sp_diamante_valor_comercial_recuperar_vigente;
DELIMITER //
CREATE PROCEDURE sp_diamante_valor_comercial_recuperar_vigente(OUT idListadoVigeneteActual BIGINT)
    BEGIN
        -- Se selecciona el identificador y la fecha de carga del listado vigente
        SELECT id INTO idListadoVigeneteActual
        FROM cfg_diamante_listado_valor_comercial ORDER BY id DESC LIMIT 1;

        -- Verifica si existe un listado vigente
        IF idListadoVigeneteActual IS NULL THEN
            SIGNAL SQLSTATE '02000'
            SET MESSAGE_TEXT = 'No existe un listado de precios vigente.',
            MYSQL_ERRNO = 1329;
        END IF;
    END //
DELIMITER ;
