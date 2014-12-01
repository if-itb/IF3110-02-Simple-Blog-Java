-- phpMyAdmin SQL Dump
-- version 4.1.6
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1:3307
-- Generation Time: 30 Nov 2014 pada 07.24
-- Versi Server: 5.6.16
-- PHP Version: 5.5.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `blogjava`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `blogpost`
--

CREATE TABLE IF NOT EXISTS `blogpost` (
  `pid` int(5) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `posttitle` varchar(100) NOT NULL,
  `postcontent` text NOT NULL,
  `postdate` date NOT NULL,
  `published` tinyint(1) NOT NULL DEFAULT '0',
  `deleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`pid`),
  KEY `uid` (`uid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=53 ;

--
-- Dumping data untuk tabel `blogpost`
--

INSERT INTO `blogpost` (`pid`, `uid`, `posttitle`, `postcontent`, `postdate`, `published`, `deleted`) VALUES
(7, 1, 'Post Pertamaku', 'ieu post pertama kuring', '2014-11-25', 1, 0),
(8, 1, 'Post Keduaku', 'ieu post Kedua kuring', '2014-11-25', 1, 0),
(9, 1, 'Post ketigaku', 'isi Post ketiga', '2014-11-25', 0, 0),
(10, 2, 'post pertama nisa', 'nisa dian lalala', '2014-11-26', 1, 0),
(45, 1, 'Post ketigaku - Copy', 'isi Post ketiga', '2014-11-25', 0, 0),
(46, 1, 'ini post buat di hapus', 'sesuai judul', '2014-11-28', 1, 0),
(47, 3, 'ini postnya hayyu', 'tanggalnya sudah benar', '2014-11-29', 1, 0),
(48, 2, 'post pertama nisa - edited', 'nisa dian lilili', '2014-11-29', 1, 0),
(49, 3, 'hayyu luthfi', 'di hati yang ter dalam', '2014-11-29', 1, 0),
(50, 3, 'Post Keduaku', 'ieu post Kedua kuring', '2014-11-25', 1, 0),
(51, 3, 'Post Keduaku', 'ieu post Kedua kuring', '2014-11-25', 1, 0),
(52, 3, 'laksjlkajd', ';lkasja/dskjalkdj', '2014-11-28', 0, 0);

-- --------------------------------------------------------

--
-- Struktur dari tabel `komentar`
--

CREATE TABLE IF NOT EXISTS `komentar` (
  `cid` int(3) NOT NULL AUTO_INCREMENT,
  `pid` int(3) NOT NULL,
  `komentator` varchar(32) NOT NULL,
  `komen` text NOT NULL,
  `email` varchar(30) NOT NULL,
  `commentdate` date NOT NULL,
  PRIMARY KEY (`cid`),
  KEY `fk.pid` (`pid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=36 ;

--
-- Dumping data untuk tabel `komentar`
--

INSERT INTO `komentar` (`cid`, `pid`, `komentator`, `komen`, `email`, `commentdate`) VALUES
(3, 10, 'guest', 'nisa jelek', 'guest@guest.com', '2014-11-28'),
(10, 10, 'ucuplain', 'ucup ganteng loh', 'ucup@gmail.com', '2014-11-28'),
(11, 10, 'ucup-lain', 'ucup ganteng loh broo', 'ucup@gmail.com', '2014-11-28'),
(16, 10, 'lala', 'lilili', 'lali@lali.com', '2014-11-28'),
(17, 10, 'lala', 'lilili', 'lali@lali.com', '2014-11-28'),
(23, 10, 'lala', '', 'lala@lala.lala', '2014-11-28'),
(27, 10, 'guest', 'lalalalala', 'lala.asda', '2014-11-28'),
(28, 51, 'guest', 'llida.adasd', 'lala.aswd', '2014-11-28'),
(29, 10, 'bukan guest', 'harusnya saklah', 'bukan@guest.com', '2014-11-28'),
(30, 10, 'bukan guest', 'harusnya saklah', 'bukan@guest.com', '2014-11-28'),
(31, 10, 'guest syalala bum bunmb ubmbm', 'isinya panjang amat mas', 'panjang@email.com', '2014-11-29'),
(32, 10, 'guest syalala bum bunmb ubmbm', 'isinya panjang amat mas', 'panjang@email.com', '2014-11-29'),
(33, 10, 'guest syalala ', 'isinya panjang amat mas', 'panjang@email.com', '2014-11-29'),
(34, 10, 'guest syalala bum bumnmb', 'isinya panjang amat mas', 'panjang@email.com', '2014-11-29'),
(35, 10, 'guest syalala bum bumnmb', 'isinya panjang amat mas', 'panjang@email.com', '2014-11-29');

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `uid` int(3) NOT NULL AUTO_INCREMENT,
  `fullname` varchar(32) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(30) NOT NULL,
  `role` varchar(10) NOT NULL,
  `email` varchar(50) NOT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`uid`, `fullname`, `username`, `password`, `role`, `email`) VALUES
(1, 'Yusuf Rahmatullah', 'ucup', 'ucup', 'owner', 'ucup@gmail.com'),
(2, 'Nisa Dian Rachmadi', 'nisa', 'nisa', 'editor', 'nisa@gmail.com'),
(3, 'Hayyu Luthfi Hanifah', 'hayyu', 'hayyu', 'admin', 'hayyu@gmail.com'),
(4, 'dummy man', 'dummyman', 'dummywoman', 'owner', 'dummyman@simpleblog.com'),
(5, 'semarak cinta ', 'semarakcinta', 'cinta', 'editor', 'semarak@cinta.com');

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `blogpost`
--
ALTER TABLE `blogpost`
  ADD CONSTRAINT `fk.uid` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `komentar`
--
ALTER TABLE `komentar`
  ADD CONSTRAINT `fk.pid` FOREIGN KEY (`pid`) REFERENCES `blogpost` (`pid`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
