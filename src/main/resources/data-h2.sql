------------------------------------------------------------------------------------------------------------------------
-- INICIA - DATOS DE PRUEBA: VALOR COMERCIAL 'DIAMANTE'
------------------------------------------------------------------------------------------------------------------------

--
-- HISTÓRICOS
--
INSERT INTO hist_cfg_diamante_listado_valor_comercial (id, fecha_carga, fecha_listado) VALUES (1, '2016-11-20 10:00:00.521-06:00', '2016-11-20');
INSERT INTO hist_cfg_diamante_listado_valor_comercial (id, fecha_carga, fecha_listado) VALUES (2, '2016-11-21 10:00:00.521-06:00', '2016-11-21');
INSERT INTO hist_cfg_diamante_listado_valor_comercial (id, fecha_carga, fecha_listado) VALUES (3, '2016-11-21 11:00:00.521-06:00', '2016-11-21');

INSERT INTO hist_cfg_diamante_valor_comercial (id, corte, color, claridad, tamanio_inferior, tamanio_superior, precio, listado) VALUES (1, 'Oval', 'D', 'IF', 0.90, 0.99, 132.0000, 1);
INSERT INTO hist_cfg_diamante_valor_comercial (id, corte, color, claridad, tamanio_inferior, tamanio_superior, precio, listado) VALUES (2, 'Oval', 'E', 'IF', 0.90, 0.99, 104.0000, 1);
INSERT INTO hist_cfg_diamante_valor_comercial (id, corte, color, claridad, tamanio_inferior, tamanio_superior, precio, listado) VALUES (3, 'Oval', 'D', 'IF', 0.90, 0.99, 133.0000, 2);
INSERT INTO hist_cfg_diamante_valor_comercial (id, corte, color, claridad, tamanio_inferior, tamanio_superior, precio, listado) VALUES (4, 'Oval', 'E', 'IF', 0.90, 0.99, 105.0000, 2);
INSERT INTO hist_cfg_diamante_valor_comercial (id, corte, color, claridad, tamanio_inferior, tamanio_superior, precio, listado) VALUES (5, 'Oval', 'D', 'IF', 0.90, 0.99, 134.0000, 3);
INSERT INTO hist_cfg_diamante_valor_comercial (id, corte, color, claridad, tamanio_inferior, tamanio_superior, precio, listado) VALUES (6, 'Oval', 'D', 'VVS1', 0.90, 0.99, 110.0000, 3);
INSERT INTO hist_cfg_diamante_valor_comercial (id, corte, color, claridad, tamanio_inferior, tamanio_superior, precio, listado) VALUES (7, 'Oval', 'E', 'IF', 0.90, 0.99, 106.0000, 3);
INSERT INTO hist_cfg_diamante_valor_comercial (id, corte, color, claridad, tamanio_inferior, tamanio_superior, precio, listado) VALUES (8, 'Oval', 'E', 'VVS1', 0.90, 0.99, 98.0000, 3);

--
-- VIGENTES
--
INSERT INTO cfg_diamante_listado_valor_comercial (id, fecha_carga, fecha_listado) VALUES (4, '2016-11-23 10:00:00.521-06:00', '2016-11-23');

