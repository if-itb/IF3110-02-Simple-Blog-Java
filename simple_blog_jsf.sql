-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 26, 2014 at 06:43 PM
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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=120 ;

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
(40, 53, 'f', 'd', 'a', NULL, '2014-11-23 11:24:17'),
(41, 48, 'asf', 'safsa', 'f', NULL, '2014-11-26 06:04:45'),
(42, 48, 'asf', 'safsa', 'f', NULL, '2014-11-26 06:04:58'),
(87, 48, 'asf', 'asdf@afsd.asdf', 'asfd', NULL, '2014-11-26 10:02:34'),
(88, 48, 'saf', 'asdf@afsd.asdf', 'asfd', NULL, '2014-11-26 10:02:40'),
(89, 57, 'af', 'asf@asdf.sdaf', 'asf', NULL, '2014-11-26 10:03:05'),
(90, 63, 'satuu', 'asas@a.ce', 'asdf', NULL, '2014-11-26 11:25:10'),
(91, 63, 'satuu', 'asas@a.ce', 'asdf', NULL, '2014-11-26 11:25:17'),
(92, 63, 'satu', 'as@asdf.ds', 'asdf', NULL, '2014-11-26 11:25:29'),
(93, 63, 'satu', 'as@asdf.ds', 'asdf', NULL, '2014-11-26 11:25:34'),
(94, 63, 'satu', 'as@asdf.ds', 'asdf', NULL, '2014-11-26 11:25:34'),
(95, 63, 'satu', 'as@asdf.ds', 'asdf', NULL, '2014-11-26 11:25:35'),
(96, 63, 'satu', 'as@asdf.ds', 'asdf', NULL, '2014-11-26 11:25:35'),
(97, 63, 'as', 'as@asdf.ds', 'saf', NULL, '2014-11-26 11:30:46'),
(98, 63, 'd', 'd@ds.sd', 'sd', NULL, '2014-11-26 11:32:09'),
(99, 63, 'a', 'asdf@afsd.asdfa', 'a', NULL, '2014-11-26 11:34:21'),
(100, 63, 'asd', 'asdf@asdf.adsf', 'asdf', NULL, '2014-11-26 11:38:09'),
(101, 63, 'f', 'f@d.df', 'f', NULL, '2014-11-26 11:38:28'),
(102, 57, 'hjkj', 'jk@sdafk.df', 'sfa', NULL, '2014-11-26 12:10:26'),
(103, 63, '', '', 'd', NULL, '2014-11-26 17:23:56'),
(104, 63, 'admin', '', 'nanana', NULL, '2014-11-26 17:30:46'),
(105, 63, 'admin', 'nana@email.com', 'lala', NULL, '2014-11-26 17:31:20'),
(106, 63, 'admin', 'nana@email.com', 'lala', NULL, '2014-11-26 17:31:22'),
(107, 63, 'admin', 'nana@email.com', 'lala', NULL, '2014-11-26 17:31:23'),
(108, 63, 'admin', 'nana@email.com', 'lala', NULL, '2014-11-26 17:31:24'),
(109, 63, 'admin', 'nana@email.com', 'lala', NULL, '2014-11-26 17:31:24'),
(110, 63, 'admin', 'nana@email.com', 'lala', NULL, '2014-11-26 17:31:25'),
(111, 63, 'admin', 'nana@email.com', 'lala', NULL, '2014-11-26 17:31:25'),
(112, 63, 'admin', 'nana@email.com', 'lala', NULL, '2014-11-26 17:31:26'),
(113, 63, 'admin', 'nana@email.com', 'lala', NULL, '2014-11-26 17:31:26'),
(114, 63, 'admin', 'nana@email.com', 'lala', NULL, '2014-11-26 17:31:27'),
(115, 63, 'admin', 'nana@email.com', 'lala', NULL, '2014-11-26 17:31:27'),
(116, 63, 'admin', 'nana@email.com', 'lala', NULL, '2014-11-26 17:31:33'),
(117, 63, 'admin', '', 'asdf', NULL, '2014-11-26 17:31:53'),
(118, 63, 'admin', '', 'f', NULL, '2014-11-26 17:34:00'),
(119, 63, 'd', 'd@ds.sd', 'd', NULL, '2014-11-26 17:34:36');

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=64 ;

