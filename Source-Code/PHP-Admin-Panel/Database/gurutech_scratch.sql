-- phpMyAdmin SQL Dump
-- version 4.0.10.7
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 05, 2015 at 02:09 PM
-- Server version: 5.5.36-cll
-- PHP Version: 5.4.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `gurutech_scratch`
--

-- --------------------------------------------------------

--
-- Table structure for table `answers`
--

CREATE TABLE IF NOT EXISTS `answers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url_id` int(11) NOT NULL,
  `answer` varchar(255) NOT NULL,
  `result` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=37 ;

--
-- Dumping data for table `answers`
--

INSERT INTO `answers` (`id`, `url_id`, `answer`, `result`) VALUES
(1, 1, 'mickey', 0),
(2, 1, 'tweety', 1),
(3, 1, 'Duck', 0),
(4, 1, 'Scooby', 0),
(5, 2, 'Kim Anderson', 0),
(6, 2, 'aishwariya', 0),
(7, 2, 'nicole kidman', 0),
(8, 2, 'Norah Jones', 1),
(9, 4, 'kristna', 1),
(10, 4, 'anjelina', 0),
(11, 4, 'nicole', 0),
(12, 4, 'sarah', 0),
(13, 5, 'Jerry', 1),
(14, 5, 'Tom', 0),
(15, 5, 'Tweety', 0),
(16, 5, 'Mickey Mouse', 0),
(17, 6, 'Tweety', 0),
(18, 6, 'Duck', 0),
(19, 6, 'Mickey Mouse', 1),
(20, 6, 'Jerry', 0),
(21, 7, 'Donkey Kong', 0),
(22, 7, 'Droopy', 0),
(23, 7, 'Fred Fredburger', 0),
(24, 7, 'Mario', 1),
(25, 8, 'Bugs Bunny', 0),
(26, 8, 'Donald Duck''s', 1),
(27, 8, 'Bart Simpson', 0),
(28, 8, 'Charlie Brown', 0),
(29, 9, 'Jasmine', 0),
(30, 9, 'Jafar', 0),
(31, 9, 'Alex', 0),
(32, 9, 'Aladdin', 1),
(33, 10, 'Axew', 0),
(34, 10, 'Chikorita', 0),
(35, 10, 'Pikachu', 1),
(36, 10, 'Goldeen', 0);

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE IF NOT EXISTS `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `category`) VALUES
(1, 'Cartoon'),
(2, 'Actress');

-- --------------------------------------------------------

--
-- Table structure for table `members`
--

CREATE TABLE IF NOT EXISTS `members` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `username` varchar(65) NOT NULL DEFAULT '',
  `password` varchar(65) NOT NULL DEFAULT '',
  `type` int(11) NOT NULL COMMENT '1=admin,2=user',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Dumping data for table `members`
--

INSERT INTO `members` (`id`, `username`, `password`, `type`) VALUES
(1, 'admin', '21232f297a57a5a743894a0e4a801fc3', 1),
(4, 'gurutechnolabs', '6203429ccb752d852c545b42ab462552', 1),
(5, 'user', 'ee11cbb19052e40b07aac0ca060c23ee', 2),
(11, 'amit', '0cb1eb413b8f7cee17701a37a1d74dc3', 1);

-- --------------------------------------------------------

--
-- Table structure for table `poweredby`
--

CREATE TABLE IF NOT EXISTS `poweredby` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `poweredby` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `poweredby`
--

INSERT INTO `poweredby` (`id`, `poweredby`) VALUES
(1, 'Guru Technolabs'),
(2, 'Guru Technolabs');

-- --------------------------------------------------------

--
-- Table structure for table `url`
--

CREATE TABLE IF NOT EXISTS `url` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cat_id` int(11) NOT NULL,
  `url` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `url`
--

INSERT INTO `url` (`id`, `cat_id`, `url`) VALUES
(1, 1, 'http://gurutechnolabs.com/demo/scratch/uploads/cute-cartoons-21.jpg'),
(2, 2, 'http://gurutechnolabs.com/demo/scratch/uploads/Norah-Jones-Indian-Actress-Hollywood.png'),
(4, 2, 'http://gurutechnolabs.com/demo/scratch/uploads/Most-Beautiful-Hollywood-actress-2013-wallpapers.jpg'),
(5, 1, 'http://gurutechnolabs.com/demo/scratch/uploads/acbbd49b22b8c556979418f6618a35fd.jpg'),
(6, 1, 'http://gurutechnolabs.com/demo/scratch/uploads/cartoon_1.jpg'),
(7, 1, 'http://gurutechnolabs.com/demo/scratch/uploads/cartoon-characters-26v.jpg'),
(8, 1, 'http://gurutechnolabs.com/demo/scratch/uploads/what-don-t-you-know-about-your-favorite-cartoon-characters-635771852-aug-2-2013-1-600x500.jpg'),
(9, 1, 'http://gurutechnolabs.com/demo/scratch/uploads/1245369-untitled_1.png'),
(10, 1, 'http://gurutechnolabs.com/demo/scratch/uploads/images.jpg');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
