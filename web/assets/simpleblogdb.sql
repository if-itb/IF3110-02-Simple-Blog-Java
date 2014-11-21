-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Oct 14, 2014 at 07:31 AM
-- Server version: 5.5.32
-- PHP Version: 5.4.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `simpleblogdb`
--
CREATE DATABASE IF NOT EXISTS `simpleblogdb` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `simpleblogdb`;

-- --------------------------------------------------------

--
-- Table structure for table `komentar`
--

CREATE TABLE IF NOT EXISTS `komentar` (
  `ID` int(11) NOT NULL,
  `Nama` varchar(30) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Komentar` longtext NOT NULL,
  `Waktu` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `ID` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `komentar`
--

INSERT INTO `komentar` (`ID`, `Nama`, `Email`, `Komentar`, `Waktu`) VALUES
(7, 'Rakhmatullah', 'rakhmatullahyoga@gmail.com', 'sundul gan!', '2014-10-14 04:17:31'),
(31, 'admin', 'rakhmatullahyoga@gmail.com', 'PERTAMAX GAN!!', '2014-10-14 05:24:07'),
(31, 'ADMIN', '13512053@std.stei.itb.ac.id', 'KEDUAX', '2014-10-14 05:24:43'),
(31, 'Rakhmatullah', 'rakhmatullahyoga@students.itb.ac.id', 'gile gan email ngasal gak bisa masuk!', '2014-10-14 05:25:57');

-- --------------------------------------------------------

--
-- Table structure for table `postingan`
--

CREATE TABLE IF NOT EXISTS `postingan` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `Judul` varchar(100) NOT NULL,
  `Tanggal` date NOT NULL,
  `Konten` longtext NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=32 ;

--
-- Dumping data for table `postingan`
--

INSERT INTO `postingan` (`ID`, `Judul`, `Tanggal`, `Konten`) VALUES
(7, 'Perangi Fakir Internet, Mark Zuckerberg ke Indonesia', '2014-10-10', 'Jakarta - Setelah menyambangi India, Mark Zuckerberg rencananya langsung blusukan ke Indonesia. Hal ini membuktikan bahwa Facebook makin serius menggarap Asia yang kebanyakan masih fakir internet.\r\n\r\nDi India, anak muda yang baru genap 30 tahun itu langsung menemui Perdana Menteri India setibanya di New Delhi. Dalam pertemuan itu diperoleh kesepakatan bahwa Facebook akan membantu membangun koneksi internet untuk desa-desa di India. \r\n\r\nâ€œKoneksi internet bukan cuma untuk orang kaya,â€ kata Mark untuk menekankan tujuannya dalam roadshow ini, seperti detikINET kutip IndiaTimes, Jumat (10/10/2014).\r\n\r\nZuck sendiri memang punya tujuan ingin menghadirkan internet kepada 5 miliar orang yang belum merasakannya. Desa-desa di India pun menjadi salah satu sasaran bagi rencananya itu. \r\n\r\nSelain itu, pengembangan konektivitas internet hingga ke desa juga sejalan dengan bisnis Facebook yang ingin merengkuh lebih banyak orang lagi untuk menggunakan layanannya.\r\n\r\nLalu bagaimana di Indonesia? Bocoran mengenai kehadiran Zuck ke Indonesia memang cukup santer terdengar belakangan ini. Apalagi dengan jumlah pengguna Facebook di Indonesia yang mencapai 65 juta pengguna, Indonesia memang menjadi pasar yang penting bagi Zuck.\r\n\r\nSejauh ini memang belum ada informasi apa yang akan diincar Zuck bila mampir ke Indonesia. Namun bila dikaitkan dengan beberapa waktu lalu dimana salah satu petinggi Facebook menemui Wakil Gubernur DKI Ahok, bisa jadi tujuan Zuck adalah untuk lebih jauh mempromosikan Jakarta lewat Facebook sekaligus merengkuh lebih banyak pengguna melalui kampanye tersebut.\r\nsumber : http://inet.detik.com/read/2014/10/10/155920/2715544/398/perangi-fakir-internet-mark-zuckerberg-ke-indonesia?i991101105'),
(11, 'validasi tanggal', '2014-10-01', 'kenapa gabisabisaaaaa..... :''('),
(31, 'Final test 1 (edited)', '2014-10-14', '1. validasi tanggal berhasil dengan asumsi ketika postingan blog diedit, tanggal harus menyesuaikan waktu saat edit post, artinya jika dipost hari ini dan akan diedit besok, maka field tanggal harus berisi minimal tanggal pada saat pengeditan post.\r\n2. berhasil melakukan edit post.');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `komentar`
--
ALTER TABLE `komentar`
  ADD CONSTRAINT `komentar_ibfk_1` FOREIGN KEY (`ID`) REFERENCES `postingan` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
