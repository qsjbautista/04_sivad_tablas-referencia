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
INSERT INTO `cfg_diamante_factores_x_rango_de_color` (`ID`,`FECHA`,`COLOR_DESDE`,`COLOR_HASTA`,`RANGO_COLOR_BASE`,`FACTOR`) VALUES (1,'2018-08-28 00:00:00','O','R','M',0.80);
INSERT INTO `cfg_diamante_factores_x_rango_de_color` (`ID`,`FECHA`,`COLOR_DESDE`,`COLOR_HASTA`,`RANGO_COLOR_BASE`,`FACTOR`) VALUES (2,'2018-08-28 00:00:00','S','Z','M',0.65);

/*
cfg_diamante_parametros_quilates
*/
INSERT INTO `cfg_diamante_parametros_quilates` (`ID`,`FECHA`,`QUILATES_DESDE`,`QUILATES_HASTA`,`QUILATES_BASE_DESDE`,`QUILATES_BASE_HASTA`,`PORCENTAJE`) VALUES (1,'2018-08-28 00:00:00',6.00,6.99,5.00,5.99,1.100);
INSERT INTO `cfg_diamante_parametros_quilates` (`ID`,`FECHA`,`QUILATES_DESDE`,`QUILATES_HASTA`,`QUILATES_BASE_DESDE`,`QUILATES_BASE_HASTA`,`PORCENTAJE`) VALUES (6,'2018-08-28 00:00:00',7.00,7.99,5.00,5.99,1.150);
INSERT INTO `cfg_diamante_parametros_quilates` (`ID`,`FECHA`,`QUILATES_DESDE`,`QUILATES_HASTA`,`QUILATES_BASE_DESDE`,`QUILATES_BASE_HASTA`,`PORCENTAJE`) VALUES (7,'2018-08-28 00:00:00',8.00,8.99,5.00,5.99,1.200);
INSERT INTO `cfg_diamante_parametros_quilates` (`ID`,`FECHA`,`QUILATES_DESDE`,`QUILATES_HASTA`,`QUILATES_BASE_DESDE`,`QUILATES_BASE_HASTA`,`PORCENTAJE`) VALUES (8,'2018-08-28 00:00:00',9.00,9.99,5.00,5.99,1.250);
INSERT INTO `cfg_diamante_parametros_quilates` (`ID`,`FECHA`,`QUILATES_DESDE`,`QUILATES_HASTA`,`QUILATES_BASE_DESDE`,`QUILATES_BASE_HASTA`,`PORCENTAJE`) VALUES (9,'2018-08-28 00:00:00',11.00,20.00,10.00,10.99,1.075);

/*
cfg_diamante_porcentaje_castigo_x_rango_de_pesos
*/
INSERT INTO `cfg_diamante_porcentaje_castigo_x_rango_de_pesos` (`ID`,`FECHA`,`QUILATES_DESDE`,`QUILATES_HASTA`,`FACTOR`) VALUES (1,'2018-08-28 00:00:00',0.01,0.49,0.70);
INSERT INTO `cfg_diamante_porcentaje_castigo_x_rango_de_pesos` (`ID`,`FECHA`,`QUILATES_DESDE`,`QUILATES_HASTA`,`FACTOR`) VALUES (2,'2018-08-28 00:00:00',0.50,0.89,0.60);
INSERT INTO `cfg_diamante_porcentaje_castigo_x_rango_de_pesos` (`ID`,`FECHA`,`QUILATES_DESDE`,`QUILATES_HASTA`,`FACTOR`) VALUES (3,'2018-08-28 00:00:00',0.90,1.49,0.50);
INSERT INTO `cfg_diamante_porcentaje_castigo_x_rango_de_pesos` (`ID`,`FECHA`,`QUILATES_DESDE`,`QUILATES_HASTA`,`FACTOR`) VALUES (4,'2018-08-28 00:00:00',1.50,1.99,0.45);
INSERT INTO `cfg_diamante_porcentaje_castigo_x_rango_de_pesos` (`ID`,`FECHA`,`QUILATES_DESDE`,`QUILATES_HASTA`,`FACTOR`) VALUES (5,'2018-08-28 00:00:00',2.00,100.00,0.35);

