
DROP TABLE IF EXISTS journal_entity_event;
CREATE TABLE journal_entity_event
(
    id BIGINT NOT NULL,
    class VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS journal_custom_event;
CREATE TABLE journal_custom_event
(
    id BIGINT NOT NULL,
    comment VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS journal_event_data;
CREATE TABLE journal_event_data
(
    id BIGINT AUTO_INCREMENT NOT NULL,
    event BIGINT NOT NULL,
    property VARCHAR(255) NOT NULL,
    value VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS journal_event;
CREATE TABLE journal_event
(
    id BIGINT AUTO_INCREMENT NOT NULL,
    principal VARCHAR(50) NOT NULL,
    DATE TIMESTAMP,
    type VARCHAR(255),
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS cfg_diamante_factor;
CREATE TABLE cfg_diamante_factor
(
    id BIGINT AUTO_INCREMENT NOT NULL,
    factor_maximo DECIMAL(10, 3) NOT NULL,
    factor_medio DECIMAL(10, 3) NOT NULL,
    factor_minimo DECIMAL(10, 3) NOT NULL,
    fecha_carga TIMESTAMP NOT NULL,
    fecha_listado DATE NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS cfg_factor_alhaja;
CREATE TABLE cfg_factor_alhaja
(
    id BIGINT AUTO_INCREMENT NOT NULL,
    metal VARCHAR(20) NOT NULL,
    calidad VARCHAR(20),
    rango VARCHAR(20) NOT NULL,
    factor DECIMAL(8, 2) NOT NULL,
    desplazamiento DECIMAL(8, 2) NOT NULL,
    limite_inferior DECIMAL(8, 2) NOT NULL,
    limite_superior DECIMAL(8, 2) NOT NULL,
    ultima_actualizacion TIMESTAMP NOT NULL,
    listado BIGINT,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS cfg_alhaja_listado_factor;
CREATE TABLE cfg_alhaja_listado_factor
(
    id BIGINT AUTO_INCREMENT NOT NULL,
    ultima_actualizacion TIMESTAMP NOT NULL,
    fecha_listado DATE NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS hist_cfg_factor_alhaja;
CREATE TABLE hist_cfg_factor_alhaja
(
    id BIGINT AUTO_INCREMENT NOT NULL,
    metal VARCHAR(20) NOT NULL,
    calidad VARCHAR(20),
    rango VARCHAR(20) NOT NULL,
    factor DECIMAL(8, 2) NOT NULL,
    desplazamiento DECIMAL(8, 2) NOT NULL,
    limite_inferior DECIMAL(8, 2) NOT NULL,
    limite_superior DECIMAL(8, 2) NOT NULL,
    ultima_actualizacion TIMESTAMP NOT NULL,
    listado BIGINT,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS hist_cfg_alhaja_listado_factor;
CREATE TABLE hist_cfg_alhaja_listado_factor
(
    id BIGINT AUTO_INCREMENT NOT NULL,
    ultima_actualizacion TIMESTAMP NOT NULL,
    fecha_listado DATE NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS cfg_diamante_valor_certificado;
CREATE TABLE cfg_diamante_valor_certificado
(
    id BIGINT AUTO_INCREMENT NOT NULL,
    certificado VARCHAR(20) NOT NULL,
    factor DECIMAL(10, 3) NOT NULL,
    listado BIGINT,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS cfg_diamante_listado_valor_certificado;
CREATE TABLE cfg_diamante_listado_valor_certificado
(
    id BIGINT AUTO_INCREMENT NOT NULL,
    ultima_actualizacion TIMESTAMP NOT NULL,
    fecha_listado DATE NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS hist_cfg_diamante_valor_certificado;
CREATE TABLE hist_cfg_diamante_valor_certificado
(
    id BIGINT AUTO_INCREMENT NOT NULL,
    certificado VARCHAR(20) NOT NULL,
    factor DECIMAL(10, 3) NOT NULL,
    listado BIGINT,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS hist_cfg_diamante_listado_valor_certificado;
CREATE TABLE hist_cfg_diamante_listado_valor_certificado
(
    id BIGINT AUTO_INCREMENT NOT NULL,
    ultima_actualizacion TIMESTAMP NOT NULL,
    fecha_listado DATE NOT NULL,
    PRIMARY KEY (id)
);

-- ----------------------------------------------------------------------------------------------------------------------
-- inicia - tablas: valor comercial 'diamante'
-- ----------------------------------------------------------------------------------------------------------------------

--
-- históricos
--

DROP TABLE IF EXISTS hist_cfg_diamante_valor_comercial;
CREATE TABLE hist_cfg_diamante_valor_comercial
(
    id BIGINT NOT NULL,
    corte VARCHAR(20) NOT NULL,
    color VARCHAR(20) NOT NULL,
    claridad VARCHAR(20) NOT NULL,
    tamanio_inferior DECIMAL(6, 2) NOT NULL,
    tamanio_superior DECIMAL(6, 2) NOT NULL,
    precio DECIMAL(10, 4) NOT NULL,
    listado BIGINT,
    tipo_cambio DECIMAL(12,4) NOT NULL,
    montovbd DECIMAL(15, 4) NOT NULL,
    montofcastigoxrango DECIMAL(15, 4) NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS hist_cfg_diamante_listado_valor_comercial;
CREATE TABLE hist_cfg_diamante_listado_valor_comercial
(
    id BIGINT NOT NULL,
    fecha_carga TIMESTAMP NOT NULL,
    fecha_listado DATE NOT NULL,
    PRIMARY KEY (id)
);

--
-- vigentes
--

DROP TABLE IF EXISTS cfg_diamante_valor_comercial;
CREATE TABLE cfg_diamante_valor_comercial
(
    id BIGINT AUTO_INCREMENT NOT NULL,
    corte VARCHAR(20) NOT NULL,
    color VARCHAR(20) NOT NULL,
    claridad VARCHAR(20) NOT NULL,
    tamanio_inferior DECIMAL(6, 2) NOT NULL,
    tamanio_superior DECIMAL(6, 2) NOT NULL,
    precio DECIMAL(10, 4) NOT NULL,
    listado BIGINT,
    tipo_cambio DECIMAL(12,4) NOT NULL,
    montovbd DECIMAL(15, 4) NOT NULL,
    montofcastigoxrango DECIMAL(15, 4) NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS tmp_diamante_valor_comercial;
CREATE TABLE tmp_diamante_valor_comercial
(
	id BIGINT AUTO_INCREMENT NOT NULL,
    corte VARCHAR(20) NOT NULL,
    color VARCHAR(20) NOT NULL,
    claridad VARCHAR(20) NOT NULL,
    tamanio_inferior DECIMAL(6, 2) NOT NULL,
    tamanio_superior DECIMAL(6, 2) NOT NULL,
    precio DECIMAL(10, 4) NOT NULL,
    tipo_cambio DECIMAL(12,4) NOT NULL,
    montovbd DECIMAL(15, 4) NOT NULL,
    montofcastigoxrango DECIMAL(15, 4) NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS cfg_diamante_listado_valor_comercial;
CREATE TABLE cfg_diamante_listado_valor_comercial
(
    id BIGINT AUTO_INCREMENT NOT NULL,
    fecha_carga TIMESTAMP NOT NULL,
    fecha_listado DATE NOT NULL,
    PRIMARY KEY (id)
);

-- ----------------------------------------------------------------------------------------------------------------------
-- termina - tablas: valor comercial 'diamante'
-- ----------------------------------------------------------------------------------------------------------------------



-- ----------------------------------------------------------------------------------------------------------------------
-- inicia - tablas: valor comercial 'oro'
-- ----------------------------------------------------------------------------------------------------------------------

--
-- históricos
--

DROP TABLE IF EXISTS hist_cfg_alhaja_valor_comercial_oro;
CREATE TABLE hist_cfg_alhaja_valor_comercial_oro
(
    id BIGINT AUTO_INCREMENT NOT NULL,
    color VARCHAR(20) NOT NULL,
    calidad VARCHAR(20) NOT NULL,
    precio DECIMAL(10, 3) NOT NULL,
    listado BIGINT,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS hist_cfg_alhaja_listado_valor_comercial_oro;
CREATE TABLE hist_cfg_alhaja_listado_valor_comercial_oro
(
    id BIGINT AUTO_INCREMENT NOT NULL,
    ultima_actualizacion TIMESTAMP NOT NULL,
    PRIMARY KEY (id)
);

--
-- vigentes
--

DROP TABLE IF EXISTS cfg_alhaja_valor_comercial_oro;
CREATE TABLE cfg_alhaja_valor_comercial_oro
(
    id BIGINT AUTO_INCREMENT NOT NULL,
    color VARCHAR(20) NOT NULL,
    calidad VARCHAR(20) NOT NULL,
    precio DECIMAL(10, 3) NOT NULL,
    listado BIGINT,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS cfg_alhaja_listado_valor_comercial_oro;
CREATE TABLE cfg_alhaja_listado_valor_comercial_oro
(
    id BIGINT AUTO_INCREMENT NOT NULL,
    ultima_actualizacion TIMESTAMP NOT NULL,
    PRIMARY KEY (id)
);

-- ----------------------------------------------------------------------------------------------------------------------
-- termina - tablas: valor comercial 'oro'
-- ----------------------------------------------------------------------------------------------------------------------



-- ----------------------------------------------------------------------------------------------------------------------
-- inicia - tablas: valor comercial 'metal'
-- ----------------------------------------------------------------------------------------------------------------------

--
-- históricos
--

DROP TABLE IF EXISTS hist_cfg_alhaja_valor_comercial_metal;
CREATE TABLE hist_cfg_alhaja_valor_comercial_metal
(
    id BIGINT AUTO_INCREMENT NOT NULL,
    metal VARCHAR(20) NOT NULL,
    calidad VARCHAR(20),
    precio DECIMAL(10, 3) NOT NULL,
    listado BIGINT,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS hist_cfg_alhaja_listado_valor_comercial_metal;
CREATE TABLE hist_cfg_alhaja_listado_valor_comercial_metal
(
    id BIGINT AUTO_INCREMENT NOT NULL,
    ultima_actualizacion TIMESTAMP NOT NULL,
    PRIMARY KEY (id)
);

--
-- vigentes
--

DROP TABLE IF EXISTS cfg_alhaja_valor_comercial_metal;
CREATE TABLE cfg_alhaja_valor_comercial_metal
(
    id BIGINT AUTO_INCREMENT NOT NULL,
    metal VARCHAR(20) NOT NULL,
    calidad VARCHAR(20),
    precio DECIMAL(10, 3) NOT NULL,
    listado BIGINT,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS cfg_alhaja_listado_valor_comercial_metal;
CREATE TABLE cfg_alhaja_listado_valor_comercial_metal
(
    id BIGINT AUTO_INCREMENT NOT NULL,
    ultima_actualizacion TIMESTAMP NOT NULL,
    PRIMARY KEY (id)
);

-- ----------------------------------------------------------------------------------------------------------------------
-- termina - tablas: valor comercial 'metal'
-- ----------------------------------------------------------------------------------------------------------------------

ALTER TABLE journal_entity_event add FOREIGN KEY (id) REFERENCES journal_event (id);
ALTER TABLE journal_custom_event add FOREIGN KEY (id) REFERENCES journal_event (id);
ALTER TABLE journal_event_data add FOREIGN KEY (event) REFERENCES journal_event (id);

CREATE INDEX idx_cfg_diamante_factor_id ON cfg_diamante_factor(id);
CREATE INDEX idx_cfg_diamante_factor_fecha_carga ON cfg_diamante_factor(fecha_carga);

CREATE INDEX idx_cfg_alhaja_listado_factor_id ON cfg_alhaja_listado_factor(id);

CREATE INDEX idx_cfg_factor_alhaja_id ON cfg_factor_alhaja(id);
ALTER TABLE cfg_factor_alhaja ADD CONSTRAINT fk_cfg_factor_alhaja_cfg_alhaja_listado_factor
FOREIGN KEY(listado) REFERENCES cfg_alhaja_listado_factor(id);

CREATE INDEX idx_hist_cfg_factor_alhaja_id ON hist_cfg_factor_alhaja(id);
ALTER TABLE hist_cfg_factor_alhaja ADD CONSTRAINT fk_hist_cfg_factor_alhaja_hist_cfg_alhaja_listado_factor
FOREIGN KEY(listado) REFERENCES hist_cfg_alhaja_listado_factor(id);

CREATE INDEX idx_cfg_diamante_listado_valor_certificado_id ON cfg_diamante_listado_valor_certificado(id);

CREATE INDEX idx_cfg_diamante_valor_certificado_id ON cfg_diamante_valor_certificado(id);
ALTER TABLE cfg_diamante_valor_certificado ADD CONSTRAINT fk_cfg_diamante_valor_certificado_listado
FOREIGN KEY(listado) REFERENCES cfg_diamante_listado_valor_certificado(id);

CREATE INDEX idx_hist_cfg_diamante_valor_certificado_id ON hist_cfg_diamante_valor_certificado(id);
ALTER TABLE hist_cfg_diamante_valor_certificado ADD CONSTRAINT fk_hist_cfg_diamante_valor_certificado
FOREIGN KEY(listado) REFERENCES hist_cfg_diamante_listado_valor_certificado(id);

CREATE INDEX idx_hist_cfg_diamante_listado_valor_comercial_id ON hist_cfg_diamante_listado_valor_comercial(id);

CREATE INDEX idx_hist_cfg_diamante_valor_comercial_id ON hist_cfg_diamante_valor_comercial(id);
ALTER TABLE hist_cfg_diamante_valor_comercial ADD CONSTRAINT fk_hist_cfg_diamante_valor_comercial
FOREIGN KEY(listado) REFERENCES hist_cfg_diamante_listado_valor_comercial(id) ON DELETE cascade;

CREATE INDEX idx_cfg_diamante_listado_valor_comercial_id ON cfg_diamante_listado_valor_comercial(id);

CREATE INDEX idx_cfg_diamante_valor_comercial_id ON cfg_diamante_valor_comercial(id);
ALTER TABLE cfg_diamante_valor_comercial ADD CONSTRAINT fk_cfg_diamante_valor_comercial
FOREIGN KEY(listado) REFERENCES cfg_diamante_listado_valor_comercial(id) ON DELETE cascade;

CREATE INDEX idx_hist_cfg_alhaja_listado_valor_comercial_oro_id ON hist_cfg_alhaja_listado_valor_comercial_oro(id);

CREATE INDEX idx_hist_cfg_alhaja_valor_comercial_oro_id ON hist_cfg_alhaja_valor_comercial_oro(id);
ALTER TABLE hist_cfg_alhaja_valor_comercial_oro ADD CONSTRAINT fk_hist_cfg_alhaja_valor_comercial_oro
FOREIGN KEY(listado) REFERENCES hist_cfg_alhaja_listado_valor_comercial_oro(id);

CREATE INDEX idx_cfg_alhaja_listado_valor_comercial_oro_id ON cfg_alhaja_listado_valor_comercial_oro(id);

CREATE INDEX idx_cfg_alhaja_valor_comercial_oro_id ON cfg_alhaja_valor_comercial_oro(id);
ALTER TABLE cfg_alhaja_valor_comercial_oro ADD CONSTRAINT fk_cfg_alhaja_valor_comercial_oro
FOREIGN KEY(listado) REFERENCES cfg_alhaja_listado_valor_comercial_oro(id);

CREATE INDEX idx_hist_cfg_alhaja_listado_valor_comercial_metal_id ON hist_cfg_alhaja_listado_valor_comercial_metal(id);

CREATE INDEX idx_hist_cfg_alhaja_valor_comercial_metal_id ON hist_cfg_alhaja_valor_comercial_metal(id);
ALTER TABLE hist_cfg_alhaja_valor_comercial_metal ADD CONSTRAINT fk_hist_cfg_alhaja_valor_comercial_metal
FOREIGN KEY(listado) REFERENCES hist_cfg_alhaja_listado_valor_comercial_metal(id);

CREATE INDEX idx_cfg_alhaja_listado_valor_comercial_metal_id ON cfg_alhaja_listado_valor_comercial_metal(id);

CREATE INDEX idx_cfg_alhaja_valor_comercial_metal_id ON cfg_alhaja_valor_comercial_metal(id);
ALTER TABLE cfg_alhaja_valor_comercial_metal ADD CONSTRAINT fk_cfg_alhaja_valor_comercial_metal
FOREIGN KEY(listado) REFERENCES cfg_alhaja_listado_valor_comercial_metal(id);


DROP PROCEDURE IF exists sp_diamante_valor_comercial_generar_historico;
DELIMITER //
CREATE PROCEDURE sp_diamante_valor_comercial_generar_historico(in _listado BIGINT)
    BEGIN
        DECLARE EXIT HANDLER FOR SQLEXCEPTION
        BEGIN
            ROLLBACK;
            RESIGNAL;
        END;

        START TRANSACTION;
            -- se pasa el listado vigente a la tabla de historicos
            INSERT INTO hist_cfg_diamante_listado_valor_comercial
                SELECT * FROM cfg_diamante_listado_valor_comercial
                WHERE id = _listado;

            -- se pasan los valores comerciales vigentes a la tabla de historicos
            INSERT INTO hist_cfg_diamante_valor_comercial
                SELECT * FROM cfg_diamante_valor_comercial
                WHERE listado = _listado;

            -- se elimina el listado y valores comerciales vigentes
            DELETE FROM cfg_diamante_listado_valor_comercial
            WHERE id = _listado;
        COMMIT;
    END //
DELIMITER ;

DROP PROCEDURE IF exists sp_diamante_valor_comercial_generar_vigente;
DELIMITER //
CREATE PROCEDURE sp_diamante_valor_comercial_generar_vigente(in _fechalistado DATE)
    BEGIN
        DECLARE idlistadovigeneteactual BIGINT;
        DECLARE idlistadovigenetenuevo BIGINT;

        DECLARE EXIT HANDLER FOR SQLEXCEPTION
        BEGIN
            ROLLBACK;
            RESIGNAL;
        END;

        START TRANSACTION;
            -- se selecciona el identificador del listado vigente
            SELECT max(id) INTO idlistadovigeneteactual FROM cfg_diamante_listado_valor_comercial;

            -- se generan los historicos
            CALL sp_diamante_valor_comercial_generar_historico(idlistadovigeneteactual);

            -- se inserta el nuevo listado vigente
            INSERT INTO cfg_diamante_listado_valor_comercial(fecha_listado) values (_fechalistado);

            -- se recupera el identificador del listado vigente
            SET idlistadovigenetenuevo = last_insert_id();

            -- se insertan los valores comerciales vigentes con el listado vigente
            INSERT INTO
                cfg_diamante_valor_comercial(corte, color, claridad, tamanio_inferior, tamanio_superior, precio, listado,
                    tipo_cambio, montovbd, montofcastigoxrango)
                SELECT corte, color, claridad, tamanio_inferior, tamanio_superior, precio, idlistadovigenetenuevo,
                    tipo_cambio, montovbd, montofcastigoxrango
                FROM tmp_diamante_valor_comercial;

            -- se limpia la tabla temporal
            truncate table tmp_diamante_valor_comercial;
        COMMIT;
    END //
DELIMITER ;

DROP PROCEDURE IF exists sp_diamante_valor_comercial_restaurar_historico;
DELIMITER //
CREATE PROCEDURE sp_diamante_valor_comercial_restaurar_historico(in _listado BIGINT)
    BEGIN
        DECLARE idlistadovigenetenuevo BIGINT;

        DECLARE EXIT HANDLER FOR SQLEXCEPTION
        BEGIN
            ROLLBACK;
            RESIGNAL;
        END;

        START TRANSACTION;
            -- se pasa el listado historico a la tabla de vigentes
            INSERT INTO cfg_diamante_listado_valor_comercial (fecha_listado)
                SELECT fecha_listado FROM hist_cfg_diamante_listado_valor_comercial
                WHERE id = _listado;

            -- se recupera el identificador del listado vigente
            SET idlistadovigenetenuevo = last_insert_id();

            -- se pasan los valores comerciales historicos a la tabla de vigentes
            INSERT INTO
                cfg_diamante_valor_comercial (corte, color, claridad, tamanio_inferior, tamanio_superior, precio, listado,
                    tipo_cambio, montovbd, montofcastigoxrango)
                SELECT corte, color, claridad, tamanio_inferior, tamanio_superior, precio, idlistadovigenetenuevo,
                    tipo_cambio, montovbd, montofcastigoxrango
                FROM hist_cfg_diamante_valor_comercial
                WHERE listado = _listado;
        COMMIT;
    END //
DELIMITER ;

DROP PROCEDURE IF exists sp_diamante_valor_comercial_restaurar_anterior;
DELIMITER //
CREATE PROCEDURE sp_diamante_valor_comercial_restaurar_anterior()
    BEGIN
        DECLARE idlistadovigeneteactual BIGINT;
        DECLARE idlistadohistorico BIGINT;

        DECLARE EXIT HANDLER FOR SQLEXCEPTION
        BEGIN
            ROLLBACK;
            RESIGNAL;
        END;

        START TRANSACTION;
            -- recupera el id y fecha carga del listado vigente
            CALL sp_diamante_valor_comercial_recuperar_vigente(idlistadovigeneteactual);

            -- selecciona el identificador del listado historico donde la fecha de
            -- carga se menor a la del lisdado vigente
            SELECT id INTO idlistadohistorico
            FROM hist_cfg_diamante_listado_valor_comercial
            ORDER BY fecha_carga DESC LIMIT 1;

            -- verifica si no hay una fecha de carga menor a la vigente
            IF idlistadohistorico IS NULL THEN
                signal sqlstate '02000'
                SET message_text = 'no existe un listado de precios anterior.',
                mysql_errno = 1329;
            END IF;

            CALL sp_diamante_valor_comercial_generar_historico(idlistadovigeneteactual);
            CALL sp_diamante_valor_comercial_restaurar_historico(idlistadohistorico);
        COMMIT;
    END //
DELIMITER ;

DROP PROCEDURE IF exists sp_diamante_valor_comercial_restaurar_fecha;
DELIMITER //
CREATE PROCEDURE sp_diamante_valor_comercial_restaurar_fecha(in _fechaini TIMESTAMP, in _fechafin TIMESTAMP)
    BEGIN
        DECLARE idlistadovigeneteactual BIGINT;
        DECLARE idlistadohistorico BIGINT;

        DECLARE EXIT HANDLER FOR SQLEXCEPTION
        BEGIN
            ROLLBACK;
            RESIGNAL;
        END;

        START TRANSACTION;
            -- recupera el id y fecha carga del listado vigente
            CALL sp_diamante_valor_comercial_recuperar_vigente(idlistadovigeneteactual);

            -- selecciona el identificador del listado historico donde la fecha de
            -- carga este en el rango de los parametros
            SELECT id INTO idlistadohistorico
            FROM hist_cfg_diamante_listado_valor_comercial
            WHERE fecha_carga between _fechaini and _fechafin ORDER BY fecha_carga DESC LIMIT 1;

        -- verifica si existe un listado historico para la fecha
        IF idlistadohistorico IS NULL THEN
            signal sqlstate '02000'
            SET message_text = 'no existe un listado de precios para la fecha.',
            mysql_errno = 1329;
        END IF;

        CALL sp_diamante_valor_comercial_generar_historico(idlistadovigeneteactual);
        CALL sp_diamante_valor_comercial_restaurar_historico(idlistadohistorico);
        COMMIT;
    END //
DELIMITER ;


DROP PROCEDURE IF exists sp_diamante_valor_comercial_recuperar_vigente;
DELIMITER //
CREATE PROCEDURE sp_diamante_valor_comercial_recuperar_vigente(OUT idlistadovigeneteactual BIGINT)
    BEGIN
        -- se selecciona el identificador y la fecha de carga del listado vigente
        SELECT id INTO idlistadovigeneteactual
        FROM cfg_diamante_listado_valor_comercial ORDER BY id DESC LIMIT 1;

        -- verifica si existe un listado vigente
        IF idlistadovigeneteactual IS NULL THEN
            signal sqlstate '02000'
            SET message_text = 'no existe un listado de precios vigente.',
            mysql_errno = 1329;
        END IF;
    END //
DELIMITER ;

-- inicio nuevas tablas para siva diamantes f2

--
-- table structure for table `cfg_diamante_factores_x_rango_de_color`
--

DROP TABLE IF EXISTS `cfg_diamante_factores_x_rango_de_color`;
CREATE TABLE `cfg_diamante_factores_x_rango_de_color` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `fecha` DATE NOT NULL,
  `color_desde` VARCHAR(20) NOT NULL,
  `color_hasta` VARCHAR(20) NOT NULL,
  `rango_color_base` VARCHAR(20) NOT NULL,
  `factor` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;



--
-- table structure for table `cfg_diamante_porcentaje_castigo_x_rango_de_pesos`
--

DROP TABLE IF EXISTS `cfg_diamante_porcentaje_castigo_x_rango_de_pesos`;
CREATE TABLE `cfg_diamante_porcentaje_castigo_x_rango_de_pesos` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `fecha` DATE NOT NULL,
  `quilates_desde` DECIMAL(10,2) NOT NULL,
  `quilates_hasta` DECIMAL(10,2) NOT NULL,
  `factor` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;



--
-- table structure for table `cfg_diamante_porcentaje_castigo_x_tipo_corte`
--

DROP TABLE IF EXISTS `cfg_diamante_porcentaje_castigo_x_tipo_corte`;
CREATE TABLE `cfg_diamante_porcentaje_castigo_x_tipo_corte` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `fecha` DATE NOT NULL,
  `corte` VARCHAR(20) NOT NULL,
  `factor` DECIMAL(10,4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


--
-- table structure for table `cfg_diamante_rango_pesos`
--

DROP TABLE IF EXISTS `cfg_diamante_rango_pesos`;
CREATE TABLE `cfg_diamante_rango_pesos` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `fecha` DATE NOT NULL,
  `quilates_desde` DECIMAL(10,4) NOT NULL,
  `quilates_hasta` DECIMAL(10,4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


--
-- table structure for table `cfg_diamante_parametros_quilates`
--

DROP TABLE IF EXISTS `cfg_diamante_parametros_quilates`;
CREATE TABLE `cfg_diamante_parametros_quilates` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `fecha` DATE NOT NULL,
  `quilates_desde` DECIMAL(10,2) NOT NULL,
  `quilates_hasta` DECIMAL(10,2) NOT NULL,
  `quilates_base_desde` DECIMAL(10,2) NOT NULL,
  `quilates_base_hasta` DECIMAL(10,2) NOT NULL,
  `porcentaje` DECIMAL(10,3) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

--
-- table structure for table `cfg_diamante_factor_rapaport`
--

DROP TABLE IF EXISTS `cfg_diamante_factor_rapaport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cfg_diamante_factor_rapaport` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `factor` DECIMAL(10,1) NOT NULL,
  `fecha` DATE DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- fin nuevas tablas para siva diamantes f2




-- TABLAS QUE SE CREAN AUTOMATICO PARA SPRING BATCH ---------------

CREATE TABLE `BATCH_STEP_EXECUTION_SEQ` (
  `ID` bigint(20) NOT NULL,
  `UNIQUE_KEY` char(1) NOT NULL,
  UNIQUE KEY `UNIQUE_KEY_UN` (`UNIQUE_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `BATCH_JOB_EXECUTION_SEQ` (
  `ID` bigint(20) NOT NULL,
  `UNIQUE_KEY` char(1) NOT NULL,
  UNIQUE KEY `UNIQUE_KEY_UN` (`UNIQUE_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `BATCH_JOB_INSTANCE` (
  `JOB_INSTANCE_ID` bigint(20) NOT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  `JOB_NAME` varchar(100) NOT NULL,
  `JOB_KEY` varchar(32) NOT NULL,
  PRIMARY KEY (`JOB_INSTANCE_ID`),
  UNIQUE KEY `JOB_INST_UN` (`JOB_NAME`,`JOB_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `BATCH_JOB_SEQ` (
  `ID` bigint(20) NOT NULL,
  `UNIQUE_KEY` char(1) NOT NULL,
  UNIQUE KEY `UNIQUE_KEY_UN` (`UNIQUE_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `BATCH_JOB_EXECUTION` (
  `JOB_EXECUTION_ID` bigint(20) NOT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  `JOB_INSTANCE_ID` bigint(20) NOT NULL,
  `CREATE_TIME` datetime NOT NULL,
  `START_TIME` datetime DEFAULT NULL,
  `END_TIME` datetime DEFAULT NULL,
  `STATUS` varchar(10) DEFAULT NULL,
  `EXIT_CODE` varchar(2500) DEFAULT NULL,
  `EXIT_MESSAGE` varchar(2500) DEFAULT NULL,
  `LAST_UPDATED` datetime DEFAULT NULL,
  `JOB_CONFIGURATION_LOCATION` varchar(2500) DEFAULT NULL,
  PRIMARY KEY (`JOB_EXECUTION_ID`),
  KEY `JOB_INST_EXEC_FK` (`JOB_INSTANCE_ID`),
  CONSTRAINT `JOB_INST_EXEC_FK` FOREIGN KEY (`JOB_INSTANCE_ID`) REFERENCES `BATCH_JOB_INSTANCE` (`JOB_INSTANCE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `BATCH_JOB_EXECUTION_CONTEXT` (
  `JOB_EXECUTION_ID` bigint(20) NOT NULL,
  `SHORT_CONTEXT` varchar(2500) NOT NULL,
  `SERIALIZED_CONTEXT` text,
  PRIMARY KEY (`JOB_EXECUTION_ID`),
  CONSTRAINT `JOB_EXEC_CTX_FK` FOREIGN KEY (`JOB_EXECUTION_ID`) REFERENCES `BATCH_JOB_EXECUTION` (`JOB_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `BATCH_JOB_EXECUTION_PARAMS` (
  `JOB_EXECUTION_ID` bigint(20) NOT NULL,
  `TYPE_CD` varchar(6) NOT NULL,
  `KEY_NAME` varchar(100) NOT NULL,
  `STRING_VAL` varchar(250) DEFAULT NULL,
  `DATE_VAL` datetime DEFAULT NULL,
  `LONG_VAL` bigint(20) DEFAULT NULL,
  `DOUBLE_VAL` double DEFAULT NULL,
  `IDENTIFYING` char(1) NOT NULL,
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`),
  KEY `JOB_EXEC_PARAMS_FK` (`JOB_EXECUTION_ID`),
  CONSTRAINT `JOB_EXEC_PARAMS_FK` FOREIGN KEY (`JOB_EXECUTION_ID`) REFERENCES `BATCH_JOB_EXECUTION` (`JOB_EXECUTION_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=120 DEFAULT CHARSET=latin1;


CREATE TABLE `BATCH_STEP_EXECUTION` (
  `STEP_EXECUTION_ID` bigint(20) NOT NULL,
  `VERSION` bigint(20) NOT NULL,
  `STEP_NAME` varchar(100) NOT NULL,
  `JOB_EXECUTION_ID` bigint(20) NOT NULL,
  `START_TIME` datetime NOT NULL,
  `END_TIME` datetime DEFAULT NULL,
  `STATUS` varchar(10) DEFAULT NULL,
  `COMMIT_COUNT` bigint(20) DEFAULT NULL,
  `READ_COUNT` bigint(20) DEFAULT NULL,
  `FILTER_COUNT` bigint(20) DEFAULT NULL,
  `WRITE_COUNT` bigint(20) DEFAULT NULL,
  `READ_SKIP_COUNT` bigint(20) DEFAULT NULL,
  `WRITE_SKIP_COUNT` bigint(20) DEFAULT NULL,
  `PROCESS_SKIP_COUNT` bigint(20) DEFAULT NULL,
  `ROLLBACK_COUNT` bigint(20) DEFAULT NULL,
  `EXIT_CODE` varchar(2500) DEFAULT NULL,
  `EXIT_MESSAGE` varchar(2500) DEFAULT NULL,
  `LAST_UPDATED` datetime DEFAULT NULL,
  PRIMARY KEY (`STEP_EXECUTION_ID`),
  KEY `JOB_EXEC_STEP_FK` (`JOB_EXECUTION_ID`),
  CONSTRAINT `JOB_EXEC_STEP_FK` FOREIGN KEY (`JOB_EXECUTION_ID`) REFERENCES `BATCH_JOB_EXECUTION` (`JOB_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `BATCH_STEP_EXECUTION_CONTEXT` (
  `STEP_EXECUTION_ID` bigint(20) NOT NULL,
  `SHORT_CONTEXT` varchar(2500) NOT NULL,
  `SERIALIZED_CONTEXT` text,
  PRIMARY KEY (`STEP_EXECUTION_ID`),
  CONSTRAINT `STEP_EXEC_CTX_FK` FOREIGN KEY (`STEP_EXECUTION_ID`) REFERENCES `BATCH_STEP_EXECUTION` (`STEP_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;








--
-- Utilizado para poblar la BD (mysql) utilizada con los perfiles local y cloud.
--



-- ----------------------------------------------------------------------------------------------------------------------
-- INICIA - DATOS: VALOR COMERCIAL 'METAL'
-- ----------------------------------------------------------------------------------------------------------------------
INSERT INTO cfg_alhaja_listado_valor_comercial_metal (id, ultima_actualizacion)
VALUES (1, '2016-12-20 10:00:00.521');

INSERT INTO cfg_alhaja_valor_comercial_metal (id, metal, calidad, precio, listado)
VALUES (1, 'PT', null, 600.000, 1);
INSERT INTO cfg_alhaja_valor_comercial_metal (id, metal, calidad, precio, listado)
VALUES (2, 'AG', 'CL_999', 8.000, 1);
INSERT INTO cfg_alhaja_valor_comercial_metal (id, metal, calidad, precio, listado)
VALUES (3, 'AG', 'CL_925', 7.400, 1);
INSERT INTO cfg_alhaja_valor_comercial_metal (id, metal, calidad, precio, listado)
VALUES (4, 'AG', 'CL_900', 7.200, 1);
INSERT INTO cfg_alhaja_valor_comercial_metal (id, metal, calidad, precio, listado)
VALUES (5, 'AG', 'CL_720', 5.760, 1);
INSERT INTO cfg_alhaja_valor_comercial_metal (id, metal, calidad, precio, listado)
VALUES (6, 'AG', 'CL_500', 4.000, 1);
INSERT INTO cfg_alhaja_valor_comercial_metal (id, metal, calidad, precio, listado)
VALUES (7, 'PD', null, 50.000, 1);
-- ----------------------------------------------------------------------------------------------------------------------
-- TERMINA - DATOS: VALOR COMERCIAL 'METAL'
-- ----------------------------------------------------------------------------------------------------------------------



-- ----------------------------------------------------------------------------------------------------------------------
-- INICIA - DATOS: VALOR COMERCIAL 'ORO'
-- ----------------------------------------------------------------------------------------------------------------------
INSERT INTO cfg_alhaja_listado_valor_comercial_oro (id, ultima_actualizacion)
VALUES (1, '2016-12-20 10:00:00.521');

INSERT INTO cfg_alhaja_valor_comercial_oro (id, color, calidad, precio, listado)
VALUES (1, 'AU_AM', '8_Q', 260.000, 1);
INSERT INTO cfg_alhaja_valor_comercial_oro (id, color, calidad, precio, listado)
VALUES (2, 'AU_AM', '10_Q', 325.000, 1);
INSERT INTO cfg_alhaja_valor_comercial_oro (id, color, calidad, precio, listado)
VALUES (3, 'AU_AM', '12_Q', 390.000, 1);
INSERT INTO cfg_alhaja_valor_comercial_oro (id, color, calidad, precio, listado)
VALUES (4, 'AU_AM', '14_Q', 455.000, 1);
INSERT INTO cfg_alhaja_valor_comercial_oro (id, color, calidad, precio, listado)
VALUES (5, 'AU_AM', '16_Q', 520.000, 1);
INSERT INTO cfg_alhaja_valor_comercial_oro (id, color, calidad, precio, listado)
VALUES (6, 'AU_AM', '18_Q', 585.000, 1);
INSERT INTO cfg_alhaja_valor_comercial_oro (id, color, calidad, precio, listado)
VALUES (7, 'AU_AM', '21_Q', 632.770, 1);
INSERT INTO cfg_alhaja_valor_comercial_oro (id, color, calidad, precio, listado)
VALUES (8, 'AU_AM', '22_Q', 715.000, 1);
INSERT INTO cfg_alhaja_valor_comercial_oro (id, color, calidad, precio, listado)
VALUES (9, 'AU_AM', '24_Q', 780.000, 1);
INSERT INTO cfg_alhaja_valor_comercial_oro (id, color, calidad, precio, listado)
VALUES (10, 'AU_BL', '8_Q', 260.000, 1);
INSERT INTO cfg_alhaja_valor_comercial_oro (id, color, calidad, precio, listado)
VALUES (11, 'AU_BL', '10_Q', 325.000, 1);
INSERT INTO cfg_alhaja_valor_comercial_oro (id, color, calidad, precio, listado)
VALUES (12, 'AU_BL', '12_Q', 390.000, 1);
INSERT INTO cfg_alhaja_valor_comercial_oro (id, color, calidad, precio, listado)
VALUES (13, 'AU_BL', '14_Q', 455.000, 1);
INSERT INTO cfg_alhaja_valor_comercial_oro (id, color, calidad, precio, listado)
VALUES (14, 'AU_BL', '16_Q', 520.000, 1);
INSERT INTO cfg_alhaja_valor_comercial_oro (id, color, calidad, precio, listado)
VALUES (15, 'AU_BL', '18_Q', 585.000, 1);
INSERT INTO cfg_alhaja_valor_comercial_oro (id, color, calidad, precio, listado)
VALUES (16, 'AU_BL', '21_Q', 632.770, 1);
INSERT INTO cfg_alhaja_valor_comercial_oro (id, color, calidad, precio, listado)
VALUES (17, 'AU_BL', '22_Q', 715.000, 1);
INSERT INTO cfg_alhaja_valor_comercial_oro (id, color, calidad, precio, listado)
VALUES (18, 'AU_BL', '24_Q', 780.000, 1);
-- ----------------------------------------------------------------------------------------------------------------------
-- TERMINA - DATOS: VALOR COMERCIAL 'ORO'
-- ----------------------------------------------------------------------------------------------------------------------




-- ----------------------------------------------------------------------------------------------------------------------
-- INICIA - DATOS: FACTOR VALOR DIAMANTE
-- ----------------------------------------------------------------------------------------------------------------------
INSERT INTO cfg_diamante_factor (id, factor_minimo, factor_medio, factor_maximo, fecha_carga, fecha_listado)
VALUES (1, 1.250, 1.400, 1.550, '2017-05-02 12:56:00.521', '2017-05-02');
-- ----------------------------------------------------------------------------------------------------------------------
-- TERMINA - DATOS: FACTOR VALOR DIAMANTE
-- ----------------------------------------------------------------------------------------------------------------------



-- ----------------------------------------------------------------------------------------------------------------------
-- INICIA - DATOS: MODIFICADOR CERTIFICADO
-- ----------------------------------------------------------------------------------------------------------------------
INSERT INTO cfg_diamante_listado_valor_certificado (id, ultima_actualizacion, fecha_listado)
VALUES (1, '2016-12-20 10:00:00.521', '2016-12-20');

INSERT INTO cfg_diamante_valor_certificado (id, certificado, factor, listado)
VALUES (1, 'GI', 1.100, 1);
INSERT INTO cfg_diamante_valor_certificado (id, certificado, factor, listado)
VALUES (2, 'HR', 1.100, 1);
INSERT INTO cfg_diamante_valor_certificado (id, certificado, factor, listado)
VALUES (3, 'IG', 1.100, 1);
INSERT INTO cfg_diamante_valor_certificado (id, certificado, factor, listado)
VALUES (4, 'AJ', 1.100, 1);
-- ----------------------------------------------------------------------------------------------------------------------
-- TERMINA - DATOS: MODIFICADOR CERTIFICADO
-- ----------------------------------------------------------------------------------------------------------------------



-- ----------------------------------------------------------------------------------------------------------------------
-- INICIA - DATOS: MODIFICADORES DE FACTORES DE ALHAJAS.
-- ----------------------------------------------------------------------------------------------------------------------
INSERT INTO cfg_alhaja_listado_factor (id, ultima_actualizacion, fecha_listado)
VALUES (1, '2016-12-20 10:00:00.521', '2016-12-20');

INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento, limite_inferior, limite_superior, ultima_actualizacion, listado)
VALUES (1, 'AU', '8_Q', 'F1', 1.10, 0.00, -4.00, -4.00, '2016-12-20 10:00:00.521', 1);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento, limite_inferior, limite_superior, ultima_actualizacion, listado)
VALUES (2, 'AU', '8_Q', 'F2', 1.25, 0.00, 0.00, 10.00, '2016-12-20 10:00:00.521', 1);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento, limite_inferior, limite_superior, ultima_actualizacion, listado)
VALUES (3, 'AU', '8_Q', 'F3', 1.35, 5.00, 10.00, 20.00, '2016-12-20 10:00:00.521', 1);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento, limite_inferior, limite_superior, ultima_actualizacion, listado)
VALUES (4, 'AU', '8_Q', 'F4', 1.40, 10.00, 20.00, 30.00, '2016-12-20 10:00:00.521', 1);

INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento, limite_inferior, limite_superior, ultima_actualizacion, listado)
VALUES (5, 'AU', '10_Q', 'F1', 1.10, 0.00, -4.00, -4.00, '2016-12-20 10:00:00.521', 1);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento, limite_inferior, limite_superior, ultima_actualizacion, listado)
VALUES (6, 'AU', '10_Q', 'F2', 1.25, 0.00, 0.00, 10.00, '2016-12-20 10:00:00.521', 1);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento, limite_inferior, limite_superior, ultima_actualizacion, listado)
VALUES (7, 'AU', '10_Q', 'F3', 1.35, 5.00, 10.00, 20.00, '2016-12-20 10:00:00.521', 1);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento, limite_inferior, limite_superior, ultima_actualizacion, listado)
VALUES (8, 'AU', '10_Q', 'F4', 1.40, 10.00, 20.00, 30.00, '2016-12-20 10:00:00.521', 1);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento, limite_inferior, limite_superior, ultima_actualizacion, listado)
VALUES (9, 'AU', '10_Q', 'F5', 1.50, 10.00, 30.00, 45.00, '2016-12-20 10:00:00.521', 1);

INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento, limite_inferior, limite_superior, ultima_actualizacion, listado)
VALUES (10, 'AU', '12_Q', 'F1', 1.10, 0.00, -4.00, -4.00, '2016-12-20 10:00:00.521', 1);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento, limite_inferior, limite_superior, ultima_actualizacion, listado)
VALUES (11, 'AU', '12_Q', 'F2', 1.25, 0.00, 0.00, 10.00, '2016-12-20 10:00:00.521', 1);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento, limite_inferior, limite_superior, ultima_actualizacion, listado)
VALUES (12, 'AU', '12_Q', 'F3', 1.35, 5.00, 10.00, 20.00, '2016-12-20 10:00:00.521', 1);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento, limite_inferior, limite_superior, ultima_actualizacion, listado)
VALUES (13, 'AU', '12_Q', 'F4', 1.40, 10.00, 20.00, 30.00, '2016-12-20 10:00:00.521', 1);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento, limite_inferior, limite_superior, ultima_actualizacion, listado)
VALUES (14, 'AU', '12_Q', 'F5', 1.50, 10.00, 30.00, 45.00, '2016-12-20 10:00:00.521', 1);

INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento, limite_inferior, limite_superior, ultima_actualizacion, listado)
VALUES (15, 'AU', '14_Q', 'F1', 1.10, 0.00, -4.00, -4.00, '2016-12-20 10:00:00.521', 1);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento, limite_inferior, limite_superior, ultima_actualizacion, listado)
VALUES (16, 'AU', '14_Q', 'F2', 1.25, 0.00, 0.00, 10.00, '2016-12-20 10:00:00.521', 1);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento, limite_inferior, limite_superior, ultima_actualizacion, listado)
VALUES (17, 'AU', '14_Q', 'F3', 1.35, 5.00, 10.00, 20.00, '2016-12-20 10:00:00.521', 1);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento, limite_inferior, limite_superior, ultima_actualizacion, listado)
VALUES (18, 'AU', '14_Q', 'F4', 1.40, 10.00, 20.00, 30.00, '2016-12-20 10:00:00.521', 1);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento, limite_inferior, limite_superior, ultima_actualizacion, listado)
VALUES (19, 'AU', '14_Q', 'F5', 1.50, 10.00, 30.00, 45.00, '2016-12-20 10:00:00.521', 1);

INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento, limite_inferior, limite_superior, ultima_actualizacion, listado)
VALUES (20, 'AU', '16_Q', 'F1', 1.10, 0.00, -4.00, -4.00, '2016-12-20 10:00:00.521', 1);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento, limite_inferior, limite_superior, ultima_actualizacion, listado)
VALUES (21, 'AU', '16_Q', 'F2', 1.25, 0.00, 0.00, 10.00, '2016-12-20 10:00:00.521', 1);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento, limite_inferior, limite_superior, ultima_actualizacion, listado)
VALUES (22, 'AU', '16_Q', 'F3', 1.35, 5.00, 10.00, 20.00, '2016-12-20 10:00:00.521', 1);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento, limite_inferior, limite_superior, ultima_actualizacion, listado)
VALUES (23, 'AU', '16_Q', 'F4', 1.40, 10.00, 20.00, 30.00, '2016-12-20 10:00:00.521', 1);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento, limite_inferior, limite_superior, ultima_actualizacion, listado)
VALUES (24, 'AU', '16_Q', 'F5', 1.50, 10.00, 30.00, 45.00, '2016-12-20 10:00:00.521', 1);

INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento, limite_inferior, limite_superior, ultima_actualizacion, listado)
VALUES (25, 'AU', '18_Q', 'F1', 1.10, 0.00, -4.00, -4.00, '2016-12-20 10:00:00.521', 1);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento, limite_inferior, limite_superior, ultima_actualizacion, listado)
VALUES (26, 'AU', '18_Q', 'F2', 1.25, 0.00, 0.00, 10.00, '2016-12-20 10:00:00.521', 1);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento, limite_inferior, limite_superior, ultima_actualizacion, listado)
VALUES (27, 'AU', '18_Q', 'F3', 1.35, 5.00, 10.00, 20.00, '2016-12-20 10:00:00.521', 1);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento, limite_inferior, limite_superior, ultima_actualizacion, listado)
VALUES (28, 'AU', '18_Q', 'F4', 1.40, 10.00, 20.00, 30.00, '2016-12-20 10:00:00.521', 1);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento, limite_inferior, limite_superior, ultima_actualizacion, listado)
VALUES (29, 'AU', '18_Q', 'F5', 1.50, 10.00, 30.00, 45.00, '2016-12-20 10:00:00.521', 1);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento, limite_inferior, limite_superior, ultima_actualizacion, listado)
VALUES (30, 'AU', '18_Q', 'F6', 1.60, 30.00, 65.00, 85.00, '2016-12-20 10:00:00.521', 1);

INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento, limite_inferior, limite_superior, ultima_actualizacion, listado)
VALUES (31, 'AU', '22_Q', 'F1', 1.10, 0.00, -4.00, -4.00, '2016-12-20 10:00:00.521', 1);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento, limite_inferior, limite_superior, ultima_actualizacion, listado)
VALUES (32, 'AU', '22_Q', 'F2', 1.25, 0.00, 0.00, 10.00, '2016-12-20 10:00:00.521', 1);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento, limite_inferior, limite_superior, ultima_actualizacion, listado)
VALUES (33, 'AU', '22_Q', 'F3', 1.35, 5.00, 10.00, 20.00, '2016-12-20 10:00:00.521', 1);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento, limite_inferior, limite_superior, ultima_actualizacion, listado)
VALUES (34, 'AU', '22_Q', 'F4', 1.40, 10.00, 20.00, 30.00, '2016-12-20 10:00:00.521', 1);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento, limite_inferior, limite_superior, ultima_actualizacion, listado)
VALUES (35, 'AU', '22_Q', 'F5', 1.50, 10.00, 30.00, 45.00, '2016-12-20 10:00:00.521', 1);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento, limite_inferior, limite_superior, ultima_actualizacion, listado)
VALUES (36, 'AU', '22_Q', 'F6', 1.60, 30.00, 65.00, 85.00, '2016-12-20 10:00:00.521', 1);

INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento, limite_inferior, limite_superior, ultima_actualizacion, listado)
VALUES (37, 'AU', '24_Q', 'F1', 1.10, 0.00, -4.00, -4.00, '2016-12-20 10:00:00.521', 1);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento, limite_inferior, limite_superior, ultima_actualizacion, listado)
VALUES (38, 'AU', '24_Q', 'F2', 1.25, 0.00, 0.00, 10.00, '2016-12-20 10:00:00.521', 1);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento, limite_inferior, limite_superior, ultima_actualizacion, listado)
VALUES (39, 'AU', '24_Q', 'F3', 1.35, 5.00, 10.00, 20.00, '2016-12-20 10:00:00.521', 1);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento, limite_inferior, limite_superior, ultima_actualizacion, listado)
VALUES (40, 'AU', '24_Q', 'F4', 1.40, 10.00, 20.00, 30.00, '2016-12-20 10:00:00.521', 1);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento, limite_inferior, limite_superior, ultima_actualizacion, listado)
VALUES (41, 'AU', '24_Q', 'F5', 1.50, 10.00, 30.00, 45.00, '2016-12-20 10:00:00.521', 1);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento, limite_inferior, limite_superior, ultima_actualizacion, listado)
VALUES (42, 'AU', '24_Q', 'F6', 1.60, 30.00, 65.00, 85.00, '2016-12-20 10:00:00.521', 1);

INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento, limite_inferior, limite_superior, ultima_actualizacion, listado)
VALUES (43, 'PT', NULL, 'F1', 1.00, 0.00, 0.00, 0.00, '2016-12-20 10:00:00.521', 1);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento, limite_inferior, limite_superior, ultima_actualizacion, listado)
VALUES (44, 'PT', NULL, 'DE', 1.10, 0.00, 0.00, 0.00, '2016-12-20 10:00:00.521', 1);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento, limite_inferior, limite_superior, ultima_actualizacion, listado)
VALUES (45, 'PT', NULL, 'MC', 1.20, 0.00, 0.00, 0.00, '2016-12-20 10:00:00.521', 1);

INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento, limite_inferior, limite_superior, ultima_actualizacion, listado)
VALUES (46, 'PD', NULL, 'F1', 1.00, 0.00, 0.00, 0.00, '2016-12-20 10:00:00.521', 1);

INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento, limite_inferior, limite_superior, ultima_actualizacion, listado)
VALUES (47, 'AG', 'CL_999', 'F1', 1.00, 0.00, 0.00, 0.00, '2016-12-20 10:00:00.521', 1);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento, limite_inferior, limite_superior, ultima_actualizacion, listado)
VALUES (48, 'AG', 'CL_925', 'F1', 1.00, 0.00, 0.00, 0.00, '2016-12-20 10:00:00.521', 1);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento, limite_inferior, limite_superior, ultima_actualizacion, listado)
VALUES (49, 'AG', 'CL_900', 'F1', 1.00, 0.00, 0.00, 0.00, '2016-12-20 10:00:00.521', 1);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento, limite_inferior, limite_superior, ultima_actualizacion, listado)
VALUES (50, 'AG', 'CL_720', 'F1', 1.00, 0.00, 0.00, 0.00, '2016-12-20 10:00:00.521', 1);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento, limite_inferior, limite_superior, ultima_actualizacion, listado)
VALUES (51, 'AG', 'CL_500', 'F1', 1.00, 0.00, 0.00, 0.00, '2016-12-20 10:00:00.521', 1);
-- ----------------------------------------------------------------------------------------------------------------------
-- TERMINA - DATOS: MODIFICADORES DE FACTORES DE ALHAJAS.
-- ----------------------------------------------------------------------------------------------------------------------

