SET @add_rating_column = IF(
    EXISTS(
        SELECT 1
        FROM INFORMATION_SCHEMA.COLUMNS
        WHERE TABLE_SCHEMA = DATABASE()
          AND TABLE_NAME = 'USER_FISH_BUN_BOOK'
          AND COLUMN_NAME = 'rating'
    ),
    'SELECT 1',
    'ALTER TABLE USER_FISH_BUN_BOOK ADD COLUMN rating DOUBLE NULL'
);

PREPARE stmt FROM @add_rating_column;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;