/*
cfg_diamante_factor_rapaport
*/
INSERT INTO `cfg_diamante_factor_rapaport` (`ID`,`FACTOR`,`FECHA`) VALUES (1,100,'2018-08-28 00:00:00');

/*
cfg_diamante_rango_pesos
*/
INSERT INTO cfg_diamante_rango_pesos(fecha, quilates_desde, quilates_hasta) VALUES('2018-09-05 00:00:00',0.01,0.03);
INSERT INTO cfg_diamante_rango_pesos(fecha, quilates_desde, quilates_hasta) VALUES('2018-09-05 00:00:00',0.04,0.07);
INSERT INTO cfg_diamante_rango_pesos(fecha, quilates_desde, quilates_hasta) VALUES('2018-09-05 00:00:00',0.08,0.14);
INSERT INTO cfg_diamante_rango_pesos(fecha, quilates_desde, quilates_hasta) VALUES('2018-09-05 00:00:00',0.15,0.17);
INSERT INTO cfg_diamante_rango_pesos(fecha, quilates_desde, quilates_hasta) values('2018-09-05 00:00:00',0.18,0.22);
INSERT INTO cfg_diamante_rango_pesos(fecha, quilates_desde, quilates_hasta) values('2018-09-05 00:00:00',0.23,0.29);
INSERT INTO cfg_diamante_rango_pesos(fecha, quilates_desde, quilates_hasta) values('2018-09-05 00:00:00',0.30,0.39);
INSERT INTO cfg_diamante_rango_pesos(fecha, quilates_desde, quilates_hasta) values('2018-09-05 00:00:00',0.40,0.49);
INSERT INTO cfg_diamante_rango_pesos(fecha, quilates_desde, quilates_hasta) values('2018-09-05 00:00:00',0.50,0.69);
INSERT INTO cfg_diamante_rango_pesos(fecha, quilates_desde, quilates_hasta) values('2018-09-05 00:00:00',0.70,0.89);
INSERT INTO cfg_diamante_rango_pesos(fecha, quilates_desde, quilates_hasta) values('2018-09-05 00:00:00',0.90,0.99);
INSERT INTO cfg_diamante_rango_pesos(fecha, quilates_desde, quilates_hasta) values('2018-09-05 00:00:00',1.00,1.49);
INSERT INTO cfg_diamante_rango_pesos(fecha, quilates_desde, quilates_hasta) values('2018-09-05 00:00:00',1.50,1.99);
INSERT INTO cfg_diamante_rango_pesos(fecha, quilates_desde, quilates_hasta) values('2018-09-05 00:00:00',2.00,2.99);
INSERT INTO cfg_diamante_rango_pesos(fecha, quilates_desde, quilates_hasta) values('2018-09-05 00:00:00',3.00,3.99);
INSERT INTO cfg_diamante_rango_pesos(fecha, quilates_desde, quilates_hasta) values('2018-09-05 00:00:00',4.00,4.99);
INSERT INTO cfg_diamante_rango_pesos(fecha, quilates_desde, quilates_hasta) values('2018-09-05 00:00:00',5.00,5.99);
INSERT INTO cfg_diamante_rango_pesos(fecha, quilates_desde, quilates_hasta) values('2018-09-05 00:00:00',6.00,6.99);
INSERT INTO cfg_diamante_rango_pesos(fecha, quilates_desde, quilates_hasta) values('2018-09-05 00:00:00',7.00,7.99);
INSERT INTO cfg_diamante_rango_pesos(fecha, quilates_desde, quilates_hasta) values('2018-09-05 00:00:00',8.00,8.99);
INSERT INTO cfg_diamante_rango_pesos(fecha, quilates_desde, quilates_hasta) values('2018-09-05 00:00:00',9.00,9.99);
INSERT INTO cfg_diamante_rango_pesos(fecha, quilates_desde, quilates_hasta) values('2018-09-05 00:00:00',10.00,10.99);
INSERT INTO cfg_diamante_rango_pesos(fecha, quilates_desde, quilates_hasta) values('2018-09-05 00:00:00',11.00,20.00);


