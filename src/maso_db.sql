-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 28, 2026 at 02:47 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `maso_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `matches`
--

CREATE TABLE `matches` (
  `id` int(11) NOT NULL,
  `user1_id` int(11) NOT NULL,
  `user2_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `messages`
--

CREATE TABLE `messages` (
  `id` int(11) NOT NULL,
  `sender_id` int(11) NOT NULL,
  `receiver_id` int(11) NOT NULL,
  `pesan` text NOT NULL,
  `waktu` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `swipes`
--

CREATE TABLE `swipes` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `target_id` int(11) NOT NULL,
  `interest` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `domisili` varchar(100) DEFAULT NULL,
  `umur` int(11) DEFAULT NULL,
  `kelamin` enum('Laki-laki','Perempuan') DEFAULT NULL,
  `hobi` text DEFAULT NULL,
  `tujuan` enum('friends','fwb','fun date','menikah') DEFAULT NULL,
  `deskripsi` text DEFAULT NULL,
  `no_telepon` varchar(20) DEFAULT NULL,
  `foto_profil` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `nama`, `username`, `password`, `domisili`, `umur`, `kelamin`, `hobi`, `tujuan`, `deskripsi`, `no_telepon`, `foto_profil`) VALUES
(1, 'Maya Cahya', 'maya_id', '2$EjT3oB19', 'Jakarta', 31, 'Perempuan', 'Bela Diri, Bersepeda', 'menikah', 'Orang bilang aku seru kalau udah kenal. Nama aku Maya, suka banget Bela Diri. Also into Bersepeda. Let\'s talk!', '081111111001', 'user2.jpeg'),
(2, 'Tari Surya', 'tari_12', 'X0d8LPsTZo', 'Bandung', 24, 'Perempuan', 'Bersepeda, Bela Diri, Ngopi', 'menikah', 'Chill, seru, dan nggak bosen diajak ngobrol — itu aku, Tari! Hobby: Bersepeda & Bela Diri. DM open~', '081111111002', 'user2.jpeg'),
(3, 'Bagas Setiawan', 'xbagas770', '@hZ9US!DUf', 'Manado', 35, 'Laki-laki', 'Gaming', 'fwb', 'Hai, aku Bagas! Seorang pria yang gemar Gaming. Lagi cari teman baru yang seru diajak ngobrol soal Gaming.', '081111111003', 'user1.jpeg'),
(4, 'Rina Prasetyo', 'rina_3', '#syk0Hrs0!', 'Padang', 24, 'Perempuan', 'Nonton Film', 'fun date', 'Halo semuanya! Aku Rina dari Padang. Kalau nggak lagi Nonton Film, pasti lagi Nonton Film. Suka hal-hal baru!', '081111111004', 'user2.jpeg'),
(5, 'Rizky Saputra', 'rizky_3', 'yxC6G6G4gj', 'Banjarmasin', 20, 'Laki-laki', 'Musik', 'friends', 'Aku Rizky, seseorang yang percaya bahwa hidup harus dijalani dengan Musik. Juga suka Musik di waktu senggang.', '081111111005', 'user1.jpeg'),
(6, 'Kevin Prasetyo', 'kevin_7', 'CcSgac1KpS', 'Padang', 29, 'Laki-laki', 'Musik, Nonton Film, Skateboard', 'fun date', 'Halo semuanya! Aku Kevin dari Padang. Kalau nggak lagi Musik, pasti lagi Nonton Film. Suka hal-hal baru!', '081111111006', 'user1.jpeg'),
(7, 'Desi Kurniawan', 'desi8', 'Cc8yFQ87gc', 'Palembang', 21, 'Perempuan', 'DIY Craft', 'fwb', 'Orang bilang aku seru kalau udah kenal. Nama aku Desi, suka banget DIY Craft. Also into DIY Craft. Let\'s talk!', '081111111007', 'user2.jpeg'),
(8, 'Dimas Permana', 'xdimas975', 'jOtUSkH$MV', 'Malang', 31, 'Laki-laki', 'Melukis, Menulis, Membaca', 'fun date', 'Halo semuanya! Aku Dimas dari Malang. Kalau nggak lagi Melukis, pasti lagi Menulis. Suka hal-hal baru!', '081111111008', 'user1.jpeg'),
(9, 'Hani Kurniawan', 'xhani626', 'DOJmzkQyig', 'Bogor', 31, 'Perempuan', 'Berkebun, Yoga, Nonton Film', 'friends', 'Simple person, Hani namaku. Hobi Berkebun, Yoga, dan kadang Nonton Film kalau mood lagi bagus. Hit me up!', '081111111009', 'user2.jpeg'),
(10, 'Maya Kurniawan', 'xmaya683', 'qn8!KyE!5s', 'Balikpapan', 18, 'Perempuan', 'Olahraga, Membaca, Cosplay', 'fun date', 'Halo! Aku Maya, 18 tahun dari Balikpapan. Suka Olahraga dan Membaca, orangnya easy-going dan open-minded. Yuk kenalan!', '081111111010', 'user2.jpeg'),
(11, 'Dewi Putri', 'dewi93', 'Fheh4MW61Y', 'Banjarmasin', 32, 'Perempuan', 'Memasak', 'fun date', 'Orang bilang aku seru kalau udah kenal. Nama aku Dewi, suka banget Memasak. Also into Memasak. Let\'s talk!', '081111111011', 'user2.jpeg'),
(12, 'Raihan Pratama', 'raihan_3', 'OnKrxE57NO', 'Padang', 23, 'Laki-laki', 'Ngopi, Nonton Film, Vlogging', 'fwb', 'Hey there! I\'m Raihan, 23 y/o based in Padang. Passionate about Ngopi & Nonton Film. Let\'s vibe!', '081111111012', 'user1.jpeg'),
(13, 'Agus Saputra', 'agus_74', 'l#@uCzb#8C', 'Bogor', 24, 'Laki-laki', 'Bersepeda, Ngopi', 'menikah', 'Hey there! I\'m Agus, 24 y/o based in Bogor. Passionate about Bersepeda & Ngopi. Let\'s vibe!', '081111111013', 'user1.jpeg'),
(14, 'Rafi Hakim', 'xrafi644', 'sHh5jTmSHL', 'Padang', 21, 'Laki-laki', 'Olahraga, Ngopi, Yoga', 'friends', 'Halo! Aku Rafi, 21 tahun dari Padang. Suka Olahraga dan Ngopi, orangnya easy-going dan open-minded. Yuk kenalan!', '081111111014', 'user1.jpeg'),
(15, 'Galih Setiawan', 'galih92', 'p5aI1eQMO%', 'Bogor', 33, 'Laki-laki', 'Yoga', 'fun date', 'Orang bilang aku seru kalau udah kenal. Nama aku Galih, suka banget Yoga. Also into Yoga. Let\'s talk!', '081111111015', 'user1.jpeg'),
(16, 'Putri Prasetyo', 'putri02', 'CKZS1OqLxp', 'Padang', 23, 'Perempuan', 'Menulis', 'menikah', 'Hai, aku Putri! Seorang wanita yang gemar Menulis. Lagi cari teman baru yang seru diajak ngobrol soal Menulis.', '081111111016', 'user2.jpeg'),
(17, 'Gilang Nugroho', 'gilang01', 'bCPWlG#757', 'Semarang', 24, 'Laki-laki', 'Skateboard', 'fwb', 'Hey there! I\'m Gilang, 24 y/o based in Semarang. Passionate about Skateboard & Skateboard. Let\'s vibe!', '081111111017', 'user1.jpeg'),
(18, 'Hafiz Putri', 'hafiz03', 'UuX9zX72th', 'Yogyakarta', 22, 'Laki-laki', 'Menulis, Olahraga', 'fun date', 'Chill, seru, dan nggak bosen diajak ngobrol — itu aku, Hafiz! Hobby: Menulis & Olahraga. DM open~', '081111111018', 'user1.jpeg'),
(19, 'Tari Nugroho', 'xtari942', 'Ba$5B1ZWxo', 'Malang', 23, 'Perempuan', 'Ngopi, Membaca', 'menikah', 'Orang bilang aku seru kalau udah kenal. Nama aku Tari, suka banget Ngopi. Also into Membaca. Let\'s talk!', '081111111019', 'user2.jpeg'),
(20, 'Putri Permana', 'putri_5', '3%f7O#VMD2', 'Bandung', 19, 'Perempuan', 'Memasak, Gaming, Renang', 'fun date', 'Hey there! I\'m Putri, 19 y/o based in Bandung. Passionate about Memasak & Gaming. Let\'s vibe!', '081111111020', 'user2.jpeg'),
(21, 'Ulfa Ramadhan', 'ulfa3', 'VEPqrO0yM9', 'Bekasi', 29, 'Perempuan', 'Berkebun, Membaca', 'menikah', 'Hey there! I\'m Ulfa, 29 y/o based in Bekasi. Passionate about Berkebun & Membaca. Let\'s vibe!', '081111111021', 'user2.jpeg'),
(22, 'Nadia Wijaya', 'nadia81', '#ZI4fZM9qN', 'Balikpapan', 32, 'Perempuan', 'Mendaki', 'fwb', 'Simple person, Nadia namaku. Hobi Mendaki, Mendaki, dan kadang Mendaki kalau mood lagi bagus. Hit me up!', '081111111022', 'user2.jpeg'),
(23, 'Salma Pratama', 'xsalma459', 'HsabEiRa42', 'Surabaya', 22, 'Perempuan', 'Memancing', 'menikah', 'Halo semuanya! Aku Salma dari Surabaya. Kalau nggak lagi Memancing, pasti lagi Memancing. Suka hal-hal baru!', '081111111023', 'user2.jpeg'),
(24, 'Maya Saputra', 'maya04', 'V6n2KETQg%', 'Manado', 21, 'Perempuan', 'Mendaki, Badminton', 'fwb', 'Halo semuanya! Aku Maya dari Manado. Kalau nggak lagi Mendaki, pasti lagi Badminton. Suka hal-hal baru!', '081111111024', 'user2.jpeg'),
(25, 'Taufik Surya', 'taufik90', 'D6%1PHpRPA', 'Samarinda', 35, 'Laki-laki', 'Fotografi', 'fun date', 'Taufik, 35 tahun. Hidup itu singkat, jadi aku habiskan dengan Fotografi dan sesekali Fotografi. Yuk connect!', '081111111025', 'user1.jpeg'),
(26, 'Ivan Rahayu', 'ivan91', 'XnbUb7VHNc', 'Depok', 34, 'Laki-laki', 'Memancing, Skateboard', 'friends', 'Ivan, 34 tahun. Hidup itu singkat, jadi aku habiskan dengan Memancing dan sesekali Skateboard. Yuk connect!', '081111111026', 'user1.jpeg'),
(27, 'Raihan Lestari', 'raihan67', '@$B2t5uvtD', 'Manado', 30, 'Laki-laki', 'Tennis, Skateboard', 'fwb', 'Hai, aku Raihan! Seorang pria yang gemar Tennis. Lagi cari teman baru yang seru diajak ngobrol soal Skateboard.', '081111111027', 'user1.jpeg'),
(28, 'Yuni Putra', 'yuni06', 'ZN4TmKwTq4', 'Depok', 19, 'Perempuan', 'Mendaki, Melukis', 'fun date', 'Hai, aku Yuni! Seorang wanita yang gemar Mendaki. Lagi cari teman baru yang seru diajak ngobrol soal Melukis.', '081111111028', 'user2.jpeg'),
(29, 'Bayu Prasetyo', 'bayu98', 'R59P#N392c', 'Denpasar', 27, 'Laki-laki', 'DIY Craft, Gaming', 'fwb', 'Aku Bayu, seseorang yang percaya bahwa hidup harus dijalani dengan DIY Craft. Juga suka Gaming di waktu senggang.', '081111111029', 'user1.jpeg'),
(30, 'Farhan Surya', 'xfarhan906', 'I4PbXuqiuq', 'Bekasi', 33, 'Laki-laki', 'Mendaki, Olahraga', 'menikah', 'Chill, seru, dan nggak bosen diajak ngobrol — itu aku, Farhan! Hobby: Mendaki & Olahraga. DM open~', '081111111030', 'user1.jpeg'),
(31, 'Nadia Cahya', 'nadia98', 'vugl3PvUdq', 'Depok', 27, 'Perempuan', 'Berkebun, Nonton Film', 'fwb', 'Nadia, 27 tahun. Hidup itu singkat, jadi aku habiskan dengan Berkebun dan sesekali Nonton Film. Yuk connect!', '081111111031', 'user2.jpeg'),
(32, 'Bagas Kusuma', 'bagas_id', 'vu2!IS6EFS', 'Tangerang', 34, 'Laki-laki', 'Memancing', 'fun date', 'Halo! Aku Bagas, 34 tahun dari Tangerang. Suka Memancing dan Memancing, orangnya easy-going dan open-minded. Yuk kenalan!', '081111111032', 'user1.jpeg'),
(33, 'Rafi Ramadhan', 'rafi_id', 'NUwYnisjRK', 'Yogyakarta', 28, 'Laki-laki', 'Cosplay, Badminton', 'fun date', 'Halo! Aku Rafi, 28 tahun dari Yogyakarta. Suka Cosplay dan Badminton, orangnya easy-going dan open-minded. Yuk kenalan!', '081111111033', 'user1.jpeg'),
(34, 'Dina Wijaya', 'dina_id', 'ya$sxCxaUz', 'Surabaya', 20, 'Perempuan', 'Fotografi, Ngopi', 'menikah', 'Hai, aku Dina! Seorang wanita yang gemar Fotografi. Lagi cari teman baru yang seru diajak ngobrol soal Ngopi.', '081111111034', 'user2.jpeg'),
(35, 'Yoga Susanto', 'yoga1', 'YGrf4TAlcs', 'Samarinda', 33, 'Laki-laki', 'Memancing, Skateboard, Vlogging', 'menikah', 'Halo semuanya! Aku Yoga dari Samarinda. Kalau nggak lagi Memancing, pasti lagi Skateboard. Suka hal-hal baru!', '081111111035', 'user1.jpeg'),
(36, 'Bagas Putra', 'bagas95', 'aOYHiDqHJc', 'Tangerang', 30, 'Laki-laki', 'Tennis', 'menikah', 'Bagas, 30 tahun. Hidup itu singkat, jadi aku habiskan dengan Tennis dan sesekali Tennis. Yuk connect!', '081111111036', 'user1.jpeg'),
(37, 'Faisal Putra', 'faisal_id', 'l%ozqKHgSr', 'Bogor', 19, 'Laki-laki', 'Olahraga, Menulis, Gaming', 'menikah', 'Hey there! I\'m Faisal, 19 y/o based in Bogor. Passionate about Olahraga & Menulis. Let\'s vibe!', '081111111037', 'user1.jpeg'),
(38, 'Aulia Cahya', 'aulia01', 'D7odixrXMg', 'Yogyakarta', 24, 'Perempuan', 'Membaca, Bersepeda, Memancing', 'fwb', 'Orang bilang aku seru kalau udah kenal. Nama aku Aulia, suka banget Membaca. Also into Bersepeda. Let\'s talk!', '081111111038', 'user2.jpeg'),
(39, 'Galih Fauzi', 'galih74', 'v$Ccy$r4q8', 'Makassar', 28, 'Laki-laki', 'Bersepeda, Memancing, Berkebun', 'fun date', 'Orang bilang aku seru kalau udah kenal. Nama aku Galih, suka banget Bersepeda. Also into Memancing. Let\'s talk!', '081111111039', 'user1.jpeg'),
(40, 'Zahra Surya', 'zahra06', 'GRPv3Ne8QE', 'Balikpapan', 19, 'Perempuan', 'Olahraga', 'menikah', 'Chill, seru, dan nggak bosen diajak ngobrol — itu aku, Zahra! Hobby: Olahraga & Olahraga. DM open~', '081111111040', 'user2.jpeg');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `matches`
--
ALTER TABLE `matches`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `user1_id` (`user1_id`,`user2_id`),
  ADD KEY `user2_id` (`user2_id`);

--
-- Indexes for table `messages`
--
ALTER TABLE `messages`
  ADD PRIMARY KEY (`id`),
  ADD KEY `sender_id` (`sender_id`),
  ADD KEY `receiver_id` (`receiver_id`);

--
-- Indexes for table `swipes`
--
ALTER TABLE `swipes`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `user_id` (`user_id`,`target_id`),
  ADD KEY `target_id` (`target_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `matches`
--
ALTER TABLE `matches`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `messages`
--
ALTER TABLE `messages`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `swipes`
--
ALTER TABLE `swipes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `matches`
--
ALTER TABLE `matches`
  ADD CONSTRAINT `matches_ibfk_1` FOREIGN KEY (`user1_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `matches_ibfk_2` FOREIGN KEY (`user2_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `messages`
--
ALTER TABLE `messages`
  ADD CONSTRAINT `messages_ibfk_1` FOREIGN KEY (`sender_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `messages_ibfk_2` FOREIGN KEY (`receiver_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `swipes`
--
ALTER TABLE `swipes`
  ADD CONSTRAINT `swipes_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `swipes_ibfk_2` FOREIGN KEY (`target_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