--
-- Dumping data for table `post`
--

INSERT INTO `post` (`id`, `title`, `date`, `content`, `status`, `deleted_at`, `created_at`, `updated_at`) VALUES
(48, 'asdf', '2015-01-24', 'asf', 1, NULL, '2014-11-23 05:58:17', '2014-11-26 11:07:58'),
(49, 'asdf', '1970-01-01', 'asf', 1, NULL, '2014-11-23 06:24:43', '2014-11-26 05:53:12'),
(50, 'efw', '1970-01-01', 'a', 1, NULL, '2014-11-23 06:24:55', '2014-11-23 06:42:09'),
(51, 'ef', '1970-01-01', 'asf', 1, NULL, '2014-11-23 06:32:45', '2014-11-23 06:42:07'),
(52, 'ef', '2015-01-08', 'wow', 1, NULL, '2014-11-23 06:36:31', '2014-11-23 06:42:05'),
(53, 'ef', '1970-01-01', 'f', 1, NULL, '2014-11-23 06:39:24', '2014-11-23 06:42:03'),
(54, 'nah', '1970-01-01', 'nah', 1, NULL, '2014-11-23 06:40:56', '2014-11-23 06:41:00'),
(55, 'aaaa', '1970-01-01', 'asfsa', 1, NULL, '2014-11-23 06:48:42', '2014-11-23 06:49:38'),
(56, 'a', '1970-01-01', 'd', 1, NULL, '2014-11-23 06:49:50', '2014-11-23 06:49:53'),
(57, 'as', '1970-01-01', 'fd', 1, NULL, '2014-11-23 08:16:01', '2014-11-26 05:54:46'),
(58, 'adsds', '2015-01-08', 'adasd', 1, NULL, '2014-11-23 08:17:54', '2014-11-23 11:51:48'),
(59, 'sdf', '2009-01-06', 'sfd', 1, NULL, '2014-11-23 10:57:59', '2014-11-26 10:54:31'),
(60, '1232131', '1970-01-01', '31231', 0, NULL, '2014-11-26 05:38:39', '2014-11-26 05:38:39'),
(61, '123123', '1970-01-01', '312', 0, NULL, '2014-11-26 05:39:00', '2014-11-26 05:39:00'),
(62, 'aa', '2016-01-24', 'aa', 0, NULL, '2014-11-26 11:21:11', '2014-11-26 11:21:11'),
(63, 'judul1', '2015-11-11', 'asdfasdf', 1, NULL, '2014-11-26 11:21:31', '2014-11-26 11:21:55');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
`id` int(10) NOT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `role` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `email`, `username`, `password`, `role`, `deleted_at`, `created_at`, `updated_at`) VALUES
(1, '', 'b', 'sf', 'a', '2014-11-23 12:20:57', '2014-11-17 09:19:11', '2014-11-23 12:20:54'),
(2, '', 'd', 's', 's', '2014-11-23 12:21:18', '2014-11-23 12:19:38', '2014-11-23 12:19:38'),
(3, '', 'sasfa', 'sdf', 'sasfd', '2014-11-23 12:21:26', '2014-11-23 12:19:51', '2014-11-23 12:21:23'),
(4, '', 'asdf', 'asdf', 'sd', '2014-11-26 10:43:18', '2014-11-23 12:21:29', '2014-11-26 10:43:13'),
(5, 'admin@admin.com', 'admin', 'admin', 'admin', NULL, '2014-11-26 10:36:39', '2014-11-26 11:38:45'),
(6, 'owner@owner.com', 'owner', 'owner', 'owner', NULL, '2014-11-26 10:42:57', '2014-11-26 10:42:57'),
(7, 'editor@editor.com', 'editor', 'editor', 'editor', NULL, '2014-11-26 11:38:53', '2014-11-26 11:38:53');

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
MODIFY `id` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=120;
--
-- AUTO_INCREMENT for table `post`
--
ALTER TABLE `post`
MODIFY `id` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=64;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
MODIFY `id` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=8;
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
