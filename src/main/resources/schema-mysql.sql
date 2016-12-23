
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
    METAL VARCHAR(5) NOT NULL,
    CALIDAD VARCHAR(5),
    RANGO VARCHAR(5) NOT NULL,
    FACTOR DECIMAL(10, 3) NOT NULL,
    DESPLAZAMIENTO DECIMAL(10, 3) NOT NULL,
    LIMITE_INFERIOR DECIMAL(10, 3) NOT NULL,
    LIMITE_SUPERIOR DECIMAL(10, 3) NOT NULL,
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
    METAL VARCHAR(5) NOT NULL,
    CALIDAD VARCHAR(5),
    RANGO VARCHAR(5) NOT NULL,
    FACTOR DECIMAL(10, 3) NOT NULL,
    DESPLAZAMIENTO DECIMAL(10, 3) NOT NULL,
    LIMITE_INFERIOR DECIMAL(10, 3) NOT NULL,
    LIMITE_SUPERIOR DECIMAL(10, 3) NOT NULL,
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
    CERTIFICADO VARCHAR(80) NOT NULL,
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
    CERTIFICADO VARCHAR(80) NOT NULL,
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

------------------------------------------------------------------------------------------------------------------------
-- INICIA - TABLAS: VALOR COMERCIAL 'DIAMANTE'
------------------------------------------------------------------------------------------------------------------------

--
-- HISTÓRICOS
--

DROP TABLE IF EXISTS HIST_CFG_DIAMANTE_VALOR_COMERCIAL;
CREATE TABLE HIST_CFG_DIAMANTE_VALOR_COMERCIAL
(
    ID BIGINT AUTO_INCREMENT NOT NULL,
    CORTE VARCHAR(50) NOT NULL,
    COLOR CHAR(1) NOT NULL,
    CLARIDAD VARCHAR(4) NOT NULL,
    TAMANIO_INFERIOR DECIMAL(6, 2) NOT NULL,
    TAMANIO_SUPERIOR DECIMAL(6, 2) NOT NULL,
    PRECIO DECIMAL(10, 4) NOT NULL,
    LISTADO BIGINT,
    PRIMARY KEY (ID)
);

DROP TABLE IF EXISTS HIST_CFG_DIAMANTE_LISTADO_VALOR_COMERCIAL;
CREATE TABLE HIST_CFG_DIAMANTE_LISTADO_VALOR_COMERCIAL
(
    ID BIGINT AUTO_INCREMENT NOT NULL,
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
    CORTE VARCHAR(50) NOT NULL,
    COLOR CHAR(1) NOT NULL,
    CLARIDAD VARCHAR(4) NOT NULL,
    TAMANIO_INFERIOR DECIMAL(6, 2) NOT NULL,
    TAMANIO_SUPERIOR DECIMAL(6, 2) NOT NULL,
    PRECIO DECIMAL(10, 4) NOT NULL,
    LISTADO BIGINT,
    PRIMARY KEY (ID)
);

DROP TABLE IF EXISTS CFG_DIAMANTE_LISTADO_VALOR_COMERCIAL;
CREATE TABLE CFG_DIAMANTE_LISTADO_VALOR_COMERCIAL
(
    ID BIGINT AUTO_INCREMENT NOT NULL,
    FECHA_CARGA TIMESTAMP NOT NULL,
    FECHA_LISTADO DATE NOT NULL,
    PRIMARY KEY (ID)
);

------------------------------------------------------------------------------------------------------------------------
-- TERMINA - TABLAS: VALOR COMERCIAL 'DIAMANTE'
------------------------------------------------------------------------------------------------------------------------



------------------------------------------------------------------------------------------------------------------------
-- INICIA - TABLAS: VALOR COMERCIAL 'ORO'
------------------------------------------------------------------------------------------------------------------------

--
-- HISTÓRICOS
--

DROP TABLE IF EXISTS HIST_CFG_ALHAJA_VALOR_COMERCIAL_ORO;
CREATE TABLE HIST_CFG_ALHAJA_VALOR_COMERCIAL_ORO
(
    ID BIGINT AUTO_INCREMENT NOT NULL,
    COLOR VARCHAR(15) NOT NULL,
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
    COLOR VARCHAR(15) NOT NULL,
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

------------------------------------------------------------------------------------------------------------------------
-- TERMINA - TABLAS: VALOR COMERCIAL 'ORO'
------------------------------------------------------------------------------------------------------------------------



------------------------------------------------------------------------------------------------------------------------
-- INICIA - TABLAS: VALOR COMERCIAL 'METAL'
------------------------------------------------------------------------------------------------------------------------

--
-- HISTÓRICOS
--

DROP TABLE IF EXISTS HIST_CFG_ALHAJA_VALOR_COMERCIAL_METAL;
CREATE TABLE HIST_CFG_ALHAJA_VALOR_COMERCIAL_METAL
(
    ID BIGINT AUTO_INCREMENT NOT NULL,
    METAL VARCHAR(80) NOT NULL,
    CALIDAD VARCHAR(10),
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
    METAL VARCHAR(80) NOT NULL,
    CALIDAD VARCHAR(10),
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

------------------------------------------------------------------------------------------------------------------------
-- TERMINA - TABLAS: VALOR COMERCIAL 'METAL'
------------------------------------------------------------------------------------------------------------------------

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
FOREIGN KEY(LISTADO) REFERENCES HIST_CFG_DIAMANTE_LISTADO_VALOR_COMERCIAL(ID);

CREATE INDEX IDX_CFG_DIAMANTE_LISTADO_VALOR_COMERCIAL_ID ON CFG_DIAMANTE_LISTADO_VALOR_COMERCIAL(ID);

CREATE INDEX IDX_CFG_DIAMANTE_VALOR_COMERCIAL_ID ON CFG_DIAMANTE_VALOR_COMERCIAL(ID);
ALTER TABLE CFG_DIAMANTE_VALOR_COMERCIAL ADD CONSTRAINT FK_CFG_DIAMANTE_VALOR_COMERCIAL
FOREIGN KEY(LISTADO) REFERENCES CFG_DIAMANTE_LISTADO_VALOR_COMERCIAL(ID);

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
