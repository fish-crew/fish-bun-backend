INSERT INTO USER (providerId, providerProfile, nickname, level, uuid, providerType) values (1, 'fjuwrmy.png', 'Dredi', 1, 'fuangs-13941-dsdddf', 'kakao');
INSERT INTO USER (providerId, providerProfile, nickname, level, uuid, providerType) values (2, 'scospuz.png', 'Hildagard', 2, 'djsklg-23523-dsdddf', 'kakao');
INSERT INTO USER (providerId, providerProfile, nickname, level, uuid, providerType) values (3, 'gcvmqnq.png', 'Aurelia', 1, 'dgdgsd-13941-qwrgs', 'kakao');
INSERT INTO USER (providerId, providerProfile, nickname, level, uuid, providerType) values (4, 'akoquen.png', 'Nancy', 3, 'ndhdvx-426235-dsdddf', 'kakao');

INSERT INTO FISH_BUN_DETAIL (userId, flavors, date, fileId) values (1, '누텔라 붕어빵, 뿌링클 붕어빵', '2024-09-11 15:28:44', 1);
INSERT INTO FISH_BUN_DETAIL (userId, flavors, date, fileId) values (2, '누텔라 붕어빵, 마파두부 붕어빵', '2024-09-12 15:28:44', 2);
INSERT INTO FISH_BUN_DETAIL (userId, flavors, date, fileId) values (3, '팥 붕어빵', '2024-09-25 15:28:44', 3);
INSERT INTO FISH_BUN_DETAIL (userId, flavors, date, fileId) values (3, '누텔라 붕어빵, 팥 붕어빵', '2024-10-01 15:28:44', 4);
INSERT INTO FISH_BUN_DETAIL (userId, flavors, date, fileId) values (5, '애플파이 붕어빵, 팥 붕어빵', '2024-11-22 11:12:58', 5);
INSERT INTO FISH_BUN_DETAIL (userId, flavors, date, fileId) values (5, '슈크림 붕어빵', '2024-11-23 21:00:58', 6);
INSERT INTO FISH_BUN_DETAIL (userId, flavors, date, fileId) values (5, '슈크림 붕어빵', '2024-12-10 21:00:58', 1);
INSERT INTO FISH_BUN_DETAIL (userId, flavors, date, fileId) values (5, '슈크림 붕어빵', '2024-12-11 21:00:58', 2);
INSERT INTO FISH_BUN_DETAIL (userId, flavors, date, fileId) values (5, '슈크림 붕어빵', '2024-12-15 21:00:58', 3);
INSERT INTO FISH_BUN_DETAIL (userId, flavors, date, fileId) values (5, '슈크림 붕어빵', '2024-12-17 21:00:58', 4);

INSERT INTO FISH_BUN_FLAVOR (flavor, image) values ('피자 붕어빵', 'wuziu.png');
INSERT INTO FISH_BUN_FLAVOR (flavor, image) values ('두부 붕어빵', 'golms.png');
INSERT INTO FISH_BUN_FLAVOR (flavor, image) values ('타코야끼 붕어빵', 'htcnq.png');
INSERT INTO FISH_BUN_FLAVOR (flavor, image) values ('팥 붕어빵', 'vmuhl.png');
INSERT INTO FISH_BUN_FLAVOR (flavor, image) values ('슈크림 붕어빵', 'nmlnv.png');
INSERT INTO FISH_BUN_FLAVOR (flavor, image) values ('고구마 붕어빵', 'xaptr.png');
INSERT INTO FISH_BUN_FLAVOR (flavor, image) values ('애플파이 붕어빵', 'fhwjd.png');
INSERT INTO FISH_BUN_FLAVOR (flavor, image) values ('귀여운 붕어빵', 'sgkrq.png');

INSERT INTO USER_FISH_BUN_BOOK (userId, completedFlavor, date) values (1, '피자 붕어빵', '2024-11-25 13:01:42');
INSERT INTO USER_FISH_BUN_BOOK (userId, completedFlavor, date) values (2, '팥 붕어빵', '2024-11-25 13:01:42');
INSERT INTO USER_FISH_BUN_BOOK (userId, completedFlavor, date) values (3, '피자 붕어빵', '2024-11-25 13:01:42');
INSERT INTO USER_FISH_BUN_BOOK (userId, completedFlavor, date) values (4, '고구마 붕어빵', '2024-11-25 13:01:42');
INSERT INTO USER_FISH_BUN_BOOK (userId, completedFlavor, date) values (5, '피자 붕어빵', '2024-11-24 13:01:42');
INSERT INTO USER_FISH_BUN_BOOK (userId, completedFlavor, date) values (5, '애플파이 붕어빵', '2024-11-25 13:01:42');
INSERT INTO USER_FISH_BUN_BOOK (userId, completedFlavor, date) values (5, '뿌링클 붕어빵', '2024-11-28 13:01:42');
INSERT INTO USER_FISH_BUN_BOOK (userId, completedFlavor, date) values (1, '고구마 붕어빵', '2024-11-25 13:01:42');
INSERT INTO USER_FISH_BUN_BOOK (userId, completedFlavor, date) values (2, '뿌링클 붕어빵', '2024-11-25 13:01:42');
INSERT INTO USER_FISH_BUN_BOOK (userId, completedFlavor, date) values (3, '팥 붕어빵', '2024-11-25 13:01:42');
INSERT INTO USER_FISH_BUN_BOOK (userId, completedFlavor, date) values (4, '팥 붕어빵', '2024-11-25 13:01:42');