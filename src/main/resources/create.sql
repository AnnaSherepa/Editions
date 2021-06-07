DROP DATABASE finalProject;
CREATE DATABASE finalProject;
USE finalProject;

CREATE TABLE IF NOT EXISTS genres(
    `idGenre` INTEGER(11) AUTO_INCREMENT NOT NULL PRIMARY KEY,
    `g_name_uk` VARCHAR(255) NOT NULL UNIQUE,
    `g_name_en` VARCHAR(255) NOT NULL UNIQUE
);

INSERT INTO genres(g_name_uk, g_name_en) VALUES
('Фентезі','Fantazy'),
('Фентастика', 'Fantastic'),
('Комедія', 'Comedy'),
('Вірші', 'Poems'),
('Драма', 'Drama');

CREATE TABLE IF NOT EXISTS authors(
    `idAuthor` INTEGER(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `a_name_uk` VARCHAR(255) NOT NULL,
    `a_name_en` VARCHAR(255) NOT NULL
);
INSERT INTO authors(a_name_uk, a_name_en)
VALUES
('Шевченко Тарас Григорович','Shevchenko Taras Hryhorovych'),
('Франко Іван Якович', 'Franko Ivan Yackovych');


CREATE TABLE IF NOT EXISTS  `editions`(
    `idEdition` INTEGER(11) AUTO_INCREMENT NOT NULL PRIMARY KEY,
    `title_uk` VARCHAR(255) NOT NULL,
    `title_en` VARCHAR(255) NOT NULL,
    `imgPath` VARCHAR(255) DEFAULT NULL,
    `description_uk` LONGTEXT DEFAULT NULL,
    `description_en` LONGTEXT DEFAULT NULL,

    `id_genre` INTEGER(11) NOT NULL,
    `id_author` INTEGER(11) NOT NULL,
    `price` DECIMAL(11, 2) DEFAULT 0.00,
    `measurementPrice` VARCHAR(20) DEFAULT 'USD',
    FOREIGN KEY (id_genre) REFERENCES genres(idGenre),
    FOREIGN KEY (id_author) REFERENCES authors(idAuthor)
);

INSERT INTO editions(title_uk, title_en, id_genre, id_author)
VALUES ('Кобзар', 'Kobzar', 4, 1), ('Лис Микита', 'Fox Mykyta', 3, 2);


CREATE TABLE IF NOT EXISTS  users(
    idUser INTEGER(11) AUTO_INCREMENT NOT NULL PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    surname VARCHAR(20) NOT NULL,

    email VARCHAR(255) NOT NULL,
    login VARCHAR(20) NOT NULL,
    pass VARCHAR(20) NOT NULL,

    balance DECIMAL(11, 2) DEFAULT 0.00,
    role ENUM('admin', 'user', 'host'),
    status BOOLEAN DEFAULT false,
    UNIQUE(login)
);

INSERT INTO users(`name`, surname, email, login, pass, role)
VALUES
('Anna', 'Sherepa', 'annasherepa@gmail.com', 'admin', '123456Aa', 'admin'),
('Maria', 'Debian', 'mariadebian@gmail.com', 'user', '123456Aa', 'user'),
('Ivan', 'Ivanor', 'ivanivanov@gmail.com', 'userB', '123456Aa', 'user');

CREATE TABLE IF NOT EXISTS  `users_editions`(
    `userId` INTEGER(11) NOT NULL,
    `editionId` INTEGER(11) NOT NULL,
    `endDate` DATE NULL,

    PRIMARY KEY(`userId`, `editionId`),
    FOREIGN KEY(`userId`) REFERENCES users(idUser) ON DELETE CASCADE,
    FOREIGN KEY(`editionId`) REFERENCES editions(idEdition) ON DELETE CASCADE
);
INSERT INTO users_editions(userId, editionId, endDate)
VALUES
(2, 1, '2021:06:25'),
(2, 2, '2021:06:25'),
(1, 2, '2021:06:25');

