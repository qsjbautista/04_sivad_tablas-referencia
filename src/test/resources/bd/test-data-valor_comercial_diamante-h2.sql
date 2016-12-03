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


--
-- FACTOR VALOR DIAMANTE
--
INSERT INTO cfg_diamante_factor (factor_minimo, factor_medio, factor_maximo, fecha_carga, fecha_listado) VALUES (0.10, 0.20, 0.30, '2016-11-23T07:19:15.521-06:00', '2016-11-23');
INSERT INTO cfg_diamante_factor (factor_minimo, factor_medio, factor_maximo, fecha_carga, fecha_listado) VALUES (0.20, 0.30, 0.40, '2016-11-24T07:52:51.312-06:00', '2016-11-24');
INSERT INTO cfg_diamante_factor (factor_minimo, factor_medio, factor_maximo, fecha_carga, fecha_listado) VALUES (0.30, 0.40, 0.50, '2016-11-24T07:59:41.143-06:00', '2016-11-24');
