-- ----------------------------------------------------------------------------------------------------------------------
-- INICIO - TABLAS SISTEMA DE OPERACION PRENDARIA EMERGENTE
-- ----------------------------------------------------------------------------------------------------------------------
INSERT INTO cat_tipo_hechura (id, abreviatura, descripcion) VALUES (1, 'F1', 'Factor 1');
INSERT INTO cat_tipo_hechura (id, abreviatura, descripcion) VALUES (2, 'DE', 'Diseño y Estado');
INSERT INTO cat_tipo_hechura (id, abreviatura, descripcion) VALUES (3, 'MC', 'Marca');
INSERT INTO cat_tipo_hechura (id, abreviatura, descripcion) VALUES (4, 'MO', 'Monedas con Oro');
INSERT INTO cat_tipo_hechura (id, abreviatura, descripcion) VALUES (5, 'MS', 'Monedas sin Oro');
INSERT INTO cat_tipo_hechura (id, abreviatura, descripcion) VALUES (6, 'RF', 'RJ-Fundir');
INSERT INTO cat_tipo_hechura (id, abreviatura, descripcion) VALUES (7, 'RM', 'RJ-Marca');
INSERT INTO cat_tipo_hechura (id, abreviatura, descripcion) VALUES (8, 'F1', 'Pedaceria y Piezas Rotas');
INSERT INTO cat_tipo_hechura (id, abreviatura, descripcion) VALUES (9, 'F2', 'Buen Estado Personalizadas');
INSERT INTO cat_tipo_hechura (id, abreviatura, descripcion) VALUES (10, 'F3', 'Buen Estado Sin Personalizar');
INSERT INTO cat_tipo_hechura (id, abreviatura, descripcion) VALUES (11, 'F4', 'Piezas Nuevas');
INSERT INTO cat_tipo_hechura (id, abreviatura, descripcion) VALUES (12, 'F5', 'Marcas Comerciales');
INSERT INTO cat_tipo_hechura (id, abreviatura, descripcion) VALUES (13, 'F6', 'Alta Joyería');
INSERT INTO cat_tipo_hechura (id, abreviatura, descripcion) VALUES (14, 'F1', 'Rotas y Pedaceria');
INSERT INTO cat_tipo_hechura (id, abreviatura, descripcion) VALUES (15, 'F2', 'Personalizado');
INSERT INTO cat_tipo_hechura (id, abreviatura, descripcion) VALUES (16, 'F3', 'Sin Personalizar');
INSERT INTO cat_tipo_hechura (id, abreviatura, descripcion) VALUES (17, 'F4', 'Buen Estado Sin Personalizar');
INSERT INTO cat_tipo_hechura (id, abreviatura, descripcion) VALUES (18, 'F5', 'Marcas Comerciales y Piezas Nuevas');
INSERT INTO cat_tipo_hechura (id, abreviatura, descripcion) VALUES (19, 'F6', 'Alta Joyeria');

INSERT INTO cfg_metal_calidad_subramo (id, metal, calidad, subramo) VALUES (1, 'AU', '8_Q', 'AL');
INSERT INTO cfg_metal_calidad_subramo (id, metal, calidad, subramo) VALUES (2, 'AU', '10_Q', 'AL');
INSERT INTO cfg_metal_calidad_subramo (id, metal, calidad, subramo) VALUES (3, 'AU', '12_Q', 'AL');
INSERT INTO cfg_metal_calidad_subramo (id, metal, calidad, subramo) VALUES (4, 'AU', '14_Q', 'AL');
INSERT INTO cfg_metal_calidad_subramo (id, metal, calidad, subramo) VALUES (5, 'AU', '16_Q', 'AL');
INSERT INTO cfg_metal_calidad_subramo (id, metal, calidad, subramo) VALUES (6, 'AU', '18_Q', 'AL');
INSERT INTO cfg_metal_calidad_subramo (id, metal, calidad, subramo) VALUES (7, 'AU', '22_Q', 'AL');
INSERT INTO cfg_metal_calidad_subramo (id, metal, calidad, subramo) VALUES (8, 'AU', '24_Q', 'AL');
INSERT INTO cfg_metal_calidad_subramo (id, metal, calidad, subramo) VALUES (9, 'PD', null, 'AL');

