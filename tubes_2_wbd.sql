-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 25, 2014 at 04:44 PM
-- Server version: 5.6.16
-- PHP Version: 5.5.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `tubes_2_wbd`
--

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `post_id` int(11) NOT NULL,
  `nama` varchar(100) NOT NULL DEFAULT 'anonymous',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `konten` varchar(1000) NOT NULL,
  `email` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `post_id` (`post_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=49 ;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`id`, `post_id`, `nama`, `created_at`, `konten`, `email`) VALUES
(43, 27, 'a', '2014-11-25 12:52:51', 'abc', 'a'),
(44, 27, 'a', '2014-11-25 12:55:04', 'abc', 'a'),
(45, 27, 'a', '2014-11-25 12:55:51', 'a', 'a'),
(46, 27, 'abc', '2014-11-25 12:56:03', 'abc', 'abc'),
(47, 27, 'abcd', '2014-11-25 12:57:33', 'abcd', 'abcd'),
(48, 27, 'e', '2014-11-25 13:00:24', 'e', 'e@e.com');

-- --------------------------------------------------------

--
-- Table structure for table `post`
--

CREATE TABLE IF NOT EXISTS `post` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `judul` varchar(100) NOT NULL,
  `tanggal` date NOT NULL,
  `konten` text,
  `published` tinyint(4) NOT NULL DEFAULT '0',
  `image_url` varchar(500) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=29 ;

--
-- Dumping data for table `post`
--

INSERT INTO `post` (`id`, `judul`, `tanggal`, `konten`, `published`, `image_url`, `user_id`) VALUES
(26, 'woi', '2014-10-10', 'woi', 1, NULL, 1),
(27, 'halo', '2023-02-01', 'halo', 1, NULL, 1),
(28, 'asd', '2014-11-25', 'asd', 0, NULL, 1);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(200) NOT NULL,
  `password` varchar(200) NOT NULL,
  `nama` varchar(200) NOT NULL,
  `is_admin` tinyint(4) NOT NULL DEFAULT '0',
  `is_owner` tinyint(4) NOT NULL DEFAULT '0',
  `is_editor` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=14 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `email`, `password`, `nama`, `is_admin`, `is_owner`, `is_editor`) VALUES
(1, 'a@a.com', '86f7e437faa5a7fce15d1ddcb9eaeaea377667b8', 'Administrator Lagi', 1, 1, 1),
(12, 'o@o.com', '7a81af3e591ac713f81ea1efe93dcf36157d8376', 'Owner', 0, 1, 0),
(13, 'e@e.com', '58e6b3a414a1e090dfc6029add0f3555ccba127f', 'Editor', 0, 0, 1);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `post`
--
ALTER TABLE `post`
  ADD CONSTRAINT `post_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
