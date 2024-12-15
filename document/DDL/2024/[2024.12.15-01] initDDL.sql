CREATE TABLE `FISH_BUN_DETAIL` (
	`id` bigint NOT NULL AUTO_INCREMENT,
	`date` datetime(6) DEFAULT NULL,
  	`fileId` bigint DEFAULT NULL,
 	`userId` bigint DEFAULT NULL,
 	`flavors` varchar(30) DEFAULT NULL,
  	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
;

CREATE TABLE `FISH_BUN_FILE` (
                                 `id` bigint NOT NULL AUTO_INCREMENT,
                                 `fileSize` bigint DEFAULT NULL,
                                 `fileExtension` varchar(15) DEFAULT NULL,
                                 `filePath` varchar(100) DEFAULT NULL,
                                 `originFileName` varchar(100) DEFAULT NULL,
                                 `systemFileName` varchar(100) DEFAULT NULL,
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
;

CREATE TABLE `FISH_BUN_FLAVOR` (
                                   `id` bigint NOT NULL AUTO_INCREMENT COMMENT '파일 고유 ID',
                                   `flavor` varchar(30) NOT NULL COMMENT '붕어빵 맛',
                                   `iconCode` varchar(30) NOT NULL COMMENT '아이콘 코드',
                                   `seq` int NOT NULL COMMENT '순서',
                                   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
;

CREATE TABLE `FISH_BUN_FLAVOR_REPORT` (
                                          `id` bigint NOT NULL AUTO_INCREMENT,
                                          `userId` bigint DEFAULT NULL,
                                          `flavor` varchar(30) DEFAULT NULL,
                                          `status` enum('APPROVED','DENIED','PENDING') DEFAULT NULL,
                                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
;

CREATE TABLE `USER` (
                        `id` bigint NOT NULL AUTO_INCREMENT,
                        `level` bigint DEFAULT NULL,
                        `providerId` bigint DEFAULT NULL,
                        `nickname` varchar(15) DEFAULT NULL,
                        `providerProfile` varchar(100) DEFAULT NULL,
                        `providerType` varchar(20) DEFAULT NULL,
                        `uuid` varchar(100) NOT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
;

CREATE TABLE `USER_FISH_BUN_BOOK` (
                                      `id` bigint NOT NULL AUTO_INCREMENT,
                                      `date` datetime(6) DEFAULT NULL,
                                      `userId` bigint DEFAULT NULL,
                                      `completedFlavor` varchar(30) DEFAULT NULL,
                                      PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
;

CREATE TABLE `USER_HISTORY` (
                                `id` bigint NOT NULL AUTO_INCREMENT,
                                `date` datetime(6) DEFAULT NULL,
                                `providerId` bigint DEFAULT NULL,
                                `ip` varchar(40) DEFAULT NULL,
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
;

INSERT INTO FISH_BUN_FLAVOR
(flavor, iconCode, seq)
VALUES
('팥 붕어빵', 'redbean', 1),
('슈크림 붕어빵', 'custard', 2),
('초코 붕어빵', 'choco', 3),
('고구마 붕어빵', 'guma', 4),
('미니 붕어빵', 'mini', 5),
('김치 붕어빵', 'kimchi', 6),
('피자 붕어빵', 'pizza', 7),
('팥 크림치즈 붕어빵', 'redbean-cream-cheese', 8),
('치즈 붕어빵', 'cheese', 9),
('콘치즈 붕어빵', 'corn-cheese', 10),
('매콤이 붕어빵', 'maecom', 11),
('뿌링클 붕어빵 ', 'bburing', 12),
('애플파이 붕어빵', 'apple-pie', 13),
('흑임자 붕어빵 ', 'black-sesame', 14),
('팥절미 붕어빵', 'redbean-mozzi', 15),
('고구마 크림치즈 붕어빵', 'guma-cream-cheese', 16),
('애플시나몬 붕어빵', 'apple-sinnamon', 17),
('대왕 붕어빵', 'king', 18),
('타코야끼 붕어빵', 'tako', 19)
;