INSERT INTO public.user_test(id, name, surname, email) VALUES
    (1,'Artur','Ciemiorek','artur@cos.org'),
    (2,'Agata','kupa','kupa@cos.org'),
    (3,'Tadek','Tadeusz','Tadek@cos.org'),
    (4,'Krzysiek','Kszykszy','kazik@cos.org');

INSERT INTO public.book(id, name, author, isbn, user_test_id) VALUES
    (5,'ANNA KArenina','Leo Tolstoy',555555,null),
    (6,'Madame Bovary','Gustave Flaubert',666666,null),
    (7,'War and Peace','Leo Tolstoy',777777,null),
    (8,'Lolota','Vladimir Nabkov',88888888,null),
     (15,'Lolota','Vladimir Nabkov',88888888,null),
      (16,'Lolota','Vladimir Nabkov',88888888,null),
    (9,'ANNA KArenina','Leo Tolstoy',555555,null),
    (10,'ANNA KArenina','Leo Tolstoy',555555,null),
    (11,'Madame Bovary','Gustave Flaubert',666666,null),
    (12,'Madame Bovary','Gustave Flaubert',666666,null),
    (13,'War and Peace','Leo Tolstoy',777777,null),
    (14,'War and Peace','Leo Tolstoy',777777,null);


SELECT setval('public.hibernate_sequence', 20, true);