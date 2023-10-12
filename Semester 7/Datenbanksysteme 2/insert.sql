-- Beispielprodukte
INSERT INTO PRODUKT (produkt_bezeichnung, produkt_preis, produkt_gruppe, produkt_hersteller, produkt_land)
VALUES ('Smartphone', '499.99', 'Elektronik', 'Samsung', 'Deutschland');
INSERT INTO PRODUKT (produkt_bezeichnung, produkt_preis, produkt_gruppe, produkt_hersteller, produkt_land)
VALUES ('Laptop', '799.99', 'Elektronik', 'Dell', 'USA');
INSERT INTO PRODUKT (produkt_bezeichnung, produkt_preis, produkt_gruppe, produkt_hersteller, produkt_land)
VALUES ('Smartwatch', '249.99', 'Elektronik', 'Apple', 'USA');
INSERT INTO PRODUKT (produkt_bezeichnung, produkt_preis, produkt_gruppe, produkt_hersteller, produkt_land)
VALUES ('Schuhe', '249.99', 'Bekleidung', 'Adidas', 'Deutschland');
INSERT INTO PRODUKT (produkt_bezeichnung, produkt_preis, produkt_gruppe, produkt_hersteller, produkt_land)
VALUES ('Schuhe', '349.99', 'Bekleidung', 'Nike', 'USA');
INSERT INTO PRODUKT (produkt_bezeichnung, produkt_preis, produkt_gruppe, produkt_hersteller, produkt_land)
VALUES ('Schuhe', '149.99', 'Bekleidung', 'Puma', 'Deutschland');
INSERT INTO PRODUKT (produkt_bezeichnung, produkt_preis, produkt_gruppe, produkt_hersteller, produkt_land)
VALUES ('Schuhe', '49.99', 'Bekleidung', 'Adidas', 'Deutschland');
INSERT INTO PRODUKT (produkt_bezeichnung, produkt_preis, produkt_gruppe, produkt_hersteller, produkt_land)
VALUES ('Hoodie', '749.99', 'Bekleidung', 'Nike', 'USA');
INSERT INTO PRODUKT (produkt_bezeichnung, produkt_preis, produkt_gruppe, produkt_hersteller, produkt_land)
VALUES ('Hoodie', '849.99', 'Bekleidung', 'Puma', 'Deutschland');

-- Beispielfilialen
INSERT INTO FILIALE (filiale_filialleiter, filiale_stadt, filiale_bezirk, filiale_land)
VALUES ('Max Mustermann', 'Berlin', 'Mitte', 'Deutschland');
INSERT INTO FILIALE (filiale_filialleiter, filiale_stadt, filiale_bezirk, filiale_land)
VALUES ('Jane doe', 'München', 'Schwabing', 'Deutschland');

-- Beispiel-
INSERT INTO KUNDE (kunde_alter, kunde_name, kunde_land) VALUES
(30, 'Max Mustermann', 'Deutschland');
INSERT INTO KUNDE (kunde_alter, kunde_name, kunde_land) VALUES
(25, 'Maria Schmidt', 'Österreich');
INSERT INTO KUNDE (kunde_alter, kunde_name, kunde_land) VALUES
(40, 'John Smith', 'USA');
INSERT INTO KUNDE (kunde_alter, kunde_name, kunde_land) VALUES
(28, 'Sophie Dupont', 'Frankreich');
INSERT INTO KUNDE (kunde_alter, kunde_name, kunde_land) VALUES
(40, 'Peter Alt', 'Frankreich');

-- Beispiel-Verkäufer
INSERT INTO VERKAEUFER (verkaeufer_geburtsdatum, verkaeufer_name, verkaeufer_fachgebiet) VALUES
(TO_DATE('1980-05-15', 'YYYY-MM-DD'), 'Anna Müller', 'Elektronik');
INSERT INTO VERKAEUFER (verkaeufer_geburtsdatum, verkaeufer_name, verkaeufer_fachgebiet) VALUES
(TO_DATE('1975-08-22', 'YYYY-MM-DD'), 'Peter Johnson', 'Bekleidung');
INSERT INTO VERKAEUFER (verkaeufer_geburtsdatum, verkaeufer_name, verkaeufer_fachgebiet) VALUES
(TO_DATE('1988-03-10', 'YYYY-MM-DD'), 'Elena Garcia', 'Haushaltsgeräte');
INSERT INTO VERKAEUFER (verkaeufer_geburtsdatum, verkaeufer_name, verkaeufer_fachgebiet) VALUES
(TO_DATE('1982-11-05', 'YYYY-MM-DD'), 'Hans Meier', 'Sportartikel');

