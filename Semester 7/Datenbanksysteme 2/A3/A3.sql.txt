DROP TABLE ProfessorTab;
DROP TABLE FakultaetTab;
DROP TABLE StudiengangTab;
DROP TABLE StudierenderTab;
DROP TABLE VorlesungTab;
DROP TABLE PruefungsergebnisTab;

DROP TYPE PruefungsergebnisT;
DROP TYPE VorlesungT;
DROP TYPE StudierenderT;
DROP TYPE StudiengangT;
DROP TYPE FakultaetT;
DROP TYPE ProfessorenVA;
DROP TYPE ProfessorT;
DROP TYPE PersonT;
DROP TYPE BODY StudierenderT;

CREATE OR REPLACE TYPE PruefungsergebnisT AS OBJECT
(
    PruefungsDatum DATE,
    Note          NUMBER,
    Vorlesung     REF VORLESUNGT,
    Studierender  REF STUDIERENDERT
) FINAL;

CREATE OR REPLACE TYPE PersonT AS OBJECT
(name VARCHAR2(40)) NOT FINAL;

CREATE OR REPLACE TYPE ProfessorT UNDER PersonT
(fachgebiet VARCHAR2(40),
buero CHAR(4)) FINAL;

CREATE OR REPLACE TYPE ProfessorenVA AS VARRAY(50) OF REF ProfessorT;

CREATE OR REPLACE TYPE FakultaetT AS OBJECT
(name VARCHAR2(20),
 dekan REF ProfessorT,
 professoren ProfessorenVA) FINAL;

CREATE OR REPLACE TYPE StudiengangT AS OBJECT
(name VARCHAR2(40),
fakultaet REF FakultaetT) FINAL;

CREATE OR REPLACE TYPE StudierenderT UNDER PersonT
(matrikelnummer VARCHAR(6),
 studiengang REF StudiengangT,
 MEMBER FUNCTION getNotenschnitt RETURN FLOAT) FINAL;

CREATE OR REPLACE TYPE VorlesungT AS OBJECT
(name VARCHAR2(40),
professor REF ProfessorT) FINAL;

CREATE TABLE ProfessorTab OF ProfessorT;
CREATE TABLE FakultaetTab OF FakultaetT (dekan SCOPE IS ProfessorTab);
CREATE TABLE StudiengangTab OF StudiengangT (fakultaet SCOPE IS FakultaetTab);
CREATE TABLE StudierenderTab OF StudierenderT (studiengang SCOPE IS StudiengangTab);
CREATE TABLE VorlesungTab OF VorlesungT (professor SCOPE IS ProfessorTab);

/*  -------------------------------------- Stuff -------------------------------------- */

CREATE OR REPLACE TYPE PruefungsergebnisT AS OBJECT
(pruefungsDatum Date,
 vorlesung REF VorlesungT,
 student REF StudierenderT,
 note Float);


CREATE TABLE PruefungsergebnisTab OF PruefungsergebnisT
(
Check(note = 1.0 OR note = 1.3 OR note = 1.7
 OR note = 2.0 OR note = 2.3 OR note = 2.7 
 OR note = 3.0 OR note = 3.3 OR note = 3.7
 OR note = 4.0  OR note = 5.0));
 
Delete From ProfessorTab where 1=1;
 
INSERT INTO ProfessorTab VALUES (ProfessorT('Langweg', 'Software Sicherheit', 'B101'));
INSERT INTO ProfessorTab VALUES (ProfessorT('Eck', 'Datenbanken', 'C202'));
INSERT INTO ProfessorTab VALUES (ProfessorT('Drachenfels', 'Programmiertechnik', 'A303'));
INSERT INTO ProfessorTab VALUES (ProfessorT('Schoppa', 'Digitaltechnik', 'D404'));

Delete From FakultaetTab where 1=1;

