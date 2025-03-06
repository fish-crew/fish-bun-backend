CREATE TABLE `POST` (
	`id` bigint NOT NULL AUTO_INCREMENT,
  	`title` varchar(50) NOT NULL,
 	`contents` varchar(500) DEFAULT NULL,
 	`firstOption` varchar(30) DEFAULT NULL,
 	`secondOption` varchar(30) DEFAULT NULL,
	`regDate` datetime(6) DEFAULT NULL,
	`fileIdList` varchar(50) DEFAULT NULL,
  	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
;


CREATE TABLE `COMMENT` (
	`id` bigint NOT NULL AUTO_INCREMENT,
 	`contents` varchar(500) DEFAULT NULL,
 	`postId` bigint NOT NULL,
    `userId` bigint NOT NULL,
    `userNickName` varchar(15) DEFAULT NULL,
	`regDate` datetime(6) DEFAULT NULL,
  	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
;

CREATE TABLE `COMMENT_LIKES` (
	`id` bigint NOT NULL AUTO_INCREMENT,
 	`commentId` bigint NOT NULL,
    `userId` bigint NOT NULL,
	`regDate` datetime(6) DEFAULT NULL,
	UNIQUE KEY unique_user_comment (`commentId`, `userId`),
  	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
;



CREATE TABLE `VOTE` (
	`id` bigint NOT NULL AUTO_INCREMENT,
  	`postId` bigint NOT NULL,
    `userId` bigint NOT NULL,
    `voteOption` varchar(30) DEFAULT NULL,
	`regDate` datetime(6) DEFAULT NULL,
  	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
;