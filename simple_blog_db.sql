-- phpMyAdmin SQL Dump
-- version 4.1.6
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 01 Des 2014 pada 18.57
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
CREATE DATABASE IF NOT EXISTS `simple_blog_db` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `simple_blog_db`;

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=25 ;

--
-- Dumping data untuk tabel `sb_comments`
--

INSERT INTO `sb_comments` (`id_komentar`, `nama`, `email`, `komentar`, `timestamp`, `id_post`) VALUES
(6, 'Jelink', 'jelink@bin.com', 'ajsd jkab kjsba kjbkjb', '2014-11-25 13:09:10', 84),
(13, 'jeffhorus', 'jeffhorus19@gmail.com', 'Rusuh melulu mahasiswa', '2014-11-26 11:06:54', 83),
(15, 'adminganteng', 'admin@admin.com', 'bagus jok!', '2014-12-01 03:00:42', 82),
(16, 'jeffhorus', 'jeffhorus19@gmail.com', 'lucu ya politik di negeri ini', '2014-12-01 15:08:33', 84),
(17, 'Guest', 'jeje@jeje.com', 'lucu ya', '2014-12-01 15:14:51', 84),
(18, 'jelink', 'jeje@jeje.co', 'wah keren nih', '2014-12-01 15:15:15', 84),
(19, 'Guest', 'jelink@je.ling', 'masuk dong', '2014-12-01 15:36:02', 84),
(20, 'editor', 'editor@gmail.com', 'wah kasian balotelli', '2014-12-01 17:00:25', 88),
(21, 'jeffhorus', 'jeffhorus19@gmail.com', 'sangat sangat gak demokratis. . ckck', '2014-12-01 17:07:18', 87),
(22, 'Guest', 'gu@est.com', 'udah kayak jaman suharto', '2014-12-01 17:07:48', 87),
(23, 'owner', 'owner@gmail.com', 'you had one job. . .', '2014-12-01 17:08:04', 87),
(24, 'editor', 'editor@gmail.com', 'udah jangan pada ribut', '2014-12-01 17:08:24', 87);

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=90 ;

--
-- Dumping data untuk tabel `sb_posts`
--

