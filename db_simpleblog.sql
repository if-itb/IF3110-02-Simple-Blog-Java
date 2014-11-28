-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 28, 2014 at 11:23 PM
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
-- Table structure for table `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
`id` int(11) NOT NULL,
  `idpost` int(11) NOT NULL,
  `commentator` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `comment_content` text NOT NULL,
  `tanggal` date NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`id`, `idpost`, `commentator`, `email`, `comment_content`, `tanggal`) VALUES
(1, 1, 'Joko', 'joko@yahoo.com', 'Ini adalah komentar', '2014-11-29'),
(2, 1, 'asd', 'asd', 'ads', '2014-10-12'),
(3, 1, 'sdf', 'sdf', 'sfd', '2014-10-12'),
(4, 1, 'sdf', 'sdf', 'qqwrqwr', '2014-11-29'),
(5, 1, 'qwe', 'qwesad', 'zxczc', '2014-11-29'),
(6, 1, 'qwe', 'qwesad', 'zxczc', '2014-11-29'),
(7, 1, 'asdsad', 'asd@asd.com', 'asdsad', '2014-11-29');

-- --------------------------------------------------------

--
-- Table structure for table `post`
--

CREATE TABLE IF NOT EXISTS `post` (
`id` int(11) NOT NULL,
  `judul` varchar(100) NOT NULL,
  `tanggal` date NOT NULL,
  `konten` text NOT NULL,
  `status` varchar(20) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=74 ;

--
-- Dumping data for table `post`
--

INSERT INTO `post` (`id`, `judul`, `tanggal`, `konten`, `status`) VALUES
(1, 'Apa itu simple blongs?', '2014-11-10', 'Lorm ipsum dolor sit amet, consectetur adipisicing elit. Blanditiis aliquam minus consequuntur amet nulla eius, neque beatae, nostrum possimus, officiis eaque consectetur. Sequi sunt maiores dolore, illum quidem eos explicabo! Lorem ipsum dolor sit amet, consectetur adipisicing elit. Magnam consequuntur consequatur molestiae saepe sed, incidunt sunt inventore minima voluptatum adipisci hic, est ipsa iste. Nobis, aperiam provident quae. Reprehenderit, iste.\r\n\r\nLorem ipsum dolor sit amet, consectetur adipisicing elit. Asperiores animi tenetur nam delectus eveniet iste non culpa laborum provident minima numquam excepturi rem commodi, officia accusamus eos voluptates obcaecati. Possimuse?', 'published'),
(2, 'Siapa Dibalik Simple Blog?', '2014-10-23', 'Donec id elit non mi porta gravida at eget metus. Cras mattis consectetur purus sit amet fermentum.\r\n\r\nMaecenas faucibus mollis interdum. Donec sed odio dui. Morbi leo risus, porta ac consectetur ac, vestibulum at eros. Curabitur blandit tempus porttitor.', 'published'),
(39, 'Kenapa asd QWER', '2014-10-21', 'Face id elit non mi porta gravida at eget metus. Cras mattis consectetur purus sit amet fermentum.\r\n\r\nMaecenas faucibus mollis interdum. Donec sed odio dui. Morbi leo risus, porta ac consectetur ac, vestibulum at eros. Curabitur blandit tempus porttitor.', 'unpublished'),
(42, 'Aku', '2009-12-23', 'asdasd', 'published'),
(43, 'Dia', '2009-12-23', 'asdasd', 'unpublished'),
(44, 'KAMI', '2009-12-23', 'sadasdasd', 'unpublished'),
(45, 'KAMI', '2009-12-23', 'sadasdasd', 'unpublished'),
(46, 'MEREKA', '2009-12-23', 'asdasd', 'unpublished'),
(47, 'Kalian', '2009-12-23', 'asdasdas', 'unpublished'),
(48, 'asd', '2009-12-23', 'asdasd', 'unpublished'),
(49, 'qwe', '2009-12-23', 'asdasd', 'unpublished'),
(50, 'asdasd', '2009-12-23', 'asd', 'unpublished'),
(51, 'asdasd', '2009-12-23', 'asd', 'unpublished'),
(52, 'Edmund', '2009-12-23', 'asdasd', 'unpublished'),
(53, 'Edmund', '2015-12-23', 'asdasd', 'unpublished'),
(54, 'Aku', '2009-12-23', 'asdasd', 'unpublished'),
(55, 'Dia', '2009-12-23', 'asdasd', 'unpublished'),
(56, 'Dia', '2009-12-23', 'asdasd', 'unpublished'),
(57, 'Edmund', '2009-12-23', 'asdasd', 'unpublished'),
(58, 'Edmund', '2009-02-12', 'asdasd', 'unpublished'),
(59, 'asd', '2009-12-23', 'asdasdasd', 'unpublished'),
(60, 'Edmund', '2009-12-23', 'asdasd', 'unpublished'),
(61, 'asdasd', '2009-12-23', 'asdasd', 'unpublished'),
(62, 'Edmund', '2009-12-23', 'asdasd', 'unpublished'),
(63, 'asd', '2007-03-12', 'asdasd', 'unpublished'),
(64, 'asd', '2007-02-12', 'asdasdasd', 'unpublished'),
(65, 'asd', '2009-12-23', 'asdasd', 'unpublished'),
(66, 'asd', '2009-12-23', 'asdasd', 'unpublished'),
(67, 'asd', '2014-12-02', 'asdasd', 'unpublished'),
(68, 'asd', '2009-12-12', 'wdqwe', 'unpublished'),
(69, 'Edmund', '2009-02-12', 'asdasd', 'unpublished'),
(70, 'asd', '2009-02-12', 'asdasd', 'unpublished'),
(71, 'asdasd', '2009-02-12', 'asdasd', 'unpublished'),
(72, 'Edmund', '2014-11-30', 'asdasdsa', 'unpublished'),
(73, 'Kopi', '2014-11-30', 'asdad', 'unpublished');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
`id` int(11) NOT NULL,
  `name` varchar(64) NOT NULL,
  `email` varchar(254) NOT NULL,
  `password` text NOT NULL,
  `roleId` int(11) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `name`, `email`, `password`, `roleId`) VALUES
(1, 'alvin_nt', 'alvin@coba.org', '5c74471e96b5a5448e95698c29085dab4b3baa168ec085f634b85f734e1d32fe65ec31eedc91fff09bde56708752db26d4d6f4fe40a0a316a500f35e98769803', 1),
(4, 'coba', 'coba@coba.coba', 'df51049df637fe2c6091f1613df9f9dca182e73420affb6056c13430a3dd10b9ca3eff93343b5d7d7a9108d5e7b61202b8a205a2b204bbb85dff4aacceb6753a', 1),
(5, 'bisa', 'bisa@bisa.bisa', 'dd87d36774ddc635b3d9a74806c5cb7bc50932dabdba3ac87737370eb2e750d905fd1d29f8cf84bdd8c8d14b23efeca62688df52dc1b49ae42f8e98accd45881', 3),
(6, 'anak_mama', 'anak@mama.com', '5df9d18c2be6ebc4e84801b71b065fcc63b721c097370ce0dc8ca5f716b36956266d06913514274b8bc9ff4d88ce29112f82802d838c0293b910944a25752dd4', 1),
(7, 'admin', 'admin@yahoo.com', '3c9909afec25354d551dae21590bb26e38d53f2173b8d3dc3eee4c047e7ab1c1eb8b85103e3be7ba613b31bb5c9c36214dc9f14a42fd7a2fdb84856bca5c44c2', 1),
(8, 'owner', 'owner@yahoo.com', '3c9909afec25354d551dae21590bb26e38d53f2173b8d3dc3eee4c047e7ab1c1eb8b85103e3be7ba613b31bb5c9c36214dc9f14a42fd7a2fdb84856bca5c44c2', 2),
(9, 'editor', 'editor@yahoo.com', '3c9909afec25354d551dae21590bb26e38d53f2173b8d3dc3eee4c047e7ab1c1eb8b85103e3be7ba613b31bb5c9c36214dc9f14a42fd7a2fdb84856bca5c44c2', 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `comment`
--
ALTER TABLE `comment`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `post`
--
ALTER TABLE `post`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `name` (`name`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `comment`
--
ALTER TABLE `comment`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `post`
--
ALTER TABLE `post`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=74;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=12;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
