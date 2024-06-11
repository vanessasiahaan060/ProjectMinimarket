-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 16 Des 2023 pada 17.04
-- Versi server: 10.4.27-MariaDB
-- Versi PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `minimarket`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `admin`
--

CREATE TABLE `admin` (
  `id` int(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `admin`
--

INSERT INTO `admin` (`id`, `username`, `password`) VALUES
(1, 'admin', 'admin123'),
(2, 'admin00', 'sobatcim');

-- --------------------------------------------------------

--
-- Struktur dari tabel `cashier`
--

CREATE TABLE `cashier` (
  `id` int(100) NOT NULL,
  `cashier_id` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `firstname` varchar(100) NOT NULL,
  `lastname` varchar(100) NOT NULL,
  `gender` varchar(100) NOT NULL,
  `date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `cashier`
--

INSERT INTO `cashier` (`id`, `cashier_id`, `password`, `firstname`, `lastname`, `gender`, `date`) VALUES
(1, 'ABC123', 'cashier123', 'vanessa', 'siahaan', 'famale', NULL),
(2, 'ABC789', 'lucuww', 'nania', 'pangaribuan', 'Male', '2023-12-12');

-- --------------------------------------------------------

--
-- Struktur dari tabel `customer`
--

CREATE TABLE `customer` (
  `id` int(100) NOT NULL,
  `customer_id` int(100) NOT NULL,
  `brand` varchar(100) NOT NULL,
  `productName` varchar(100) NOT NULL,
  `quantity` int(100) NOT NULL,
  `price` double NOT NULL,
  `date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `customer`
--

INSERT INTO `customer` (`id`, `customer_id`, `brand`, `productName`, `quantity`, `price`, `date`) VALUES
(7, 1, 'Basreng', 'Basreng Ori', 2, 12, '2023-12-15'),
(8, 2, 'Basreng', 'Basreng Pedas', 4, 36, '2023-12-15'),
(9, 2, 'Basreng', 'Basreng Ori', 2, 12, '2023-12-15'),
(10, 3, 'Basreng', 'Basreng Ori', 2, 12, '2023-12-15'),
(11, 3, 'Basreng', 'Basreng Pedas', 4, 36, '2023-12-15'),
(12, 4, 'Basreng', 'Basreng Ori', 2, 12, '2023-12-15'),
(13, 4, 'Cimol', 'Cimol Ori', 2, 18, '2023-12-15'),
(14, 5, 'Cimol', 'Cimol Pedas', 2, 20, '2023-12-15'),
(15, 5, 'Cimol', 'Cimol Ori', 2, 18, '2023-12-15'),
(16, 5, 'Basreng', 'Basreng Ori', 2, 12, '2023-12-15'),
(17, 5, 'Basreng', 'Basreng Pedas', 2, 18, '2023-12-15'),
(18, 6, 'Keripik', 'Kerpik pedas', 3, 12, '2023-12-16'),
(19, 6, 'Cimol', 'Cimol Pedas', 1, 10, '2023-12-16'),
(20, 6, 'Cimol', 'Cimol Ori', 3, 27, '2023-12-16'),
(21, 7, 'Bubur', 'Bubur Sumsum', 2, 10, '2023-12-16'),
(22, 7, 'Keripik', 'Kerpik jagung', 1, 2, '2023-12-16'),
(23, 8, 'Bubur', 'Bubur Sumsum', 2, 10, '2023-12-16'),
(24, 8, 'Basreng', 'Basreng Ori', 4, 24, '2023-12-16'),
(25, 8, 'Cimol', 'Cimol Ori', 1, 9, '2023-12-16'),
(26, 9, 'Keripik', 'Kerpik ori', 2, 6, '2023-12-16'),
(27, 10, 'Basreng', 'Basreng Ori', 2, 12, '2023-12-16'),
(28, 10, 'Basreng', 'Basreng Pedas', 4, 36, '2023-12-16'),
(29, 10, 'Cimol', 'Cimol Ori', 6, 54, '2023-12-16');

-- --------------------------------------------------------

--
-- Struktur dari tabel `customer_receipt`
--

CREATE TABLE `customer_receipt` (
  `id` int(100) NOT NULL,
  `customer_id` int(100) NOT NULL,
  `total` double NOT NULL,
  `date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `customer_receipt`
--

INSERT INTO `customer_receipt` (`id`, `customer_id`, `total`, `date`) VALUES
(1, 1, 12, '2023-12-15'),
(2, 2, 48, '2023-12-15'),
(3, 3, 48, '2023-12-15'),
(4, 4, 30, '2023-12-15'),
(5, 5, 68, '2023-12-15'),
(6, 6, 49, '2023-12-16'),
(7, 7, 12, '2023-12-16'),
(8, 8, 43, '2023-12-16'),
(9, 9, 6, '2023-12-16'),
(10, 10, 102, '2023-12-16');

-- --------------------------------------------------------

--
-- Struktur dari tabel `product`
--

CREATE TABLE `product` (
  `id` int(100) NOT NULL,
  `product_id` varchar(100) NOT NULL,
  `brand` varchar(100) NOT NULL,
  `product_name` varchar(100) NOT NULL,
  `status` varchar(100) NOT NULL,
  `price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `product`
--

INSERT INTO `product` (`id`, `product_id`, `brand`, `product_name`, `status`, `price`) VALUES
(1, 'CIM01', 'Basreng', 'Basreng Ori', 'Available', 6),
(18, 'CIM02', 'Basreng', 'Basreng Pedas', 'Available', 9),
(19, 'CIM03', 'Cimol', 'Cimol Ori', 'Available', 9),
(20, 'CIM04', 'Cimol', 'Cimol Pedas', 'Available', 10),
(21, 'CIM05', 'Keripik', 'Kerpik jagung', 'Available', 2),
(22, 'CIM06', 'Keripik', 'Kerpik pedas', 'Available', 4),
(23, 'CIM07', 'Keripik', 'Kerpik ori', 'Available', 3),
(24, 'CIM08', 'Bubur', 'Bubur Sumsum', 'Not Available', 5),
(26, 'CIM09', 'Bubur', 'Bubur Kacang Ijo', 'Not Available', 5);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `cashier`
--
ALTER TABLE `cashier`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `customer_receipt`
--
ALTER TABLE `customer_receipt`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT untuk tabel `cashier`
--
ALTER TABLE `cashier`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT untuk tabel `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT untuk tabel `customer_receipt`
--
ALTER TABLE `customer_receipt`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT untuk tabel `product`
--
ALTER TABLE `product`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
