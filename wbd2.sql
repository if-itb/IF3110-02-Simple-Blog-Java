-- phpMyAdmin SQL Dump
-- version 4.2.5
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 02, 2014 at 04:00 AM
-- Server version: 5.5.38
-- PHP Version: 5.4.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `wbd2`
--

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
`id` int(11) NOT NULL,
  `pid` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `content` text NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`id`, `pid`, `name`, `email`, `content`, `time`) VALUES
(3, 9, 'admin', 'admin@simple-blog.com', 'Test', '2014-12-01 03:50:19'),
(4, 10, 'Luthfi Hamid Masykuri', 'luthfi_hamid_m@yahoo.co.id', 'ndsdjnsjd', '2014-12-01 03:51:44'),
(5, 9, 'Luthfi', 'luthfi_hamid_m@arc.itb.ac.id', 'hahah', '2014-12-01 03:53:53');

-- --------------------------------------------------------

--
-- Table structure for table `post`
--

CREATE TABLE IF NOT EXISTS `post` (
`id` int(11) NOT NULL,
  `title` varchar(50) NOT NULL,
  `image` varchar(100) NOT NULL,
  `date` date NOT NULL,
  `content` varchar(1000) NOT NULL,
  `author` varchar(50) NOT NULL,
  `published` tinyint(1) NOT NULL,
  `deleted` tinyint(1) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=14 ;

--
-- Dumping data for table `post`
--

INSERT INTO `post` (`id`, `title`, `image`, `date`, `content`, `author`, `published`, `deleted`) VALUES
(9, 'Test123', 'admin_2014-12-01_10-35-51.61.jpg', '2014-12-01', 'vcerjh 4ewfiherufnvjern', 'admin', 0, 1),
(10, 'Coba', 'luthfi_2014-12-01_10-51-28.036.jpg', '2014-12-02', 'fweijfiweufhnjwenf e', 'luthfi', 1, 0),
(12, 'Coba lagi', 'admin_2014-12-02_01-15-21.499.jpg', '2014-12-02', 'Lorem ipsum dolor sit amet, enim oblique detracto eu eum, eum consulatu democritum interpretaris ad, usu no impedit scribentur. Libris repudiare nam in, audire expetendis mea ad, at eum choro equidem consequuntur. Te eum semper denique tractatos. Eu cum scaevola abhorreant. Bonorum recteque quaerendum mei ne.', 'admin', 0, 0),
(13, 'Test', 'admin_2014-12-02_02-23-41.815.jpg', '2014-12-01', 'Test', 'admin', 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `username` varchar(50) NOT NULL,
  `password` varchar(32) NOT NULL,
  `role` varchar(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `password`, `role`, `name`, `email`) VALUES
('admin', '21232f297a57a5a743894a0e4a801fc3', 'admin', 'MiminLab', 'admin@simple-blog.com'),
('luthfi', 'd5cd72b7bcbf56bc503904f1ac7d9bc2', 'owner', 'Luthfi Hamid Masykuri', 'luthfi_hamid_m@yahoo.co.id'),
('riva', 'ad9588ec76eb97853f9ee03ef1dc80a7', 'editor', 'riva', 'riav@riva.com');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `comment`
--
ALTER TABLE `comment`
 ADD PRIMARY KEY (`id`), ADD KEY `pid` (`pid`);

--
-- Indexes for table `post`
--
ALTER TABLE `post`
 ADD PRIMARY KEY (`id`), ADD KEY `author` (`author`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
 ADD PRIMARY KEY (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `comment`
--
ALTER TABLE `comment`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `post`
--
ALTER TABLE `post`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=14;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `comment`
--
ALTER TABLE `comment`
ADD CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `post` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `post`
--
ALTER TABLE `post`
ADD CONSTRAINT `post_ibfk_1` FOREIGN KEY (`author`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
