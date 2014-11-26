-- phpMyAdmin SQL Dump
-- version 4.1.6
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 26 Nov 2014 pada 08.29
-- Versi Server: 5.6.16
-- PHP Version: 5.5.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `simple_blog_db`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `sb_comments`
--

CREATE TABLE IF NOT EXISTS `sb_comments` (
  `id_komentar` int(4) NOT NULL AUTO_INCREMENT COMMENT 'id komentar',
  `nama` varchar(14) NOT NULL,
  `email` varchar(30) NOT NULL,
  `komentar` text NOT NULL COMMENT 'konten dari komentar',
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'waktu submit komentar',
  `id_post` int(4) NOT NULL,
  PRIMARY KEY (`id_komentar`),
  KEY `id_post` (`id_post`),
  KEY `id_post_2` (`id_post`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Dumping data untuk tabel `sb_comments`
--

INSERT INTO `sb_comments` (`id_komentar`, `nama`, `email`, `komentar`, `timestamp`, `id_post`) VALUES
(1, 'jeffhorus', 'jeffhorus19@gmail.com', 'Coba bisa gak masuk', '2014-11-25 12:12:07', 80),
(3, 'jelink', 'je@link.com', 'sda akdlad klas', '2014-11-25 12:39:46', 80),
(4, 'jelink', 'je@link.com', 'jehasda', '2014-11-25 12:42:10', 80),
(5, 'jelink', 'je@link.com', 'ardi bege', '2014-11-25 12:42:29', 80),
(6, 'Jelink', 'jelink@bin.com', 'ajsd jkab kjsba kjbkjb', '2014-11-25 13:09:10', 84),
(7, 'adminganteng', 'admin@admin.com', 'asdjkada', '2014-11-25 13:31:02', 80),
(8, 'jeffhorus', 'jeffhorus19@gmail.com', 'semen', '2014-11-25 13:39:11', 80),
(9, 'jeffhorus', 'jeffhorus19@gmail.com', 'laki laki laki', '2014-11-25 13:39:18', 80);

-- --------------------------------------------------------

--
-- Struktur dari tabel `sb_posts`
--

CREATE TABLE IF NOT EXISTS `sb_posts` (
  `id_post` int(4) NOT NULL AUTO_INCREMENT COMMENT 'primary key id post',
  `judul` varchar(100) NOT NULL COMMENT 'judul post',
  `tanggal` date NOT NULL COMMENT 'tanggal publikasi post',
  `konten` text NOT NULL COMMENT 'isi post',
  `published` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_post`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=89 ;

--
-- Dumping data untuk tabel `sb_posts`
--

INSERT INTO `sb_posts` (`id_post`, `judul`, `tanggal`, `konten`, `published`) VALUES
(80, 'Malam minggu', '2014-11-25', 'paling asyik ngoding jsf, wkwkwk haha', 1),
(82, 'Pos Hari Terakhir', '2014-10-23', 'Semoga bisa', 1),
(83, 'Pos Tes', '2014-10-15', 'oh my god', 1),
(84, 'Pos 2014', '2014-11-10', 'Pos ini tahun 2014', 1),
(86, 'Ardi Berisik', '2014-11-26', 'Berisik lu\r\ndi di', 1),
(87, 'Pos Malem', '2014-11-26', 'Terhangat dari oven\r\n\r\niya oven\r\npasti', 1),
(88, 'Pos Malem', '2014-11-26', 'coba', 0);

-- --------------------------------------------------------

--
-- Struktur dari tabel `sb_users`
--

CREATE TABLE IF NOT EXISTS `sb_users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(14) NOT NULL,
  `password` varchar(10) NOT NULL,
  `email` varchar(30) NOT NULL,
  `type` int(11) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data untuk tabel `sb_users`
--

INSERT INTO `sb_users` (`user_id`, `username`, `password`, `email`, `type`) VALUES
(1, 'jeffhorus', 'pass', 'jeffhorus19@gmail.com', 3),
(4, 'owner', '', 'owner@gmail.com', 1),
(5, 'editor', 'pass', 'editor@gmail.com', 2),
(6, 'adminganteng', 'admin', 'admin@admin.com', 1);

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `sb_comments`
--
ALTER TABLE `sb_comments`
  ADD CONSTRAINT `sb_comments_ibfk_1` FOREIGN KEY (`id_post`) REFERENCES `sb_posts` (`id_post`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
