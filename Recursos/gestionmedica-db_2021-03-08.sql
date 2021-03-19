-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 08-03-2021 a las 12:05:14
-- Versión del servidor: 8.0.18
-- Versión de PHP: 7.4.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `gestionmedica`
--
CREATE DATABASE IF NOT EXISTS `gestionmedica` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `gestionmedica`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `agenda`
--

CREATE TABLE `agenda` (
  `id` int(255) NOT NULL,
  `tipo` varchar(255) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `fecha` date NOT NULL,
  `usuario` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `historial`
--

CREATE TABLE `historial` (
  `paciente` varchar(255) NOT NULL,
  `fecha` date NOT NULL,
  `motivo` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `historial`
--

INSERT INTO `historial` (`paciente`, `fecha`, `motivo`) VALUES
('74041334X', '2021-03-24', 'Rotura de tibia y perone');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pacientes`
--

CREATE TABLE `pacientes` (
  `dni` varchar(255) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `edad` int(255) NOT NULL,
  `peso` float(255,0) NOT NULL,
  `sexo` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `pacientes`
--

INSERT INTO `pacientes` (`dni`, `nombre`, `edad`, `peso`, `sexo`) VALUES
('74041334X', 'Segundo Paciente', 25, 69, 1),
('77056529C', 'Primer Paciente', 18, 74, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `resultados`
--

CREATE TABLE `resultados` (
  `archivo` varchar(255) NOT NULL,
  `paciente` varchar(255) NOT NULL,
  `fecha` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `resultados`
--

INSERT INTO `resultados` (`archivo`, `paciente`, `fecha`) VALUES
('C:\\Users\\ernes\\OneDrive\\Escritorio\\radiografia.pdf', '74041334X', '2021-03-02'),
('C:\\Users\\ernes\\OneDrive\\Escritorio\\electro.pdf', '77056529C', '2021-02-04');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tratamientos`
--

CREATE TABLE `tratamientos` (
  `paciente` varchar(255) NOT NULL,
  `motivo` varchar(255) NOT NULL,
  `tratamiento` varchar(255) NOT NULL,
  `tiempo` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tratamientos`
--

INSERT INTO `tratamientos` (`paciente`, `motivo`, `tratamiento`, `tiempo`) VALUES
('74041334X', 'Trastorno por déficit de atención', 'Medikinet 40mg', '2021-04-08');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `dni` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `contrasena` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`dni`, `email`, `contrasena`) VALUES
('11111111J', 'primeremail@gmail.com', '12345');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `agenda`
--
ALTER TABLE `agenda`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_dni_usuario` (`usuario`);

--
-- Indices de la tabla `historial`
--
ALTER TABLE `historial`
  ADD KEY `fk_dni_paciente_historico` (`paciente`);

--
-- Indices de la tabla `pacientes`
--
ALTER TABLE `pacientes`
  ADD PRIMARY KEY (`dni`);

--
-- Indices de la tabla `resultados`
--
ALTER TABLE `resultados`
  ADD KEY `fk_dni_paciente_resultado` (`paciente`);

--
-- Indices de la tabla `tratamientos`
--
ALTER TABLE `tratamientos`
  ADD KEY `fk_dni_paciente` (`paciente`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`dni`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `agenda`
--
ALTER TABLE `agenda`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `agenda`
--
ALTER TABLE `agenda`
  ADD CONSTRAINT `fk_dni_usuario` FOREIGN KEY (`usuario`) REFERENCES `usuarios` (`dni`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Filtros para la tabla `historial`
--
ALTER TABLE `historial`
  ADD CONSTRAINT `fk_dni_paciente_historico` FOREIGN KEY (`paciente`) REFERENCES `pacientes` (`dni`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Filtros para la tabla `resultados`
--
ALTER TABLE `resultados`
  ADD CONSTRAINT `fk_dni_paciente_resultado` FOREIGN KEY (`paciente`) REFERENCES `pacientes` (`dni`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Filtros para la tabla `tratamientos`
--
ALTER TABLE `tratamientos`
  ADD CONSTRAINT `fk_dni_paciente` FOREIGN KEY (`paciente`) REFERENCES `pacientes` (`dni`) ON DELETE RESTRICT ON UPDATE RESTRICT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
