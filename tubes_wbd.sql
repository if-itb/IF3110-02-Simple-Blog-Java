-- phpMyAdmin SQL Dump
-- version 4.1.6
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 28, 2014 at 02:14 PM
-- Server version: 5.6.16
-- PHP Version: 5.5.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `tubes_wbd`
--

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `post_id` int(11) NOT NULL,
  `content` text NOT NULL,
  `date` datetime NOT NULL,
  `email` varchar(100) NOT NULL,
  PRIMARY KEY (`comment_id`,`post_id`),
  KEY `user_id` (`name`),
  KEY `post_id` (`post_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=25 ;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`comment_id`, `name`, `post_id`, `content`, `date`, `email`) VALUES
(2, 'admin', 3, 'test', '2014-11-26 00:00:00', 'admin@admin.com'),
(3, 'admin', 3, 'test2', '2014-11-26 00:00:00', 'admin@admin.com'),
(4, 'admin', 3, 'cxcxzvcdsvfdsvfd', '2014-11-26 12:55:12', 'admin@admin.com'),
(5, 'gilang', 3, 'comment lagi nih', '2014-11-26 12:58:48', 'gilang.9h@edited.com'),
(6, 'dummy user', 3, 'dsfkjkjgbwkjebgj', '2014-11-26 13:01:03', 'gdsbgjvsb@fsdkjbk.com'),
(7, 'anonymous', 3, 'dfdssdf', '2014-11-26 13:24:15', 'anonymous@anonym.com'),
(10, 'anonymous', 3, 'refdsnfjksdnkjsdnf', '2014-11-26 14:49:13', 'editor@editor.com'),
(12, 'anonymous', 3, 'hihihi', '2014-11-26 14:55:09', 'editor@editor.com'),
(13, 'anonym', 3, 'yah gitulah', '2014-11-26 14:55:40', 'editor@lalala.com'),
(14, 'anonymous', 3, 'fdsffdgdfgfgfddffdfsd', '2014-11-26 14:58:22', 'editor@editor.com'),
(17, 'admin', 5, 'admin here', '2014-11-28 16:49:31', 'admin@admin.com'),
(18, 'anonymous', 3, 'fdsfsd', '2014-11-28 17:38:25', 'dsfsdfds'),
(19, 'anonymous', 3, '', '2014-11-28 17:38:26', 'dsfsdfds'),
(20, 'anonymous', 3, '', '2014-11-28 17:38:26', 'dsfsdfds'),
(21, 'anonymous', 3, '', '2014-11-28 17:38:26', 'dsfsdfds'),
(22, 'anonymous', 3, '', '2014-11-28 17:38:27', 'dsfsdfds'),
(23, 'anonymous', 3, 'sadfdafdsf', '2014-11-28 17:38:33', 'anonim@an.com'),
(24, 'admin', 5, 'new coommemfdbvhjsfbvkj', '2014-11-28 17:44:17', 'admin@admin.com');

-- --------------------------------------------------------

--
-- Table structure for table `post`
--

CREATE TABLE IF NOT EXISTS `post` (
  `post_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(20) NOT NULL,
  `title` varchar(100) NOT NULL,
  `content` text NOT NULL,
  `date` datetime NOT NULL,
  `published` tinyint(1) NOT NULL,
  `deleted` tinyint(1) NOT NULL,
  PRIMARY KEY (`post_id`,`user_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `post`
--

INSERT INTO `post` (`post_id`, `user_id`, `title`, `content`, `date`, `published`, `deleted`) VALUES
(3, 'gilang', 'wowowowowoowwo', 'lorem ipsum dolor sir amet', '2014-11-11 00:00:00', 1, 0),
(5, 'admin', 'blahblahblah', 'edited content by admin', '2014-11-28 00:00:00', 1, 0),
(6, 'owner', 'New Second Post', 'svnrekgeubgs jhg ersjhg erhjg jrsg jrgjhergjes', '2014-11-29 00:00:00', 0, 0),
(8, 'admin', 'blahblahblah', 'blahblahblah', '2014-12-12 00:00:00', 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `user_id` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `role` int(11) NOT NULL,
  `email` varchar(50) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `password`, `role`, `email`) VALUES
('admin', 'admin', 3, 'admin@admin.com'),
('editor', 'editor', 2, 'editor@editor.com'),
('gilang', 'wohoho', 1, 'gilang.9h@edited.com'),
('guest', '', 4, ''),
('owner', 'owner', 1, 'owner@owner.com');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `post`
--
ALTER TABLE `post`
  ADD CONSTRAINT `post_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