-- Beispielverkäufe
INSERT INTO VERKAUF (verkaeufer_ID, kunde_ID, produkt_ID, filiale_ID, verkauf_datum, verkauf_anzahl)
VALUES (1, 1, 1, 1, TO_DATE('2023-10-12', 'YYYY-MM-DD'), 3);
INSERT INTO VERKAUF (verkaeufer_ID, kunde_ID, produkt_ID, filiale_ID, verkauf_datum, verkauf_anzahl)
VALUES (2, 2, 2, 2, TO_DATE('2023-10-12', 'YYYY-MM-DD'), 1);
INSERT INTO VERKAUF (verkaeufer_ID, kunde_ID, produkt_ID, filiale_ID, verkauf_datum, verkauf_anzahl)
VALUES (3, 3, 6, 1, TO_DATE('2023-10-12', 'YYYY-MM-DD'), 2);
INSERT INTO VERKAUF (verkaeufer_ID, kunde_ID, produkt_ID, filiale_ID, verkauf_datum, verkauf_anzahl)
VALUES (4, 4, 1, 2, TO_DATE('2023-10-12', 'YYYY-MM-DD'), 1);
INSERT INTO VERKAUF (verkaeufer_ID, kunde_ID, produkt_ID, filiale_ID, verkauf_datum, verkauf_anzahl)
VALUES (1, 3, 4, 1, TO_DATE('2023-10-12', 'YYYY-MM-DD'), 2);
INSERT INTO VERKAUF (verkaeufer_ID, kunde_ID, produkt_ID, filiale_ID, verkauf_datum, verkauf_anzahl)
VALUES (2, 2, 3, 2, TO_DATE('2023-10-12', 'YYYY-MM-DD'), 1);
INSERT INTO VERKAUF (verkaeufer_ID, kunde_ID, produkt_ID, filiale_ID, verkauf_datum, verkauf_anzahl)
VALUES (3, 1, 1, 1, TO_DATE('2023-10-12', 'YYYY-MM-DD'), 3);
INSERT INTO VERKAUF (verkaeufer_ID, kunde_ID, produkt_ID, filiale_ID, verkauf_datum, verkauf_anzahl)
VALUES (4, 4, 2, 2, TO_DATE('2023-10-12', 'YYYY-MM-DD'), 1);
INSERT INTO VERKAUF (verkaeufer_ID, kunde_ID, produkt_ID, filiale_ID, verkauf_datum, verkauf_anzahl)
VALUES (1, 2, 3, 1, TO_DATE('2023-10-12', 'YYYY-MM-DD'), 2);
INSERT INTO VERKAUF (verkaeufer_ID, kunde_ID, produkt_ID, filiale_ID, verkauf_datum, verkauf_anzahl)
VALUES (2, 3, 7, 2, TO_DATE('2023-10-12', 'YYYY-MM-DD'), 1);
INSERT INTO VERKAUF (verkaeufer_ID, kunde_ID, produkt_ID, filiale_ID, verkauf_datum, verkauf_anzahl)
VALUES (1, 4, 2, 1, TO_DATE('2023-10-12', 'YYYY-MM-DD'), 1);
INSERT INTO VERKAUF (verkaeufer_ID, kunde_ID, produkt_ID, filiale_ID, verkauf_datum, verkauf_anzahl)
VALUES (1, 2, 3, 1, TO_DATE('2023-10-12', 'YYYY-MM-DD'), 1);
INSERT INTO VERKAUF (verkaeufer_ID, kunde_ID, produkt_ID, filiale_ID, verkauf_datum, verkauf_anzahl)
VALUES (4, 1, 6, 2, TO_DATE('2023-10-12', 'YYYY-MM-DD'), 1);
INSERT INTO VERKAUF (verkaeufer_ID, kunde_ID, produkt_ID, filiale_ID, verkauf_datum, verkauf_anzahl)
VALUES (4, 3, 8, 2, TO_DATE('2023-10-12', 'YYYY-MM-DD'), 1);
INSERT INTO VERKAUF (verkaeufer_ID, kunde_ID, produkt_ID, filiale_ID, verkauf_datum, verkauf_anzahl)
VALUES (3, 1, 5, 1, TO_DATE('2023-10-12', 'YYYY-MM-DD'), 1);
INSERT INTO VERKAUF (verkaeufer_ID, kunde_ID, produkt_ID, filiale_ID, verkauf_datum, verkauf_anzahl)
VALUES (4, 3, 9, 2, TO_DATE('2023-10-12', 'YYYY-MM-DD'), 1);
INSERT INTO VERKAUF (verkaeufer_ID, kunde_ID, produkt_ID, filiale_ID, verkauf_datum, verkauf_anzahl)
VALUES (1, 4, 8, 1, TO_DATE('2023-10-12', 'YYYY-MM-DD'), 1);
INSERT INTO VERKAUF (verkaeufer_ID, kunde_ID, produkt_ID, filiale_ID, verkauf_datum, verkauf_anzahl)
VALUES (1, 5, 8, 1, TO_DATE('2023-10-12', 'YYYY-MM-DD'), 1);
INSERT INTO VERKAUF (verkaeufer_ID, kunde_ID, produkt_ID, filiale_ID, verkauf_datum, verkauf_anzahl)
VALUES (1, 5, 6, 1, TO_DATE('2023-10-12', 'YYYY-MM-DD'), 3);