-- Beispielprodukt 1
INSERT INTO PRODUKT (produkt_bezeichnung, produkt_preis, produkt_gruppe, produkt_hersteller, produkt_land)
VALUES ('Smartphone', '499.99', 'Elektronik', 'Samsung', 'Deutschland');

-- Beispielprodukt 2
INSERT INTO PRODUKT (produkt_bezeichnung, produkt_preis, produkt_gruppe, produkt_hersteller, produkt_land)
VALUES ('Laptop', '799.99', 'Elektronik', 'Dell', 'USA');

-- Beispielprodukt 3
INSERT INTO PRODUKT (produkt_bezeichnung, produkt_preis, produkt_gruppe, produkt_hersteller, produkt_land)
VALUES ('Smartwatch', '249.99', 'Elektronik', 'Apple', 'USA');

-- Beispiel-Filiale 1
INSERT INTO FILIALE (filiale_filialleiter, filiale_stadt, filiale_bezirk, filiale_land)
VALUES ('Max Mustermann', 'Berlin', 'Mitte', 'Deutschland');

-- Beispiel-Filiale 2
INSERT INTO FILIALE (filiale_filialleiter, filiale_stadt, filiale_bezirk, filiale_land)
VALUES ('Jane doe', 'München', 'Schwabing', 'Deutschland');

-- Beispiel-Kunden
INSERT INTO KUNDE (kunde_alter, kunde_name, kunde_land) VALUES
(30, 'Max Mustermann', 'Deutschland'),
(25, 'Maria Schmidt', 'Österreich'),
(40, 'John Smith', 'USA'),
(28, 'Sophie Dupont', 'Frankreich');

-- Beispiel-Verkäufer
INSERT INTO VERKAEUFER (verkaeufer_geburtsdatum, verkaeufer_name, verkaeufer_fachgebiet) VALUES
('1980-05-15', 'Anna Müller', 'Elektronik'),
('1975-08-22', 'Peter Johnson', 'Bekleidung'),
('1988-03-10', 'Elena Garcia', 'Haushaltsgeräte'),
('1982-11-05', 'Hans Meier', 'Sportartikel');