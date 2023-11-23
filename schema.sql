create database ticket;

use ticket;

CREATE TABLE IF NOT EXISTS `user` (
  `user_id` int AUTO_INCREMENT  PRIMARY KEY,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `created_at` TIMESTAMP NOT NULL,
  `created_by` varchar(50) NOT NULL,
  `updated_at` TIMESTAMP DEFAULT NULL,
  `updated_by` varchar(50) DEFAULT NULL,
   UNIQUE (`email`)
);


CREATE TABLE IF NOT EXISTS `event` (
  `event_id` int AUTO_INCREMENT  PRIMARY KEY,
  `name` varchar(100) NOT NULL,
  `date` TIMESTAMP NOT NULL,
  `availableattendeescount` int NOT NULL,
  `description` varchar(500) NOT NULL,
  `category` varchar(10) NOT NULL,
  `created_at` TIMESTAMP NOT NULL,
  `created_by` varchar(50) NOT NULL,
  `updated_at` TIMESTAMP DEFAULT NULL,
  `updated_by` varchar(50) DEFAULT NULL,
   CHECK (availableattendeescount<=1000)
);