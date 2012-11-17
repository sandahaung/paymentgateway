-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.5.9 - MySQL Community Server (GPL)
-- Server OS:                    Win32
-- HeidiSQL version:             7.0.0.4053
-- Date/time:                    2012-11-17 19:15:31
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;

-- Dumping database structure for payment
DROP DATABASE IF EXISTS `payment`;
CREATE DATABASE IF NOT EXISTS `payment` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `payment`;


-- Dumping structure for table payment.accounts
DROP TABLE IF EXISTS `accounts`;
CREATE TABLE IF NOT EXISTS `accounts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `company_id` int(11) NOT NULL,
  `service_id` int(11) DEFAULT NULL,
  `currency` varchar(255) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `paid_date` datetime DEFAULT NULL,
  `settled` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ACCOUNT_COMPANY` (`company_id`),
  KEY `FK_ACCOUNT_SERVICE` (`service_id`),
  CONSTRAINT `FK_ACCOUNT_COMPANY` FOREIGN KEY (`company_id`) REFERENCES `companies` (`id`),
  CONSTRAINT `FK_ACCOUNT_SERVICE` FOREIGN KEY (`service_id`) REFERENCES `services` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table payment.accounts: ~0 rows (approximately)
DELETE FROM `accounts`;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;


-- Dumping structure for table payment.accounts_customers
DROP TABLE IF EXISTS `accounts_customers`;
CREATE TABLE IF NOT EXISTS `accounts_customers` (
  `account_id` int(11) DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  KEY `FK_ACCOUNT` (`account_id`),
  KEY `FK_CUSTOMER` (`customer_id`),
  CONSTRAINT `FK_ACCOUNT` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`id`),
  CONSTRAINT `FK_CUSTOMER` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table payment.accounts_customers: ~0 rows (approximately)
DELETE FROM `accounts_customers`;
/*!40000 ALTER TABLE `accounts_customers` DISABLE KEYS */;
/*!40000 ALTER TABLE `accounts_customers` ENABLE KEYS */;


-- Dumping structure for table payment.brokers
DROP TABLE IF EXISTS `brokers`;
CREATE TABLE IF NOT EXISTS `brokers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table payment.brokers: ~0 rows (approximately)
DELETE FROM `brokers`;
/*!40000 ALTER TABLE `brokers` DISABLE KEYS */;
/*!40000 ALTER TABLE `brokers` ENABLE KEYS */;


-- Dumping structure for table payment.companies
DROP TABLE IF EXISTS `companies`;
CREATE TABLE IF NOT EXISTS `companies` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `company_id` varchar(255) DEFAULT NULL,
  `company_name` varchar(255) DEFAULT NULL,
  `company_email` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Dumping data for table payment.companies: ~2 rows (approximately)
DELETE FROM `companies`;
/*!40000 ALTER TABLE `companies` DISABLE KEYS */;
INSERT INTO `companies` (`id`, `company_id`, `company_name`, `company_email`, `username`, `password`) VALUES
	(1, 'MSFT', 'Microsoft', 'ms@microsoft.com', 'steve.ballmer', 'password'),
	(2, 'ORCL', 'Oracle', 'oracle@oracle.com', 'larry.elison', 'word');
/*!40000 ALTER TABLE `companies` ENABLE KEYS */;


-- Dumping structure for table payment.customers
DROP TABLE IF EXISTS `customers`;
CREATE TABLE IF NOT EXISTS `customers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table payment.customers: ~0 rows (approximately)
DELETE FROM `customers`;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;


-- Dumping structure for table payment.hibernate_sequences
DROP TABLE IF EXISTS `hibernate_sequences`;
CREATE TABLE IF NOT EXISTS `hibernate_sequences` (
  `sequence_name` varchar(255) DEFAULT NULL,
  `sequence_next_hi_value` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table payment.hibernate_sequences: ~0 rows (approximately)
DELETE FROM `hibernate_sequences`;
/*!40000 ALTER TABLE `hibernate_sequences` DISABLE KEYS */;
/*!40000 ALTER TABLE `hibernate_sequences` ENABLE KEYS */;


-- Dumping structure for table payment.id_gen
DROP TABLE IF EXISTS `id_gen`;
CREATE TABLE IF NOT EXISTS `id_gen` (
  `TABS` varchar(255) DEFAULT NULL,
  `IDS` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table payment.id_gen: ~1 rows (approximately)
DELETE FROM `id_gen`;
/*!40000 ALTER TABLE `id_gen` DISABLE KEYS */;
INSERT INTO `id_gen` (`TABS`, `IDS`) VALUES
	('COM_GEN', 1);
/*!40000 ALTER TABLE `id_gen` ENABLE KEYS */;


-- Dumping structure for table payment.reminders
DROP TABLE IF EXISTS `reminders`;
CREATE TABLE IF NOT EXISTS `reminders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_id` int(11) DEFAULT NULL,
  `message` text,
  `sent_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_REMINDER_ACCOUNT` (`account_id`),
  CONSTRAINT `FK_REMINDER_ACCOUNT` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table payment.reminders: ~0 rows (approximately)
DELETE FROM `reminders`;
/*!40000 ALTER TABLE `reminders` DISABLE KEYS */;
/*!40000 ALTER TABLE `reminders` ENABLE KEYS */;


-- Dumping structure for table payment.services
DROP TABLE IF EXISTS `services`;
CREATE TABLE IF NOT EXISTS `services` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table payment.services: ~0 rows (approximately)
DELETE FROM `services`;
/*!40000 ALTER TABLE `services` DISABLE KEYS */;
/*!40000 ALTER TABLE `services` ENABLE KEYS */;
/*!40014 SET FOREIGN_KEY_CHECKS=1 */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
