-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 22, 2014 at 03:28 AM
-- Server version: 5.6.20
-- PHP Version: 5.5.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `db_simpleblog`
--
CREATE DATABASE IF NOT EXISTS `db_simpleblog` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `db_simpleblog`;

-- --------------------------------------------------------

--
-- Table structure for table `komentar`
--

CREATE TABLE IF NOT EXISTS `komentar` (
`id` int(11) NOT NULL,
  `post_id` int(11) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `komentar` text NOT NULL,
  `tanggal` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `post`
--

CREATE TABLE IF NOT EXISTS `post` (
`id` int(11) NOT NULL,
  `judul` varchar(100) NOT NULL,
  `tanggal` date NOT NULL,
  `konten` text NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `post`
--

INSERT INTO `post` (`id`, `judul`, `tanggal`, `konten`) VALUES
(1, 'Apa itu simple blogs?', '2014-11-09', '<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Blanditiis aliquam minus consequuntur amet nulla eius, neque beatae, nostrum possimus, officiis eaque consectetur. Sequi sunt maiores dolore, illum quidem eos explicabo! Lorem ipsum dolor sit amet, consectetur adipisicing elit. Magnam consequuntur consequatur molestiae saepe sed, incidunt sunt inventore minima voluptatum adipisci hic, est ipsa iste. Nobis, aperiam provident quae. Reprehenderit, iste.</p>\r\n\r\n<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Asperiores animi tenetur nam delectus eveniet iste non culpa laborum provident minima numquam excepturi rem commodi, officia accusamus eos voluptates obcaecati. Possimuse?</p>'),
(2, 'Siapa Dibalik Simple Blog?', '2014-10-23', '<p>Donec id elit non mi porta gravida at eget metus. Cras mattis consectetur purus sit amet fermentum.</p>\r\n\r\n<p>Maecenas faucibus mollis interdum. Donec sed odio dui. Morbi leo risus, porta ac consectetur ac, vestibulum at eros. Curabitur blandit tempus porttitor.</p>');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `komentar`
--
ALTER TABLE `komentar`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `post`
--
ALTER TABLE `post`
 ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `komentar`
--
ALTER TABLE `komentar`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `post`
--
ALTER TABLE `post`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
