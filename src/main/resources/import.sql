INSERT INTO `roles` VALUES (1,'ROLE_GESTOR'),(2,'ROLE_ADMIN'),(3,'ROLE_REPONEDOR'),(4,'ROLE_TABLET');

INSERT INTO `accounts` VALUES ('02d20153-00d1-4693-bb08-bd6a3d2c0d0c','Cuenta QuironSalud'),('903d4f14-a98a-40dd-82e3-6e488936741c','Cuenta 20');

INSERT INTO `users` VALUES ('09677904-ad47-47ab-814f-bcc069ff034d','oscar@email.com','oscar','$2a$10$KijuNXqYd2b0tzClvfAdK.8N5h/FkX.YFjJdZcqY3uIWDJebzJsVC','opbaquero','02d20153-00d1-4693-bb08-bd6a3d2c0d0c'),('46f38bb2-7823-42b0-8722-7be8fff51864','raul@email.com','raul','$2a$10$EkjxDkQEXNbNWnAY0d99J.ehmgb1HIbp6HjPKw2ke4g3VLYPts.6O','raul','02d20153-00d1-4693-bb08-bd6a3d2c0d0c'),('6e2ccb1c-b7ea-429f-be43-13683ae439c9','admin@email.com','admin','$2a$10$G.xXxFw5ssT0uzHUbaRlLe7zck3L/O/GMe63oCIPaRVn3yMSAd9ZC','admin',NULL),('db7da56f-0fc3-4176-863d-a1e89609df0a','tablet@email.com','tablet1','$2a$10$dOR8DDtjWEK43.hWOVOuMufwIDxVSVg8gQ0GVjv/kjig9sLPaCzam','tablet1','02d20153-00d1-4693-bb08-bd6a3d2c0d0c');

INSERT INTO `usuario_rol` VALUES ('46f38bb2-7823-42b0-8722-7be8fff51864',1),('6e2ccb1c-b7ea-429f-be43-13683ae439c9',1),('6e2ccb1c-b7ea-429f-be43-13683ae439c9',2),('09677904-ad47-47ab-814f-bcc069ff034d',3),('6e2ccb1c-b7ea-429f-be43-13683ae439c9',3),('db7da56f-0fc3-4176-863d-a1e89609df0a',4);

INSERT INTO `hospitals` VALUES ('2964cb79-a389-416f-9c63-5b1d5ae4e6bb','C de arriba','Ourense','Hospital de ourense','123456789','Ourense',36003,'02d20153-00d1-4693-bb08-bd6a3d2c0d0c'),('8187f5f7-c755-443e-b45e-c55d586ca20d','C de arriba','Pontecaldelas','Hospital de Pontevedra','123456789','Pontevedra',36003,'02d20153-00d1-4693-bb08-bd6a3d2c0d0c');

INSERT INTO `buildings` VALUES ('28bc3ad3-0f91-4cce-bcf8-15d91b89d319','Ala este (Edificio 1)','8187f5f7-c755-443e-b45e-c55d586ca20d'),('32c78182-3c18-4e2e-a29d-3c87d13f8a86','Ala este (Edificio 2)','2964cb79-a389-416f-9c63-5b1d5ae4e6bb'),('4b36938e-5249-4d60-92bb-5f0c05aed23f','Ala este (Edificio 3)','2964cb79-a389-416f-9c63-5b1d5ae4e6bb'),('59a6d4e5-21a3-4eb8-afc1-e9c70cd8ad7b','Ala este (Edificio 1)','2964cb79-a389-416f-9c63-5b1d5ae4e6bb'),('7e50d31d-16cf-422a-8171-004318cbc798','Ala este (Edificio 2)','8187f5f7-c755-443e-b45e-c55d586ca20d');

INSERT INTO `products` VALUES ('041c363b-c1f3-4022-8609-3e2bf7561df6','bendas','903d4f14-a98a-40dd-82e3-6e488936741c'),('5c70937e-e8d0-45a2-83d4-cf1af41ea25c','Jeringas','903d4f14-a98a-40dd-82e3-6e488936741c');

INSERT INTO `warehouses` VALUES ('dc580baa-b5bd-11eb-8529-0242ac130003',1,'Almacen 1', '28bc3ad3-0f91-4cce-bcf8-15d91b89d319'),('dc580dd0-b5bd-11eb-8529-0242ac130003',1,'Almacen 2', '28bc3ad3-0f91-4cce-bcf8-15d91b89d319'),('dc580eb6-b5bd-11eb-8529-0242ac130003',2,'Almacen 3', '28bc3ad3-0f91-4cce-bcf8-15d91b89d319'),('dc580f92-b5bd-11eb-8529-0242ac130003',1,'almacen 4', '32c78182-3c18-4e2e-a29d-3c87d13f8a86');

--INSERT INTO `warehouse_products` VALUES ('4f95526a-4e2f-4f71-83c3-92d0a5e55a3e', 34, '041c363b-c1f3-4022-8609-3e2bf7561df6', 'dc580eb6-b5bd-11eb-8529-0242ac130003');