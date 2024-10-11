-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 01, 2023 at 03:00 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bikerent`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `customer_id` varchar(255) NOT NULL,
  `customer_name` varchar(255) NOT NULL,
  `phone` int(11) NOT NULL,
  `email` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id`, `customer_id`, `customer_name`, `phone`, `email`) VALUES
(7, 'C0001', 'Lucas', 98766566, '4848938@gmail.com'),
(8, 'C0002', 'Chun Hei', 987765544, 'chunkoheikokoman@gmail.com'),
(9, 'C0003', 'Bob', 98876655, 'bobb@gmail.com'),
(10, 'C0004', 'Mark', 85744343, 'mark@gmail.com'),
(11, 'C0005', 'Adolm', 98776543, 'dam@gmail.com'),
(12, 'C0006', 'Charlie', 97554252, 'CharlieCharlie@gmail.com'),
(13, 'C0007', 'Bobby', 98776543, 'bobby@gmail.com'),
(14, 'C0008', 'Chun Hei Ko', 94334252, '18705@learning.gsis.edu.hk'),
(15, 'C0009', 'Conor Sippel', 98778765, '17110@learning.gsis.edu.hk'),
(16, 'C0010', 'Sami', 98776543, '');

-- --------------------------------------------------------

--
-- Table structure for table `registration`
--

