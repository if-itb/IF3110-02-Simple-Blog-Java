-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 01, 2014 at 11:25 PM
-- Server version: 5.5.32
-- PHP Version: 5.4.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `blog`
--
CREATE DATABASE IF NOT EXISTS `blog` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `blog`;

-- --------------------------------------------------------

--
-- Table structure for table `komentar`
--

CREATE TABLE IF NOT EXISTS `komentar` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idpost` int(11) NOT NULL,
  `nama` varchar(100) COLLATE latin1_general_cs NOT NULL,
  `email` varchar(100) COLLATE latin1_general_cs NOT NULL,
  `waktu` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `komentar` longtext COLLATE latin1_general_cs NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idpost` (`idpost`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_general_cs AUTO_INCREMENT=28 ;

--
-- Dumping data for table `komentar`
--

INSERT INTO `komentar` (`id`, `idpost`, `nama`, `email`, `waktu`, `komentar`) VALUES
(1, 26, 'yoga', 'rakhmatullah@yahoo.com', '2014-11-28 13:51:41', 'up'),
(2, 26, 'yoga', 'rakhmatullahyoga@gmail.com', '2014-11-29 06:54:38', 'wkwkwk'),
(3, 26, 'admin', '13512053@std.stei.itb.ac.id', '2014-11-29 07:17:49', 'hai'),
(4, 26, 'kuda', '13512053@std.stei.itb.ac.id', '2014-11-29 07:20:08', 'helloooo'),
(5, 26, 'admin', 'rakhmatullahyoga@gmail.com', '2014-11-29 07:24:45', 'yeeeyy'),
(6, 30, 'admin', 'rakhmatullahyoga@gmail.com', '2014-11-29 07:26:10', 'pertamax gan'),
(7, 30, 'mimin', '13512053@std.stei.itb.ac.id', '2014-11-29 07:35:37', 'yeey'),
(8, 30, 'si coy', 'rakhmatullahyoga@gmail.com', '2014-11-29 07:51:54', 'woy'),
(9, 26, 'zaky', 'dewazaky@gmail.com', '2014-11-29 23:54:37', 'weee'),
(10, 30, 'admin', 'rakhmatullahyoga@gmail.com', '2014-11-30 12:58:40', 'kgkhdfcdsfghjkl'),
(11, 30, 'admin', '13512053@std.stei.itb.ac.id', '2014-11-30 13:02:50', 'KLMAJGNDVAJSD'),
(12, 30, 'admin', '13512053@std.stei.itb.ac.id', '2014-11-30 13:07:05', 'qwertyuiopwertyuioasdfghjkl;'),
(13, 30, 'admin', '13512053@std.stei.itb.ac.id', '2014-11-30 13:09:41', 'asdfghjkl;zxcvbnmqwertyui\nasdfghjqwertyuixcvbnm,asdfghjk'),
(14, 30, 'admin', 'rakhmatullahyoga@gmail.com', '2014-11-30 13:14:01', 'lhkajshhcjsgc\nsvas\nfav\nsf\nasdf\nsvvf\nsf\navsf\nasf\na'),
(15, 30, 'admin', 'rakhmatullahyoga@gmail.com', '2014-11-30 13:17:20', 'kjsdm,ihnfggbjbhvfhnvbmyjmgbvfdsa'),
(16, 28, 'si fullan', '13512053@std.stei.itb.ac.id', '2014-11-30 13:22:22', 'pertamax gan!'),
(17, 28, 'si fullan', '13512053@std.stei.itb.ac.id', '2014-11-30 13:22:35', 'pertamax gan!'),
(18, 28, 'si fullan', '13512053@std.stei.itb.ac.id', '2014-11-30 13:22:47', 'pertamax gan!'),
(19, 29, 'yoga', '13512053@std.stei.itb.ac.id', '2014-11-30 13:25:21', 'yeeey'),
(20, 29, 'yoga', '13512053@std.stei.itb.ac.id', '2014-11-30 13:25:28', 'ngek ngok'),
(21, 29, 'fullan', '13512053@std.stei.itb.ac.id', '2014-11-30 13:27:12', 'fixed!'),
(22, 29, 'admin', '13512053@std.stei.itb.ac.id', '2014-11-30 13:27:27', 'lalala'),
(23, 29, 'zaky', 'dewazaky@gmail.com', '2014-11-30 13:52:45', 'pret'),
(24, 29, 'zaky', 'dewazaky@gmail.com', '2014-11-30 13:52:51', 'prot'),
(25, 26, 'Rakhmatullah Yoga Sutrisna', 'rakhmatullahyoga@gmail.com', '2014-12-01 22:15:30', 'yeyeye'),
(26, 30, 'dede', 'rakhmatullahyoga@gmail.com', '2014-12-01 22:19:05', 'yeeyy'),
(27, 28, 'dede', 'rakhmatullahyoga@gmail.com', '2014-12-01 22:22:14', 'yeey');

-- --------------------------------------------------------

--
-- Table structure for table `post`
--

CREATE TABLE IF NOT EXISTS `post` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `judul` varchar(255) COLLATE latin1_general_cs NOT NULL,
  `tanggal` date NOT NULL,
  `konten` text COLLATE latin1_general_cs NOT NULL,
  `publishStatus` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_general_cs AUTO_INCREMENT=32 ;

--
-- Dumping data for table `post`
--

INSERT INTO `post` (`id`, `judul`, `tanggal`, `konten`, `publishStatus`) VALUES
(26, 'hhh', '2014-11-26', 'rte', 1),
(28, 'test', '2014-11-07', 'lalala', 1),
(29, 'bugtesting1', '2014-10-14', 'gak valid', 1),
(30, 'bugtesting2', '2014-11-25', 'salah woy', 1),
(31, '', '2014-11-28', '', 0);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `username` varchar(10) COLLATE latin1_general_cs NOT NULL,
  `nama` varchar(50) COLLATE latin1_general_cs NOT NULL,
  `email` varchar(50) COLLATE latin1_general_cs NOT NULL,
  `password` varchar(100) COLLATE latin1_general_cs NOT NULL,
  `role` char(6) COLLATE latin1_general_cs NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_cs;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `nama`, `email`, `password`, `role`) VALUES
('admin', 'zaky', 'dewazaky@gmail.com', 'admin', 'admin'),
('aji', 'Try Ajitiono', 'rakhmatullahyoga@gmail.com', 'aji', 'owner'),
('dede', 'dede', 'rakhmatullahyoga@gmail.com', 'dede', 'editor'),
('yoga', 'Rakhmatullah Yoga Sutrisna', 'rakhmatullahyoga@gmail.com', 'godlike123', 'admin');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `komentar`
--
ALTER TABLE `komentar`
  ADD CONSTRAINT `komentar_ibfk_1` FOREIGN KEY (`idpost`) REFERENCES `post` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
