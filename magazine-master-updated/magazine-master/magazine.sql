-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 02, 2020 at 07:47 PM
-- Server version: 10.4.6-MariaDB
-- PHP Version: 7.3.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `magazine`
--

-- --------------------------------------------------------

--
-- Table structure for table `account_emailaddress`
--

CREATE TABLE `account_emailaddress` (
  `id` int(11) NOT NULL,
  `email` varchar(254) NOT NULL,
  `verified` tinyint(1) NOT NULL,
  `primary` tinyint(1) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `account_emailconfirmation`
--

CREATE TABLE `account_emailconfirmation` (
  `id` int(11) NOT NULL,
  `created` datetime(6) NOT NULL,
  `sent` datetime(6) DEFAULT NULL,
  `key` varchar(64) NOT NULL,
  `email_address_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `alumni_alumni`
--

CREATE TABLE `alumni_alumni` (
  `id` int(11) NOT NULL,
  `name` longtext NOT NULL,
  `email` varchar(254) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `year_of_passing` varchar(5) NOT NULL,
  `date_of_birth` date NOT NULL,
  `martial_status` longtext NOT NULL,
  `profession` longtext NOT NULL,
  `address` longtext NOT NULL,
  `graduation` longtext NOT NULL,
  `graduation1` longtext NOT NULL,
  `status` tinyint(1) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `last_updated` datetime(6) NOT NULL,
  `requestor_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `auditorium_auditoriumreservation`
--

CREATE TABLE `auditorium_auditoriumreservation` (
  `id` int(11) NOT NULL,
  `reason` longtext NOT NULL,
  `start_time` datetime(6) NOT NULL,
  `end_time` datetime(6) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `last_updated` datetime(6) NOT NULL,
  `requestor_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `authtoken_token`
--

CREATE TABLE `authtoken_token` (
  `key` varchar(40) NOT NULL,
  `created` datetime(6) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `auth_group`
--

CREATE TABLE `auth_group` (
  `id` int(11) NOT NULL,
  `name` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `auth_group`
--

INSERT INTO `auth_group` (`id`, `name`) VALUES
(1, 'Admins');

-- --------------------------------------------------------

--
-- Table structure for table `auth_group_permissions`
--

CREATE TABLE `auth_group_permissions` (
  `id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `auth_group_permissions`
--

INSERT INTO `auth_group_permissions` (`id`, `group_id`, `permission_id`) VALUES
(1, 1, 1),
(2, 1, 2),
(3, 1, 3),
(4, 1, 4),
(5, 1, 5),
(6, 1, 6),
(7, 1, 7),
(8, 1, 8),
(9, 1, 9),
(10, 1, 10),
(11, 1, 11),
(12, 1, 12),
(13, 1, 13),
(14, 1, 14),
(15, 1, 15),
(16, 1, 16),
(17, 1, 17),
(18, 1, 18),
(19, 1, 19),
(20, 1, 20),
(21, 1, 21),
(22, 1, 22),
(23, 1, 23),
(24, 1, 24),
(25, 1, 25),
(26, 1, 26),
(27, 1, 27),
(28, 1, 28),
(29, 1, 29),
(30, 1, 30),
(31, 1, 31),
(32, 1, 32),
(33, 1, 33),
(34, 1, 34),
(35, 1, 35),
(36, 1, 36),
(37, 1, 37),
(38, 1, 38),
(39, 1, 39),
(40, 1, 40),
(41, 1, 41),
(42, 1, 42),
(43, 1, 43),
(44, 1, 44),
(45, 1, 45),
(46, 1, 46),
(47, 1, 47),
(48, 1, 48),
(49, 1, 49),
(50, 1, 50),
(51, 1, 51),
(52, 1, 52),
(53, 1, 53),
(54, 1, 54),
(55, 1, 55),
(56, 1, 56),
(57, 1, 57),
(58, 1, 58),
(59, 1, 59),
(60, 1, 60),
(61, 1, 61),
(62, 1, 62),
(63, 1, 63),
(64, 1, 64),
(65, 1, 65),
(66, 1, 66),
(67, 1, 67),
(68, 1, 68),
(69, 1, 69),
(70, 1, 70),
(71, 1, 71),
(72, 1, 72),
(73, 1, 73),
(74, 1, 74),
(75, 1, 75),
(76, 1, 76),
(77, 1, 77),
(78, 1, 78),
(79, 1, 79),
(80, 1, 80),
(81, 1, 81),
(82, 1, 82),
(83, 1, 83),
(84, 1, 84),
(85, 1, 85),
(86, 1, 86),
(87, 1, 87),
(88, 1, 88),
(89, 1, 89),
(90, 1, 90),
(91, 1, 91),
(92, 1, 92);

-- --------------------------------------------------------

--
-- Table structure for table `auth_permission`
--

CREATE TABLE `auth_permission` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `content_type_id` int(11) NOT NULL,
  `codename` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `auth_permission`
--

INSERT INTO `auth_permission` (`id`, `name`, `content_type_id`, `codename`) VALUES
(1, 'Can add auditorium reservation', 1, 'add_auditoriumreservation'),
(2, 'Can change auditorium reservation', 1, 'change_auditoriumreservation'),
(3, 'Can delete auditorium reservation', 1, 'delete_auditoriumreservation'),
(4, 'Can view auditorium reservation', 1, 'view_auditoriumreservation'),
(5, 'Can add daycare reservation', 2, 'add_daycarereservation'),
(6, 'Can change daycare reservation', 2, 'change_daycarereservation'),
(7, 'Can delete daycare reservation', 2, 'delete_daycarereservation'),
(8, 'Can view daycare reservation', 2, 'view_daycarereservation'),
(9, 'Can add user', 3, 'add_user'),
(10, 'Can change user', 3, 'change_user'),
(11, 'Can delete user', 3, 'delete_user'),
(12, 'Can view user', 3, 'view_user'),
(13, 'Can add role', 4, 'add_role'),
(14, 'Can change role', 4, 'change_role'),
(15, 'Can delete role', 4, 'delete_role'),
(16, 'Can view role', 4, 'view_role'),
(17, 'Can add user role', 5, 'add_userrole'),
(18, 'Can change user role', 5, 'change_userrole'),
(19, 'Can delete user role', 5, 'delete_userrole'),
(20, 'Can view user role', 5, 'view_userrole'),
(21, 'Can add room', 6, 'add_room'),
(22, 'Can change room', 6, 'change_room'),
(23, 'Can delete room', 6, 'delete_room'),
(24, 'Can view room', 6, 'view_room'),
(25, 'Can add room user', 7, 'add_roomuser'),
(26, 'Can change room user', 7, 'change_roomuser'),
(27, 'Can delete room user', 7, 'delete_roomuser'),
(28, 'Can view room user', 7, 'view_roomuser'),
(29, 'Can add chat', 8, 'add_chat'),
(30, 'Can change chat', 8, 'change_chat'),
(31, 'Can delete chat', 8, 'delete_chat'),
(32, 'Can view chat', 8, 'view_chat'),
(33, 'Can add alumni', 9, 'add_alumni'),
(34, 'Can change alumni', 9, 'change_alumni'),
(35, 'Can delete alumni', 9, 'delete_alumni'),
(36, 'Can view alumni', 9, 'view_alumni'),
(37, 'Can add log entry', 10, 'add_logentry'),
(38, 'Can change log entry', 10, 'change_logentry'),
(39, 'Can delete log entry', 10, 'delete_logentry'),
(40, 'Can view log entry', 10, 'view_logentry'),
(41, 'Can add permission', 11, 'add_permission'),
(42, 'Can change permission', 11, 'change_permission'),
(43, 'Can delete permission', 11, 'delete_permission'),
(44, 'Can view permission', 11, 'view_permission'),
(45, 'Can add group', 12, 'add_group'),
(46, 'Can change group', 12, 'change_group'),
(47, 'Can delete group', 12, 'delete_group'),
(48, 'Can view group', 12, 'view_group'),
(49, 'Can add content type', 13, 'add_contenttype'),
(50, 'Can change content type', 13, 'change_contenttype'),
(51, 'Can delete content type', 13, 'delete_contenttype'),
(52, 'Can view content type', 13, 'view_contenttype'),
(53, 'Can add session', 14, 'add_session'),
(54, 'Can change session', 14, 'change_session'),
(55, 'Can delete session', 14, 'delete_session'),
(56, 'Can view session', 14, 'view_session'),
(57, 'Can add site', 15, 'add_site'),
(58, 'Can change site', 15, 'change_site'),
(59, 'Can delete site', 15, 'delete_site'),
(60, 'Can view site', 15, 'view_site'),
(61, 'Can add social application', 16, 'add_socialapp'),
(62, 'Can change social application', 16, 'change_socialapp'),
(63, 'Can delete social application', 16, 'delete_socialapp'),
(64, 'Can view social application', 16, 'view_socialapp'),
(65, 'Can add social account', 17, 'add_socialaccount'),
(66, 'Can change social account', 17, 'change_socialaccount'),
(67, 'Can delete social account', 17, 'delete_socialaccount'),
(68, 'Can view social account', 17, 'view_socialaccount'),
(69, 'Can add social application token', 18, 'add_socialtoken'),
(70, 'Can change social application token', 18, 'change_socialtoken'),
(71, 'Can delete social application token', 18, 'delete_socialtoken'),
(72, 'Can view social application token', 18, 'view_socialtoken'),
(73, 'Can add email address', 19, 'add_emailaddress'),
(74, 'Can change email address', 19, 'change_emailaddress'),
(75, 'Can delete email address', 19, 'delete_emailaddress'),
(76, 'Can view email address', 19, 'view_emailaddress'),
(77, 'Can add email confirmation', 20, 'add_emailconfirmation'),
(78, 'Can change email confirmation', 20, 'change_emailconfirmation'),
(79, 'Can delete email confirmation', 20, 'delete_emailconfirmation'),
(80, 'Can view email confirmation', 20, 'view_emailconfirmation'),
(81, 'Can add Token', 21, 'add_token'),
(82, 'Can change Token', 21, 'change_token'),
(83, 'Can delete Token', 21, 'delete_token'),
(84, 'Can view Token', 21, 'view_token'),
(85, 'Can add journal', 22, 'add_journal'),
(86, 'Can change journal', 22, 'change_journal'),
(87, 'Can delete journal', 22, 'delete_journal'),
(88, 'Can view journal', 22, 'view_journal'),
(89, 'Can add comments', 23, 'add_comments'),
(90, 'Can change comments', 23, 'change_comments'),
(91, 'Can delete comments', 23, 'delete_comments'),
(92, 'Can view comments', 23, 'view_comments'),
(93, 'Can add attachment', 24, 'add_attachment'),
(94, 'Can change attachment', 24, 'change_attachment'),
(95, 'Can delete attachment', 24, 'delete_attachment'),
(96, 'Can view attachment', 24, 'view_attachment'),
(97, 'Can add admin comments', 25, 'add_admincomments'),
(98, 'Can change admin comments', 25, 'change_admincomments'),
(99, 'Can delete admin comments', 25, 'delete_admincomments'),
(100, 'Can view admin comments', 25, 'view_admincomments');

-- --------------------------------------------------------

--
-- Table structure for table `chat_chat`
--

CREATE TABLE `chat_chat` (
  `id` int(11) NOT NULL,
  `message` varchar(200) DEFAULT NULL,
  `created_at` datetime(6) NOT NULL,
  `last_updated` datetime(6) NOT NULL,
  `room_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `chat_room`
--

CREATE TABLE `chat_room` (
  `id` int(11) NOT NULL,
  `name` varchar(70) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `status` tinyint(1) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `last_updated` datetime(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `chat_roomuser`
--

CREATE TABLE `chat_roomuser` (
  `id` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `last_updated` datetime(6) NOT NULL,
  `room_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `daycare_daycarereservation`
--

CREATE TABLE `daycare_daycarereservation` (
  `id` int(11) NOT NULL,
  `reason` longtext NOT NULL,
  `start_time` datetime(6) NOT NULL,
  `end_time` datetime(6) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `last_updated` datetime(6) NOT NULL,
  `requestor_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `django_admin_log`
--

CREATE TABLE `django_admin_log` (
  `id` int(11) NOT NULL,
  `action_time` datetime(6) NOT NULL,
  `object_id` longtext DEFAULT NULL,
  `object_repr` varchar(200) NOT NULL,
  `action_flag` smallint(5) UNSIGNED NOT NULL
) ;

--
-- Dumping data for table `django_admin_log`
--

INSERT INTO `django_admin_log` (`id`, `action_time`, `object_id`, `object_repr`, `action_flag`, `change_message`, `content_type_id`, `user_id`) VALUES
(1, '2020-05-31 06:37:18.347850', '1', 'Admins', 1, '[{\"added\": {}}]', 12, 1),
(2, '2020-05-31 06:37:48.315444', '1', 'ashargillani', 2, '[{\"changed\": {\"fields\": [\"Groups\"]}}]', 3, 1);

-- --------------------------------------------------------

--
-- Table structure for table `django_content_type`
--

CREATE TABLE `django_content_type` (
  `id` int(11) NOT NULL,
  `app_label` varchar(100) NOT NULL,
  `model` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `django_content_type`
--

INSERT INTO `django_content_type` (`id`, `app_label`, `model`) VALUES
(19, 'account', 'emailaddress'),
(20, 'account', 'emailconfirmation'),
(10, 'admin', 'logentry'),
(17, 'allauth', 'socialaccount'),
(16, 'allauth', 'socialapp'),
(18, 'allauth', 'socialtoken'),
(9, 'alumni', 'alumni'),
(1, 'auditorium', 'auditoriumreservation'),
(12, 'auth', 'group'),
(11, 'auth', 'permission'),
(21, 'authtoken', 'token'),
(8, 'chat', 'chat'),
(6, 'chat', 'room'),
(7, 'chat', 'roomuser'),
(13, 'contenttypes', 'contenttype'),
(2, 'daycare', 'daycarereservation'),
(24, 'django_summernote', 'attachment'),
(25, 'journals', 'admincomments'),
(23, 'journals', 'comments'),
(22, 'journals', 'journal'),
(14, 'sessions', 'session'),
(15, 'sites', 'site'),
(4, 'user_management', 'role'),
(3, 'user_management', 'user'),
(5, 'user_management', 'userrole');

-- --------------------------------------------------------

--
-- Table structure for table `django_migrations`
--

CREATE TABLE `django_migrations` (
  `id` int(11) NOT NULL,
  `app` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `applied` datetime(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `django_migrations`
--

INSERT INTO `django_migrations` (`id`, `app`, `name`, `applied`) VALUES
(1, 'contenttypes', '0001_initial', '2020-03-26 06:52:59.525662'),
(2, 'contenttypes', '0002_remove_content_type_name', '2020-03-26 06:52:59.584505'),
(3, 'auth', '0001_initial', '2020-03-26 06:52:59.658306'),
(4, 'auth', '0002_alter_permission_name_max_length', '2020-03-26 06:52:59.842814'),
(5, 'auth', '0003_alter_user_email_max_length', '2020-03-26 06:52:59.849794'),
(6, 'auth', '0004_alter_user_username_opts', '2020-03-26 06:52:59.855778'),
(7, 'auth', '0005_alter_user_last_login_null', '2020-03-26 06:52:59.862760'),
(8, 'auth', '0006_require_contenttypes_0002', '2020-03-26 06:52:59.865751'),
(9, 'auth', '0007_alter_validators_add_error_messages', '2020-03-26 06:52:59.871768'),
(10, 'auth', '0008_alter_user_username_max_length', '2020-03-26 06:52:59.878717'),
(11, 'auth', '0009_alter_user_last_name_max_length', '2020-03-26 06:52:59.884701'),
(12, 'auth', '0010_alter_group_name_max_length', '2020-03-26 06:52:59.900658'),
(13, 'auth', '0011_update_proxy_permissions', '2020-03-26 06:52:59.910633'),
(14, 'user_management', '0001_initial', '2020-03-26 06:53:00.033305'),
(15, 'account', '0001_initial', '2020-03-26 06:53:00.451186'),
(16, 'account', '0002_email_max_length', '2020-03-26 06:53:00.583832'),
(17, 'admin', '0001_initial', '2020-03-26 06:53:00.619736'),
(18, 'admin', '0002_logentry_remove_auto_add', '2020-03-26 06:53:00.718471'),
(19, 'admin', '0003_logentry_add_action_flag_choices', '2020-03-26 06:53:00.729443'),
(20, 'alumni', '0001_initial', '2020-03-26 06:53:00.775320'),
(21, 'alumni', '0002_auto_20200326_1152', '2020-03-26 06:53:00.878045'),
(22, 'auditorium', '0001_initial', '2020-03-26 06:53:00.904973'),
(23, 'auditorium', '0002_auditoriumreservation_requestor', '2020-03-26 06:53:00.972793'),
(24, 'authtoken', '0001_initial', '2020-03-26 06:53:01.020664'),
(25, 'authtoken', '0002_auto_20160226_1747', '2020-03-26 06:53:01.213150'),
(26, 'chat', '0001_initial', '2020-03-26 06:53:01.296926'),
(27, 'daycare', '0001_initial', '2020-03-26 06:53:01.522323'),
(28, 'sessions', '0001_initial', '2020-03-26 06:53:01.591139'),
(29, 'sites', '0001_initial', '2020-03-26 06:53:01.630035'),
(30, 'sites', '0002_alter_domain_unique', '2020-03-26 06:53:01.652973'),
(31, 'journals', '0001_initial', '2020-03-26 10:03:07.470120'),
(32, 'journals', '0002_auto_20200513_1652', '2020-05-13 11:52:16.138433'),
(33, 'journals', '0003_auto_20200601_1133', '2020-06-01 06:33:23.210418'),
(34, 'journals', '0004_auto_20200601_1134', '2020-06-01 06:34:21.097992'),
(35, 'django_summernote', '0001_initial', '2020-06-01 12:01:04.599832'),
(36, 'django_summernote', '0002_update-help_text', '2020-06-01 12:01:04.606816'),
(37, 'journals', '0005_auto_20200602_2012', '2020-06-02 15:12:32.272936');

-- --------------------------------------------------------

--
-- Table structure for table `django_session`
--

CREATE TABLE `django_session` (
  `session_key` varchar(40) NOT NULL,
  `session_data` longtext NOT NULL,
  `expire_date` datetime(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `django_session`
--

INSERT INTO `django_session` (`session_key`, `session_data`, `expire_date`) VALUES
('88lem0r8hsabw1p1b2ysift659a7bjnv', 'MWM1NmI3N2JhMjBkZjE0NzFiYjZkOTQxMjViMDhlZDVlNjI2ZDIyZTp7Il9hdXRoX3VzZXJfaWQiOiIxIiwiX2F1dGhfdXNlcl9iYWNrZW5kIjoiZGphbmdvLmNvbnRyaWIuYXV0aC5iYWNrZW5kcy5Nb2RlbEJhY2tlbmQiLCJfYXV0aF91c2VyX2hhc2giOiIzMzkyMmEwZDJiYzM4YjliMGVhMzIzNTBjMzk1YmQxYTg0ZTY4MzBhIn0=', '2020-06-15 12:06:56.663132'),
('8e3jpy9f8cyb97apwg0fbsxbvw1b4lzi', 'OGY0ZjU0MjA3MDE0NDc0YTRkZTkwMWE0N2Q3Nzk1MTA3M2I3NWM2NDp7fQ==', '2020-04-09 07:49:58.647569'),
('8p6kzpmgul0u6fmdcvpt7qd3r8wv61a0', 'OGY0ZjU0MjA3MDE0NDc0YTRkZTkwMWE0N2Q3Nzk1MTA3M2I3NWM2NDp7fQ==', '2020-04-09 07:51:00.682458'),
('b1lc65sqhttnvgk4hfcx5sz9mqk54b8t', 'OGY0ZjU0MjA3MDE0NDc0YTRkZTkwMWE0N2Q3Nzk1MTA3M2I3NWM2NDp7fQ==', '2020-04-09 07:50:07.764253'),
('c35hoaq91iyu0q33tox523ueiegziw9d', 'MWM1NmI3N2JhMjBkZjE0NzFiYjZkOTQxMjViMDhlZDVlNjI2ZDIyZTp7Il9hdXRoX3VzZXJfaWQiOiIxIiwiX2F1dGhfdXNlcl9iYWNrZW5kIjoiZGphbmdvLmNvbnRyaWIuYXV0aC5iYWNrZW5kcy5Nb2RlbEJhY2tlbmQiLCJfYXV0aF91c2VyX2hhc2giOiIzMzkyMmEwZDJiYzM4YjliMGVhMzIzNTBjMzk1YmQxYTg0ZTY4MzBhIn0=', '2020-04-09 07:59:50.243507'),
('d05b29lla6wykbrini64uqzddtd70lc4', 'OGY0ZjU0MjA3MDE0NDc0YTRkZTkwMWE0N2Q3Nzk1MTA3M2I3NWM2NDp7fQ==', '2020-04-09 07:49:47.970345'),
('g433bdruxk3z79e5hh4x1yb8xf9m4ljk', 'MWM1NmI3N2JhMjBkZjE0NzFiYjZkOTQxMjViMDhlZDVlNjI2ZDIyZTp7Il9hdXRoX3VzZXJfaWQiOiIxIiwiX2F1dGhfdXNlcl9iYWNrZW5kIjoiZGphbmdvLmNvbnRyaWIuYXV0aC5iYWNrZW5kcy5Nb2RlbEJhY2tlbmQiLCJfYXV0aF91c2VyX2hhc2giOiIzMzkyMmEwZDJiYzM4YjliMGVhMzIzNTBjMzk1YmQxYTg0ZTY4MzBhIn0=', '2020-05-27 09:36:38.210708'),
('p920oiy2vi2sv5i3pi68e6kdin2z72us', 'OGY0ZjU0MjA3MDE0NDc0YTRkZTkwMWE0N2Q3Nzk1MTA3M2I3NWM2NDp7fQ==', '2020-04-27 06:13:05.023367'),
('pi8pd4shuog5tra9k6zp7ggyheyajuqn', 'OGY0ZjU0MjA3MDE0NDc0YTRkZTkwMWE0N2Q3Nzk1MTA3M2I3NWM2NDp7fQ==', '2020-04-09 07:50:18.496557'),
('ps6hh1oqje3x6e01gaplkpw79jfzussg', 'OGY0ZjU0MjA3MDE0NDc0YTRkZTkwMWE0N2Q3Nzk1MTA3M2I3NWM2NDp7fQ==', '2020-05-27 09:35:45.156042'),
('s49gvmn7m51fumtttz0vjw2vfkpkt56w', 'OGY0ZjU0MjA3MDE0NDc0YTRkZTkwMWE0N2Q3Nzk1MTA3M2I3NWM2NDp7fQ==', '2020-05-27 09:36:30.236695'),
('ux9j3zwxcxagje39gy0ssj1bnim5a3jv', 'OGY0ZjU0MjA3MDE0NDc0YTRkZTkwMWE0N2Q3Nzk1MTA3M2I3NWM2NDp7fQ==', '2020-06-14 05:55:31.377680'),
('zm3dn0bx1zclvu809kb6ar36nxhmxr87', 'MWM1NmI3N2JhMjBkZjE0NzFiYjZkOTQxMjViMDhlZDVlNjI2ZDIyZTp7Il9hdXRoX3VzZXJfaWQiOiIxIiwiX2F1dGhfdXNlcl9iYWNrZW5kIjoiZGphbmdvLmNvbnRyaWIuYXV0aC5iYWNrZW5kcy5Nb2RlbEJhY2tlbmQiLCJfYXV0aF91c2VyX2hhc2giOiIzMzkyMmEwZDJiYzM4YjliMGVhMzIzNTBjMzk1YmQxYTg0ZTY4MzBhIn0=', '2020-04-27 06:13:13.250570');

-- --------------------------------------------------------

--
-- Table structure for table `django_site`
--

CREATE TABLE `django_site` (
  `id` int(11) NOT NULL,
  `domain` varchar(100) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `django_site`
--

INSERT INTO `django_site` (`id`, `domain`, `name`) VALUES
(1, 'example.com', 'example.com');

-- --------------------------------------------------------

--
-- Table structure for table `django_summernote_attachment`
--

CREATE TABLE `django_summernote_attachment` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `file` varchar(100) NOT NULL,
  `uploaded` datetime(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `django_summernote_attachment`
--

INSERT INTO `django_summernote_attachment` (`id`, `name`, `file`, `uploaded`) VALUES
(1, 'Overtime_Application_October.PNG', 'django-summernote/2020-06-01/764f9ba7-dfa4-43aa-89de-6a8a42efe9ae.PNG', '2020-06-01 12:13:27.364968'),
(2, 'Overtime_Application_October.PNG', 'django-summernote/2020-06-01/aafb017d-534d-4b44-95c2-fc39cc2e9080.PNG', '2020-06-01 12:14:35.416091'),
(3, 'javascript_icon.jpg', 'django-summernote/2020-06-01/7f1ce150-61d3-407f-87cf-b43bd0a9a6c3.jpg', '2020-06-01 12:20:29.372816'),
(4, 'javascript_icon.jpg', 'django-summernote/2020-06-01/16fa7d71-5cf4-475c-b44c-a2d2cc78b05a.jpg', '2020-06-01 12:20:46.010111'),
(5, 'ajax.png', 'django-summernote/2020-06-01/d574a517-14b1-411b-aace-5950a5f83502.png', '2020-06-01 12:20:58.623483'),
(6, 'ajax.png', 'django-summernote/2020-06-01/d8993cd5-e774-4e53-9c8f-0efe2d549784.png', '2020-06-01 12:21:17.642850'),
(7, 'ChangeTable.PNG', 'django-summernote/2020-06-01/a85b38db-df60-4799-be38-bc2bc9724e66.PNG', '2020-06-01 12:35:17.144890'),
(8, 'EDM_1.PNG', 'django-summernote/2020-06-01/bd5d4900-f50c-4014-b2e1-d072590dd33a.PNG', '2020-06-01 12:37:39.647282');

-- --------------------------------------------------------

--
-- Table structure for table `journals_admincomments`
--

CREATE TABLE `journals_admincomments` (
  `id` int(11) NOT NULL,
  `body` longtext NOT NULL,
  `created_on` datetime(6) NOT NULL,
  `admin_id` int(11) NOT NULL,
  `journal_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `journals_admincomments`
--

INSERT INTO `journals_admincomments` (`id`, `body`, `created_on`, `admin_id`, `journal_id`) VALUES
(1, 'Please add a little more detail to the comments', '2020-06-02 16:52:51.833528', 1, 1),
(2, 'Please add a little more detail to the comments', '2020-06-02 16:53:43.456054', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `journals_comments`
--

CREATE TABLE `journals_comments` (
  `id` int(11) NOT NULL,
  `journal_id` int(11) NOT NULL,
  `active` tinyint(1) NOT NULL,
  `body` longtext NOT NULL,
  `created_on` datetime(6) NOT NULL,
  `email` varchar(254) NOT NULL,
  `name` varchar(80) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `journals_comments`
--

INSERT INTO `journals_comments` (`id`, `journal_id`, `active`, `body`, `created_on`, `email`, `name`) VALUES
(4, 7, 0, 'Test Comment', '2020-06-01 10:38:26.753559', 'ashargillani@yahoo.com', 'Syed Ashar Gillani'),
(5, 5, 0, 'Test Comment', '2020-06-01 10:50:59.258057', 'ashargillani@yahoo.com', 'Syed Ashar Gillani'),
(6, 5, 0, 'Test Comment', '2020-06-01 11:01:53.410280', 'ashargillani@yahoo.com', 'Syed Ashar Gillani'),
(7, 5, 0, 'Test Comment', '2020-06-01 11:02:44.901503', 'ashargillani@yahoo.com', 'Syed Ashar Gillani'),
(8, 5, 0, 'Test Comment', '2020-06-01 11:21:41.137208', 'ashargillani@yahoo.com', 'Syed Ashar Gillani'),
(9, 5, 0, 'Test Comment', '2020-06-01 11:30:00.278702', 'ashargillani@yahoo.com', 'Syed Ashar Gillani');

-- --------------------------------------------------------

--
-- Table structure for table `journals_journal`
--

CREATE TABLE `journals_journal` (
  `id` int(11) NOT NULL,
  `title` varchar(100) NOT NULL,
  `body` longtext DEFAULT NULL,
  `status` varchar(10) NOT NULL,
  `published_at` datetime(6) DEFAULT NULL,
  `created_at` datetime(6) NOT NULL,
  `last_updated` datetime(6) NOT NULL,
  `author_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `journals_journal`
--

INSERT INTO `journals_journal` (`id`, `title`, `body`, `status`, `published_at`, `created_at`, `last_updated`, `author_id`) VALUES
(1, 'New Survey', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis pharetra varius quam sit amet vulputate. Quisque mauris augue, molestie tincidunt condimentum vitae, gravida a libero. Aenean sit amet felis dolor, in sagittis nisi. Sed ac orci quis tortor imperdiet venenatis. Duis elementum auctor accumsan. Aliquam in felis sit amet augue.', 'PENDING', NULL, '2020-04-13 11:33:01.270084', '2020-04-13 11:33:01.270084', 1),
(5, 'New Survey 2', 'This is the test paragraph for the journal\'s body.. This is the test paragraph for the journal\'s body.. This is the test paragraph for the journal\'s body.. This is the test paragraph for the journal\'s body.. This is the test paragraph for the journal\'s body.. This is the test paragraph for the journal\'s body.. This is the test paragraph for the journal\'s body.. This is the test paragraph for the journal\'s body..', 'REJECTED', NULL, '2020-04-13 11:33:01.270084', '2020-04-13 11:33:01.270084', 1),
(7, 'When the winds of change blow, some people build walls and others build windmills.” – Chinese prover', '<p><span style=\"color: rgb(33, 34, 38); font-family: Merriweather, serif; font-size: 18px;\">The winds of change can be welcome, pushing you to a better world. For many teachers in Wales today the current climate feels more like a storm. In 1999 the first elections to the newly established Welsh Assembly were held, and ever since, Wales has embarked on its own learning curve. As with every learning process, the country has experienced successes, as well as failures.&nbsp; Soon after the first decade of devolution, PISA and other results, caused much soul searching and criticism of national policy. A series of powerful winds blew through the system. A new process of school categorization, a qualifications review, establishment of regional education consortia, an initial teacher education review, and a major review of the school curriculum by Professor Graham Donaldson, were all held within a short space of time.&nbsp; Professor Donaldson has challenged Wales to build for a successful future. In response, we must be confident that we can build windmills rather than walls.<br></span><img src=\"/files/django-summernote/2020-06-01/bd5d4900-f50c-4014-b2e1-d072590dd33a.PNG\" style=\"width: 698px;\"><br><br>.<br>Image test successfully done</p>', 'Published', NULL, '2020-06-01 12:38:09.008226', '2020-06-02 12:39:12.354011', 1);

-- --------------------------------------------------------

--
-- Table structure for table `user_management_role`
--

CREATE TABLE `user_management_role` (
  `id` int(11) NOT NULL,
  `title` varchar(20) NOT NULL,
  `description` varchar(200) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `last_updated` datetime(6) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user_management_user`
--

CREATE TABLE `user_management_user` (
  `id` int(11) NOT NULL,
  `password` varchar(128) NOT NULL,
  `last_login` datetime(6) DEFAULT NULL,
  `is_superuser` tinyint(1) NOT NULL,
  `username` varchar(150) NOT NULL,
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(150) NOT NULL,
  `email` varchar(254) NOT NULL,
  `is_staff` tinyint(1) NOT NULL,
  `is_active` tinyint(1) NOT NULL,
  `date_joined` datetime(6) NOT NULL,
  `address` longtext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_management_user`
--

INSERT INTO `user_management_user` (`id`, `password`, `last_login`, `is_superuser`, `username`, `first_name`, `last_name`, `email`, `is_staff`, `is_active`, `date_joined`, `address`) VALUES
(1, 'pbkdf2_sha256$180000$xuwuXZZW5cAE$wOsZWLNe/MLCSkfBzI1a9D+598LVQ0m8M6dpE1gNMxc=', '2020-06-01 12:06:56.659073', 1, 'ashargillani', 'Ashar', 'Gillani', 'ashargillani@yahoo.com', 1, 1, '2020-03-26 07:49:23.000000', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user_management_userrole`
--

CREATE TABLE `user_management_userrole` (
  `id` int(11) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `last_updated` datetime(6) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `approver_id` int(11) DEFAULT NULL,
  `role_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user_management_user_groups`
--

CREATE TABLE `user_management_user_groups` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_management_user_groups`
--

INSERT INTO `user_management_user_groups` (`id`, `user_id`, `group_id`) VALUES
(1, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `user_management_user_user_permissions`
--

CREATE TABLE `user_management_user_user_permissions` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account_emailaddress`
--
ALTER TABLE `account_emailaddress`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `account_emailaddress_user_id_2c513194_fk_user_management_user_id` (`user_id`);

--
-- Indexes for table `account_emailconfirmation`
--
ALTER TABLE `account_emailconfirmation`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `key` (`key`),
  ADD KEY `account_emailconfirm_email_address_id_5b7f8c58_fk_account_e` (`email_address_id`);

--
-- Indexes for table `alumni_alumni`
--
ALTER TABLE `alumni_alumni`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `phone` (`phone`),
  ADD KEY `alumni_alumni_requestor_id_ea462da5_fk_user_management_user_id` (`requestor_id`);

--
-- Indexes for table `auditorium_auditoriumreservation`
--
ALTER TABLE `auditorium_auditoriumreservation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `auditorium_auditoriu_requestor_id_de5ac066_fk_user_mana` (`requestor_id`);

--
-- Indexes for table `authtoken_token`
--
ALTER TABLE `authtoken_token`
  ADD PRIMARY KEY (`key`),
  ADD UNIQUE KEY `user_id` (`user_id`);

--
-- Indexes for table `auth_group`
--
ALTER TABLE `auth_group`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `auth_group_permissions`
--
ALTER TABLE `auth_group_permissions`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `auth_group_permissions_group_id_permission_id_0cd325b0_uniq` (`group_id`,`permission_id`),
  ADD KEY `auth_group_permissio_permission_id_84c5c92e_fk_auth_perm` (`permission_id`);

--
-- Indexes for table `auth_permission`
--
ALTER TABLE `auth_permission`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `auth_permission_content_type_id_codename_01ab375a_uniq` (`content_type_id`,`codename`);

--
-- Indexes for table `chat_chat`
--
ALTER TABLE `chat_chat`
  ADD PRIMARY KEY (`id`),
  ADD KEY `chat_chat_room_id_db235889_fk_chat_room_id` (`room_id`),
  ADD KEY `chat_chat_user_id_bbe8a5b9_fk_user_management_user_id` (`user_id`);

--
-- Indexes for table `chat_room`
--
ALTER TABLE `chat_room`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `chat_roomuser`
--
ALTER TABLE `chat_roomuser`
  ADD PRIMARY KEY (`id`),
  ADD KEY `chat_roomuser_room_id_f8d1f8aa_fk_chat_room_id` (`room_id`),
  ADD KEY `chat_roomuser_user_id_6b7f07c3_fk_user_management_user_id` (`user_id`);

--
-- Indexes for table `daycare_daycarereservation`
--
ALTER TABLE `daycare_daycarereservation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `daycare_daycarereser_requestor_id_46ab410d_fk_user_mana` (`requestor_id`);

--
-- Indexes for table `django_content_type`
--
ALTER TABLE `django_content_type`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `django_content_type_app_label_model_76bd3d3b_uniq` (`app_label`,`model`);

--
-- Indexes for table `django_migrations`
--
ALTER TABLE `django_migrations`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `django_session`
--
ALTER TABLE `django_session`
  ADD PRIMARY KEY (`session_key`),
  ADD KEY `django_session_expire_date_a5c62663` (`expire_date`);

--
-- Indexes for table `django_site`
--
ALTER TABLE `django_site`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `django_site_domain_a2e37b91_uniq` (`domain`);

--
-- Indexes for table `django_summernote_attachment`
--
ALTER TABLE `django_summernote_attachment`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `journals_admincomments`
--
ALTER TABLE `journals_admincomments`
  ADD PRIMARY KEY (`id`),
  ADD KEY `journals_admincommen_admin_id_3bfb9194_fk_user_mana` (`admin_id`),
  ADD KEY `journals_admincommen_journal_id_8447741c_fk_journals_` (`journal_id`);

--
-- Indexes for table `journals_comments`
--
ALTER TABLE `journals_comments`
  ADD PRIMARY KEY (`id`),
  ADD KEY `journals_comments_journal_id_58f3b708_fk_journals_journal_id` (`journal_id`);

--
-- Indexes for table `journals_journal`
--
ALTER TABLE `journals_journal`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `title` (`title`),
  ADD KEY `journals_journal_author_id_b3f903f4` (`author_id`);

--
-- Indexes for table `user_management_role`
--
ALTER TABLE `user_management_role`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_management_user`
--
ALTER TABLE `user_management_user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `user_management_userrole`
--
ALTER TABLE `user_management_userrole`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_management_user_approver_id_8f5b842a_fk_user_mana` (`approver_id`),
  ADD KEY `user_management_user_role_id_5bc8cf6b_fk_user_mana` (`role_id`),
  ADD KEY `user_management_user_user_id_6a4845cd_fk_user_mana` (`user_id`);

--
-- Indexes for table `user_management_user_groups`
--
ALTER TABLE `user_management_user_groups`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `user_management_user_groups_user_id_group_id_bed1779a_uniq` (`user_id`,`group_id`),
  ADD KEY `user_management_user_groups_group_id_6f577055_fk_auth_group_id` (`group_id`);

--
-- Indexes for table `user_management_user_user_permissions`
--
ALTER TABLE `user_management_user_user_permissions`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `user_management_user_use_user_id_permission_id_c71a3268_uniq` (`user_id`,`permission_id`),
  ADD KEY `user_management_user_permission_id_d8c2b1e9_fk_auth_perm` (`permission_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `account_emailaddress`
--
ALTER TABLE `account_emailaddress`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `account_emailconfirmation`
--
ALTER TABLE `account_emailconfirmation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `alumni_alumni`
--
ALTER TABLE `alumni_alumni`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `auditorium_auditoriumreservation`
--
ALTER TABLE `auditorium_auditoriumreservation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `auth_group`
--
ALTER TABLE `auth_group`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `auth_group_permissions`
--
ALTER TABLE `auth_group_permissions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=93;

--
-- AUTO_INCREMENT for table `auth_permission`
--
ALTER TABLE `auth_permission`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=101;

--
-- AUTO_INCREMENT for table `chat_chat`
--
ALTER TABLE `chat_chat`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `chat_room`
--
ALTER TABLE `chat_room`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `chat_roomuser`
--
ALTER TABLE `chat_roomuser`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `daycare_daycarereservation`
--
ALTER TABLE `daycare_daycarereservation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `django_admin_log`
--
ALTER TABLE `django_admin_log`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `django_content_type`
--
ALTER TABLE `django_content_type`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `django_migrations`
--
ALTER TABLE `django_migrations`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT for table `django_site`
--
ALTER TABLE `django_site`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `django_summernote_attachment`
--
ALTER TABLE `django_summernote_attachment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `journals_admincomments`
--
ALTER TABLE `journals_admincomments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `journals_comments`
--
ALTER TABLE `journals_comments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `journals_journal`
--
ALTER TABLE `journals_journal`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `user_management_role`
--
ALTER TABLE `user_management_role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user_management_user`
--
ALTER TABLE `user_management_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `user_management_userrole`
--
ALTER TABLE `user_management_userrole`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user_management_user_groups`
--
ALTER TABLE `user_management_user_groups`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `user_management_user_user_permissions`
--
ALTER TABLE `user_management_user_user_permissions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `account_emailaddress`
--
ALTER TABLE `account_emailaddress`
  ADD CONSTRAINT `account_emailaddress_user_id_2c513194_fk_user_management_user_id` FOREIGN KEY (`user_id`) REFERENCES `user_management_user` (`id`);

--
-- Constraints for table `account_emailconfirmation`
--
ALTER TABLE `account_emailconfirmation`
  ADD CONSTRAINT `account_emailconfirm_email_address_id_5b7f8c58_fk_account_e` FOREIGN KEY (`email_address_id`) REFERENCES `account_emailaddress` (`id`);

--
-- Constraints for table `alumni_alumni`
--
ALTER TABLE `alumni_alumni`
  ADD CONSTRAINT `alumni_alumni_requestor_id_ea462da5_fk_user_management_user_id` FOREIGN KEY (`requestor_id`) REFERENCES `user_management_user` (`id`);

--
-- Constraints for table `auditorium_auditoriumreservation`
--
ALTER TABLE `auditorium_auditoriumreservation`
  ADD CONSTRAINT `auditorium_auditoriu_requestor_id_de5ac066_fk_user_mana` FOREIGN KEY (`requestor_id`) REFERENCES `user_management_user` (`id`);

--
-- Constraints for table `authtoken_token`
--
ALTER TABLE `authtoken_token`
  ADD CONSTRAINT `authtoken_token_user_id_35299eff_fk_user_management_user_id` FOREIGN KEY (`user_id`) REFERENCES `user_management_user` (`id`);

--
-- Constraints for table `auth_group_permissions`
--
ALTER TABLE `auth_group_permissions`
  ADD CONSTRAINT `auth_group_permissio_permission_id_84c5c92e_fk_auth_perm` FOREIGN KEY (`permission_id`) REFERENCES `auth_permission` (`id`),
  ADD CONSTRAINT `auth_group_permissions_group_id_b120cbf9_fk_auth_group_id` FOREIGN KEY (`group_id`) REFERENCES `auth_group` (`id`);

--
-- Constraints for table `auth_permission`
--
ALTER TABLE `auth_permission`
  ADD CONSTRAINT `auth_permission_content_type_id_2f476e4b_fk_django_co` FOREIGN KEY (`content_type_id`) REFERENCES `django_content_type` (`id`);

--
-- Constraints for table `chat_chat`
--
ALTER TABLE `chat_chat`
  ADD CONSTRAINT `chat_chat_room_id_db235889_fk_chat_room_id` FOREIGN KEY (`room_id`) REFERENCES `chat_room` (`id`),
  ADD CONSTRAINT `chat_chat_user_id_bbe8a5b9_fk_user_management_user_id` FOREIGN KEY (`user_id`) REFERENCES `user_management_user` (`id`);

--
-- Constraints for table `chat_roomuser`
--
ALTER TABLE `chat_roomuser`
  ADD CONSTRAINT `chat_roomuser_room_id_f8d1f8aa_fk_chat_room_id` FOREIGN KEY (`room_id`) REFERENCES `chat_room` (`id`),
  ADD CONSTRAINT `chat_roomuser_user_id_6b7f07c3_fk_user_management_user_id` FOREIGN KEY (`user_id`) REFERENCES `user_management_user` (`id`);

--
-- Constraints for table `daycare_daycarereservation`
--
ALTER TABLE `daycare_daycarereservation`
  ADD CONSTRAINT `daycare_daycarereser_requestor_id_46ab410d_fk_user_mana` FOREIGN KEY (`requestor_id`) REFERENCES `user_management_user` (`id`);

--
-- Constraints for table `journals_admincomments`
--
ALTER TABLE `journals_admincomments`
  ADD CONSTRAINT `journals_admincommen_admin_id_3bfb9194_fk_user_mana` FOREIGN KEY (`admin_id`) REFERENCES `user_management_user` (`id`),
  ADD CONSTRAINT `journals_admincommen_journal_id_8447741c_fk_journals_` FOREIGN KEY (`journal_id`) REFERENCES `journals_journal` (`id`);

--
-- Constraints for table `journals_comments`
--
ALTER TABLE `journals_comments`
  ADD CONSTRAINT `journals_comments_journal_id_58f3b708_fk_journals_journal_id` FOREIGN KEY (`journal_id`) REFERENCES `journals_journal` (`id`);

--
-- Constraints for table `journals_journal`
--
ALTER TABLE `journals_journal`
  ADD CONSTRAINT `journals_journal_author_id_b3f903f4_fk_user_management_user_id` FOREIGN KEY (`author_id`) REFERENCES `user_management_user` (`id`);

--
-- Constraints for table `user_management_userrole`
--
ALTER TABLE `user_management_userrole`
  ADD CONSTRAINT `user_management_user_approver_id_8f5b842a_fk_user_mana` FOREIGN KEY (`approver_id`) REFERENCES `user_management_user` (`id`),
  ADD CONSTRAINT `user_management_user_role_id_5bc8cf6b_fk_user_mana` FOREIGN KEY (`role_id`) REFERENCES `user_management_role` (`id`),
  ADD CONSTRAINT `user_management_user_user_id_6a4845cd_fk_user_mana` FOREIGN KEY (`user_id`) REFERENCES `user_management_user` (`id`);

--
-- Constraints for table `user_management_user_groups`
--
ALTER TABLE `user_management_user_groups`
  ADD CONSTRAINT `user_management_user_groups_group_id_6f577055_fk_auth_group_id` FOREIGN KEY (`group_id`) REFERENCES `auth_group` (`id`),
  ADD CONSTRAINT `user_management_user_user_id_092e6f6b_fk_user_mana` FOREIGN KEY (`user_id`) REFERENCES `user_management_user` (`id`);

--
-- Constraints for table `user_management_user_user_permissions`
--
ALTER TABLE `user_management_user_user_permissions`
  ADD CONSTRAINT `user_management_user_permission_id_d8c2b1e9_fk_auth_perm` FOREIGN KEY (`permission_id`) REFERENCES `auth_permission` (`id`),
  ADD CONSTRAINT `user_management_user_user_id_4b8c2c7b_fk_user_mana` FOREIGN KEY (`user_id`) REFERENCES `user_management_user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
