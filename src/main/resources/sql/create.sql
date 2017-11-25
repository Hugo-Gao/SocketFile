use socketdb;
CREATE TABLE file_info(
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `user_id` int NOT NULL ,
  `pic_info` MEDIUMBLOB NOT NULL
)