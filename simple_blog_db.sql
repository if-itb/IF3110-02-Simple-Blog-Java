-- phpMyAdmin SQL Dump
-- version 4.1.6
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 26 Nov 2014 pada 13.30
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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=15 ;

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
(9, 'jeffhorus', 'jeffhorus19@gmail.com', 'laki laki laki', '2014-11-25 13:39:18', 80),
(10, 'adhike', 'adhike@gmail.com', 'mati aja lu', '2014-11-26 09:05:52', 80),
(11, 'adhike', 'adhike@gmail.com', 'mati aja lu', '2014-11-26 09:05:52', 80),
(12, 'andre', 'powerfull@andresusanto.com', 'mati aje lu', '2014-11-26 09:06:10', 80),
(13, 'jeffhorus', 'jeffhorus19@gmail.com', 'Rusuh melulu mahasiswa', '2014-11-26 11:06:54', 83),
(14, 'jeffhorus', 'jeffhorus19@gmail.com', 'Keren', '2014-11-26 11:21:39', 80);

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
(80, 'Sudah 202 Anggota DPR Teken Interpelasi, 6 dari PPP', '2014-11-25', 'Jakarta - Anggota DPR terus menggalang hak interpelasi untuk mempertanyakan kebijakan pemerintah atas kenaikan harga BBM. Hari ini, sudah 202 anggota DPR yang Teken interpelasi, sebanyak 6 di antaranya adalah PPP.\r\n\r\n"Sampai hari ini sudah 202 anggota naik dari kemarin yaitu 157 anggota," kata inisiator hak interpleasi asal Fraksi Golkar Misbakhun dalam jumpa pers di gedung DPR, Jakarta, Rabu (26/11/2014).\r\n\r\nMisbakhun merinci, 202 anggota itu berasal dari 5 fraksi yaitu Golkar, Gerindra, PKS, PAN, dan PPP. Dalam jumpa pers turut hadir inisiator lainnya Desmon (Gerindra), Ecky (PKS), Yandri (PAN), dan Aditya (Golkar). Mereka juga membagikan salinan tandatangan dari 202 anggota.\r\n\r\n"PPP ada 6 yang sudah teken beri dukungan, jadi 5 fraksi yang beri dukungan. Kami masih tunggu dukungan dari partai lain," ujarnya.\r\n\r\nInisiator asal Gerindra Desmon Mahesa mengatakan hak interplasi ini adalah hak anggota yang tidak ada arahan dari fraksi. Gerindra sudah 62 dari total 73 anggota.\r\n\r\n"Fraksi Gerindra lebih pada apa yang kami dengar di konstituen masing-masing, jadi fraksi Gerindra tidak atasnama fraksi dan partai tapi individual," ujar pimpinan komisi III itu,\r\n\r\n"Ini kegelisahan yang diterima buruh, rakyat, tukang bakso, ibu-ibu sebagai akibat kenaikan harga BBM. Maka kita gunakan hak konstitusi sekarang," imbuh insiator PKS Ecky Awal Muharram.', 1),
(82, 'Tinjau Kebakaran Hutan Riau, Jokowi: Kita Selesaikan Masalah Berulang Ini', '2014-10-23', 'Pekanbaru - Presiden Joko Widodo terbang langsung ke titik lokasi kebakaran hutan Riau di Kabupaten Meranti. Dia ingin melihat langsung inti masalah untuk kemudian menyelesaikannya secara cepat.\r\n\r\nJokowi ke lokasi kebakaran hutan itu menggunakan helikopter Super Puma milik TNI AU sekitar pukul 14.20 WIB, Rabu (26/11/2014). Turut mendampingi, Ibu Iriana, Menteri Kehutanan dan Lingkungan Hidup Siti Nurbaya Bakar dan Plt Gubernur Riau Arsyadjuliandi Rachman. \r\n\r\nHadir juga Danpaspampres Mayjen Andika Perkasa serta beberapa staf pengamanan yang berada di dua heli bell milik TNI AD. \r\n\r\nSebelum terbang, Jokowi sempat diwawancarai wartawan. Dia mengatakan, masalah kebakaran hutan di Riau selalu terulang, namun tak pernah ditemukan solusinya.\r\n\r\n"Di situ ada masalah yang setiap tahun berulang yaitu masalah lahan yang terbakar, itu mau kita selesaikan. Kalau masalah di lapangan kita ketahui, menyelesaikannya gampang," kata Jokowi.\r\n\r\nBagaimana dengan perusahaan yang membakar? \r\n\r\n"Itu nanti urusan menteri kehutanan. Tanya langsung ke dia, itu masalah teknis," tambahnya.', 1),
(83, 'Serbu Kampus saat Demo Rusuh di Makassar, 22 Polisi Jalani Sidang Disiplin', '2014-10-15', 'Makassar - Sebanyak 22 anggota Sabhara Polrestabes Makassar menjalani sidang disiplin di Mapolrestabes Makassar, Rabu (26/11/2014). Mereka ditengarai terlibat dalam penyerangan kampus Universitas Negeri Makassar dan pemukulan beberapa orang wartawan, Kamis (13/11) lalu.\r\n\r\nKepala Seksi Profesi dan Pengamanan Polrestabes Makassar Kompol Busran, pada wartawan menyebutkan bahwa ke-22 anggota Sabhara, diperiksa terkait kasus penyerbuan kampus UNM.\r\n"Dalam sidang kode etik ini, kami belum menemukan hasilnya karena mereka rata-rata tidak mengetahui insiden penyerbuan kampus dan pemukulan wartawan, mereka hanya mengejar pelaku pemanah Wakapolres yang masuk ke dalam kampus," ujar Kompol Busran.\r\n\r\nSalah satu anggota Sabhara yang diperiksa, Bripda Akbar mengaku tidak melakukan pemukulan dan perusakan fasilitas kampus UNM.\r\n\r\n"Aksi kami spontan, kami hanya melakukan penyisiran untuk mencari pelaku pembusuran Wakapolres," ujar Akbar singkat.\r\n\r\nSidang disiplin ini dimulai sekitar pukul 09.00 wita. Kemudian agenda ditunda pada pukul 12.00 wita dan hingga batas waktu yang belum ditentukan. \r\n\r\nDalam peristiwa penyerbuan masuk ke dalam kampus UNM, sebelumnya didahului insiden Wakapolrestabes Makassar AKBP Totok Lisdiarto yang terkena anak panah di bawah ketiak kanannya. Pasca insiden ini sontak puluhan anggota polisi menyerbu masuk ke dalam kampus. Sejumlah wartawan yang merekam aksi perusakan polisi mendapat tindak kekerasan\r\nberupa pemukulan dan pengambilan memory card, pada sejumlah wartawan Makassar, seperti Iqbal Lubis, fotografer Tempo, Ikrar Culleng, kamerawan Celebes TV, Waldy, kamerawan Metro TV dan Asep Ikhsan, fotografer koran Rakyat Sulsel.\r\n\r\nHampir semua anggota polisi yang disidang berusia muda, antara 19 tahun hingga 23 tahun.', 1),
(84, 'Tunda Penetapan UU MD3 Masuk Prolegnas, Fahri Hamzah: Ini Jalan Tengah', '2014-11-10', 'Jakarta - Wakil Ketua DPR Fahri Hamzah yang memimpin sidang paripurna mengetok palu untuk menunda revisi UU MD3 masuk ke Prolegnas 2014. Fahri menyebutnya sebagai suatu jalan tengah.\r\n\r\n"Kalau ditolak itu tidak bisa diajukan kembali dan proses pengajuannya jadi rumit. Jadi kita ambil jalan tengah, kita tunda," kata Fahri usai sidang paripurna di Gedung DPR, Senayan, Jakpus, Rabu (26/11/2014).\r\n\r\nFahri menuturkan bahwa pembahasan ulang revisi UU MD3 ini di Badan Legislasi akan diusahakan secepatnya. Selanjutnya, ia berharap tidak ada lagi perdebatan di paripurna.\r\n\r\n"Biar tidak ada lagi perdebatan di paripurna. (Perdebatannya) soal DPD, soal kekhawatiran di judicial review, dan lain-lain," ujar Wasekjen PKS ini.\r\n\r\nSaat sidang paripurna, anggota DPR mempertanyakan urgensi revisi UU MD3 dimasukkan ke dalam Prolegnas. DPD juga diminta untuk dilibatkan saat pembahasan.\r\n\r\nFraksi-fraksi di KIH sebenarnya sudah menyetujui agar revisi UU MD3 ini masuk ke Prolegnas dan menjadi usul inisiatif DPR. Namun, masih ada perdebatan sehingga Fahri kemudian mengetok palu untuk menunda penetapan.', 1);

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data untuk tabel `sb_users`
--

INSERT INTO `sb_users` (`user_id`, `username`, `password`, `email`, `type`) VALUES
(1, 'jeffhorus', 'pass', 'jeffhorus19@gmail.com', 3),
(4, 'owner', '', 'owner@gmail.com', 1),
(5, 'editor', 'pass', 'editor@gmail.com', 2),
(6, 'adminganteng', 'admin', 'admin@admin.com', 1),
(7, 'adhika', 'adhika', 'adhika@sigit.ram', 3),
(8, 'jeffhorus', 'pass', '', 4);

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
