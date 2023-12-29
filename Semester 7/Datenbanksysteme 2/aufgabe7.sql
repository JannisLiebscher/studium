/* A1 a */ 
SELECT COUNT(DISTINCT p.pnr) AS AnzahlAlleineInAbteilung
FROM Pers p
LEFT JOIN Pers p2 ON p.anr = p2.anr AND p.pnr != p2.pnr
WHERE p2.pnr IS NULL;

/* A1 b  Gibt auch abteilungen wo niemand arbeitet aber das ist ach von der Fragestellung abgedeckt*/
SELECT a.aname
FROM abt a 
LEFT JOIN Pers p ON p.anr = a.anr AND p.beruf = 'Programmierer'
WHERE p.pnr IS NULL;

/* A1 c */
CREATE MATERIALIZED VIEW ProgGehalt AS 
 SELECT AVG(gehalt) AS durchschnitt
 FROM pers 
 WHERE beruf = 'Programmierer';
 
SELECT COUNT(DISTINCT p.pnr)
FROM pers p, ProgGehalt pg
WHERE p.gehalt > pg.durchschnitt;

/* A1 d */
SELECT p2.Name
FROM Pers p1, Pers p2
WHERE p1.jahrg > p2.jahrg
AND p1.pnr = p2.vnr;

/* A1 e */
CREATE MATERIALIZED VIEW Junghans AS 
SELECT p.anr, p.beruf
FROM pers WHERE name = 'Junghans');

SELECT DISTINCT p1.Name
FROM Pers p1, Junghans j
WHERE p1.anr = j.anr
AND p1.beruf = j.beruf;

/* A1 f */
SELECT p1.name
FROM Pers p1
LEFT JOIN Pers p2 ON p1.name = p2.name AND p1.pnr != p2.pnr
WHERE p2.pnr IS NULL;

/* A1 g */
SELECT DISTINCT p.Name
FROM Pers p, Abt a
WHERE p.name = a.aname;

/*A2*/

DROP INDEX indexPers;
CREATE INDEX indexPers ON Pers(name);

EXPLAIN PLAN FOR SELECT p.anr, min(p.gehalt), max(p.gehalt)
FROM Pers p INNER JOIN Abt a ON p.anr = a.anr
WHERE p.beruf = 'Programmierer'
AND p.name = 'Junghans'
AND a.ort = 'Erlangen'
GROUP BY p.anr
ORDER BY p.anr;

SELECT * FROM TABLE(DBMS_XPLAN.DISPLAY);

INSERT INTO pers VALUES
(123457, 'Beck', 1958, TO_DATE('01.09.1983', 'DD.MM.YYYY'), 68000, 'Programmierer', 'K51', NULL );


CREATE INDEX Eintrittsindex ON Pers(eindat);
SELECT UPPER(name), UPPER(beruf)
FROM pers
WHERE eindat >= TO_DATE('1994-01-01', 'YYYY-MM-DD'); /*index wird durch toChar nicht mehr verwendet*/



