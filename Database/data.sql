USE `lockscreen_db`;

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','admin','admin','admin',1),(2,'client','client','client','client',2),(3,'seller','seller','seller','seller',3);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `advertising` WRITE;
/*!40000 ALTER TABLE `advertising` DISABLE KEYS */;
INSERT INTO `advertising` VALUES (1,'footwear','111',NULL,3),(2,'shirt','222',NULL,3);
/*!40000 ALTER TABLE `advertising` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `count_of_clicks` WRITE;
/*!40000 ALTER TABLE `count_of_clicks` DISABLE KEYS */;
INSERT INTO `count_of_clicks` VALUES (1,'1'),(2,'1');
/*!40000 ALTER TABLE `count_of_clicks` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `history_of_clicks` WRITE;
/*!40000 ALTER TABLE `history_of_clicks` DISABLE KEYS */;
INSERT INTO `history_of_clicks` VALUES (1,'2016-08-03 20:09:01',2,1),(2,'2016-08-04 20:09:01',2,2);
/*!40000 ALTER TABLE `history_of_clicks` ENABLE KEYS */;
UNLOCK TABLES;