INSERT INTO  FakultaetTab VALUES(
        FakultaetT('Informatik', 
                (SELECT REF(tab) FROM ProfessorTAB tab WHERE tab.name = 'Langweg'),
                ProfessorenVA((SELECT REF(tab) FROM ProfessorTAB tab WHERE tab.name = 'Langweg'),
                (SELECT REF(tab) FROM ProfessorTAB tab WHERE tab.name = 'Eck'),
                (SELECT REF(tab) FROM ProfessorTAB tab WHERE tab.name = 'Drachenfels')
                )
         )
 );
INSERT INTO  FakultaetTab VALUES(
        FakultaetT('BWL', 
                (SELECT REF(tab) FROM ProfessorTAB tab WHERE tab.name = 'Schoppa'),
        ProfessorenVA(
                (SELECT REF(tab) FROM ProfessorTAB tab WHERE tab.name = 'Schoppa')            
                )
        )
);

Delete From VorlesungTab where 1=1;
 
INSERT INTO VorlesungTab VALUES(
        VorlesungT('Digitaltechnik', (SELECT REF(tab) FROM ProfessorTAB tab WHERE tab.name = 'Schoppa'))
);
INSERT INTO VorlesungTab VALUES(
        VorlesungT('DBSYS', (SELECT REF(tab) FROM ProfessorTAB tab WHERE tab.name = 'Eck'))
);
INSERT INTO VorlesungTab VALUES(
        VorlesungT('Programmiertechnik', (SELECT REF(tab) FROM ProfessorTAB tab WHERE tab.name = 'Drachenfels'))
);

Delete From StudiengangTab where 1=1;

INSERT INTO StudiengangTab VALUES(
        StudiengangT('AIN', (SELECT REF(tab) FROM FakultaetTab tab WHERE tab.name = 'Informatik'))
);        
INSERT INTO StudiengangTab VALUES(
        StudiengangT('WIN', (SELECT REF(tab) FROM FakultaetTab tab WHERE tab.name = 'Informatik'))        
);
INSERT INTO StudiengangTab VALUES(
        StudiengangT('BWL-Gang', (SELECT REF(tab) FROM FakultaetTab tab WHERE tab.name = 'BWL'))        
);



Delete From StudierenderTab where 1=1;

INSERT INTO StudierenderTab VALUES(
        StudierenderT('Gustav', '123456', (SELECT REF(tab) FROM StudiengangTab tab WHERE tab.name = 'AIN'))    
);       
INSERT INTO StudierenderTab VALUES(
        StudierenderT('Tick', '424242', (SELECT REF(tab) FROM StudiengangTab tab WHERE tab.name = 'AIN'))    
);
INSERT INTO StudierenderTab VALUES(
        StudierenderT('Trick', '696969', (SELECT REF(tab) FROM StudiengangTab tab WHERE tab.name = 'AIN'))    
);
INSERT INTO StudierenderTab VALUES(
        StudierenderT('Track', '313131', (SELECT REF(tab) FROM StudiengangTab tab WHERE tab.name = 'WIN'))    
);
INSERT INTO StudierenderTab VALUES(
        StudierenderT('Dumbatz', '000000', (SELECT REF(tab) FROM StudiengangTab tab WHERE tab.name = 'BWL-Gang'))    
);

Delete From PruefungsergebnisTab where 1=1;

INSERT INTO  PruefungsergebnisTab VALUES(
        PruefungsergebnisT(
                TO_DATE('01.03.1986', 'DD.MM.YYYY'),                
                (SELECT REF(tab) FROM VorlesungTab tab WHERE tab.name = 'DBSYS'),
                (SELECT REF(tab) FROM StudierenderTab tab WHERE tab.name = 'Gustav'),
                1.7             
        )   
);