/*
cfg_diamante_porcentaje_castigo_x_tipo_corte
*/
-- Se estandarizan los nombres segun el catalogo de subcortes
INSERT INTO `cfg_diamante_porcentaje_castigo_x_tipo_corte` (`id`,`fecha`,`corte`,`factor`) VALUES (1,'2018-09-05 00:00:00','Brillante Redondo',1.0000);
INSERT INTO `cfg_diamante_porcentaje_castigo_x_tipo_corte` (`id`,`fecha`,`corte`,`factor`) VALUES (2,'2018-09-05 00:00:00','Acojinado',0.9000);
INSERT INTO `cfg_diamante_porcentaje_castigo_x_tipo_corte` (`id`,`fecha`,`corte`,`factor`) VALUES (3,'2018-09-05 00:00:00','Pera',0.8500);
INSERT INTO `cfg_diamante_porcentaje_castigo_x_tipo_corte` (`id`,`fecha`,`corte`,`factor`) VALUES (4,'2018-09-05 00:00:00','Oval',0.8300);
INSERT INTO `cfg_diamante_porcentaje_castigo_x_tipo_corte` (`id`,`fecha`,`corte`,`factor`) VALUES (5,'2018-09-05 00:00:00','Corazón',0.8200);
INSERT INTO `cfg_diamante_porcentaje_castigo_x_tipo_corte` (`id`,`fecha`,`corte`,`factor`) VALUES (6,'2018-09-05 00:00:00','Marquesa',0.8200);
INSERT INTO `cfg_diamante_porcentaje_castigo_x_tipo_corte` (`id`,`fecha`,`corte`,`factor`) VALUES (7,'2018-09-05 00:00:00','Flanders',0.8000);
INSERT INTO `cfg_diamante_porcentaje_castigo_x_tipo_corte` (`id`,`fecha`,`corte`,`factor`) VALUES (8,'2018-09-05 00:00:00','Princesa',0.8000);
INSERT INTO `cfg_diamante_porcentaje_castigo_x_tipo_corte` (`id`,`fecha`,`corte`,`factor`) VALUES (9,'2018-09-05 00:00:00','Radiante',0.7600);
INSERT INTO `cfg_diamante_porcentaje_castigo_x_tipo_corte` (`id`,`fecha`,`corte`,`factor`) VALUES (10,'2018-09-05 00:00:00','Asscher',0.7500);
INSERT INTO `cfg_diamante_porcentaje_castigo_x_tipo_corte` (`id`,`fecha`,`corte`,`factor`) VALUES (11,'2018-09-05 00:00:00','Trapecio',0.7500);
INSERT INTO `cfg_diamante_porcentaje_castigo_x_tipo_corte` (`id`,`fecha`,`corte`,`factor`) VALUES (12,'2018-09-05 00:00:00','Trillante',0.7500);
INSERT INTO `cfg_diamante_porcentaje_castigo_x_tipo_corte` (`id`,`fecha`,`corte`,`factor`) VALUES (13,'2018-09-05 00:00:00','Baguette',0.7000);
INSERT INTO `cfg_diamante_porcentaje_castigo_x_tipo_corte` (`id`,`fecha`,`corte`,`factor`) VALUES (14,'2018-09-05 00:00:00','Esmeralda',0.7000);
INSERT INTO `cfg_diamante_porcentaje_castigo_x_tipo_corte` (`id`,`fecha`,`corte`,`factor`) VALUES (15,'2018-09-05 00:00:00','Antiguo',0.6000);
INSERT INTO `cfg_diamante_porcentaje_castigo_x_tipo_corte` (`id`,`fecha`,`corte`,`factor`) VALUES (16,'2018-09-05 00:00:00','8x8',0.6000);
INSERT INTO `cfg_diamante_porcentaje_castigo_x_tipo_corte` (`id`,`fecha`,`corte`,`factor`) VALUES (17,'2018-09-05 00:00:00','Otro',0.6000);

-- Fin configuración de tablas de referencia

-- Se actualiza el catalogo de incremento
UPDATE cfg_factor_alhaja SET limite_inferior=5.00, limite_superior=20.00 WHERE id in (2,6,11,16,21,26,32,38);
UPDATE cfg_factor_alhaja SET limite_inferior=10.00, limite_superior=15.00 WHERE id in (3,7,12,17,22,27,33,39);
UPDATE cfg_factor_alhaja SET limite_inferior=25.00, limite_superior=55.00 WHERE id in (9,14,19,24,29,35,41);
UPDATE cfg_factor_alhaja SET limite_inferior=70.00, limite_superior=110.00 WHERE id in (30,36,42);

