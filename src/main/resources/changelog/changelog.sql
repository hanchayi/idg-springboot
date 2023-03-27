-- liquibase formatted sql

-- changeset Liquibase:1670297382387

CREATE TABLE `demo_student` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `demo_score` (
  `id` int NOT NULL AUTO_INCREMENT,
  `demo_student_id` int NOT NULL,
  `score` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `demo_student_id` (`demo_student_id`),
  CONSTRAINT `demo_score_ibfk_1` FOREIGN KEY (`demo_student_id`) REFERENCES `demo_student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