-- Incio configuración de tablas de referencia  Siva Diamantes F2

/*
cfg_diamante_factores_x_rango_de_color
*/
INSERT INTO `cfg_diamante_factores_x_rango_de_color` (`ID`,`FECHA`,`COLOR_DESDE`,`COLOR_HASTA`,`RANGO_COLOR_BASE`,`FACTOR`) VALUES (1,'2018-08-28','O','R','M',0.80);
INSERT INTO `cfg_diamante_factores_x_rango_de_color` (`ID`,`FECHA`,`COLOR_DESDE`,`COLOR_HASTA`,`RANGO_COLOR_BASE`,`FACTOR`) VALUES (2,'2018-08-28','S','Z','M',0.65);

/*
cfg_diamante_parametros_quilates
*/
INSERT INTO `cfg_diamante_parametros_quilates` (`ID`,`FECHA`,`QUILATES_DESDE`,`QUILATES_HASTA`,`QUILATES_BASE_DESDE`,`QUILATES_BASE_HASTA`,`PORCENTAJE`) VALUES (1,'2018-08-28',6.00,6.99,5.00,5.99,1.100);
INSERT INTO `cfg_diamante_parametros_quilates` (`ID`,`FECHA`,`QUILATES_DESDE`,`QUILATES_HASTA`,`QUILATES_BASE_DESDE`,`QUILATES_BASE_HASTA`,`PORCENTAJE`) VALUES (6,'2018-08-28',7.00,7.99,5.00,5.99,1.150);
INSERT INTO `cfg_diamante_parametros_quilates` (`ID`,`FECHA`,`QUILATES_DESDE`,`QUILATES_HASTA`,`QUILATES_BASE_DESDE`,`QUILATES_BASE_HASTA`,`PORCENTAJE`) VALUES (7,'2018-08-28',8.00,8.99,5.00,5.99,1.200);
INSERT INTO `cfg_diamante_parametros_quilates` (`ID`,`FECHA`,`QUILATES_DESDE`,`QUILATES_HASTA`,`QUILATES_BASE_DESDE`,`QUILATES_BASE_HASTA`,`PORCENTAJE`) VALUES (8,'2018-08-28',9.00,9.99,5.00,5.99,1.250);
INSERT INTO `cfg_diamante_parametros_quilates` (`ID`,`FECHA`,`QUILATES_DESDE`,`QUILATES_HASTA`,`QUILATES_BASE_DESDE`,`QUILATES_BASE_HASTA`,`PORCENTAJE`) VALUES (9,'2018-08-28',11.00,20.00,10.00,10.99,1.075);

