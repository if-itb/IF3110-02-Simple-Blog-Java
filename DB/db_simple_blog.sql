-- phpMyAdmin SQL Dump
-- version 4.1.6
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 29, 2014 at 09:15 AM
-- Server version: 5.6.16
-- PHP Version: 5.5.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `db_simple_blog`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_comment`
--

CREATE TABLE IF NOT EXISTS `tbl_comment` (
  `id_comment` int(10) NOT NULL AUTO_INCREMENT,
  `id_post` int(10) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `tanggal` datetime NOT NULL,
  `komentar` text NOT NULL,
  PRIMARY KEY (`id_comment`),
  KEY `id_post` (`id_post`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=14 ;

--
-- Dumping data for table `tbl_comment`
--

INSERT INTO `tbl_comment` (`id_comment`, `id_post`, `nama`, `email`, `tanggal`, `komentar`) VALUES
(1, 5, 'Anonymous', 'saya@simail.com', '2014-10-12 00:00:00', 'loerem'),
(4, 13, 'rapi', 'rapi@simail.com', '2014-10-13 00:00:00', 'top'),
(5, 13, 'Anonymous', 'tanpa@simail.com', '2014-10-13 00:00:00', 'sip'),
(6, 5, 'saya', 'saya@saya.com', '2014-10-15 00:00:00', 'oy'),
(7, 5, 'aink', 'aink@aink.com', '2014-11-28 08:26:10', 'a'),
(8, 5, 'midum', 'aink@aink.com', '2014-11-28 13:09:53', 'sadasd'),
(9, 5, 'ad', 'asds', '2014-11-28 13:13:38', 'asdas'),
(10, 5, 'dasasd', 'asdasd', '2014-11-28 13:15:29', 'asdsa'),
(11, 5, 'asdasd', 'asdasdasd', '2014-11-28 15:52:56', 'asdsadasd'),
(12, 5, 'ere', 'dzir.shhh@gmail.com', '2014-11-28 16:14:12', 'erere'),
(13, 5, 'aink', 'aink@aink.aink', '2014-11-28 16:15:27', 'sdasdsd');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_posting`
--

CREATE TABLE IF NOT EXISTS `tbl_posting` (
  `id_post` int(10) NOT NULL AUTO_INCREMENT,
  `judul` varchar(30) NOT NULL,
  `tanggal` date NOT NULL,
  `konten` text NOT NULL,
  `status_published` tinyint(1) NOT NULL DEFAULT '0',
  `status_delete` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_post`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=16 ;

--
-- Dumping data for table `tbl_posting`
--

INSERT INTO `tbl_posting` (`id_post`, `judul`, `tanggal`, `konten`, `status_published`, `status_delete`) VALUES
(5, 'Apa itu Simple Blog?', '2014-07-15', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Blanditiis aliquam minus consequuntur amet nulla eius, neque beatae, nostrum possimus, officiis eaque consectetur. Sequi sunt maiores dolore, illum quidem eos explicabo! Lorem ipsum dolor sit amet, consectetur adipisicing elit. Magnam consequuntur consequatur molestiae saepe sed, incidunt sunt inventore minima voluptatum adipisci hic, est ipsa iste. Nobis, aperiam provident quae. Reprehenderit, iste.\r\n\r\nLorem ipsum dolor sit amet, consectetur adipisicing elit. Asperiores animi tenetur nam delectus eveniet iste non culpa laborum provident minima numquam excepturi rem commodi, officia accusamus eos voluptates obcaecati. Possimus?', 1, 0),
(13, 'Hari ini?', '2014-10-13', 'Hari ini pengumpulan nge-pull request WBD', 1, 1),
(14, 'Barutut', '2014-11-28', 'Lorem ', 0, 0),
(15, 'Barutut explain 2', '2014-11-29', 'Lorem X', 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_role`
--

CREATE TABLE IF NOT EXISTS `tbl_role` (
  `role_id` int(10) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(25) NOT NULL,
  `description` varchar(50) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `tbl_role`
--

INSERT INTO `tbl_role` (`role_id`, `role_name`, `description`) VALUES
(1, 'Guest', 'User biasa'),
(2, 'Admin', 'Administrator'),
(3, 'Owner', 'Pemilik Blog'),
(4, 'Editor', 'Penulis');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_user`
--

CREATE TABLE IF NOT EXISTS `tbl_user` (
  `user_id` int(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `role_id` int(10) DEFAULT '1',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`),
  KEY `role_id` (`role_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `tbl_user`
--

INSERT INTO `tbl_user` (`user_id`, `username`, `password`, `name`, `email`, `role_id`) VALUES
(1, 'admin', 'admin', 'administrator', '', 2),
(2, 'aink', 'pisan', 'aaa', 'aaa', 3),
(3, 'aku', 'iya', 'aku', 'aku', 4),
(4, 'abc', '5dasar', 'aink', 'asink@ain.com', 2);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tbl_comment`
--
ALTER TABLE `tbl_comment`
  ADD CONSTRAINT `tbl_comment_ibfk_1` FOREIGN KEY (`id_post`) REFERENCES `tbl_posting` (`id_post`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tbl_user`
--
ALTER TABLE `tbl_user`
  ADD CONSTRAINT `tbl_user_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `tbl_role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
