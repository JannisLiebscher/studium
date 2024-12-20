DROP TABLE VERKAUF;

DROP TABLE KUNDE;
DROP TABLE VERKAEUFER;
DROP TABLE FILIALE;
DROP TABLE PRODUKT;

CREATE TABLE KUNDE (
    kunde_id NUMBER GENERATED by default on null as IDENTITY PRIMARY KEY,
    kunde_alter NUMBER,
    kunde_name VARCHAR(255),
    kunde_land VARCHAR(255)
);

CREATE TABLE VERKAEUFER (
    verkaeufer_id NUMBER GENERATED by default on null as IDENTITY PRIMARY KEY,
    verkaeufer_geburtsdatum DATE,
    verkaeufer_name VARCHAR(255),
    verkaeufer_fachgebiet VARCHAR(255)
);

CREATE TABLE FILIALE (
    filial_ID NUMBER GENERATED by default on null as IDENTITY PRIMARY KEY,
    filiale_filialleiter VARCHAR(255),
    filiale_stadt VARCHAR(255),
    filiale_bezirk VARCHAR(255),
    filiale_land VARCHAR(255)
);
CREATE TABLE PRODUKT (
    produkt_ID NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY PRIMARY KEY,
    produkt_bezeichnung VARCHAR2(255),
    produkt_preis DOUBLE PRECISION,
    produkt_gruppe VARCHAR2(255),
    produkt_hersteller VARCHAR2(255),
    produkt_land VARCHAR2(255)
);
CREATE TABLE VERKAUF (
    verkaeufer_ID NUMBER,
    kunde_ID NUMBER,
    produkt_ID NUMBER,
    filiale_ID NUMBER,
    verkauf_datum DATE,
    verkauf_anzahl NUMBER,
    CONSTRAINT fk_verkauf_verkaeufer FOREIGN KEY (verkaeufer_ID) REFERENCES VERKAEUFER(VERKAEUFER_ID),
    CONSTRAINT fk_verkauf_kunde FOREIGN KEY (kunde_ID) REFERENCES KUNDE(kunde_ID),
    CONSTRAINT fk_verkauf_produkt FOREIGN KEY (produkt_ID) REFERENCES PRODUKT(produkt_ID),
    CONSTRAINT fk_verkauf_filiale FOREIGN KEY (filiale_ID) REFERENCES FILIALE(filial_ID)
);