/*
cfg_diamante_porcentaje_castigo_x_rango_de_pesos
*/
INSERT INTO `cfg_diamante_porcentaje_castigo_x_rango_de_pesos` (`ID`,`FECHA`,`QUILATES_DESDE`,`QUILATES_HASTA`,`FACTOR`) VALUES (1,'2018-08-28',0.01,0.49,0.70);
INSERT INTO `cfg_diamante_porcentaje_castigo_x_rango_de_pesos` (`ID`,`FECHA`,`QUILATES_DESDE`,`QUILATES_HASTA`,`FACTOR`) VALUES (2,'2018-08-28',0.50,0.89,0.60);
INSERT INTO `cfg_diamante_porcentaje_castigo_x_rango_de_pesos` (`ID`,`FECHA`,`QUILATES_DESDE`,`QUILATES_HASTA`,`FACTOR`) VALUES (3,'2018-08-28',0.90,1.49,0.50);
INSERT INTO `cfg_diamante_porcentaje_castigo_x_rango_de_pesos` (`ID`,`FECHA`,`QUILATES_DESDE`,`QUILATES_HASTA`,`FACTOR`) VALUES (4,'2018-08-28',1.50,1.99,0.45);
INSERT INTO `cfg_diamante_porcentaje_castigo_x_rango_de_pesos` (`ID`,`FECHA`,`QUILATES_DESDE`,`QUILATES_HASTA`,`FACTOR`) VALUES (5,'2018-08-28',2.00,100.00,0.35);