-- Se agregan los campos de desplazamiento comercial y el incremento
ALTER TABLE cfg_factor_alhaja 
CHANGE COLUMN desplazamiento desplazamiento_limite_superior DECIMAL(8,2) NOT NULL ,
ADD COLUMN desplazamiento_limite_inferior DECIMAL(8,2) NOT NULL AFTER factor,
ADD COLUMN desplazamiento_incremento INT NOT NULL AFTER desplazamiento_limite_superior,
ADD COLUMN incremento INT NOT NULL AFTER limite_superior;


ALTER TABLE hist_cfg_factor_alhaja 
CHANGE COLUMN desplazamiento desplazamiento_limite_superior DECIMAL(8,2) NOT NULL ,
ADD COLUMN desplazamiento_limite_inferior DECIMAL(8,2) NOT NULL AFTER factor,
ADD COLUMN desplazamiento_incremento INT NOT NULL AFTER desplazamiento_limite_superior,
ADD COLUMN incremento INT NOT NULL AFTER limite_superior;

-- Rango F2
UPDATE cfg_factor_alhaja SET incremento=5 WHERE id in (2,6,11,16,21,26,32,38);
-- Rango F3
UPDATE cfg_factor_alhaja SET desplazamiento_limite_inferior=0.00, desplazamiento_incremento=5, incremento=5 WHERE id in (3,7,12,17,22,27,33,39);
-- Rango F4
UPDATE cfg_factor_alhaja SET desplazamiento_limite_inferior=5.00,desplazamiento_limite_superior = 15.00, desplazamiento_incremento=5, incremento=5 WHERE id in (4,8,13,18,23,28,34,40);
-- Rango F5
UPDATE cfg_factor_alhaja SET desplazamiento_limite_inferior=5.00,desplazamiento_limite_superior = 15.00, desplazamiento_incremento=5, incremento=5 WHERE id in (9,14,19,24,29,35,41);
-- Rango F6
UPDATE cfg_factor_alhaja SET desplazamiento_limite_inferior=5.00,desplazamiento_limite_superior = 30.00, desplazamiento_incremento=5, incremento=10 WHERE id in (30,36,42);

-- Se agregan los inserts de calidad ley para platino, precio y factor

delete from cfg_alhaja_valor_comercial_metal where id in (1);
INSERT INTO cfg_alhaja_valor_comercial_metal (metal, calidad, precio, listado)
VALUES ('PT', 'CL_950', 520.60, 1);
INSERT INTO cfg_alhaja_valor_comercial_metal (metal, calidad, precio, listado)
VALUES ('PT', 'CL_900', 493.20, 1);

delete from cfg_factor_alhaja where id in (43,44,45);
INSERT INTO cfg_factor_alhaja (metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES ('PT', 'CL_950', 'F1', 1.10, 0.00, 0.00, 0.00, now(), 1, 0.00, 0.00, 0.00);
INSERT INTO cfg_factor_alhaja (metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES ('PT', 'CL_950', 'DE', 1.20, 0.00, 0.00, 0.00, now(), 1, 0.00, 0.00, 0.00);
INSERT INTO cfg_factor_alhaja (metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES ('PT', 'CL_950', 'MC', 1.30, 0.00, 0.00, 0.00, now(), 1, 0.00, 0.00, 0.00);
INSERT INTO cfg_factor_alhaja (metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES ('PT', 'CL_900', 'F1', 1.10, 0.00, 0.00, 0.00, now(), 1, 0.00, 0.00, 0.00);
INSERT INTO cfg_factor_alhaja (metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES ('PT', 'CL_900', 'DE', 1.20, 0.00, 0.00, 0.00, now(), 1, 0.00, 0.00, 0.00);
INSERT INTO cfg_factor_alhaja (metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES ('PT', 'CL_900', 'MC', 1.30, 0.00, 0.00, 0.00, now(), 1, 0.00, 0.00, 0.00);



