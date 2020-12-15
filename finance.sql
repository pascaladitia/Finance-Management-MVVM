-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 15 Des 2020 pada 07.41
-- Versi server: 10.4.14-MariaDB
-- Versi PHP: 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `finance`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `category_expence`
--

CREATE TABLE `category_expence` (
  `category_id` int(11) NOT NULL,
  `category_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `category_expence`
--

INSERT INTO `category_expence` (`category_id`, `category_name`) VALUES
(1, 'Ekonomi'),
(2, 'Pajak'),
(3, 'Bayar Hutang'),
(4, 'Lainnya');

-- --------------------------------------------------------

--
-- Struktur dari tabel `category_income`
--

CREATE TABLE `category_income` (
  `category_id` int(11) NOT NULL,
  `category_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `category_income`
--

INSERT INTO `category_income` (`category_id`, `category_name`) VALUES
(1, 'Gaji'),
(2, 'Hasil Sewa'),
(3, 'Bunga Hutang'),
(4, 'Bonus');

-- --------------------------------------------------------

--
-- Struktur dari tabel `expence`
--

CREATE TABLE `expence` (
  `expence_id` int(11) NOT NULL,
  `expence_date` date NOT NULL,
  `expence_money` varchar(50) NOT NULL,
  `category_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `expence`
--

INSERT INTO `expence` (`expence_id`, `expence_date`, `expence_money`, `category_id`) VALUES
(1, '2020-12-18', '3000', 1),
(2, '2020-12-17', '12000', 2),
(3, '2020-12-15', '2200', 3),
(7, '2020-12-10', '5000', 3),
(8, '2020-12-16', '7000', 2);

-- --------------------------------------------------------

--
-- Struktur dari tabel `income`
--

CREATE TABLE `income` (
  `income_id` int(11) NOT NULL,
  `income_date` date NOT NULL,
  `income_money` varchar(50) NOT NULL,
  `category_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `income`
--

INSERT INTO `income` (`income_id`, `income_date`, `income_money`, `category_id`) VALUES
(1, '2020-12-16', '5000', 1),
(2, '2020-12-18', '2000', 2),
(5, '2020-12-19', '7000', 3),
(6, '2020-12-29', '8000', 4),
(7, '2020-12-31', '21000', 4),
(10, '2020-12-13', '4000', 1),
(11, '2020-10-12', '5000', 3);

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `user_email` varchar(100) NOT NULL,
  `user_password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`user_id`, `user_email`, `user_password`) VALUES
(1, 'pascal@gmail.com', '25d55ad283aa400af464c76d713c07ad');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `category_expence`
--
ALTER TABLE `category_expence`
  ADD PRIMARY KEY (`category_id`);

--
-- Indeks untuk tabel `category_income`
--
ALTER TABLE `category_income`
  ADD PRIMARY KEY (`category_id`);

--
-- Indeks untuk tabel `expence`
--
ALTER TABLE `expence`
  ADD PRIMARY KEY (`expence_id`),
  ADD KEY `category_id` (`category_id`);

--
-- Indeks untuk tabel `income`
--
ALTER TABLE `income`
  ADD PRIMARY KEY (`income_id`),
  ADD KEY `category_id` (`category_id`);

--
-- Indeks untuk tabel `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `category_expence`
--
ALTER TABLE `category_expence`
  MODIFY `category_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT untuk tabel `category_income`
--
ALTER TABLE `category_income`
  MODIFY `category_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT untuk tabel `expence`
--
ALTER TABLE `expence`
  MODIFY `expence_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT untuk tabel `income`
--
ALTER TABLE `income`
  MODIFY `income_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT untuk tabel `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `expence`
--
ALTER TABLE `expence`
  ADD CONSTRAINT `expence_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `category_expence` (`category_id`);

--
-- Ketidakleluasaan untuk tabel `income`
--
ALTER TABLE `income`
  ADD CONSTRAINT `income_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `category_income` (`category_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
