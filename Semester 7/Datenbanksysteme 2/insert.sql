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
(30, 'Max Mustermann', 'Deutschland');
INSERT INTO KUNDE (kunde_alter, kunde_name, kunde_land) VALUES
(25, 'Maria Schmidt', 'Österreich');
INSERT INTO KUNDE (kunde_alter, kunde_name, kunde_land) VALUES
(40, 'John Smith', 'USA');
INSERT INTO KUNDE (kunde_alter, kunde_name, kunde_land) VALUES
(28, 'Sophie Dupont', 'Frankreich');

-- Beispiel-Verkäufer
INSERT INTO VERKAEUFER (verkaeufer_geburtsdatum, verkaeufer_name, verkaeufer_fachgebiet) VALUES
(TO_DATE('1980-05-15', 'YYYY-MM-DD'), 'Anna Müller', 'Elektronik');
INSERT INTO VERKAEUFER (verkaeufer_geburtsdatum, verkaeufer_name, verkaeufer_fachgebiet) VALUES
(TO_DATE('1975-08-22', 'YYYY-MM-DD'), 'Peter Johnson', 'Bekleidung');
INSERT INTO VERKAEUFER (verkaeufer_geburtsdatum, verkaeufer_name, verkaeufer_fachgebiet) VALUES
(TO_DATE('1988-03-10', 'YYYY-MM-DD'), 'Elena Garcia', 'Haushaltsgeräte');
INSERT INTO VERKAEUFER (verkaeufer_geburtsdatum, verkaeufer_name, verkaeufer_fachgebiet) VALUES
(TO_DATE('1982-11-05', 'YYYY-MM-DD'), 'Hans Meier', 'Sportartikel');