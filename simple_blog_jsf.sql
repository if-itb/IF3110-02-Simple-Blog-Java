-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 24, 2014 at 05:29 AM
-- Server version: 5.6.20
-- PHP Version: 5.5.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `simple_blog_jsf`
--
CREATE DATABASE IF NOT EXISTS `simple_blog_jsf` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `simple_blog_jsf`;

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
`id` int(10) NOT NULL,
  `post_id` int(10) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `comment` varchar(21327) COLLATE utf8_unicode_ci NOT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=41 ;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`id`, `post_id`, `name`, `email`, `comment`, `deleted_at`, `created_at`) VALUES
(8, 49, 'sa', 'sa', 'sa', NULL, '2014-11-23 07:40:49'),
(9, 50, 'a', 'fd', 'sf', NULL, '2014-11-23 08:10:57'),
(10, 50, 'd', 's', 'f', NULL, '2014-11-23 08:14:19'),
(11, 51, 'sadf', 'asfd', 'asf', NULL, '2014-11-23 08:14:31'),
(12, 51, 'df', 'sf', 'sf', NULL, '2014-11-23 08:14:40'),
(13, 50, '', '', '', NULL, '2014-11-23 08:24:48'),
(14, 50, '', '', '', NULL, '2014-11-23 10:51:35'),
(15, 50, '', '', '', NULL, '2014-11-23 10:55:31'),
(16, 50, '', '', '', NULL, '2014-11-23 10:55:58'),
(17, 50, '', '', '', NULL, '2014-11-23 10:57:29'),
(18, 50, '', '', '', NULL, '2014-11-23 10:57:36'),
(19, 50, '', '', '', NULL, '2014-11-23 10:57:44'),
(20, 50, '', '', '', NULL, '2014-11-23 10:58:06'),
(21, 52, '', '', '', NULL, '2014-11-23 10:59:40'),
(22, 55, '', '', '', NULL, '2014-11-23 11:00:25'),
(23, 55, '', '', '', NULL, '2014-11-23 11:01:50'),
(24, 55, '', '', '', NULL, '2014-11-23 11:01:55'),
(25, 51, '', '', '', NULL, '2014-11-23 11:03:54'),
(26, 51, '', '', '', NULL, '2014-11-23 11:03:55'),
(27, 52, 'f', 'f', 'f', NULL, '2014-11-23 11:04:21'),
(28, 52, 'f', 'f', 'f', NULL, '2014-11-23 11:04:46'),
(29, 51, '', '', '', NULL, '2014-11-23 11:12:11'),
(30, 51, '', '', '', NULL, '2014-11-23 11:12:17'),
(31, 54, 'a', 's', 'd', NULL, '2014-11-23 11:12:37'),
(32, 55, 'f', 'a', 'd', NULL, '2014-11-23 11:13:22'),
(33, 52, 'f', 'a', 's', NULL, '2014-11-23 11:13:45'),
(34, 52, 'f', '1', 's', NULL, '2014-11-23 11:13:50'),
(35, 52, 'f', 'w', 's', NULL, '2014-11-23 11:15:45'),
(36, 55, 's', 'a', 'd', NULL, '2014-11-23 11:17:39'),
(37, 55, 's', 'a', 'd', NULL, '2014-11-23 11:22:02'),
(38, 55, 's', 'a', 'd', NULL, '2014-11-23 11:22:04'),
(39, 58, 'sfd', 'sadf', 'asd', NULL, '2014-11-23 11:22:13'),
(40, 53, 'f', 'd', 'a', NULL, '2014-11-23 11:24:17');

-- --------------------------------------------------------

--
-- Table structure for table `post`
--

CREATE TABLE IF NOT EXISTS `post` (
`id` int(10) NOT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `date` date NOT NULL DEFAULT '1970-01-01',
  `content` varchar(21327) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `status` tinyint(1) NOT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=60 ;

--
-- Dumping data for table `post`
--

INSERT INTO `post` (`id`, `title`, `date`, `content`, `status`, `deleted_at`, `created_at`, `updated_at`) VALUES
(48, 'asdf', '1970-01-01', 'asf', 1, NULL, '2014-11-23 05:58:17', '2014-11-23 11:51:40'),
(49, 'asdf', '1970-01-01', 'asf', 1, NULL, '2014-11-23 06:24:43', '2014-11-23 11:50:45'),
(50, 'efw', '1970-01-01', 'a', 1, NULL, '2014-11-23 06:24:55', '2014-11-23 06:42:09'),
(51, 'ef', '1970-01-01', 'asf', 1, NULL, '2014-11-23 06:32:45', '2014-11-23 06:42:07'),
(52, 'ef', '2015-01-08', 'wow', 1, NULL, '2014-11-23 06:36:31', '2014-11-23 06:42:05'),
(53, 'ef', '1970-01-01', 'f', 1, NULL, '2014-11-23 06:39:24', '2014-11-23 06:42:03'),
(54, 'nah', '1970-01-01', 'nah', 1, NULL, '2014-11-23 06:40:56', '2014-11-23 06:41:00'),
(55, 'aaaa', '1970-01-01', 'asfsa', 1, NULL, '2014-11-23 06:48:42', '2014-11-23 06:49:38'),
(56, 'a', '1970-01-01', 'd', 1, NULL, '2014-11-23 06:49:50', '2014-11-23 06:49:53'),
(57, 'as', '1970-01-01', 'fd', 0, NULL, '2014-11-23 08:16:01', '2014-11-23 08:16:01'),
(58, 'adsds', '2015-01-08', 'adasd', 1, NULL, '2014-11-23 08:17:54', '2014-11-23 11:51:48'),
(59, 'sdf', '2009-01-06', 'sfd', 0, NULL, '2014-11-23 10:57:59', '2014-11-23 10:57:59');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
`id` int(10) NOT NULL,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `role` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `role`, `deleted_at`, `created_at`, `updated_at`) VALUES
(1, 'b', 'sf', 'a', '2014-11-23 12:20:57', '2014-11-17 09:19:11', '2014-11-23 12:20:54'),
(2, 'd', 's', 's', '2014-11-23 12:21:18', '2014-11-23 12:19:38', '2014-11-23 12:19:38'),
(3, 'sasfa', 'sdf', 'sasfd', '2014-11-23 12:21:26', '2014-11-23 12:19:51', '2014-11-23 12:21:23'),
(4, 'saf', 'af', 'asdf', NULL, '2014-11-23 12:21:29', '2014-11-23 12:21:29');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `comment`
--
ALTER TABLE `comment`
 ADD PRIMARY KEY (`id`), ADD KEY `post_id` (`post_id`);

--
-- Indexes for table `post`
--
ALTER TABLE `post`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
 ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `comment`
--
ALTER TABLE `comment`
MODIFY `id` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=41;
--
-- AUTO_INCREMENT for table `post`
--
ALTER TABLE `post`
MODIFY `id` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=60;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
MODIFY `id` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `comment`
--
ALTER TABLE `comment`
ADD CONSTRAINT `post_id_fk_1` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
