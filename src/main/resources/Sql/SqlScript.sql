create schema `pharmacy`;

use `pharmacy`;

create table `category`(
	`id` bigint auto_increment not null,
    `title` varchar(100) not null,
    `img_url` varchar(800) default null,
    primary key(`id`)
);

create table `product`(
	`id` bigint auto_increment not null,
    `title` varchar(100) not null,
    `quantity` int default null,
    `price` double default null,
    `price_after_discount` double default null,
    `img_url` varchar(800) default null,
    `category_id` bigint ,
    primary key(`id`),
    constraint foreign key(`category_id`) references `category`(`id`)
);


CREATE TABLE `cart` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `quantity` int DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  `user` varchar(55) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
);
create table `review`(
	`id` bigint auto_increment not null,
    `date` datetime(6) default null,
    `rating` decimal(3,2) default null,
    `description` text default null,
    `user` varchar(55) not null,
    `product_id` bigint,
    primary key (`id`),
    constraint foreign key (`product_id`) references `product`(`id`)
);

CREATE TABLE `address` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `customer` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `orders` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `order_tracking_number` varchar(255) DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  `total_price_after_discount` double DEFAULT NULL,
  `total_quantity` int DEFAULT NULL,
  `address_id` bigint DEFAULT NULL,
  `customer_id` bigint DEFAULT NULL,
  `date_created` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_address_id` (`address_id`),
  KEY `K_customer_id` (`customer_id`),
  CONSTRAINT `FK_address_id` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`),
  CONSTRAINT `FK_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
);

CREATE TABLE `order_item` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `image_url` varchar(255) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `unit_price` decimal(19,2) DEFAULT NULL,
  `order_id` bigint DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `K_order_id` (`order_id`),
  KEY `FK_product_id` (`product_id`),
  CONSTRAINT `FK_order_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `FK_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
);
