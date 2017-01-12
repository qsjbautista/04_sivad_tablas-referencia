--
-- Utilizado para poblar la BD (mysql) utilizada con los perfiles cloud.
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
VALUES (1, 'AU_AM', '8_Q', 250.000, 1);
INSERT INTO cfg_alhaja_valor_comercial_oro (id, color, calidad, precio, listado)
VALUES (2, 'AU_AM', '10_Q', 312.500, 1);
INSERT INTO cfg_alhaja_valor_comercial_oro (id, color, calidad, precio, listado)
VALUES (3, 'AU_AM', '12_Q', 375.000, 1);
INSERT INTO cfg_alhaja_valor_comercial_oro (id, color, calidad, precio, listado)
VALUES (4, 'AU_AM', '14_Q', 437.500, 1);
INSERT INTO cfg_alhaja_valor_comercial_oro (id, color, calidad, precio, listado)
VALUES (5, 'AU_AM', '16_Q', 500.000, 1);
INSERT INTO cfg_alhaja_valor_comercial_oro (id, color, calidad, precio, listado)
VALUES (6, 'AU_AM', '18_Q', 562.500, 1);
INSERT INTO cfg_alhaja_valor_comercial_oro (id, color, calidad, precio, listado)
VALUES (7, 'AU_AM', '21_Q', 570.500, 1);
INSERT INTO cfg_alhaja_valor_comercial_oro (id, color, calidad, precio, listado)
VALUES (8, 'AU_AM', '22_Q', 687.500, 1);
INSERT INTO cfg_alhaja_valor_comercial_oro (id, color, calidad, precio, listado)
VALUES (9, 'AU_AM', '24_Q', 750.000, 1);
INSERT INTO cfg_alhaja_valor_comercial_oro (id, color, calidad, precio, listado)
VALUES (10, 'AU_BL', '8_Q', 250.000, 1);
INSERT INTO cfg_alhaja_valor_comercial_oro (id, color, calidad, precio, listado)
VALUES (11, 'AU_BL', '10_Q', 312.500, 1);
INSERT INTO cfg_alhaja_valor_comercial_oro (id, color, calidad, precio, listado)
VALUES (12, 'AU_BL', '12_Q', 375.000, 1);
INSERT INTO cfg_alhaja_valor_comercial_oro (id, color, calidad, precio, listado)
VALUES (13, 'AU_BL', '14_Q', 437.500, 1);
INSERT INTO cfg_alhaja_valor_comercial_oro (id, color, calidad, precio, listado)
VALUES (14, 'AU_BL', '16_Q', 500.000, 1);
INSERT INTO cfg_alhaja_valor_comercial_oro (id, color, calidad, precio, listado)
VALUES (15, 'AU_BL', '18_Q', 562.500, 1);
INSERT INTO cfg_alhaja_valor_comercial_oro (id, color, calidad, precio, listado)
VALUES (16, 'AU_BL', '21_Q', 570.500, 1);
INSERT INTO cfg_alhaja_valor_comercial_oro (id, color, calidad, precio, listado)
VALUES (17, 'AU_BL', '22_Q', 687.500, 1);
INSERT INTO cfg_alhaja_valor_comercial_oro (id, color, calidad, precio, listado)
VALUES (18, 'AU_BL', '24_Q', 750.000, 1);
-- ----------------------------------------------------------------------------------------------------------------------
-- TERMINA - DATOS: VALOR COMERCIAL 'ORO'
-- ----------------------------------------------------------------------------------------------------------------------



-- ----------------------------------------------------------------------------------------------------------------------
-- INICIA - DATOS: VALOR COMERCIAL 'DIAMANTE'
-- ----------------------------------------------------------------------------------------------------------------------
INSERT INTO cfg_diamante_listado_valor_comercial (id, fecha_carga, fecha_listado)
VALUES (1, '2016-12-20 10:00:00.521', '2016-12-20');

