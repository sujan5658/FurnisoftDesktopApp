-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 29, 2020 at 04:28 PM
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

--
-- Dumping data for table `amounts_paid`
--

INSERT INTO `amounts_paid` (`id`, `date`, `amount_paid`, `amount_due`) VALUES
(1, '2074-04-04', 60000, 7800),
(2, '2055-05-05', 50221.72, 0),
(3, '2044-04-04', 2000, 825),
(4, '2099-09-09', 2825, 0),
(5, '2055-05-05', 1000, 1034),
(6, '2044-04-04', 1000, 695),
(7, '2044-04-04', 500, 517),
(8, '2055-05-05', 4972, 0),
(9, '2088-05-06', 30000, 10491.29),
(10, '2066-06-06', 2000, 825),
(11, '2066-06-06', 60000, 2777.1500000000015),
(12, '2033-03-03', 500, 2325),
(13, '2077-05-13', 1000, 627.2),
(14, '2074-05-06', 627.2, 0),
(15, '2025-05-05', 2325, 0),
(16, '2090-08-09', 2500, 277.15000000000146),
(17, '2033-03-03', 130000, 864.1699999999983),
(18, '2077-05-06', 30000, 10000),
(19, '2066-06-06', 100000, 3400),
(20, '2078-08-09', 20000, 25300),
(21, '2055-05-05', 15000, 10300),
(22, '2066-06-06', 10000, 300),
(23, '2022-02-02', 250, 50),
(24, '2045-05-05', 100000, 11000.559999999998),
(25, '2055-06-08', 80000, 7888.01),
(26, '2074-05-06', 7000, 888.0099999999948),
(27, '2074-06-08', 55000, 4826.72),
(28, '2074-05-08', 4800, 26.72),
(29, '2074-08-08', 2000, 0);

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

--
-- Dumping data for table `bill`
--