/*
cfg_diamante_factor_rapaport
*/
INSERT INTO `cfg_diamante_factor_rapaport` (`ID`,`FACTOR`,`FECHA`) VALUES (1,100,'2018-08-28');

/*
cfg_diamante_rango_pesos
*/
-- INSERT INTO `cfg_diamante_rango_pesos` (`id`,`fecha`,`quilates_desde`,`quilates_hasta`) VALUES (1,'2018-09-05',0.0100,0.0300);
-- INSERT INTO `cfg_diamante_rango_pesos` (`id`,`fecha`,`quilates_desde`,`quilates_hasta`) VALUES (8,'2018-09-05',0.0400,0.0700);
-- INSERT INTO `cfg_diamante_rango_pesos` (`id`,`fecha`,`quilates_desde`,`quilates_hasta`) VALUES (15,'2018-09-05',0.0800,0.1400);
INSERT INTO cfg_diamante_rango_pesos(fecha, quilates_desde, quilates_hasta) values('2018-09-05',0.18,0.22);
INSERT INTO cfg_diamante_rango_pesos(fecha, quilates_desde, quilates_hasta) values('2018-09-05',0.23,0.29);
INSERT INTO cfg_diamante_rango_pesos(fecha, quilates_desde, quilates_hasta) values('2018-09-05',0.30,0.39);
INSERT INTO cfg_diamante_rango_pesos(fecha, quilates_desde, quilates_hasta) values('2018-09-05',0.40,0.49);
INSERT INTO cfg_diamante_rango_pesos(fecha, quilates_desde, quilates_hasta) values('2018-09-05',0.50,0.69);
INSERT INTO cfg_diamante_rango_pesos(fecha, quilates_desde, quilates_hasta) values('2018-09-05',0.70,0.89);
INSERT INTO cfg_diamante_rango_pesos(fecha, quilates_desde, quilates_hasta) values('2018-09-05',0.90,0.99);
INSERT INTO cfg_diamante_rango_pesos(fecha, quilates_desde, quilates_hasta) values('2018-09-05',1.00,1.49);
INSERT INTO cfg_diamante_rango_pesos(fecha, quilates_desde, quilates_hasta) values('2018-09-05',1.50,1.99);
INSERT INTO cfg_diamante_rango_pesos(fecha, quilates_desde, quilates_hasta) values('2018-09-05',5.00,5.99);
INSERT INTO cfg_diamante_rango_pesos(fecha, quilates_desde, quilates_hasta) values('2018-09-05',6.00,6.99);
INSERT INTO cfg_diamante_rango_pesos(fecha, quilates_desde, quilates_hasta) values('2018-09-05',10.00,10.99);
INSERT INTO cfg_diamante_rango_pesos(fecha, quilates_desde, quilates_hasta) values('2018-09-05',11.00,20.00);
INSERT INTO cfg_diamante_rango_pesos(fecha, quilates_desde, quilates_hasta) values('2018-09-05',2.00,2.99);
INSERT INTO cfg_diamante_rango_pesos(fecha, quilates_desde, quilates_hasta) values('2018-09-05',3.00,3.99);
INSERT INTO cfg_diamante_rango_pesos(fecha, quilates_desde, quilates_hasta) values('2018-09-05',4.00,4.99);

