--
-- Utilizado para poblar la BD (mysql) utilizada con los perfiles local y cloud.
--



-- ----------------------------------------------------------------------------------------------------------------------
-- INICIA - DATOS: VALOR COMERCIAL 'METAL'
-- ----------------------------------------------------------------------------------------------------------------------
INSERT INTO cfg_alhaja_listado_valor_comercial_metal (id, ultima_actualizacion)
VALUES (1, '2016-12-20 10:00:00.521');

INSERT INTO cfg_alhaja_valor_comercial_metal (id, metal, calidad, precio, listado)
VALUES (1, 'PT', 'CL_999', 548.000, 1);
INSERT INTO cfg_alhaja_valor_comercial_metal (id, metal, calidad, precio, listado)
VALUES (2, 'PT', 'CL_950', 520.600, 1);
INSERT INTO cfg_alhaja_valor_comercial_metal (id, metal, calidad, precio, listado)
VALUES (3, 'PT', 'CL_900', 493.200, 1);
INSERT INTO cfg_alhaja_valor_comercial_metal (id, metal, calidad, precio, listado)
VALUES (4, 'PT', 'CL_850', 465.800, 1);
INSERT INTO cfg_alhaja_valor_comercial_metal (id, metal, calidad, precio, listado)
VALUES (5, 'PT', 'CL_800', 438.400, 1);
INSERT INTO cfg_alhaja_valor_comercial_metal (id, metal, calidad, precio, listado)
VALUES (6, 'AG', 'CL_999', 8.000, 1);
INSERT INTO cfg_alhaja_valor_comercial_metal (id, metal, calidad, precio, listado)
VALUES (7, 'AG', 'CL_925', 7.400, 1);
INSERT INTO cfg_alhaja_valor_comercial_metal (id, metal, calidad, precio, listado)
VALUES (8, 'AG', 'CL_900', 7.200, 1);
INSERT INTO cfg_alhaja_valor_comercial_metal (id, metal, calidad, precio, listado)
VALUES (9, 'AG', 'CL_720', 5.760, 1);
INSERT INTO cfg_alhaja_valor_comercial_metal (id, metal, calidad, precio, listado)
VALUES (10, 'AG', 'CL_500', 4.000, 1);
INSERT INTO cfg_alhaja_valor_comercial_metal (id, metal, calidad, precio, listado)
VALUES (11, 'PD', null, 50.000, 1);
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
VALUES (1, 1.000, 1.400, 1.550, '2017-05-02 12:56:00.521', '2017-05-02');
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
-- ----------------------------------------------------------------------------------------------------------------------
-- TERMINA - DATOS: MODIFICADOR CERTIFICADO
-- ----------------------------------------------------------------------------------------------------------------------



-- ----------------------------------------------------------------------------------------------------------------------
-- INICIA - DATOS: MODIFICADORES DE FACTORES DE ALHAJAS.
-- ----------------------------------------------------------------------------------------------------------------------
INSERT INTO cfg_alhaja_listado_factor (id, ultima_actualizacion, fecha_listado)
VALUES (1, '2016-12-20 10:00:00.521', '2016-12-20');

INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (1, 'AU', '8_Q', 'F1', 1.10, 0.00, -4.00, -4.00, '2016-12-20 10:00:00.521', 1, 0.00, 0.00, 0.00);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (2, 'AU', '8_Q', 'F2', 1.25, 0.00, 5.00, 20.00, '2016-12-20 10:00:00.521', 1, 0.00, 0.00, 5.00);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (3, 'AU', '8_Q', 'F3', 1.35, 5.00, 10.00, 15.00, '2016-12-20 10:00:00.521', 1, 0.00, 5.00, 5.00);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (4, 'AU', '8_Q', 'F4', 1.40, 15.00, 20.00, 30.00, '2016-12-20 10:00:00.521', 1, 5.00, 5.00, 5.00);

INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (5, 'AU', '10_Q', 'F1', 1.10, 0.00, -4.00, -4.00, '2016-12-20 10:00:00.521', 1, 0.00, 0.00, 0.00);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (6, 'AU', '10_Q', 'F2', 1.25, 0.00, 5.00, 20.00, '2016-12-20 10:00:00.521', 1, 0.00, 0.00, 5.00);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (7, 'AU', '10_Q', 'F3', 1.35, 5.00, 10.00, 15.00, '2016-12-20 10:00:00.521', 1, 0.00, 5.00, 5.00);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (8, 'AU', '10_Q', 'F4', 1.40, 15.00, 20.00, 30.00, '2016-12-20 10:00:00.521', 1, 5.00, 5.00, 5.00);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (9, 'AU', '10_Q', 'F5', 1.50, 15.00, 25.00, 55.00, '2016-12-20 10:00:00.521', 1, 5.00, 5.00, 5.00);

INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (10, 'AU', '12_Q', 'F1', 1.10, 0.00, -4.00, -4.00, '2016-12-20 10:00:00.521', 1, 0.00, 0.00, 0.00);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (11, 'AU', '12_Q', 'F2', 1.25, 0.00, 5.00, 20.00, '2016-12-20 10:00:00.521', 1, 0.00, 0.00, 5.00);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (12, 'AU', '12_Q', 'F3', 1.35, 5.00, 10.00, 15.00, '2016-12-20 10:00:00.521', 1, 0.00, 5.00, 5.00);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (13, 'AU', '12_Q', 'F4', 1.40, 15.00, 20.00, 30.00, '2016-12-20 10:00:00.521', 1, 5.00, 5.00, 5.00);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (14, 'AU', '12_Q', 'F5', 1.50, 15.00, 25.00, 55.00, '2016-12-20 10:00:00.521', 1, 5.00, 5.00, 5.00);

INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (15, 'AU', '14_Q', 'F1', 1.10, 0.00, -4.00, -4.00, '2016-12-20 10:00:00.521', 1, 0.00, 0.00, 0.00);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (16, 'AU', '14_Q', 'F2', 1.25, 0.00, 5.00, 20.00, '2016-12-20 10:00:00.521', 1, 0.00, 0.00, 5.00);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (17, 'AU', '14_Q', 'F3', 1.35, 5.00, 10.00, 15.00, '2016-12-20 10:00:00.521', 1, 0.00, 5.00, 5.00);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (18, 'AU', '14_Q', 'F4', 1.40, 15.00, 20.00, 30.00, '2016-12-20 10:00:00.521', 1, 5.00, 5.00, 5.00);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (19, 'AU', '14_Q', 'F5', 1.50, 15.00, 25.00, 55.00, '2016-12-20 10:00:00.521', 1, 5.00, 5.00, 5.00);


INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (20, 'AU', '16_Q', 'F1', 1.10, 0.00, -4.00, -4.00, '2016-12-20 10:00:00.521', 1, 0.00, 0.00, 0.00);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (21, 'AU', '16_Q', 'F2', 1.25, 0.00, 5.00, 20.00, '2016-12-20 10:00:00.521', 1, 0.00, 0.00, 5.00);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (22, 'AU', '16_Q', 'F3', 1.35, 5.00, 10.00, 15.00, '2016-12-20 10:00:00.521', 1, 0.00, 5.00, 5.00);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (23, 'AU', '16_Q', 'F4', 1.40, 15.00, 20.00, 30.00, '2016-12-20 10:00:00.521', 1, 5.00, 5.00, 5.00);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (24, 'AU', '16_Q', 'F5', 1.50, 15.00, 25.00, 55.00, '2016-12-20 10:00:00.521', 1, 5.00, 5.00, 5.00);

INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (25, 'AU', '18_Q', 'F1', 1.10, 0.00, -4.00, -4.00, '2016-12-20 10:00:00.521', 1, 0.00, 0.00, 0.00);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (26, 'AU', '18_Q', 'F2', 1.25, 0.00, 5.00, 20.00, '2016-12-20 10:00:00.521', 1, 0.00, 0.00, 5.00);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (27, 'AU', '18_Q', 'F3', 1.35, 5.00, 10.00, 15.00, '2016-12-20 10:00:00.521', 1, 0.00, 5.00, 5.00);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (28, 'AU', '18_Q', 'F4', 1.40, 15.00, 20.00, 30.00, '2016-12-20 10:00:00.521', 1, 5.00, 5.00, 5.00);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (29, 'AU', '18_Q', 'F5', 1.50, 15.00, 25.00, 55.00, '2016-12-20 10:00:00.521', 1, 5.00, 5.00, 5.00);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (30, 'AU', '18_Q', 'F6', 1.60, 30.00, 70.00, 110.00, '2016-12-20 10:00:00.521', 1, 5.00, 5.00, 10.00);

INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (31, 'AU', '22_Q', 'F1', 1.10, 0.00, -4.00, -4.00, '2016-12-20 10:00:00.521', 1, 0.00, 0.00, 0.00);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (32, 'AU', '22_Q', 'F2', 1.25, 0.00, 5.00, 20.00, '2016-12-20 10:00:00.521', 1, 0.00, 0.00, 5.00);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (33, 'AU', '22_Q', 'F3', 1.35, 5.00, 10.00, 15.00, '2016-12-20 10:00:00.521', 1, 0.00, 5.00, 5.00);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (34, 'AU', '22_Q', 'F4', 1.40, 15.00, 20.00, 30.00, '2016-12-20 10:00:00.521', 1, 5.00, 5.00, 5.00);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (35, 'AU', '22_Q', 'F5', 1.50, 15.00, 25.00, 55.00, '2016-12-20 10:00:00.521', 1, 5.00, 5.00, 5.00);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (36, 'AU', '22_Q', 'F6', 1.60, 30.00, 70.00, 110.00, '2016-12-20 10:00:00.521', 1, 5.00, 5.00, 10.00);

INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (37, 'AU', '24_Q', 'F1', 1.10, 0.00, -4.00, -4.00, '2016-12-20 10:00:00.521', 1, 0.00, 0.00, 0.00);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (38, 'AU', '24_Q', 'F2', 1.25, 0.00, 5.00, 20.00, '2016-12-20 10:00:00.521', 1, 0.00, 0.00, 5.00);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (39, 'AU', '24_Q', 'F3', 1.35, 5.00, 10.00, 15.00, '2016-12-20 10:00:00.521', 1, 0.00, 5.00, 5.00);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (40, 'AU', '24_Q', 'F4', 1.40, 15.00, 20.00, 30.00, '2016-12-20 10:00:00.521', 1, 5.00, 5.00, 5.00);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (41, 'AU', '24_Q', 'F5', 1.50, 15.00, 25.00, 55.00, '2016-12-20 10:00:00.521', 1, 5.00, 5.00, 5.00);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (42, 'AU', '24_Q', 'F6', 1.60, 30.00, 70.00, 110.00, '2016-12-20 10:00:00.521', 1, 5.00, 5.00, 10.00);

INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (43, 'PT', 'CL_999', 'F1', 1.10, 0.00, 0.00, 0.00, '2016-12-20 10:00:00.521', 1, 0.00, 0.00, 0.00);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (44, 'PT', 'CL_999', 'DE', 1.20, 0.00, 0.00, 0.00, '2016-12-20 10:00:00.521', 1, 0.00, 0.00, 0.00);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (45, 'PT', 'CL_999', 'MC', 1.30, 0.00, 0.00, 0.00, '2016-12-20 10:00:00.521', 1, 0.00, 0.00, 0.00);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (46, 'PT', 'CL_950', 'F1', 1.10, 0.00, 0.00, 0.00, '2016-12-20 10:00:00.521', 1, 0.00, 0.00, 0.00);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (47, 'PT', 'CL_950', 'DE', 1.20, 0.00, 0.00, 0.00, '2016-12-20 10:00:00.521', 1, 0.00, 0.00, 0.00);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (48, 'PT', 'CL_950', 'MC', 1.30, 0.00, 0.00, 0.00, '2016-12-20 10:00:00.521', 1, 0.00, 0.00, 0.00);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (49, 'PT', 'CL_900', 'F1', 1.10, 0.00, 0.00, 0.00, '2016-12-20 10:00:00.521', 1, 0.00, 0.00, 0.00);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (50, 'PT', 'CL_900', 'DE', 1.20, 0.00, 0.00, 0.00, '2016-12-20 10:00:00.521', 1, 0.00, 0.00, 0.00);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (51, 'PT', 'CL_900', 'MC', 1.30, 0.00, 0.00, 0.00, '2016-12-20 10:00:00.521', 1, 0.00, 0.00, 0.00);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (52, 'PT', 'CL_850', 'F1', 1.10, 0.00, 0.00, 0.00, '2016-12-20 10:00:00.521', 1, 0.00, 0.00, 0.00);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (53, 'PT', 'CL_850', 'DE', 1.20, 0.00, 0.00, 0.00, '2016-12-20 10:00:00.521', 1, 0.00, 0.00, 0.00);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (54, 'PT', 'CL_850', 'MC', 1.30, 0.00, 0.00, 0.00, '2016-12-20 10:00:00.521', 1, 0.00, 0.00, 0.00);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (55, 'PT', 'CL_800', 'F1', 1.10, 0.00, 0.00, 0.00, '2016-12-20 10:00:00.521', 1, 0.00, 0.00, 0.00);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (56, 'PT', 'CL_800', 'DE', 1.20, 0.00, 0.00, 0.00, '2016-12-20 10:00:00.521', 1, 0.00, 0.00, 0.00);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (57, 'PT', 'CL_800', 'MC', 1.30, 0.00, 0.00, 0.00, '2016-12-20 10:00:00.521', 1, 0.00, 0.00, 0.00);

INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (58, 'PD', NULL, 'F1', 1.00, 0.00, 0.00, 0.00, '2016-12-20 10:00:00.521', 1, 0.00, 0.00, 0.00);

INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (59, 'AG', 'CL_999', 'F1', 1.00, 0.00, 0.00, 0.00, '2016-12-20 10:00:00.521', 1, 0.00, 0.00, 0.00);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (60, 'AG', 'CL_925', 'F1', 1.00, 0.00, 0.00, 0.00, '2016-12-20 10:00:00.521', 1, 0.00, 0.00, 0.00);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (61, 'AG', 'CL_900', 'F1', 1.00, 0.00, 0.00, 0.00, '2016-12-20 10:00:00.521', 1, 0.00, 0.00, 0.00);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (62, 'AG', 'CL_720', 'F1', 1.00, 0.00, 0.00, 0.00, '2016-12-20 10:00:00.521', 1, 0.00, 0.00, 0.00);
INSERT INTO cfg_factor_alhaja (id, metal, calidad, rango, factor, desplazamiento_limite_superior, limite_inferior, limite_superior, ultima_actualizacion, listado, desplazamiento_limite_inferior, desplazamiento_incremento, incremento)
VALUES (63, 'AG', 'CL_500', 'F1', 1.00, 0.00, 0.00, 0.00, '2016-12-20 10:00:00.521', 1, 0.00, 0.00, 0.00);
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
INSERT INTO `cfg_diamante_factor_rapaport` (`ID`,`FACTOR`,`FECHA`) VALUES (1,1,'2018-08-28 00:00:00');

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
INSERT INTO `cfg_diamante_porcentaje_castigo_x_tipo_corte` (`id`,`fecha`,`corte`,`factor`) VALUES (7,'2018-09-05 00:00:00','Flandes',0.8000);
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










