-- liquibase formatted sql

-- changeset Liquibase:1670297382387
drop table if exists `demo_student`;
CREATE TABLE `demo_student` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

drop table if exists `demo_score`;
CREATE TABLE `demo_score` (
  `id` int NOT NULL AUTO_INCREMENT,
  `demo_student_id` int NOT NULL,
  `score` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `demo_student_id` (`demo_student_id`),
  CONSTRAINT `demo_score_ibfk_1` FOREIGN KEY (`demo_student_id`) REFERENCES `demo_student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- changeset Liquibase:1680015263000
drop table if exists `demo_user`;
create table `demo_user`
(
	`id`		 int unsigned auto_increment,
	`username` varchar(16) not null,
	`password` varchar(32) not null,
	primary key (`id`)
) engine = InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
