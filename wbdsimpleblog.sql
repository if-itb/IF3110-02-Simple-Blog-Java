-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 01, 2014 at 11:50 AM
-- Server version: 5.5.32
-- PHP Version: 5.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `wbdsimpleblog`
--
CREATE DATABASE IF NOT EXISTS `wbdsimpleblog` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `wbdsimpleblog`;

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
  `comid` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NOT NULL,
  `tglkomen` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `nama` varchar(12) NOT NULL,
  `email` varchar(25) NOT NULL,
  `komen` text NOT NULL,
  PRIMARY KEY (`comid`),
  KEY `pid` (`pid`),
  KEY `pid_2` (`pid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=93 ;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`comid`, `pid`, `tglkomen`, `nama`, `email`, `komen`) VALUES
(1, 4, '2014-10-13 11:04:42', 'Ardi', 'ardi@lalala.com', 'yeyeyeyeyeye lalala yeyeye'),
(2, 4, '2014-10-13 11:04:50', 'Ardi', 'ardi@lalala.com', 'ini yang kedua yeyeyeyeyeye lalala yeyeye'),
(3, 12, '2014-10-13 11:05:44', 'Breki', 'breki@gmail.com', 'ini breki ngekomen post dengan id 12'),
(5, 22, '2014-10-14 02:12:59', 'Ardi', 'jejeje@host.com', 'lalalayeyeye'),
(6, 4, '2014-10-14 02:17:10', 'Joni', 'joni@jono.org', 'komen lagi dong'),
(7, 22, '2014-10-14 05:24:21', 'Joni', 'budi@lalala.org', 'semoga ini komen masuk'),
(10, 22, '2014-10-14 05:36:10', 'jonijonigogo', 'email@isp.com', 'eaeaeaea'),
(12, 22, '2014-10-14 05:48:00', 'maheeeeeee', 'lalalalaala@lalala.org.id', 'ea'),
(13, 24, '2014-10-14 14:35:13', 'mahe', 'ardi@gmail.com', 'ulalalalalalalala'),
(15, 24, '2014-10-14 14:45:54', 'Ardi', 'budi@lalala.org', 'ini jadi valid'),
(16, 24, '2014-10-14 14:48:14', 'Joni', 'ardi@gmail.com', 'lorem ipsum'),
(27, 12, '2014-10-14 15:29:17', 'Breki', 'email@isp.com', 'breki komen lagi'),
(28, 12, '2014-10-14 15:32:58', 'hambele', 'ham@bele.museum', 'ini komentar lagi'),
(29, 24, '2014-10-15 06:01:21', 'inda', 'indaaja@gmail.com', 'ini komen'),
(49, 36, '2014-11-26 09:10:24', 'ardiwii', 'ardi@gmail.com', 'ini komentar dari saya'),
(50, 37, '2014-11-26 09:12:49', 'saya', 'email@isp.org', 'lalalala ini komen bakal kemana'),
(51, 36, '2014-11-26 09:14:23', 'ardiwii', 'email@isp.org', 'mamamamamama'),
(52, 36, '2014-11-26 09:37:16', 'joni', 'breki@gmail.com', 'lalala yeyeyeye ulawawalalala'),
(53, 36, '2014-11-26 09:38:30', 'broni', 'broni@mama.org', 'joni jelek'),
(54, 32, '2014-11-26 09:40:47', 'komentor', 'komen@tor.com', 'jrengjengjengjeng jreng jreeengg'),
(55, 30, '2014-11-26 09:48:04', 'manoman', 'mano@man.mon', 'sasasasasa lalalala'),
(58, 32, '2014-11-26 10:02:06', 'baru', 'ini@baru.org', 'inni baru loh'),
(59, 29, '2014-11-26 10:03:21', 'username', 'email@isp.org', 'cie baru nih ye'),
(60, 37, '2014-11-26 10:26:07', 'username', 'email@isp.org', 'lalalalala ini komen lagi yeyeye'),
(61, 32, '2014-11-26 10:30:41', 'username', 'email@isp.org', 'komentar nih'),
(62, 38, '2014-11-26 10:32:26', 'username', 'email@isp.org', 'wah ajax keren ya'),
(63, 38, '2014-11-26 10:32:52', 'pemain', 'ajax@amsterdam.museum', 'iya dong ini keren gitu banget'),
(64, 26, '2014-11-26 15:21:56', 'username', 'email@isp.org', 'lalalala'),
(65, 38, '2014-11-26 15:28:24', 'username', 'email@jreng.jeng', '"hoahahaha" ''jiaaja'' <''eaeaea''>'),
(66, 38, '2014-11-26 15:28:31', '"username"', 'email@jreng.jeng', '"hoahahaha" ''jiaaja'' <''eaeaea''>'),
(67, 37, '2014-11-30 11:49:28', 'username', 'email', 'tes ngekomen dong'),
(68, 37, '2014-11-30 11:49:28', 'username', 'email', 'tes ngekomen dong'),
(69, 41, '2014-11-30 11:50:31', 'username', 'email', 'tes tes'),
(70, 41, '2014-11-30 11:50:49', 'username', 'email', 'tes tes yg kedua kali'),
(71, 41, '2014-12-01 02:10:09', 'hoho', 'ho@ho', 'yeaueay'),
(78, 40, '2014-12-01 04:34:48', 'bagas', 'baspram@gmail.com', 'jrengjengejgengejgengn'),
(82, 40, '2014-12-01 05:08:06', 'bagas', 'baspram@gmail.com', 'ini komentar'),
(83, 40, '2014-12-01 05:10:04', 'bagas', 'baspram@mamama', 'ini komentar'),
(84, 40, '2014-12-01 05:11:27', 'bagas', 'baspram@gmail.com', 'eaeaea'),
(85, 40, '2014-12-01 05:22:07', 'bagas', 'ardi@gmail', 'lalalala'),
(86, 38, '2014-12-01 05:22:53', 'bagas', 'baspram@gmail', 'dadadadadada'),
(87, 38, '2014-12-01 05:23:14', 'bagas', 'baspram@gmail.web.id', 'dadadadadada'),
(88, 41, '2014-12-01 05:26:27', 'admin', 'admin@eaea.loco', 'ini komentar'),
(89, 41, '2014-12-01 05:27:16', 'admin', 'admin@stei.itb.ac.id', 'ini komentar'),
(90, 43, '2014-12-01 08:45:54', 'editor', 'editor@blog.com', 'gue bisa komen lho. . .'),
(91, 44, '2014-12-01 08:50:39', 'admin', 'admin@simple.com', 'hbsjkdhbjshdg'),
(92, 39, '2014-12-01 09:45:09', 'admin', 'admin@simpleblog.com', 'dikomentarin');

-- --------------------------------------------------------

--
-- Table structure for table `post`
--

CREATE TABLE IF NOT EXISTS `post` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `judul` varchar(40) CHARACTER SET latin1 COLLATE latin1_general_cs NOT NULL,
  `tanggal` varchar(15) NOT NULL,
  `konten` text CHARACTER SET latin1 COLLATE latin1_general_cs NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=45 ;

--
-- Dumping data for table `post`
--

INSERT INTO `post` (`pid`, `judul`, `tanggal`, `konten`, `status`) VALUES
(4, 'asik keempat kali ini udah diedit', '2014-10-14', 'dolor sit amet ini adalah post keempat yang udah diedit dan divalidasi jrengjengng', 1),
(12, 'udah post kelima nih padahal ngetik', '2014-10-15', 'lorem ipsum edited post keenam', 1),
(22, 'tes tes posting', '2014-10-18', 'posting tes ini buat dikomen', 1),
(24, 'Post dari masa lalu', '2014-11-30', 'ini adalah post dari masa depan, sekarang bumi sedang dalam bahaya\r\nadsasdasdasfafss', 1),
(26, 'testestes java', '2014-11-25', 'ngetes java <tes ada giniannya> sama ini {} <>/\\'';l[pp[]', 1),
(27, 'judulbaru', '2014-10-20', 'lalalalalainkonten', 1),
(28, 'ini judul baru semoga masuk', '2014-11-26', 'kalva lsjflajkj falksj flkasfkalksflalk fsjaklfsjalsk  flkalk lfkajkfjalkskfaj ak', 1),
(29, 'judul baru nih ye', '2014-11-27', 'ini post baru yeyeyeyeye lalalalalala', 1),
(30, 'asik keempat kali ini udah diedit', '2014-10-14', 'dolor sit amet ini adalah post keempat yang udah diedit dan divalidasi, dan ini diedit lagi yehah asdlkasllalalalalala aaaaaaaa aaaaaaaaaaaaaaaaaaa sdkjdklsjdajsdl aaaaaaaaaaaaaaaaaaa sdkjdklsjdajsdl', 1),
(31, 'ini post baru yeyeyeye', '2014-08-21', 'ini post baru eaeaeaeaea cie valentinan', 2),
(32, 'judul baru ceritanya', '2020-12-19', 'jrengjengjengjeng', 2),
(33, 'makanan', '2002-10-09', 'makanan ada banyak eaeaeaea ini diedit loh', 1),
(34, 'nambah lagi', '2009-12-10', 'ini dia kok tadi ga bisa diubah sih hadeh', 2),
(35, 'gila lingga gampang', '2020-10-21', 'gila lingga gila katanya lingga gampang nih cuy cuy bro sis yeah', 0),
(36, 'gambarnya aset', '2013-11-28', 'kata bagas ini gambarnya aset, terima kasih ohu :)', 2),
(37, 'ini bakal kemana', '2015-01-10', 'yuk kita kemana2 dengan post ini "ada kutip" ''ada petik'' <eaea>', 1),
(38, 'Ajax', '2014-11-28', 'Ajax: A Brief Introduction\r\n\r\n    AJAX stands for Asynchronous JavaScript And Xml.\r\n\r\n    Ajax is a technique to use HTTPXMLObject of JavaScript to send data to server and receive data from server asynchronously.\r\n\r\n    So using Ajax technique, javascript code exchanges data with server, updates parts of web page without reloading the whole page.\r\n\r\nJSF provides execellent support for making ajax call. It provides f:ajax tag to handle ajax calls. ', 1),
(39, 'ini post baru buat di publish', '2014-11-28', 'ini dia jengjengjeng lalala ini abis duedit', 0),
(40, 'judul', '2014-11-26', 'mamamamamama', 1),
(41, 'tespostingsusah', '2014-12-10', '"eaea" ea'' /dasda ''ddkkd''', 1),
(42, 'posting terbaru banget', '2014-09-12', 'lagi nontonin latian stema nih', 2),
(43, 'post tanggalnya salah', '2014-12-02', 'eaeaea semoga ga masuk', 2),
(44, 'Post baru Test', '2014-12-02', 'eh salah bener\r\n', 1);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `nama` varchar(35) NOT NULL,
  `email` varchar(40) NOT NULL,
  `tipe` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `nama`, `email`, `tipe`) VALUES
(1, 'admin', 'passadmin', 'Joni Admin', 'admin@simpleblog.com', 'Admin'),
(2, 'editor', 'passeditor', 'Adi Editor', 'editor@blog.com', 'Editor'),
(3, 'owner', 'passowner', 'Erika Owner', 'erika@gmail.com', 'Owner'),
(4, 'ardi', 'lalala', 'Ardi W', 'ardiwii@gmail.com', 'Admin'),
(5, 'bagas', 'bagasaja', 'Bagaskara Pramudita', 'baspram@gmail.com', 'Editor');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `post` (`pid`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
