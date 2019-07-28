INSERT INTO role VALUES (1, 'ROLE_USER'),(2, 'ROLE_ADMIN');

INSERT INTO user VALUES (1, 'admin@admin', 1, 'admin' , 'admin', '$2a$10$dHL3TB81KVI9FpApty2PPuiBcp.8eXGdOxhgP9H.FoF4tBP.TBSaS');

INSERT INTO institution  VALUES (1,'Cel i misja: Pomoc dzieciom z ubogich rodzin.', 'Fundacja \"Dbam o Zdrowie\"'),(2,'Cel i misja: Pomoc dla osób nie posiadających miejsca zamieszkania', 'Fundacja \“Bez domu\”'),(3,'Cel i misja: Pomoc osobom znajdującym się w trudnej sytuacji życiowej.', 'Fundacja \“Dla dzieci\"'),(4,'Cel i misja: Pomoc wybudzaniu dzieci ze śpiączki.', 'Fundacja \"A kogo\"');

INSERT INTO category VALUES (1, 'ubrania'),(2, 'zabawki'),(3, 'naczynia'),(4, 'obuwie');

INSERT INTO user_role VALUES (1, 2);