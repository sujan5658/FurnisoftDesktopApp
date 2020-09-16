-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 30, 2020 at 12:39 PM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `furnisoft`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `email` varchar(30) NOT NULL,
  `pass` varchar(255) NOT NULL,
  `invalid_count` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `email`, `pass`, `invalid_count`, `status`) VALUES
(1, 'myselfsujan67@gmail.com', '$2a$12$EuxdL5WmSBZLzvdNyjQiG.bd8PKVJ2s3WMv1HFF0/WLTg6OsHOPfW', 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `amounts_paid`
--

CREATE TABLE `amounts_paid` (
  `id` bigint(20) NOT NULL,
  `date` varchar(10) NOT NULL,
  `amount_paid` double NOT NULL,
  `amount_due` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `bill`
--

CREATE TABLE `bill` (
  `bill_no` bigint(20) NOT NULL,
  `bill_date` varchar(10) NOT NULL,
  `customer_name` varchar(255) NOT NULL,
  `customer_address` varchar(255) NOT NULL,
  `customer_no` varchar(10) NOT NULL,
  `payment_status` varchar(20) NOT NULL,
  `total` double NOT NULL,
  `vat_amount` double NOT NULL,
  `net_total` double NOT NULL,
  `discount_amount` double NOT NULL,
  `grand_total` double NOT NULL,
  `bill_name` varchar(255) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `bill_amounts_paid`
--

CREATE TABLE `bill_amounts_paid` (
  `bill_bill_no` bigint(20) NOT NULL,
  `amountPaid_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `bill_sold_items`
--

CREATE TABLE `bill_sold_items` (
  `bill_bill_no` bigint(20) NOT NULL,
  `soldItems_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id` bigint(20) NOT NULL,
  `category_name` varchar(255) NOT NULL,
  `reg_date` varchar(10) NOT NULL,
  `exp_date` varchar(10) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `hardwares`
--

CREATE TABLE `hardwares` (
  `id` bigint(20) NOT NULL,
  `hardware_name` varchar(255) NOT NULL,
  `price` double NOT NULL,
  `available_quantity` int(11) NOT NULL,
  `category` varchar(255) NOT NULL,
  `size` varchar(255) NOT NULL,
  `reg_date` varchar(10) NOT NULL,
  `exp_date` varchar(10) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `hardwares_stock_details`
--

CREATE TABLE `hardwares_stock_details` (
  `hardwares_id` bigint(20) NOT NULL,
  `stockDetails_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `shop_details`
--

CREATE TABLE `shop_details` (
  `id` int(11) NOT NULL,
  `shop_name` varchar(255) NOT NULL,
  `shop_address` varchar(255) NOT NULL,
  `vat_no` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `shop_details`
--

INSERT INTO `shop_details` (`id`, `shop_name`, `shop_address`, `vat_no`) VALUES
(1, 'Brahmayani Trade Concern', 'Chyamasingh road, Bhaktapur', 609448971);

-- --------------------------------------------------------

--
-- Table structure for table `sold_items`
--

CREATE TABLE `sold_items` (
  `id` bigint(20) NOT NULL,
  `hardware_name` varchar(255) NOT NULL,
  `rate` double NOT NULL,
  `quantity` int(11) NOT NULL,
  `total` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `stock_details`
--

CREATE TABLE `stock_details` (
  `id` bigint(20) NOT NULL,
  `quantity` int(11) NOT NULL,
  `action` varchar(10) NOT NULL,
  `comment` text NOT NULL,
  `reg_date` varchar(10) NOT NULL,
  `exp_date` varchar(10) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `amounts_paid`
--
ALTER TABLE `amounts_paid`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bill`
--
ALTER TABLE `bill`
  ADD PRIMARY KEY (`bill_no`);

--
-- Indexes for table `bill_amounts_paid`
--
ALTER TABLE `bill_amounts_paid`
  ADD UNIQUE KEY `amountPaid_id` (`amountPaid_id`),
  ADD KEY `bill_bill_no` (`bill_bill_no`);

--
-- Indexes for table `bill_sold_items`
--
ALTER TABLE `bill_sold_items`
  ADD UNIQUE KEY `soldItems_id` (`soldItems_id`),
  ADD KEY `bill_bill_no` (`bill_bill_no`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `hardwares`
--
ALTER TABLE `hardwares`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `hardwares_stock_details`
--
ALTER TABLE `hardwares_stock_details`
  ADD UNIQUE KEY `stockDetails_id` (`stockDetails_id`),
  ADD KEY `hardwares_id` (`hardwares_id`);

--
-- Indexes for table `shop_details`
--
ALTER TABLE `shop_details`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sold_items`
--
ALTER TABLE `sold_items`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `stock_details`
--
ALTER TABLE `stock_details`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `amounts_paid`
--
ALTER TABLE `amounts_paid`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `hardwares`
--
ALTER TABLE `hardwares`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `shop_details`
--
ALTER TABLE `shop_details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `sold_items`
--
ALTER TABLE `sold_items`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `stock_details`
--
ALTER TABLE `stock_details`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bill_amounts_paid`
--
ALTER TABLE `bill_amounts_paid`
  ADD CONSTRAINT `bill_amounts_paid_ibfk_1` FOREIGN KEY (`bill_bill_no`) REFERENCES `bill` (`bill_no`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `bill_amounts_paid_ibfk_2` FOREIGN KEY (`amountPaid_id`) REFERENCES `amounts_paid` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `bill_sold_items`
--
ALTER TABLE `bill_sold_items`
  ADD CONSTRAINT `bill_sold_items_ibfk_1` FOREIGN KEY (`bill_bill_no`) REFERENCES `bill` (`bill_no`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `bill_sold_items_ibfk_2` FOREIGN KEY (`soldItems_id`) REFERENCES `sold_items` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `hardwares_stock_details`
--
ALTER TABLE `hardwares_stock_details`
  ADD CONSTRAINT `hardwares_stock_details_ibfk_1` FOREIGN KEY (`hardwares_id`) REFERENCES `hardwares` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `hardwares_stock_details_ibfk_2` FOREIGN KEY (`stockDetails_id`) REFERENCES `stock_details` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
