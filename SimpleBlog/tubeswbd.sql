-- phpMyAdmin SQL Dump
-- version 4.1.6
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 01, 2014 at 10:44 PM
-- Server version: 5.6.16
-- PHP Version: 5.5.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `tubeswbd`
--

-- --------------------------------------------------------

--
-- Table structure for table `komen`
--

CREATE TABLE IF NOT EXISTS `komen` (
  `id_kom` int(11) NOT NULL AUTO_INCREMENT,
  `id_post` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `waktu` varchar(255) NOT NULL,
  `komentar` text NOT NULL,
  PRIMARY KEY (`id_kom`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `komen`
--

INSERT INTO `komen` (`id_kom`, `id_post`, `username`, `email`, `waktu`, `komentar`) VALUES
(1, 38, 'ichas', 'ichas@gmail.com', '2014-12-22', 'baguss!'),
(2, 38, 'yolla', 'yolla', '2014-12-02', 'haiii');

-- --------------------------------------------------------

--
-- Table structure for table `post`
--

CREATE TABLE IF NOT EXISTS `post` (
  `id_post` int(11) NOT NULL AUTO_INCREMENT,
  `judul` varchar(255) NOT NULL,
  `tanggal` varchar(255) NOT NULL,
  `konten` text NOT NULL,
  `status` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_post`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=52 ;

--
-- Dumping data for table `post`
--

INSERT INTO `post` (`id_post`, `judul`, `tanggal`, `konten`, `status`) VALUES
(38, 'dfsfdsd', '', 'sdfsdfdsf', 1),
(42, 'Hajskdfkdnfkjsdebr', '12-12-2010', 'gkjkfgdkfnkdfnjdfnvkjkjb', 1),
(43, 'dhghfjgfjjfgf', '12-12-2010', 'vcbbvbbvcvcbcv', 1),
(44, 'dfhjsdgjdsfsd', '12/12/2012', 'dfjsdgfjhdsgfhsdf', 0),
(45, 'sfhjsgdsgd', '12-12-2010kczkv', 'skfkdhfdsf', 1),
(46, 'dfsdfsdf', '1w', 'sdfssfsdf', 1),
(47, 'qwwdd', '12/12/2012', 'sdacsafdf', 0),
(48, 'jhjdsdfd', '12/12/2012', 'jhvdvjvxcvc', 0),
(49, 'dkjkdsjdcjc', '12/12/2012', 'sdsksfjhvfvd', 1),
(51, 'hjahshshjash', '12-12-2012', 'xcmnjxnvcxv', 0);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `role` varchar(6) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  KEY `id` (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `email`, `role`) VALUES
(1, 'icha', 'icha', 'icha@gmail.com', 'owner'),
(2, 'ichax', 'ichax', 'ichax@gmail.com', 'editor'),
(3, 'ichas', 'ichas', 'ichas@gmail.com', 'admin');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