INSERT INTO `bill` (`bill_no`, `bill_date`, `customer_name`, `customer_address`, `customer_no`, `payment_status`, `total`, `vat_amount`, `net_total`, `discount_amount`, `grand_total`, `bill_name`, `status`) VALUES
(1, '2075-05-09', 'Sujan Koju', 'Golmadhi', '9849675658', 'Payment Complete', 2360, 306.8, 2666.8, 0, 2666.8, '', 0),
(2, '2077-07-07', 'Sujan Koju', 'Golmadhi', '9849675658', 'Payment Complete', 18360, 2386.8, 20746.8, 0, 20746.8, 'pdf\\2077-07-072373394.pdf', 1),
(3, '2074-04-04', 'Mike', 'Austrilia', '1234567890', 'Payment Complete', 60000, 7800, 67800, 0, 67800, 'pdf\\2074-04-04390991.pdf', 1),
(4, '2055-05-05', 'Sam', 'US', '9898989898', 'Payment Complete', 44444, 5777.72, 50221.72, 0, 50221.72, 'pdf\\2055-05-054598550.pdf', 1),
(5, '2044-04-04', 'Tyson', 'hetauda', '1234567890', 'Payment Due', 2500, 325, 2825, 0, 2825, 'pdf\\2044-04-045129258.pdf', 1),
(6, '2099-09-09', 'Ram prasad', 'itachhen', '9846232549', 'Payment Complete', 2500, 325, 2825, 0, 2825, 'pdf\\2099-09-096654134.pdf', 1),
(7, '2055-05-05', 'Suko', 'golmadhi', '9846152635', 'Payment Due', 1800, 234, 2034, 0, 2034, 'pdf\\2055-05-05741412.pdf', 1),
(8, '2044-04-04', 'asdfasdf', 'asfdsdf', '9856253614', 'Payment Due', 1500, 195, 1695, 0, 1695, 'pdf\\2044-04-04859032.pdf', 1),
(9, '2044-04-04', 'asdfasdf', 'asfdsdf', '1234567980', 'Payment Due', 900, 117, 1017, 0, 1017, 'pdf\\2044-04-049304225.pdf', 1),
(10, '2055-05-05', 'asdfsaf', 'assda', '1234567890', 'Payment Complete', 4400, 572, 4972, 0, 4972, 'pdf\\2055-05-0510103452.pdf', 1),
(11, '2088-05-06', 'asfsadf', 'asdfsdfa', '9865152348', 'Payment Due', 35833, 4658.29, 40491.29, 0, 40491.29, 'pdf\\2088-05-0611587044.pdf', 1),
(12, '2066-06-06', 'asfdas', 'asfsdf', '1234567890', 'Payment Due', 2500, 325, 2825, 0, 2825, 'pdf\\2066-06-061298713.pdf', 1),
(14, '2066-06-06', 'asdfsdf', 'jhakjsdf', '1234567980', 'Payment Complete', 55555, 7222.150000000001, 62777.15, 0, 62777.15, 'pdf\\2066-06-0614818164.pdf', 1),
(15, '2033-03-03', 'adfksdj', 'lkajdsf', '1234567980', 'Payment Complete', 2500, 325, 2825, 0, 2825, 'pdf\\2033-03-0315880365.pdf', 1),
(16, '2077-05-13', 'Sujan Koju', 'Golmadhi', '9849675658', 'Payment Complete', 1440, 187.20000000000002, 1627.2, 0, 1627.2, 'pdf\\2077-05-1316514416.pdf', 1),
(18, '2033-03-03', 'test test', 'test123', '9876543210', 'Payment Due', 115809, 15055.17, 130864.17, 0, 130864.17, 'pdf\\2033-03-0318950490.pdf', 1),
(19, '2077-05-06', 'Naran Goapal ', 'Kausaltar', '9849675658', 'Payment Due', 36040, 4685.2, 40725.2, 725.2, 40000, 'pdf\\2077-05-0619637299.pdf', 1),
(20, '2066-06-06', 'Krishna Shrestha', 'Suryabinayek', '9846235618', 'Payment Due', 92000, 11960, 103960, 560, 103400, 'pdf\\2066-06-0620584660.pdf', 1),
(21, '2078-08-09', 'Madan Bahadur Suwal', 'Byasi', '9847562819', 'Payment Complete', 40540, 5270.2, 45810.2, 510.2, 45300, 'pdf\\2078-08-0921821443.pdf', 0),
(24, '2045-05-05', 'Bubble Gum', 'kitchen', '9846285939', 'Payment Due', 98501.38, 12805.18, 111306.56, 306, 111000.56, 'pdf\\2045-05-0524409186.pdf', 1),
(26, '2055-06-08', 'Hunger', 'mangal', '9846257928', 'Payment Due', 77777, 10111.01, 87888.01, 0, 87888.01, 'pdf\\2055-06-082639565.pdf', 1),
(27, '2074-06-08', 'mike mike', 'asfsadf', '9846582613', 'Payment Complete', 52944, 6882.72, 59826.72, 0, 59826.72, 'pdf\\2074-06-0827747095.pdf', 1),
(29, '2074-08-08', 'sfsadf', 'sadjhh', '1234567890', 'Payment Complete', 2000, 260, 2260, 260, 2000, 'pdf\\2074-08-0829752756.pdf', 0);

-- --------------------------------------------------------

--
-- Table structure for table `bill_amounts_paid`
--

CREATE TABLE `bill_amounts_paid` (
  `bill_bill_no` bigint(20) NOT NULL,
  `amountPaid_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `bill_amounts_paid`
--

INSERT INTO `bill_amounts_paid` (`bill_bill_no`, `amountPaid_id`) VALUES
(3, 1),
(4, 2),
(5, 3),
(6, 4),
(7, 5),
(8, 6),
(9, 7),
(10, 8),
(11, 9),
(12, 10),
(14, 11),
(14, 16),
(15, 12),
(15, 15),
(16, 13),
(16, 14),
(18, 17),
(19, 18),
(20, 19),
(21, 20),
(21, 21),
(21, 22),
(21, 23),
(24, 24),
(26, 25),
(26, 26),
(27, 27),
(27, 28),
(29, 29);

-- --------------------------------------------------------

--
-- Table structure for table `bill_sold_items`
--

CREATE TABLE `bill_sold_items` (
  `bill_bill_no` bigint(20) NOT NULL,
  `soldItems_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `bill_sold_items`
--

INSERT INTO `bill_sold_items` (`bill_bill_no`, `soldItems_id`) VALUES
(1, 1),
(1, 2),
(2, 3),
(2, 4),
(3, 5),
(4, 6),
(5, 7),
(6, 8),
(7, 9),
(8, 10),
(9, 11),
(10, 12),
(11, 13),
(11, 14),
(12, 15),
(14, 16),
(15, 17),
(16, 18),
(18, 19),
(18, 20),
(18, 21),
(18, 22),
(19, 23),
(19, 24),
(19, 25),
(19, 26),
(20, 27),
(20, 28),
(20, 29),
(21, 30),
(21, 31),
(21, 32),
(21, 33),
(24, 34),
(24, 35),
(24, 36),
(26, 37),
(26, 38),
(27, 39),
(27, 40),
(27, 41),
(29, 42);

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

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `category_name`, `reg_date`, `exp_date`, `status`) VALUES
(1, 'Chair', '2077-05-23', '1111-11-11', 1),
(2, 'Bed', '2077-04-23', '1111-11-11', 1),
(3, 'Darajs', '2077-05-25', '1111-11-11', 1),
(4, 'Locks', '2074-08-09', '1111-11-11', 1),
(5, 'Nail', '2074-07-08', '1111-11-11', 1),
(6, 'Plys', '2074-05-06', '2088-08-08', 1),
(7, 'Ply', '2074-05-06', '2088-08-08', 0),
(8, 'ply', '2074-05-08', '2066-06-06', 0);

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