INSERT INTO `sb_posts` (`id_post`, `judul`, `tanggal`, `konten`, `published`) VALUES
(82, 'Tinjau Kebakaran Hutan Riau, Jokowi: Kita Selesaikan Masalah Berulang Ini', '2014-10-23', 'Pekanbaru - Presiden Joko Widodo terbang langsung ke titik lokasi kebakaran hutan Riau di Kabupaten Meranti. Dia ingin melihat langsung inti masalah untuk kemudian menyelesaikannya secara cepat.\r\n\r\nJokowi ke lokasi kebakaran hutan itu menggunakan helikopter Super Puma milik TNI AU sekitar pukul 14.20 WIB, Rabu (26/11/2014). Turut mendampingi, Ibu Iriana, Menteri Kehutanan dan Lingkungan Hidup Siti Nurbaya Bakar dan Plt Gubernur Riau Arsyadjuliandi Rachman. \r\n\r\nHadir juga Danpaspampres Mayjen Andika Perkasa serta beberapa staf pengamanan yang berada di dua heli bell milik TNI AD. \r\n\r\nSebelum terbang, Jokowi sempat diwawancarai wartawan. Dia mengatakan, masalah kebakaran hutan di Riau selalu terulang, namun tak pernah ditemukan solusinya.\r\n\r\n"Di situ ada masalah yang setiap tahun berulang yaitu masalah lahan yang terbakar, itu mau kita selesaikan. Kalau masalah di lapangan kita ketahui, menyelesaikannya gampang," kata Jokowi.\r\n\r\nBagaimana dengan perusahaan yang membakar? \r\n\r\n"Itu nanti urusan menteri kehutanan. Tanya langsung ke dia, itu masalah teknis," tambahnya.', 1),
(83, 'Serbu Kampus saat Demo Rusuh di Makassar, 22 Polisi Jalani Sidang Disiplin', '2014-10-15', 'Makassar - Sebanyak 22 anggota Sabhara Polrestabes Makassar menjalani sidang disiplin di Mapolrestabes Makassar, Rabu (26/11/2014). Mereka ditengarai terlibat dalam penyerangan kampus Universitas Negeri Makassar dan pemukulan beberapa orang wartawan, Kamis (13/11) lalu.\r\n\r\nKepala Seksi Profesi dan Pengamanan Polrestabes Makassar Kompol Busran, pada wartawan menyebutkan bahwa ke-22 anggota Sabhara, diperiksa terkait kasus penyerbuan kampus UNM.\r\n"Dalam sidang kode etik ini, kami belum menemukan hasilnya karena mereka rata-rata tidak mengetahui insiden penyerbuan kampus dan pemukulan wartawan, mereka hanya mengejar pelaku pemanah Wakapolres yang masuk ke dalam kampus," ujar Kompol Busran.\r\n\r\nSalah satu anggota Sabhara yang diperiksa, Bripda Akbar mengaku tidak melakukan pemukulan dan perusakan fasilitas kampus UNM.\r\n\r\n"Aksi kami spontan, kami hanya melakukan penyisiran untuk mencari pelaku pembusuran Wakapolres," ujar Akbar singkat.\r\n\r\nSidang disiplin ini dimulai sekitar pukul 09.00 wita. Kemudian agenda ditunda pada pukul 12.00 wita dan hingga batas waktu yang belum ditentukan. \r\n\r\nDalam peristiwa penyerbuan masuk ke dalam kampus UNM, sebelumnya didahului insiden Wakapolrestabes Makassar AKBP Totok Lisdiarto yang terkena anak panah di bawah ketiak kanannya. Pasca insiden ini sontak puluhan anggota polisi menyerbu masuk ke dalam kampus. Sejumlah wartawan yang merekam aksi perusakan polisi mendapat tindak kekerasan\r\nberupa pemukulan dan pengambilan memory card, pada sejumlah wartawan Makassar, seperti Iqbal Lubis, fotografer Tempo, Ikrar Culleng, kamerawan Celebes TV, Waldy, kamerawan Metro TV dan Asep Ikhsan, fotografer koran Rakyat Sulsel.\r\n\r\nHampir semua anggota polisi yang disidang berusia muda, antara 19 tahun hingga 23 tahun.', 0),
(84, 'Tunda Penetapan UU MD3 Masuk Prolegnas, Fahri Hamzah "Ini Jalan Tengah"', '2014-12-02', 'Jakarta- Wakil Ketua DPR Fahri Hamzah yang memimpin sidang paripurna mengetok palu untuk menunda revisi UU MD3 masuk ke Prolegnas 2014. Fahri menyebutnya sebagai suatu jalan tengah.\r\n\r\n"Kalau ditolak itu tidak bisa diajukan kembali dan proses pengajuannya jadi rumit. Jadi kita ambil jalan tengah, kita tunda," kata Fahri usai sidang paripurna di Gedung DPR, Senayan, Jakpus, Rabu (26/11/2014).\r\n\r\nFahri menuturkan bahwa pembahasan ulang revisi UU MD3 ini di Badan Legislasi akan diusahakan secepatnya. Selanjutnya, ia berharap tidak ada lagi perdebatan di paripurna.\r\n\r\n"Biar tidak ada lagi perdebatan di paripurna. (Perdebatannya) soal DPD, soal kekhawatiran di judicial review, dan lain-lain," ujar Wasekjen PKS ini.\r\n\r\nSaat sidang paripurna, anggota DPR mempertanyakan urgensi revisi UU MD3 dimasukkan ke dalam Prolegnas. DPD juga diminta untuk dilibatkan saat pembahasan.\r\n\r\nFraksi-fraksi di KIH sebenarnya sudah menyetujui agar revisi UU MD3 ini masuk ke Prolegnas dan menjadi usul inisiatif DPR. Namun, masih ada perdebatan sehingga Fahri kemudian mengetok palu untuk menunda penetapan.', 1),
(86, 'Kambing', '2014-12-12', 'Muda Enak Sekali Kambing Muda Enak Sekali Kambing Muda Enak Sekali Kambing Muda Enak Sekali Kambing Muda Enak Sekali Kambing Muda Enak Sekali Kambing Muda Enak Sekali Kambing Muda Enak Sekali Kambing Muda Enak Sekali Kambing Muda Enak Sekali Kambing Muda Enak Sekali Kambing Muda Enak Sekali Kambing Muda Enak Sekali Kambing Muda Enak Sekali Kambing Muda Enak Sekali Kambing Muda Enak Sekali Kambing ', 2),
(87, 'Ketua Munas Golkar Pastikan Ical Menang Secara Aklamasi', '2014-12-01', 'Badung - Usai pengunduran diri Airlangga Hartarto, hanya ada calon tunggal untuk menjadi Ketua Umum Golkar yakni Aburizal Bakrie (Ical). Ketua Panitia Munas IX Golkar Ahmadi Noor Supit memastikan Ical menang secara aklamasi.\r\n\r\n"Dalam tata tertib kan sudah jelas, apabila hanya ada satu calon ketua umum saja maka dinyatakan terpilih secara aklamasi," ujar Supit di Westin Hotel, Kawasan Nusa Dua, Bali, Senin (1/12/2014).\r\n\r\nDia mengaku tak bisa menghindari keputusan politik yang diambil para caketum lain yang mundur. Meskipun sebelumnya ada politikus senior Golkar Zainal Bintang yang berniat daftarkan diri.\r\n\r\n"Itu nggak fair dong. Kalau dia bilang Munas ini tak legal lalu dia masuk juga kan tidak fair," imbuh Ketua Banggar DPR ini.\r\n\r\nSupit pun mengaku belum dijanjikan jabatan oleh calon mana pun. Walau tak menolak bila nantinya ada tawaran dari ketua umum terpilih.\r\n\r\n"Sebagai kader kan kita menuruti amanah yang diberikan oleh ketua," pungkas pria berkacamata itu.', 1),
(88, 'Lawan Leicester, Liverpool Masih Tanpa Balotelli', '2014-12-03', 'Liverpool - Liverpool tetap akan mengandalkan Rickie Lambert di lini depan saat menghadapi Leicester City midweek ini, setelah Mario Balotelli masih dalam tahap pemulihan cederanya.\r\n\r\nBalotelli sudah absen sejak mengalami cedera pangkah paha kala membela timnas Italia. Terakhir kali Balotelli main bersama Liverpool adalah saat kalah 1-2 dari Chelsea.\r\n\r\nDi tiga pertandingan terakhir The Reds, Lambert lah yang jadi pengganti Balotelli dan penyerang Inggris itu tampil cukup oke dengan bikin dua gol.\r\n\r\nLambert pun masih bakal jadi andalan Brendan Rodgers untuk mendulang gol, saat tim asal Merseyside itu menyambangi King Power Stadium untuk menghadapi Leicester, Rabu (3/12) dinihari WIB.\r\n\r\nBalotelli disebut masih belum fit dari cedera pangkal paha, sementara itu Mamadou Sakho juga tak akan dibawa mengingat ia baru saja pulih dari cedera paha.\r\n\r\n"Skuat di Leicester kurang lebih sama dengan saat mengalahkan Stoke 1-0 Sabtu lalu," ujar Rodgers seperti dikutip Soccerway.\r\n\r\n"Sakho sudah berlatih kemarin bersama tim utama, tapi dia belum siap untuk bermain. Dan Mario juga masih belum fit," sambung Rodgers.\r\n\r\nLiverpool akan membidik lagi tiga poin demi mendongrak posisi mereka di klasemen, karena saat ini masih ada di urutan ke-11 dengan 17 poin.', 1),
(89, 'Jadi Juru Kunci, Situasi yang Tak Terbayangkan oleh Dortmund', '2014-12-06', 'Frankfurt - Kekalahan dari Eintracht Frankfurt membuat Borussia Dortmund terbenam di dasar klasemen. Mimpi buruk Die Borussien di Bundesliga musim ini tampaknya masih jauh dari kata berakhir.\r\n\r\nKemenangan amat dibutuhkan Dortmund menyusul laju buruk di liga. Namun, saat melawat ke Commerzbank-Arena, Senin (1/12/2014) dinihari WIB, Dortmund dipaksa pulang dengan kekalahan 0-2.\r\n\r\nIni adalah kekalahan kedelapan Dortmund di Bundesliga musim ini. Hasil itu sekaligus membuat Dortmund terperosok ke dasar klasemen atau posisi ke-18 dengan raihan 11 poin dari 13 pertandingan--terpisah 22 poin dari Bayern Munich yang ada di puncak klasemen.\r\n\r\nDalam catatan situs resmi Dortmund, ini adalah kali pertama mereka menjadi juru kunci sejak pekan kedua musim 2007/2008. Sementara menurut Opta, tanpa memasukkan pekan pertama dan pekan kedua, ini adalah kali pertama dalam 29 tahun Dortmund ada di posisi terbawah Bundesliga.\r\n\r\nTak sedikit yang terheran-heran dengan krisis yang tengah dihadapi Dortmund saat ini--termasuk para pemainnya sendiri. Menilik pencapaian Dortmund dalam beberapa musim terakhir, menjadi juru kunci di Bundesliga tentu jauh dari bayangan.\r\n\r\nDalam empat musim terakhir, Dortmund selalu bersaing di papan atas Bundesliga. Usai jadi juara di musim 2010/2011 dan 2011/2012, tim arahan Juergen Klopp itu kemudian finis sebagai runner-up di dua musim berikutnya.\r\n\r\nApalagi laju Dortmund di kompetisi Eropa juga tak buruk. Musim ini, kendati jeblok di liga, Dortmund sudah menggenggam tiket ke babak 16 besar Liga Champions.', 0);

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=25 ;

--
-- Dumping data untuk tabel `sb_users`
--

INSERT INTO `sb_users` (`user_id`, `username`, `password`, `email`, `type`) VALUES
(1, 'jeffhorus', 'pass', 'jeffhorus19@gmail.com', 3),
(4, 'owner', 'pass', 'owner@gmail.com', 1),
(5, 'editor', 'pass', 'editor@gmail.com', 2),
(6, 'adminganteng', '', 'admin@admin.com', 3),
(7, 'adhika', 'adhika', 'adhika@sigit.ram', 3),
(9, 'jelink19', 'pass', 'jeje@jeje.com', 2),
(10, 'jeje', 'pass', 'pass@pass.com', 3),
(11, 'owner2', 'pass', '13131@ajsbfajf.com', 1),
(14, 'haha', 'haha', 'hahaha', 1),
(20, 'jelo', 'wewa', 'jelo@jelo.com', 3),
(22, 'jemaat', 'jejeje', 'jelink@je.com', 1),
(24, 'kiki', 'kaka', 'koko@keke.kuku', 1);

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