INSERT INTO cfg_metal_calidad_subramo_rango (id, metal_calidad_subramo, tipo_hechura) VALUES (1, 1, 14);
INSERT INTO cfg_metal_calidad_subramo_rango (id, metal_calidad_subramo, tipo_hechura) VALUES (2, 1, 15);
INSERT INTO cfg_metal_calidad_subramo_rango (id, metal_calidad_subramo, tipo_hechura) VALUES (3, 1, 16);
INSERT INTO cfg_metal_calidad_subramo_rango (id, metal_calidad_subramo, tipo_hechura) VALUES (4, 1, 17);
INSERT INTO cfg_metal_calidad_subramo_rango (id, metal_calidad_subramo, tipo_hechura) VALUES (5, 2, 14);
INSERT INTO cfg_metal_calidad_subramo_rango (id, metal_calidad_subramo, tipo_hechura) VALUES (6, 2, 15);
INSERT INTO cfg_metal_calidad_subramo_rango (id, metal_calidad_subramo, tipo_hechura) VALUES (7, 2, 16);
INSERT INTO cfg_metal_calidad_subramo_rango (id, metal_calidad_subramo, tipo_hechura) VALUES (8, 2, 17);
INSERT INTO cfg_metal_calidad_subramo_rango (id, metal_calidad_subramo, tipo_hechura) VALUES (9, 2, 18);
INSERT INTO cfg_metal_calidad_subramo_rango (id, metal_calidad_subramo, tipo_hechura) VALUES (10, 3, 14);
INSERT INTO cfg_metal_calidad_subramo_rango (id, metal_calidad_subramo, tipo_hechura) VALUES (11, 3, 15);
INSERT INTO cfg_metal_calidad_subramo_rango (id, metal_calidad_subramo, tipo_hechura) VALUES (12, 3, 16);
INSERT INTO cfg_metal_calidad_subramo_rango (id, metal_calidad_subramo, tipo_hechura) VALUES (13, 3, 17);
INSERT INTO cfg_metal_calidad_subramo_rango (id, metal_calidad_subramo, tipo_hechura) VALUES (14, 3, 18);
INSERT INTO cfg_metal_calidad_subramo_rango (id, metal_calidad_subramo, tipo_hechura) VALUES (15, 4, 14);
INSERT INTO cfg_metal_calidad_subramo_rango (id, metal_calidad_subramo, tipo_hechura) VALUES (16, 4, 15);
INSERT INTO cfg_metal_calidad_subramo_rango (id, metal_calidad_subramo, tipo_hechura) VALUES (17, 4, 16);
INSERT INTO cfg_metal_calidad_subramo_rango (id, metal_calidad_subramo, tipo_hechura) VALUES (18, 4, 17);
INSERT INTO cfg_metal_calidad_subramo_rango (id, metal_calidad_subramo, tipo_hechura) VALUES (19, 4, 18);
INSERT INTO cfg_metal_calidad_subramo_rango (id, metal_calidad_subramo, tipo_hechura) VALUES (20, 5, 14);
INSERT INTO cfg_metal_calidad_subramo_rango (id, metal_calidad_subramo, tipo_hechura) VALUES (21, 5, 15);
INSERT INTO cfg_metal_calidad_subramo_rango (id, metal_calidad_subramo, tipo_hechura) VALUES (22, 5, 16);
INSERT INTO cfg_metal_calidad_subramo_rango (id, metal_calidad_subramo, tipo_hechura) VALUES (23, 5, 17);
INSERT INTO cfg_metal_calidad_subramo_rango (id, metal_calidad_subramo, tipo_hechura) VALUES (24, 5, 18);
INSERT INTO cfg_metal_calidad_subramo_rango (id, metal_calidad_subramo, tipo_hechura) VALUES (25, 6, 14);
INSERT INTO cfg_metal_calidad_subramo_rango (id, metal_calidad_subramo, tipo_hechura) VALUES (26, 6, 15);
INSERT INTO cfg_metal_calidad_subramo_rango (id, metal_calidad_subramo, tipo_hechura) VALUES (27, 6, 16);
INSERT INTO cfg_metal_calidad_subramo_rango (id, metal_calidad_subramo, tipo_hechura) VALUES (28, 6, 17);
INSERT INTO cfg_metal_calidad_subramo_rango (id, metal_calidad_subramo, tipo_hechura) VALUES (29, 6, 18);
INSERT INTO cfg_metal_calidad_subramo_rango (id, metal_calidad_subramo, tipo_hechura) VALUES (30, 6, 19);
INSERT INTO cfg_metal_calidad_subramo_rango (id, metal_calidad_subramo, tipo_hechura) VALUES (31, 7, 14);
INSERT INTO cfg_metal_calidad_subramo_rango (id, metal_calidad_subramo, tipo_hechura) VALUES (32, 7, 15);
INSERT INTO cfg_metal_calidad_subramo_rango (id, metal_calidad_subramo, tipo_hechura) VALUES (33, 7, 16);
INSERT INTO cfg_metal_calidad_subramo_rango (id, metal_calidad_subramo, tipo_hechura) VALUES (34, 7, 17);
INSERT INTO cfg_metal_calidad_subramo_rango (id, metal_calidad_subramo, tipo_hechura) VALUES (35, 7, 18);
INSERT INTO cfg_metal_calidad_subramo_rango (id, metal_calidad_subramo, tipo_hechura) VALUES (36, 7, 19);
INSERT INTO cfg_metal_calidad_subramo_rango (id, metal_calidad_subramo, tipo_hechura) VALUES (37, 8, 14);
INSERT INTO cfg_metal_calidad_subramo_rango (id, metal_calidad_subramo, tipo_hechura) VALUES (38, 8, 15);
INSERT INTO cfg_metal_calidad_subramo_rango (id, metal_calidad_subramo, tipo_hechura) VALUES (39, 8, 16);
INSERT INTO cfg_metal_calidad_subramo_rango (id, metal_calidad_subramo, tipo_hechura) VALUES (40, 8, 17);
INSERT INTO cfg_metal_calidad_subramo_rango (id, metal_calidad_subramo, tipo_hechura) VALUES (41, 8, 18);
INSERT INTO cfg_metal_calidad_subramo_rango (id, metal_calidad_subramo, tipo_hechura) VALUES (42, 8, 19);
INSERT INTO cfg_metal_calidad_subramo_rango (id, metal_calidad_subramo, tipo_hechura) VALUES (43, 9, 1);

-- ----------------------------------------------------------------------------------------------------------------------
-- FIN - TABLAS SISTEMA DE OPERACION PRENDARIA EMERGENTE
-- ----------------------------------------------------------------------------------------------------------------------