INSERT INTO  PruefungsergebnisTab VALUES(
        PruefungsergebnisT(
                TO_DATE('01.03.1986', 'DD.MM.YYYY'),                
                (SELECT REF(tab) FROM VorlesungTab tab WHERE tab.name = 'DBSYS'),
                (SELECT REF(tab) FROM StudierenderTab tab WHERE tab.name = 'Tick'),
                1.3             
        )   
);
INSERT INTO  PruefungsergebnisTab VALUES(
        PruefungsergebnisT(
                TO_DATE('01.03.1986', 'DD.MM.YYYY'),                
                (SELECT REF(tab) FROM VorlesungTab tab WHERE tab.name = 'DBSYS'),
                (SELECT REF(tab) FROM StudierenderTab tab WHERE tab.name = 'Trick'),
                2.3             
        )   
);
INSERT INTO  PruefungsergebnisTab VALUES(
        PruefungsergebnisT(
                TO_DATE('01.03.1986', 'DD.MM.YYYY'),                
                (SELECT REF(tab) FROM VorlesungTab tab WHERE tab.name = 'DBSYS'),
                (SELECT REF(tab) FROM StudierenderTab tab WHERE tab.name = 'Track'),
                5.0             
        )   
);
INSERT INTO  PruefungsergebnisTab VALUES(
        PruefungsergebnisT(
                TO_DATE('03.03.1986', 'DD.MM.YYYY'),                
                (SELECT REF(tab) FROM VorlesungTab tab WHERE tab.name = 'Programmiertechnik'),
                (SELECT REF(tab) FROM StudierenderTab tab WHERE tab.name = 'Gustav'),
                1.0             
        )   
);
INSERT INTO  PruefungsergebnisTab VALUES(
        PruefungsergebnisT(
                TO_DATE('31.03.1986', 'DD.MM.YYYY'),                
                (SELECT REF(tab) FROM VorlesungTab tab WHERE tab.name = 'Digitaltechnik'),
                (SELECT REF(tab) FROM StudierenderTab tab WHERE tab.name = 'Gustav'),
                2.0           
        )   
);
INSERT INTO  PruefungsergebnisTab VALUES(
        PruefungsergebnisT(
                TO_DATE('03.03.1986', 'DD.MM.YYYY'),                
                (SELECT REF(tab) FROM VorlesungTab tab WHERE tab.name = 'Programmiertechnik'),
                (SELECT REF(tab) FROM StudierenderTab tab WHERE tab.name = 'Track'),
                5.0             
        )   
);
INSERT INTO  PruefungsergebnisTab VALUES(
        PruefungsergebnisT(
                TO_DATE('31.03.1986', 'DD.MM.YYYY'),                
                (SELECT REF(tab) FROM VorlesungTab tab WHERE tab.name = 'Digitaltechnik'),
                (SELECT REF(tab) FROM StudierenderTab tab WHERE tab.name = 'Track'),
                3.0            
        )   
);

SELECT tab.name FROM FakultaetTab tab;
SELECT * FROM ProfessorTab tab;
SELECT tab.professor.name FROM VorlesungTab tab;
SELECT tab.name, tab.matrikelnummer, tab.studiengang.name FROM StudierenderTab tab;
SELECT tab.pruefungsDatum, tab.vorlesung.name, tab.student.name, tab.note  FROM PruefungsergebnisTab tab;

-------------------------------------- A2 -------------------------------------- 

---   2 a --- 
SELECT tab.student.name FROM PruefungsergebnisTab tab WHERE tab.note < 2.0 AND tab.vorlesung.name = 'DBSYS';

---   2 b --- 
SELECT name FROM StudierenderTab tab WHERE tab.studiengang.fakultaet.dekan.name = 'Langweg';

---  2 c --- 
SELECT p.COLUMN_VALUE.name AS Name
FROM FakultaetTab f, table(f.professoren) p
WHERE f.name = 'Informatik';

--- A3 --- 
--/
CREATE OR REPLACE TYPE BODY StudierenderT AS
        MEMBER FUNCTION getNotenschnitt RETURN FLOAT IS        
                schnitt FLOAT;
        BEGIN
                SELECT AVG(tab.note) INTO schnitt FROM PruefungsergebnisTab tab WHERE tab.student.matrikelnummer = self.matrikelnummer;
                RETURN ROUND(schnitt, 2);
        END getNotenschnitt;
END;
/
SELECT s.matrikelnummer, s.name, s.getNotenschnitt() as Notenschnitt
FROM studierendertab s;