INSERT INTO cfg_diamante_valor_comercial (id, corte, color, claridad, tamanio_inferior, tamanio_superior, precio, listado)
VALUES (1, 'Pear', 'D', 'IF', 0.90, 0.99, 135.0000, 1);
INSERT INTO cfg_diamante_valor_comercial (id, corte, color, claridad, tamanio_inferior, tamanio_superior, precio, listado)
VALUES (2, 'Pear', 'D', 'VVS1', 0.90, 0.99, 111.0000, 1);
INSERT INTO cfg_diamante_valor_comercial (id, corte, color, claridad, tamanio_inferior, tamanio_superior, precio, listado)
VALUES (3, 'Pear', 'D', 'VVS2', 0.90, 0.99, 97.0000, 1);
INSERT INTO cfg_diamante_valor_comercial (id, corte, color, claridad, tamanio_inferior, tamanio_superior, precio, listado)
VALUES (4, 'Pear', 'D', 'VS1', 0.90, 0.99, 73.0000, 1);
INSERT INTO cfg_diamante_valor_comercial (id, corte, color, claridad, tamanio_inferior, tamanio_superior, precio, listado)
VALUES (5, 'Pear', 'D', 'VS2', 0.90, 0.99, 66.0000, 1);
INSERT INTO cfg_diamante_valor_comercial (id, corte, color, claridad, tamanio_inferior, tamanio_superior, precio, listado)
VALUES (6, 'Pear', 'E', 'IF', 0.90, 0.99, 107.0000, 1);
INSERT INTO cfg_diamante_valor_comercial (id, corte, color, claridad, tamanio_inferior, tamanio_superior, precio, listado)
VALUES (7, 'Pear', 'E', 'VVS1', 0.90, 0.99, 99.0000, 1);
INSERT INTO cfg_diamante_valor_comercial (id, corte, color, claridad, tamanio_inferior, tamanio_superior, precio, listado)
VALUES (8, 'Pear', 'E', 'VVS2', 0.90, 0.99, 84.0000, 1);
INSERT INTO cfg_diamante_valor_comercial (id, corte, color, claridad, tamanio_inferior, tamanio_superior, precio, listado)
VALUES (9, 'Pear', 'E', 'VS1', 0.90, 0.99, 68.0000, 1);
INSERT INTO cfg_diamante_valor_comercial (id, corte, color, claridad, tamanio_inferior, tamanio_superior, precio, listado)
VALUES (10, 'Pear', 'E', 'VS2', 0.90, 0.99, 63.0000, 1);
-- ----------------------------------------------------------------------------------------------------------------------
-- TERMINA - DATOS: VALOR COMERCIAL 'DIAMANTE'
-- ----------------------------------------------------------------------------------------------------------------------



-- ----------------------------------------------------------------------------------------------------------------------
-- INICIA - DATOS: FACTOR VALOR DIAMANTE
-- ----------------------------------------------------------------------------------------------------------------------
INSERT INTO cfg_diamante_factor (id, factor_minimo, factor_medio, factor_maximo, fecha_carga, fecha_listado)
VALUES (1, 1.000, 1.050, 1.100, '2016-12-20 10:00:00.521', '2016-12-20');
-- ----------------------------------------------------------------------------------------------------------------------
-- TERMINA - DATOS: FACTOR VALOR DIAMANTE
-- ----------------------------------------------------------------------------------------------------------------------



-- ----------------------------------------------------------------------------------------------------------------------
-- INICIA - DATOS: MODIFICADOR CERTIFICADO
-- ----------------------------------------------------------------------------------------------------------------------
INSERT INTO cfg_diamante_listado_valor_certificado (id, ultima_actualizacion, fecha_listado)
VALUES (1, '2016-12-20 10:00:00.521', '2016-12-20');

INSERT INTO cfg_diamante_valor_certificado (id, certificado, factor, listado)
VALUES (1, 'GI', 1.050, 1);
INSERT INTO cfg_diamante_valor_certificado (id, certificado, factor, listado)
VALUES (2, 'HR', 1.050, 1);
INSERT INTO cfg_diamante_valor_certificado (id, certificado, factor, listado)
VALUES (3, 'IG', 1.050, 1);
-- ----------------------------------------------------------------------------------------------------------------------
-- TERMINA - DATOS: MODIFICADOR CERTIFICADO
-- ----------------------------------------------------------------------------------------------------------------------



-- ----------------------------------------------------------------------------------------------------------------------
-- INICIA - DATOS: MODIFICADORES DE FACTORES DE ALHAJAS.
-- ----------------------------------------------------------------------------------------------------------------------
INSERT INTO cfg_alhaja_listado_factor (id, ultima_actualizacion, fecha_listado)
VALUES (1, '2016-12-20 10:00:00.521', '2016-12-20');

INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento, limite_inferior, limite_superior, ultima_actualizacion, listado)
VALUES (1, 'AU', '8_Q', 'F2', 1.25, 0.00, 0.00, 10.00, '2016-12-20 10:00:00.521', 1);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento, limite_inferior, limite_superior, ultima_actualizacion, listado)
VALUES (2, 'AU', '8_Q', 'F3', 1.35, 5.00, 10.00, 20.00, '2016-12-20 10:00:00.521', 1);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento, limite_inferior, limite_superior, ultima_actualizacion, listado)
VALUES (3, 'AU', '8_Q', 'F4', 1.40, 10.00, 20.00, 30.00, '2016-12-20 10:00:00.521', 1);
-- ----------------------------------------------------------------------------------------------------------------------
-- TERMINA - DATOS: MODIFICADORES DE FACTORES DE ALHAJAS.
-- ----------------------------------------------------------------------------------------------------------------------
