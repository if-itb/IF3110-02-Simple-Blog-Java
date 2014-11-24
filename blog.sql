-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 24, 2014 at 12:34 AM
-- Server version: 5.5.40-0ubuntu0.14.04.1
-- PHP Version: 5.5.9-1ubuntu4.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `blog`
--

-- --------------------------------------------------------

--
-- Table structure for table `Comment`
--

CREATE TABLE IF NOT EXISTS `Comment` (
  `PostID` int(11) NOT NULL,
  `CommentID` int(11) NOT NULL AUTO_INCREMENT,
  `Nama` varchar(50) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Komentar` text NOT NULL,
  `Tanggal` date NOT NULL,
  PRIMARY KEY (`CommentID`),
  KEY `PostID` (`PostID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `Comment`
--

INSERT INTO `Comment` (`PostID`, `CommentID`, `Nama`, `Email`, `Komentar`, `Tanggal`) VALUES
(2, 3, 'Nananananana', 'nanana@gmail.com', 'NanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNananananananana', '2014-11-29');

-- --------------------------------------------------------

--
-- Table structure for table `Post`
--

CREATE TABLE IF NOT EXISTS `Post` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Judul` text NOT NULL,
  `Tanggal` date NOT NULL,
  `Content` text NOT NULL,
  `Author` varchar(24) NOT NULL,
  `Status` varchar(20) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `Author` (`Author`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `Post`
--

INSERT INTO `Post` (`ID`, `Judul`, `Tanggal`, `Content`, `Author`, `Status`) VALUES
(2, 'Nananananananana', '2014-11-29', 'NanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNanananananananaNananananananana', 'chobits', 'published'),
(3, 'asdasdasdkljsladjlasd', '2014-11-21', 'asdasdasdkljsladjlasdasdasdasdkljsladjlasdasdasdasdkljsladjlasdasdasdasdkljsladjlasdasdasdasdkljsladjlasdasdasdasdkljsladjlasdasdasdasdkljsladjlasdasdasdasdkljsladjlasd', 'chobits', 'unpublished'),
(4, 'Nananananana', '2014-11-28', 'NanananananaNanananananaNanananananaNanananananaNanananananaNanananananaNanananananaNanananananaNanananananaNanananananaNanananananaNanananananaNanananananaNanananananaNanananananaNanananananaNanananananaNanananananaNanananananaNanananananaNanananananaNanananananaNanananananaNanananananaNanananananaNanananananaNananananana', 'chobits', 'unpublished');

-- --------------------------------------------------------

--
-- Table structure for table `User`
--

CREATE TABLE IF NOT EXISTS `User` (
  `Username` varchar(24) NOT NULL,
  `Password` varchar(20) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `email` text NOT NULL,
  `Role` varchar(10) NOT NULL,
  PRIMARY KEY (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `User`
--

INSERT INTO `User` (`Username`, `Password`, `Name`, `email`, `Role`) VALUES
('chobits', 'chobitsu', 'Mario Filino', 'mario.filino@gmail.com', 'owner');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Comment`
--
ALTER TABLE `Comment`
  ADD CONSTRAINT `Comment_ibfk_1` FOREIGN KEY (`PostID`) REFERENCES `Post` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `Post`
--
ALTER TABLE `Post`
  ADD CONSTRAINT `Post_ibfk_1` FOREIGN KEY (`Author`) REFERENCES `User` (`Username`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
