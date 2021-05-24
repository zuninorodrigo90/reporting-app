-- Run this locally to create db

-- mysql -u database_username -pdatabase_password --host=127.0.0.1  --port=19200 < database-setup/schema.sql
-- mysql -u userrodrigo -ppassword --host=127.0.0.1  --port=3306 < database-setup/schema.sql

DROP SCHEMA IF EXISTS `reporting`;

CREATE SCHEMA IF NOT EXISTS `reporting` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

USE `reporting`;