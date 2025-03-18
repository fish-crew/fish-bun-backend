CREATE TABLE `POST_REPORT` (
	`id` bigint NOT NULL AUTO_INCREMENT,
 	`contents` varchar(500) NOT NULL,
    `userId` bigint NOT NULL,
    `status` enum('APPROVED','DENIED','PENDING') DEFAULT NULL,
	`regDate` datetime(6) DEFAULT NULL,
  	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
;