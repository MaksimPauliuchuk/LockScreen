DROP DATABASE IF EXISTS `lockscreen_db`;
CREATE DATABASE    IF NOT EXISTS `lockscreen_db` DEFAULT CHARACTER SET utf8;
USE `lockscreen_db`;

-- -----------------------------
-- SHEMA
-- -----------------------------

DROP TABLE IF EXISTS `groups_users`;
CREATE TABLE `groups_users` (
    `id` INT(10) unsigned NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
    `id` INT(11) unsigned NOT NULL AUTO_INCREMENT,
    `login` VARCHAR(45) NOT NULL,
    `password` VARCHAR(45) NOT NULL,
    `email` VARCHAR(45) NOT NULL,
    `phone` VARCHAR(45) NOT NULL,
    `fk_group` INT(11) unsigned NOT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_group_idx` (`fk_group`),
    CONSTRAINT `fk_groups_users_u` FOREIGN KEY (`fk_group`) REFERENCES `groups_users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

DROP TABLE IF EXISTS `tokens`;
CREATE TABLE `tokens` (
    `id` INT(10) unsigned NOT NULL AUTO_INCREMENT,
    `token` VARCHAR(45) NOT NULL,
    `time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `fk_user` INT(10) unsigned NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `token_UNIQUE` (`token`),
    KEY `fk_user_t_idx` (`fk_user`),
    CONSTRAINT `fk_user_t` FOREIGN KEY (`fk_user`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

DROP TABLE IF EXISTS `advertising`;
CREATE TABLE `advertising` (
    `id` INT(10) unsigned NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(45) NOT NULL,
    `comment` VARCHAR(250) DEFAULT NULL,
    `image_url` VARCHAR(45) DEFAULT NULL,
    `fk_user_author` INT(10) unsigned NOT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_user_author_idx` (`fk_user_author`),
    CONSTRAINT `fk_user_author_a` FOREIGN KEY (`fk_user_author`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

DROP TABLE IF EXISTS `count_of_clicks`;
CREATE TABLE `count_of_clicks` (
    `id` INT(10) unsigned NOT NULL,
    `count` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_advertising_c` FOREIGN KEY (`id`) REFERENCES `advertising` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

DROP TABLE IF EXISTS `history_of_clicks`;
CREATE TABLE `history_of_clicks` (
    `id` INT(10) unsigned NOT NULL AUTO_INCREMENT,
    `time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `fk_user` INT(10) unsigned NOT NULL,
    `fk_advertising` INT(10) unsigned NOT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_user_idx` (`fk_user`),
    KEY `fk_advertising_idx` (`fk_advertising`),
    CONSTRAINT `fk_advertising_h` FOREIGN KEY (`fk_advertising`) REFERENCES `advertising` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT `fk_user_h` FOREIGN KEY (`fk_user`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

-- -----------------------------
-- DATA
-- -----------------------------

LOCK TABLES `groups_users` WRITE;
INSERT INTO `groups_users` VALUES (1,'admin'),(2,'client'),(3,'seller');
UNLOCK TABLES;

-- -----------------------------
-- PROCEDURES
-- -----------------------------

DROP procedure IF EXISTS `user_add`;
DROP procedure IF EXISTS `user_get_by_id`;
DROP procedure IF EXISTS `user_get_by_login`;

DROP procedure IF EXISTS `token_add`;

DELIMITER ;;
CREATE PROCEDURE `user_add` (a_login VARCHAR(45), a_password VARCHAR(45), a_email VARCHAR(45), a_phone VARCHAR(45), a_fk_group INT(10))
BEGIN
    INSERT INTO `lockscreen_db`.`users`
        (`login`,
        `password`,
        `email`,
        `phone`,
        `fk_group`)
    VALUES
        (a_login, 
        a_password,
        a_email,
        a_phone,
        a_fk_group);
END ;;

CREATE PROCEDURE `user_get_by_id` (a_id INT(10))
BEGIN
    SELECT 
        `id`,
        `login`,
        `password`,
        `email`,
        `phone`,
        `fk_group`
    FROM `lockscreen_db`.`users` 
    WHERE `id` = a_id;
END ;;

CREATE PROCEDURE `user_get_by_login` (a_login VARCHAR(45))
BEGIN
    SELECT 
        `id`,
        `login`,
        `password`,
        `email`,
        `phone`,
        `fk_group`
    FROM `lockscreen_db`.`users` 
    WHERE `login` = a_login;
END ;;

CREATE PROCEDURE `token_add` (a_token VARCHAR(45), a_time TIMESTAMP, a_fk_user INT(10))
BEGIN
    INSERT INTO `lockscreen_db`.`tokens`
        (`token`,
        `time`,
        `fk_user`)
    VALUES
        (a_token, 
        a_time,
        a_fk_user);
END ;;

CREATE PROCEDURE `token_get` (a_token VARCHAR(45))
BEGIN
    SELECT 
        `id`,
        `token`,
        `time`,
        `fk_user`
    FROM `lockscreen_db`.`tokens` 
    WHERE `token` = a_token;
END ;;

CREATE PROCEDURE `token_update_time` (a_id INT(10), a_time TIMESTAMP)
BEGIN
    UPDATE `lockscreen_db`.`tokens`
    SET
        `time` = a_time
    WHERE `id` = a_id;
END ;;

DELIMITER ;

