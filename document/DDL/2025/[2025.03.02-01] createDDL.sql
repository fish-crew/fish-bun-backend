CREATE TABLE STORE (
               id BIGINT AUTO_INCREMENT,
               userId BIGINT NOT NULL,
               address VARCHAR(255) NOT NULL,
               name VARCHAR(30) NOT NULL,
               detail VARCHAR(100),
               lat DOUBLE NOT NULL,
               lng DOUBLE NOT NULL,
               regDate datetime(6) DEFAULT NULL,
               modDate datetime(6) DEFAULT NULL,
               PRIMARY KEY (`id`)
            )
            ;

CREATE TABLE STORE_LIKES (
             id BIGINT AUTO_INCREMENT,
             userId BIGINT NOT NULL,
             storeId BIGINT NOT NULL,
             regDate datetime(6) DEFAULT NULL,
             PRIMARY KEY (`id`)
);