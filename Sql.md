# 2024-09-05
## ERD
### ERDCloud사용
![ee](https://github.com/user-attachments/assets/9764e542-4768-4803-91ea-fad0f826e415)
### ERDCloud사용 후 MySql Workbench 사용
![erd](https://github.com/user-attachments/assets/7b5ced57-84c7-4baf-a24d-c36aeaa824b9)

## SQL 생성문
boardboard-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema todolist
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema todolist
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `todolist` DEFAULT CHARACTER SET utf8 ;
USE `todolist` ;

-- -----------------------------------------------------
-- Table `todolist`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `todolist`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(50) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `todolist`.`board`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `todolist`.`board` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `content` VARCHAR(500) NOT NULL,
  `addExp` VARCHAR(500) NULL,
  `deadline` DATE NOT NULL,
  `createDate` DATE NULL,
  `modifDate` DATE NULL,
  `sucess` TINYINT NULL,
  `delete` TINYINT NULL,
  `users_id` INT NOT NULL,
  PRIMARY KEY (`id`, `users_id`),
  INDEX `fk_board_users_idx` (`users_id` ASC) VISIBLE,
  CONSTRAINT `fk_board_users`
    FOREIGN KEY (`users_id`)
    REFERENCES `todolist`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

