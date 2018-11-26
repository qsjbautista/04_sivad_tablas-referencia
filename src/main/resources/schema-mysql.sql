
DROP TABLE IF EXISTS journal_entity_event;
DROP TABLE IF EXISTS journal_custom_event;
DROP TABLE IF EXISTS journal_event_data;
DROP TABLE IF EXISTS journal_event;

DROP TABLE IF EXISTS cfg_diamante_factor;
DROP TABLE IF EXISTS cfg_factor_alhaja;
DROP TABLE IF EXISTS cfg_alhaja_listado_factor;
DROP TABLE IF EXISTS hist_cfg_factor_alhaja;
DROP TABLE IF EXISTS hist_cfg_alhaja_listado_factor;
DROP TABLE IF EXISTS cfg_diamante_valor_certificado;
DROP TABLE IF EXISTS cfg_diamante_listado_valor_certificado;
DROP TABLE IF EXISTS hist_cfg_diamante_valor_certificado;
DROP TABLE IF EXISTS hist_cfg_diamante_listado_valor_certificado;
DROP TABLE IF EXISTS hist_cfg_diamante_valor_comercial;
DROP TABLE IF EXISTS hist_cfg_diamante_listado_valor_comercial;
DROP TABLE IF EXISTS cfg_diamante_valor_comercial;
DROP TABLE IF EXISTS tmp_diamante_valor_comercial;
DROP TABLE IF EXISTS cfg_diamante_listado_valor_comercial;
DROP TABLE IF EXISTS hist_cfg_alhaja_valor_comercial_oro;
DROP TABLE IF EXISTS hist_cfg_alhaja_listado_valor_comercial_oro;
DROP TABLE IF EXISTS cfg_alhaja_valor_comercial_oro;
DROP TABLE IF EXISTS cfg_alhaja_listado_valor_comercial_oro;
DROP TABLE IF EXISTS hist_cfg_alhaja_valor_comercial_metal;
DROP TABLE IF EXISTS hist_cfg_alhaja_listado_valor_comercial_metal;
DROP TABLE IF EXISTS cfg_alhaja_valor_comercial_metal;
DROP TABLE IF EXISTS cfg_alhaja_listado_valor_comercial_metal;
DROP TABLE IF EXISTS `cfg_diamante_factores_x_rango_de_color`;
DROP TABLE IF EXISTS `cfg_diamante_porcentaje_castigo_x_rango_de_pesos`;
DROP TABLE IF EXISTS `cfg_diamante_porcentaje_castigo_x_tipo_corte`;
DROP TABLE IF EXISTS `cfg_diamante_rango_pesos`;
DROP TABLE IF EXISTS `cfg_diamante_parametros_quilates`;
DROP TABLE IF EXISTS `cfg_diamante_factor_rapaport`;

DROP TABLE IF EXISTS `BATCH_STEP_EXECUTION_SEQ`;
DROP TABLE IF EXISTS `BATCH_JOB_EXECUTION_SEQ`;
DROP TABLE IF EXISTS `BATCH_JOB_SEQ`;
DROP TABLE IF EXISTS `BATCH_JOB_EXECUTION_CONTEXT`;
DROP TABLE IF EXISTS `BATCH_JOB_EXECUTION_PARAMS`;
DROP TABLE IF EXISTS `BATCH_STEP_EXECUTION_CONTEXT`;
DROP TABLE IF EXISTS `BATCH_STEP_EXECUTION`;
DROP TABLE IF EXISTS `BATCH_JOB_EXECUTION`;
DROP TABLE IF EXISTS `BATCH_JOB_INSTANCE`;


