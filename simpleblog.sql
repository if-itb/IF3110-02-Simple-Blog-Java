-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 29, 2014 at 08:55 AM
-- Server version: 5.6.11
-- PHP Version: 5.5.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `simpleblog`
--
CREATE DATABASE IF NOT EXISTS `simpleblog` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `simpleblog`;

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
  PRIMARY KEY (`id`),
  KEY `comment_post` (`id_post`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `member`
--

CREATE TABLE IF NOT EXISTS `member` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `Email` varchar(100) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Password` varchar(12) NOT NULL,
  `Role` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `member`
--

INSERT INTO `member` (`id`, `Email`, `Name`, `Password`, `Role`) VALUES
(1, 'indam.muhammad@docomo.ne.jp', 'Arina Gendut', 'password', 'Admin'),
(2, 'agi.kautsar@docomo.ne.jp', 'Iga Agi', 'password', 'Owner'),
(3, 'arina@docomo.ne.jp', 'arinitem', 'password', 'Editor');

-- --------------------------------------------------------

--
-- Table structure for table `post`
--

CREATE TABLE IF NOT EXISTS `post` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_member` int(255) NOT NULL,
  `Status` varchar(11) NOT NULL,
  `Judul` varchar(255) NOT NULL,
  `Tanggal` date DEFAULT NULL,
  `Konten` text NOT NULL,
  `deleted` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `member_post` (`id_member`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=37 ;

--
-- Dumping data for table `post`
--

INSERT INTO `post` (`id`, `id_member`, `Status`, `Judul`, `Tanggal`, `Konten`, `deleted`) VALUES
(4, 1, 'published', 'Nanana batman', '2014-11-26', 'nananananananananananananananananananananananana BATMAN!!\r\nPengen parfait', 0),
(6, 1, 'unpublished', 'NyanNyan', '2014-11-08', 'WAini nih', 0),
(7, 1, 'unpublished', 'ABCDE', '2014-10-05', 'Yess toss Cang kacang panjang nu panjang ucing. Cang kacang panjang nu panjang ucing. Cang kacang panjang nu panjang ucing. Cang kacang panjang nu panjang ucing. Cang kacang panjang nu panjang ucing. Cang kacang panjang nu panjang ucing. Cang kacang panjang nu panjang ucing. Cang kacang panjang nu panjang ucing. Cang kacang panjang nu panjang ucing. Cang kacang panjang nu panjang ucing. Cang kacang panjang nu panjang ucing. Cang kacang panjang nu panjang ucing. Cang kacang panjang nu panjang ucing. Cang kacang panjang nu panjang ucing. Cang kacang panjang nu panjang ucing. Cang kacang panjang nu panjang ucing. Cang kacang panjang nu panjang ucing. Cang kacang panjang nu panjang ucing. Cang kacang panjang nu panjang ucing. Cang kacang panjang nu panjang ucing. Cang kacang panjang nu panjang ucing. Cang kacang panjang nu panjang ucing. Cang kacang panjang nu panjang ucing. Cang kacang panjang nu panjang ucing. Cang kacang panjang nu panjang ucing. Cang kacang panjang nu panjang ucing. Cang kacang panjang nu panjang ucing. Cang kacang panjang nu panjang ucing. Cang kacang panjang nu panjang ucing. Cang kacang panjang nu panjang ucing. Cang kacang panjang nu panjang ucing. Cang kacang panjang nu panjang ucing.', 0),
(14, 1, '', '', '0000-00-00', '', 0),
(17, 1, 'published', 'Tokyo banana', '2015-02-01', 'Enak pisan siah', 0),
(18, 1, 'unpublished', 'a', '2015-02-01', 'adalah parfait', 0),
(19, 1, 'published', 'NYUNYU', '2000-01-01', 'hahihuheho', 0),
(20, 1, 'published', 'Monyong menor CIH', '2015-02-05', 'fniow finfiiofew.', 0),
(22, 1, 'published', 'yeeeeeeeeeeeeey', '2014-10-26', 'gw tau dongggggg', 0),
(23, 1, 'published', 'Sepatu', '2016-03-08', 'gw warna biru unyunyu.\r\nAh masa?!!\r\nKYAAAAA KYAAA', 0),
(26, 1, 'published', 'lagi lagi la', '2016-02-05', 'lalalalla', 0),
(32, 1, 'published', 'Chocolate Parfait', '2016-05-01', 'dih gakebaca hiragana n kanji nya. CIHH', 0),
(35, 1, 'published', 'Mau delete', '2015-05-31', 'IH KASIAN', 1),
(36, 1, 'unpublished', 'Dihapus Huhuu Hiks', '2015-02-03', 'IH WATIR', 1);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `comment_post` FOREIGN KEY (`id_post`) REFERENCES `post` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `post`
--
ALTER TABLE `post`
  ADD CONSTRAINT `member_post` FOREIGN KEY (`id_member`) REFERENCES `member` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
