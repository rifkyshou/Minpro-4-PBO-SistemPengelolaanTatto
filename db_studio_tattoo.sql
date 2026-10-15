-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Oct 15, 2026 at 11:58 AM
-- Server version: 8.0.30
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_studio_tattoo`
--

-- --------------------------------------------------------

--
-- Table structure for table `booking`
--

CREATE TABLE `booking` (
  `id_booking` int NOT NULL,
  `nama_pelanggan` varchar(100) NOT NULL,
  `hp_pelanggan` varchar(20) NOT NULL,
  `alamat_pelanggan` varchar(150) NOT NULL,
  `id_artist` int NOT NULL,
  `nama_desain` varchar(100) NOT NULL,
  `harga` double NOT NULL,
  `tanggal` varchar(10) NOT NULL,
  `status` varchar(20) NOT NULL DEFAULT 'Menunggu'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `booking`
--

INSERT INTO `booking` (`id_booking`, `nama_pelanggan`, `hp_pelanggan`, `alamat_pelanggan`, `id_artist`, `nama_desain`, `harga`, `tanggal`, `status`) VALUES
(1, 'Andi Saputra', '08124657890', 'Banjarmasin', 1, 'Naga Jepang', 750000, '12-09-2026', 'Menunggu'),
(2, 'Siti Nurhaliza', '081355657898', 'Martapura', 2, 'Bunga Mawar', 350000, '13-09-2026', 'Selesai');

-- --------------------------------------------------------

--
-- Table structure for table `tattoo_artist`
--

CREATE TABLE `tattoo_artist` (
  `id_artist` int NOT NULL,
  `nama` varchar(100) NOT NULL,
  `no_hp` varchar(20) NOT NULL,
  `spesialisasi` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `tattoo_artist`
--

INSERT INTO `tattoo_artist` (`id_artist`, `nama`, `no_hp`, `spesialisasi`) VALUES
(1, 'Rian Ink', '0821767686', 'Realis'),
(2, 'Dewi Tattoo', '082145456655', 'Minimalis'),
(3, 'Liver', '081340474545', 'Minimalis');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `booking`
--
ALTER TABLE `booking`
  ADD PRIMARY KEY (`id_booking`),
  ADD KEY `fk_booking_artist` (`id_artist`);

--
-- Indexes for table `tattoo_artist`
--
ALTER TABLE `tattoo_artist`
  ADD PRIMARY KEY (`id_artist`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `booking`
--
ALTER TABLE `booking`
  MODIFY `id_booking` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `tattoo_artist`
--
ALTER TABLE `tattoo_artist`
  MODIFY `id_artist` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `booking`
--
ALTER TABLE `booking`
  ADD CONSTRAINT `fk_booking_artist` FOREIGN KEY (`id_artist`) REFERENCES `tattoo_artist` (`id_artist`) ON DELETE RESTRICT ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
