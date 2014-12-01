-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 01, 2014 at 07:17 PM
-- Server version: 5.6.11
-- PHP Version: 5.5.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `wbdhebat`
--
CREATE DATABASE IF NOT EXISTS `wbdhebat` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `wbdhebat`;

-- --------------------------------------------------------

--
-- Table structure for table `komentar`
--

CREATE TABLE IF NOT EXISTS `komentar` (
  `name` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `tanggal` datetime DEFAULT NULL,
  `komentar` text,
  `post_id` int(11) NOT NULL,
  KEY `fk_komentar_post_idx` (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `komentar`
--

INSERT INTO `komentar` (`name`, `email`, `tanggal`, `komentar`, `post_id`) VALUES
('Editor I', 'editor@wbd.com', '2014-11-24 05:58:15', 'Ahey', 5),
('Editor I', 'editor@wbd.com', '2014-11-24 05:58:20', 'Woi', 5),
('Editor I', 'editor@wbd.com', '2014-11-24 05:58:26', 'Blaem', 5),
('a', 'asep@gmail.com', '2014-12-02 01:14:00', 'haha', 2);

-- --------------------------------------------------------

--
-- Table structure for table `post`
--

CREATE TABLE IF NOT EXISTS `post` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `judul` varchar(200) DEFAULT NULL,
  `tanggal` datetime DEFAULT NULL,
  `konten` text,
  `status` varchar(20) DEFAULT NULL,
  `image` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=14 ;

--
-- Dumping data for table `post`
--

INSERT INTO `post` (`id`, `judul`, `tanggal`, `konten`, `status`, `image`) VALUES
(2, 'Unpublished', '2015-11-02 00:00:00', 'Hahayyyy', 'Published', 'Gods-Valley-Dawn.jpg'),
(5, 'Tes Siomay Note', '2015-01-23 00:00:00', 'Hahaa', 'Published', ''),
(6, 'Tes Lagi', '2016-11-11 00:00:00', 'Uhuk', 'Published', ''),
(7, 'Tes Lagi 2', '2017-11-11 00:00:00', 'Uhuk 2', 'Unpublished', 'lib.PNG'),
(8, 'Uhuk 2', '2019-11-11 00:00:00', 'Jebret', 'Published', ''),
(9, 'Teskjlsdk ', '2019-11-11 00:00:00', 'Jskads', 'Unpublished', ''),
(10, 'Hahay', '2015-11-12 00:00:00', 'KHDsa', 'Unpublished', ''),
(11, 'Uhuyyyy', '2016-11-11 00:00:00', 'Uhuy', 'Published', ''),
(12, 'Upload', '2016-11-01 00:00:00', 'Aheyy', 'Unpublished', NULL),
(13, 'Aa', '2016-12-13 00:00:00', 'Hahayyyy', 'Unpublished', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `role` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`email`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `email`, `password`, `name`, `role`) VALUES
(1, 'ibnu.komarudin@gmail.com', 'bandung', 'Asep Saepudin', 'Admin'),
(3, 'owner@wbd.com', 'owner', 'Owner I', 'Owner'),
(4, 'editor@wbd.com', 'editor', 'Editor I', 'Editor'),
(5, 'admin@wbd.com', 'admin', 'Admin I', 'Admin'),
(8, 'admin2@wbd.com', 'admin2', 'Admin II', 'Admin'),
(9, 'editor2@wbd.com', 'editor2', 'Editor II', 'Editor'),
(10, 'owner2@wbd.com', 'owner2', 'Owner II', 'Owner');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `komentar`
--
ALTER TABLE `komentar`
  ADD CONSTRAINT `fk_komentar_post` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
