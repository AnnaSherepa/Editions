SELECT * from finalproject.users
LEFT JOIN users_editions ue on users.idUser = ue.userId
LEFT JOIN editions e on ue.editionId = e.idEdition
LEFT JOIN authors a on e.id_author = a.idAuthor
LEFT JOIN genres g on e.id_genre = g.idGenre;

SELECT *
FROM finalproject.editions
LEFT JOIN authors a on editions.id_author = a.idAuthor
LEFT JOIN genres g on editions.id_genre = g.idGenre;

SELECT *
FROM finalproject.editions
         LEFT JOIN authors a on editions.id_author = a.idAuthor
         LEFT JOIN genres g on editions.id_genre = g.idGenre

WHERE id_genre = 4;


SELECT *
FROM finalproject.editions
         LEFT JOIN authors a on editions.id_author = a.idAuthor
         LEFT JOIN genres g on editions.id_genre = g.idGenre

ORDER BY price;

SELECT *
FROM finalproject.editions
         LEFT JOIN authors a on editions.id_author = a.idAuthor
         LEFT JOIN genres g on editions.id_genre = g.idGenre

ORDER BY a.a_name_en;

SELECT *
FROM finalproject.editions
         LEFT JOIN authors a on editions.id_author = a.idAuthor
         LEFT JOIN genres g on editions.id_genre = g.idGenre

ORDER BY a.a_name_uk;

SELECT *
FROM finalproject.editions
         LEFT JOIN authors a on editions.id_author = a.idAuthor
         LEFT JOIN genres g on editions.id_genre = g.idGenre

ORDER BY title_uk;

SELECT *
FROM finalproject.editions
         LEFT JOIN authors a on editions.id_author = a.idAuthor
         LEFT JOIN genres g on editions.id_genre = g.idGenre

ORDER BY title_en;

SELECT *
FROM finalproject.editions
         LEFT JOIN authors a on editions.id_author = a.idAuthor
         LEFT JOIN genres g on editions.id_genre = g.idGenre

WHERE title_en LIKE '%a%' OR title_uk LIKE '%Ko%';


SELECT * from users

LIMIT 20 OFFSET 2
;


SELECT COUNT(*) as size FROM users;