--
-- Dumping data for table `hardwares`
--

INSERT INTO `hardwares` (`id`, `hardware_name`, `price`, `available_quantity`, `category`, `size`, `reg_date`, `exp_date`, `status`) VALUES
(1, 'light chair', 5000, 20, 'Chair', 'medium', '2077-06-28', '1111-11-11', 1),
(2, 'king size bed std', 18000, 0, 'Bed', 'medium', '2074-04-12', '1111-11-11', 1),
(3, 'Queen size bed', 15000, 40, 'Bed', 'large', '2074-04-12', '1111-11-11', 1),
(4, 'bright chair', 1500, 0, 'Chair', 'small', '2077-08-04', '1111-11-11', 1),
(5, 'quik chair', 1900, 5, 'Chair', 'large', '2045-08-06', '1111-11-11', 1),
(6, 'Daraj 2 piece std', 1000, 0, 'Darajs', 'small', '2075-05-05', '1111-11-11', 1),
(7, 'Daraj 3 piece std', 55000, 0, 'Darajs', 'large', '2077-07-08', '1111-11-11', 1),
(8, 'Daraj 4 piece stdn', 11111, 0, 'Darajs', 'large', '2077-07-07', '1111-11-11', 1),
(9, 'Daraj 5 piece std', 11111, 0, 'Darajs', 'Large', '2222-12-22', '1111-11-11', 1),
(10, 'Chair standard ', 100, 0, 'Chair', 'medium', '2074-08-10', '1111-11-11', 1),
(11, 'chair small ', 1100, 0, 'Chair', 'small', '2074-08-06', '1111-11-11', 1),
(12, 'Fish Lock std', 500, 0, 'Locks', 'medium', '2074-08-09', '1111-11-11', 1),
(13, 'Tank Lock std', 300, 200, 'Locks', 'small', '2074-08-09', '2080-08-08', 0),
(14, 'Dhalan killa std', 18, 0, 'Nail', 'largee', '2074-08-10', '1111-11-11', 1),
(15, 'main daraj', 1500, 0, 'Darajs', 'small', '2074-05-06', '1111-11-11', 1),
(16, 'new Ply 1', 1500, 0, 'ply', 'small', '2075-08-06', '1111-11-11', 0),
(17, 'Tank Lock std', 2000, 0, 'Locks', 'small', '2074-08-09', '1111-11-11', 1);

-- --------------------------------------------------------

--
-- Table structure for table `hardwares_stock_details`
--

