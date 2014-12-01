SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `wbdhebat` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `wbdhebat` ;

-- -----------------------------------------------------
-- Table `wbdhebat`.`user`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `wbdhebat`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `email` VARCHAR(100) NOT NULL ,
  `password` VARCHAR(100) NOT NULL ,
  `name` VARCHAR(45) NULL ,
  `role` VARCHAR(20) NULL ,
  PRIMARY KEY (`id`, `email`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wbdhebat`.`post`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `wbdhebat`.`post` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `judul` VARCHAR(200) NULL ,
  `tanggal` DATETIME NULL ,
  `konten` TEXT NULL ,
  `status` VARCHAR(20) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wbdhebat`.`komentar`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `wbdhebat`.`komentar` (
  `name` VARCHAR(100) NULL ,
  `email` VARCHAR(100) NULL ,
  `tanggal` DATETIME NULL ,
  `komentar` TEXT NULL ,
  `post_id` INT NOT NULL ,
  INDEX `fk_komentar_post_idx` (`post_id` ASC) ,
  CONSTRAINT `fk_komentar_post`
    FOREIGN KEY (`post_id` )
    REFERENCES `wbdhebat`.`post` (`id` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `wbdhebat` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
