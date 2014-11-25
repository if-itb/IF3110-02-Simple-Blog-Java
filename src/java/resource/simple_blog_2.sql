-- phpMyAdmin SQL Dump
-- version 4.1.6
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 25 Nov 2014 pada 10.46
-- Versi Server: 5.5.36
-- PHP Version: 5.4.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `simple_blog_2`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `entries`
--

CREATE TABLE IF NOT EXISTS `entries` (
  `PID` int(11) NOT NULL AUTO_INCREMENT,
  `JUDUL` text NOT NULL,
  `TANGGAL` date NOT NULL,
  `KONTEN` text NOT NULL,
  `PUBLISHED` tinyint(1) NOT NULL,
  `AUTHOR` text NOT NULL,
  PRIMARY KEY (`PID`),
  UNIQUE KEY `PID` (`PID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data untuk tabel `entries`
--

INSERT INTO `entries` (`PID`, `JUDUL`, `TANGGAL`, `KONTEN`, `PUBLISHED`, `AUTHOR`) VALUES
(1, 'Everything You Need to Know About Grunt', '2014-11-25', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.', 1, 'blah blah');

-- --------------------------------------------------------

--
-- Struktur dari tabel `komentar`
--

CREATE TABLE IF NOT EXISTS `komentar` (
  `PID` int(11) NOT NULL,
  `KID` int(11) NOT NULL AUTO_INCREMENT,
  `NAMA` varchar(50) NOT NULL,
  `EMAIL` varchar(50) NOT NULL,
  `TANGGAL` date NOT NULL,
  `KOMENTAR` text NOT NULL,
  PRIMARY KEY (`KID`),
  UNIQUE KEY `KID` (`KID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `USERNAME` varchar(20) NOT NULL,
  `PASSWORD` varchar(20) NOT NULL,
  `ROLE` varchar(10) NOT NULL,
  PRIMARY KEY (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
