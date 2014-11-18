-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 18, 2014 at 07:24 AM
-- Server version: 5.5.27
-- PHP Version: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `simpleblog`
--

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `id_post` int(11) NOT NULL,
  `Nama` varchar(255) NOT NULL,
  `Email` varchar(255) NOT NULL,
  `Tanggal` varchar(255) NOT NULL,
  `Komentar` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`id`, `id_post`, `Nama`, `Email`, `Tanggal`, `Komentar`) VALUES
(1, 1, 'Keren', 'keren@banget.co', '14 Oct 2014 06:38', 'Ini contoh komentar'),
(2, 4, 'arina', 'arinalistyarini@yahoo.com', '17 Nov 2014 10:21', 'aku kribo loochh');

-- --------------------------------------------------------

--
-- Table structure for table `member`
--

CREATE TABLE IF NOT EXISTS `member` (
  `Email` varchar(100) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Password` varchar(12) NOT NULL,
  `Role` varchar(100) NOT NULL,
  PRIMARY KEY (`Email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `member_post`
--

CREATE TABLE IF NOT EXISTS `member_post` (
  `id_post` int(11) NOT NULL,
  `member_email` varchar(100) NOT NULL,
  PRIMARY KEY (`id_post`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `post`
--

CREATE TABLE IF NOT EXISTS `post` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Status` varchar(10) NOT NULL,
  `Judul` varchar(255) NOT NULL,
  `Tanggal` varchar(255) NOT NULL,
  `Konten` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `post`
--

INSERT INTO `post` (`id`, `Status`, `Judul`, `Tanggal`, `Konten`) VALUES
(1, 'Publish', 'Post Pertama', '14 Oktober 2014', 'Simple blog ini dikerjakan untuk memenuhi tugas matakuliah IF3110 Pengembangan Aplikasi Berbasis Web.\r\n\r\nNama: Arina Listyarini Dwiastuti\r\nNIM: 13512006\r\nKelas: K-01\r\n\r\nSekian.'),
(4, 'Publish', 'Deadline WBD', '15 Oktober 2014', 'Deadline WBD diundur menjadi pukul 07.00 WIB 15 Oktober 2014.\r\n\r\nTerima kasih\r\n\r\n:)');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
