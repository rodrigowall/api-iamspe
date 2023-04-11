INSERT INTO `role` VALUES (1,'ADMIN');
INSERT INTO `user` (user_id,active,email,last_name,name,password,data_cadastro) VALUES (1,1,'rodrigo.parede@gmail.com','Parede','Rodrigo','$2a$10$kr293cQtalLcDGB5rwK03u.DIRcB1/Beozy/ZT4LcTnJLY2yQHYNG',NULL);
INSERT INTO `user_role` (user_id,role_id) VALUES (1,1);
--senha 123456