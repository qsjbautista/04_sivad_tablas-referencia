--
-- Utilizado para poblar la BD (h2) utilizada con el perfil de desarrollo.
--



------------------------------------------------------------------------------------------------------------------------
-- INICIA - DATOS: VALOR COMERCIAL 'METAL'
------------------------------------------------------------------------------------------------------------------------
INSERT INTO cfg_alhaja_listado_valor_comercial_metal (id, ultima_actualizacion)
VALUES (1, '2016-12-20 10:00:00.521-06:00');

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
------------------------------------------------------------------------------------------------------------------------
-- TERMINA - DATOS: VALOR COMERCIAL 'METAL'
------------------------------------------------------------------------------------------------------------------------



------------------------------------------------------------------------------------------------------------------------
-- INICIA - DATOS: VALOR COMERCIAL 'ORO'
------------------------------------------------------------------------------------------------------------------------
INSERT INTO cfg_alhaja_listado_valor_comercial_oro (id, ultima_actualizacion)
VALUES (1, '2016-12-20 10:00:00.521-06:00');

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
------------------------------------------------------------------------------------------------------------------------
-- TERMINA - DATOS: VALOR COMERCIAL 'ORO'
------------------------------------------------------------------------------------------------------------------------



------------------------------------------------------------------------------------------------------------------------
-- INICIA - DATOS: VALOR COMERCIAL 'DIAMANTE'
------------------------------------------------------------------------------------------------------------------------
INSERT INTO cfg_diamante_listado_valor_comercial (id, fecha_carga, fecha_listado)
VALUES (1, '2016-12-20 10:00:00.521-06:00', '2016-12-20');

INSERT INTO cfg_diamante_valor_comercial (id, corte, color, claridad, tamanio_inferior, tamanio_superior, precio, listado, tipo_cambio, montoVbd, montofCastigoxRango)
VALUES (1, 'Pear', 'D', 'IF', 0.90, 0.99, 135.0000, 1, 19.35, 135.0000, 135.0000);
INSERT INTO cfg_diamante_valor_comercial (id, corte, color, claridad, tamanio_inferior, tamanio_superior, precio, listado, tipo_cambio, montoVbd, montofCastigoxRango)
VALUES (2, 'Pear', 'D', 'VVS1', 0.90, 0.99, 111.0000, 1, 19.35, 111.0000, 111.0000);
INSERT INTO cfg_diamante_valor_comercial (id, corte, color, claridad, tamanio_inferior, tamanio_superior, precio, listado, tipo_cambio, montoVbd, montofCastigoxRango)
VALUES (3, 'Pear', 'D', 'VVS2', 0.90, 0.99, 97.0000, 1, 19.35, 97.0000, 97.0000);
INSERT INTO cfg_diamante_valor_comercial (id, corte, color, claridad, tamanio_inferior, tamanio_superior, precio, listado, tipo_cambio, montoVbd, montofCastigoxRango)
VALUES (4, 'Pear', 'D', 'VS1', 0.90, 0.99, 73.0000, 1, 19.35, 73.0000, 73.0000);
INSERT INTO cfg_diamante_valor_comercial (id, corte, color, claridad, tamanio_inferior, tamanio_superior, precio, listado, tipo_cambio, montoVbd, montofCastigoxRango)
VALUES (5, 'Pear', 'D', 'VS2', 0.90, 0.99, 66.0000, 1, 19.35, 66.0000, 66.0000);
INSERT INTO cfg_diamante_valor_comercial (id, corte, color, claridad, tamanio_inferior, tamanio_superior, precio, listado, tipo_cambio, montoVbd, montofCastigoxRango)
VALUES (6, 'Pear', 'E', 'IF', 0.90, 0.99, 107.0000, 1, 19.35, 107.0000, 107.0000);
INSERT INTO cfg_diamante_valor_comercial (id, corte, color, claridad, tamanio_inferior, tamanio_superior, precio, listado, tipo_cambio, montoVbd, montofCastigoxRango)
VALUES (7, 'Pear', 'E', 'VVS1', 0.90, 0.99, 99.0000, 1, 19.35, 99.0000, 99.0000);
INSERT INTO cfg_diamante_valor_comercial (id, corte, color, claridad, tamanio_inferior, tamanio_superior, precio, listado, tipo_cambio, montoVbd, montofCastigoxRango)
VALUES (8, 'Pear', 'E', 'VVS2', 0.90, 0.99, 84.0000, 1, 19.35, 84.0000, 84.0000);
INSERT INTO cfg_diamante_valor_comercial (id, corte, color, claridad, tamanio_inferior, tamanio_superior, precio, listado, tipo_cambio, montoVbd, montofCastigoxRango)
VALUES (9, 'Pear', 'E', 'VS1', 0.90, 0.99, 68.0000, 1, 19.35, 68.0000, 68.0000);
INSERT INTO cfg_diamante_valor_comercial (id, corte, color, claridad, tamanio_inferior, tamanio_superior, precio, listado, tipo_cambio, montoVbd, montofCastigoxRango)
VALUES (10, 'Pear', 'E', 'VS2', 0.90, 0.99, 63.0000, 1, 19.35, 63.0000, 63.0000);
------------------------------------------------------------------------------------------------------------------------
-- TERMINA - DATOS: VALOR COMERCIAL 'DIAMANTE'
------------------------------------------------------------------------------------------------------------------------