INSERT INTO cfg_diamante_valor_comercial (id, corte, color, claridad, tamanio_inferior, tamanio_superior, precio, listado) VALUES (1, 'Oval', 'D', 'IF', 0.90, 0.99, 135.0000, 4);
INSERT INTO cfg_diamante_valor_comercial (id, corte, color, claridad, tamanio_inferior, tamanio_superior, precio, listado) VALUES (2, 'Oval', 'D', 'VVS1', 0.90, 0.99, 111.0000, 4);
INSERT INTO cfg_diamante_valor_comercial (id, corte, color, claridad, tamanio_inferior, tamanio_superior, precio, listado) VALUES (3, 'Oval', 'D', 'VVS2', 0.90, 0.99, 97.0000, 4);
INSERT INTO cfg_diamante_valor_comercial (id, corte, color, claridad, tamanio_inferior, tamanio_superior, precio, listado) VALUES (4, 'Oval', 'D', 'VS1', 0.90, 0.99, 73.0000, 4);
INSERT INTO cfg_diamante_valor_comercial (id, corte, color, claridad, tamanio_inferior, tamanio_superior, precio, listado) VALUES (5, 'Oval', 'D', 'VS2', 0.90, 0.99, 66.0000, 4);
INSERT INTO cfg_diamante_valor_comercial (id, corte, color, claridad, tamanio_inferior, tamanio_superior, precio, listado) VALUES (6, 'Oval', 'E', 'IF', 0.90, 0.99, 107.0000, 4);
INSERT INTO cfg_diamante_valor_comercial (id, corte, color, claridad, tamanio_inferior, tamanio_superior, precio, listado) VALUES (7, 'Oval', 'E', 'VVS1', 0.90, 0.99, 99.0000, 4);
INSERT INTO cfg_diamante_valor_comercial (id, corte, color, claridad, tamanio_inferior, tamanio_superior, precio, listado) VALUES (8, 'Oval', 'E', 'VVS2', 0.90, 0.99, 84.0000, 4);
INSERT INTO cfg_diamante_valor_comercial (id, corte, color, claridad, tamanio_inferior, tamanio_superior, precio, listado) VALUES (9, 'Oval', 'E', 'VS1', 0.90, 0.99, 68.0000, 4);
INSERT INTO cfg_diamante_valor_comercial (id, corte, color, claridad, tamanio_inferior, tamanio_superior, precio, listado) VALUES (10, 'Oval', 'E', 'VS2', 0.90, 0.99, 63.0000, 4);

------------------------------------------------------------------------------------------------------------------------
-- TERMINA - DATOS DE PRUEBA: VALOR COMERCIAL 'DIAMANTE'
------------------------------------------------------------------------------------------------------------------------


------------------------------------------------------------------------------------------------------------------------
-- INICIA - DATOS DE PRUEBA: VALOR COMERCIAL 'ORO'
------------------------------------------------------------------------------------------------------------------------

--
-- HISTÓRICOS
--
INSERT INTO hist_cfg_alhaja_listado_valor_comercial_oro (id, ultima_actualizacion) VALUES (1, '2016-11-20 10:00:00.521-06:00');
INSERT INTO hist_cfg_alhaja_listado_valor_comercial_oro (id, ultima_actualizacion) VALUES (2, '2016-11-21 10:00:00.521-06:00');
INSERT INTO hist_cfg_alhaja_listado_valor_comercial_oro (id, ultima_actualizacion) VALUES (3, '2016-11-21 11:00:00.521-06:00');

INSERT INTO hist_cfg_alhaja_valor_comercial_oro (id, color, kilataje, precio, listado) VALUES (1, 'Amarillo', 10, 70.250, 1);
INSERT INTO hist_cfg_alhaja_valor_comercial_oro (id, color, kilataje, precio, listado) VALUES (2, 'Amarillo', 10, 80.250, 2);
INSERT INTO hist_cfg_alhaja_valor_comercial_oro (id, color, kilataje, precio, listado) VALUES (3, 'Blanco', 10, 75.250, 2);
INSERT INTO hist_cfg_alhaja_valor_comercial_oro (id, color, kilataje, precio, listado) VALUES (4, 'Amarillo', 10, 90.250, 3);
INSERT INTO hist_cfg_alhaja_valor_comercial_oro (id, color, kilataje, precio, listado) VALUES (5, 'Amarillo', 14, 140.250, 3);
INSERT INTO hist_cfg_alhaja_valor_comercial_oro (id, color, kilataje, precio, listado) VALUES (6, 'Blanco', 10, 85.250, 3);

--
-- VIGENTES
--
INSERT INTO cfg_alhaja_listado_valor_comercial_oro (id, ultima_actualizacion) VALUES (4, '2016-11-23 10:00:00.521-06:00');

