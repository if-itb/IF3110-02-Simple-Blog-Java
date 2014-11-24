-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Inang: 127.0.0.1
-- Waktu pembuatan: 24 Nov 2014 pada 07.23
-- Versi Server: 5.5.32
-- Versi PHP: 5.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Basis data: `simpleblog_withjava`
--
CREATE DATABASE IF NOT EXISTS `simpleblog_withjava` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `simpleblog_withjava`;

-- --------------------------------------------------------

--
-- Struktur dari tabel `komentar`
--

CREATE TABLE IF NOT EXISTS `komentar` (
  `komentar_id` int(11) NOT NULL AUTO_INCREMENT,
  `post_id` int(11) NOT NULL,
  `komentar_nama` varchar(50) NOT NULL,
  `komentar_email` varchar(50) NOT NULL,
  `komentar_konten` text NOT NULL,
  `komentar_tanggal` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`komentar_id`),
  KEY `komentar_post` (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `post`
--

CREATE TABLE IF NOT EXISTS `post` (
  `post_id` int(11) NOT NULL AUTO_INCREMENT,
  `tanggal` date NOT NULL,
  `konten` text NOT NULL,
  `user_id` int(11) NOT NULL,
  `status_publish` int(11) NOT NULL,
  `judul` varchar(50) NOT NULL,
  PRIMARY KEY (`post_id`),
  UNIQUE KEY `post_id` (`post_id`),
  KEY `user_posts` (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data untuk tabel `post`
--

INSERT INTO `post` (`post_id`, `tanggal`, `konten`, `user_id`, `status_publish`, `judul`) VALUES
(1, '2014-11-26', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Blanditiis aliquam minus consequuntur amet nulla eius, neque beatae, nostrum possimus, officiis eaque consectetur. Sequi sunt maiores dolore, illum quidem eos explicabo! Lorem ipsum dolor sit amet, consectetur adipisicing elit. Magnam consequuntur consequatur molestiae saepe sed, incidunt sunt inventore minima voluptatum adipisci hic, est ipsa iste. Nobis, aperiam provident quae. Reprehenderit, iste.\r\n\r\nLorem ipsum dolor sit amet, consectetur adipisicing elit. Asperiores animi tenetur nam delectus eveniet iste non culpa laborum provident minima numquam excepturi rem commodi, officia accusamus eos voluptates obcaecati. Possimus?', 1, 1, 'Artikel 1'),
(2, '2014-11-29', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Blanditiis aliquam minus consequuntur amet nulla eius, neque beatae, nostrum possimus, officiis eaque consectetur. Sequi sunt maiores dolore, illum quidem eos explicabo! Lorem ipsum dolor sit amet, consectetur adipisicing elit. Magnam consequuntur consequatur molestiae saepe sed, incidunt sunt inventore minima voluptatum adipisci hic, est ipsa iste. Nobis, aperiam provident quae. Reprehenderit, iste.\r\n\r\nLorem ipsum dolor sit amet, consectetur adipisicing elit. Asperiores animi tenetur nam delectus eveniet iste non culpa laborum provident minima numquam excepturi rem commodi, officia accusamus eos voluptates obcaecati. Possimus?', 1, 1, 'Artikel 2');

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `role` int(11) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`user_id`, `role`, `nama`, `password`, `email`) VALUES
(1, 3, 'adminganteng', 'adminganteng', ''),
(2, 1, 'ownerganteng', 'ownerganteng', '');

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `komentar`
--
ALTER TABLE `komentar`
  ADD CONSTRAINT `komentar_post` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`);

--
-- Ketidakleluasaan untuk tabel `post`
--
ALTER TABLE `post`
  ADD CONSTRAINT `user_posts` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
