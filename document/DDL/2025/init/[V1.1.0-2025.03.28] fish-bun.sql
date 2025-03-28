-- fish_project.BUNGBAL definition

CREATE TABLE `BUNGBAL` (
                           `id` bigint NOT NULL AUTO_INCREMENT,
                           `mbti` varchar(4) DEFAULT NULL,
                           `name` varchar(15) DEFAULT NULL,
                           `count` bigint NOT NULL DEFAULT '0',
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- fish_project.COMMENT definition

CREATE TABLE `COMMENT` (
                           `id` bigint NOT NULL AUTO_INCREMENT,
                           `contents` varchar(500) DEFAULT NULL,
                           `postId` bigint NOT NULL,
                           `userId` bigint NOT NULL,
                           `userNickName` varchar(15) DEFAULT NULL,
                           `regDate` datetime(6) DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- fish_project.COMMENT_LIKES definition

CREATE TABLE `COMMENT_LIKES` (
                                 `id` bigint NOT NULL AUTO_INCREMENT,
                                 `commentId` bigint NOT NULL,
                                 `userId` bigint NOT NULL,
                                 `regDate` datetime(6) DEFAULT NULL,
                                 PRIMARY KEY (`id`),
                                 UNIQUE KEY `unique_user_comment` (`commentId`,`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=602 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- fish_project.FISH_BUN_DETAIL definition

CREATE TABLE `FISH_BUN_DETAIL` (
                                   `id` bigint NOT NULL AUTO_INCREMENT,
                                   `regDate` datetime(6) DEFAULT NULL,
                                   `fileId` bigint DEFAULT NULL,
                                   `userId` bigint DEFAULT NULL,
                                   `storeId` mediumtext,
                                   `flavors` varchar(500) DEFAULT NULL COMMENT '맛',
                                   `date` varchar(10) NOT NULL,
                                   `contents` varchar(150) NOT NULL,
                                   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=209 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- fish_project.FISH_BUN_FILE definition

CREATE TABLE `FISH_BUN_FILE` (
                                 `id` bigint NOT NULL AUTO_INCREMENT,
                                 `fileSize` bigint DEFAULT NULL,
                                 `fileExtension` varchar(15) DEFAULT NULL,
                                 `filePath` varchar(100) DEFAULT NULL,
                                 `originFileName` varchar(100) DEFAULT NULL,
                                 `systemFileName` varchar(100) DEFAULT NULL,
                                 `regDate` datetime(6) DEFAULT NULL,
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=263 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- fish_project.FISH_BUN_FLAVOR definition

CREATE TABLE `FISH_BUN_FLAVOR` (
                                   `id` bigint NOT NULL AUTO_INCREMENT COMMENT '파일 고유 ID',
                                   `flavor` varchar(30) NOT NULL COMMENT '붕어빵 맛',
                                   `iconCode` varchar(30) NOT NULL COMMENT '아이콘 코드',
                                   `seq` int NOT NULL COMMENT '순서',
                                   `regDate` datetime(6) DEFAULT NULL,
                                   `description` varchar(255) NOT NULL,
                                   `highlight` varchar(100) NOT NULL,
                                   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- fish_project.FISH_BUN_FLAVOR_REPORT definition

CREATE TABLE `FISH_BUN_FLAVOR_REPORT` (
                                          `id` bigint NOT NULL AUTO_INCREMENT,
                                          `userId` bigint DEFAULT NULL,
                                          `flavor` varchar(30) DEFAULT NULL,
                                          `status` enum('APPROVED','DENIED','PENDING') DEFAULT NULL,
                                          `regDate` datetime(6) DEFAULT NULL,
                                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- fish_project.POST definition

CREATE TABLE `POST` (
                        `id` bigint NOT NULL AUTO_INCREMENT,
                        `title` varchar(50) NOT NULL,
                        `contents` varchar(500) DEFAULT NULL,
                        `firstOption` varchar(30) DEFAULT NULL,
                        `secondOption` varchar(30) DEFAULT NULL,
                        `regDate` datetime(6) DEFAULT NULL,
                        `fileIdList` varchar(50) DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- fish_project.POST_REPORT definition

CREATE TABLE `POST_REPORT` (
                               `id` bigint NOT NULL AUTO_INCREMENT,
                               `contents` varchar(500) NOT NULL,
                               `userId` bigint NOT NULL,
                               `status` enum('APPROVED','DENIED','PENDING') DEFAULT NULL,
                               `regDate` datetime(6) DEFAULT NULL,
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- fish_project.STORE definition

CREATE TABLE `STORE` (
                         `id` bigint NOT NULL AUTO_INCREMENT,
                         `userId` bigint NOT NULL,
                         `address` varchar(255) NOT NULL,
                         `name` varchar(30) NOT NULL,
                         `detail` varchar(100) DEFAULT NULL,
                         `lat` double NOT NULL,
                         `lng` double NOT NULL,
                         `regDate` datetime(6) DEFAULT NULL,
                         `modDate` datetime(6) DEFAULT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- fish_project.STORE_LIKES definition

CREATE TABLE `STORE_LIKES` (
                               `id` bigint NOT NULL AUTO_INCREMENT,
                               `userId` bigint NOT NULL,
                               `storeId` bigint NOT NULL,
                               `regDate` datetime(6) DEFAULT NULL,
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- fish_project.`USER` definition

CREATE TABLE `USER` (
                        `id` bigint NOT NULL AUTO_INCREMENT,
                        `level` bigint DEFAULT NULL,
                        `providerId` varchar(50) NOT NULL,
                        `nickname` varchar(15) DEFAULT NULL,
                        `providerProfile` varchar(150) NOT NULL,
                        `providerType` varchar(20) DEFAULT NULL,
                        `uuid` varchar(100) NOT NULL,
                        `regDate` datetime(6) DEFAULT NULL,
                        `lastDate` datetime(6) DEFAULT NULL,
                        `isFirstLogin` char(1) DEFAULT 'Y',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- fish_project.USER_FISH_BUN_BOOK definition

CREATE TABLE `USER_FISH_BUN_BOOK` (
                                      `id` bigint NOT NULL AUTO_INCREMENT,
                                      `regDate` datetime(6) DEFAULT NULL,
                                      `userId` bigint DEFAULT NULL,
                                      `completedFlavorId` bigint DEFAULT NULL,
                                      `rating` float DEFAULT NULL,
                                      PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- fish_project.USER_HISTORY definition

CREATE TABLE `USER_HISTORY` (
                                `id` bigint NOT NULL AUTO_INCREMENT,
                                `regDate` datetime(6) DEFAULT NULL,
                                `providerId` varchar(50) NOT NULL,
                                `ip` varchar(40) DEFAULT NULL,
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=703 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- fish_project.VOTE definition

CREATE TABLE `VOTE` (
                        `id` bigint NOT NULL AUTO_INCREMENT,
                        `postId` bigint NOT NULL,
                        `userId` bigint NOT NULL,
                        `voteOption` varchar(30) DEFAULT NULL,
                        `regDate` datetime(6) DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=252 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO FISH_BUN_FLAVOR
(flavor, iconCode, seq, description, highlight)
VALUES
    ('팥 붕어빵', 'redbean', 1, '붕어빵의 원조이자 클래식의 정석! 겉은 바삭, 속은 달달한 팥이 가득 들어있어 한입 베어 물면 겨울 추위도 잊게 해주는 따뜻한 행복.', '붕어빵의 전통을 이어가는, 변함없는 국민 간식!'),
    ('슈크림 붕어빵', 'custard', 2, '입안 가득 퍼지는 부드럽고 달콤한 크림의 매력! 바삭한 붕어빵 속에서 사르르 녹는 슈크림이 남녀노소 모두의 입맛을 사로잡는다.', '부드러움과 달콤함이 폭발하는, 입속의 힐링타임!'),
    ('초코 붕어빵', 'choco', 3, '초코덕후들을 위한 필수템! 한 입 베어 물면 진한 초콜릿이 가득 퍼져 마치 초코 케이크를 먹는 듯한 달콤함을 선사한다.', '초콜릿이 흘러넘치는, 붕어빵계의 디저트 혁명!'),
    ('고구마 붕어빵', 'guma', 4, '달콤하고 포슬포슬한 고구마 속이 듬뿍! 씹을수록 깊어지는 자연스러운 단맛이 매력적인 고구마 덕후들의 최애 붕어빵.', '자연이 만든 달콤함, 포근한 겨울 간식의 끝판왕!'),
    ('미니 붕어빵', 'mini', 5, '작지만 강한 존재감! 한입에 쏙 들어가 간편하게 먹을 수 있어 손이 멈추지 않는 중독성 강한 붕어빵.', '한 개만 먹을 수 없는, 무한 흡입주의!'),
    ('김치 붕어빵', 'kimchi', 6, '바삭한 붕어빵 속에서 퍼지는 매콤한 김치의 풍미! 한국 전통의 깊은 맛을 살린 강렬한 퓨전 간식.', '전통과 퓨전이 만난, 한국적인 붕어빵 혁명!'),
    ('피자 붕어빵', 'pizza', 7, '붕어빵과 피자의 환상적인 만남! 고소한 치즈, 짭짤한 토마토 소스, 그리고 다양한 토핑이 더해져 피자 한 조각을 먹는 듯한 기분을 느낄 수 있다.', '붕어빵 한 입, 피자 한 조각! 이 조합, 거부할 수 없다!'),
    ('팥 크림치즈 붕어빵', 'redbean-cream-cheese', 8, '달콤한 팥과 진한 크림치즈의 조화! 부드러움과 고소함이 만나 입안에서 사르르 녹아내리는 환상의 맛.', '팥과 크림치즈의 환상 듀엣, 입안에서 춤춘다!'),
    ('치즈 붕어빵', 'cheese', 9, '한입 물면 늘어나는 치즈의 쭉쭉한 매력! 고소하고 짭짤한 치즈가 가득 들어가 있어 치즈 덕후라면 절대 놓칠 수 없는 붕어빵.', '치즈가 쭉쭉! 한입 베어 물면 멈출 수 없다!'),
    ('콘치즈 붕어빵', 'corn-cheese', 10, '고소한 옥수수 알갱이와 부드러운 치즈가 가득한 붕어빵! 달콤함과 짭짤함이 조화롭게 어우러진 최고의 간식.', '톡톡 터지는 옥수수의 고소함과 치즈의 환상 궁합!'),
    ('매콤이 붕어빵', 'maecom', 11, '은근하게 퍼지는 매콤한 맛이 중독적! 한 번 맛보면 멈출 수 없는 강렬한 붕어빵.', '한입 먹으면 혀 끝이 짜릿! 붕어빵계의 매운맛 도전장!'),
    ('뿌링클 붕어빵', 'bburing', 12, '달콤짭짤한 뿌링클 시즈닝이 솔솔~ 한 번 먹으면 멈출 수 없는 마성의 붕어빵. 바삭한 식감까지 완벽!', '단짠의 미학, 붕어빵계의 중독적인 유혹!'),
    ('앙버터 붕어빵', 'ang', 13, '겉은 바삭, 속은 달콤한 사과 필링이 가득! 따뜻한 시나몬 향까지 더해져 마치 붕어빵으로 만든 애플파이를 먹는 듯한 기분.', '달콤한 사과와 시나몬의 향연, 한입이면 입안 가득 퍼지는 따뜻한 감성!'),
    ('흑임자 붕어빵', 'black-sesame', 14, '고소함의 끝판왕! 깊고 진한 흑임자의 풍미가 가득해 어른들의 입맛까지 사로잡는 고급스러운 붕어빵.', '깊고 진한 고소함, 씹을수록 빠져드는 매력!'),
    ('팥절미 붕어빵', 'redbean-mozzi', 15, '쫀득쫀득한 인절미와 달달한 팥이 만나 최고의 조화를 이루는 붕어빵. 전통과 현대가 어우러진 색다른 맛!', '전통의 맛과 쫀득한 식감, 한입에 담긴 추억!'),
    ('고구마 크림치즈 붕어빵', 'guma-cream-cheese', 16, '부드러운 크림치즈와 달달한 고구마의 완벽한 조합! 고구마의 자연스러운 단맛과 크림치즈의 고소함이 어우러져 깊은 감칠맛을 선사한다.', '달콤함과 고소함의 환상적인 밸런스, 고급 디저트 붕어빵!'),
    ('애플시나몬 붕어빵', 'apple-sinnamon', 17, '사과의 상큼한 맛과 시나몬의 은은한 향이 조화를 이루는 붕어빵. 따뜻하게 먹으면 더 깊은 풍미를 느낄 수 있다.', '한입 베어 물면 퍼지는 시나몬의 향, 따뜻한 여운이 남는 붕어빵!'),
    ('대왕 붕어빵', 'king', 18, '붕어빵계의 거인! 압도적인 크기와 풍부한 속재료로 한 개만 먹어도 배부른 푸짐한 붕어빵.', '한 개로 충분하다! 크기의 끝판왕!'),
    ('타코야끼 붕어빵', 'tako', 19, '겉은 바삭하고 속은 촉촉한 타코야끼 스타일 붕어빵! 오코노미야키 소스와 가쓰오부시의 풍미까지 더해져 이국적인 감성을 자극한다.', '타코야끼 감성 그대로! 색다른 붕어빵의 신세계!')
;

INSERT INTO BUNGBAL (mbti, name)
VALUES
    ('ISTJ', '팥 붕어빵'),
    ('ESFP', '슈크림 붕어빵'),
    ('ENFP', '초코 붕어빵'),
    ('INFJ', '고구마 붕어빵'),
    ('ISFP', '미니 붕어빵'),
    ('ESTP', '김치 붕어빵'),
    ('INTP', '피자 붕어빵'),
    ('INFP', '팥 크림치즈 붕어빵'),
    ('ISFJ', '치즈 붕어빵'),
    ('ESFJ', '콘치즈 붕어빵'),
    ('ESTJ', '매콤이 붕어빵'),
    ('ENTJ', '대왕 붕어빵'),
    ('ENTP', '타코야끼 붕어빵'),
    ('ISTP', '뿌링클 붕어빵'),
    ('ENFJ', '애플시나몬 붕어빵'),
    ('INTJ', '흑임자 붕어빵')
;