CREATE TABLE `hardwares_stock_details` (
  `hardwares_id` bigint(20) NOT NULL,
  `stockDetails_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `hardwares_stock_details`
--

INSERT INTO `hardwares_stock_details` (`hardwares_id`, `stockDetails_id`) VALUES
(3, 2),
(9, 3),
(13, 8),
(13, 9),
(13, 10),
(13, 11);

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
(1, 'Brahmayani Trade Concern', 'Chyamasingh road, Bhaktapur', 609448971),
(2, 'Brahmayani Trade Concern', 'Chyamasingh road, Bhaktapur', 609448971),
(3, 'Brahmayani Trade Concern', 'Chyamasingh road, Bhaktapur', 609448971),
(4, 'Brahmayani Trade Concern', 'Chyamasingh road, Bhaktapur', 609448971);

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

--
-- Dumping data for table `sold_items`
--

INSERT INTO `sold_items` (`id`, `hardware_name`, `rate`, `quantity`, `total`) VALUES
(1, 'Fish Lock std', 500, 4, 2000),
(2, 'Dhalan killa std', 18, 20, 360),
(3, 'king size bed std', 18000, 1, 18000),
(4, 'Dhalan killa std', 18, 20, 360),
(5, 'Queen size bed', 15000, 4, 60000),
(6, 'Daraj 5 piece std', 11111, 4, 44444),
(7, 'Fish Lock std', 500, 5, 2500),
(8, 'Fish Lock std', 500, 5, 2500),
(9, 'Dhalan killa std', 18, 100, 1800),
(10, 'main daraj', 1500, 1, 1500),
(11, 'Dhalan killa std', 18, 50, 900),
(12, 'chair small ', 1100, 4, 4400),
(13, 'Daraj 5 piece std', 11111, 3, 33333),
(14, 'Fish Lock std', 500, 5, 2500),
(15, 'Fish Lock std', 500, 5, 2500),
(16, 'Daraj 4 piece stdn', 11111, 5, 55555),
(17, 'Fish Lock std', 500, 5, 2500),
(18, 'Dhalan killa std', 18, 80, 1440),
(19, 'Dhalan killa std', 18, 3, 54),
(20, 'Chair standard ', 100, 2, 200),
(21, 'Daraj 4 piece stdn', 11111, 5, 55555),
(22, 'Tank Lock std', 2000, 30, 60000),
(23, 'king size bed std', 18000, 1, 18000),
(24, 'Queen size bed', 15000, 1, 15000),
(25, 'Fish Lock std', 500, 5, 2500),
(26, 'Dhalan killa std', 18, 30, 540),
(27, 'Tank Lock std', 2000, 5, 10000),
(28, 'Fish Lock std', 500, 20, 10000),
(29, 'king size bed std', 18000, 4, 72000),
(30, 'king size bed std', 18000, 2, 36000),
(31, 'main daraj', 1500, 1, 1500),
(32, 'Dhalan killa std', 18, 30, 540),
(33, 'Fish Lock std', 500, 5, 2500),
(34, 'main daraj', 1500, 5, 7500),
(35, 'Daraj 5 piece std', 25000.46, 3, 75001.38),
(36, 'Tank Lock std', 2000, 8, 16000),
(37, 'Daraj 5 piece std', 11111, 5, 55555),
(38, 'Daraj 4 piece stdn', 11111, 2, 22222),
(39, 'Daraj 5 piece std', 11111, 4, 44444),
(40, 'Fish Lock std', 500, 5, 2500),
(41, 'main daraj', 1500, 4, 6000),
(42, 'Fish Lock std', 500, 4, 2000);

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
-- Dumping data for table `stock_details`
--

INSERT INTO `stock_details` (`id`, `quantity`, `action`, `comment`, `reg_date`, `exp_date`, `status`) VALUES
(1, 15, 'ADD', 'test add stock', '2075-05-25', '1111-11-11', 1),
(2, 5, 'REMOVE', 'removed 5 damaged beds.', '2074-08-08', '1111-11-11', 1),
(3, 25, 'ADD', '25 piece added. To Daraj.', '2074-05-05', '1111-11-11', 1),
(4, 50, 'ADD', 'fresh added', '2074-05-05', '1111-11-11', 1),
(5, 30, 'ADD', '2nd added', '2075-05-05', '1111-11-11', 1),
(6, 10, 'ADD', '3rd added', '2076-05-05', '1111-11-11', 1),
(7, 10, 'REMOVE', 'returned defective pieces.', '2076-05-10', '1111-11-11', 1),
(8, 10, 'ADD', 'replaced defective pieces.', '2076-05-15', '1111-11-11', 1),
(9, 50, 'ADD', 'added again.', '2075-05-05', '1111-11-11', 1),
(10, 50, 'ADD', 'Added new locks.', '2044-08-09', '1111-11-11', 1),
(11, 10, 'ADD', 'Added new designed locks.', '2075-08-09', '1111-11-11', 1);

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
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `hardwares`
--
ALTER TABLE `hardwares`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `shop_details`
--
ALTER TABLE `shop_details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `sold_items`
--
ALTER TABLE `sold_items`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT for table `stock_details`
--
ALTER TABLE `stock_details`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

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