------------------------------------------------------------------------------------------------------------------------
-- INICIA - DATOS: FACTOR VALOR DIAMANTE
------------------------------------------------------------------------------------------------------------------------
INSERT INTO cfg_diamante_factor (id, factor_minimo, factor_medio, factor_maximo, fecha_carga, fecha_listado)
VALUES (1, 0.550, 1.000, 1.100, '2017-05-02 12:56:00.521', '2017-05-02');
------------------------------------------------------------------------------------------------------------------------
-- TERMINA - DATOS: FACTOR VALOR DIAMANTE
------------------------------------------------------------------------------------------------------------------------



------------------------------------------------------------------------------------------------------------------------
-- INICIA - DATOS: MODIFICADOR CERTIFICADO
------------------------------------------------------------------------------------------------------------------------
INSERT INTO cfg_diamante_listado_valor_certificado (id, ultima_actualizacion, fecha_listado)
VALUES (1, '2016-12-20 10:00:00.521-06:00', '2016-12-20');

INSERT INTO cfg_diamante_valor_certificado (id, certificado, factor, listado)
VALUES (1, 'GI', 1.050, 1);
INSERT INTO cfg_diamante_valor_certificado (id, certificado, factor, listado)
VALUES (2, 'HR', 1.050, 1);
INSERT INTO cfg_diamante_valor_certificado (id, certificado, factor, listado)
VALUES (3, 'IG', 1.050, 1);
INSERT INTO cfg_diamante_valor_certificado (id, certificado, factor, listado)
VALUES (4, 'AJ', 1.050, 1);
------------------------------------------------------------------------------------------------------------------------
-- TERMINA - DATOS: MODIFICADOR CERTIFICADO
------------------------------------------------------------------------------------------------------------------------



------------------------------------------------------------------------------------------------------------------------
-- INICIA - DATOS: MODIFICADORES DE FACTORES DE ALHAJAS.
------------------------------------------------------------------------------------------------------------------------
INSERT INTO cfg_alhaja_listado_factor (id, ultima_actualizacion, fecha_listado)
VALUES (1, '2016-12-20 10:00:00.521-06:00', '2016-12-20');

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
------------------------------------------------------------------------------------------------------------------------
-- TERMINA - DATOS: MODIFICADORES DE FACTORES DE ALHAJAS.
------------------------------------------------------------------------------------------------------------------------

-- Incio configuración de tablas de referencia  Siva Diamantes F2

/*
cfg_diamante_factores_x_rango_de_color
*/
INSERT INTO `cfg_diamante_factores_x_rango_de_color` (`ID`,`FECHA`,`COLOR_DESDE`,`COLOR_HASTA`,`RANGO_COLOR_BASE`,`FACTOR`) VALUES (1,'2018-08-28','O','R','MN',0.80);
INSERT INTO `cfg_diamante_factores_x_rango_de_color` (`ID`,`FECHA`,`COLOR_DESDE`,`COLOR_HASTA`,`RANGO_COLOR_BASE`,`FACTOR`) VALUES (2,'2018-08-28','S','Z','MN',0.65);

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
cfg_diamante_rango_pesos
*/
INSERT INTO `cfg_diamante_rango_pesos` (`id`,`fecha`,`quilates_desde`,`quilates_hasta`) VALUES (1,'2018-09-05',0.0100,0.0300);
INSERT INTO `cfg_diamante_rango_pesos` (`id`,`fecha`,`quilates_desde`,`quilates_hasta`) VALUES (8,'2018-09-05',0.0400,0.0700);
INSERT INTO `cfg_diamante_rango_pesos` (`id`,`fecha`,`quilates_desde`,`quilates_hasta`) VALUES (15,'2018-09-05',0.0800,0.1400);

/*
cfg_diamante_porcentaje_castigo_x_tipo_corte
*/
INSERT INTO `cfg_diamante_porcentaje_castigo_x_tipo_corte` (`id`,`fecha`,`corte`,`factor`) VALUES (1,'2018-09-05','brillante',1.0000);
INSERT INTO `cfg_diamante_porcentaje_castigo_x_tipo_corte` (`id`,`fecha`,`corte`,`factor`) VALUES (8,'2018-09-05','acojinado',0.9000);



-- Fin configuración de tablas de referencia
