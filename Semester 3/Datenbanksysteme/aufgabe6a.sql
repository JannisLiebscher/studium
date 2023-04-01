--Table Stornierungen als Clone Buchungstabelle erstellen, mit zusätzlicher Spalte
CREATE TABLE Stornierungen AS SELECT * FROM Buchung WHERE 1 = 0;
/*SELECT * INTO Stornierungen FROM BUCHUNG WHERE 1 = 0;*/
ALTER TABLE Stornierungen ADD stornierungsdatum date;

--Trigger um stornierte Buchungen im Table Stornierungen zu speichern
CREATE OR REPLACE TRIGGER storno
    BEFORE DELETE ON Buchung
    FOR EACH ROW
    BEGIN 
        INSERT INTO Stornierungen( buchungsnummer, name_ferienwohnung, 
            mailadresse, buchungsdatum, anreisedatum, abreisedatum, rechnungsnummer,
            betrag, rechnungsdatum, bewertung, bewertungsdatum, stornierungsdatum)
        VALUES( :old.buchungsnummer, :old.name_ferienwohnung, 
            :old.mailadresse, :old.buchungsdatum, :old.anreisedatum, :old.abreisedatum, :old.rechnungsnummer,
            :old.betrag, :old.rechnungsdatum, :old.bewertung, :old.bewertungsdatum, SYSDATE);
    END;
/

--welche Ferienwohnungen haben noch keine Buchung?
SELECT f.name_ferienwohnung, count(b.buchungsnummer)
FROM Ferienwohnung f LEFT Outer join buchung b on f.name_ferienwohnung = b.name_ferienwohnung
group by f.name_ferienwohnung
order by count(b.buchungsnummer) desc;

--Ferienwohnung die bisher noch nicht gebucht wurde hinzufügen
INSERT INTO Buchung(buchungsnummer, name_ferienwohnung, mailadresse, buchungsdatum, anreisedatum, abreisedatum, betrag, rechnungsdatum, bewertung, bewertungsdatum)    
    VALUES (buchungsNR.NextVal, 'Leo Messi', 'kylo.ren@webmail.com', TO_DATE('27.10.2021', 'DD.MM.YYYY'), TO_DATE('13.11.2021', 'DD.MM.YYYY'), TO_DATE('20.11.2021', 'DD.MM.YYYY'), 560, TO_DATE('28.10.2021', 'DD.MM.YYYY'), 5, TO_DATE('24.11.2021', 'DD.MM.YYYY'));

--Eben hinzugefügte Ferienwohnung wieder löschen    
DELETE FROM Buchung WHERE name_ferienwohnung = 'Leo Messi';   

--Buchungsnummer herausfinden
SELECT * FROM Anzahlung;

--Über Buchungsnummer löschen
DELETE FROM Buchung WHERE buchungsnummer = 181;  

--Nachschauen ob die Buchung tatsächlich im Table Stornierungen zu finden ist.
SELECT * FROM Stornierungen;

--Rückgängig machen wenn gewünscht
rollback;

/*
CREATE OR REPLACE TRIGGER storno
    BEFORE DELETE FROM Buchung
    FOR EACH ROW
    BEGIN 
        INSERT INTO Stornierungen SELECT * FROM Buchung;
        INSERT INTO Stornierungen (stornierungsdatum) VALUES (SYSDATE);
    END;
/
wieso ging der Trigger nicht? 
*/