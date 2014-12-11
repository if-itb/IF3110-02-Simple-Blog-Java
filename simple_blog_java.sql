-- phpMyAdmin SQL Dump
-- version 4.1.6
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 01, 2014 at 04:06 AM
-- Server version: 5.6.16
-- PHP Version: 5.5.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `simple_blog_java`
--
CREATE DATABASE IF NOT EXISTS `simple_blog_java` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `simple_blog_java`;

-- --------------------------------------------------------

--
-- Table structure for table `tb_cmt`
--

DROP TABLE IF EXISTS `tb_cmt`;
CREATE TABLE IF NOT EXISTS `tb_cmt` (
  `cid` bigint(20) NOT NULL AUTO_INCREMENT,
  `pid` bigint(20) NOT NULL,
  `name` text NOT NULL,
  `email` text NOT NULL,
  `ccontent` text NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `tb_cmt`
--

INSERT INTO `tb_cmt` (`cid`, `pid`, `name`, `email`, `ccontent`, `timestamp`) VALUES
(1, 2, 'Tito', 'tito@tito.com', 'Ini komentar Tito', '2014-11-26 15:48:10'),
(2, 2, 'Tito2', 'tito@tito.com2', 'Ini komentar Tito2', '2014-11-26 15:48:27'),
(7, 2, 'nama3', 'email3', 'komen3', '2014-11-26 16:04:44'),
(8, 2, 'nama4', 'email4', 'komen4', '2014-11-26 16:07:54'),
(9, 6, 'Asri', 'Cantik', 'ini komentar asri\n', '2014-11-27 03:18:51');

-- --------------------------------------------------------

--
-- Table structure for table `tb_post`
--

DROP TABLE IF EXISTS `tb_post`;
CREATE TABLE IF NOT EXISTS `tb_post` (
  `pid` bigint(20) NOT NULL AUTO_INCREMENT,
  `pdate` date NOT NULL,
  `ptitle` text NOT NULL,
  `pcontent` text NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `isPublished` tinyint(1) NOT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Dumping data for table `tb_post`
--

INSERT INTO `tb_post` (`pid`, `pdate`, `ptitle`, `pcontent`, `timestamp`, `isPublished`) VALUES
(2, '2014-11-11', 'Post Kedua', 'Ini isi dari post kedua. Lorem ipsum dolor sit amet, consectetur adipisicing elit. Perferendis repudiandae quae natus quos alias eos repellendus a obcaecati cupiditate similique quibusdam, atque omnis illum, minus ex dolorem facilis tempora deserunt!salaknfksdjcnskdjcsdcsdc', '2014-11-25 11:49:14', 1),
(6, '2014-11-20', 'Post Keempat', 'Ini post keempat. syalalala ding dong', '2014-11-25 16:04:13', 1),
(9, '2014-11-05', 'Ini gw tambahin', 'Syalalala ding dong', '2014-11-25 19:14:01', 0),
(10, '2014-10-10', 'syala dirubah', 'asdadasdasdasdasdasddakc asdkbsdahkjcbasdkjchbsakdjhcbsakjdhcbaskdhjcbsakdjhcbsdkajhcbsakjdcsdc', '2014-11-25 19:54:17', 1),
(11, '2014-08-08', 'Syalalala', 'eyeyeye', '2014-11-27 03:19:16', 1);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
