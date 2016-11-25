CREATE TABLE IF NOT EXISTS `competition` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255),
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `bet_match` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `competition_id` INT NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_bet_match_competition_1`
            FOREIGN KEY (`competition_id`)
            REFERENCES `competition` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS `bet_course` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `bet_match_id` INT NOT NULL,
    `course` VARCHAR(255),
    `course_type` VARCHAR(255),
    `win` boolean,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_bet_course_bet_match_1`
        FOREIGN KEY (`bet_match_id`)
        REFERENCES `bet_match` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);
