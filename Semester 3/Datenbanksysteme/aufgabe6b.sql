--iban anstatt Kundennummer (keine Vorhanden in unseren Tabellen)
CREATE OR REPLACE VIEW Kundenstatistik (iban, anz_buchungen, anz_stornierungen, sum_zahlungen) AS 
    SELECT k.iban, COUNT (b.buchungsnummer), COUNT (s.buchungsnummer), SUM(a.betrag_anzahlung) 
    FROM Kunde k LEFT OUTER JOIN Buchung b ON k.mailadresse = b.mailadresse
    LEFT OUTER JOIN Stornierungen s ON k.mailadresse = s.mailadresse
    LEFT OUTER JOIN Anzahlung a ON b.buchungsnummer = a.buchungsnummer
    GROUP BY k.iban;     
        
--Ausgabe des Views        
SELECT * FROM Kundenstatistik;
--Wenn man bei Summe aller Zahlungen 0 anstatt NULL möchte.
SELECT iban, anz_buchungen, anz_stornierungen, COALESCE(sum_zahlungen, 0) AS sum_zahlungen FROM Kundenstatistik;

--Überprüfung ob Summe der Zahlungen und Anzahl Buchungen stimmen.
SELECT k.vorname, k.nachname, k.iban, a.betrag_anzahlung FROM Buchung b, Anzahlung a, Kunde k 
WHERE b.buchungsnummer = a.buchungsnummer
AND b.mailadresse = k.mailadresse;

--Kundennummer als Spalte in Table Kunde hinzufügen
ALTER TABLE Kunde ADD kundennummer NUMBER;

--Sequenz anlegen die Automatisch neue Kundennummern generiert
CREATE SEQUENCE kundenID INCREMENT BY 1 START WITH 100;

--bestehende Kunden eine Kundennummer zuweisen.
UPDATE Kunde SET kundennummer = kundenID.nextval;

--neuer Trigger für das automatische einfügen von Kundennummern beim erstellen neuer Kunden
CREATE OR REPLACE TRIGGER auto_kundenID
      BEFORE INSERT ON Kunde
      FOR EACH ROW
    BEGIN
      :new.kundennummer := kundenID.nextval;
    END;
/
DROP TRIGGER auto_kundenID;
--Kunde einfügen Test 
INSERT INTO Kunde(vorname, nachname, iban, mailadresse, passwort, newsletter, name_land)    
    VALUES ('Test', 'Person', 'CN50161532165486', 'schli@t-online.de', 'eicbiche20', 'N', 'Deutschland');
----------------    FUNKTIONIERT NICHT MEHR!!!  
    
ALTER TABLE Kunde DROP COLUMN kundennummer;     
--Kunden ausgeben lassen    
SELECT * FROM Kunde, Adresse WHERE Kunde.adress_id = adresse.adress_id;