CREATE TABLE journal_entity_event
(
    id BIGINT NOT NULL,
    class VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE journal_custom_event
(
    id BIGINT NOT NULL,
    comment VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE journal_event_data
(
    id BIGINT AUTO_INCREMENT NOT NULL,
    event BIGINT NOT NULL,
    property VARCHAR(255) NOT NULL,
    value VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE journal_event
(
    id BIGINT AUTO_INCREMENT NOT NULL,
    principal VARCHAR(50) NOT NULL,
    DATE TIMESTAMP,
    type VARCHAR(255),
    PRIMARY KEY (id)
);

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

CREATE TABLE cfg_factor_alhaja
(
    id BIGINT AUTO_INCREMENT NOT NULL,
    metal VARCHAR(20) NOT NULL,
    calidad VARCHAR(20),
    rango VARCHAR(20) NOT NULL,
    factor DECIMAL(8, 2) NOT NULL,
	desplazamiento_limite_inferior DECIMAL(8, 2) NOT NULL,
    desplazamiento_limite_superior DECIMAL(8, 2) NOT NULL,
	desplazamiento_incremento INT(11) NOT NULL,
    limite_inferior DECIMAL(8, 2) NOT NULL,
    limite_superior DECIMAL(8, 2) NOT NULL,
	incremento INT(11) NOT NULL,
    ultima_actualizacion TIMESTAMP NOT NULL,
    listado BIGINT,
    PRIMARY KEY (id)
);

CREATE TABLE cfg_alhaja_listado_factor
(
    id BIGINT AUTO_INCREMENT NOT NULL,
    ultima_actualizacion TIMESTAMP NOT NULL,
    fecha_listado DATE NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE hist_cfg_factor_alhaja
(
    id BIGINT AUTO_INCREMENT NOT NULL,
    metal VARCHAR(20) NOT NULL,
    calidad VARCHAR(20),
    rango VARCHAR(20) NOT NULL,
    factor DECIMAL(8, 2) NOT NULL,
    desplazamiento_limite_inferior DECIMAL(8, 2) NOT NULL,
    desplazamiento_limite_superior DECIMAL(8, 2) NOT NULL,
	desplazamiento_incremento INT(11) NOT NULL,
    limite_inferior DECIMAL(8, 2) NOT NULL,
    limite_superior DECIMAL(8, 2) NOT NULL,
	incremento INT(11) NOT NULL,
    ultima_actualizacion TIMESTAMP NOT NULL,
    listado BIGINT,
    PRIMARY KEY (id)
);

CREATE TABLE hist_cfg_alhaja_listado_factor
(
    id BIGINT AUTO_INCREMENT NOT NULL,
    ultima_actualizacion TIMESTAMP NOT NULL,
    fecha_listado DATE NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE cfg_diamante_valor_certificado
(
    id BIGINT AUTO_INCREMENT NOT NULL,
    certificado VARCHAR(20) NOT NULL,
    factor DECIMAL(10, 3) NOT NULL,
    listado BIGINT,
    PRIMARY KEY (id)
);

CREATE TABLE cfg_diamante_listado_valor_certificado
(
    id BIGINT AUTO_INCREMENT NOT NULL,
    ultima_actualizacion TIMESTAMP NOT NULL,
    fecha_listado DATE NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE hist_cfg_diamante_valor_certificado
(
    id BIGINT AUTO_INCREMENT NOT NULL,
    certificado VARCHAR(20) NOT NULL,
    factor DECIMAL(10, 3) NOT NULL,
    listado BIGINT,
    PRIMARY KEY (id)
);

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

CREATE TABLE hist_cfg_alhaja_valor_comercial_oro
(
    id BIGINT AUTO_INCREMENT NOT NULL,
    color VARCHAR(20) NOT NULL,
    calidad VARCHAR(20) NOT NULL,
    precio DECIMAL(10, 3) NOT NULL,
    listado BIGINT,
    PRIMARY KEY (id)
);

CREATE TABLE hist_cfg_alhaja_listado_valor_comercial_oro
(
    id BIGINT AUTO_INCREMENT NOT NULL,
    ultima_actualizacion TIMESTAMP NOT NULL,
    PRIMARY KEY (id)
);

--
-- vigentes
--

CREATE TABLE cfg_alhaja_valor_comercial_oro
(
    id BIGINT AUTO_INCREMENT NOT NULL,
    color VARCHAR(20) NOT NULL,
    calidad VARCHAR(20) NOT NULL,
    precio DECIMAL(10, 3) NOT NULL,
    listado BIGINT,
    PRIMARY KEY (id)
);

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

CREATE TABLE hist_cfg_alhaja_valor_comercial_metal
(
    id BIGINT AUTO_INCREMENT NOT NULL,
    metal VARCHAR(20) NOT NULL,
    calidad VARCHAR(20),
    precio DECIMAL(10, 3) NOT NULL,
    listado BIGINT,
    PRIMARY KEY (id)
);

CREATE TABLE hist_cfg_alhaja_listado_valor_comercial_metal
(
    id BIGINT AUTO_INCREMENT NOT NULL,
    ultima_actualizacion TIMESTAMP NOT NULL,
    PRIMARY KEY (id)
);

--
-- vigentes
--

CREATE TABLE cfg_alhaja_valor_comercial_metal
(
    id BIGINT AUTO_INCREMENT NOT NULL,
    metal VARCHAR(20) NOT NULL,
    calidad VARCHAR(20),
    precio DECIMAL(10, 3) NOT NULL,
    listado BIGINT,
    PRIMARY KEY (id)
);

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

CREATE TABLE `cfg_diamante_factores_x_rango_de_color` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `fecha` TIMESTAMP NOT NULL,
  `color_desde` VARCHAR(20) NOT NULL,
  `color_hasta` VARCHAR(20) NOT NULL,
  `rango_color_base` VARCHAR(20) NOT NULL,
  `factor` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;



--
-- table structure for table `cfg_diamante_porcentaje_castigo_x_rango_de_pesos`
--

CREATE TABLE `cfg_diamante_porcentaje_castigo_x_rango_de_pesos` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `fecha` TIMESTAMP NOT NULL,
  `quilates_desde` DECIMAL(10,2) NOT NULL,
  `quilates_hasta` DECIMAL(10,2) NOT NULL,
  `factor` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;



--
-- table structure for table `cfg_diamante_porcentaje_castigo_x_tipo_corte`
--

CREATE TABLE `cfg_diamante_porcentaje_castigo_x_tipo_corte` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `fecha` TIMESTAMP NOT NULL,
  `corte` VARCHAR(20) NOT NULL,
  `factor` DECIMAL(10,4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


--
-- table structure for table `cfg_diamante_rango_pesos`
--

CREATE TABLE `cfg_diamante_rango_pesos` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `fecha` TIMESTAMP NOT NULL,
  `quilates_desde` DECIMAL(10,4) NOT NULL,
  `quilates_hasta` DECIMAL(10,4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


--
-- table structure for table `cfg_diamante_parametros_quilates`
--

CREATE TABLE `cfg_diamante_parametros_quilates` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `fecha` TIMESTAMP NOT NULL,
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

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cfg_diamante_factor_rapaport` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `factor` DECIMAL(10,1) NOT NULL,
  `fecha` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
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

INSERT INTO BATCH_STEP_EXECUTION_SEQ values(0,1);
INSERT INTO BATCH_JOB_EXECUTION_SEQ values(0,1);
INSERT INTO BATCH_JOB_SEQ values(0,1);

-- GRANT SELECT ON mysql.proc TO 'tablas_referencia_usr'@'%'
