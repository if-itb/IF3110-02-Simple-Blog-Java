-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 01, 2014 at 03:33 PM
-- Server version: 5.6.16
-- PHP Version: 5.5.11

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
-- Table structure for table `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
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
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`PostID`, `CommentID`, `Nama`, `Email`, `Komentar`, `Tanggal`) VALUES
(1, 1, 'Bangbang', 'bangbang@gmail.com', 'Menarik sekali user1 dapat melakukan penghapusan user dan pengembalian post yang sudah di delete yang tidak dijelaskan.', '2014-12-03'),
(2, 2, 'Kismis', 'kismis@gmail.com', 'Editor dapat melakukan edit pada post sebelum melakukan post...menarik', '2014-12-03'),
(3, 3, 'Kutilang', 'kutilang@gmail.com', 'Owner hanya dapat menambahkan post dan bahkan tidak dapat menghapus post yang telah dibuatnya...Sabar ya', '2014-12-04');

-- --------------------------------------------------------

--
-- Table structure for table `post`
--

CREATE TABLE IF NOT EXISTS `post` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Judul` text NOT NULL,
  `Tanggal` date NOT NULL,
  `Content` text NOT NULL,
  `Author` varchar(24) NOT NULL,
  `Status` varchar(20) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `Author` (`Author`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=27 ;

--
-- Dumping data for table `post`
--

INSERT INTO `post` (`ID`, `Judul`, `Tanggal`, `Content`, `Author`, `Status`) VALUES
(1, 'Kisah User1', '2014-12-02', 'User1 adalah user yang memiliki role sebagai admin yang dapat membuat user baru, melakukan edit pada user, men-delete user serta mengedit post dan meng-publishkannya.', 'User1', 'Published'),
(2, 'Kisah User2', '2014-12-03', 'User2 adalaah user yang memiliki role sebagai editor yang dapat menambahkan post, melakukan edit pada post serta melakukan delete pada post', 'User2', 'Published'),
(3, 'Kisah User3', '2014-12-04', 'User3 adalah user yang memiliki role sebagai seorang owner yang hanya dapat melakukan postingan dan tidak dapat mengubah status post yang dibuat. Menyedihkan bukan?', 'User3', 'Published');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `Username` varchar(24) NOT NULL,
  `Password` varchar(20) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `email` text NOT NULL,
  `Role` varchar(10) NOT NULL,
  PRIMARY KEY (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`Username`, `Password`, `Name`, `email`, `Role`) VALUES
('User1', 'user1', 'IamUser1', 'user1@gmail.com', 'Admin'),
('User2', 'user2', 'IamUser2', 'user2@gmail.com', 'Editor'),
('User3', 'user3', 'IamUser3', 'user3@gmail.com', 'Owner');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `Comment_ibfk_1` FOREIGN KEY (`PostID`) REFERENCES `post` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `post`
--
ALTER TABLE `post`
  ADD CONSTRAINT `Post_ibfk_1` FOREIGN KEY (`Author`) REFERENCES `user` (`Username`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
