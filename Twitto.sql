-- phpMyAdmin SQL Dump
-- version 4.2.12deb2+deb8u2
-- http://www.phpmyadmin.net
--
-- Client :  localhost
-- Généré le :  Mar 27 Février 2018 à 11:57
-- Version du serveur :  5.5.59-0+deb8u1
-- Version de PHP :  5.6.33-0+deb8u1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `Twitto`
--

-- --------------------------------------------------------

--
-- Structure de la table `Connexions`
--

CREATE TABLE IF NOT EXISTS `Connexions` (
  `cle` varchar(200) NOT NULL,
  `id_user` int(50) NOT NULL,
  `debut` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `root` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `Connexions`
--

INSERT INTO `Connexions` (`cle`, `id_user`, `debut`, `root`) VALUES
('abkdkldlieowkkkdklsl', 1, '2018-02-27 10:57:05', -1),
('jksklekllkelellelkk', 2, '2018-02-27 10:57:05', -1);

-- --------------------------------------------------------

--
-- Structure de la table `Friends`
--

CREATE TABLE IF NOT EXISTS `Friends` (
  `id_user1` int(50) NOT NULL,
  `id_user2` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `Users`
--

CREATE TABLE IF NOT EXISTS `Users` (
`id_user` int(50) NOT NULL,
  `login` varchar(50) NOT NULL,
  `password` varchar(200) NOT NULL,
  `nom` varchar(100) NOT NULL,
  `prenom` varchar(100) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `Users`
--

INSERT INTO `Users` (`id_user`, `login`, `password`, `nom`, `prenom`) VALUES
(1, 'sidi1', '123s', 'diallo', 'bouba'),
(2, 'oumar2', '123o', 'bah', 'oumar'),
(5, 'fanta3', '123f', 'barry', 'fatou'),
(6, 'sadio4', '123s', 'camara', 'sadio');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `Connexions`
--
ALTER TABLE `Connexions`
 ADD PRIMARY KEY (`cle`), ADD KEY `id_user` (`id_user`), ADD KEY `cle` (`cle`,`id_user`,`debut`);

--
-- Index pour la table `Friends`
--
ALTER TABLE `Friends`
 ADD PRIMARY KEY (`id_user1`,`id_user2`), ADD KEY `id_user2` (`id_user2`);

--
-- Index pour la table `Users`
--
ALTER TABLE `Users`
 ADD PRIMARY KEY (`id_user`), ADD UNIQUE KEY `login` (`login`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `Users`
--
ALTER TABLE `Users`
MODIFY `id_user` int(50) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `Connexions`
--
ALTER TABLE `Connexions`
ADD CONSTRAINT `Connexions_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `Users` (`id_user`);

--
-- Contraintes pour la table `Friends`
--
ALTER TABLE `Friends`
ADD CONSTRAINT `Friends_ibfk_1` FOREIGN KEY (`id_user1`) REFERENCES `Users` (`id_user`),
ADD CONSTRAINT `Friends_ibfk_2` FOREIGN KEY (`id_user2`) REFERENCES `Users` (`id_user`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
