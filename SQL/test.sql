-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Aug 07, 2020 at 07:26 AM
-- Server version: 5.7.26
-- PHP Version: 7.1.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `test`
--

-- --------------------------------------------------------

--
-- Table structure for table `assignment`
--

DROP TABLE IF EXISTS `assignment`;
CREATE TABLE IF NOT EXISTS `assignment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tId` int(11) NOT NULL,
  `std` varchar(15) NOT NULL,
  `period` varchar(3) NOT NULL,
  `subject` varchar(7) NOT NULL,
  `topic` text NOT NULL,
  `link` text NOT NULL,
  `currDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `assignCode` varchar(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `assignment`
--

INSERT INTO `assignment` (`id`, `tId`, `std`, `period`, `subject`, `topic`, `link`, `currDate`, `assignCode`) VALUES
(1, 2, 'VII', '3rd', 'Eng', 'Night of the Scorpian', 'UGUhfnJUkl', '2020-08-04 06:33:20', '1596469073'),
(2, 2, 'V', '2nd', 'Eng', 'Topic of the Day', 'sjdhiihbjdhhdu', '2020-08-04 07:04:26', '1596524646');

-- --------------------------------------------------------

--
-- Table structure for table `organisation`
--

DROP TABLE IF EXISTS `organisation`;
CREATE TABLE IF NOT EXISTS `organisation` (
  `orgCode` int(11) NOT NULL AUTO_INCREMENT,
  `orgName` varchar(50) NOT NULL,
  `estDate` varchar(15) NOT NULL,
  `address` text NOT NULL,
  `contact` int(11) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `orgHead` varchar(40) NOT NULL,
  `status` varchar(8) NOT NULL DEFAULT 'active',
  `live` varchar(1) NOT NULL DEFAULT '0',
  `run` varchar(20) NOT NULL,
  `uploadDoc` text,
  PRIMARY KEY (`orgCode`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `organisation`
--

INSERT INTO `organisation` (`orgCode`, `orgName`, `estDate`, `address`, `contact`, `email`, `password`, `orgHead`, `status`, `live`, `run`, `uploadDoc`) VALUES
(1, 'Siksha Niketan', '2015-04-07', 'KATRAS BAZAR', 1234567890, 'nimesh@gmail.com', 'pass@123', 'Nimesh', '1', '0', 'Indivisual', NULL),
(2, 'Mazumdar Classes', '2020-04-01', 'Dhanbad', 1234567890, 'admin@gmail.com', 'admin', 'Gaurav Mazumdar', '1', '0', 'Indivisual', NULL),
(3, 'Sharma Classes', '2007-04-23', 'ABC Street, Mumbai', 1234567890, 'gaurav@gmail.com', 'pass@123', 'RD Sharma', '1', '0', 'Trust', NULL),
(4, 'Siksha Shantha', '2014-01-03', 'Kolkata', 1234567890, 'unq@gmail.com', 'pass@123', 'Raj Singh', 'active', '0', 'Self', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `teacherrole`
--

DROP TABLE IF EXISTS `teacherrole`;
CREATE TABLE IF NOT EXISTS `teacherrole` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orgCode` int(11) NOT NULL,
  `teacherId` int(11) NOT NULL,
  `teacherName` varchar(40) NOT NULL,
  `assignedClass` varchar(8) NOT NULL,
  `assignedSub` varchar(20) NOT NULL,
  `periodNum` varchar(3) NOT NULL,
  `dayVal` varchar(9) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `teacherrole`
--

INSERT INTO `teacherrole` (`id`, `orgCode`, `teacherId`, `teacherName`, `assignedClass`, `assignedSub`, `periodNum`, `dayVal`) VALUES
(1, 2, 2, 'Sandeep Yadav', 'IX', 'Maths', '4th', 'Saturday'),
(2, 2, 3, 'Arindam Banerjee', 'X', 'Sci', '2nd', 'Monday'),
(3, 2, 2, 'Sandeep Yadav', 'V', 'Eng', '2nd', 'Thursday');

-- --------------------------------------------------------

--
-- Table structure for table `teachers`
--

DROP TABLE IF EXISTS `teachers`;
CREATE TABLE IF NOT EXISTS `teachers` (
  `teacherId` int(11) NOT NULL AUTO_INCREMENT,
  `orgCode` int(11) NOT NULL DEFAULT '0',
  `name` varchar(40) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `contact` int(10) NOT NULL,
  `qualification` text NOT NULL,
  `address` text NOT NULL,
  `status` varchar(1) NOT NULL DEFAULT '0',
  `live` varchar(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`teacherId`),
  KEY `orgCode` (`orgCode`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `teachers`
--

INSERT INTO `teachers` (`teacherId`, `orgCode`, `name`, `email`, `password`, `contact`, `qualification`, `address`, `status`, `live`) VALUES
(1, 1, 'Nimesh', 'nimesh.nt@gmail.com', 'pass@123', 1234567890, 'B.ED (Economics)', 'Katras Bazar, Dhanbad', '0', '0'),
(2, 2, 'Sandeep Yadav', 'sandy@gmail.com', 'pass@123', 1234567890, 'MCA', 'Dhanbad', '1', '1'),
(3, 2, 'Arindam Banerjee', 'Arindam.Ab@gmail.com', 'pass@123', 1234567890, 'B-Tech (Electronics)', 'Dhanbad', '1', '0');

-- --------------------------------------------------------

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
CREATE TABLE IF NOT EXISTS `token` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `access_token` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `token`
--

INSERT INTO `token` (`id`, `access_token`) VALUES
(2, '{\"access_token\":\"ya29.a0AfH6SMD9oqTdNknUonAaB8thUs3H7k40eBqHj7rdq823dKBk0qHJjBFQWI6E_yrRe7w_jtznbXtsHrOgT8ar0019GUMk4m-2b7YCu9bGEIujObdhwoXguT2JAYB1FC8aA-fo-zBJC4XDz31dpI8ZMxjEDCSf3Cq1xP4\",\"token_type\":\"Bearer\",\"refresh_token\":\"1//0gaiDJ__0QFBTCgYIARAAGBASNwF-L9IrEvslMd0xue2s4-7g-z_tP4p55TwVeEJiLsqBWhosemwR1WFNwLeJVFsoVAQb2JpzzGw\",\"expires_in\":3599,\"expires_at\":1596648203}');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `teachers`
--
ALTER TABLE `teachers`
  ADD CONSTRAINT `teachers_ibfk_1` FOREIGN KEY (`orgCode`) REFERENCES `organisation` (`orgCode`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
