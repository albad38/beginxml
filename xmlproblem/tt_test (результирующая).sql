-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Время создания: Мар 29 2021 г., 04:44
-- Версия сервера: 10.4.18-MariaDB
-- Версия PHP: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `tt_test`
--

-- --------------------------------------------------------

--
-- Структура таблицы `humans`
--

CREATE TABLE `humans` (
  `id` int(5) NOT NULL,
  `fname` text NOT NULL,
  `sname` text NOT NULL,
  `tname` text NOT NULL,
  `job` text NOT NULL,
  `experience` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `humans`
--

INSERT INTO `humans` (`id`, `fname`, `sname`, `tname`, `job`, `experience`) VALUES
(1, 'Колокольцев', 'Юриий', 'Семенович', 'Финансист', 20),
(2, 'Мозговиков', 'Дмитрий', 'Харитонович', 'Программист', 10),
(3, 'Уланова', 'Зоя', 'Игоревна', 'Бухгалтер', 34),
(4, 'Елистратов', 'Николай', 'Александрович', 'Продавец-консультант', 4.3),
(5, 'Алексеева', 'Наталья', 'Романовна', 'Администратор гостиницы', 6.8),
(6, 'Шаповалов', 'Леонид', 'Владимирович', 'Шеф-повар', 11.7),
(7, 'Громовой ', 'Вадим', 'Алексеевич', 'Строитель', 12),
(8, 'Дудочкина', 'Марина', 'Юрьевна', 'Врач-окулист', 9),
(9, 'Корнеев', 'Олег', 'Сергеевич', 'Архитектор', 15.2),
(10, 'Маркова', 'Галина', 'Петровна', 'Стюардесса', 3),
(20, 'Metel', 'Zhanna', 'Petrovna', 'Journalist', 15),
(21, 'Letuchy', 'Semen', 'Borisovich', 'Hauler', 11.5),
(24, 'Tinkova', 'Yulia', 'Agafonovna', 'Cassier', 2),
(42, 'Polyakova', 'Svetlana', 'Mikhailovna', 'Analytic', 11.5),
(55, 'Risovalkin', 'Fedor', 'Afanasievich', 'Manager', 7);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `humans`
--
ALTER TABLE `humans`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `humans`
--
ALTER TABLE `humans`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=56;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