INSERT INTO cfg_alhaja_valor_comercial_oro (id, color, kilataje, precio, listado) VALUES (1, 'Amarillo', 10, 100.250, 4);
INSERT INTO cfg_alhaja_valor_comercial_oro (id, color, kilataje, precio, listado) VALUES (2, 'Amarillo', 14, 150.250, 4);
INSERT INTO cfg_alhaja_valor_comercial_oro (id, color, kilataje, precio, listado) VALUES (3, 'Blanco', 10, 105.250, 4);
INSERT INTO cfg_alhaja_valor_comercial_oro (id, color, kilataje, precio, listado) VALUES (4, 'Blanco', 14, 155.250, 4);

------------------------------------------------------------------------------------------------------------------------
-- TERMINA - DATOS DE PRUEBA: VALOR COMERCIAL 'ORO'
------------------------------------------------------------------------------------------------------------------------


------------------------------------------------------------------------------------------------------------------------
-- INICIA - DATOS DE PRUEBA: VALOR COMERCIAL 'METAL'
------------------------------------------------------------------------------------------------------------------------

--
-- HISTÓRICOS
--
INSERT INTO hist_cfg_alhaja_listado_valor_comercial_metal (id, ultima_actualizacion) VALUES (1, '2016-11-20 10:00:00.521-06:00');
INSERT INTO hist_cfg_alhaja_listado_valor_comercial_metal (id, ultima_actualizacion) VALUES (2, '2016-11-21 10:00:00.521-06:00');
INSERT INTO hist_cfg_alhaja_listado_valor_comercial_metal (id, ultima_actualizacion) VALUES (3, '2016-11-21 11:00:00.521-06:00');

INSERT INTO hist_cfg_alhaja_valor_comercial_metal (id, metal, calidad, precio, listado) VALUES (1, 'Plata', '0.925', 70.250, 1);
INSERT INTO hist_cfg_alhaja_valor_comercial_metal (id, metal, calidad, precio, listado) VALUES (2, 'Plata', '0.925', 80.250, 2);
INSERT INTO hist_cfg_alhaja_valor_comercial_metal (id, metal, calidad, precio, listado) VALUES (3, 'Bronce', '0.925', 75.250, 2);
INSERT INTO hist_cfg_alhaja_valor_comercial_metal (id, metal, calidad, precio, listado) VALUES (4, 'Plata', '0.925', 90.250, 3);
INSERT INTO hist_cfg_alhaja_valor_comercial_metal (id, metal, calidad, precio, listado) VALUES (5, 'Plata', '0.725', 140.250, 3);
INSERT INTO hist_cfg_alhaja_valor_comercial_metal (id, metal, calidad, precio, listado) VALUES (6, 'Bronce', '0.925', 85.250, 3);

--
-- VIGENTES
--
INSERT INTO cfg_alhaja_listado_valor_comercial_metal (id, ultima_actualizacion) VALUES (4, '2016-11-23 10:00:00.521-06:00');

INSERT INTO cfg_alhaja_valor_comercial_metal (id, metal, calidad, precio, listado) VALUES (1, 'Plata', '0.925', 100.250, 4);
INSERT INTO cfg_alhaja_valor_comercial_metal (id, metal, calidad, precio, listado) VALUES (2, 'Plata', '0.725', 150.250, 4);
INSERT INTO cfg_alhaja_valor_comercial_metal (id, metal, calidad, precio, listado) VALUES (3, 'Bronce', '0.925', 105.250, 4);
INSERT INTO cfg_alhaja_valor_comercial_metal (id, metal, calidad, precio, listado) VALUES (4, 'Bronce', '0.725', 155.250, 4);

------------------------------------------------------------------------------------------------------------------------
-- TERMINA - DATOS DE PRUEBA: VALOR COMERCIAL 'METAL'
------------------------------------------------------------------------------------------------------------------------
