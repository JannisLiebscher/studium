--iban anstatt Kundennummer (keine Vorhanden in unseren Tabellen)
CREATE OR REPLACE VIEW BuchungsCount (bc_mail, count_buchungen) AS
    SELECT k.mailadresse, COUNT(b.buchungsnummer) FROM Kunde k LEFT OUTER JOIN Buchung b ON k.mailadresse = b.mailadresse
    GROUP BY k.mailadresse; 
    
SELECT * FROM BuchungsCount;

CREATE OR REPLACE VIEW StornierungsCount (sc_mail, count_stornierungen) AS
    SELECT k.mailadresse, COUNT(s.buchungsnummer) FROM Kunde k LEFT OUTER JOIN Stornierungen s ON k.mailadresse = s.mailadresse
    GROUP BY k.mailadresse;  
    
SELECT * FROM StornierungsCount; 

CREATE OR REPLACE VIEW AnzahlungsSum (az_mail, sum_anzahlung) AS
    SELECT b.mailadresse, SUM(a.betrag_anzahlung) FROM Buchung b LEFT OUTER JOIN Anzahlung a ON b.buchungsnummer = a.buchungsnummer
    GROUP BY b.mailadresse;
    
SELECT * FROM AnzahlungsSUM;
/*CREATE OR REPLACE VIEW AnzahlungsSumMail (az_mail, az_bNR, sum_anzahlung) AS 
    SELECT b.mailadresse, sum_anzahlung FROM Buchung b LEFT OUTER JOIN AnzahlungsSum a ON b.buchungsnummer = az_bNR
    GROUP BY b.mailadresse;*/

CREATE OR REPLACE VIEW Kundenstatistik (mail, anz_buchungen, anz_stornierungen, sum_zahlungen) AS 
    SELECT bc_mail, count_buchungen, count_stornierungen, sum_anzahlung
    FROM BuchungsCount LEFT OUTER JOIN StornierungsCount ON bc_mail = sc_mail
    LEFT OUTER JOIN AnzahlungsSum a ON bc_mail = az_mail;     
        
        
--Ausgabe des Views        
SELECT * FROM Kundenstatistik;
--Wenn man bei Summe aller Zahlungen 0 anstatt NULL möchte.
SELECT mail, anz_buchungen, anz_stornierungen, COALESCE(sum_zahlungen, 0) AS sum_zahlungen FROM Kundenstatistik; --Kohlski

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