--Falls man auch Indizes für die Join Abfragen braucht:
CREATE INDEX besitztFW ON besitzt(name_ferienwohnung);
CREATE INDEX BuchungFW ON Buchung(name_ferienwohnung);
CREATE INDEX FerienwohnungFW ON Ferienwohnung(name_ferienwohnung);

--Falls nein, dann ab hier:
CREATE INDEX Ferienwohnung_land ON Ferienwohnung(name_land);
CREATE INDEX besitzt_ausstattung ON besitzt(name_ausstattung);
CREATE INDEX Buchung_FW_an_ab ON Buchung(name_ferienwohnung, anreisedatum, abreisedatum);

--Auszug aus Aufgabe 5i)
SELECT b.name_ferienwohnung, avg(b.bewertung)
FROM Ferienwohnung f INNER JOIN besitzt a ON a.name_ferienwohnung = f.name_ferienwohnung
LEFT OUTER JOIN Buchung b ON b.name_ferienwohnung = f.name_ferienwohnung
WHERE f.name_land = 'Deutschland'
AND a.name_ausstattung = 'Sauna'
AND NOT EXISTS (SELECT buchungsnummer FROM Buchung b2
                WHERE b.name_ferienwohnung = b2.name_ferienwohnung
                AND (b2.abreisedatum BETWEEN TO_DATE('01.11.2021', 'DD.MM.YYYY') AND TO_DATE('21.11.2021', 'DD.MM.YYYY')
                OR (b2.anreisedatum BETWEEN TO_DATE('01.11.2021', 'DD.MM.YYYY') AND TO_DATE('21.11.2021', 'DD.MM.YYYY'))
                OR (b2.anreisedatum < TO_DATE('01.11.2021', 'DD.MM.YYYY') AND b2.abreisedatum > TO_DATE('21.11.2021', 'DD.MM.YYYY'))))
GROUP BY b.name_ferienwohnung
ORDER BY avg(b.bewertung) DESC
NULLS LAST;

select * from table(dbms_xplan.display_cursor(sql_id=>'0n69ght0s1nhs', format=>'ALLSTATS LAST'));

DROP INDEX Ferienwohnung_land ;





















--Lösung b: vielleicht ist es schneller nur 2 Spalten zu nehmen, weil es sein kann das eines der Daten reicht
CREATE INDEX Ferienwohnung_land ON Ferienwohnung(name_land);
CREATE INDEX besitzt_ausstattung ON besitzt(name_ausstattung);
CREATE INDEX Buchung_FW_an ON Buchung(name_ferienwohnung, anreisedatum);
CREATE INDEX Buchung_FW_ab ON Buchung(name_ferienwohnung, abreisedatum);