CREATE TABLE `registration` (
  `id` int(11) NOT NULL,
  `bikeID` varchar(255) NOT NULL,
  `model` varchar(255) NOT NULL,
  `cost` decimal(65,0) NOT NULL,
  `available` varchar(255) NOT NULL,
  `suspension` varchar(255) NOT NULL,
  `frame` varchar(255) NOT NULL,
  `tireTPI` int(11) NOT NULL,
  `wheelSize` int(11) NOT NULL,
  `weight` int(11) NOT NULL,
  `comfort_score` double NOT NULL,
  `steepness_score` double NOT NULL,
  `experience_score` double NOT NULL,
  `speed_score` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `registration`
--

INSERT INTO `registration` (`id`, `bikeID`, `model`, `cost`, `available`, `suspension`, `frame`, `tireTPI`, `wheelSize`, `weight`, `comfort_score`, `steepness_score`, `experience_score`, `speed_score`) VALUES
(9, 'B0001', 'Urban', '25', 'No', '', '', 0, 0, 0, 0, 0, 0, 0),
(10, 'B0002', 'Road', '30', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(11, 'B0003', 'Urban', '25', 'No', '', '', 0, 0, 0, 0, 0, 0, 0),
(12, 'B0004', 'Urban', '15', 'No', '', '', 0, 0, 0, 0, 0, 0, 0),
(13, 'B0005', 'Fixed Gear', '30', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(14, 'B0006', 'Urban', '25', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(15, 'B0007', 'Urban', '25', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(16, 'B0008', 'Mountain', '90', 'No', '', '', 0, 0, 0, 0, 0, 0, 0),
(17, 'B0009', 'Mountain', '90', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(18, 'B0010', 'Electric', '220', 'No', '', '', 0, 0, 0, 0, 0, 0, 0),
(25, 'B0011', 'Mountain', '75', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(29, 'B0012', 'Mountain', '75', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(30, 'B0013', 'Mountain', '75', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(31, 'B0014', 'Mountain', '75', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(32, 'B0015', 'Mountain', '75', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(33, 'B0016', 'Mountain', '75', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(34, 'B0017', 'Mountain', '75', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(35, 'B0018', 'Mountain', '75', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(36, 'B0019', 'Mountain', '75', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(37, 'B0020', 'Mountain', '75', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(38, 'B0021', 'Urban', '16', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(39, 'B0022', 'Urban', '16', 'No', '', '', 0, 0, 0, 0, 0, 0, 0),
(40, 'B0023', 'Urban', '16', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(41, 'B0024', 'Urban', '16', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(42, 'B0025', 'Urban', '16', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(43, 'B0026', 'Urban', '16', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(44, 'B0027', 'Urban', '16', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(45, 'B0028', 'Urban', '16', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(46, 'B0029', 'Urban', '16', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(47, 'B0030', 'Urban', '16', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(48, 'B0031', 'Urban', '16', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(49, 'B0032', 'Urban', '16', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(50, 'B0033', 'Electric', '160', 'No', '', '', 0, 0, 0, 0, 0, 0, 0),
(51, 'B0034', 'Electric', '160', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(52, 'B0035', 'Electric', '160', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(53, 'B0036', 'Fixed Gear', '30', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(54, 'B0037', 'Fixed Gear', '60', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(55, 'B0038', 'Fixed Gear', '60', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(56, 'B0039', 'Fixed Gear', '60', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(57, 'B0040', 'Fixed Gear', '60', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(58, 'B0041', 'Road', '40', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(59, 'B0042', 'Road', '40', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(60, 'B0043', 'Road', '40', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(61, 'B0044', 'Road', '25', 'No', '', '', 0, 0, 0, 0, 0, 0, 0),
(62, 'B0045', 'Road', '25', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(63, 'B0046', 'Electric', '110', 'No', '', '', 0, 0, 0, 0, 0, 0, 0),
(64, 'B0047', 'Electric', '110', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(65, 'B0048', 'Electric', '110', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(66, 'B0049', 'Electric', '110', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(67, 'B0050', 'Mountain', '90', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(68, 'B0051', 'Mountain', '90', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(69, 'B0052', 'Mountain', '90', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(70, 'B0053', 'Mountain', '90', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(71, 'B0054', 'Mountain', '110', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(72, 'B0055', 'Mountain', '110', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(73, 'B0056', 'Mountain', '110', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(74, 'B0057', 'Mountain', '110', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(75, 'B0058', 'Mountain', '110', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(76, 'B0059', 'Mountain', '55', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(77, 'B0060', 'Mountain', '55', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(78, 'B0061', 'Mountain', '55', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(79, 'B0062', 'Mountain', '55', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(80, 'B0063', 'Mountain', '55', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(81, 'B0049', 'Urban', '10', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(82, 'B0050', 'Urban', '10', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(83, 'B0051', 'Urban', '10', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(84, 'B0052', 'Urban', '10', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(85, 'B0053', 'Urban', '10', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(86, 'B0054', 'Urban', '10', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(87, 'B0064', 'Urban', '5', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(88, 'B0065', 'Urban', '5', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(89, 'B0066', 'Urban', '5', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(90, 'B0067', 'Urban', '5', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(91, 'B0068', 'Urban', '5', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(92, 'B0069', 'Road', '10', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(93, 'B0070', 'Road', '10', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(94, 'B0071', 'Road', '10', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(95, 'B0072', 'Fixed Gear', '20', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(96, 'B0073', 'Fixed Gear', '20', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(97, 'B0074', 'Fixed Gear', '20', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(98, 'B0075', 'Electric', '60', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(99, 'B0076', 'Electric', '60', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(100, 'B0077', 'Electric', '60', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(101, 'B0078', 'Electric', '80', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(102, 'B0079', 'Electric', '80', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(103, 'B0080', 'BMX', '30', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(104, 'B0081', 'BMX', '30', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(105, 'B0082', 'BMX', '30', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(106, 'B0083', 'BMX', '30', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(107, 'B0084', 'BMX', '30', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(108, 'B0085', 'BMX', '40', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(109, 'B0086', 'BMX', '40', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(110, 'B0087', 'BMX', '40', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(111, 'B0088', 'BMX', '40', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(112, 'B0089', 'BMX', '40', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(113, 'B0090', 'BMX', '40', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(114, 'B0091', 'BMX', '50', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0),
(115, 'B0092', 'BMX', '50', 'Yes', '', '', 0, 0, 0, 0, 0, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `rental`
--

CREATE TABLE `rental` (
  `id` int(11) NOT NULL,
  `bikeID` varchar(255) NOT NULL,
  `customer_id` varchar(255) NOT NULL,
  `hours` int(11) NOT NULL,
  `total_cost` int(11) NOT NULL,
  `start_time` date DEFAULT NULL,
  `planned_return_time` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `rental`
--

INSERT INTO `rental` (`id`, `bikeID`, `customer_id`, `hours`, `total_cost`, `start_time`, `planned_return_time`) VALUES
(42, 'B0046', 'C0002', 6, 660, '2023-02-22', '2023-02-22');

-- --------------------------------------------------------

--
-- Table structure for table `returnbike`
--

CREATE TABLE `returnbike` (
  `id` int(11) NOT NULL,
  `bike_id` varchar(255) NOT NULL,
  `customer_id` varchar(255) NOT NULL,
  `return_date` varchar(255) NOT NULL,
  `time_elapsed` int(11) NOT NULL,
  `fine` int(11) NOT NULL,
  `paid` int(255) NOT NULL,
  `model` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `returnbike`
--

INSERT INTO `returnbike` (`id`, `bike_id`, `customer_id`, `return_date`, `time_elapsed`, `fine`, `paid`, `model`) VALUES
(20, 'B0006', 'C0008', '18/2/2023', 7, 200, 0, 'Urban'),
(21, 'B0009', 'C0009', '20/2/2023', 54, 100, 4154, 'Mountain'),
(23, 'B0005', 'C0009', '21/2/2023', 75, 100, 1765, 'Fixed Gear'),
(26, 'B0041', 'C0008', '22/2/2023', 26, 200, 1016, 'Road'),
(27, 'B0080', 'C0008', '21/2/2023', 10, 0, 300, 'BMX'),
(28, 'B0037', 'C0002', '22/2/2023', 3, 0, 180, 'Fixed Gear');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `registration`
--
ALTER TABLE `registration`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `rental`
--
ALTER TABLE `rental`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `returnbike`
--
ALTER TABLE `returnbike`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `registration`
--
ALTER TABLE `registration`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=116;

--
-- AUTO_INCREMENT for table `rental`
--
ALTER TABLE `rental`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT for table `returnbike`
--
ALTER TABLE `returnbike`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
