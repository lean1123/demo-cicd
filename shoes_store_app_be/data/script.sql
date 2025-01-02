-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               11.3.2-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for shoes_store_app
CREATE DATABASE IF NOT EXISTS `shoes_store_app` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `shoes_store_app`;

-- Dumping structure for table shoes_store_app.accounts
CREATE TABLE IF NOT EXISTS `accounts` (
  `email` varchar(255) DEFAULT NULL,
  `id` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKe4w4av1wrhanry7t6mxt42nou` (`user_id`),
  CONSTRAINT `FKnjuop33mo69pd79ctplkck40n` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table shoes_store_app.accounts: ~4 rows (approximately)
INSERT INTO `accounts` (`email`, `id`, `password`, `user_id`) VALUES
	('tokiejame@gmail.com', '020c8f5d-6197-4b1e-a446-7b3dc6a24047', '$2a$10$yHRyVEZgK0azY/xa7aA70e9uUXEJGf0odxDH6sHHcyBVag3lNMl2K', '8effe8ff-b81d-4768-badf-12d0d89807d5'),
	('21136181.an@student.iuh.edu.vn', '72509765-a3dc-4a8a-b82e-51771efc4cc3', '$2a$10$DIAgPp.xIjDaQ9FlS4ELxuEcrYyjo1uduxnvttgRI5oCb4cwZxyZm', 'a28a2405-86b8-48e1-bd61-50b0e2c72d23'),
	('lean4@gmail.com', 'a0c3aaab-ef0b-491d-8d98-8a6270410310', '$2a$10$tP1HLurQkJWnA9T44aX1ruBHAV2WgtRwihPWEHQ4vdIn3z.wjyFPq', '08cbba31-427d-4a09-9a6c-f5e7e3ba0e19'),
	('abc@gmail.com', 'abb003fb-6e5b-46a3-9df0-4bc10fc58d7a', '$2a$10$E3GhEWpfsjNriDg84aiI4uEctY0TJN3EaBAEjsomtPmlqtNxzO842', '68bc814f-807b-4eec-aa46-15df83c45c48'),
	('huydemo@gmail.com', 'c94a4524-4bbd-40e8-b112-78f9c04cd09c', '$2a$10$VG0W2alZDEcJ0J0egbEddO5L2Sw4XE/t0Xy/u96ksYhAsjajyRPnG', '976a1187-a95c-41a5-b813-b0a3b140be2e');

-- Dumping structure for table shoes_store_app.address
CREATE TABLE IF NOT EXISTS `address` (
  `city` varchar(255) NOT NULL,
  `district` varchar(255) NOT NULL,
  `homeNumber` varchar(255) NOT NULL,
  `id` varchar(255) NOT NULL,
  `street` varchar(255) NOT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `ward` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6i66ijb8twgcqtetl8eeeed6v` (`user_id`),
  CONSTRAINT `FK6i66ijb8twgcqtetl8eeeed6v` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table shoes_store_app.address: ~1 rows (approximately)
INSERT INTO `address` (`city`, `district`, `homeNumber`, `id`, `street`, `user_id`, `ward`) VALUES
	('TP HCM', 'Thủ Đức', '183/22', 'address1', 'Hiệp Bình', 'd0e56e58-9962-426f-8084-cadb76562fff', 'Hiệp Bình Chánh'),
	('Hồ Chí Minh', 'Thủ Đức', '182/16/7', 'd15cb9e4-3357-40f7-92fd-96c249e7d752', 'Hiệp Bình', 'a28a2405-86b8-48e1-bd61-50b0e2c72d23', 'Hiệp Bình Chánh');

-- Dumping structure for table shoes_store_app.brands
CREATE TABLE IF NOT EXISTS `brands` (
  `avatar` varchar(255) DEFAULT NULL,
  `brandName` varchar(255) DEFAULT NULL,
  `id` varchar(255) NOT NULL,
  `post_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKiwaf6l85vcqwdwetnjsh6fw5u` (`post_id`),
  CONSTRAINT `FKprjkxio4e20sumvu1t8a7dadt` FOREIGN KEY (`post_id`) REFERENCES `posts` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table shoes_store_app.brands: ~0 rows (approximately)
INSERT INTO `brands` (`avatar`, `brandName`, `id`, `post_id`) VALUES
	('http://res.cloudinary.com/dr7uxdi9o/image/upload/v1732939777/ShoesShopApp/Brand/Gi%C3%A0y%20Bitis%20Hunter.jpg', 'Giày Bitis Hunter', '06252487-5fc0-464b-86a7-64d8c69d0700', NULL);

-- Dumping structure for table shoes_store_app.cart
CREATE TABLE IF NOT EXISTS `cart` (
  `id` varchar(255) NOT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK94tjs0klq3ti3pbnorndv7ecu` (`user_id`),
  CONSTRAINT `FK7toxf0y2a3mewe83du44h29fi` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table shoes_store_app.cart: ~2 rows (approximately)
INSERT INTO `cart` (`id`, `user_id`) VALUES
	('3ce156f6-54b3-45ba-b988-7d36f92698b1', '08cbba31-427d-4a09-9a6c-f5e7e3ba0e19'),
	('9e69ab36-8df8-4ab9-a593-83db4b2ec9c8', 'a28a2405-86b8-48e1-bd61-50b0e2c72d23'),
	('8e048db6-e147-4ecf-9a4a-9e377283d8e2', 'd0e56e58-9962-426f-8084-cadb76562fff');

-- Dumping structure for table shoes_store_app.cart_details
CREATE TABLE IF NOT EXISTS `cart_details` (
  `quantity` int(11) NOT NULL,
  `cart_id` varchar(255) NOT NULL,
  `product_item_id` varchar(255) NOT NULL,
  PRIMARY KEY (`cart_id`,`product_item_id`),
  KEY `FKtcvcn9ub9sucd6u14qwb8qajo` (`product_item_id`),
  CONSTRAINT `FK5b6y7dqchjh6hlptn1v36foq3` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`),
  CONSTRAINT `FKtcvcn9ub9sucd6u14qwb8qajo` FOREIGN KEY (`product_item_id`) REFERENCES `product_items` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table shoes_store_app.cart_details: ~10 rows (approximately)
INSERT INTO `cart_details` (`quantity`, `cart_id`, `product_item_id`) VALUES
	(1, '3ce156f6-54b3-45ba-b988-7d36f92698b1', '13004bab-94b5-41b7-ab2c-d2c067d8b5e9'),
	(1, '3ce156f6-54b3-45ba-b988-7d36f92698b1', '21d5bd7d-3d80-477b-9e64-699dbb850ad2'),
	(1, '3ce156f6-54b3-45ba-b988-7d36f92698b1', '52ad2930-b4a3-48ff-ac91-2c4551810059'),
	(1, '3ce156f6-54b3-45ba-b988-7d36f92698b1', '6f51e7bc-4b41-4830-af53-940b138d67c5'),
	(1, '3ce156f6-54b3-45ba-b988-7d36f92698b1', '71e464c1-6d5c-4707-993b-c7792f26205f'),
	(1, '9e69ab36-8df8-4ab9-a593-83db4b2ec9c8', '13004bab-94b5-41b7-ab2c-d2c067d8b5e9'),
	(1, '9e69ab36-8df8-4ab9-a593-83db4b2ec9c8', '21d5bd7d-3d80-477b-9e64-699dbb850ad2'),
	(1, '9e69ab36-8df8-4ab9-a593-83db4b2ec9c8', '487ad0b9-8f19-4439-9956-078575cbaf55'),
	(3, '9e69ab36-8df8-4ab9-a593-83db4b2ec9c8', '6f51e7bc-4b41-4830-af53-940b138d67c5'),
	(3, '9e69ab36-8df8-4ab9-a593-83db4b2ec9c8', '71e464c1-6d5c-4707-993b-c7792f26205f');

-- Dumping structure for table shoes_store_app.categories
CREATE TABLE IF NOT EXISTS `categories` (
  `description` varchar(255) DEFAULT NULL,
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table shoes_store_app.categories: ~0 rows (approximately)
INSERT INTO `categories` (`description`, `id`, `name`) VALUES
	('Description', '51369832-1bbd-49f4-8efe-0cda56c125bf', 'Giày Sneaker');

-- Dumping structure for table shoes_store_app.feedbacks
CREATE TABLE IF NOT EXISTS `feedbacks` (
  `rating` float NOT NULL,
  `createdDate` datetime(6) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `id` varchar(255) NOT NULL,
  `order_id` varchar(255) DEFAULT NULL,
  `product_id` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbdhoov2mv332ks2m84owt5tv3` (`order_id`),
  KEY `FKti2ywtwc29ys1i591rmmaveyc` (`product_id`),
  KEY `FK312drfl5lquu37mu4trk8jkwx` (`user_id`),
  CONSTRAINT `FK312drfl5lquu37mu4trk8jkwx` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKbdhoov2mv332ks2m84owt5tv3` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `FKti2ywtwc29ys1i591rmmaveyc` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table shoes_store_app.feedbacks: ~0 rows (approximately)

-- Dumping structure for table shoes_store_app.feedback_list_detail_images
CREATE TABLE IF NOT EXISTS `feedback_list_detail_images` (
  `feedback_id` varchar(255) NOT NULL,
  `listDetailImages` varchar(255) DEFAULT NULL,
  KEY `FKtqnta6oq308ncjgvh9603wofn` (`feedback_id`),
  CONSTRAINT `FKtqnta6oq308ncjgvh9603wofn` FOREIGN KEY (`feedback_id`) REFERENCES `feedbacks` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table shoes_store_app.feedback_list_detail_images: ~0 rows (approximately)

-- Dumping structure for table shoes_store_app.orders
CREATE TABLE IF NOT EXISTS `orders` (
  `totalPrice` double NOT NULL,
  `createdDate` datetime(6) DEFAULT NULL,
  `address_id` varchar(255) DEFAULT NULL,
  `id` varchar(255) NOT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `orderStatus` enum('ACCEPTED','CANCELLED','PENDING','REJECTED','SHIPPING','SUCCESS') DEFAULT NULL,
  `paymentMethod` enum('CASH','VNPAY') DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKf5464gxwc32ongdvka2rtvw96` (`address_id`),
  KEY `FK32ql8ubntj5uh44ph9659tiih` (`user_id`),
  CONSTRAINT `FK32ql8ubntj5uh44ph9659tiih` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKf5464gxwc32ongdvka2rtvw96` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table shoes_store_app.orders: ~18 rows (approximately)
INSERT INTO `orders` (`totalPrice`, `createdDate`, `address_id`, `id`, `user_id`, `orderStatus`, `paymentMethod`) VALUES
	(100000, '2024-11-30 13:58:20.641959', 'address1', '3e10afeb-8790-4fa5-bf55-29768468135a', 'd0e56e58-9962-426f-8084-cadb76562fff', 'PENDING', 'CASH'),
	(100000, '2024-12-03 11:23:08.522420', 'd15cb9e4-3357-40f7-92fd-96c249e7d752', '3ea425ed-4a32-426c-8d57-3c66b81e1974', 'a28a2405-86b8-48e1-bd61-50b0e2c72d23', 'PENDING', 'CASH'),
	(100000, '2024-12-03 11:33:27.121114', 'd15cb9e4-3357-40f7-92fd-96c249e7d752', '5884ea73-c64e-4fc0-86ba-f250362187bd', 'a28a2405-86b8-48e1-bd61-50b0e2c72d23', 'PENDING', 'CASH'),
	(100000, '2024-12-03 11:38:26.142410', 'd15cb9e4-3357-40f7-92fd-96c249e7d752', '63398cbf-9cd2-4161-b79b-7e01fcdec2f3', 'a28a2405-86b8-48e1-bd61-50b0e2c72d23', 'PENDING', 'CASH'),
	(200000, '2024-11-30 17:13:40.141600', 'address1', '64dfb3c3-ce21-44ff-9957-b5543245793d', '08cbba31-427d-4a09-9a6c-f5e7e3ba0e19', 'PENDING', 'CASH'),
	(300000, '2024-12-03 11:57:03.039747', 'd15cb9e4-3357-40f7-92fd-96c249e7d752', '6bbea360-0f49-410f-a523-59ae9ea8692e', 'a28a2405-86b8-48e1-bd61-50b0e2c72d23', 'PENDING', 'CASH'),
	(100000, '2024-12-03 11:35:41.589338', 'd15cb9e4-3357-40f7-92fd-96c249e7d752', '74b1045d-c86a-4998-9022-bc37e822a411', 'a28a2405-86b8-48e1-bd61-50b0e2c72d23', 'PENDING', 'CASH'),
	(100000, '2024-11-30 12:40:14.119917', 'address1', '80d94472-c75a-4130-8d60-8ff0ffba4a1e', '08cbba31-427d-4a09-9a6c-f5e7e3ba0e19', 'PENDING', 'CASH'),
	(100000, '2024-11-30 11:59:48.616216', 'address1', '8a68f757-c1a1-4d49-808f-1e02b86b1ab6', 'd0e56e58-9962-426f-8084-cadb76562fff', 'PENDING', 'CASH'),
	(200000, '2024-12-02 16:31:02.876038', 'd15cb9e4-3357-40f7-92fd-96c249e7d752', '8cba99a5-8028-447e-885e-dd808a1dceaa', 'a28a2405-86b8-48e1-bd61-50b0e2c72d23', 'PENDING', 'CASH'),
	(200000, '2024-12-03 11:43:59.084748', 'd15cb9e4-3357-40f7-92fd-96c249e7d752', '97047dc8-721e-4da9-b08e-c9acfcb2931b', 'a28a2405-86b8-48e1-bd61-50b0e2c72d23', 'PENDING', 'CASH'),
	(400000, '2024-11-30 15:32:14.147716', 'address1', '97d018ff-c950-417a-8356-5f9f6b1ab493', 'a28a2405-86b8-48e1-bd61-50b0e2c72d23', 'PENDING', 'CASH'),
	(100000, '2024-12-03 11:30:32.632435', 'd15cb9e4-3357-40f7-92fd-96c249e7d752', '9be1c4d2-e60b-41cf-b11f-fdfe7cd771d1', 'a28a2405-86b8-48e1-bd61-50b0e2c72d23', 'PENDING', 'CASH'),
	(100000, '2024-12-03 11:27:56.247004', 'd15cb9e4-3357-40f7-92fd-96c249e7d752', 'b2093f0c-0a51-447f-9699-3138c98d4f14', 'a28a2405-86b8-48e1-bd61-50b0e2c72d23', 'PENDING', 'CASH'),
	(200000, '2024-11-30 11:51:17.662459', 'address1', 'bba4c353-7865-4588-b785-ddba7b2d5fc8', 'd0e56e58-9962-426f-8084-cadb76562fff', 'PENDING', 'CASH'),
	(100000, '2024-12-03 18:36:59.364948', 'd15cb9e4-3357-40f7-92fd-96c249e7d752', 'cc0e6253-c54d-4127-9699-84b6335b78af', 'a28a2405-86b8-48e1-bd61-50b0e2c72d23', 'PENDING', 'CASH'),
	(200000, '2024-11-30 16:25:31.835683', 'address1', 'd16b04b9-c737-4d61-8a88-0ce393abb677', 'a28a2405-86b8-48e1-bd61-50b0e2c72d23', 'PENDING', 'CASH'),
	(800000, '2024-12-02 22:49:05.279086', 'd15cb9e4-3357-40f7-92fd-96c249e7d752', 'fd2053b0-d12b-4506-86f7-45f8a4558a97', 'a28a2405-86b8-48e1-bd61-50b0e2c72d23', 'PENDING', 'CASH');

-- Dumping structure for table shoes_store_app.order_details
CREATE TABLE IF NOT EXISTS `order_details` (
  `pricePerItem` double NOT NULL,
  `quantity` int(11) NOT NULL,
  `id` varchar(255) NOT NULL,
  `order_id` varchar(255) DEFAULT NULL,
  `product_item_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjyu2qbqt8gnvno9oe9j2s2ldk` (`order_id`),
  KEY `FK3xc421q7c1dmafnfeouwtbs6` (`product_item_id`),
  CONSTRAINT `FK3xc421q7c1dmafnfeouwtbs6` FOREIGN KEY (`product_item_id`) REFERENCES `product_items` (`id`),
  CONSTRAINT `FKjyu2qbqt8gnvno9oe9j2s2ldk` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table shoes_store_app.order_details: ~21 rows (approximately)
INSERT INTO `order_details` (`pricePerItem`, `quantity`, `id`, `order_id`, `product_item_id`) VALUES
	(100000, 1, '020d6938-1661-4595-9f13-5c7e02b61e3f', '80d94472-c75a-4130-8d60-8ff0ffba4a1e', '71e464c1-6d5c-4707-993b-c7792f26205f'),
	(100000, 1, '038a0ce1-0396-4e74-b682-3600c3e8a8a3', '3e10afeb-8790-4fa5-bf55-29768468135a', '13004bab-94b5-41b7-ab2c-d2c067d8b5e9'),
	(100000, 2, '243a6a05-364b-429d-b764-20452d5e1a7b', 'bba4c353-7865-4588-b785-ddba7b2d5fc8', '71e464c1-6d5c-4707-993b-c7792f26205f'),
	(100000, 6, '2d7fc996-d40f-459c-9bdb-9898c8a5ff57', 'fd2053b0-d12b-4506-86f7-45f8a4558a97', '487ad0b9-8f19-4439-9956-078575cbaf55'),
	(100000, 2, '30ba7812-faaf-44c2-99f7-eef2e637b241', '64dfb3c3-ce21-44ff-9957-b5543245793d', '21d5bd7d-3d80-477b-9e64-699dbb850ad2'),
	(100000, 2, '3c4b9f22-f859-4bfb-b9e0-5fa879bbce60', '97d018ff-c950-417a-8356-5f9f6b1ab493', '21d5bd7d-3d80-477b-9e64-699dbb850ad2'),
	(100000, 1, '4efb0431-4c1a-44ae-95d6-fa02d5711015', 'fd2053b0-d12b-4506-86f7-45f8a4558a97', '13004bab-94b5-41b7-ab2c-d2c067d8b5e9'),
	(100000, 2, '566ea5ac-32f1-475a-897c-3b8620023730', '97d018ff-c950-417a-8356-5f9f6b1ab493', '13004bab-94b5-41b7-ab2c-d2c067d8b5e9'),
	(100000, 1, '5aaba8c3-1244-40b0-a004-7cca8f75043a', '9be1c4d2-e60b-41cf-b11f-fdfe7cd771d1', '21d5bd7d-3d80-477b-9e64-699dbb850ad2'),
	(100000, 2, '72ca9e52-313d-45e6-8836-70ea2b95fa14', 'd16b04b9-c737-4d61-8a88-0ce393abb677', '21d5bd7d-3d80-477b-9e64-699dbb850ad2'),
	(100000, 3, '79563b5c-2d08-44f8-be2a-d1ff4420f2e3', '6bbea360-0f49-410f-a523-59ae9ea8692e', '71e464c1-6d5c-4707-993b-c7792f26205f'),
	(100000, 1, '9d454966-27e3-46a5-8c6f-5b63fcce0449', 'cc0e6253-c54d-4127-9699-84b6335b78af', '13004bab-94b5-41b7-ab2c-d2c067d8b5e9'),
	(100000, 1, 'b171e0ca-8d6d-4650-861f-84e8cf22400a', 'b2093f0c-0a51-447f-9699-3138c98d4f14', '21d5bd7d-3d80-477b-9e64-699dbb850ad2'),
	(100000, 2, 'bb87f1f8-3c76-434c-815b-2f5c1b1de980', '97047dc8-721e-4da9-b08e-c9acfcb2931b', '21d5bd7d-3d80-477b-9e64-699dbb850ad2'),
	(100000, 1, 'cd2d6ae0-d20b-44e4-9d03-a5291e8519cb', '74b1045d-c86a-4998-9022-bc37e822a411', '21d5bd7d-3d80-477b-9e64-699dbb850ad2'),
	(100000, 1, 'd92685fd-034e-4769-b6bb-6136de41b7c5', '63398cbf-9cd2-4161-b79b-7e01fcdec2f3', '21d5bd7d-3d80-477b-9e64-699dbb850ad2'),
	(100000, 1, 'da668be9-5764-42ba-8248-abc0471f65da', 'fd2053b0-d12b-4506-86f7-45f8a4558a97', '21d5bd7d-3d80-477b-9e64-699dbb850ad2'),
	(100000, 1, 'dedb4573-93be-4fc3-a4da-16c7ca07b28c', '3ea425ed-4a32-426c-8d57-3c66b81e1974', '21d5bd7d-3d80-477b-9e64-699dbb850ad2'),
	(100000, 1, 'e4387d31-7b45-403e-a53f-d6d8aa03b0dc', '8a68f757-c1a1-4d49-808f-1e02b86b1ab6', '487ad0b9-8f19-4439-9956-078575cbaf55'),
	(100000, 2, 'eb32df34-a1e4-4e06-ad4d-510277665e79', '8cba99a5-8028-447e-885e-dd808a1dceaa', '13004bab-94b5-41b7-ab2c-d2c067d8b5e9'),
	(100000, 1, 'fa7804de-f8ca-4b48-aa51-3c5cfaad8ba9', '5884ea73-c64e-4fc0-86ba-f250362187bd', '21d5bd7d-3d80-477b-9e64-699dbb850ad2');

-- Dumping structure for table shoes_store_app.payment
CREATE TABLE IF NOT EXISTS `payment` (
  `amount` double NOT NULL,
  `paymentDate` datetime(6) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `id` varchar(255) NOT NULL,
  `order_id` varchar(255) DEFAULT NULL,
  `transactionId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKmf7n8wo2rwrxsd6f3t9ub2mep` (`order_id`),
  CONSTRAINT `FKlouu98csyullos9k25tbpk4va` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table shoes_store_app.payment: ~0 rows (approximately)

-- Dumping structure for table shoes_store_app.posts
CREATE TABLE IF NOT EXISTS `posts` (
  `author` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `content` longtext DEFAULT NULL,
  `createdDate` varchar(255) DEFAULT NULL,
  `id` varchar(255) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table shoes_store_app.posts: ~0 rows (approximately)

-- Dumping structure for table shoes_store_app.products
CREATE TABLE IF NOT EXISTS `products` (
  `rating` double NOT NULL,
  `createdDate` datetime(6) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `category_id` varchar(255) DEFAULT NULL,
  `collection_id` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `gender` enum('MEN','UNISEX','WOMAN') DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKog2rp4qthbtt2lfyhfo32lsw9` (`category_id`),
  KEY `FKpaipq9uqo5vdql7d6xfjdr9fx` (`collection_id`),
  CONSTRAINT `FKog2rp4qthbtt2lfyhfo32lsw9` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`),
  CONSTRAINT `FKpaipq9uqo5vdql7d6xfjdr9fx` FOREIGN KEY (`collection_id`) REFERENCES `product_collection` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table shoes_store_app.products: ~1 rows (approximately)
INSERT INTO `products` (`rating`, `createdDate`, `avatar`, `category_id`, `collection_id`, `description`, `id`, `name`, `gender`) VALUES
	(0, NULL, NULL, '51369832-1bbd-49f4-8efe-0cda56c125bf', NULL, 'Sản Phẩm Tốt Nhất 2024', 'a8723df1-d390-4b45-89f6-c5eb17d26d10', 'Giày Thể Thao Bittis', NULL),
	(0, '2024-12-02 23:24:18.481389', NULL, '51369832-1bbd-49f4-8efe-0cda56c125bf', '85488647-4764-487d-b656-87c35662db60', 'Giay Sneaker co thap', 'aa3535e8-df3a-4b10-90ba-65f7256fdd01', 'Giay Sneaker co thap', 'MEN');

-- Dumping structure for table shoes_store_app.product_collection
CREATE TABLE IF NOT EXISTS `product_collection` (
  `brand_id` varchar(255) DEFAULT NULL,
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcyfkl19xx04tlx3vo92fbtygg` (`brand_id`),
  CONSTRAINT `FKcyfkl19xx04tlx3vo92fbtygg` FOREIGN KEY (`brand_id`) REFERENCES `brands` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table shoes_store_app.product_collection: ~0 rows (approximately)
INSERT INTO `product_collection` (`brand_id`, `id`, `name`) VALUES
	('06252487-5fc0-464b-86a7-64d8c69d0700', '85488647-4764-487d-b656-87c35662db60', 'Collection 2024');

-- Dumping structure for table shoes_store_app.product_items
CREATE TABLE IF NOT EXISTS `product_items` (
  `price` double NOT NULL,
  `quantity` int(11) NOT NULL,
  `id` varchar(255) NOT NULL,
  `product_id` varchar(255) DEFAULT NULL,
  `size` varchar(255) DEFAULT NULL,
  `color` enum('BLACK','BLUE','BROWN','GRAY','GREEN','MULTICOLOR','ORANGE','PINK','PURPLE','RED','SILVER','WHITE','YELLOW') DEFAULT NULL,
  `status` enum('INSTOCK','OUTOFSTOCK') DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3gegsnjqr0sr3ipk5vu75t21r` (`product_id`),
  CONSTRAINT `FK3gegsnjqr0sr3ipk5vu75t21r` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table shoes_store_app.product_items: ~8 rows (approximately)
INSERT INTO `product_items` (`price`, `quantity`, `id`, `product_id`, `size`, `color`, `status`) VALUES
	(100000, 18, '13004bab-94b5-41b7-ab2c-d2c067d8b5e9', 'a8723df1-d390-4b45-89f6-c5eb17d26d10', '36', 'RED', 'INSTOCK'),
	(100000, 10, '21d5bd7d-3d80-477b-9e64-699dbb850ad2', 'a8723df1-d390-4b45-89f6-c5eb17d26d10', '35', 'RED', 'INSTOCK'),
	(100000, 18, '487ad0b9-8f19-4439-9956-078575cbaf55', 'a8723df1-d390-4b45-89f6-c5eb17d26d10', '36', 'GREEN', 'INSTOCK'),
	(100000, 10, '52ad2930-b4a3-48ff-ac91-2c4551810059', 'aa3535e8-df3a-4b10-90ba-65f7256fdd01', '35', 'WHITE', 'INSTOCK'),
	(100000, 10, '65d749cf-567e-47cb-9a5b-7e4433b28bd9', 'aa3535e8-df3a-4b10-90ba-65f7256fdd01', '35', 'YELLOW', 'INSTOCK'),
	(100000, 10, '6f51e7bc-4b41-4830-af53-940b138d67c5', 'aa3535e8-df3a-4b10-90ba-65f7256fdd01', '36', 'WHITE', 'INSTOCK'),
	(100000, 19, '71e464c1-6d5c-4707-993b-c7792f26205f', 'a8723df1-d390-4b45-89f6-c5eb17d26d10', '35', 'GREEN', 'INSTOCK'),
	(100000, 10, 'bc4fdc73-5880-4113-90aa-4ea3cfd16375', 'aa3535e8-df3a-4b10-90ba-65f7256fdd01', '36', 'YELLOW', 'INSTOCK');

-- Dumping structure for table shoes_store_app.product_item_detail_images
CREATE TABLE IF NOT EXISTS `product_item_detail_images` (
  `listDetailImages` varchar(255) DEFAULT NULL,
  `product_item_id` varchar(255) NOT NULL,
  KEY `FKrsr2po80akcswh00wjo5o91j4` (`product_item_id`),
  CONSTRAINT `FKrsr2po80akcswh00wjo5o91j4` FOREIGN KEY (`product_item_id`) REFERENCES `product_items` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table shoes_store_app.product_item_detail_images: ~20 rows (approximately)
INSERT INTO `product_item_detail_images` (`listDetailImages`, `product_item_id`) VALUES
	('http://res.cloudinary.com/dr7uxdi9o/image/upload/v1732939948/ShoesShopApp/Product-Item/null_9eb759ec.webp', '13004bab-94b5-41b7-ab2c-d2c067d8b5e9'),
	('http://res.cloudinary.com/dr7uxdi9o/image/upload/v1732939950/ShoesShopApp/Product-Item/null_608934b3.webp', '13004bab-94b5-41b7-ab2c-d2c067d8b5e9'),
	('http://res.cloudinary.com/dr7uxdi9o/image/upload/v1732939953/ShoesShopApp/Product-Item/null_3af830e2.webp', '13004bab-94b5-41b7-ab2c-d2c067d8b5e9'),
	('http://res.cloudinary.com/dr7uxdi9o/image/upload/v1732939962/ShoesShopApp/Product-Item/null_efefbc06.webp', '487ad0b9-8f19-4439-9956-078575cbaf55'),
	('http://res.cloudinary.com/dr7uxdi9o/image/upload/v1732939964/ShoesShopApp/Product-Item/null_7616ecb1.webp', '487ad0b9-8f19-4439-9956-078575cbaf55'),
	('http://res.cloudinary.com/dr7uxdi9o/image/upload/v1732939967/ShoesShopApp/Product-Item/null_6f9bb6d5.webp', '487ad0b9-8f19-4439-9956-078575cbaf55'),
	('http://res.cloudinary.com/dr7uxdi9o/image/upload/v1732940005/ShoesShopApp/Product-Item/null_7f413009.webp', '21d5bd7d-3d80-477b-9e64-699dbb850ad2'),
	('http://res.cloudinary.com/dr7uxdi9o/image/upload/v1732940006/ShoesShopApp/Product-Item/null_6f038dde.webp', '21d5bd7d-3d80-477b-9e64-699dbb850ad2'),
	('http://res.cloudinary.com/dr7uxdi9o/image/upload/v1732940009/ShoesShopApp/Product-Item/null_0013d93b.webp', '21d5bd7d-3d80-477b-9e64-699dbb850ad2'),
	('http://res.cloudinary.com/dr7uxdi9o/image/upload/v1732940014/ShoesShopApp/Product-Item/null_a61d5d1a.webp', '71e464c1-6d5c-4707-993b-c7792f26205f'),
	('http://res.cloudinary.com/dr7uxdi9o/image/upload/v1732940016/ShoesShopApp/Product-Item/null_16beae1c.webp', '71e464c1-6d5c-4707-993b-c7792f26205f'),
	('http://res.cloudinary.com/dr7uxdi9o/image/upload/v1732940018/ShoesShopApp/Product-Item/null_b4d67a70.webp', '71e464c1-6d5c-4707-993b-c7792f26205f'),
	('http://res.cloudinary.com/dr7uxdi9o/image/upload/v1733156726/ShoesShopApp/Product-Item/aa3535e8-df3a-4b10-90ba-65f7256fdd01_580be726.webp', 'bc4fdc73-5880-4113-90aa-4ea3cfd16375'),
	('http://res.cloudinary.com/dr7uxdi9o/image/upload/v1733156728/ShoesShopApp/Product-Item/aa3535e8-df3a-4b10-90ba-65f7256fdd01_1360c452.webp', 'bc4fdc73-5880-4113-90aa-4ea3cfd16375'),
	('http://res.cloudinary.com/dr7uxdi9o/image/upload/v1733156746/ShoesShopApp/Product-Item/aa3535e8-df3a-4b10-90ba-65f7256fdd01_f31ca5f9.webp', '6f51e7bc-4b41-4830-af53-940b138d67c5'),
	('http://res.cloudinary.com/dr7uxdi9o/image/upload/v1733156748/ShoesShopApp/Product-Item/aa3535e8-df3a-4b10-90ba-65f7256fdd01_98188f56.webp', '6f51e7bc-4b41-4830-af53-940b138d67c5'),
	('http://res.cloudinary.com/dr7uxdi9o/image/upload/v1733156772/ShoesShopApp/Product-Item/aa3535e8-df3a-4b10-90ba-65f7256fdd01_ba65d3be.webp', '52ad2930-b4a3-48ff-ac91-2c4551810059'),
	('http://res.cloudinary.com/dr7uxdi9o/image/upload/v1733156774/ShoesShopApp/Product-Item/aa3535e8-df3a-4b10-90ba-65f7256fdd01_f757effd.webp', '52ad2930-b4a3-48ff-ac91-2c4551810059'),
	('http://res.cloudinary.com/dr7uxdi9o/image/upload/v1733156779/ShoesShopApp/Product-Item/aa3535e8-df3a-4b10-90ba-65f7256fdd01_f96113a6.webp', '65d749cf-567e-47cb-9a5b-7e4433b28bd9'),
	('http://res.cloudinary.com/dr7uxdi9o/image/upload/v1733156783/ShoesShopApp/Product-Item/aa3535e8-df3a-4b10-90ba-65f7256fdd01_5198b654.webp', '65d749cf-567e-47cb-9a5b-7e4433b28bd9');

-- Dumping structure for table shoes_store_app.spring_session
CREATE TABLE IF NOT EXISTS `spring_session` (
  `PRIMARY_ID` char(36) NOT NULL,
  `SESSION_ID` char(36) NOT NULL,
  `CREATION_TIME` bigint(20) NOT NULL,
  `LAST_ACCESS_TIME` bigint(20) NOT NULL,
  `MAX_INACTIVE_INTERVAL` int(11) NOT NULL,
  `EXPIRY_TIME` bigint(20) NOT NULL,
  `PRINCIPAL_NAME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`PRIMARY_ID`),
  UNIQUE KEY `SPRING_SESSION_IX1` (`SESSION_ID`),
  KEY `SPRING_SESSION_IX2` (`EXPIRY_TIME`),
  KEY `SPRING_SESSION_IX3` (`PRINCIPAL_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- Dumping data for table shoes_store_app.spring_session: ~2 rows (approximately)
INSERT INTO `spring_session` (`PRIMARY_ID`, `SESSION_ID`, `CREATION_TIME`, `LAST_ACCESS_TIME`, `MAX_INACTIVE_INTERVAL`, `EXPIRY_TIME`, `PRINCIPAL_NAME`) VALUES
	('7ea15dc6-0c02-46da-8a40-58d1337ebf8d', 'caa2d0f2-761b-4f92-805e-91031745c1be', 1733225809102, 1733225809102, 604800, 1733830609102, NULL),
	('b8481a4e-e40d-4d3b-a6fb-50278da00630', 'a9045517-5822-4563-9f33-82a7009992f4', 1733225809102, 1733225823378, 604800, 1733830623378, NULL);

-- Dumping structure for table shoes_store_app.spring_session_attributes
CREATE TABLE IF NOT EXISTS `spring_session_attributes` (
  `SESSION_PRIMARY_ID` char(36) NOT NULL,
  `ATTRIBUTE_NAME` varchar(200) NOT NULL,
  `ATTRIBUTE_BYTES` blob NOT NULL,
  PRIMARY KEY (`SESSION_PRIMARY_ID`,`ATTRIBUTE_NAME`),
  CONSTRAINT `SPRING_SESSION_ATTRIBUTES_FK` FOREIGN KEY (`SESSION_PRIMARY_ID`) REFERENCES `spring_session` (`PRIMARY_ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- Dumping data for table shoes_store_app.spring_session_attributes: ~2 rows (approximately)
INSERT INTO `spring_session_attributes` (`SESSION_PRIMARY_ID`, `ATTRIBUTE_NAME`, `ATTRIBUTE_BYTES`) VALUES
	('b8481a4e-e40d-4d3b-a6fb-50278da00630', 'cart', _binary 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000077040000000078),
	('b8481a4e-e40d-4d3b-a6fb-50278da00630', 'recentProducts', _binary 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a65787000000001770400000001737200316975682e6669742e64686b74706d3131376374742e67726f757030362e656e7469746965732e50726f647563744974656d000000000000000102000844000570726963654900087175616e746974794c0005636f6c6f7274003a4c6975682f6669742f64686b74706d3131376374742f67726f757030362f656e7469746965732f656e756d732f50726f64756374436f6c6f723b4c000269647400124c6a6176612f6c616e672f537472696e673b4c00106c69737444657461696c496d616765737400104c6a6176612f7574696c2f4c6973743b4c000770726f6475637474002f4c6975682f6669742f64686b74706d3131376374742f67726f757030362f656e7469746965732f50726f647563743b4c000473697a6571007e00044c000673746174757374003b4c6975682f6669742f64686b74706d3131376374742f67726f757030362f656e7469746965732f656e756d732f50726f647563745374617475733b787040f86a0000000000000000137e7200386975682e6669742e64686b74706d3131376374742e67726f757030362e656e7469746965732e656e756d732e50726f64756374436f6c6f7200000000000000001200007872000e6a6176612e6c616e672e456e756d0000000000000000120000787074000352454474002431333030346261622d393462352d343162372d616232632d6432633036376438623565397372002a6f72672e68696265726e6174652e636f6c6c656374696f6e2e7370692e50657273697374656e74426167963c61233a7241920200024c000362616771007e00054c001270726f7669646564436f6c6c656374696f6e7400164c6a6176612f7574696c2f436f6c6c656374696f6e3b787200396f72672e68696265726e6174652e636f6c6c656374696f6e2e7370692e416273747261637450657273697374656e74436f6c6c656374696f6e33a4b04a3cf0460c02000b5a001b616c6c6f774c6f61644f7574736964655472616e73616374696f6e49000a63616368656453697a655a000564697274795a000e656c656d656e7452656d6f7665645a000b696e697469616c697a65645a000d697354656d7053657373696f6e4c00036b65797400124c6a6176612f6c616e672f4f626a6563743b4c00056f776e657271007e00114c0004726f6c6571007e00044c001273657373696f6e466163746f72795575696471007e00044c000e73746f726564536e617073686f747400164c6a6176612f696f2f53657269616c697a61626c653b787000ffffffff0000010071007e000d71007e00087400426975682e6669742e64686b74706d3131376374742e67726f757030362e656e7469746965732e50726f647563744974656d2e6c69737444657461696c496d61676573707371007e000000000003770400000003740069687474703a2f2f7265732e636c6f7564696e6172792e636f6d2f64723775786469396f2f696d6167652f75706c6f61642f76313733323933393934382f53686f657353686f704170702f50726f647563742d4974656d2f6e756c6c5f39656237353965632e77656270740069687474703a2f2f7265732e636c6f7564696e6172792e636f6d2f64723775786469396f2f696d6167652f75706c6f61642f76313733323933393935302f53686f657353686f704170702f50726f647563742d4974656d2f6e756c6c5f36303839333462332e77656270740069687474703a2f2f7265732e636c6f7564696e6172792e636f6d2f64723775786469396f2f696d6167652f75706c6f61642f76313733323933393935332f53686f657353686f704170702f50726f647563742d4974656d2f6e756c6c5f33616638333065322e77656270787371007e00000000000377040000000371007e001671007e001771007e001878707372002d6975682e6669742e64686b74706d3131376374742e67726f757030362e656e7469746965732e50726f64756374000000000000000102000a440006726174696e674c000863617465676f72797400304c6975682f6669742f64686b74706d3131376374742f67726f757030362f656e7469746965732f43617465676f72793b4c000a636f6c6c656374696f6e7400394c6975682f6669742f64686b74706d3131376374742f67726f757030362f656e7469746965732f50726f64756374436f6c6c656374696f6e3b4c000b63726561746564446174657400194c6a6176612f74696d652f4c6f63616c4461746554696d653b4c000b6465736372697074696f6e71007e00044c0009666565644261636b7371007e00054c000667656e64657274003b4c6975682f6669742f64686b74706d3131376374742f67726f757030362f656e7469746965732f656e756d732f50726f6475637447656e6465723b4c0002696471007e00044c00046e616d6571007e00044c000c70726f647563744974656d7371007e0005787000000000000000007372002e6975682e6669742e64686b74706d3131376374742e67726f757030362e656e7469746965732e43617465676f727900000000000000010200044c000b6465736372697074696f6e71007e00044c0002696471007e00044c00046e616d6571007e00044c000870726f647563747371007e0005787074000b4465736372697074696f6e74002435313336393833322d316262642d343966342d386566652d30636461353663313235626674000d4769c3a07920536e65616b65727371007e000e00ffffffff0000000071007e002371007e00217400376975682e6669742e64686b74706d3131376374742e67726f757030362e656e7469746965732e43617465676f72792e70726f647563747370707070707074001e53e1baa36e205068e1baa96d2054e1bb9174204e68e1baa57420323032347371007e000e00ffffffff0000000074002461383732336466312d643339302d346234352d383966362d63356562313764323664313071007e001f7400376975682e6669742e64686b74706d3131376374742e67726f757030362e656e7469746965732e50726f647563742e666565644261636b73707070707071007e00297400174769c3a079205468e1bb83205468616f204269747469737371007e000e00ffffffff0000000071007e002971007e001f74003a6975682e6669742e64686b74706d3131376374742e67726f757030362e656e7469746965732e50726f647563742e70726f647563744974656d737070707074000233367e7200396975682e6669742e64686b74706d3131376374742e67726f757030362e656e7469746965732e656e756d732e50726f6475637453746174757300000000000000001200007871007e000a740007494e53544f434b78);

-- Dumping structure for table shoes_store_app.users
CREATE TABLE IF NOT EXISTS `users` (
  `avatar` varchar(255) DEFAULT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `id` varchar(255) NOT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `gender` enum('FEMALE','MALE') DEFAULT NULL,
  `role` enum('ADMIN','CUSTOMER') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table shoes_store_app.users: ~6 rows (approximately)
INSERT INTO `users` (`avatar`, `firstName`, `id`, `lastName`, `phone`, `gender`, `role`) VALUES
	(NULL, 'Demo', '08cbba31-427d-4a09-9a6c-f5e7e3ba0e19', 'Demo Name', NULL, NULL, 'ADMIN'),
	(NULL, 'An ', '68bc814f-807b-4eec-aa46-15df83c45c48', 'An An', NULL, NULL, 'CUSTOMER'),
	(NULL, 'Phan', '8effe8ff-b81d-4768-badf-12d0d89807d5', 'Ngọc Huy', NULL, NULL, 'CUSTOMER'),
	(NULL, 'Nguyen', '976a1187-a95c-41a5-b813-b0a3b140be2e', 'Huy', NULL, NULL, 'CUSTOMER'),
	(NULL, 'Lê ', 'a28a2405-86b8-48e1-bd61-50b0e2c72d23', 'Thanh An', NULL, NULL, 'CUSTOMER'),
	(NULL, 'Le', 'd0e56e58-9962-426f-8084-cadb76562fff', 'An', NULL, NULL, 'ADMIN');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
