-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Anamakine: localhost
-- Üretim Zamanı: 09 Kas 2023, 20:28:22
-- Sunucu sürümü: 8.0.31
-- PHP Sürümü: 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `turizm`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `hotel`
--

CREATE TABLE `hotel` (
  `hotel_id` int NOT NULL,
  `hotel_name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `hotel_city` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `hotel_district` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `hotel_features` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `hotel_address` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `hotel_mail` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `hotel_phone` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `hotel_star` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `hotel`
--

INSERT INTO `hotel` (`hotel_id`, `hotel_name`, `hotel_city`, `hotel_district`, `hotel_features`, `hotel_address`, `hotel_mail`, `hotel_phone`, `hotel_star`) VALUES
(24, 'Pegasus Otel', 'Antalya', 'Alanya', 'Ücretsiz Otopark ,Yüzme Havuzu', 'Avsallar, D400', 'resort@pegasoshotels.com.tr', '0850 281 0112', 5),
(25, 'Voyage Sorgun', 'Antalya', 'Manavgat', 'Yüzme Havuzu,Fitness Center,Hotel Concierge', 'Manavgat Sorgun Yöresi, Titreyengöl Mevkii / Antalya', 'sorgun@voyagehotel.com', '+90 242 756 93 00 ', 5),
(26, 'Boğaz Otel', 'Antalya', 'Manavgat', 'Yüzme Havuzu,Fitness Center,Hotel Concierge', 'Sorgun, Titreyengöl Cd. No:3, 07600 Manavgat/Antalya', 'info@hotelbogaz.com', '(0242) 756 96 91', 4),
(27, 'Four Seasons', 'İstanbul', 'Beşiktaş', 'Ücretsiz Otopark ,Yüzme Havuzu', 'ÇIRAĞAN CAD. NO. 28, ISTANBUL, BEŞIKTAŞ 34349 TURKEY', 'RESERVATIONS.ISTANBUL@FOURSEASONS.COM', '+90 (212) 381 40 00', 5),
(28, 'EN Otel', 'Eskişehir', 'Odunpazarı', 'Ücretsiz Otopark ,Yüzme Havuzu', 'Kurtuluş, Yunus Emre Cd. NO:88, 26100 Odunpazarı/Eskişehir', 'info@enotel.com', '(0222) 222 11 66', 3);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `reservation`
--

CREATE TABLE `reservation` (
  `reservation_id` int NOT NULL,
  `customer_name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `customer_phone` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `customer_mail` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `customer_number` int NOT NULL,
  `reservation_start_date` date NOT NULL,
  `reservation_end_date` date NOT NULL,
  `customer_price` double NOT NULL,
  `term_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `reservation`
--

INSERT INTO `reservation` (`reservation_id`, `customer_name`, `customer_phone`, `customer_mail`, `customer_number`, `reservation_start_date`, `reservation_end_date`, `customer_price`, `term_id`) VALUES
(34, 'Büşra Korkmaz', '05346522975', 'busrak@gmail.com', 1, '2024-02-02', '2024-02-07', 42720, 26),
(35, 'Sena Çalışkan', '05356122478', 'caliskansena1@gmail.com', 2, '2024-03-05', '2024-03-11', 42000, 29),
(36, 'Dilan Polat', '05554563212', 'enercii@gmail.com', 4, '2024-07-15', '2024-07-25', 396000, 11),
(37, 'Ahmet Turan', '05312256339', 'turancccahmet@gmail.com', 1, '2024-06-05', '2024-06-09', 82500, 27);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `room`
--

CREATE TABLE `room` (
  `room_id` int NOT NULL,
  `hotel_id` int NOT NULL,
  `room_type` enum('Single','Double','Suit') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `room_stock` int NOT NULL,
  `room_feature` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `room_size` int NOT NULL,
  `room_bed_count` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `room`
--

INSERT INTO `room` (`room_id`, `hotel_id`, `room_type`, `room_stock`, `room_feature`, `room_size`, `room_bed_count`) VALUES
(35, 24, 'Suit', 48, 'Televizyon,Minibar,Oyun Konsolu,Kasa,Projeksiyon', 75, 4),
(38, 26, 'Double', 22, 'Televizyon,Minibar,Oyun Konsolu,Kasa', 22, 5),
(39, 25, 'Double', 33, 'Televizyon,Minibar,Kasa', 65, 5),
(46, 25, 'Suit', 15, 'Televizyon,Minibar,Oyun Konsolu,Kasa,Projeksiyon', 65, 3),
(47, 25, 'Single', 8, 'Televizyon,Minibar,Oyun Konsolu', 35, 1),
(48, 28, 'Double', 21, 'Televizyon,Minibar,Kasa', 45, 3);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `term`
--

CREATE TABLE `term` (
  `term_id` int NOT NULL,
  `hotel_id` int NOT NULL,
  `room_id` int NOT NULL,
  `room_board_type` enum('Yarım Pansiyon','Tam Pansiyon','Oda Kahvaltı','Her şey Dahil','Ultra Her şey Dahil','Sadece Yatak','Alkol Hariç Full credit') COLLATE utf8mb4_general_ci NOT NULL,
  `term_start_date` date NOT NULL,
  `term_end_date` date NOT NULL,
  `adult_price` int NOT NULL,
  `children_price` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `term`
--

INSERT INTO `term` (`term_id`, `hotel_id`, `room_id`, `room_board_type`, `term_start_date`, `term_end_date`, `adult_price`, `children_price`) VALUES
(11, 24, 35, 'Tam Pansiyon', '2024-06-15', '2024-08-15', 10000, 8000),
(12, 24, 35, 'Tam Pansiyon', '2024-08-16', '2025-02-15', 7500, 6000),
(13, 24, 35, 'Tam Pansiyon', '2023-07-11', '2024-02-15', 6500, 5200),
(14, 24, 35, 'Tam Pansiyon', '2024-02-16', '2024-06-14', 6000, 4800),
(26, 25, 47, 'Yarım Pansiyon', '2024-01-01', '2024-05-15', 7120, 5696),
(27, 25, 47, 'Yarım Pansiyon', '2024-05-16', '2024-07-20', 16500, 13200),
(28, 25, 47, 'Tam Pansiyon', '2024-07-16', '2025-01-01', 9510, 7608),
(29, 28, 48, 'Tam Pansiyon', '2024-01-01', '2024-05-01', 3000, 2400),
(30, 28, 48, 'Tam Pansiyon', '2024-05-02', '2024-08-20', 5000, 4000),
(31, 28, 48, 'Tam Pansiyon', '2024-08-21', '2025-01-01', 2444, 1955);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `user`
--

CREATE TABLE `user` (
  `user_id` int NOT NULL,
  `user_name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `user_uname` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `user_pass` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `user_type` enum('admin','employee') COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `user`
--

INSERT INTO `user` (`user_id`, `user_name`, `user_uname`, `user_pass`, `user_type`) VALUES
(3, 'Patika Admin', 'admin', '1234', 'admin'),
(12, 'Acente1', 'acente', '1234', 'employee');

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `hotel`
--
ALTER TABLE `hotel`
  ADD PRIMARY KEY (`hotel_id`);

--
-- Tablo için indeksler `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`reservation_id`);

--
-- Tablo için indeksler `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`room_id`);

--
-- Tablo için indeksler `term`
--
ALTER TABLE `term`
  ADD PRIMARY KEY (`term_id`);

--
-- Tablo için indeksler `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `hotel`
--
ALTER TABLE `hotel`
  MODIFY `hotel_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- Tablo için AUTO_INCREMENT değeri `reservation`
--
ALTER TABLE `reservation`
  MODIFY `reservation_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- Tablo için AUTO_INCREMENT değeri `room`
--
ALTER TABLE `room`
  MODIFY `room_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;

--
-- Tablo için AUTO_INCREMENT değeri `term`
--
ALTER TABLE `term`
  MODIFY `term_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- Tablo için AUTO_INCREMENT değeri `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
