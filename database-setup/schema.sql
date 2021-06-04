-- Run this locally to create db

-- mysql -u database_username -pdatabase_password --host=127.0.0.1  --port=19200 < database-setup/schema.sql
-- mysql -u userrodrigo -ppassword --host=127.0.0.1  --port=3306 < database-setup/schema.sql

DROP SCHEMA IF EXISTS `reporting`;

CREATE SCHEMA IF NOT EXISTS `reporting` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

USE `reporting`;


create table reporting.Kpi
(
    id          bigint unsigned auto_increment
        primary key,
    createdDate datetime(6)  null,
    updatedDate datetime(6)  null,
    version     bigint       null,
    name        varchar(255) null
);

create table reporting.Report
(
    id          bigint unsigned auto_increment
        primary key,
    createdDate datetime(6)  null,
    updatedDate datetime(6)  null,
    version     bigint       null,
    description varchar(255) null,
    isPrivate   bit          null,
    name        varchar(255) not null,
    constraint UK_flil86qdrwwdpm3uya6lqtu5f
        unique (name)
);

create table reporting.Report_Kpi
(
    Report_id bigint unsigned not null,
    kpis_id   bigint unsigned not null,
    primary key (Report_id, kpis_id),
    constraint FK7tpffr0jsbt1uc2lc1ds1t5f9
        foreign key (Report_id) references reporting.Report (id),
    constraint FKo9bhusvtr12ddk6ebluckjy8e
        foreign key (kpis_id) references reporting.Kpi (id)
);



INSERT INTO reporting.Kpi (id, createdDate, updatedDate, version, name) VALUES (1, '2021-05-26 15:43:23', '2021-05-26 15:43:26', 0, 'TWAMP Availability');
INSERT INTO reporting.Kpi (id, createdDate, updatedDate, version, name) VALUES (2, '2021-05-26 15:43:38', '2021-05-26 15:43:42', 0, 'TWAMP Jitter');
INSERT INTO reporting.Kpi (id, createdDate, updatedDate, version, name) VALUES (3, '2021-05-28 16:57:04', '2021-05-28 16:57:12', 0, 'TWAMP Jitter Far');
INSERT INTO reporting.Kpi (id, createdDate, updatedDate, version, name) VALUES (4, '2021-05-28 16:57:14', '2021-05-28 16:57:09', 0, 'TWAMP Jitter Near');
INSERT INTO reporting.Kpi (id, createdDate, updatedDate, version, name) VALUES (5, '2021-05-28 16:57:16', '2021-05-28 16:57:17', 0, 'TWAMP Latency');
INSERT INTO reporting.Kpi (id, createdDate, updatedDate, version, name) VALUES (6, '2021-05-28 16:57:35', '2021-05-28 16:57:42', 0, 'TWAMP Latency Far');
INSERT INTO reporting.Kpi (id, createdDate, updatedDate, version, name) VALUES (7, '2021-05-28 16:57:38', '2021-05-28 16:57:43', 0, 'TWAMP Latency Near');
INSERT INTO reporting.Kpi (id, createdDate, updatedDate, version, name) VALUES (8, '2021-05-28 16:57:40', '2021-05-28 16:57:44', 0, 'TWAMP Packets');
INSERT INTO reporting.Kpi (id, createdDate, updatedDate, version, name) VALUES (9, '2021-05-28 16:57:40', '2021-05-28 16:57:44', 0, 'TWAMP Tests');
INSERT INTO reporting.Kpi (id, createdDate, updatedDate, version, name) VALUES (10, '2021-06-03 21:05:26', '2021-06-03 21:05:29', 0, 'TWAMP Thresholds');

