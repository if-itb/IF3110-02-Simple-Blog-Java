-- phpMyAdmin SQL Dump
-- version 4.1.6
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 25, 2014 at 07:15 PM
-- Server version: 5.6.16
-- PHP Version: 5.5.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `wbd_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
  `num` int(11) NOT NULL AUTO_INCREMENT,
  `id_post` int(11) NOT NULL,
  `isi` text NOT NULL,
  `waktu` date NOT NULL,
  `name` varchar(2047) NOT NULL,
  `email` varchar(2047) NOT NULL,
  PRIMARY KEY (`num`,`id_post`),
  KEY `id_post` (`id_post`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`num`, `id_post`, `isi`, `waktu`, `name`, `email`) VALUES
(1, 2, 'null', '2014-11-25', 'null', 'null'),
(2, 2, 'null', '2014-11-25', 'null', 'null'),
(6, 2, 'null', '2014-11-25', 'null', 'null'),
(7, 2, 'c', '2014-11-25', 'a', 'b'),
(8, 2, 't', '2014-11-25', 'a', 'b'),
(9, 1, 'c', '2014-11-25', 'a', 'b'),
(10, 1, 'wowwowowowowoowoowwowowowow', '2014-11-25', 'Wow', 'Mr@vain.com'),
(11, 1, '3', '2014-11-25', 'asdf', 'asdf@a.com'),
(12, 1, 'lolasdfsadf', '2014-11-26', 'dendi', 'dendi@dendimon.org');

-- --------------------------------------------------------

--
-- Table structure for table `post`
--

CREATE TABLE IF NOT EXISTS `post` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `judul` varchar(2047) NOT NULL,
  `isi` text NOT NULL,
  `waktu` date NOT NULL,
  `is_deleted` tinyint(1) DEFAULT '0',
  `is_published` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `id_user` (`id_user`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `post`
--

INSERT INTO `post` (`id`, `id_user`, `judul`, `isi`, `waktu`, `is_deleted`, `is_published`) VALUES
(1, 2, 'Morphling', 'Morphling is a ranged agility hero that has many flexible abilities and item builds and is leveled as a very strong hard carry. He relies strongly on his attributes, and is an effective ganker. Unlike other agility carries who rely on physical attacks, he relies on his powerful abilities to initiate and shift into a state from which he can make a kill. Waveform lets him surge directly forward to a location, damaging enemies on his path, working both as a nuke or an escape. Adaptive Strike is an interesting scaling ability that blasts enemies with magical water while also stunning, knocking back, and dealing damage. Its power is based on either Agility or Strength. Morph allows him to edit his physical being, switching it from Agility or Strength, transforming him into a strong damage dealer or a tank. His ultimate, Replicate, creates a copy of either an ally or an enemy. At will, he can take on the place of that Replicate and instantly surprise enemies. ', '2014-11-25', 0, 1),
(2, 3, 'Huskar', 'Huskar the Sacred Warrior is a ranged Strength hero who is an effective ganker and carry, with both great benefits and risks offered. He is known to be a powerful adversary in most games, as his damage per second can easily kill other heroes at any point in the game assuming no one is near by to save them. Unlike most heroes, he doesn''t use mana much to use his abilities, rather, he sacrifices his own health to inflict the highest damage possible. His passive Berserker''s Blood defines the power of sacrifice; he gets more dangerous the more he gets hurt. This gives him increased magic resistance and attack speed depending how much life is missing, and the given magic resistance and attack speed are staggering, potentially having the highest damage output in the earliest stage. Huskar can use his own life force to attack using Burning Spears which scorches foes and gives him an advantage early on. Due to the health cost of Burning Spears, his Berserker''s Blood activates, granting him the ability to decimate any target. Inner Vitality magically heals Huskar or his allies, regenerating health each second based upon the target''s primary attribute, and heals more when the health is on a critical level. His Life Break is his most dangerous ability and a risky one too. Charging towards his target, Huskar sheds a large amount of life force to cut a target''s current HP to fractions. This is great to use as an initiation spell, as it deals immense damage based on the target''s current health, breaking it down, allowing Huskar and his allies to feel the pain that the Sacred Warrior felt. Courageous and fearless, Huskar is powerful at any stage of the game, and is willing to suffer and face death, for him to contribute and deliver a marvelous victory to his team. ', '2014-11-25', 0, 1),
(4, 4, 'Lina', 'Lina the Slayer is a ranged Intelligence hero, adept at destroying enemy heroes quickly by delivering massive bursts of magical damage, making her one of the most effective gankers in the game. She possesses immense damaging capabilities all throughout the game, but is very fragile. Two of her fiery spells are her main source of damage, Dragon Slave sends a wave of fire to burn enemies in her path while Light Strike Array stuns them with a concentrated pillar of solar flame. Each of her spells deals great damage early on and has a low cooldown. Her Fiery Soul bolsters her attack and movement speed every time she casts a spell, which gives her scaling damage for the later game. Laguna Blade, her ultimate, is her ace in the hole. Lina fires off a huge bolt of lightning at a single target, dealing colossal damage. Laguna Blade''s damage is staggering in early-mid game, and late game is still enough to destroy frail enemy heroes. Dragon Slave, Light Strike Array, and Laguna Blade are incredible flaming nukes that can incinerate her target instantly, and Fiery Soul allows her to transition into a strong and fast physical attacker. Though her power falls from its peak late game, mana-boosting and damage-increasing items can be purchased to keep herself up. ', '2014-11-25', 0, 1),
(5, 2, 'Sven', 'Sven the Rogue Knight is a versatile melee strength Hero with superior physical power yet is coupled with various abilities that provide various utility. He can fulfill various roles, but is often played as a support or a semi-carry due to his high utility even without items. He possesses a versatile arsenal, from shouts that grant armor both for escaping and pushing to throwable gauntlets that disorient enemies around the target unit. With enough items, Sven has the potential to be a strong late-game carry due to his ultimate which increases his damage and his passive which allows him to hit multiple targets at once. He is a formidable foe and his versatility makes the Rogue Knight a great asset to any team. ', '2014-11-25', 0, 0),
(6, 2, 'Sven', 'Sven the Rogue Knight is a versatile melee strength Hero with superior physical power yet is coupled with various abilities that provide various utility. He can fulfill various roles, but is often played as a support or a semi-carry due to his high utility even without items. He possesses a versatile arsenal, from shouts that grant armor both for escaping and pushing to throwable gauntlets that disorient enemies around the target unit. With enough items, Sven has the potential to be a strong late-game carry due to his ultimate which increases his damage and his passive which allows him to hit multiple targets at once. He is a formidable foe and his versatility makes the Rogue Knight a great asset to any team. ', '2014-11-25', 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` char(255) NOT NULL,
  `password` char(255) NOT NULL,
  `nama` varchar(2047) NOT NULL,
  `email` varchar(2047) NOT NULL,
  `role` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COMMENT='Role terbagi menjadi guest(0), user(10), editor(20), dan admin(30)' AUTO_INCREMENT=7 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `nama`, `email`, `role`) VALUES
(2, 'dendimon', 'secret', 'Daniel Ishutin', 'dendi@navi.gg', 10),
(3, 'puppey', 'secret', 'Clement Ivanov', 'puppey@top.sc', 20),
(4, 'kuroky', 'secret', 'Kuro Salehi Takshanomi', 'kuroky@top.sc', 10),
(5, 'xboct', 'secret', 'Alexander Dashkevich', 'xboct@top.sc', 10),
(6, 'icefrog', 'secret', 'Bruno Carlucci', 'icefrog@dota2.com', 30);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`id_post`) REFERENCES `post` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `post`
--
ALTER TABLE `post`
  ADD CONSTRAINT `post_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
