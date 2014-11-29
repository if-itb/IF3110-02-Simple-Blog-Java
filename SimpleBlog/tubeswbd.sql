-- phpMyAdmin SQL Dump
-- version 4.1.6
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 30, 2014 at 12:29 AM
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
-- Table structure for table `post`
--

CREATE TABLE IF NOT EXISTS `post` (
  `id_post` int(11) NOT NULL AUTO_INCREMENT,
  `judul` varchar(255) NOT NULL,
  `tanggal` varchar(255) NOT NULL,
  `konten` text NOT NULL,
  `status` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_post`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=39 ;

--
-- Dumping data for table `post`
--

INSERT INTO `post` (`id_post`, `judul`, `tanggal`, `konten`, `status`) VALUES
(3, 'Google Doodle Ikut Populerkan Batik Indonesia revisi', '15-10-2014', 'JAKARTA - Tampilan Google Doodle hari ini memperlihatkan tema busana batik. Google Doodle menampilkan gambar yang menunjukkan variasi motif batik yang dikenakan oleh anak muda maupun orang dewasa.\r\n\r\nBila Anda mengklik gambar Doodle, Anda akan dibawa menuju hasil pencarian Batik Indonesia. Doodle menampilkan tema batik untuk memperingati Hari Batik Nasional yang jatuh pada hari ini atau 2 Oktober 2014.\r\n\r\nBatik ditetapkan sebagai Warisan Kemanusiaan untuk Budaya Lisan dan Nonbendawi (Masterpieces of the Oral and Intangible Heritage of Humanity) pada 2 Oktober 2009 oleh UNESCO. \r\n\r\nWikipedia menerangkan, Kata "batik" berasal dari gabungan dua kata bahasa Jawa, yakni "amba" yang bermakna "menulis" dan "titik" yang bermakna "titik". Batik merupakan kerajinan yang kental dengan seni dan menjadi bagian dari budaya Indonesia (khususnya Jawa).\r\n\r\nPerempuan-perempuan Jawa pada masa lampau menjadikan keterampilan mereka dalam membatik sebagai mata pencaharian. Batik merupakan warisan nenek moyang Indonesia yang sampai saat ini masih ada.	(ahl)', 1),
(6, 'Sistem Transfer Uang Melalui Kicauan di Twitter revisi', '14-10-2013', 'Ilustrasi Twitter yang mampu mentransfer uang melalui kicauan (foto: neowin)\r\nSistem Transfer Uang Melalui Kicauan di Twitter\r\nPRANCIS. Saat ini, media jejaring sosial bukan lagi hanya bisa difungsikan untuk melontarkan tentang status maupun keberadaan seseorang disuatu tempat. Melebihi ekspetasinya, ternyata situs jejaring sosial ini bisa dimanfaatkan untuk pengiriman uang, seperti halnya Twitter.\r\n\r\nSalah satu Negara yang sedang mengembangkan kemampuan dari situs microblogging, Twitter, tersebut adalah Prancis. Melalui kerjasamanya dengan salah satu bank terbesar di Negara tesebut, Groupe BPCE, kabarnya pengguna Twitter di Negara Prancis bisa mengirim uang hanya dengan kicauan Twit keakun seseorang.\r\n\r\nDilansir neowin, Selasa (14/10/2014) Groupe BPCE dan Twitter dikabarkan sedang bersinergi untuk menjalankan sebuah program yang akan dikenalkan dengan nama S-money, yang dapat dimanfaatkan pengguna Twitter untuk melakukan transfer uang kepada pengguna lainnya.\r\n\r\nNamun sayangnya, belum ada informasi lebih lanjut mengenai rincian tentang system yang akan dipakai dalam S-money ini. Bahkan, mengenai pengirim dan penerima apakah hanya bisa digunakan oleh satu nasabah bank tersebut saja, atau bahkan kicauan tersebut disertakan dengan nomor rekening si penerima langsung. ', 1),
(9, 'dfsfssdsadas', '12-12-2014', 'sfsdfdsfsjhshdasaja', 1),
(35, '', '12-12-2012', '', 0),
(38, 'dfsfdsd', '', 'sdfsdfdsf', 0);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(20) NOT NULL,
  `role` varchar(6) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  KEY `id` (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `role`) VALUES
(1, 'icha', 'icha', 'owner'),
(2, 'ichax', 'ichax', 'editor'),
(3, 'ichas', 'ichas', 'admin');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