/*
cfg_diamante_porcentaje_castigo_x_tipo_corte
*/
-- Se estandarizan los nombres segun el catalogo de subcortes
INSERT INTO `cfg_diamante_porcentaje_castigo_x_tipo_corte` (`id`,`fecha`,`corte`,`factor`) VALUES (1,'2018-09-05','Brillante Redondo',1.0000); 
INSERT INTO `cfg_diamante_porcentaje_castigo_x_tipo_corte` (`id`,`fecha`,`corte`,`factor`) VALUES (2,'2018-09-05','Acojinado',0.9000); 
INSERT INTO `cfg_diamante_porcentaje_castigo_x_tipo_corte` (`id`,`fecha`,`corte`,`factor`) VALUES (3,'2018-09-05','Pera',1.0000);
INSERT INTO `cfg_diamante_porcentaje_castigo_x_tipo_corte` (`id`,`fecha`,`corte`,`factor`) VALUES (4,'2018-09-05','Oval',0.8300);
INSERT INTO `cfg_diamante_porcentaje_castigo_x_tipo_corte` (`id`,`fecha`,`corte`,`factor`) VALUES (5,'2018-09-05','Corazón',0.8200);
INSERT INTO `cfg_diamante_porcentaje_castigo_x_tipo_corte` (`id`,`fecha`,`corte`,`factor`) VALUES (6,'2018-09-05','Marquesa',0.8200);
INSERT INTO `cfg_diamante_porcentaje_castigo_x_tipo_corte` (`id`,`fecha`,`corte`,`factor`) VALUES (7,'2018-09-05','Flanders',0.8000);
INSERT INTO `cfg_diamante_porcentaje_castigo_x_tipo_corte` (`id`,`fecha`,`corte`,`factor`) VALUES (8,'2018-09-05','Princesa',0.8000);
INSERT INTO `cfg_diamante_porcentaje_castigo_x_tipo_corte` (`id`,`fecha`,`corte`,`factor`) VALUES (9,'2018-09-05','Radiante',0.7600); 
INSERT INTO `cfg_diamante_porcentaje_castigo_x_tipo_corte` (`id`,`fecha`,`corte`,`factor`) VALUES (10,'2018-09-05','Asscher',0.7500);
INSERT INTO `cfg_diamante_porcentaje_castigo_x_tipo_corte` (`id`,`fecha`,`corte`,`factor`) VALUES (11,'2018-09-05','Trapecio',0.7500);
INSERT INTO `cfg_diamante_porcentaje_castigo_x_tipo_corte` (`id`,`fecha`,`corte`,`factor`) VALUES (12,'2018-09-05','Trillante',0.7500); 
INSERT INTO `cfg_diamante_porcentaje_castigo_x_tipo_corte` (`id`,`fecha`,`corte`,`factor`) VALUES (13,'2018-09-05','Baguette',0.7000);
INSERT INTO `cfg_diamante_porcentaje_castigo_x_tipo_corte` (`id`,`fecha`,`corte`,`factor`) VALUES (14,'2018-09-05','Esmeralda',0.7000);
INSERT INTO `cfg_diamante_porcentaje_castigo_x_tipo_corte` (`id`,`fecha`,`corte`,`factor`) VALUES (15,'2018-09-05','Antiguo',0.6000);
INSERT INTO `cfg_diamante_porcentaje_castigo_x_tipo_corte` (`id`,`fecha`,`corte`,`factor`) VALUES (16,'2018-09-05','8x8',0.6000);
INSERT INTO `cfg_diamante_porcentaje_castigo_x_tipo_corte` (`id`,`fecha`,`corte`,`factor`) VALUES (17,'2018-09-05','Otro',0.6000);

-- Fin configuración de tablas de referencia

-- Se actualiza el catálogo de incremento
UPDATE cfg_factor_alhaja SET limite_inferior=5.00, limite_superior=20.00 WHERE id in (2,6,11,16,21,26,32,38);
UPDATE cfg_factor_alhaja SET limite_inferior=10.00, limite_superior=15.00 WHERE id in (3,7,12,17,22,27,33,39);
UPDATE cfg_factor_alhaja SET limite_inferior=25.00, limite_superior=55.00 WHERE id in (9,14,19,24,29,35,41);
UPDATE cfg_factor_alhaja SET limite_inferior=70.00, limite_superior=110.00 WHERE id in (30,36,42);
